package com.sachin.qfixmessenger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

/**
 * LogFileDialog
 * 

 */
public class LogfileDialog extends JDialog
{

\t@java.io.Serial
\tprivate static final long serialVersionUID = -1373316797900491533L;

	private static final Logger logger = Logger.getLogger(LogfileDialog.class);

	private final FixMessengerFrame frame;

	private JTabbedPane logTabPane;

	private JTextArea eventTextArea;

	private JTextArea messageTextArea;

	public LogfileDialog(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	public void launch(String beginStr)
	{
		initDialog();
		initComponents();
		setVisible(true);
		loadLogfile(beginStr);
	}

	public void loadLogfile(String beginStr)
	{
		if (beginStr == null || beginStr.isEmpty())
		{
			return;
		}

		String path = frame.getMessenger().getConfig().getLogFilePath();
		String eventFileName = path + beginStr;
		String messengeFileName = path + beginStr;

		if (frame.getMessenger().getConfig().isInitiator())
		{
			eventFileName += "-INIT-ACCEPT.event.log";
			messengeFileName += "-INIT-ACCEPT.messages.log";
		} else
		{
			eventFileName += "-ACCEPT-INIT.event.log";
			messengeFileName += "-ACCEPT-INIT.messages.log";
		}

		Path eventPath = Paths.get(eventFileName);
		Path messagePath = Paths.get(messengeFileName);

		try
		{
			List<String> eventLines = Files.readAllLines(eventPath,
					Charset.defaultCharset());
			List<String> messageLines = Files.readAllLines(messagePath,
					Charset.defaultCharset());

			for (String l : eventLines)
			{
				eventTextArea.append(l);
				eventTextArea.append("\n");
			}

			for (String l : messageLines)
			{
				messageTextArea.append(l);
				messageTextArea.append("\n");
			}
		} catch (IOException e)
		{
			logger.error("Exception in LogfileDialog: " + e.getMessage());
		}

	}

	private void initDialog()
	{

		String title = "Logfile";
		if (frame.getMessenger().getConfig().isInitiator())
		{
			title = title + " (Initiator)";
		} else
		{
			title = title + " (Acceptor)";
		}
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initComponents()
	{

		setLayout(new BorderLayout());

		eventTextArea = new JTextArea();
		messageTextArea = new JTextArea();

		JScrollPane eventPane = new JScrollPane(eventTextArea);
		JScrollPane messagePane = new JScrollPane(messageTextArea);

		logTabPane = new JTabbedPane();
		logTabPane.setPreferredSize(new Dimension(500, 500));
		logTabPane.setTabPlacement(JTabbedPane.BOTTOM);
		logTabPane.addTab("Event", eventPane);
		logTabPane.addTab("Message", messagePane);

		add(logTabPane);
		pack();
	}

}

