package com.sxd.fs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sxd.fs.bean.Message;
import com.sxd.fs.service.impl.MessageServiceImpl;


public class MessageList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=utf-8");
		MessageServiceImpl service = new MessageServiceImpl();
		List<Message> list = service.select(new String[] {});  	//ÎÞÌõ¼þ
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		for(Message mess : list.toArray(new Message[0])) {
			jsonObj.put("rowno", mess.getRowno());
			jsonObj.put("id", mess.getId());
			jsonObj.put("time", mess.getTime().getTime());
			jsonObj.put("response",  mess.getResponse());
			
			jsonArr.add(jsonObj);
		}
		response.getWriter().println(jsonArr.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
