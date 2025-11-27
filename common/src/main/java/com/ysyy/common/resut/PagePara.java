// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BPage.java

package com.ysyy.common.resut;


import java.util.List;

public class PagePara
{
	 Class<?> clazz=String.class;
	String sqlSelect;
	String sqlFrom;
	String sqlWhere;
	String sqlGroup;
	String sqlLimit;


	//	String sqlStr;
//String[] whereStrs;
	Integer pageNum=1;
	Integer pageSize=10;
	Boolean needTotal=true;


	public PagePara()
	{
	}

	public void reAutoLimitSQL()
	{
		int offSet=(this.pageNum-1)*this.pageSize;

		this.sqlLimit=offSet+" "+this.pageSize;
	}



	public  Class<?> getClazz()
	{
		return clazz;
	}

	public void setClazz(Class<?> clazz)
	{
		this.clazz = clazz;
	}


	public String getSqlSelect() {
		return sqlSelect;
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlFrom() {
		return sqlFrom;
	}

	public void setSqlFrom(String sqlFrom) {
		this.sqlFrom = sqlFrom;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getSqlGroup() {
		return sqlGroup;
	}

	public void setSqlGroup(String sqlGroup) {
		this.sqlGroup = sqlGroup;
	}

	public String getSqlLimit() {
		return sqlLimit;
	}

	public void setSqlLimit(String sqlLimit) {
		this.sqlLimit = sqlLimit;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if(pageNum==null||pageNum<1) pageNum=1;
		this.pageNum = pageNum;

		reAutoLimitSQL();
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize==null||pageSize<1) pageSize=1;
		this.pageSize = pageSize;

		reAutoLimitSQL();
	}

	public Boolean getNeedTotal() {
		return needTotal;
	}

	public void setNeedTotal(Boolean needTotal) {
		this.needTotal = needTotal;
	}



}
