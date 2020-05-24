package com.xx.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xx.model.User;

/* 身份认证 */
public class AdminFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		
		Object o = httprequest.getSession().getAttribute("user");
		if(o == null) {
			//System.out.println("未登录");
			httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsp");
		}else {
			User u = (User) o;
			if(u.isAdmin()) {
				chain.doFilter(request, response); //是管理员放行
			}else {
				//System.out.println("无权限");
				httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsp");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

}
