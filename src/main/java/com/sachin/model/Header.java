package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Standard Message Header
 * 

 */
public class Header
{
	private final SortedMap<MemberOrder, Boolean> members;

	public Header(Map<MemberOrder, Boolean> members)
	{
		this.members = new TreeMap<MemberOrder, Boolean>(members);
	}

	public Header(Header header)
	{
		this.members = new TreeMap<MemberOrder, Boolean>(header.members);
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return Collections.unmodifiableMap(members);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
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
		Header other = (Header) obj;
		if (members == null)
		{
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}
}
