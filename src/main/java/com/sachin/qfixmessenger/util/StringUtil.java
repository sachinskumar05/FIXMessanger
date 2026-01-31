package com.sachin.qfixmessenger.util;

/**
 * General utility for Strings
 * 

 */
public final class StringUtil
{
	private StringUtil()
	{
	}

	/**
	 * Indicates if the specified string is null or empty. An empty string is
	 * one that has a length of zero after being trimmed.
	 * 
	 * @param value the string value.
	 * @return true if the specified string is null or empty, false otherwise.
	 */
	public static boolean isNullOrEmpty(String value)
	{
		return value == null || value.trim().isEmpty();
	}
}
