package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Message
 * 

 */
public record Message(String name, String msgType, MessageCategory category,
		SortedMap<MemberOrder, Boolean> members) implements Member
{
	public Message
	{
		members = Collections.unmodifiableSortedMap(new TreeMap<MemberOrder, Boolean>(
				members));
	}

	public Message(Message message)
	{
		this(message.name, message.msgType, message.category, message.members);
	}

	@Override
	public String getName()
	{
		return name;
	}

	public String getMsgType()
	{
		return msgType;
	}

	public Map<MemberOrder, Boolean> getMembers()
	{
		return members;
	}

	public MessageCategory getCategory()
	{
		return category;
	}

	public boolean isAdminMessage()
	{
		return category.equals(MessageCategory.admin);
	}

	public boolean isApplicationMessage()
	{
		return category.equals(MessageCategory.app);
	}

	@Override
	public String toString()
	{
		return new StringBuilder(name).append(" (").append(msgType).append(")")
				.toString();
	}
}
