package com.sachin.qfixmessenger.ui.panels;

import com.sachin.fix.model.Component;
import com.sachin.fix.model.Field;
import com.sachin.fix.model.Group;
import com.sachin.qfixmessenger.ui.FixMessengerFrame;

/**
 * Static factory for creating MemberPanels belonging to a group
 * 

 */
public class GroupMemberPanelFactory
{
	public static ComponentPanel createComponentPanel(FixMessengerFrame frame,
			Component component, int index, boolean isRequiredOnly,
			boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getGroupMemberPanel(index, component);
		if (prevMemberPanel != null)
		{
			ComponentPanel prevComponentPanel = (ComponentPanel) prevMemberPanel;
			frame.getMemberPanelCache().encacheMembers(
					prevComponentPanel.getMembers());
		}

		ComponentPanel componentPanel = new ComponentPanel(frame, component,
				isRequiredOnly, isRequired);
		frame.getMemberPanelCache().encacheGroupMember(index, componentPanel);

		return componentPanel;
	}

	public static FieldPanel createFieldPanel(FixMessengerFrame frame,
			Field field, int index, boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getGroupMemberPanel(index, field);
		String value = null;
		if (prevMemberPanel != null)
		{
			FieldPanel prevFieldPanel = (FieldPanel) prevMemberPanel;
			value = prevFieldPanel.getValue();
		}

		FieldPanel fieldPanel = new FieldPanel(frame, field, isRequired, value);
		return fieldPanel;
	}

	public static GroupPanel createGroupPanel(FixMessengerFrame frame,
			Group group, int index, boolean isRequiredOnly, boolean isRequired)
	{
		MemberPanel<?, ?, ?> prevMemberPanel = frame.getMemberPanelCache()
				.getGroupMemberPanel(index, group);
		int noOfGroups = -1;
		if (prevMemberPanel != null)
		{
			GroupPanel prevGroupPanel = (GroupPanel) prevMemberPanel;
			noOfGroups = prevGroupPanel.getNoOfGroups();
			for (int i = 0; i < prevGroupPanel.getGroups().size(); i++)
			{
				frame.getMemberPanelCache().encacheGroupMembers(i,
						prevGroupPanel.getGroups().get(i));
			}
		}

		GroupPanel groupPanel = new GroupPanel(frame, group, isRequiredOnly,
				isRequired, noOfGroups);
		frame.getMemberPanelCache().encacheGroupMember(index, groupPanel);

		return groupPanel;
	}
}
