package com.sxd.fs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sxd.fs.bean.File;
import com.sxd.fs.service.impl.FileServiceImpl;

public class FileList extends HttpServlet {
	private static final long serialVersionUID = -8669308572478685907L;

	//前端Ajax请求文件列表
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/javascript; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		FileServiceImpl service = new FileServiceImpl(new File());
		List<File> list = service.select(new String[] {});
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		for(int i = 0; i < list.size(); i++) {
			jObject.put("fileId", list.get(i).getFileId());
			jObject.put("fileName", list.get(i).getFileName());
			jObject.put("uploadAuthor", list.get(i).getUploadAuthor());
			jObject.put("fileType", list.get(i).getFileType());
			jObject.put("uploadTime", list.get(i).getUploadTime());
			jArray.add(jObject);
		}
//		String[] lists = FileUtil.getFileList(filePath);
//		fileList = Arrays.toString(lists);
		//response.setHeader("Content-Length",fileList.length()+"");					//jsp页面获取数据异常，字符串被截断
		out.println(jArray.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
