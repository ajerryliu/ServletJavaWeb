package com.walker.ServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.walker.Service.IUserService;
import com.walker.bean.userBean;
import com.walker.dao.UserDao;

public class UserServiceImpl implements IUserService
{
	UserDao userDao = new UserDao();
	@Override
	public boolean userLogin(String name, String password)
	{
		String sql = "select * from emp where empno ='"+password+"' and ename='"+name+"'";
	   System.out.println(sql);
		ResultSet result	= userDao.ifUserExit(sql);
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
	}
	
	@Override
	public List<userBean> userShow()
	{
		String sql = "select * from emp";
		return userDao.userShow(sql);
	}
}
