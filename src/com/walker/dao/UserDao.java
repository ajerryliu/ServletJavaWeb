package com.walker.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.walker.Service.IUserService;
import com.walker.baseDao.OperateDB;
import com.walker.bean.userBean;

public class UserDao
{
   private	OperateDB operater = new OperateDB();
	/**
	 * 查看这个用户是否存在
	 * @param sql 数据库查询语句
	 * @return 结果集
	 */
	public ResultSet ifUserExit(String sql)
	{
		ResultSet result = operater.getResultByQuery(sql);
		return result;
	}
	/**
	 * 查看用户
	 * @param sql
	 * @return 用户的
	 */
	public List<userBean> userShow(String sql)
	{
		List<userBean> userList = new ArrayList<userBean>();
		ResultSet result = operater.getResultByQuery(sql);
		List<List<Object>> objectList = operater.resultSetToList(result);
		//对数据进行封装 返回userBean list
		for(List<Object> list : objectList)
		{
			userBean user = new userBean();
			user.setEmpno((String)list.get(0));
			user.setName((String) list.get(1));
			user.setJob((String)list.get(2));
			user.setMgr((String)list.get(3));
			user.setHiredate((Date) list.get(4));
			user.setSal( (BigDecimal) list.get(5));
			user.setComm( (BigDecimal) list.get(6));
			user.setDeptno( (String) list.get(7));
			userList.add(user);
		}
	   return userList;
	}
/*   OperateDB operater = new OperateDB();
	//调用baseDao 方法 并对数据进行封装
	@Override
	public void findUser(String sql)
	{
		
	}

	@Override
	public boolean userLogin(String name, String password)
	{
		String sql = "select * from emp where empno ='"+password+"' and ename='"+name+"'";
	   System.out.println(sql);
		ResultSet result	= operater.getResultByQuery(sql);
		System.out.println("-----"+result);
	   try
		{
			if(result.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return false;
	}*/
}
