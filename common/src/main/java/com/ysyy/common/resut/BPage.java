// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BPage.java

package com.ysyy.common.resut;


// Referenced classes of package com.ysyy.common.resut:
//			QPage

public class BPage extends QPage
{

	private Integer btype;
	private String type;
	private String id;

	public BPage()
	{
	}

	public Integer getBtype()
	{
		return btype;
	}

	public void setBtype(Integer btype)
	{
		this.btype = btype;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
