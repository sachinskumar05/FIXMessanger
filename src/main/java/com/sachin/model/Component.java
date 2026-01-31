package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Message Component
 * 

 */
public record Component(String name, SortedMap<MemberOrder, Boolean> members,
		Field firstField) implements Member
{
	public Component
	{
		members = Collections.unmodifiableSortedMap(new TreeMap<MemberOrder, Boolean>(
				members));
	}

	public Component(Component component)
	{
		this(component.name, component.members, component.firstField);
	}

	@Override
	public String getName()
	{
		return name;
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return members;
	}

	public Field getFirstField()
	{
		return firstField;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
