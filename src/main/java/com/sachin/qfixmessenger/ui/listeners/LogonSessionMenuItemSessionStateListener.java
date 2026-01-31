package com.sachin.qfixmessenger.ui.listeners;

import javax.swing.JCheckBoxMenuItem;

import quickfix.SessionStateListener;

public class LogonSessionMenuItemSessionStateListener implements
		SessionStateListener
{
	private JCheckBoxMenuItem menuItem;

	public LogonSessionMenuItemSessionStateListener(JCheckBoxMenuItem menuItem)
	{
		this.menuItem = menuItem;
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
		menuItem.setSelected(true);
	}

	@Override
	public void onLogout()
	{
		menuItem.setSelected(false);
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
