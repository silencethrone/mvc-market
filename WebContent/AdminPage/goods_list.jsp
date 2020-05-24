<%@page import="com.xx.model.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户中心</title>
</head>
<body>
<%int i=0;%>
<h3>商品列表</h3>
 <a href="<%=request.getContextPath()%>/AdminPage/goods_insert.jsp">添加</a>
 <form action="<%=request.getContextPath()%>/AdminPage/readgoods_do" method="post">
 	<input type="text" name="goodsname" />
 	<input type="submit" value="查找" />
 </form>

<table border="1" cellspacing="0px" cellpadding="10px">
		  <tr>
		  	<td>序号</td>
		    <td>商品名</td>
		    <td>描述</td>
		    <td>价格 (元)</td>
		    <td>库存</td>
		    <td>操作</td>
		  </tr>
		
	<c:forEach items="${list}" var="g">
		<tr>
			<td><%i++;out.print(i); %></td>
			<td>${g.goodsname }</td>
			<td>${g.description }</td>
			<td>${g.price }</td>
			<td>${g.stock }</td>
			<td><a href="<%=request.getContextPath()%>/AdminPage/deletegoods_do?id=${g.id }">删除</a>  
				<a href="<%=request.getContextPath() %>/AdminPage/goods_updata.jsp?id=${g.id }
				&goodsname=${g.goodsname }
				&description=${g.description }
				&price=${g.price }
				&stock=${g.stock }">
				修改</a>
			</td>
		</tr>
	</c:forEach>
</table>
${message}
${isnull}
</body>
</html>