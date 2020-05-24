package com.xx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.service.GoodsService;

public class UpdataGoodsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String goodsname = request.getParameter("goodsname");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		boolean updataSuccess = GoodsService.instance.updatagoods(id, goodsname, description, price, stock);
		
		if(updataSuccess) {
			request.setAttribute("message", "<font color='green'>修改成功</font>");
			request.getRequestDispatcher("/AdminPage/goods_list").forward(request,response);
		}else {
			request.setAttribute("message", "<font color='red'>修改失败</font>");
			request.getRequestDispatcher("/AdminPage/goods_list").forward(request,response);
		}
	}

}
