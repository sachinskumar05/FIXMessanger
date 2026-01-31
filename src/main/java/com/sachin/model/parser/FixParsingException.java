package com.sachin.fix.model.parser;

/**
 * Represents an exception encountered while parsing a FIX dictionary
 * 

 */
public class FixParsingException extends Exception
{
	private static final long serialVersionUID = -1530572421465962040L;

	public FixParsingException()
	{
		super();
	}

	public FixParsingException(String msg)
	{
		super(msg);
	}

	public FixParsingException(String msg, Throwable ex)
	{
		super(msg, ex);
	}
}
