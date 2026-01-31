package com.sachin.qfixmessenger.quickfix;

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
public class ComponentHelper
{
	private final List<StringField> fields;
	private final List<quickfix.Group> groups;

	public ComponentHelper(List<StringField> fields, List<quickfix.Group> groups)
	{
		this.fields = fields;
		this.groups = groups;
	}

	public List<StringField> getFields()
	{
		return Collections.unmodifiableList(fields);
	}

	public List<quickfix.Group> getGroups()
	{
		return Collections.unmodifiableList(groups);
	}
}
