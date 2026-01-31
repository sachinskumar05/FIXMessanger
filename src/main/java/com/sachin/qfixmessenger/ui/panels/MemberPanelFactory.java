package com.sachin.qfixmessenger.ui.panels;

import com.sachin.fix.model.Component;
import com.sachin.fix.model.Field;
import com.sachin.fix.model.Group;
import com.sachin.qfixmessenger.ui.QFixMessengerFrame;

/**
 * Static factory for creating MemberPanels
 * 

 */
public class MemberPanelFactory
{
	public static ComponentPanel createComponentPanel(QFixMessengerFrame frame,
			Component component, boolean isRequiredOnly, boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getMemberPanel(component);
		if (prevMemberPanel != null)
		{
			ComponentPanel prevComponentPanel = (ComponentPanel) prevMemberPanel;
			frame.getMemberPanelCache().encacheMembers(
					prevComponentPanel.getMembers());
		}

		ComponentPanel componentPanel = new ComponentPanel(frame, component,
				isRequiredOnly, isRequired);
		frame.getMemberPanelCache().encacheMember(componentPanel);

		return componentPanel;
	}

	public static FieldPanel createFieldPanel(QFixMessengerFrame frame,
			Field field, boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getMemberPanel(field);
		String value = null;
		if (prevMemberPanel != null)
		{
			FieldPanel prevFieldPanel = (FieldPanel) prevMemberPanel;
			value = prevFieldPanel.getValue();
		}

		FieldPanel fieldPanel = new FieldPanel(frame, field, isRequired, value);
		return fieldPanel;
	}

	public static GroupPanel createGroupPanel(QFixMessengerFrame frame,
			Group group, boolean isRequiredOnly, boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getMemberPanel(group);
		int noOfGroups = -1;
		if (prevMemberPanel != null)
		{
			GroupPanel prevGroupPanel = (GroupPanel) prevMemberPanel;
			noOfGroups = prevGroupPanel.getNoOfGroups();
			for (int i = 0; i < prevGroupPanel.getGroups().size(); i++)
			{
				// Encache as a group member
				frame.getMemberPanelCache().encacheGroupMembers(i,
						prevGroupPanel.getGroups().get(i));
			}
		}

		GroupPanel groupPanel = new GroupPanel(frame, group, isRequiredOnly,
				isRequired, noOfGroups);
		frame.getMemberPanelCache().encacheMember(groupPanel);

		return groupPanel;
	}
}
