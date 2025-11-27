// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Page.java

package com.ysyy.common.resut;


// Referenced classes of package com.ysyy.common.resut:
//			BasePage

public class Page extends BasePage
{

	private String startRow;
	private String stopRow;
	private String queryStr;

	public Page()
	{
	}

	public String getStartRow()
	{
		return startRow;
	}

	public void setStartRow(String startRow)
	{
		this.startRow = startRow;
	}

	public String getStopRow()
	{
		return stopRow;
	}

	public void setStopRow(String stopRow)
	{
		this.stopRow = stopRow;
	}

	public String getQueryStr()
	{
		return queryStr;
	}

	public void setQueryStr(String queryStr)
	{
		this.queryStr = queryStr;
	}
}
