// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Operator.java

package com.ysyy.common.resut.prompt;


public class Operator
{

	public static final String LESS = "<";
	public static final String LESS_OR_EQUAL = "<=";
	public static final String EQUAL = "=";
	public static final String NOT_EQUAL = "<>";
	public static final String GREATER_OR_EQUAL = ">=";
	public static final String GREATER = ">";
	public static final String START = "start";
	public static final String CONTAIN = "contain";
	public static final String AND_TYPE = "and";
	public static final String OR_TYPE = "or";

	public Operator()
	{
	}

	public static boolean oper(String op)
	{
		return "<".equals(op) || "<=".equals(op) || "=".equals(op) || "<>".equals(op) || ">=".equals(op) || ">".equals(op) || "contain".equals(op);
	}
}
