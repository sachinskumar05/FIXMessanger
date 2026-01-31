package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Session;
import quickfix.SessionID;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;

/**
 * 

 */
public class ResetAllSessionsActionListener implements ActionListener
{
	private static final Logger logger = LoggerFactory
			.getLogger(ResetAllSessionsActionListener.class);

	private FixMessengerFrame frame;

	public ResetAllSessionsActionListener(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int choice = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to reset all sessions?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION)
		{
			for (SessionID sessionId : frame.getMessenger().getConnector()
					.getSessions())
			{
				Session session = Session.lookupSession(sessionId);
				session.reset();
			}
		}
	}
}
