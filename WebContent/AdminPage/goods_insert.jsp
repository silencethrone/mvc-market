<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加商品</title>
</head>
<body>
<%
request.getAttribute("message");
%>
${message}
	<form action="<%=request.getContextPath()%>/AdminPage/insertgoods_do" method="post">
		商品名：<input type="text" required="required" name="goodsname" /><br/>
		价格：<input type="text" required="required" name="price" /><br/>
		描述：<input type="text" required="required" name="description" /><br/>
		库存：<input type="text" required="required" name="stock" /><br/>
		<input type="submit" value="确定">
	</form>
</body>
</html>