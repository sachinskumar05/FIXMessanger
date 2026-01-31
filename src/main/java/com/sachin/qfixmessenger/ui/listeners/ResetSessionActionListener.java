package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Session;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class ResetSessionActionListener implements ActionListener
{
	private static final Logger logger = LoggerFactory
			.getLogger(ResetSessionActionListener.class);

	private QFixMessengerFrame frame;

	private Session session;

	public ResetSessionActionListener(QFixMessengerFrame frame, Session session)
	{
		this.frame = frame;
		this.session = session;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int choice = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to reset the sequence numbers?",
				"Confirm", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION)
		{
			session.reset();
		}
	}
}
