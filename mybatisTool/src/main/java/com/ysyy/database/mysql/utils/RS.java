// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RS.java

package com.ysyy.database.mysql.utils;


public class RS
{

	public String cols[];
	public String values[];

	public RS(Integer size)
	{
		cols = new String[size.intValue()];
		values = new String[size.intValue()];
	}
}
