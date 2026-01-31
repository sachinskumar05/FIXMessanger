package com.sachin.fix.model;

/**
 * Abstract class for all FIX member classes
 * 

 */
public abstract class AbstractMember implements Member
{
	/**
	 * Members are compared against Member.getName()
	 */
	@Override
	public int compareTo(Member o)
	{
		if (o != null)
		{
			return getName().compareTo(o.getName());
		} else
		{
			return Integer.MIN_VALUE;
		}
	}
}
