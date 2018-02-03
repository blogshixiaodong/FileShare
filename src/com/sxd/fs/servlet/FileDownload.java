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
		String prjPath = this.getClass().getClassLoader().getResource("/").getPath();	//�õ�������WEB-INF/classes/·��
		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));						//��ȡWEB_INF·��
		String filePath = "config/fileConfig.properties";								//Ĭ�������ļ�·��
		Properties props = PropertiesUtil.getProperties(prjPath + filePath);
		downloadPath = props.getProperty("PATH");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");																//�����������
		response.setCharacterEncoding("UTF-8");																//���ķ������������ݵ�Ĭ�ϱ���
		response.setHeader("Content-Type","text/html;charset=UTF-8");										//֪ͨ�ͻ��˽��뷽ʽ
		
		//ǰ��URLEncoder��Servlet��URLDecoder  
		String fileName = new String(request.getParameter("fileName").getBytes("ISO-8859-1"), "UTF-8");  	 //URL��������GET���󣩴�������
		String downloadFileName =  URLEncoder.encode(fileName, "UTF-8");;
		
		//TODO:��ҪУ�����ݿ����Ƿ���ڸ�����¼����������������
		int index = downloadFileName.indexOf("_");
		if(-1 != index) {
			downloadFileName = downloadFileName.substring(index + 1);
		}
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" +  downloadFileName);
			ServletOutputStream out = response.getOutputStream();
			//����ʹ��returnFile()�������ر��ص��ļ���ʹ��returnURL()�������������ϵ��ļ�  
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
