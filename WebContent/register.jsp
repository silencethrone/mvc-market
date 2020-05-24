<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name='username']").blur(verifyusername);
	});
	function verifyusername(){
		
		$.ajax({
			url:encodeURI("${pageContext.request.contextPath}/ajaxregister_do"),
			type:"post",
			data:{
				username:$("input[name='username']").val()
			},
			dataType:"json",
			success:function(message){
				if(message.isSuccess){
					$("#message").html("<font color='green'>用户名可用</font>");
				}else{
					$("#message").html("<font color='red'>用户名重复</font>");
				}
			}
		});
	}
</script>
</head>
<body>
	注册&nbsp;&nbsp;${message}
	<hr/>
	<form action="<%=request.getContextPath()%>/register_do" method="post">
		用户名：<input type="text" required="required" name="username" /><div id="message"></div><br/>
		密码：<input type="password" required="required" name="password" /><br/>
		年龄：<input type="text" required="required" name="age" /><br/>
		性别：<input type="radio" required="required" name="sex" value="男" checked="checked">男
			 <input type="radio" required="required" name="sex" value="女" >女<br/>
		<input type="submit" value="注册" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/login_do"><input type="button" value="我要登录" /></a>
	</form>
</body>
</html>