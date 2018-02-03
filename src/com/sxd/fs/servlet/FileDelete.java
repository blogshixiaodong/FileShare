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
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//�õ�������WEB-INF/classes/·��
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//��ȡWEB_INF·��
		String filePath = "config/fileConfig.properties";								//Ĭ�������ļ�·������Servlet������ȡ�����ļ�·����չ�ԱȽϺ�
//		String  filePath = this.getServletConfig().getInitParameter("config_path");  	//servlet��ʼ��������ȡ�����ļ�·��
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
		//�ܾ� ɾ������Ա�ϴ����ļ� �Լ� ���ϴ��ļ����û�ɾ���ļ�
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
		
		//request.getRequestDispatcher("../index.jsp").forward(request, response);   JS CSS ���ᱻ����
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
