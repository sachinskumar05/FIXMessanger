package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.fix.xml.MessageType;
import com.sachin.qfixmessenger.ui.QFixMessengerFrame;
import com.sachin.qfixmessenger.ui.QFixMessengerFrame.XmlFileFilter;

public class ImportMessageActionListener implements ActionListener
{
	private static final Logger logger = LoggerFactory
			.getLogger(ImportMessageActionListener.class);

	private QFixMessengerFrame frame;

	public ImportMessageActionListener(QFixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(XmlFileFilter.INSTANCE);
		jFileChooser.setDialogTitle("Import Message");

		int choice = jFileChooser.showOpenDialog(frame);
		if (choice == JFileChooser.APPROVE_OPTION)
		{
			File file = jFileChooser.getSelectedFile();
			new UnmarshallWorker(frame, file).execute();
		}
	}

	private static class UnmarshallWorker extends
			SwingWorker<MessageType, Void>
	{
		private QFixMessengerFrame frame;
		private File file;

		public UnmarshallWorker(QFixMessengerFrame frame, File file)
		{
			this.frame = frame;
			this.file = file;
		}

		@Override
		protected MessageType doInBackground() throws Exception
		{
			Unmarshaller unmarshaller = frame.getMessenger().getJaxbContext()
					.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<MessageType> rootElement = (JAXBElement<MessageType>) unmarshaller
					.unmarshal(file);
			return rootElement.getValue();
		}

		@Override
		protected void done()
		{
			MessageType xmlMessageType;
			try
			{
				xmlMessageType = get();
				frame.loadXmlMessage(xmlMessageType);
			} catch (Exception ex)
			{
				logger.error("An Exception occurred while importing message.",
						ex);
				JOptionPane.showMessageDialog(frame, "Unable to open file!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
