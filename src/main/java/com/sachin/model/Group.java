package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Repeating Group
 * 

 */
public final class Group extends AbstractMember
{
	private final Field field;
	private final Member firstMember;
	private final SortedMap<MemberOrder, Boolean> members;

	public Group(Field field, Map<MemberOrder, Boolean> members,
			Member firstMember)
	{
		this.field = field;
		this.members = new TreeMap<MemberOrder, Boolean>(members);
		this.firstMember = firstMember;
	}

	public Group(Group group)
	{
		this.field = group.field;
		this.members = new TreeMap<MemberOrder, Boolean>(group.members);
		this.firstMember = group.firstMember;
	}

	public Member getFirstMember()
	{
		return firstMember;
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return Collections.unmodifiableMap(members);
	}

	@Override
	public String getName()
	{
		return field.getName();
	}

	public int getNumber()
	{
		return field.getNumber();
	}

	@Override
	public String toString()
	{
		return new StringBuilder(field.getName()).append("(")
				.append(field.getNumber()).append(")").append(" [GROUP]")
				.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result
				+ ((firstMember == null) ? 0 : firstMember.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		return result;
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
		Group other = (Group) obj;
		if (field == null)
		{
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (firstMember == null)
		{
			if (other.firstMember != null)
				return false;
		} else if (!firstMember.equals(other.firstMember))
			return false;
		if (members == null)
		{
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}
}
