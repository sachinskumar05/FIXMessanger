package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import quickfix.Session;

public class LogonSessionItemListener implements ItemListener
{
	private final Session session;

	public LogonSessionItemListener(Session session)
	{
		this.session = session;
	}

	@Override
	public void itemStateChanged(ItemEvent event)
	{
		if (event.getStateChange() == ItemEvent.SELECTED)
		{
			if (!session.isLoggedOn() && !session.isLogonAlreadySent())
			{
				session.logon();
			}
		}

		else if (event.getStateChange() == ItemEvent.DESELECTED)
		{
			if (session.isLoggedOn())
			{
				session.logout();
			}
		}
	}
}
