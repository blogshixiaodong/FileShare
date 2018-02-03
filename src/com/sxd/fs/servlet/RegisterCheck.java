package com.sxd.fs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.fs.bean.User;
import com.sxd.fs.service.impl.UserServiceImpl;

public class RegisterCheck extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String passwords = request.getParameter("passwords");
		if(null == account || null == password || null == passwords) {
			return;
		} else if(account.equals("") || password.equals("") || passwords.equals("")) {
			return;
		} else if(!passwords.equals(password)) {
			return;
		}
		UserServiceImpl service = new UserServiceImpl();
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		service.register(user);
		request.getSession().setAttribute("loginUser", account);
		request.getRequestDispatcher("../index.jsp");
		/*
		 * TODO: 没有反馈数据到前端
		 * */
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
