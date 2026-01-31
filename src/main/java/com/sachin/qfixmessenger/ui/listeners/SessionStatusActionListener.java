package com.sachin.qfixmessenger.ui.listeners;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import quickfix.Session;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class SessionStatusActionListener implements ActionListener
{
	private QFixMessengerFrame frame;
	private Session session;

	public SessionStatusActionListener(QFixMessengerFrame frame, Session session)
	{
		this.frame = frame;
		this.session = session;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1));

		JLabel versionLabel = new JLabel(
				"<html><b>FIX Version:</b> <i><font color='blue'>"
						+ session.getSessionID().getBeginString()
						+ "</font></i></html>");

		JLabel senderCompIdLabel = new JLabel(
				"<html><b>SenderCompID:</b> <i><font color='blue'>"
						+ session.getSessionID().getSenderCompID()
						+ "</font></i></html>");

		JLabel targetCompIdLabel = new JLabel(
				"<html><b>TargetCompID:</b> <i><font color='blue'>"
						+ session.getSessionID().getTargetCompID()
						+ "</font></i></html>");

		JLabel isLoggedOnLabel;
		if (session.isLoggedOn())
		{
			isLoggedOnLabel = new JLabel(
					"<html><b>Logged On:</b> <i><font color='green'>Yes</font></i></html>");
		} else
		{
			isLoggedOnLabel = new JLabel(
					"<html><b>Logged On:</b> <i><font color='red'>NO</font></i></html>");
		}

		JLabel expectedInSeqLabel = new JLabel(
				"<html><b>Expected IN Seq:</b> <i><font color='blue'>"
						+ session.getExpectedTargetNum() + "</font></i></html>");

		JLabel expectedOutSeqLabel = new JLabel(
				"<html><b>Expected OUT Seq:</b> <i><font color='blue'>"
						+ session.getExpectedSenderNum() + "</font></i></html>");

		panel.add(versionLabel);
		panel.add(senderCompIdLabel);
		panel.add(targetCompIdLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 10)));
		panel.add(isLoggedOnLabel);
		panel.add(expectedInSeqLabel);
		panel.add(expectedOutSeqLabel);

		JOptionPane.showMessageDialog(frame, panel, "Session Details",
				JOptionPane.PLAIN_MESSAGE);
	}

}
