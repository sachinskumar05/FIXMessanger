package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import quickfix.Session;
import quickfix.SessionID;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class LogoffAllSessionsActionListener implements ActionListener
{
	private QFixMessengerFrame frame;

	public LogoffAllSessionsActionListener(QFixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int choice = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to logoff all sessions?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION)
		{
			for (SessionID sessionId : frame.getMessenger().getConnector()
					.getSessions())
			{
				Session session = Session.lookupSession(sessionId);
				if (session.isLoggedOn())
				{
					session.logout();
				}
			}
		}
	}
}
