package com.sachin.fix.model;

/**
 * Represents a FIX field value (enumeration)
 * 

 */
public class FieldValue
{
	private final String enumValue;
	private final String description;

	public FieldValue(String enumValue, String description)
	{
		this.enumValue = enumValue;
		this.description = description;
	}

	public FieldValue(FieldValue fieldValue)
	{
		this.enumValue = fieldValue.enumValue;
		this.description = fieldValue.description;
	}

	public String getEnumValue()
	{
		return enumValue;
	}

	public String getDescription()
	{
		return description;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((enumValue == null) ? 0 : enumValue.hashCode());
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
		FieldValue other = (FieldValue) obj;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (enumValue == null)
		{
			if (other.enumValue != null)
				return false;
		} else if (!enumValue.equals(other.enumValue))
			return false;
		return true;
	}
}
