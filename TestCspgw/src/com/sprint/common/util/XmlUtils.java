/*
 *
 * Copyright (C)2004 Sprint Canada Inc.
 *
 * All rights reserved.
 *
 * The source code in this file is confidential and is the sole property of
 * Sprint Canada Inc. Its use is restricted to those readers who are authorized
 * by the corporation. Any reverse engineering or reproduction or divulgence of
 * the content of this report without the written consent from Sprint Canada
 * Inc. is strictly prohibited.
 *
 * Created on Feb 22, 2004
 */
package com.sprint.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.UnmarshalListener;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

/**
 * This class provides convenience methods for working with XML strings, and for
 * marshalling Java objects to and from XML. The underlying marshalling
 * implementation is provided by Castor.
 *
 * @link www.castor.org
 *
 * @author Eugene Abramov
 */
public class XmlUtils {

	static public final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	/**
	 * All methods are static
	 */
	protected XmlUtils() {
	}

	/**
	 * @return Returns a standard XML header.
	 */
	static public String getXmlHeader() {
		return XML_HEADER;
	}

	/**
	 * Creates and XML block of the form
	 * <code>&lt;fieldName&gt;fieldValue&lt;/fieldName&gt;</code> for a long
	 * value
	 *
	 * @param fieldName
	 * @param fieldValue
	 * @param pw
	 */
	static public void writeXMLBlock(String fieldName, long fieldValue, PrintWriter pw) {
		writeXMLStart(fieldName, false, pw);
		pw.print(fieldValue);
		writeXMLEnd(fieldName, pw);
	}

	/**
	 * Creates and XML block of the form
	 * <code>&lt;fieldName&gt;fieldValue&lt;/fieldName&gt;</code> for an int
	 * value
	 *
	 * @param fieldName
	 * @param fieldValue
	 * @param pw
	 */
	static public void writeXMLBlock(String fieldName, int fieldValue, PrintWriter pw) {
		writeXMLStart(fieldName, false, pw);
		pw.print(fieldValue);
		writeXMLEnd(fieldName, pw);
	}

	/**
	 * Creates and XML block of the form
	 * <code>&lt;fieldName&gt;fieldValue&lt;/fieldName&gt;</code> for a String
	 * value
	 *
	 * @param fieldName
	 * @param fieldValue
	 * @param pw
	 */
	static public void writeXMLBlock(String fieldName, String fieldValue, PrintWriter pw) {
		boolean isNull = StringUtils.isEmpty(fieldValue);
		writeXMLStart(fieldName, isNull, pw);
		if (!isNull) {
			fieldValue = toXMLString(fieldValue);
			fieldValue = StringUtilsExt.trim(fieldValue);
			pw.print(fieldValue);
			writeXMLEnd(fieldName, pw);
		}

	}


	static public void writeXMLBlockNoEscape(String fieldName, String fieldValue, PrintWriter pw) {
		boolean isNull = StringUtils.isEmpty(fieldValue);
		writeXMLStart(fieldName, isNull, pw);
		if (!isNull) {
			fieldValue = StringUtilsExt.trim(fieldValue);
			pw.print(fieldValue);
			writeXMLEnd(fieldName, pw);
		}
	}

	/**
	 * Generates the end of an XML tag &lt;/fieldName&gt;
	 *
	 * @param fieldName
	 * @param pw
	 */
	static public void writeXMLEnd(String fieldName, PrintWriter pw) {
		pw.print("</");
		pw.print(fieldName);
		pw.println('>');
	}

	/**
	 * Generates the XML open tag &lt;fieldName&gt;
	 *
	 * @param fieldName
	 * @param isNull if true then generates a self closing tag
	 * @param pw PrintWriter to write to
	 * @throws ProcessException
	 */
	static public void writeXMLStart(String fieldName, boolean isNull, PrintWriter pw) {
		writeXMLStart(fieldName, isNull, pw, false);
	}

