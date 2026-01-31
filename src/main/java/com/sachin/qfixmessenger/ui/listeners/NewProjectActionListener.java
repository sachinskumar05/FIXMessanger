package com.sachin.qfixmessenger.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.sachin.fix.xml.ObjectFactory;
import com.sachin.fix.xml.ProjectType;
import com.sachin.qfixmessenger.ui.FixMessengerFrame;

public class NewProjectActionListener implements ActionListener
{
	private FixMessengerFrame frame;

	public NewProjectActionListener(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (frame.getActiveXmlProject() != null)
		{
			int choice = JOptionPane.showConfirmDialog(frame,
					"Do you want to save \""
							+ frame.getActiveXmlProject().getName() + "\"?",
					"Save Current Project", JOptionPane.YES_NO_CANCEL_OPTION);
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

		String projectName = (String) JOptionPane.showInputDialog(frame,
				"Project Name:", "New Project", JOptionPane.QUESTION_MESSAGE,
				null, null, "Project");
		if (projectName != null)
		{
			ObjectFactory xmlObjectFactory = new ObjectFactory();
			ProjectType xmlProjectType = xmlObjectFactory.createProjectType();
			xmlProjectType.setName(projectName);
			xmlProjectType.setMessages(xmlObjectFactory.createMessagesType());
			frame.setActiveXmlProject(xmlProjectType, null);
		}
	}
}
