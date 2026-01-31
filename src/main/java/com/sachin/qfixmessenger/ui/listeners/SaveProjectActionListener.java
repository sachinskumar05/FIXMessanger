package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public class SaveProjectActionListener implements ActionListener
{
	private FixMessengerFrame frame;

	public SaveProjectActionListener(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		frame.marshallActiveXmlProject();
	}
}
