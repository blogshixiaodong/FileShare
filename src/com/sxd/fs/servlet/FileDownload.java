package com.sxd.fs.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.ServletUtils;
import com.sxd.util.PropertiesUtil;

public class FileDownload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String downloadPath = "F:\\Share";											//default path
	
	@Override
	public void init() throws ServletException {
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//得到工程名WEB-INF/classes/路径
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//截取WEB_INF路径
		String filePath = "config/fileConfig.properties";								//默认配置文件路径
		Properties props = PropertiesUtil.getProperties(prjPath + filePath);
		downloadPath = props.getProperty("PATH");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");																//设置请求编码
		response.setCharacterEncoding("UTF-8");																//更改服务器发送数据的默认编码
		response.setHeader("Content-Type","text/html;charset=UTF-8");										//通知客户端解码方式
		
		//前端URLEncoder，Servlet中URLDecoder  
		String fileName = new String(request.getParameter("fileName").getBytes("ISO-8859-1"), "UTF-8");  	 //URL（超链接GET请求）传递乱码
		String downloadFileName =  URLEncoder.encode(fileName, "UTF-8");;
		
		//TODO:需要校验数据库中是否存在该条记录，存在则允许下载
		int index = downloadFileName.indexOf("_");
		if(-1 != index) {
			downloadFileName = downloadFileName.substring(index + 1);
		}
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" +  downloadFileName);
			ServletOutputStream out = response.getOutputStream();
			//其中使用returnFile()可以下载本地的文件，使用returnURL()可以下载网络上的文件  
			ServletUtils.returnFile(downloadPath + "\\" + fileName, out);
		} catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	

}
