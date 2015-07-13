<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
  <form action="servlet/LoginServlet" method="post" name ="loginForm">
   <input type="hidden" name="command" value="login" />
    <table>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td><input type="submit" value="登陆" onclick="return check(this)"></td>
        <td><input type="reset" value ="重置"></td>
      </tr>
    </table>
  </form>
  <a href="servlet/LoginServlet?command=lookfor">访问servlet</a>
  <h1>实现后台分页<button value="ddd" id="lookButto" onclick="buttonclick()">查询用户</button></h1>
  <script type="text/javascript" src="./js/user.js">
  </script>
</body>

</html>