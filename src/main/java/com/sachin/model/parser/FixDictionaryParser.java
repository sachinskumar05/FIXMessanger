package com.sachin.fix.model.parser;

import java.io.InputStream;

import com.sachin.fix.model.FixDictionary;

/**
 * Interface for FIX Dictionary parsers
 * 

 */
public interface FixDictionaryParser
{
	/**
	 * Parses a QuickFIX dictionary XML file
	 * 
	 * @param fileName
	 *            the name of the dictionary file
	 * @return a FixDictionary representation of the dictionary
	 * @throws FixParsingException
	 */
	@Deprecated
	FixDictionary parse(String fileName) throws FixParsingException;

	/**
	 * Parses a QuickFIX dictionary XML file
	 * 
	 * @param inputStream
	 *            the dictionary file as <code>InputStream</code>
	 * @return a FixDictionary representation of the dictionary
	 * @throws FixParsingException
	 */
	FixDictionary parse(InputStream inputStream) throws FixParsingException;
}
