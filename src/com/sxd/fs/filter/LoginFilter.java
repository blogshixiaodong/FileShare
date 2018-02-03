package com.sxd.fs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession(true);
		if(null == session.getAttribute("loginUser")) {
			res.sendRedirect("login.jsp");
		} else {
			filterchain.doFilter(req, res);
		}
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