	/**
	 * Generates the XML open tag as follows:
	 * <ul>
	 * <li>If the isNull - &lt;fieldName/&gt;
	 * <li>otherwise - &lt;fieldName&gt; a new line is inserted after this tag
	 * if endWithNewLine = true
	 * </ul>
	 *
	 * @param fieldName
	 * @param isNull if true generates a self closing tag
	 * @param pw
	 * @param endWithNewLine if true then will add a new line after '>' tag
	 * @throws Exception
	 */
	static public void writeXMLStart(String fieldName, boolean isNull, PrintWriter pw, boolean endWithNewLine) {
		pw.print('<');
		pw.print(fieldName);
		if (isNull) {
			pw.println("/>");
		} else {
			if (endWithNewLine)
				pw.println('>');
			else
				pw.print('>');
		}
	}

	/**
	 * Unmarshall supplied class from an xml string
	 *
	 * @param c class to unmarshal
	 * @param xmlStream XML string
	 */
	static public Object unmarshalFromString(Class c, String xmlStream) throws Exception {
		StringReader rdr = new StringReader(xmlStream);
		Object cInstance = ReflectUtils.makeInstance(c);
		return unmarshal(cInstance, rdr);
	}

	/**
	 * Unmarshal XML into the supplied class
	 *
	 * @param rootClass
	 * @param inputStream
	 * @return Object unmarshalled instance
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws XmlMarshalException
	 * @throws IOException
	 */
	static public Object unmarshal(Class rootClass, InputStream inputStream) throws Exception {
		Object cInstance = ReflectUtils.makeInstance(rootClass);
		return unmarshal(cInstance, inputStream);
	}

	/**
	 * Attempt to load the supplied object from the supplied XML input stream
	 * This method will delegate to unmarshal(Object root, Reader rdr)
	 *
	 * @param root object
	 * @param inputStream XML input stream
	 * @return Object unmarshaled instance
	 * @throws XmlMarshalException
	 * @throws IOException
	 */
	static public Object unmarshal(Object root, InputStream inputStream) throws Exception {
		BufferedReader rdr = new BufferedReader(new InputStreamReader(inputStream));
		return unmarshal(root, rdr);
	}

	/**
	 * Attempt to load the supplied object from the supplied XML input stream
	 * This method will delegate to unmarshal(Object root, InputStream
	 * inputStream, UnmarshalListener listener, InputStream mappingStream)
	 *
	 * @param root object
	 * @param inputStream XML input stream
	 * @param listener of unmarshalling events
	 * @return Object unmarshaled instance
	 * @throws XmlMarshalException
	 * @throws IOException
	 */
	static public Object unmarshal(Object root, InputStream inputStream, UnmarshalListener listener)
			throws Exception {
		return unmarshal(root, inputStream, listener, null);
	}

	/**
	 * Attempt to load the supplied object from the supplied XML input stream
	 * This method will delegate to unmarshal(Mapping mapping, Object root,
	 * InputStream inputStream, UnmarshalListener listener)
	 * <em>Note that all input streams will be closed by this method</em>
	 *
	 * @param root object
	 * @param inputStream XML input stream
	 * @param listener of unmarshalling events
	 * @param mappingStream an optional mapping file stream, if null mapping is
	 *                   not used.
	 *                   <em>Note that all streams will be closed by this method</em>
	 * @return Object unmarshaled instance
	 */
	static public Object unmarshal(Object root, InputStream inputStream, UnmarshalListener listener,
			InputStream mappingStream) throws Exception {

		Mapping mapping = null;
		try {
			if (mappingStream != null) {
				InputSource mappingSource = new InputSource(mappingStream);
				mapping = new Mapping();
				// mapping.setAllowRedefinitions(true);
				mapping.loadMapping(mappingSource);
			}
			return unmarshal(mapping, root, inputStream, listener);
		} finally {
			if (mappingStream != null) {
				mappingStream.close();
			}
		}
	}

