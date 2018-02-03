package com.sxd.fs.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sxd.fs.bean.Message;
import com.sxd.fs.service.impl.MessageServiceImpl;


public class MessageInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		Message mess = new Message();
		Object roleObject = request.getSession().getAttribute("loginUser");
		if(null == roleObject) {
			//Session过期，或出现Bug导致未登录状态进入留言板
			return;
		}
		String id = roleObject.toString();
		String responseString = URLDecoder.decode(request.getParameter("response"), "UTF-8");
		mess.setResponse(responseString);
		mess.setId(id);
		mess.setTime(new Date());

		MessageServiceImpl service = new MessageServiceImpl();
		int rowno = service.insert(mess);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", true);
		jsonObj.put("rowno", rowno);
		JSONArray jsonArr = new JSONArray();
		jsonArr.add(jsonObj);
		response.getWriter().println(jsonArr.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
