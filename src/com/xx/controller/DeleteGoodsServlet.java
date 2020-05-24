package com.xx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.service.GoodsService;

public class DeleteGoodsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		boolean deleteSuccess = GoodsService.instance.deletegoods(id);
		if(deleteSuccess) {
			request.setAttribute("message", "<font color='green'>删除成功</font>");
			request.getRequestDispatcher("/AdminPage/goods_list").forward(request,response);
		}else {
			request.setAttribute("message", "<font color='red'>删除失败</font>");
			request.getRequestDispatcher("/AdminPage/goods_list").forward(request,response);
		}
		
	}
}
