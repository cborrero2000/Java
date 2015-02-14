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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import java.util.List;

/**
 * Extension to Apache commons string utils
 *
 * @author Eugene Abramov
 */
public class StringUtilsExt extends StringUtils {

	public StringUtilsExt() {
	}

	/**
	 * Check if this string is empty when trimmed
	 */
	public static boolean isEmptyTrimmed(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * Trims the string to a maximum allowed size
	 *
	 * @param s string to trim
	 * @param maxSize
	 * @return trimmed string
	 */
	public static String trimToSize(String s, int maxSize) {
		if (isEmpty(s) || s.length() <= maxSize) {
			return s;
		}
		return s.substring(0, maxSize);
	}

	/**
	 * Trims the string to a maximum allowed size
	 *
	 * @param s string to trim
	 * @param maxSize
	 * @return trimmed string
	 */
	public static String trimAndRightPad(String s, int maxSize) {
		if (isEmpty(s)) {
			s = trimToEmpty(s);
		}
		String trimmed = trimToSize(s, maxSize);
		String rs = rightPad(trimmed, maxSize);
		return rs;
	}

	/**
	 * Break a string into multiple lines at the first word break after n-th
	 * character in the line.
	 * <em>Lines are separated by a new line chracter</em>
	 *
	 * @param text
	 * @param maxLineLength maxium length a line can reach before it is split.
	 * @return text broken up into n lines
	 */
	public static String breakIntoLines(String text, int maxLineLength) {
		int len = text.length();
		StringBuffer b = new StringBuffer(len + 10);
		int current = 0;
		do {
			int last = current + maxLineLength;
			if (last > len)
				last = len;
			while (current < last) {
				char c = text.charAt(current++);
				b.append(c);
				if (c == '\n') {
					last = current + maxLineLength;
					if (last > len)
						last = len;
				}
			}
			while (current < len) {
				char c = text.charAt(current++);
				if (Character.isSpaceChar(c) || c == '\n') {
					b.append('\n');
					break;
				} else
					b.append(c);
			}
		} while (current < len);
		return b.toString();
	}

	/**
	 * Converts a string into a boolean. <em>Note that case is ingored.</em>
	 * Possible values resulting in <b>true </b> are:
	 * <p>
	 * <ul>
	 * <li>Y
	 * <li>true
	 * </ul>
	 *
	 * @param s
	 * @return
	 */
	public static boolean toBoolean(String s) {
		if (equalsIgnoreCase(s, "Y")) {
			return true;
		}

		if (equalsIgnoreCase(s, "true")) {
			return true;
		}
		return false;
	}

	/**
	 * Splits the supplied string into a list of string separated by the
	 * separator
	 *
	 * @param s
	 * @param separator
	 * @return list of strings or null
	 */
	public static ArrayList splitToArrayList(String s, char separator) {
		if (isEmpty(s)) {
			return null;
		}
		String[] list = split(s, separator);
		ArrayList rs = ArrayUtilsExt.toList(list);
		return rs;
	}

	/**
	 * Splits the supplied string into a list of string separated by comma
	 *
	 * @param s
	 * @return list of strings or null
	 */
	public static ArrayList splitToArrayList(String s) {
		return splitToArrayList(s, ',');
	}

	/**
	 * Converts a string into an integer
	 *
	 * @param s
	 * @return int
	 */
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * Converts the string into an integer. If the string is empty default value
	 * is returned
	 *
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static int toInt(String s, int defaultValue) {
		if (isEmpty(s)) {
			return defaultValue;
		}
		return toInt(s);
	}

	/**
	 * Converts a string into a long
	 *
	 * @param s
	 * @param defaultValue
	 * @return long
	 */
	public static long toLong(String s, long defaultValue) {
		if (isEmpty(s)) {
			return defaultValue;
		}
		return Long.parseLong(s);
	}

	/**
	 * Converts a string into a long
	 *
	 * @param s
	 * @return long
	 */
	public static long toLong(String s) {
		return Long.parseLong(s);
	}

	/**
	 * Converts a string into a double
	 *
	 * @param s
	 * @return double
	 */
	public static double toDouble(String s) {
		return Double.parseDouble(s);
	}

	/**
	 * Converts a string into a float
	 *
	 * @param s
	 * @return float
	 */
	public static double toFloat(String s) {
		return Float.parseFloat(s);
	}

	/**
	 * Checks if the supplied string is numeric, non-empty and is &gt; 0
	 *
	 * @param s
	 * @return true if the string is numeric
	 */
	public static boolean isNumericNonZero(String s) {
		if (isEmptyTrimmed(s)) {
			return false;
		}
		if (!isNumeric(s)) {
			return false;
		}
		long l = Long.parseLong(s);
		if (l < 1) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the supplied string is a number. A valid number may contain
	 * [+-.0-9]
	 *
	 * @param s
	 * @return true if the number is a decimal representation
	 */
	public static boolean isNumericDecimal(String s) {
		if (isEmptyTrimmed(s)) {
			return false;
		}

		final int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '.' :
					break;
				case '+' :
					break;
				case '-' :
					break;
				default :
					if (!Character.isDigit(c)) {
						return false;
					}
			}
		}
		return true;
	}

