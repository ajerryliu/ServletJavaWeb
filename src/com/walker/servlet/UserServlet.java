package com.walker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.walker.ServiceImpl.UserServiceImpl;
import com.walker.bean.userBean;


/**
 * 控制层
 * @author lby walker
 *
 */
public class UserServlet extends HttpServlet
{
	
	private UserServiceImpl userImpl = new UserServiceImpl();
	private static final long serialVersionUID = 368651862026009279L;


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException
	{
		//设置字符编码
		resp.setContentType("text/html;charset=utf-8");
    
		String commond = req.getParameter("command");
		if(commond.equals("lookfor"))
		{
			System.out.println("查找用户");
			userShow(req,resp);
		}
		if(commond.equals("login"))
		{
			String name = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("用户登录");
			login(name, password, req, resp);
		}
	}
	

	private void login(String name, String password,HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		
		System.out.println("用户名：" + name);
		System.out.println("密码：" + password);
		String URL ="";
		//添加错误信息
		if(name == null || name.trim().equals(""))
		{
			req.setAttribute("errorInfo", "用户名输入错误");
			URL = "/page/error.jsp";
		}
		else if(password == null || password.trim().equals(""))
		{
			req.setAttribute("errorInfo", "密码输入错误");
			URL = "/page/error.jsp";
		}
		
		//正确则跳到正确界面  错误则跳到错误界面
		//如果以重定向的方式进入页面 无法获取表单数据 总共有两次请求和响应
		//请求转发
		boolean ifRight = userImpl.userLogin(name, password);
		if(ifRight)
		{
			//重定向
			//resp.sendRedirect(req.getContextPath()+"/page/success.jsp");
			//能够使用其他应用的地址
			//resp.sendRedirect("http://www.baidu.com");
		   //什么时候该加上下文路径?
			URL = "/page/success.jsp";
		}
		else
		{
			//重定向
			//resp.sendRedirect(req.getContextPath()+"/page/error.jsp");
			URL = "/page/error.jsp";
			
		}
		
		//获取请求转发对象
		RequestDispatcher dispatcher = req.getRequestDispatcher(URL);
		System.out.println("context      "+req.getContextPath());
		dispatcher.forward(req, resp);
		
	}
	
	//查询所有的用户 并在前台页面上显示出来
	private void userShow(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		String URL = "/page/userResult.jsp";
		List<userBean> userBean = userImpl.userShow();
		req.setAttribute("userList", userBean);
		RequestDispatcher dispatcher = req.getRequestDispatcher(URL);
		dispatcher.forward(req, resp);
	}
	

}
