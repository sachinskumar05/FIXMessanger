package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

public class CloseProjectActionListener implements ActionListener
{
	private QFixMessengerFrame frame;

	public CloseProjectActionListener(QFixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int choice = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to close \""
						+ frame.getActiveXmlProject().getName() + "\"?",
				"Close Project", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION)
		{
			if (frame.getActiveXmlProject() != null)
			{
				choice = JOptionPane.showConfirmDialog(frame,
						"Do you want to save \""
								+ frame.getActiveXmlProject().getName() + "\"?",
						"Save Current Project",
						JOptionPane.YES_NO_CANCEL_OPTION);
				switch (choice)
				{
				case JOptionPane.NO_OPTION:
					break;
				case JOptionPane.YES_OPTION:
					frame.marshallActiveXmlProject();
					break;
				case JOptionPane.CANCEL_OPTION:
					return;
				}
			}
			frame.setActiveXmlProject(null, null);
		}
	}
}