	/**
	 * Attempt to load the supplied object using the supplied Mapping
	 * <em>Note that all input streams will be closed by this method</em>
	 *
	 * @param mapping an optional Mapping object
	 * @param root object
	 * @param xmllInput XML input stream
	 * @param listener listener of unmarshalling events
	 * @return Object unmarshaled instance
	 * @throws Exception
	 */
	static public Object unmarshal(Mapping mapping, Object root, String xmlInput, UnmarshalListener listener)
			throws Exception {
		StringReader rdr = new StringReader(xmlInput);
		return unmarshal(mapping, root, rdr, listener);
	}

	/**
	 * Attempt to load the supplied object using the supplied Mapping
	 * <em>Note that all input streams will be closed by this method</em>
	 *
	 * @param mapping an optional Mapping object
	 * @param root object
	 * @param inputStream XML input stream
	 * @param listener listener of unmarshalling events
	 * @return Object unmarshaled instance
	 * @throws Exception
	 */
	static public Object unmarshal(Mapping mapping, Object root, InputStream inputStream,
			UnmarshalListener listener) throws Exception {
		BufferedReader rdr = new BufferedReader(new InputStreamReader(inputStream));
		return unmarshal(mapping, root, rdr, listener);
	}

	/**
	 * Attempt to load the supplied object using the supplied Mapping
	 * <em>Note that all input streams will be closed by this method</em>
	 *
	 * @param mapping an optional Mapping object
	 * @param root object
	 * @param reader XML input stream
	 * @param listener listener of unmarshalling events
	 * @return Object unmarshaled instance
	 * @throws Exception
	 */
	static public Object unmarshal(Mapping mapping, Object root, Reader reader, UnmarshalListener listener)
			throws Exception {

		try {
			Unmarshaller un = createUnmarshaller(root);
			if (mapping != null) {
				un.setMapping(mapping);
			}
			if (listener != null) {
				un.setUnmarshalListener(listener);
			}
			return un.unmarshal(reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * Unmarshal information into the object from the supplied InputSource. Root
	 * object instance will be populated with values from the supplied XML
	 * stream.
	 *
	 * @param root of the unmarshall tree
	 * @param rdr input source reader
	 * @return Object unmarshalled instance
	 */
	static public Object unmarshal(Object root, Reader rdr) throws Exception {
		try {
			Unmarshaller un = createUnmarshaller(root);
			return un.unmarshal(rdr);
		} finally {
			if (rdr != null) {
				try {
					rdr.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}

	/**
	 * Creates an unmarshaller instane with the appropriate configuration
	 *
	 * @param root
	 * @return unmarshaller
	 */
	public static Unmarshaller createUnmarshaller(Object root) {
		Unmarshaller un = new Unmarshaller(root);
		un.setWhitespacePreserve(true);
		un.setReuseObjects(true);
		return un;
	}

	/**
	 * Convert a string into XML format. The result can be used safely as an
	 * attribute value or a string between tags.
	 *
	 * @param s the string to convert
	 * @return String escaped string
	 */
	public static String toXMLString(String s) {
		if (StringUtils.isEmpty(s)) {
			return "";
		}
		boolean mustChangeString = false;
		final int len = s.length();
		// first do one pass to see if we can avoid using an expensive
		// algorithm
		scan_loop : for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '&' :
				case '<' :
				case '>' :
				case '\"' :
					mustChangeString = true;
					break scan_loop;
				default :
					if (c > '\u007f') {
						mustChangeString = true;
						break scan_loop;
					}
			}
		}
		if (!mustChangeString) {
			return s;
		}
		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '&' :
					buffer.append("&amp;");
					break;
				case '<' :
					buffer.append("&lt;");
					break;
				case '>' :
					buffer.append("&gt;");
					break;
				case '\"' :
					buffer.append("&quot;");
					break;
				default :
					if (c > '\u007f') {
						buffer.append("&#").append(Integer.toString(c)).append(';');
					} else {
						buffer.append(c);
					}
			}
		}
		return buffer.toString();
	}
}
