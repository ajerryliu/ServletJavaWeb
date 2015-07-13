<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%=request.getAttribute("userList")%>

  <table>
    <tr>
      <td>用户ID</td>
      <td>用户名</td>
      <td>工作</td>
      <td>上级主管</td>
      <td>雇佣日期</td>
      <td>工资</td>
      <td>提成</td>
      <td>部门</td>
    </tr>
    <c:forEach items="${userList}" var="tmp">
      <tr>
        <td>${tmp.empno}</td>
        <td>${tmp.name}</td>
        <td>${tmp.job}</td>
        <td>${tmp.mgr}</td>
        <td>${tmp.hiredate}</td>
        <td>${tmp.sal}</td>
        <td>${tmp.comm}</td>
        <td>${tmp.deptno}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>