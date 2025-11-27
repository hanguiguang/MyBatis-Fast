// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   States.java

package com.ysyy.common.mvc;


public class States
{

	public static final int USING = 1001;
	public static final int STOP = 1101;
	public static final int Frozen = 1003;

	public States()
	{
	}

	public static boolean judgeIn(int a)
	{
		return 1001 == a || 1101 == a || 1003 == a;
	}

	public static boolean judgeCheck(int a)
	{
		return 1001 == a || 1101 == a;
	}
}
