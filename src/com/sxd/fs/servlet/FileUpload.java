package com.sxd.fs.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sxd.fs.service.impl.FileServiceImpl;
import com.sxd.util.PropertiesUtil;
import com.sxd.util.StringUtil;

public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uploadPath = "C:\\Share";											//Ĭ���ϴ�·��
	@Override
	public void init() throws ServletException {										//Servlet��ʼ����ȡ�����ļ�·����properties�����ļ�
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//�õ�������WEB-INF/classes/·��
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//��ȡWEB_INF·��
		String filePath = "config/fileConfig.properties";								//Ĭ�������ļ�·������Servlet������ȡ�����ļ�·����չ�ԱȽϺ�
//		String  filePath = this.getServletConfig().getInitParameter("config_path");  	//servlet��ʼ��������ȡ�����ļ�·��
		Properties props = PropertiesUtil.getProperties(prjPath + filePath);
		if(null != props) {
			String proPath = props.getProperty("PATH");
			if(!StringUtil.isNullOrEmpty(proPath)) {
				uploadPath = proPath;
			}
		}
	}
	
	
	@Override
	public void doGet(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File(uploadPath);
		//�ж�·���Ƿ���ڣ��������򴴽���Ӧ�ļ���
		if(!file.exists()) {
			file.mkdirs();
		}
		

		int maxPostSize = 5 * 1024 *1024;												//�ļ���С������5M
//		FileRenamePolicy policy = (FileRenamePolicy)new DefaultFileRenamePolicy();      //Ĭ������������   +1 ~ +99999
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxPostSize, "UTF-8", 		
			new FileRenamePolicy() {
				//�����ڲ��������ⲿ�������ò�������Ϊfinal
				private final String id = (String) request.getSession().getAttribute("loginUser");	//��ȡ��ǰ��½�û�ID
				@Override
				public File rename(File file) {
					file = new File(file.getParent(), file.getName());
					FileServiceImpl service = new FileServiceImpl();					//�ϴ��ļ��󣬽���Ӧ���ļ���Ϣ��ӵ����ݿ�
					service.insert(file, id);
					return file;
				}
			}
		);
/*
		System.out.println("----------------------");
		
		Enumeration<?> en2 = multi.getParameterNames();
		while(en2.hasMoreElements()) {
			String key = en2.nextElement().toString();
			System.out.println(key + "=" + multi.getParameter(key));
		}
		
		ServletInputStream in = request.getInputStream();
		String line = null;
		byte[] by = new byte[8192];
		int length = -1;
		
		
		do
	    {
			length = in.readLine(by, 0, by.length);
	      if (length != -1)
	        line += new String(by, 0, length);
	    }
	    while (length == by.length);
		System.out.println(length);
		System.out.println(line);
		String query = request.getQueryString();
		
		System.out.println(query);
		
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			System.out.println(key + "=" + request.getParameter(key));
		}
		
		Enumeration<String> en1 = request.getHeaderNames();
		while(en1.hasMoreElements()) {
			String key = en1.nextElement();
			System.out.println(key + "=" + request.getHeader(key));
		}	
*/	
		response.sendRedirect("../index.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
