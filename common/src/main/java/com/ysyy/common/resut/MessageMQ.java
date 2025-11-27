// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MessageMQ.java

package com.ysyy.common.resut;

import java.io.Serializable;

public class MessageMQ
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String classname;
	private Object body;

	public MessageMQ(String classname, Object body)
	{
		this.classname = classname;
		this.body = body;
	}

	public String getClassname()
	{
		return classname;
	}

	public MessageMQ()
	{
	}

	public void setClassname(String classname)
	{
		this.classname = classname;
	}

	public Object getBody()
	{
		return body;
	}

	public void setBody(Object body)
	{
		this.body = body;
	}
}
