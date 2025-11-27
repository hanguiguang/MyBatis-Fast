// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BasePage.java

package com.ysyy.common.resut;


public class BasePage
{

	private int totalRecords;
	private boolean isMajor;
	private int offSet;
	private int size;

	public BasePage()
	{
		isMajor = false;
		size = 20;
	}

	public int getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public boolean isMajor()
	{
		return isMajor;
	}

	public void setMajor(boolean isMajor)
	{
		this.isMajor = isMajor;
	}

	public int getOffSet()
	{
		return offSet;
	}

	public void setOffSet(int offSet)
	{
		this.offSet = offSet;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}
