<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改商品</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/AdminPage/updatagoods_do" method="post">
		<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
		商品名：<input type="text" required="required" name="goodsname" value="<%=request.getParameter("goodsname") %>"/><br/>
		价格：<input type="text" required="required" name="price" value="<%=request.getParameter("price") %>"/><br/>
		描述：<input type="text" required="required" name="description" value="<%=request.getParameter("description") %>"/><br/>
		库存：<input type="text" required="required" name="stock" value="<%=request.getParameter("stock") %>"/><br/>
		<input type="submit" value="确定">
	</form>
</body>
</html>