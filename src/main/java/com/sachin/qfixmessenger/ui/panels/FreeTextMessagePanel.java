package com.sachin.qfixmessenger.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.InvalidMessage;
import quickfix.Session;

import com.sachin.fix.model.Message;
import com.sachin.fix.xml.MessageType;
import com.sachin.qfixmessenger.FixMessenger;
import com.sachin.qfixmessenger.ui.util.TitledBorderUtil;

public class FreeTextMessagePanel extends JPanel implements
		MemberPanel<Message, quickfix.Message, MessageType>
{
	private static final long serialVersionUID = -7614167852761624847L;

	private final FixMessenger messenger;

	private final Session session;

	private final String appVersion;

	private final boolean isFixTSession;

	private JTextArea messageTextArea;

	public FreeTextMessagePanel(FixMessenger messenger, Session session,
			String appVersion, boolean isFixTSession)
	{
		this.messenger = messenger;
		this.session = session;
		this.appVersion = appVersion;
		this.isFixTSession = isFixTSession;

		initComponents();
	}

	@Override
	public String getFixString()
	{
		return messageTextArea.getText();
	}

	@Override
	public Message getMember()
	{
		return null;
	}

	@Override
	public quickfix.Message getQuickFixMember()
	{
		quickfix.Message message = null;
		try
		{
			message = new quickfix.Message();

			if (!isFixTSession)
			{
				message.fromString(getFixString(), session.getDataDictionary(),
						false);
			} else
			{
				/*
				 * FIXT sessions require the data dictionary of both the session
				 * and the application version
				 */
				DataDictionary appDictionary = null;
				DataDictionary sessionDictionary = null;
				try
				{
					String sessionDictionaryLocation = messenger.getConfig()
							.getFixT11DictionaryLocation();
					sessionDictionary = new DataDictionary(getClass()
							.getResourceAsStream(sessionDictionaryLocation));

					String appDictionaryLocation = messenger.getConfig()
							.getFixDictionaryLocation(appVersion);
					appDictionary = new DataDictionary(getClass()
							.getResourceAsStream(appDictionaryLocation));
					message.fromString(getFixString(), sessionDictionary,
							appDictionary, false);
				} catch (ConfigError ex)
				{
					message = null;
					JOptionPane.showMessageDialog(getParent(),
							"Unable to load Application "
									+ "version dictionary!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (InvalidMessage ex)
		{
			message = null;
			JOptionPane.showMessageDialog(getParent(), "Message is invalid!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return message;
	}

	@Override
	public MessageType getXmlMember()
	{
		throw new IllegalStateException(
				"FreeTextMessagePanel does not support XML Messages!");
	}

	@Override
	public boolean hasValidFormat()
	{
		return true;
	}

	public void populateXml(MessageType xmlMessageType)
	{
		throw new IllegalStateException(
				"FreeTextMessagePanel does not support XML Messages!");
	}

	private void initComponents()
	{
		setLayout(new BorderLayout());

		TitledBorder titledBorder = new TitledBorder(
				new LineBorder(Color.BLACK), "Free Text");
		setBorder(TitledBorderUtil.formatTitle(titledBorder));

		messageTextArea = new JTextArea(5, 20);
		messageTextArea.setLineWrap(true);

		JScrollPane messageTextScrollPane = new JScrollPane(messageTextArea);
		messageTextScrollPane.setBorder(new EtchedBorder());

		add(messageTextScrollPane, BorderLayout.NORTH);
	}
}
