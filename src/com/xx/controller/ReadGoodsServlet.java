package com.xx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.model.Goods;
import com.xx.service.GoodsService;

public class ReadGoodsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsname = request.getParameter("goodsname");
		
		List<Goods> list = GoodsService.instance.vagueRead(goodsname);
		if(list != null && !list.isEmpty()) {
			request.setAttribute("list",list);
			request.getRequestDispatcher("/AdminPage/goods_list.jsp").forward(request,response);
		}else {
			request.setAttribute("isnull","无查询记录！");
			request.getRequestDispatcher("/AdminPage/goods_list.jsp").forward(request,response);
		}
		
	}

}
