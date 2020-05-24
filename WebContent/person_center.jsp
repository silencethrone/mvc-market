<%@page import="com.xx.model.User"%>
<%@page import="com.xx.model.Files"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
	<script type="text/javascript">
		window.onload = function ()
		{
			var oInp = document.getElementById('Info');
			if(oInp.value == 'null'){
				oInp.value = '';
				oInp.placeholder = '暂无简介';
			}
		};
		
	</script>
</head>
<body>
	<%
		String src = null;
		User user = (User)session.getAttribute("user");
		Files file = (Files)session.getAttribute("file");
		if(file == null){
			src = "fileupload/Default.jpg";
		}else{
			src = "fileupload/"+file.getFilename();
		}
	%>
	普通用户后台
	<hr/>
	当前登录用户：<%=user.getUsername() %>&nbsp;&nbsp;(id:<%=user.getId() %>)<br/>
	用户头像：<img src="<%=src %>" class="round_icon" alt="用户头像">
	<form action="<%=request.getContextPath()%>/UploadServlet" enctype="multipart/form-data" method="post">
		<input type="file" name="file" required="required"/>
		<input type="submit" value="提交" /> &nbsp;
		<input type="reset" value="重置" />
	</form>
	年龄：<%=user.getAge() %><br/>
	性别：<%=user.getSex() %><br/>
	余额：<%=user.getBalance() %><br/>
	个人介绍：
	<form action="<%=request.getContextPath()%>/UploadServlet" method="get">
		<input type="text" name="personInfo" value="<%=user.getIntroduction()%>" >
		<input type="submit" value="提交" /> &nbsp;
	</form>
	<a href="<%=request.getContextPath()%>/index.jsp">返回首页</a><hr/>
	<a href="<%=request.getContextPath()%>/logout_do">退出登录</a>
</body>
</html>