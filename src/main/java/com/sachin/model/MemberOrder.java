package com.sachin.fix.model;

/**
 * Encapsulates the order in which a member is defined.
 * 

 */
public final class MemberOrder implements Comparable<MemberOrder>
{
	private final Integer index;
	private final Member member;

	public MemberOrder(Integer index, Member member)
	{
		this.index = index;
		this.member = member;
	}

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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
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
		MemberOrder other = (MemberOrder) obj;
		if (index == null)
		{
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (member == null)
		{
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}
}
