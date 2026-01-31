package com.sachin.qfixmessenger.ui.listeners;

import javax.swing.JList;

import quickfix.Session;
import quickfix.SessionStateListener;

public class SessionsListSessionStateListener implements SessionStateListener
{
	private JList<Session> sessionsList;

	public SessionsListSessionStateListener(JList<Session> sessionsList)
	{
		this.sessionsList = sessionsList;
	}

	@Override
	public void onConnect()
	{
	}

	@Override
	public void onDisconnect()
	{
	}

	@Override
	public void onHeartBeatTimeout()
	{
	}

	@Override
	public void onLogon()
	{
		sessionsList.repaint();
	}

	@Override
	public void onLogout()
	{
		sessionsList.repaint();
	}

	@Override
	public void onMissedHeartBeat()
	{
	}

	@Override
	public void onRefresh()
	{
	}

	@Override
	public void onReset()
	{
	}
}
