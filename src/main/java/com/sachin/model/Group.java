package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Repeating Group
 * 

 */
public record Group(Field field, SortedMap<MemberOrder, Boolean> members,
		Member firstMember) implements Member
{
	public Group
	{
		members = Collections.unmodifiableSortedMap(new TreeMap<MemberOrder, Boolean>(
				members));
	}

	public Group(Group group)
	{
		this(group.field, group.members, group.firstMember);
	}

	public Member getFirstMember()
	{
		return firstMember;
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return members;
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
}
