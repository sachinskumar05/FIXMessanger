package com.sachin.fix.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Represents a dictionary of a FIX version
 * 

 */
public final class FixDictionary
{
	private final String majorVersion;
	private final String minorVersion;
	private final String type;

	private final ConcurrentMap<String, Component> components;
	private final ConcurrentMap<String, Field> fields;
	private final ConcurrentMap<String, Message> messages;

	private Header header;
	private Trailer trailer;

	public FixDictionary(String majorVersion, String minorVersion)
	{
		this.type = null;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;

		this.fields = new ConcurrentHashMap<>();
		this.components = new ConcurrentHashMap<>();
		this.messages = new ConcurrentSkipListMap<>();
	}

	public FixDictionary(String type, String majorVersion, String minorVersion)
	{
		this.type = type;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;

		this.fields = new ConcurrentHashMap<>();
		this.components = new ConcurrentHashMap<>();
		this.messages = new ConcurrentHashMap<>();
	}

	public ConcurrentMap<String, Component> getComponents()
	{
		return components;
	}

	public ConcurrentMap<String, Field> getFields()
	{
		return fields;
	}

	public String getFullVersion()
	{
		return "%s.%s".formatted(majorVersion, minorVersion);
	}

	public String getMajorVersion()
	{
		return majorVersion;
	}

	public ConcurrentMap<String, Message> getMessages()
	{
		return messages;
	}

	public String getMinorVersion()
	{
		return minorVersion;
	}

	public String getType()
	{
		return type;
	}

	public Header getHeader()
	{
		return header;
	}

	public void setHeader(Header header)
	{
		this.header = header;
	}

	public Trailer getTrailer()
	{
		return trailer;
	}

	public void setTrailer(Trailer trailer)
	{
		this.trailer = trailer;
	}

	@Override
	public String toString()
	{
		return getFullVersion();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result
				+ ((majorVersion == null) ? 0 : majorVersion.hashCode());
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result
				+ ((minorVersion == null) ? 0 : minorVersion.hashCode());
		result = prime * result + ((trailer == null) ? 0 : trailer.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FixDictionary other = (FixDictionary) obj;
		if (components == null)
		{
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (fields == null)
		{
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (header == null)
		{
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (majorVersion == null)
		{
			if (other.majorVersion != null)
				return false;
		} else if (!majorVersion.equals(other.majorVersion))
			return false;
		if (messages == null)
		{
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (minorVersion == null)
		{
			if (other.minorVersion != null)
				return false;
		} else if (!minorVersion.equals(other.minorVersion))
			return false;
		if (trailer == null)
		{
			if (other.trailer != null)
				return false;
		} else if (!trailer.equals(other.trailer))
			return false;
		if (type == null)
		{
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
