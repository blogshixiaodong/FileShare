package com.sxd.fs.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.fs.service.impl.FileServiceImpl;
import com.sxd.util.PropertiesUtil;
import com.sxd.util.StringUtil;


public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String downloadPath = "C:\\Share";											//default path
	
	@Override
	public void init() throws ServletException {
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//得到工程名WEB-INF/classes/路径
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//截取WEB_INF路径
		String filePath = "config/fileConfig.properties";								//默认配置文件路径，从Servlet参数读取配置文件路径拓展性比较好
//		String  filePath = this.getServletConfig().getInitParameter("config_path");  	//servlet初始化参数获取配置文件路径
		Properties props = PropertiesUtil.getProperties(prjPath + filePath);
		if(null != props) {
			String proPath = props.getProperty("PATH");
			if(!StringUtil.isNullOrEmpty(proPath)) {
				downloadPath = proPath;
			}
		}
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
//		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
//		response.setHeader("Pragma","no-cache"); //HTTP 1.0 
//		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		//int fileId = Integer.parseInt(new String(request.getParameter("fileId").getBytes("ISO-8859-1"), "UTF-8"));
		int fileId = Integer.parseInt(request.getParameter("fileId").toString());
		String loginUser = request.getSession().getAttribute("loginUser").toString();
		//拒绝 删除管理员上传的文件 以及 非上传文件的用户删除文件
		FileServiceImpl service = new FileServiceImpl();
		
		if(service.isFileAuthor(loginUser, fileId)) {
			service.delete(downloadPath, fileId);
		}
//		if(-1 != fileName.indexOf("_") && fileName.substring(0, fileName.indexOf("_")).equals(request.getSession().getAttribute("loginRole"))) {
//			FileUtil.deleteFile(downloadPath, fileName);
//		}
//		PrintWriter pw = response.getWriter();
//		pw.print("sss");
		response.sendRedirect("../index.jsp");
		
		//request.getRequestDispatcher("../index.jsp").forward(request, response);   JS CSS 不会被加载
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
