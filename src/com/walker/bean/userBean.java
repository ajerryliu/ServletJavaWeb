package com.walker.bean;

import java.math.BigDecimal;
import java.util.Date;

public class userBean
{
	private String empno;
	private String name;
	private String job;
	private String mgr;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private String deptno;

	public String getEmpno()
	{
		return empno;
	}

	public void setEmpno(String empno)
	{
		this.empno = empno;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getJob()
	{
		return job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	public String getMgr()
	{
		return mgr;
	}

	public void setMgr(String mgr)
	{
		this.mgr = mgr;
	}

	public Date getHiredate()
	{
		return hiredate;
	}

	public void setHiredate(Date hiredate)
	{
		this.hiredate = hiredate;
	}

	public BigDecimal getSal()
	{
		return sal;
	}

	public void setSal(BigDecimal sal)
	{
		this.sal = sal;
	}

	public BigDecimal getComm()
	{
		return comm;
	}

	public void setComm(BigDecimal comm)
	{
		this.comm = comm;
	}

	public String getDeptno()
	{
		return deptno;
	}

	public void setDeptno(String deptno)
	{
		this.deptno = deptno;
	}

	
}
