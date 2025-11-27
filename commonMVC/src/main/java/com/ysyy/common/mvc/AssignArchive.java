// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AssignArchive.java

package com.ysyy.common.mvc;

import java.util.List;

public class AssignArchive
{

	private String id;
	private List ids;
	private List addIds;
	private List delIds;
	private String type;
	private Integer btype;

	public AssignArchive()
	{
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public List getIds()
	{
		return ids;
	}

	public void setIds(List ids)
	{
		this.ids = ids;
	}

	public List getAddIds()
	{
		return addIds;
	}

	public void setAddIds(List addIds)
	{
		this.addIds = addIds;
	}

	public List getDelIds()
	{
		return delIds;
	}

	public void setDelIds(List delIds)
	{
		this.delIds = delIds;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public Integer getBtype()
	{
		return btype;
	}

	public void setBtype(Integer btype)
	{
		this.btype = btype;
	}
}
