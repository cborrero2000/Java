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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;

/**
 * Utility methods to assist in reflection operations
 * 
 * @author Eugene Abramov
 */
public class ReflectUtils extends MethodUtils {

	/**
	 * Protected constrcutor
	 */
	protected ReflectUtils() {
	}

	/**
	 * Gets the class for the given name
	 * 
	 * @param name
	 * @return Class reflected class
	 */
	public static Class classForName(String name) throws ClassNotFoundException {
		 Class rs = Class.forName(name);
//		Class rs = BshClassManager.classForName(name);
//		if (rs == null) {
//			throw new ClassNotFoundException(name);
//		}
		return rs;
	}

	/**
	 * Creates a new instance of the supplied class
	 * 
	 * @param className
	 * @return Object
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object makeInstance(String className) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		Class c = classForName(className);
		return makeInstance(c);
	}

	/**
	 * Creates a new instance of the supplied class
	 * 
	 * @param c class
	 * @return Object instance
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object makeInstance(Class c) throws InstantiationException, IllegalAccessException {
		return c.newInstance();
	}

	/**
	 * Proxy method into BeanUtils.invokeConstructor
	 * 
	 * @param className
	 * @param args arguments
	 * @return Object constructed instance
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object makeInstance(String className, Object[] args) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		Class c = classForName(className);
		return ConstructorUtils.invokeConstructor(c, args);
	}

	/**
	 * Proxy method into BeanUtils.invokeConstructor
	 * 
	 * @param c class
	 * @param args Object[] arguments
	 * @param argTypes Class[] argument types
	 * @return Object constructed instance
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object makeInstance(Class c, Object[] args, Class[] argTypes) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		return ConstructorUtils.invokeExactConstructor(c, args, argTypes);
	}

	/**
	 * Proxy method into BeanUtils.invokeConstructor
	 * 
	 * @param c Class
	 * @param args Object[] arguments
	 * @return Object constructed instance
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object makeInstance(Class c, Object[] args) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException {
		return ConstructorUtils.invokeConstructor(c, args);
	}

	/**
	 * @param c Class
	 * @param arg Object argument
	 * @return Object constructed instance
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object makeInstance(Class c, Object arg) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException {
		return ConstructorUtils.invokeConstructor(c, arg);
	}

	/**
	 * Invokes supplied method against the supplied bean
	 * 
	 * @param bean
	 * @param method
	 * @param args
	 * @return Object value returned by the method invokation
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object bean, Method method, Object[] args)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return method.invoke(bean, args);
	}
}
