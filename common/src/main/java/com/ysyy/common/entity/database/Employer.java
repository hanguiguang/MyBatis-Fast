// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Employer.java

package com.ysyy.common.entity.database;

import java.io.Serializable;

// Referenced classes of package com.ysyy.common.entity.database:
//			User

public class Employer
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String registerMobiles;
	private String name;
	private Integer status;
	private User user;

	public Employer()
	{
	}

	public Employer(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getRegisterMobiles()
	{
		return registerMobiles;
	}

	public void setRegisterMobiles(String registerMobiles)
	{
		this.registerMobiles = registerMobiles;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
