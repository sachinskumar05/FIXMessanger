package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Standard Message Header
 * 

 */
public record Header(SortedMap<MemberOrder, Boolean> members)
{
	public Header
	{
		members = Collections.unmodifiableSortedMap(new TreeMap<MemberOrder, Boolean>(
				members));
	}

	public Header(Header header)
	{
		this(header.members);
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return members;
	}
}
