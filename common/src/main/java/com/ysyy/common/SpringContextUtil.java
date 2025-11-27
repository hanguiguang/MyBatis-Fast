// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SpringContextUtil.java

package com.ysyy.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil
	implements ApplicationContextAware
{

	private static ApplicationContext applicationContext;

	public SpringContextUtil()
	{
	}

	public void setApplicationContext(ApplicationContext applicationContext)
		throws BeansException
	{
		applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	public static Object getBean(String name)
		throws BeansException
	{
		return applicationContext.getBean(name);
	}
}
