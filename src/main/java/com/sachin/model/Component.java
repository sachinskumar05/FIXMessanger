package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Message Component
 * 

 */
public final class Component extends AbstractMember
{
	private final String name;
	private final SortedMap<MemberOrder, Boolean> members;
	private final Field firstTag;

	public Component(String name, Map<MemberOrder, Boolean> members,
			Field firstTag)
	{
		this.name = name;
		this.members = new TreeMap<MemberOrder, Boolean>(members);
		this.firstTag = firstTag;
	}

	public Component(Component component)
	{
		this.name = component.name;
		this.members = new TreeMap<MemberOrder, Boolean>(component.members);
		this.firstTag = component.firstTag;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return Collections.unmodifiableMap(members);
	}

	public Field getFirstField()
	{
		return firstTag;
	}

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstTag == null) ? 0 : firstTag.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Component other = (Component) obj;
		if (firstTag == null)
		{
			if (other.firstTag != null)
				return false;
		} else if (!firstTag.equals(other.firstTag))
			return false;
		if (members == null)
		{
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
