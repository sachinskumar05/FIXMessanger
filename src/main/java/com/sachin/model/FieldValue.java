package com.sachin.fix.model;

/**
 * Represents a FIX field value (enumeration)
 * 

 */
public record FieldValue(String enumValue, String description)
{
	public FieldValue(FieldValue fieldValue)
	{
		this(fieldValue.enumValue, fieldValue.description);
	}

	public String getEnumValue()
	{
		return enumValue;
	}

	public String getDescription()
	{
		return description;
	}
}
