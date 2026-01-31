package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quickfix.Session;
import quickfix.SessionID;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public class LogonAllSessionsActionListener implements ActionListener
{
	private FixMessengerFrame frame;

	public LogonAllSessionsActionListener(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (SessionID sessionId : frame.getMessenger().getConnector()
				.getSessions())
		{
			Session session = Session.lookupSession(sessionId);
			if (!session.isLoggedOn() && !session.isLogonAlreadySent())
			{
				session.logon();
			}
		}
	}
}
