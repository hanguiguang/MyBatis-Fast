// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonService.java

package com.ysyy.mvc.service;


public interface CommonService
{

	public abstract String setToken();

	public abstract Boolean getToken(String s)
		throws Exception;

	public abstract Boolean removeToken(String s)
		throws Exception;

	public abstract String setVerificationCode(String s)
		throws Exception;

	public abstract String getVerificationCode(String s)
		throws Exception;

	public abstract String setEmployerLogin(String s, String s1, String s2, String s3)
		throws Exception;

	public abstract String setEmployerLoginLastUsing(String s)
		throws Exception;

	public abstract Object getEmployerLogin(String s)
		throws Exception;
}
