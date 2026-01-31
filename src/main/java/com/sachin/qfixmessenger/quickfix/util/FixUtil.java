package com.sachin.qfixmessenger.quickfix.util;

import quickfix.SessionID;

/**
 * General utility for QuickFIX
 * 

 */
public final class FixUtil
{
	private FixUtil()
	{
	}

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
		return "%s:%s>%s".formatted(sessionId.getBeginString(),
				sessionId.getSenderCompID(), sessionId.getTargetCompID());
	}
}
