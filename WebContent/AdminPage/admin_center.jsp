<%@page import="com.xx.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员中心</title>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");
%>
管理员后台
<hr/>
当前登录用户：<%=user.getUsername() %>&nbsp;&nbsp;(id:<%=user.getId() %>)<br/>
年龄：<%=user.getAge() %><br/>
性别：<%=user.getSex() %><br/>
余额：<%=user.getBalance() %><br/>
个人介绍：<%=user.getIntroduction()%><br/>
<a href="<%=request.getContextPath()%>/index.jsp">返回首页</a><hr/>
<a href="<%=request.getContextPath()%>/logout_do">退出登录</a>
</body>
</html>