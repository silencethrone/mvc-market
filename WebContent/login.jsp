<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
</head>
<body>
	登录&nbsp;&nbsp;${message}
	<hr/>
	<form action="<%=request.getContextPath()%>/login_do" method="post">
		用户名：<input type="text" required="required" name="username" /><br/>
		密码：<input type="password" required="required" name="password" /><br/>
		<input type="submit" value="登录" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/register.jsp"><input type="button" value="前往注册" /></a>
	</form>
</body>
</html>