package com.sachin.qfixmessenger.quickfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import quickfix.StringField;

/**
 * ComponentHelper
 * <p>
 * A workaround to the lack of non-type-safe Components in QuickFIX/J
 * </p>
 * 

 */
public record ComponentHelper(List<StringField> fields,
		List<quickfix.Group> groups)
{
	public ComponentHelper
	{
		fields = Collections.unmodifiableList(new ArrayList<>(fields));
		groups = Collections.unmodifiableList(new ArrayList<>(groups));
	}

	public List<StringField> getFields()
	{
		return fields;
	}

	public List<quickfix.Group> getGroups()
	{
		return groups;
	}
}
