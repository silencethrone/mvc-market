package com.xx.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.service.UserService;
import com.xx.util.Base64Util;

public class RegisterServlet extends HttpServlet {
/* 
 *	 创建Cookie
 */
	void setcookie(String username,String pwd, HttpServletRequest request,HttpServletResponse response) {
		// 创建Cookie
		Cookie cookie_name = new Cookie("c_n",username);
		Cookie cookie_pwd = new Cookie("c_p",pwd);

		// 指定Cookie绑定路径
		// 最好使用根目录
		cookie_name.setPath(request.getContextPath() + "/");
		cookie_pwd.setPath(request.getContextPath() + "/");

		// 设置Cookie的有效期
		cookie_name.setMaxAge(-1); //缓存
		cookie_pwd.setMaxAge(-1);

		// 向响应中添加Cookie,保存到客户端
		response.addCookie(cookie_name);
		response.addCookie(cookie_pwd);
	}
/* 
 *	 注册
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String Epassword = "";
		int age = Integer.parseInt(request.getParameter("age"));
		String sex = request.getParameter("sex");
		
		//加密
		Epassword = Base64Util.instance.Base64Encode(password);
		
		boolean registerSuccess = UserService.instance.register(username, Epassword, age, sex);
		if (registerSuccess)
		{	//创建Cookie
			setcookie(username, Epassword, request, response);
			response.sendRedirect(request.getContextPath() + "/login_do");
			//request.setAttribute("message", "<font color='green'>注册成功，快登录吧</font>");
			//request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		else
		{
			request.setAttribute("message", "<font color='red'>注册失败，此用户名已被注册！</font>");
			request.getRequestDispatcher("register.jsp").forward(request,response);
		}
	}

}
