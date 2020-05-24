package com.xx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.service.GoodsService;

public class InsertGoodsServlet extends HttpServlet {
/* 
 ** 添加商品
 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsname = request.getParameter("goodsname");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		boolean insertSuccess = GoodsService.instance.insertgoods(goodsname, description, price, stock);
		
		if(insertSuccess) {
			request.setAttribute("message", "<font color='green'>添加成功</font>");
			request.getRequestDispatcher("/AdminPage/goods_list").forward(request,response);
		}else {
			request.setAttribute("message", "<font color='red'>添加失败，商品名重复</font>");
			request.getRequestDispatcher("/AdminPage/goods_insert").forward(request,response);
		}
	}

}
