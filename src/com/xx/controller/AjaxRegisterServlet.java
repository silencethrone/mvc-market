package com.xx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.service.UserService;

@WebServlet("/ajaxregister_do")
public class AjaxRegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean isExist = UserService.instance.isExist(username);
		response.getWriter().append("{\"isSuccess\":"+isExist+"}"); // 向客户端（前端）传递json数据
	}

}
