// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BPage.java

package com.ysyy.common.resut;


// Referenced classes of package com.ysyy.common.resut:
//			QPage

import java.util.List;

public class PageResult
{

	private Integer total;
	private List pageData;

	public PageResult()
	{
		total=null;
		pageData=null;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public List getPageData()
	{
		return pageData;
	}

	public void setPageData(List pageData)
	{
		this.pageData = pageData;
	}

}
