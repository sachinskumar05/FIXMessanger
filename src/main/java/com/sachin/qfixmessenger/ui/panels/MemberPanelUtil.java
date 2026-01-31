package com.sachin.qfixmessenger.ui.panels;

import java.util.List;

/**
 * 

 */
public class MemberPanelUtil
{
	public static MemberPanel<?, ?, ?> findMemberPanelByName(String name,
			List<MemberPanel<?, ?, ?>> memberPanels)
	{
		for (MemberPanel<?, ?, ?> memberPanel : memberPanels)
		{
			if (memberPanel.getMember().getName().equals(name))
			{
				return memberPanel;
			}
		}

		return null;
	}
}
