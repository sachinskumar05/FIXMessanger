package com.sachin.qfixmessenger;

import java.util.Collections;
import java.util.Map;
import org.agrona.collections.Object2ObjectHashMap;

/**
 * Constants used by QuickFIX Messenger
 * <p>
 * TODO These values need to be configurable
 * </p>
 * 

 */
public final class FixMessengerConstants
{
	private FixMessengerConstants()
	{
	}

	public static final String UTC_DATE_FORMAT = "yyyyMMdd-HH:mm:ss.SSS";

	public static final char SOH = '\001';

	public static final String BEGIN_STRING_FIX40 = "FIX.4.0";
	public static final String BEGIN_STRING_FIX41 = "FIX.4.1";
	public static final String BEGIN_STRING_FIX42 = "FIX.4.2";
	public static final String BEGIN_STRING_FIX43 = "FIX.4.3";
	public static final String BEGIN_STRING_FIX44 = "FIX.4.4";
	public static final String BEGIN_STRING_FIX50 = "FIX.5.0";
	public static final String BEGIN_STRING_FIX50SP1 = "FIX.5.0SP1";
	public static final String BEGIN_STRING_FIX50SP2 = "FIX.5.0SP2";
	public static final String BEGIN_STRING_FIXT11 = "FIXT.1.1";

	public static final Map<String, String> APPVER_ID_MAP;
	static
	{
		Object2ObjectHashMap<String, String> appVerIdMap = new Object2ObjectHashMap<>();
		appVerIdMap.put(BEGIN_STRING_FIX40, "2");
		appVerIdMap.put(BEGIN_STRING_FIX41, "3");
		appVerIdMap.put(BEGIN_STRING_FIX42, "4");
		appVerIdMap.put(BEGIN_STRING_FIX43, "5");
		appVerIdMap.put(BEGIN_STRING_FIX44, "6");
		appVerIdMap.put(BEGIN_STRING_FIX50, "7");
		appVerIdMap.put(BEGIN_STRING_FIX50SP1, "8");
		appVerIdMap.put(BEGIN_STRING_FIX50SP2, "9");
		APPVER_ID_MAP = Collections.unmodifiableMap(appVerIdMap);
	}

	public static final String[] FIXT_APP_VERSIONS = { BEGIN_STRING_FIX40,
			BEGIN_STRING_FIX41, BEGIN_STRING_FIX42, BEGIN_STRING_FIX43,
			BEGIN_STRING_FIX44, BEGIN_STRING_FIX50, BEGIN_STRING_FIX50SP1, BEGIN_STRING_FIX50SP2 };
}
