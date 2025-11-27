// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   App.java

package com.ysyy.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class App
{

	public App()
	{
	}

	public static void main(String args[])
	{
		System.out.println("Hello World!");
		List a = new ArrayList();
		String b = null;
		Integer c = Integer.valueOf(0);
		Boolean d = Boolean.valueOf(false);
		Object o = d;
		try
		{
			isEmpty(false, new Object[] {
					a, b, c, d, o
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean isEmpty(boolean isThrowOutBool, Object... args) throws Exception {
		int i = 0;
		boolean resut = true;
		Object[] var7 = args;
		int var6 = args.length;

		for(int var5 = 0; var5 < var6; ++var5) {
			Object a = var7[var5];
			++i;
			if (!msgThrowOut(isThrowOutBool, a == null, "第" + i + "个参数null！")) {
				return true;
			}

			System.out.println(a.getClass().getName());
			Class clazz = a.getClass();
			if (a instanceof List) {
				if (!msgThrowOut(isThrowOutBool, ((List)a).isEmpty(), "第" + i + "个参数为空")) {
					return true;
				}
			} else if (a instanceof String && !msgThrowOut(isThrowOutBool, a.toString().isEmpty(), "第" + i + "个参数为空")) {
				return true;
			}
		}

		return false;
	}

	private static boolean msgThrowOut(boolean isThrowOutBool, boolean isTrue, String msg)
		throws Exception
	{
		if (isThrowOutBool)
			if (isTrue)
				throw new Exception(msg);
			else
				return true;
		return !isTrue;
	}
}
