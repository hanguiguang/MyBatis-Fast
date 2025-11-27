// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SimpleCORSFilter.java

package com.ysyy.common.mvc.cors;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class SimpleCORSFilter
	implements Filter
{

	public SimpleCORSFilter()
	{
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException
	{
		HttpServletResponse response = (HttpServletResponse)res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,tokenLogin,Content-Type,token,uploadFileType,X_Requested_With");
		response.setHeader("Access-Control-Expose-Headers", "token");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterconfig)
	{
	}

	public void destroy()
	{
	}
}
