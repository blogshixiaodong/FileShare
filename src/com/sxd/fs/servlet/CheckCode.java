package com.sxd.fs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxd.util.CheckCodeUtil;

/**
 * @filename  CheckCode.java
 * @author    ShiXiaodong
 * @date      2017��12��30�� ����9:12:12
 * @describe  TODO
 * @version   v1.0
 */

public class CheckCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ��������
		response.setContentType("image/jpeg");
		//���������������ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		//����ʱ��
		response.setDateHeader("Expires", 0);
		
		//��ͼ��������ͻ���
		ServletOutputStream sos = response.getOutputStream();	
		CheckCodeUtil ccu = new CheckCodeUtil(85, 39, 4);
		ccu.drawImage();
		byte[] buffer = ccu.getImageBuffer();
		response.setContentLength(buffer.length);
		sos.write(buffer);
		sos.close();
		
		request.getSession().setAttribute("checkCode", ccu.getCheckCode());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
