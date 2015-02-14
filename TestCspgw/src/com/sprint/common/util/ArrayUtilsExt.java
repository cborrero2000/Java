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
 * Created on Mar 13, 2004
 */
package com.sprint.common.util;

import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An extension to ArrayUtils in Apache commons-lang
 * 
 * @author Eugene Abramov
 */
public class ArrayUtilsExt extends ArrayUtils {

	/**
	 * All methods are static
	 */
	protected ArrayUtilsExt() {
	}

	/**
	 * Appends to toAppend array to the source array
	 * 
	 * @param source
	 * @param toAppend
	 * @return two arrays concatenated
	 */
	public static Object[] append(Object[] source, Object[] toAppend) {
		if (toAppend == null) {
			return source;
		}
		if (source == null) {
			return toAppend;
		}
		final int sourceLen = source.length;
		final int toAppendLen = toAppend.length;
		Object[] rs = (Object[]) Array.newInstance(source.getClass().getComponentType(), sourceLen
				+ toAppendLen);
		System.arraycopy(source, 0, rs, 0, sourceLen);
		System.arraycopy(toAppend, 0, rs, sourceLen, toAppendLen);
		return rs;
	}

	/**
	 * Converts the supplied object list into an ArrayList
	 * 
	 * @param array
	 * @return array list or null
	 */
	public static ArrayList toList(Object[] array) {
		if (array == null) {
			return null;
		}
		final int len = array.length;
		ArrayList rs = new ArrayList(len);
		for (int i = 0; i < len; i++) {
			rs.add(array[i]);
		}
		return rs;
	}
	
    /**
     * <p>Checks if a Object array is empty or null.</p>
     * 
     * @param arr  the Object array to check, may be null
     * @return <code>true</code> if the Object array is empty or null
     */
    public static boolean isEmpty(Object[] arr) {
        return (arr == null || arr.length == 0);
    }

    /**
     * <p>Checks if a double array is empty or null.</p>
     * 
     * @param arr  the double array to check, may be null
     * @return <code>true</code> if the double array is empty or null
     */
    public static boolean isEmpty(double[] arr) {
        return (arr == null || arr.length == 0);
    }

    /**
     * <p>Checks if a float array is empty or null.</p>
     * 
     * @param arr  the float array to check, may be null
     * @return <code>true</code> if the float array is empty or null
     */
    public static boolean isEmpty(float[] arr) {
        return (arr == null || arr.length == 0);
    }
    
    /**
     * <p>Checks if the byte array is empty or null.</p>
     * 
     * @param arr  the byte array to check, may be null
     * @return <code>true</code> if the byte array is empty or null
     */
    public static boolean isEmpty(byte[] arr) {
        return (arr == null || arr.length == 0);
    }

    /**
     * <p>Checks if the short array is empty or null.</p>
     * 
     * @param arr  the short array to check, may be null
     * @return <code>true</code> if the short array is empty or null
     */
    public static boolean isEmpty(short[] arr) {
        return (arr == null || arr.length == 0);
    }

    /**
     * <p>Checks if the long array is empty or null.</p>
     * 
     * @param arr  the long array to check, may be null
     * @return <code>true</code> if the long array is empty or null
     */
    public static boolean isEmpty(long[] arr) {
        return (arr == null || arr.length == 0);
    }

    /**
     * <p>Checks if the char array is empty or null.</p>
     * 
     * @param arr  the char array to check, may be null
     * @return <code>true</code> if the char array is empty or null
     */
    public static boolean isEmpty(char[] arr) {
        return (arr == null || arr.length == 0);
    }

    
    /**
     * <p>Checks if the int array is empty or null.</p>
     * 
     * @param arr  the int array to check, may be null
     * @return <code>true</code> if the int array is empty or null
     */
    public static boolean isEmpty(int[] arr) {
        return (arr == null || arr.length == 0);
    }

    
    /**
     * <p>Checks if a boolean array is empty or null.</p>
     * 
     * @param arr  the boolean array to check, may be null
     * @return <code>true</code> if the boolean array is empty or null
     */
    public static boolean isEmpty(boolean[] arr) {
        return (arr == null || arr.length == 0);
    }
    
    
    
}