package com.sachin.fix.model;

import java.util.Date;

/**
 * Enumeration of known FIX field types
 * 

 */
public enum FieldType
{
	UNKNOWN,

	STRING,

	CHAR,

	PRICE(Double.class),

	INT(Integer.class),

	AMT(Double.class),

	QTY(Double.class),

	CURRENCY,

	MULTIPLEVALUESTRING,

	EXCHANGE,

	UTCTIMESTAMP(Date.class),

	BOOLEAN(Boolean.class),

	LOCALMKTDATE,

	DATA,

	FLOAT(Double.class),

	PRICEOFFSET(Double.class),

	MONTHYEAR,

	DAYOFMONTH(Integer.class),

	UTCDATEONLY(Date.class),

	UTCDATE(Date.class),

	UTCTIMEONLY(Date.class),

	TIME,

	NUMINGROUP(Integer.class),

	PERCENTAGE(Double.class),

	SEQNUM(Integer.class),

	LENGTH(Integer.class),

	COUNTRY;

	private final Class<?> javaClass;

	private FieldType()
	{
		this(String.class);
	}

	private FieldType(Class<?> javaClass)
	{
		this.javaClass = javaClass;
	}

	public Class<?> getJavaClass()
	{
		return javaClass;
	}
}
