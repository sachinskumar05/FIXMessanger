package com.sachin.fix.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents a FIX Message
 * 

 */
public final class Message extends AbstractMember
{
	private final String name;
	private final String msgType;
	private final MessageCategory category;
	private final SortedMap<MemberOrder, Boolean> members;

	public Message(String name, String msgType, MessageCategory category,
			Map<MemberOrder, Boolean> members)
	{
		this.name = name;
		this.msgType = msgType;
		this.category = category;
		this.members = new TreeMap<MemberOrder, Boolean>(members);
	}

	public Message(Message message)
	{
		this.name = message.name;
		this.msgType = message.msgType;
		this.category = message.category;
		this.members = new TreeMap<MemberOrder, Boolean>(message.members);
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
		return Collections.unmodifiableMap(members);
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((msgType == null) ? 0 : msgType.hashCode());
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
		Message other = (Message) obj;
		if (category == null)
		{
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (members == null)
		{
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (msgType == null)
		{
			if (other.msgType != null)
				return false;
		} else if (!msgType.equals(other.msgType))
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
