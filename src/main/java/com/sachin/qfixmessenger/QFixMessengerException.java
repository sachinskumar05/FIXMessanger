package com.sachin.qfixmessenger;

/**
 * QFixMessengerException
 * 

 */
public class QFixMessengerException extends Exception
{
	private static final long serialVersionUID = -3835891077672513984L;

	public QFixMessengerException(String message)
	{
		super(message);
	}

	public QFixMessengerException(String message, Throwable ex)
	{
		super(message, ex);
	}
}
