package com.sxd.fs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.fs.service.impl.UserServiceImpl;
import com.sxd.util.CookieUtil;

public class LoginCheck extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserServiceImpl service = new UserServiceImpl();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String checkcode = request.getParameter("checkCode");
		PrintWriter out = response.getWriter();
		//验证码错误
		System.out.println(request.getSession().getAttribute("checkCode"));
		Object checkObj = request.getSession().getAttribute("checkCode");
		//Check Code未生成CheckCode异常
		if(null == checkObj || !checkObj.toString().equalsIgnoreCase(checkcode)) {
			out.print("-1");
			//request.getRequestDispatcher("../login.jsp").forward(request, response);
			return;
		}
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		//账号或密码错误
		if(!service.loginCheck(account, password)) {
			out.print("-2");
			return;
		}
		
		//前台未开放选项，默认记住账号密码
		boolean hasCookie = true; //request.getParameterValues("isRemember") == null ? false : true; 
		if(hasCookie) {
			Cookie accountCookie = CookieUtil.createCookie("FileShare.account", account, 24 * 3600, "/");
			Cookie passwordCookie = CookieUtil.createCookie("FileShare.password", password, 24 * 3600, "/");
			response.addCookie(accountCookie);
			response.addCookie(passwordCookie);
		}
		
		
		//保存登陆状态
		String loginUser = (String)request.getSession().getAttribute("loginUser");
		if(null == loginUser || !loginUser.equals(account)) {
			request.getSession().setAttribute("loginUser", account);
			
			//登陆日志
		}
		out.write(account);
		
		
		String str1 = "sxd";
		String str2 = "SXD";
		if(str1.equalsIgnoreCase(str2)) {
		
		}
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
