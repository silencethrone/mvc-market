package com.xx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销毁Session
		HttpSession session = request.getSession();
		session.invalidate();
		
		//销毁Cookie
		Cookie[] cookies=request.getCookies();

		for(Cookie cookie: cookies)
		{
		    cookie.setMaxAge(0);
		    cookie.setPath(request.getContextPath() + "/");  //路径一定要写上，不然销毁不了
		    response.addCookie(cookie);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

}
