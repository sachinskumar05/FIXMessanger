package com.sachin.fix.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a FIX field (tag)
 * 

 */
public record Field(int number, String name, FieldType type,
		List<FieldValue> values) implements Member
{
	public Field
	{
		if (values != null)
		{
			values = Collections.unmodifiableList(new ArrayList<>(
					values));
		}
	}

	public Field(Field field)
	{
		this(field.number, field.name, field.type, field.values);
	}

	public int getNumber()
	{
		return number;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public FieldType getType()
	{
		return type;
	}

	public List<FieldValue> getValues()
	{
		return values;
	}

	@Override
	public String toString()
	{
		return new StringBuilder(name).append(" (").append(number).append(")")
				.append(" [").append(type.name()).append("]").toString();
	}
}

