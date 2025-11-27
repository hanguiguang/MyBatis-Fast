// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BasePage.java

package com.ysyy.common.resut;


import com.ysyy.common.annotation.objecttable.IgnoreField;
import jdk.nashorn.internal.objects.annotations.Getter;


public class BasePageN
{
	private int totalRecords;
	private boolean isMajor;
	private int offSet;	// 偏移量 从0 开始
	private int size;  // 每页数量‘

	// 对外使用
	private Integer pageIndex;   // 每页 的索引号
	private Integer pageNum;    //页码 从1 开始
	private Integer pageSize;   // 每页数量‘


	public BasePageN()
	{
		isMajor = false;
		size = 10;
		pageSize=10;
		pageNum=1;
		pageIndex=0;
		offSet=(pageNum-1)*pageSize;
	}

	public void reAutoOffSet()
	{
		this.offSet=(this.pageNum-1)*this.pageSize;
	}


	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if(pageNum<1) pageNum=1;
		this.pageNum = pageNum;

		this.reAutoOffSet();// 重新计算了 开始offset 方便后面直接使用
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
		this.size=pageSize;
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
