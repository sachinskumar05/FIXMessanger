package com.sachin.qfixmessenger.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.sachin.qfixmessenger.FixMessengerConstants;

/**
 * Represents a QuickFIX Messenger configuration
 * 


 */
public class FixMessengerConfig
{
	private static final String IS_INITIATOR_PROP = "messenger.isInitiator";
	private static final String PARSER_THREADS_PROP = "messenger.parser.threads";
	private static final String LICENSE_PROP = "messenger.license";
	private static final String ICONS_PROP = "messenger.icons";
	private static final String HOME_URL_PROP = "messenger.home.url";
	private static final String HELP_URL_PROP = "messenger.help.url";
	private static final String FIXWIKI_URL_PROP = "messenger.fixwiki.url";

	private static final String DICT_FIX40_PROP = "messenger.dict.fix40";
	private static final String DICT_FIX41_PROP = "messenger.dict.fix41";
	private static final String DICT_FIX42_PROP = "messenger.dict.fix42";
	private static final String DICT_FIX43_PROP = "messenger.dict.fix43";
	private static final String DICT_FIX44_PROP = "messenger.dict.fix44";
	private static final String DICT_FIX50_PROP = "messenger.dict.fix50";
	private static final String DICT_FIX50SP1_PROP = "messenger.dict.fix50SP1";
	private static final String DICT_FIX50SP2_PROP = "messenger.dict.fix50SP2";
	private static final String DICT_FIXT11_PROP = "messenger.dict.fixt11";

	private static final String LOG_PATH = "logFilePath";

	private final Properties properties;

	public FixMessengerConfig(String configFileName) throws IOException
	{
		properties = new Properties();
		try (FileInputStream inputStream = new FileInputStream(configFileName))
		{
			properties.load(inputStream);
		}
	}

	public String getFix40DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX40_PROP, "/FIX40.xml");
	}

	public String getFix41DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX41_PROP, "/FIX41.xml");
	}

	public String getFix42DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX42_PROP, "/FIX42.xml");
	}

	public String getFix43DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX43_PROP, "/FIX43.xml");
	}

	public String getFix44DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX44_PROP, "/FIX44.xml");
	}

	public String getFix50DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX50_PROP, "/FIX50.xml");
	}

	public String getFix50SP1DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX50SP1_PROP, "/FIX50SP1.xml");
	}
	public String getFix50SP2DictionaryLocation()
	{
		return properties.getProperty(DICT_FIX50SP2_PROP, "/FIX50SP2.xml");
	}

	public String getFixDictionaryLocation(String beginString)
	{
		return switch (beginString)
		{
			case FixMessengerConstants.BEGIN_STRING_FIX40 ->
				getFix40DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX41 ->
				getFix41DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX42 ->
				getFix42DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX43 ->
				getFix43DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX44 ->
				getFix44DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX50 ->
				getFix50DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX50SP1 ->
				getFix50SP1DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIX50SP2 ->
				getFix50SP2DictionaryLocation();
			case FixMessengerConstants.BEGIN_STRING_FIXT11 ->
				getFixT11DictionaryLocation();
			default -> "";
		};
	}

	public String getFixT11DictionaryLocation()
	{
		return properties.getProperty(DICT_FIXT11_PROP, "/FIXT11.xml");
	}

	public boolean isInitiator()
	{
		return Boolean.parseBoolean(properties.getProperty(IS_INITIATOR_PROP,
				"true"));
	}

	public String getIconsLocation()
	{
		return properties.getProperty(ICONS_PROP, "/icons/");
	}

	public String getLicenseLocation()
	{
		return properties.getProperty(LICENSE_PROP, "/license.txt");
	}

	public String getHomeUrl()
	{
		return properties.getProperty(HOME_URL_PROP,
				"https://github.com.sachin/quickfix-messenger");
	}

	public String getHelpUrl()
	{
		return properties
				.getProperty(HELP_URL_PROP,
						"https://github.com.sachin/quickfix-messenger/wiki/User's-Guide");
	}

	public String getFixWikiUrl()
	{
		return properties.getProperty(FIXWIKI_URL_PROP,
				"http://fixwiki.org/fixwiki/");
	}

	public int getNoOfParserThreads()
	{
		return Integer.parseInt(properties.getProperty(PARSER_THREADS_PROP,
				"20"));
	}

	public String getLogFilePath()
	{
		return properties.getProperty(LOG_PATH);
	}

}
