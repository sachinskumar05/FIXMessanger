package com.sachin.qfixmessenger;

/**
 * FixMessengerException
 * 

 */
public class FixMessengerException extends Exception
{
	@java.io.Serial
	private static final long serialVersionUID = -3835891077672513984L;

	public FixMessengerException(String message)
	{
		super(message);
	}

	public FixMessengerException(String message, Throwable ex)
	{
		super(message, ex);
	}
}
