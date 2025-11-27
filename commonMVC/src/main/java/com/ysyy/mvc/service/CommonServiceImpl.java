// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonServiceImpl.java

package com.ysyy.mvc.service;

import com.alibaba.fastjson.JSON;
import com.ysyy.common.mvc.ComFun;
import com.ysyy.common.mvc.token.TokenEntity;
import com.ysyy.mvc.service.init.InitSysParmaters;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package com.ysyy.mvc.service:
//			CommonService

public class CommonServiceImpl
	implements CommonService
{

	private InitSysParmaters initSysParmaters;
	private HttpServletRequest request;

	public CommonServiceImpl()
	{
	}

	public String setToken()
	{
		TokenEntity token = new TokenEntity();
		initSysParmaters.types.getClass();
		token.setType("01");
		token.setCreateTime(Long.valueOf(System.currentTimeMillis()));
		token.setId(ComFun.getTokenId());
		token.setUid(ComFun.getUid(request));
		initSysParmaters.getTokenMap().put(token.getId(), token);
		return token.getId();
	}

	public Boolean getToken(String tokenId)
		throws Exception
	{
		if (initSysParmaters.getTokenMap().contains(tokenId))
			return Boolean.valueOf(true);
		else
			return Boolean.valueOf(false);
	}

	public Boolean removeToken(String tokenId)
		throws Exception
	{
		if (initSysParmaters.getTokenMap().get(tokenId) != null)
		{
			System.out.println(JSON.toJSON(initSysParmaters.getTokenMap()));
			initSysParmaters.getTokenMap().remove(tokenId);
			System.out.println(JSON.toJSON(initSysParmaters.getTokenMap()));
			return Boolean.valueOf(true);
		} else
		{
			return Boolean.valueOf(false);
		}
	}

	public String setVerificationCode(String mobileNumber)
		throws Exception
	{
		return null;
	}

	public Boolean removeVerificationCode(String mobileNumber)
		throws Exception
	{
		return null;
	}

	public String getVerificationCode(String mobileNumber)
		throws Exception
	{
		return null;
	}

	public String setEmployerLogin(String uid, String username, String name, String s)
		throws Exception
	{
		return null;
	}

	public String setEmployerLoginLastUsing(String uuidLogin)
		throws Exception
	{
		return null;
	}

	public Object getEmployerLogin(String uuidLogin)
		throws Exception
	{
		return null;
	}
}
