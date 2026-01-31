package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class HelpActionListener implements ActionListener
{
	private static final Logger logger = LoggerFactory
			.getLogger(HelpActionListener.class);

	private QFixMessengerFrame frame;

	public HelpActionListener(QFixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			java.awt.Desktop.getDesktop().browse(
					java.net.URI.create(frame.getMessenger().getConfig()
							.getHelpUrl()));
		} catch (IOException ex)
		{
			logger.error("An exception occured!", ex);
			JOptionPane.showMessageDialog(frame, "An exception occured:\n"
					+ Arrays.toString(ex.getStackTrace()), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
