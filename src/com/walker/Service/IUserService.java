package com.walker.Service;

import java.util.List;

import com.walker.bean.userBean;

/**
 * user的业务逻辑层
 * @author lby walker
 * @since 2015.7.5
 */
public interface IUserService
{
	/**
	 * 用户登录
	 * @param name 用户名 
	 * @param password 密码
	 * @return 用户是否存在
	 */
	public boolean userLogin(String name ,String password);
	
	/**
	 * 用户展示
	 * @return 用户集合
	 */
	public List<userBean> userShow();
}
