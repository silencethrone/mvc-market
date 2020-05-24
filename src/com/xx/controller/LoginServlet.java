package com.xx.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.model.User;
import com.xx.model.Files;
import com.xx.service.FileService;
import com.xx.service.UserService;
import com.xx.util.Base64Util;

public class LoginServlet extends HttpServlet {
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
		cookie_name.setMaxAge(60 * 60 * 24 * 7); // 7d
		cookie_pwd.setMaxAge(60 * 60 * 24 * 7);

		// 向响应中添加Cookie,保存到客户端
		response.addCookie(cookie_name);
		response.addCookie(cookie_pwd);
	}
	
/* 
 *	 获取Cookie
 */
	void getcookie(Cookie[] cookies,HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		{	//初始化
			String username = "";
			String password = "";
			//遍历cookie，使用用户名和密码自动登录
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("c_n")) {
					username = cookie.getValue();
				}
				if (cookie.getName().equals("c_p")) {
					password = cookie.getValue();
				}
			}
			
			User loginSuccess = UserService.instance.login(username, password);
			Files fileservice = FileService.instance.FileURL(username);
			
			request.getSession().setAttribute("user", loginSuccess);//用户信息写入Session
			
			if (fileservice != null)
			{ 	//用户设定了头像
				request.getSession().setAttribute("file", fileservice);//用户头像信息
			}
			else
			{ 	//用户未设定头像
				request.getSession().setAttribute("file", null);
			}
			request.getRequestDispatcher("person_center.jsp").forward(request, response);
		}
		catch (Exception e)
		{
			handleException(request, response);
		}
	}
/* 
 *	 异常处理方法
 */	
	void handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	
/* 
 *	doGet方法
 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		{
			Cookie[] cookies = request.getCookies();
			// 获取客户端中的Cookie
			if (cookies.length > 1)
			{
				getcookie(cookies, request, response);
				//System.out.println("使用了getCookie方法");
			}
			else
			{	//没有Cookie则重定向到login.jsp页面
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} 
		}
		catch (Exception e)
		{
			// 空指针异常处理，一般时因为用户将cookie删除了所导致的
			//System.out.println("捕获了异常");
			handleException(request, response);
		}
	}
	
/* 
 * doPost方法
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try
		{
			//表单获得的用户名和密码
			String username = request.getParameter("username");
			//加密
			String Epassword = Base64Util.instance.Base64Encode(request.getParameter("password"));
			
			User loginInfo = UserService.instance.login(username, Epassword);
			Files fileservice = FileService.instance.FileURL(username);
			
			if (loginInfo != null)
			{
				if (loginInfo.isAdmin())
				{ //管理员信息保存到session中
					request.getSession().setAttribute("user", loginInfo);
					response.sendRedirect(request.getContextPath() + "/AdminPage/goods_list");
				}
				else
				{
					setcookie(username, Epassword, request, response);//将用户信息写入Cookie中（不安全）
					request.getSession().setAttribute("user", loginInfo);//用户信息
					if (fileservice != null)
					{	//用户设定了头像
						request.getSession().setAttribute("file", fileservice);//用户头像信息
					}
					else
					{	//用户未设定头像
						request.getSession().setAttribute("file", null);
					}
					request.getRequestDispatcher("person_center.jsp").forward(request, response);
				}
			}
			else
			{	//登录失败
				request.setAttribute("message", "<font color='red'>登录失败，用户名或密码错误！</font>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} 
		}
		catch (Exception e)
		{
			handleException(request, response);
		}
	}

}
