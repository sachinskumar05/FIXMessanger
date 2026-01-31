package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class SaveProjectActionListener implements ActionListener
{
	private QFixMessengerFrame frame;

	public SaveProjectActionListener(QFixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		frame.marshallActiveXmlProject();
	}
}
