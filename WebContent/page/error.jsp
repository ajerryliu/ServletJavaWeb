<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误</title>
</head>
<body>
<div   align="center"> 
  <%
  	Object error = request.getAttribute("errorInfo");
  	if (error != null)
  	{
  	out.println(error.toString());
  	} else
  	{
  	out.println("无错误信息");
  	}
  	out.println("用户名："+request.getParameter("username"));
  	out.println("密码："+request.getParameter("password"));
   
  %>
  <a href="../index.jsp">返回登陆页面</a>
  </div>
  <br>
  
</body>
</html>