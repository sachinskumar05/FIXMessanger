package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Standard Message Trailer
 * 

 */
public record Trailer(SortedMap<MemberOrder, Boolean> members)
{
	public Trailer
	{
		members = Collections.unmodifiableSortedMap(new TreeMap<MemberOrder, Boolean>(
				members));
	}

	public Trailer(Trailer trailer)
	{
		this(trailer.members);
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return members;
	}
}
