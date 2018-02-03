package com.sxd.fs.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.fs.bean.Message;
import com.sxd.fs.dao.impl.BaseDao;
import com.sxd.fs.dao.impl.MessageDaoImpl;


public class MessageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int status = 0;
		Message mess = new Message();
		String rowno = URLDecoder.decode(request.getParameter("rowno"), "UTF-8");
		String id = URLDecoder.decode(request.getParameter("id"), "UTF-8");
		Object roleObject = request.getSession().getAttribute("loginUser");
		if(null == roleObject) {
			status = 2;
		} else {
			String loginUser = roleObject.toString();
			if(loginUser.equals(id)) {
				mess.setRowno(new Integer(rowno));
				mess.setId(loginUser);
				BaseDao<Message> dao = new MessageDaoImpl(mess);
				dao.delete();
			} else {
				status = 1;
			}
		}
		/*
		 * status:
		 * 0：正常
		 * 1：删除非当前用户的留言
		 * 2：session过期，或处于未登陆状态
		 * */
		response.getWriter().println(status);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
