package com.sachin.qfixmessenger.ui.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sachin.fix.model.Member;

/**
 * Cache for MemberPanels
 * 

 */
public class MemberPanelCache
{
	private final Map<Member, MemberPanel<?, ?, ?>> cache;

	private final Map<GroupMemberKey, MemberPanel<?, ?, ?>> groupCache;

	public MemberPanelCache()
	{
		cache = new HashMap<Member, MemberPanel<?, ?, ?>>();
		groupCache = new HashMap<GroupMemberKey, MemberPanel<?, ?, ?>>();
	}

	public void clear()
	{
		cache.clear();
		groupCache.clear();
	}

	public void encacheGroupMember(int index, MemberPanel<?, ?, ?> memberPanel)
	{
		groupCache.put(new GroupMemberKey(index, memberPanel.getMember()),
				memberPanel);
	}

	public void encacheGroupMembers(int index,
			List<MemberPanel<?, ?, ?>> memberPanels)
	{
		for (MemberPanel<?, ?, ?> memberPanel : memberPanels)
		{
			groupCache.put(new GroupMemberKey(index, memberPanel.getMember()),
					memberPanel);
		}
	}

	public void encacheMember(MemberPanel<?, ?, ?> memberPanel)
	{
		cache.put(memberPanel.getMember(), memberPanel);
	}

	public void encacheMembers(List<MemberPanel<?, ?, ?>> memberPanels)
	{
		for (MemberPanel<?, ?, ?> memberPanel : memberPanels)
		{
			cache.put(memberPanel.getMember(), memberPanel);
		}
	}

	public MemberPanel<?, ?, ?> getGroupMemberPanel(int index, Member member)
	{
		return groupCache.get(new GroupMemberKey(index, member));
	}

	public MemberPanel<?, ?, ?> getMemberPanel(Member member)
	{
		return cache.get(member);
	}

	private static class GroupMemberKey
	{
		private final int index;
		private final Member member;

		private GroupMemberKey(int index, Member member)
		{
			this.index = index;
			this.member = member;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GroupMemberKey other = (GroupMemberKey) obj;
			if (index != other.index)
				return false;
			if (member == null)
			{
				if (other.member != null)
					return false;
			} else if (!member.equals(other.member))
				return false;
			return true;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + index;
			result = prime * result
					+ ((member == null) ? 0 : member.hashCode());
			return result;
		}
	}
}
