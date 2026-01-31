package com.sachin.qfixmessenger.ui.renderers;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.sachin.fix.xml.BodyType;
import com.sachin.fix.xml.ComponentType;
import com.sachin.fix.xml.FieldType;
import com.sachin.fix.xml.GroupType;
import com.sachin.fix.xml.GroupsType;
import com.sachin.fix.xml.HeaderType;
import com.sachin.fix.xml.MessageType;
import com.sachin.fix.xml.MessagesType;
import com.sachin.fix.xml.ProjectType;
import com.sachin.fix.xml.SessionType;
import com.sachin.fix.xml.TrailerType;
import com.sachin.qfixmessenger.ui.FixMessengerFrame;
import com.sachin.qfixmessenger.ui.util.IconBuilder;

public class ProjectTreeCellRenderer extends DefaultTreeCellRenderer
{
	
	 @java.io.Serial
	 private static final long serialVersionUID = -435212244413010769L;
	private final FixMessengerFrame frame;

	public ProjectTreeCellRenderer(FixMessengerFrame frame)
	{
		this.frame = frame;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus)
	{
		JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value,
				sel, expanded, leaf, row, hasFocus);

		if (value instanceof ProjectType)
		{
			ProjectType xmlProjectType = (ProjectType) value;
			label.setText(xmlProjectType.getName());
			if (expanded)
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.PROJECT_OPEN_ICON));
			} else
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.PROJECT_ICON));
			}
		}

		else if (value instanceof MessagesType)
		{
			label.setText("Messages");
			if (expanded)
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.MESSAGES_OPEN_ICON));
			} else
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.MESSAGES_ICON));
			}
		}

		else if (value instanceof MessageType)
		{
			MessageType xmlMessageType = (MessageType) value;
			label.setText(xmlMessageType.getName() + " ("
					+ xmlMessageType.getMsgType() + ")");
			if (expanded)
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.MESSAGE_OPEN_ICON));
			} else
			{
				label.setIcon(IconBuilder.build(frame.getMessenger()
						.getConfig(), IconBuilder.MESSAGE_ICON));
			}
		}

		else if (value instanceof SessionType)
		{
			label.setText("Session");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.SESSION_ICON));
		}

		else if (value instanceof HeaderType)
		{
			label.setText("Header");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.COMPONENT_ICON));
		}

		else if (value instanceof BodyType)
		{
			label.setText("Body");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.COMPONENT_ICON));
		}

		else if (value instanceof TrailerType)
		{
			label.setText("Trailer");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.COMPONENT_ICON));
		}

		else if (value instanceof GroupsType)
		{
			GroupsType xmlGroupsType = (GroupsType) value;
			label.setText(xmlGroupsType.getName() + " ("
					+ xmlGroupsType.getId() + ")");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.GROUPS_ICON));
		}

		else if (value instanceof GroupType)
		{
			label.setText("Group");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.GROUP_ICON));
		}

		else if (value instanceof ComponentType)
		{
			ComponentType xmlComponentType = (ComponentType) value;
			label.setText(xmlComponentType.getName());
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.COMPONENT_ICON));
		}

		else if (value instanceof FieldType)
		{
			FieldType xmlFieldType = (FieldType) value;
			label.setText(xmlFieldType.getName() + " (" + xmlFieldType.getId()
					+ ")");
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.FIELD_ICON));
		}

		else
		{
			label.setIcon(IconBuilder.build(frame.getMessenger().getConfig(),
					IconBuilder.TEXT_ICON));
		}

		return label;
	}
}



