package com.sachin.fix.model;

/**
 * Encapsulates the order in which a member is defined.
 * 

 */
public record MemberOrder(Integer index, Member member)
		implements Comparable<MemberOrder>
{
	/**
	 * MemberOrders are compared against the index
	 */
	@Override
	public int compareTo(MemberOrder memberOrder)
	{
		return index.compareTo(memberOrder.index);
	}

	public int getIndex()
	{
		return index;
	}

	public Member getMember()
	{
		return member;
	}
}