	/**
	 * Checks if the supplied string is composed only of characters representing
	 * digits in the specified radix
	 *
	 * @param s
	 * @param radix
	 * @return true if the number is a representation of a number in the specified
	 * radix
	 */
	public static boolean isNumeric(String s, int radix) {
		if (isEmptyTrimmed(s)) {
			return false;
		}

		final int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '.' :
					break;
				default :
					if (Character.digit(c, radix) < 0) {
						return false;
					}
			}
		}
		return true;
	}

	/**
	 * Parses a string from the supplied line record, and leaves the leading
	 * spaces in tact
	 *
	 * @param record
	 * @param start
	 * @param len
	 * @return
	 */
	public static String parseString(String record, int start, int len) {
		return parseString(record, start, len, false);
	}

	/**
	 * This method will extract a string of the specified length from a line
	 * record.
	 *
	 * @param record
	 * @param start
	 * @param len
	 * @param removeLeadingSpace - removes leading space
	 * @return - extracted string, or null if the record can not be parsed
	 */
	public static String parseString(String record, int start, int len, boolean removeLeadingSpace) {
		start--;
		final int stringLen = record.length();
		if (stringLen <= start) {
			return null;
		}

		int endIndex = (start + len - 1);
		if (endIndex >= stringLen) {
			endIndex = stringLen - 1;
		}

		while (endIndex > start && record.charAt(endIndex) == ' ') {
			endIndex--;
		}

		if (removeLeadingSpace) {
			// now search from startIndex to endIndex
			// and stop at the first non space
			while (start < endIndex && record.charAt(start) == ' ') {
				start++;
			}
		}
		return record.substring(start, endIndex + 1);
	}

	/**
	 * Read an int from a line record.
	 *
	 * @param record
	 * @param startIndex
	 * @param len
	 * @return int
	 */
	public static int parseInt(String record, int startIndex, int len) {
		String s = parseString(record, startIndex, len, true);
		return toInt(record);
	}

	/**
	 * Writes a string to the supplied string buffer
	 *
	 * @param buffer
	 * @param s
	 * @param size
	 */
	public static void writeString(StringBuffer buffer, String s, int size) {
		writeString(buffer, s, size, true);
	}

	/**
	 * Writes a string left justified, padding with space or trimming to size.
	 *
	 * @param out
	 * @param s
	 * @param size
	 * @param leftAligned true for left alignment
	 */
	public static void writeString(StringBuffer buff, String s, int size, boolean leftAligned) {
		writeString(buff, s, ' ', size, leftAligned);
	}

	/**
	 * Writes a string left or right justified, padding a character or trimming
	 * to size.
	 *
	 * @param out - output buffer
	 * @param s - string to write
	 * @param padChar
	 * @param size
	 * @param leftAligned true for left alignment
	 */
	public static void writeString(StringBuffer buff, String s, char padChar, int size, boolean leftAligned) {
		final int strLen = (s == null ? 0 : s.length());
		int nrWritten = 0;
		if (!leftAligned) {
			int spaceToWrite = (size - strLen);

			for (; nrWritten < spaceToWrite; nrWritten++) {
				buff.append(padChar);
			}
		}
		for (int i = 0; i < size & nrWritten < size; i++, nrWritten++) {
			if (i < strLen) {
				buff.append(s.charAt(i));
			} else {
				buff.append(padChar);
			}
		}
	}

	/**
	 * Writes an int to the supplied string buffer
	 *
	 * @param buffer
	 * @param i
	 * @param size
	 */
	public static void writeInt(StringBuffer buffer, int i, int size) {
		String s = Integer.toString(i);
		writeString(buffer, s, size, false);
	}

	/**
	 * Padds the supplied string with the padding character such that
	 * <p>
	 * <b>text.length() % mod == 0 </b>
	 *
	 * @param text
	 * @param mod
	 * @param pad
	 * @return padded string or null if the text is null
	 */
	public static String padRightToMod(String text, int mod, char pad) {
		if (text == null || mod <= 0) {
			return text;
		}
		final int len = text.length();
		StringBuffer buf = new StringBuffer(512);
		buf.append(text);
		for (int i = len; i % mod > 0; i++) {
			buf.append(pad);
		}
		String rs = buf.toString();
		return rs;
	}

	/**
	 * Left padds the supplied string with the padding character such that
	 * <p>
	 * <b>text.length() % mod == 0 </b>
	 *
	 * @param text
	 * @param mod
	 * @param pad
	 * @return padded string or null if the text is null
	 */
	public static String padLeftToMod(String text, int mod, char pad) {
		if (text == null || mod <= 0) {
			return text;
		}
		final int len = text.length();
		StringBuffer buf = new StringBuffer(512);
		buf.append(text);
		for (int i = len; i % mod > 0; i++) {
			buf.insert(0, pad);
		}
		String rs = buf.toString();
		return rs;
	}

	/**
	 * Checks if the supplied string starts with the prefix
	 *
	 * @param s
	 * @param prefix
	 * @return
	 */
	public static boolean startsWith(String s, String prefix) {
		if (isEmpty(s)) {
			return false;
		}
		return s.startsWith(prefix);
	}

	/**
	 * Checks if the supplied string ends with the suffix
	 *
	 * @param s
	 * @param suffix
	 * @return
	 */
	public static boolean endsWith(String s, String suffix) {
		if (isEmpty(s)) {
			return false;
		}
		return s.endsWith(suffix);
	}

	/**
	 * Ensure that string ends with the suffix
	 *
	 * @param str source string
	 * @param suffix
	 * @return string that sure ends with suffix
	 */
	public static String ensureEndsWith(String str, String suffix) {
		if (str != null && !isEmpty(suffix) && !endsWith(str, suffix)) {
			return str + suffix;
		}
		return str;
	}

	/**
	 * Checks if the supplied string ends with or starts with the prefix/suffix
	 * string
	 *
	 * @param s
	 * @param string
	 * @return
	 */
	public static boolean startOrEndsWith(String s, String string) {
		if (isEmpty(s)) {
			return false;
		}
		if (endsWith(s, string) || startsWith(s, string))
			return true;
		return false;
	}

	/**
	 * Checks if the supplied string starts with lowercase letter
	 *
	 * @param s
	 * @param string
	 * @return
	 */
	public static boolean startsWithLowerCase(String s) {
		if (isEmpty(s)) {
			return false;
		}
		if (StringUtilsExt.equals(s.substring(0, 1), StringUtilsExt.capitalize(s.substring(0, 1))))
			return false;
		return true;
	}

	/**
	 * Padds the supplied string with the spaces such that
	 * <p>
	 * <b>text.length() % mod == 0 </b>
	 *
	 * @param text
	 * @param mod
	 * @return padded string or null if the string is null
	 */
	public static String padRightToMod(String text, int mod) {
		return padRightToMod(text, mod, ' ');
	}

	/**
	 * Compares two strings by comparing their trimmed versions.
	 *
	 * @param str1
	 * @param str2
	 *
	 * @return <code>true</code> if both str1 and str2 are <code>null</code>
	 *         or if the trimmed versions of both are identical
	 *         <code>false</code> otherwise
	 */
	public static boolean equalsTrimmed(String str1, String str2) {
		if (str1 == str2) {
			return true;
		}
		str1 = trim(str1);
		str2 = trim(str2);
		return equals(str1, str2);
	}

	/**
	 * Compares two strings by comparing their trimmed versions.
	 *
	 * @param str1
	 * @param str2
	 *
	 * @return <code>true</code> if both str1 and str2 are <code>null</code>
	 *         or if the trimmed versions of both are identical
	 *         <code>false</code> otherwise
	 */
	public static boolean equalsTrimmedNull(String str1, String str2) {
		str1 = trim(str1);
		str2 = trim(str2);
		if (str1 == null && "".equals(str2)) {
			return true;
		} else if (str2 == null && "".equals(str1)) {
			return true;
		}

		return equalsTrimmed(str1, str2);
	}

	/**
	 * Remove characters defined by &quot;search&quot; string
	 *
	 * @param s the string to convert
	 * @param search array of characters to be removed
	 * @return cleaned up string
	 */
	public static String removeChars(String s, String search) {
		if (StringUtils.isEmpty(s)) {
			return s;
		}

		if (StringUtils.isEmpty(search)) {
			return s;
		}

		boolean mustChangeString = false;
		final int searchLen = search.length();
		final int len = s.length();

		// first do one pass to see if we can avoid using an expensive algorithm
		scan_loop : for (int i = 0; i < searchLen; i++) {
			char c = search.charAt(i);
			int index = s.indexOf(c);
			if (index > -1) {
				mustChangeString = true;
				break scan_loop;
			}
		}
		if (!mustChangeString) {
			return s;
		}

		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			int index = search.indexOf(c);
			if (index == -1) {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}

	/**
	 * Remove index expression from a string. <br>
	 * Example:
	 * <ol>
	 * <li>removeIndex("phone[2].empty") =&lt; "phone.empty"</li>
	 * <li>removeIndex("phone[2,2].empty") =&lt; "phone.empty"</li>
	 * <li>removeIndex("phone[2][3].empty") =&lt; "phone.empty"</li>
	 * <li>removeIndex("phone.empty") =&lt; "phone.empty"</li>
	 * <li>removeIndex("phone[2]xx[3].empty") =&lt; "phonexx.empty"</li>
	 * <li>removeIndex("phone[alfa].empty") =&lt; "phone.empty"</li>
	 * <li>removeIndex("phone[alfa][beta].empty") =&lt; "phone.empty"</li>
	 * </ol>
	 *
	 * @param s
	 * @return
	 */
	public static String removeIndexExpr(String s) {
		// see if we have an openning brace
		if (isEmpty(s)) {
			return s;
		}
		int firstParen = s.indexOf('[');
		if (firstParen == -1) {
			return s;
		}
		// we need to remove the expressions
		StringBuffer buffer = new StringBuffer();
		boolean remove = false;
		final int size = s.length();
		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);
			if (c == '[') {
				remove = true;
			} else if (c == ']') {
				remove = false;
			} else if (!remove) {
				buffer.append(c);
			}
		}
		return buffer.toString();

	}

	/**
	 * This method will format the message contained in the first element of the
	 * supplied array in the default locale
	 *
	 * @param args
	 * @return formatted text
	 */
	public static String formatString(Object[] args) {
		return formatString(args, null);
	}

	/**
	 * This method assumes that the actual message to be formatted is the first
	 * element of the supplied array. If the supplied array has only one element
	 * that element is the message
	 *
	 * @param args
	 * @param locale or null if the default should be used
	 * @return
	 */
	public static String formatString(Object[] args, Locale locale) {
		if (args == null || args.length < 1) {
			return null;
		}
		Object msg = args[0];
		if (msg == null || !(msg instanceof String)) {
			return null;
		}

		String sMsg = (String) msg;
		final int len = args.length;
		if (len < 2) {
			return sMsg;
		}

		final int argsLen = len - 1;
		Object[] fmtArgs = new Object[argsLen];
		System.arraycopy(args, 1, fmtArgs, 0, argsLen);
		return formatString(sMsg, fmtArgs);
	}

	/**
	 * Format the supplied message in the specified locale
	 *
	 * @param message
	 * @param args
	 * @return formatted text
	 */
	public static String formatString(String message, Object[] args) {
		return formatString(message, args, null);

	}

	/**
	 * A utility method to format the supplied message using MessageFormat
	 *
	 * @param message
	 * @param args
	 * @param locale may be null
	 * @return formatted text
	 */
	public static String formatString(String message, Object[] args, Locale locale) {
		MessageFormat formatter = null;
		if (locale != null) {
			formatter = new MessageFormat(message, locale);
		} else {
			formatter = new MessageFormat(message);
		}
		String formattedText = formatter.format(args);
		return formattedText;
	}

	/**
	 * Determines if the supplied string contains any special characters which
	 * should not be allowed as input from a web application
	 *
	 * @param s
	 * @return true if all chracters are legal web input
	 */
	public static boolean isValidUserInput(String s) {
		return (StringUtilsExt.indexOfAny(s, "<>\"") < 0);
	}

	/**
	 * Determines if the supplied string contains only Alpha characters, spaces,
	 * periods, apostrophe and a comma
	 *
	 * @param s
	 * @return
	 */
	public static boolean isAlphaAndPunctuation(String s) {
		if (s == null) {
			return false;
		}
		int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (!Character.isLetter(ch) && !Character.isWhitespace(ch) && !isPunctuation(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the supplied string contains only AlphaNumeric characters, spaces,
	 * periods, apostrophe and a comma
	 *
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumericAndPunctuation(String s) {
		if (s == null) {
			return false;
		}
		int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch) && !isPunctuation(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the supplied string contains only Alpha characters and
	 * spaces
	 *
	 * @param s
	 * @return
	 */
	public static boolean isAlphaAndSpaces(String s) {
		if (s == null) {
			return false;
		}
		int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
				return false;
			}
		}
		return true;
	}

	/**	 Determines if the supplied string contains only Alpha characters or not-Alpha exclusion
	 * 
	 * sample
	 * char[] excludeChars = {'-', '_', '.'};
	 * */
	   public static boolean isAlphanumericWithExclude(String str,char[] excludeChars) {
	        if (str == null) {return false;}
	        
	        int sz = str.length();
			boolean exclude = false;
			for (int i = 0; i < sz; i++) {
				if (Character.isLetterOrDigit(str.charAt(i)) == false) {
					exclude = false;
					char ch = str.charAt(i);
					for (int j = 0; j < excludeChars.length; j++) {
						if (excludeChars[j] == ch) {
							exclude = true;
							break;
						}
					}
					if(!exclude){break;}
				}
			}
	        return exclude;
	    }
	/**
	 * Determines if the supplied string is in Title Case
	 *
	 * @param s
	 * @return
	 */
	public static boolean isTitleCase(String s) {
        return isTitleCase(s, false);
    }

	/**
     * Determines if the supplied string is in Title Case
     * with handling for letter after Apostrophes and Hyphen
     *
     * @param s
     * @param isLowerOk   letter after "'" and "-" can be upper or lower case
     *
     * @return
	 */

	public static boolean isTitleCase(String s, boolean isLowerOk) {
		boolean isFirstLetter = true;
		if (s == null) {
			return false;
		}
        boolean previousCharSpecial= false;
		int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (Character.isWhitespace(ch)) {
				isFirstLetter = true;
			} else {
                if(StringUtilsExt.equals(String.valueOf(ch),"'") ||
                   StringUtilsExt.equals(String.valueOf(ch),"-")
                ){
                    previousCharSpecial = true;
                    continue;
                }
                if(previousCharSpecial){
                   previousCharSpecial = false;
                   if(isLowerOk){
                       continue;
                   } else {
                       if (Character.isLowerCase(ch)){
                           return false;
                       } else {
                           previousCharSpecial = false;
                           continue;
                       }
                   }
                }
				if (Character.isLetter(ch) && isFirstLetter) {
					isFirstLetter = false;
					if (Character.isLowerCase(ch))
						return false;
				} else if (Character.isLetter(ch) && !isFirstLetter) {
					if (Character.isUpperCase(ch))
						return false;
				}
			}
		}
		return true;
	}

	/**
	 *
	 Determines if the supplied string is in Title Case
     * with handling for letter after Apostrophes and Hyphen or upper case
     *
     *
     * @param s
     * @param isLowerOk   TRUE - if lower case is allowed after after "'" and "-"
	 */
	public static boolean isTitleCaseOrUpperCase(String s, boolean isLowerOk) {
		StringTokenizer st = new StringTokenizer(s);
		String nextWord = "";
		while (st.hasMoreTokens()) {
			nextWord = st.nextToken();
			if (!StringUtilsExt.isTitleCase(nextWord, isLowerOk) && !StringUtilsExt.isUpperCase(nextWord, isLowerOk)) {
				return false;
			}
		}
		return true;
	}

    /**
     *
     * Determines if the supplied string has two Upper Case letters in sequence
     *
     * @param s
     * @return
     */
    public static boolean isSequentialUpperCase(String s) {
        if (s == null) {
            return false;
        }
        boolean previousCharUpper= false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            char ch = s.charAt(i);
            if (Character.isWhitespace(ch)) {
                previousCharUpper= false;
                continue;
            }
            if (Character.isLetter(ch)){
                if(Character.isUpperCase(ch)) {
                    if(previousCharUpper)
                        return true;
                    previousCharUpper= true;
                } else {
                    previousCharUpper= false;
                }
            }
        }
        return false;
    }

    /**
     * In the provided string letter after apostrophes can be upper or lover case
     * This helper method determines if the string is valid
     *
     * @param s
     * @param isLowerCaseAllowed     TRUE if lower case is allowed after apostrophes
     * @return
     */
    public static boolean isApostropheRuleOk(String s, boolean isLowerCaseAllowed) {
        if (s == null) {
            return false;
        }
        boolean apostropheFound= false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            String ch = Character.toString(s.charAt(i));

            if (equals(ch, "'")) {
                apostropheFound= true;
                continue;
            } else {
                if (apostropheFound){
                    if(isUpperCase(ch) || isLowerCaseAllowed) {
                        return true;
                    } else {
				return false;
			}
		}
            }

        }
		return true;
	}

	/**
	 * Determines if the supplied string is in Upper Case
	 *
	 * @param s
	 * @return
	 */
	public static boolean isUpperCase(String s) {
        return isUpperCase(s, false);
    }

	/** In the provided string letter after hyphen can be upper or lover case
     * This helper method determines if the string is valid
     *
     * @param s
     * @param isLowerCaseAllowed     TRUE if lower case is allowed after apostrophes
     * @return
     */
    public static boolean isHyphenRuleOk(String s, boolean isLowerCaseAllowed) {
        if (s == null) {
            return false;
        }
        boolean hyphenFound= false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            String ch = Character.toString(s.charAt(i));

            if (equals(ch, "-")) {
                hyphenFound= true;
                continue;
            } else {
                if (hyphenFound){
                    if(isUpperCase(ch) || isLowerCaseAllowed) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    /**
        return isUpperCase(s, false);
    }

	/**
     * Determines if the supplied string is in Upper Case
     * with handling for letters after "'" and "-"
     *
     * @param s
     * @param isLowerAllowed   letter after "'" and "-" can be upper or lower case
     *
     * @return
	 */
	public static boolean isUpperCase(String s, boolean isLowerAllowed) {
		if (s == null)
			return false;

        boolean previousCharSpecial= false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            char ch = s.charAt(i);
            if(StringUtilsExt.equals(String.valueOf(ch),"'") ||
               StringUtilsExt.equals(String.valueOf(ch),"-")){
                    previousCharSpecial = true;
                    continue;
            }
            if(previousCharSpecial){
               previousCharSpecial = false;
               if(isLowerAllowed){
                   continue;
               }
            }
            if (Character.isLowerCase(ch)){
                return false;
        }
        }
        return true;
	}

    /**
     * Determines if the supplied string is in Lower Case
     *
     * @param s
     * @return
     */
    public static boolean isLowerCase(String s) {
        if (s == null)
            return false;
        String sLower = s.toLowerCase();
        if (s.equals(sLower))
			return true;
		return false;
	}

	/**
	 * Determines if the supplied string is initial
	 *
	 * @param s
	 * @return
	 */
	public static boolean isInitial(String s) {
		if (s == null)
			return false;
		String sUpper = s.toUpperCase();
		if (s.equals(sUpper) && (s.length() == 1))
			return true;
		return false;
	}

    /**
     *
     * Determines if the supplied string has initial at the end
     * with one space in front of the initial
     *
     * @param s
     * @return
     */
    public static boolean isInitialAtTheEnd(String s) {
        if (s == null)
            return false;
        if (s.length() < 3)
            return false;
        int stringCount = s.length();
        String last = s.substring(stringCount-1,stringCount);
        String beforeLast = s.substring(stringCount-2,stringCount-1);
        String beforeBeforeLast = s.substring(stringCount-3,stringCount-2);

        if (!isInitial(last))
            return false;
        if(!isEmptyTrimmed(beforeLast))
            return false;
        if(isEmptyTrimmed(beforeBeforeLast))
            return false;
        return true;
	}

	/**
	 * Determines if the supplied string contains only alphanumeric character
	 * and dot
	 *
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumericAndDot(String s) {
		return isAlphaNumericAndOther(s, '.');
	}

	/**
	 * Determines if the supplied string contains only alphanumeric character
	 * and dash
	 *
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumericAndDash(String s) {
		return isAlphaNumericAndOther(s,'-');
	}
	/**
	 * Determines if the supplied string contains only alphanumeric character
	 * and custom char as an exception. set exceptChar="" for no exceptions
	 *
	 * @param s
	 * @return
	 */
	/*--Alex B added--*/
	public static boolean isAlphaNumericAndException(String s,String exceptChar) {
		if(exceptChar.length()==0){exceptChar=""+null;}
		return isAlphaNumericAndOther(s,exceptChar.charAt(0));
		
	}
	
	/**
	 * Determines if the supplied string contains only alphanumeric character
	 * and some other calller specified character
	 *
	 * @param string to be tested
	 * @param other character to be checked for in addition to alphanumerics
	 * @return
	 */
	private static boolean isAlphaNumericAndOther(String s, char other) {
		if (s == null) {
			return false;
		}
		int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (!(Character.isLetterOrDigit(ch) || ch == other)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the supplied string is a valid Java identifier string, and
	 * in addtion <b>- </b> is also a valid character.
	 *
	 * @param s
	 * @return true if the supplied string is a valid name identifier
	 * @see java.lang.Character#isJavaIdentifierPart(char)
	 */
	public static boolean isNameIdentifier(String s) {
		if (isEmptyTrimmed(s)) {
			return false;
		}
		final int sz = s.length();
		for (int i = 0; i < sz; i++) {
			char ch = s.charAt(i);
			if (ch != '-' && !Character.isJavaIdentifierPart(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the supplied character is a legal punctuation character
	 *
	 * @param c
	 * @return true if the character is a valid punctuation which can be used in
	 *         names
	 */
	public static boolean isPunctuation(char c) {
		switch (c) {
			case ',' :
				return true;
			case '.' :
				return true;
			case '-' :
				return true;
			case '&' :
				return true;
			case 0xA9 :
				return true;
			case 0x27 :
				return true;
			case 0x60 :
				return true;
			case 0xB4 :
				return true;
			default :
				return false;
		}

	}

	/**
	 * <p>
	 * Splits the provided text into an array, separator specified. This is an
	 * alternative to using StringTokenizer.
	 * </p>
	 *
	 * @param str the String to parse, may be null
	 * @param separatorChar the character used as the delimiter,
	 *            <code>null</code> splits on whitespace
	 * @param keepNull if set to true then the returned array will have null
	 *            values in place of two adjacent separators
	 * @return an array of parsed Strings, <code>null</code> if null String
	 *         input
	 */
	public static ArrayList splitToArray(String str, char separatorChar, boolean keepNull) {
		if (!keepNull) {
			return splitToArrayList(str, separatorChar);
		}

		if (str == null) {
			return null;
		}

		ArrayList list = new ArrayList(5);
		final int len = str.length();
		if (len == 0) {
			return list;
		}

		int i = 0, start = 0;
		boolean match = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (i == 0) {
					list.add(null);
				}
				// No token between two adjacent separators
				else if (i == start) {
					list.add(null);
				}
				// Found the next separator
				else {
					String s = str.substring(start, i);
					list.add(s);
				}
				i++;
				start = i;
				continue;
			}
			i++;
		}
		if (start < i) {
			String s = str.substring(start, len);
			list.add(s);
		} else if (start == i) {
			list.add(null);
		}
		return list;
	}

	/**
	 * This method will mask all characters starting at the beginning of the
	 * supplied string by replacing the original characters with the supplied
	 * mask
	 *
	 * @param input
	 * @param mask
	 * @return maskes string
	 */
	public static String maskAll(String input, String mask) {
		return mask(input, mask, 0);
	}

	/**
	 * This method will mask all characters upto <b>numUnmodified </b> starting
	 * at the beginning of the supplied string by replacing the original
	 * characters with the supplied mask
	 *
	 * @param input
	 * @param mask
	 * @param numUnmodified
	 * @return maskes string
	 */
	public static String mask(String input, String mask, int numUnmodified) {

		if (isEmpty(input)) {
			return input;
		}
		final int numMasked = input.length() - numUnmodified;

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			if (i >= numMasked) {
				buf.append(input.charAt(i));
			} else {
				buf.append(mask);
			}
		}
		String rs = buf.toString();
		return rs;
	}

	/**
	 * Helper function to calculate the 2#x operation used in the IBM checksum.
	 *
	 * @param nr
	 * @return the sum of the digits of 2 * nr
	 */
	private static int sharp(int nr) {
		int t = nr * 2;
		return (int) (t / 10) + (t % 10);
	}

	/**
	 * Calculate the IBM check sum for a string
	 *
	 * @param digits
	 * @return the IBM checksum
	 */
	public static int calculateIbmCheckSum(CharSequence digits) {
		final int strLength = digits.length();
		int sum = 0;
		boolean flip = true;
		for (int i = strLength - 1; i >= 0; i--) {
			int digit = digits.charAt(i) - '0';
			if (flip) {
				sum += digit;
			} else {
				sum += sharp(digit);
			}
			flip = !flip;
		}
		return sum % 10;
	}

	/**
	 * Insert a check digit at the end of the string choosen so that the IBM
	 * checksum will give us 0
	 *
	 * @param digits
	 * @return a string with the check digit appended
	 */
	public static String appendIbmCheckDigit(String digits) {
		StringBuffer buffer = new StringBuffer(digits);
		buffer.append("0");
		int checkSum = calculateIbmCheckSum(buffer);
		char checkDigit = (char) ('0' + ((10 - checkSum) % 10));
		int length = buffer.length();
		buffer.setCharAt(length - 1, checkDigit);
		return buffer.toString();
	}

    public static String[] SplitString(String str, char separatorChar) {
		if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List list = new ArrayList(5);
        int i =0, start = 0;
        boolean match = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                list.add(str.substring(start, i));
                start = ++i;
				match = true;
                continue;
            } else {
				match = false;
            }
            i++;
        } if (!match) {
			list.add(str.substring(start, i));
		} return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * Remove characters from supplied stirng
     * @param str Source stirng
     * @param chToRemove array of characters to filter out
     * @param replaceChr replacement character
     * @return string with characters removed as passed in the chToRemove paramater
     */
	public static String removeChars(String str, char[] chToRemove, char replaceChr) {
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			for (int j = 0; j < chToRemove.length; j++) {
				if (ch[i] == chToRemove[j]) {
					ch[i] = replaceChr;
				}
			}
		}
		return new String(ch);
	}
}