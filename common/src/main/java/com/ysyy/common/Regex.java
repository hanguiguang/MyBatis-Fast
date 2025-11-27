// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Regex.java

package com.ysyy.common;

import java.util.regex.Pattern;

public class Regex
{

	private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
	public static Boolean openCheck = Boolean.valueOf(false);

	public Regex()
	{
	}

	public static boolean isUsername(String username)
	{
		return Pattern.matches("^[a-zA-Z]\\w{5,20}$", username);
	}

	public static boolean isPassword(String password)
	{
		return Pattern.matches("^[a-zA-Z0-9]{6,20}$", password);
	}

	public static boolean isMobile(String mobile)
	{
		return Pattern.matches("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", mobile);
	}

	public static boolean isEmail(String email)
	{
		return Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", email);
	}

	public static boolean isChinese(String chinese)
{
	return Pattern.matches(REGEX_CHINESE, chinese);
}

	public static boolean isIDCard(String idCard)
	{
		return Pattern.matches("(^\\d{18}$)|(^\\d{15}$)", idCard);
	}

	public static boolean isUrl(String url)
	{
		return Pattern.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url);
	}

	public static boolean isIPAddr(String ipAddr)
	{
		return Pattern.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)", ipAddr);
	}

	public static boolean queryStrCheck(String strs[])
	{
		// 韩桂光 2018-11-23 屏蔽检查
		if (openCheck.booleanValue())
		{
			String arrayOfString[] = strs;
			int j = strs.length;
			for (int i = 0; i < j; i++)
			{
				String str = arrayOfString[i];
				if (str != null && str.toLowerCase().matches(".*delete.*|.*update.*|.*insert.*|.*create.*"))
					return false;
			}

		}
		return true;
	}

}
