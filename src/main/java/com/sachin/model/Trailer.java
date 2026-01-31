package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Standard Message Trailer
 * 

 */
public class Trailer
{
	private final SortedMap<MemberOrder, Boolean> members;

	public Trailer(Map<MemberOrder, Boolean> members)
	{
		this.members = new TreeMap<MemberOrder, Boolean>(members);
	}

	public Trailer(Trailer trailer)
	{
		this.members = new TreeMap<MemberOrder, Boolean>(trailer.members);
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
		Trailer other = (Trailer) obj;
		if (members == null)
		{
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}
}
