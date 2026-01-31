package com.sachin.qfixmessenger.quickfix.util;

import quickfix.SessionID;

/**
 * General utility for QuickFIX
 * 

 */
public class FixUtil
{
	/**
	 * Returns the preferred session name format from a QuickFIX SessionID
	 * instance
	 * 
	 * @param sessionId the QuickFIX SessionID instance
	 * @return the preferred session name format from a QuickFIX SessionID
	 *         instance
	 */
	public static String getSessionName(SessionID sessionId)
	{
		return new StringBuilder(sessionId.getBeginString()).append(':')
				.append(sessionId.getSenderCompID()).append(">").append(
						sessionId.getTargetCompID()).toString();
	}
}
