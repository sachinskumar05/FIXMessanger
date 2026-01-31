package com.sachin.qfixmessenger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.Connector;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.Group;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;
import quickfix.SocketInitiator;
import quickfix.StringField;
import quickfix.field.ApplVerID;

import com.sachin.fix.model.parser.FixDictionaryParser;
import com.sachin.fix.xml.BodyType;
import com.sachin.fix.xml.ComponentType;
import com.sachin.fix.xml.FieldType;
import com.sachin.fix.xml.GroupType;
import com.sachin.fix.xml.GroupsType;
import com.sachin.fix.xml.HeaderType;
import com.sachin.fix.xml.MessageType;
import com.sachin.fix.xml.TrailerType;
import com.sachin.qfixmessenger.config.FixMessengerConfig;
import com.sachin.qfixmessenger.quickfix.ComponentHelper;
import com.sachin.qfixmessenger.quickfix.FixMessengerApplication;
import com.sachin.qfixmessenger.quickfix.parser.QuickFixDictionaryParser;
import com.sachin.qfixmessenger.quickfix.util.FixUtil;
import com.sachin.qfixmessenger.ui.FixMessengerFrame;

/**
 * QuickFIX Messenger main application
 * 
 * 
 */
public class FixMessenger {
	private static final Logger logger = LoggerFactory
			.getLogger(FixMessenger.class);
	private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable ex) {
				logger.error("An unexpected exception occured!", ex);
			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setLookAndFeel();
			}
		});

		if (args.length != 2) {
			System.out.println("""
					Usage: FixMessenger <app cfg file> <quickfix cfg file>
					""");
			System.exit(0);
		}

		String configFileName = args[0];
		try (InputStream inputStream = new FileInputStream(args[1])) {
			SessionSettings settings = new SessionSettings(inputStream);

			FixMessenger messenger = new FixMessenger(configFileName, settings);
			messenger.logon();
			FixMessengerFrame.launch(messenger);

			shutdownLatch.await();
			logger.info("Shutting down at " + new Date() + "...");
			System.exit(0);
		} catch (FileNotFoundException ex) {
			logger.error("File not found: " + args[1]);
			logger.error("Quitting...");
			System.err.println("File not found: " + args[1]);
			System.err.println("Quitting...");
			System.exit(0);
		} catch (ConfigError ex) {
			logger.error("Unable to read config file!", ex);
			logger.error("Quitting...");
			System.err.println("Unable to read config file!");
			System.err.println("Quitting...");
			System.exit(1);
		}
	}

	private static void setLookAndFeel() {
		try {
			var useSystemLookAndFeelProperty = System
					.getProperty("useSystemLaF");
			if (Boolean.valueOf(useSystemLookAndFeelProperty)) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} else {
				UIManager
						.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel");
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
			}
		} catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
	}

	private final FixMessengerConfig config;

	private final FixDictionaryParser parser;

	private final FixMessengerApplication application;

	private final Connector connector;

	private final AtomicBoolean connectorStarted;

	private JAXBContext jaxbContext;

	private FixMessenger(String configFileName, SessionSettings settings)
			throws ConfigError, IOException {
		// Load application configuration
		config = new FixMessengerConfig(configFileName);

		// Create the dictionary parser
		parser = new QuickFixDictionaryParser(config.getNoOfParserThreads());

		// Create the QuickFIX application
		application = new FixMessengerApplication(settings);

		// Initialise the factories
		MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
		LogFactory logFactory = new FileLogFactory(settings);
		MessageFactory messageFactory = new DefaultMessageFactory();

		if (config.isInitiator()) {
			connector = new SocketInitiator(application, messageStoreFactory,
					settings, logFactory, messageFactory);
		} else {
			connector = new SocketAcceptor(application, messageStoreFactory,
					settings, logFactory, messageFactory);
		}

		connectorStarted = new AtomicBoolean(false);

		try {
			jaxbContext = JAXBContext.newInstance("com.sachin.fix.xml");
		} catch (JAXBException ex) {
			logger.error("Unable to create JAXB context for com.sachin.fix.xml");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Gracefully exits the application
	 */
	public void exit() {
		logout();
		connector.stop();
		shutdownLatch.countDown();
	}

	/**
	 * Returns the QuickFIX application
	 * 
	 * @return the QuickFIX application
	 */
	public FixMessengerApplication getApplication() {
		return application;
	}

	/**
	 * Returns the QuickFIX configuration
	 * 
	 * @return the QuickFIX configuration
	 */
	public FixMessengerConfig getConfig() {
		return config;
	}

	/**
	 * Returns the QuickFIX connector
	 * 
	 * @return the QuickFIX connector
	 */
	public Connector getConnector() {
		return connector;
	}

	/**
	 * Returns the JAXB context
	 * 
	 * @return the JAXB context
	 */
	public JAXBContext getJaxbContext() {
		return jaxbContext;
	}

	/**
	 * Returns the FixDictionaryParser
	 * 
	 * @return the FixDictionaryParser
	 */
	public FixDictionaryParser getParser() {
		return parser;
	}

	/**
	 * Logs the connector on
	 */
	public void logon() {
		if (!connectorStarted.get()) {
			try {
				connector.start();
				connectorStarted.getAndSet(true);
			} catch (Exception ex) {
				logger.error("Logon failed!", ex);
			}
		} else {
			Iterator<SessionID> sessionIds = connector.getSessions().iterator();
			while (sessionIds.hasNext()) {
				SessionID sessionId = sessionIds.next();
				Session.lookupSession(sessionId).logon();
			}
		}
	}

	/**
	 * Logs the connector out
	 */
	public void logout() {
		Iterator<SessionID> sessionIds = connector.getSessions().iterator();
		while (sessionIds.hasNext()) {
			SessionID sessionId = sessionIds.next();
			Session.lookupSession(sessionId).logout("user requested");
		}
	}

	/**
	 * Sends a QuickFIX Message across a given session
	 * 
	 * @param message
	 *                a QuickFIX Message
	 * @param session
	 *                a QuickFIX Session
	 * @return whether the message was sent or not
	 */
	public boolean sendQFixMessage(Message message, Session session) {
		logger.info("Sending message: " + message.toString());
		return session.send(message);
	}

	/**
	 * Converts an XML Message to a QuickFIX Message and sends it across the
	 * session
	 * 
	 * @param xmlMessageType
	 *                       an XML Message
	 * @return whether the message was sent or not
	 * @throws FixMessengerException
	 */
	public boolean sendXmlMessage(MessageType xmlMessageType)
			throws FixMessengerException {
		Session session = null;
		List<SessionID> sessionIds = connector.getSessions();
		for (SessionID sessionId : sessionIds) {
			if (FixUtil.getSessionName(sessionId).equals(
					xmlMessageType.getSession().getName())) {
				session = Session.lookupSession(sessionId);
			}
		}

		if (session != null) {
			if (session.isLoggedOn()) {
				quickfix.Message message = session.getMessageFactory().create(
						session.getSessionID().getBeginString(),
						xmlMessageType.getMsgType());

				if (xmlMessageType.getSession().getAppVersionId() != null) {
					ApplVerID applVerID = new ApplVerID(
							FixMessengerConstants.APPVER_ID_MAP
									.get(xmlMessageType.getSession()
											.getAppVersionId()));
					message.getHeader().setField(applVerID);
				}

				if (xmlMessageType.getHeader() != null) {
					HeaderType xmlHeaderType = xmlMessageType.getHeader();
					for (FieldType xmlFieldType : xmlHeaderType.getField()) {
						message.getHeader().setField(
								createStringField(xmlFieldType));
					}
				}

				BodyType xmlBodyType = xmlMessageType.getBody();
				for (Object xmlObject : xmlBodyType
						.getFieldOrGroupsOrComponent()) {
					switch (xmlObject) {
						case FieldType xmlFieldType -> message
								.setField(createStringField(xmlFieldType));
						case GroupsType xmlGroupsType -> {
							for (Group group : createGroups(xmlGroupsType)) {
								message.addGroup(group);
							}
						}
						case ComponentType xmlComponentType -> {
							ComponentHelper componentHelper = createComponent(xmlComponentType);
							for (StringField stringField : componentHelper
									.getFields()) {
								message.setField(stringField);
							}

							for (Group group : componentHelper.getGroups()) {
								message.addGroup(group);
							}
						}
						default -> {
						}
					}
				}

				if (xmlMessageType.getTrailer() != null) {
					TrailerType xmlTrailerType = xmlMessageType.getTrailer();
					for (FieldType xmlFieldType : xmlTrailerType.getField()) {
						message.getTrailer().setField(
								createStringField(xmlFieldType));
					}
				}

				return sendQFixMessage(message, session);
			} else {
				throw new FixMessengerException("Session not logged on: "
						+ xmlMessageType.getSession().getName());
			}
		} else {
			throw new FixMessengerException("Unrecognized session: "
					+ xmlMessageType.getSession().getName());
		}
	}

	private ComponentHelper createComponent(ComponentType xmlComponentType) {
		List<StringField> fields = new ArrayList<>();
		for (Object xmlObject : xmlComponentType.getFieldOrGroupsOrComponent()) {
			if (xmlObject instanceof FieldType xmlFieldType) {
				fields.add(createStringField(xmlFieldType));
			}
		}

		List<quickfix.Group> groups = new ArrayList<>();
		for (Object xmlObject : xmlComponentType.getFieldOrGroupsOrComponent()) {
			if (xmlObject instanceof GroupsType xmlGroupsType) {
				groups.addAll(createGroups(xmlGroupsType));
			}
		}

		return new ComponentHelper(fields, groups);
	}

	private List<Group> createGroups(GroupsType xmlGroupsType) {
		List<Group> groups = new ArrayList<>();

		for (GroupType xmlGroupType : xmlGroupsType.getGroup()) {
			Object firstMember = xmlGroupType.getFieldOrGroupsOrComponent()
					.get(0);
			FieldType firstFieldType = switch (firstMember) {
				case ComponentType componentType -> (FieldType) componentType
						.getFieldOrGroupsOrComponent().get(0);
				case FieldType fieldType -> fieldType;
				default -> (FieldType) firstMember;
			};

			int i = 0;
			Group group = new Group(xmlGroupsType.getId(),
					firstFieldType.getId());
			for (Object xmlObject : xmlGroupType.getFieldOrGroupsOrComponent()) {
				switch (xmlObject) {
					case FieldType xmlFieldType ->
						group.setField(++i, createStringField(xmlFieldType));
					case ComponentType xmlComponentType -> {
						ComponentHelper componentHelper = createComponent(xmlComponentType);
						for (StringField stringField : componentHelper
								.getFields()) {
							group.setField(++i, stringField);
						}

						for (Group memberGroup : componentHelper.getGroups()) {
							group.addGroup(memberGroup);
						}
					}
					case GroupsType memberXmlGroupsType -> {
						for (Group memberGroup : createGroups(
								memberXmlGroupsType)) {
							group.addGroup(memberGroup);
						}
					}
					default -> {
					}
				}
			}

			groups.add(group);
		}

		return groups;
	}

	private StringField createStringField(FieldType xmlFieldType) {
		return new StringField(xmlFieldType.getId(), xmlFieldType.getValue());
	}
}
