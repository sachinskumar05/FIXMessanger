package com.sachin.fix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a FIX field (tag)
 * 

 */
public final class Field extends AbstractMember
{
	private final int number;
	private final String name;
	private final FieldType type;
	private final List<FieldValue> values;

	public Field(int number, String name, FieldType type,
			List<FieldValue> values)
	{
		this.number = number;
		this.name = name;
		this.type = type;
		if (values != null)
		{
			this.values = new ArrayList<FieldValue>(values);
		} else
		{
			this.values = null;
		}
	}

	public Field(Field field)
	{
		this.number = field.number;
		this.name = field.name;
		this.type = field.type;
		if (field.values != null)
		{
			this.values = new ArrayList<FieldValue>(field.values);
		} else
		{
			this.values = null;
		}
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
		if (values != null)
		{
			return Collections.unmodifiableList(values);
		} else
		{
			return null;
		}
	}

	@Override
	public String toString()
	{
		return new StringBuilder(name).append(" (").append(number).append(")")
				.append(" [").append(type.name()).append("]").toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		Field other = (Field) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (type == null)
		{
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (values == null)
		{
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
