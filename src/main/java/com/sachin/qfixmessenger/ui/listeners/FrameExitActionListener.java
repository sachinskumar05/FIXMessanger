package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public class FrameExitActionListener implements ActionListener
{
	private FixMessengerFrame frame;

	public FrameExitActionListener(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		frame.close();
	}
}
