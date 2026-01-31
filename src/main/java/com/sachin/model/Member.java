package com.sachin.fix.model;

/**
 * Interface for FIX members
 * 

 */
public sealed interface Member extends Comparable<Member>
		permits Field, Component, Group, Message
{
	String getName();

	/**
	 * Members are compared against Member.getName()
	 */
	@Override
	default int compareTo(Member other)
	{
		if (other != null)
		{
			return getName().compareTo(other.getName());
		}
		return Integer.MIN_VALUE;
	}
}
