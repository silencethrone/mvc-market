<%@page import="com.xx.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>

<c:choose>
	<c:when test="${empty user}">
		<a href="<%=request.getContextPath()%>/login_do">登录</a> <a href="<%=request.getContextPath()%>/register.jsp">注册</a>
	</c:when>
	<c:otherwise>
		当前登录用户：${user.username}
		<a href="<%=request.getContextPath()%>/person_center.jsp" >用户中心</a>
	</c:otherwise>
</c:choose>

<hr/>
以下是商品列表
</body>
</html>