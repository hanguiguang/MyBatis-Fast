// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ArchiveType.java

package com.ysyy.common.mvc;


public class ArchiveType
{

	public static final String ROLE = "101";
	public static final String ROLECategory = "101100";
	public static final String CATEGORY = "100";
	public static final String PERMISSION = "102";
	public static final String PERMISSIONCategory = "102100";
	public static final String USERRole = "000101";
	public static final String ROLEPERMISSION = "101102";
	public static final String USERPERMISSION = "000102";

	public ArchiveType()
	{
	}

	public static boolean judgeIn(String type)
	{
		return "101".equals(type) || "100".equals(type) || "102".equals(type) || "000101".equals(type) || "101102".equals(type) || "000102".equals(type) || "101100".equals(type) || "102100".equals(type);
	}
}
