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
	private String uploadPath = "C:\\Share";											//默认上传路径
	@Override
	public void init() throws ServletException {										//Servlet初始化读取保存文件路径的properties配置文件
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//得到工程名WEB-INF/classes/路径
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//截取WEB_INF路径
		String filePath = "config/fileConfig.properties";								//默认配置文件路径，从Servlet参数读取配置文件路径拓展性比较好
//		String  filePath = this.getServletConfig().getInitParameter("config_path");  	//servlet初始化参数获取配置文件路径
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
		//判断路径是否存在，不存在则创建相应文件夹
		if(!file.exists()) {
			file.mkdirs();
		}
		

		int maxPostSize = 5 * 1024 *1024;												//文件大小不超过5M
//		FileRenamePolicy policy = (FileRenamePolicy)new DefaultFileRenamePolicy();      //默认重命名规则   +1 ~ +99999
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxPostSize, "UTF-8", 		
			new FileRenamePolicy() {
				//匿名内部类引用外部参数，该参数必须为final
				private final String id = (String) request.getSession().getAttribute("loginUser");	//获取当前登陆用户ID
				@Override
				public File rename(File file) {
					file = new File(file.getParent(), file.getName());
					FileServiceImpl service = new FileServiceImpl();					//上传文件后，将相应的文件信息添加到数据库
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
