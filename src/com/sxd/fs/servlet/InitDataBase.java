package com.sxd.fs.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.sxd.database.JdbcUtil;


/***
 * 
 * @author shixiaodong
 */

////֧�ִ�WEB-INFĿ¼�¶���web.xml, config.peoperties, �Լ�META-INFĿ¼��context.xml�����õ����ݿ�
public class InitDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driverName; 
	private String url;
	private String username;
	private String password;
	
	private static String LOAD_TYPE;
	private static String DATABASE;
	private static String FILE_PATH;	//���WEB-INF·��
	
	private final static String[] loadType = {"WEBXML", "PROPERTY", "JDNI"};
	private final static String[] dbAccess = {"jdbc/SqlServer", "jdbc/MySql", "jdbc/Oracle"};
	
	
//	//���������ļ�����ȡ���ݿ����ͣ�SqlServer/MySql/Oracle�������뷽ʽ(Class.forName/JDNI)
//	static {
//		String path = "jdbc/jdbcConfig.properties";
//		Properties props = getPeoperties(path);
//		DATABASE = props.getProperty("DATABASE");
//		LOAD_TYPE = props.getProperty("LOAD_TYPE");
//		if(LOAD_TYPE.equalsIgnoreCase("PROPERTY")) {
//			FILE_PATH = props.getProperty("FILE_PATH");
//		}
//	}
//	
	@Override
	public void init() throws ServletException {
		String path = "config/jdbcConfig.properties";
		Properties props = getPeoperties(path);
		DATABASE = props.getProperty("DATABASE");
		LOAD_TYPE = props.getProperty("LOAD_TYPE");
		if(LOAD_TYPE.equalsIgnoreCase("PROPERTY")) {
			FILE_PATH = props.getProperty("FILE_PATH");
		}	
		
		if(LOAD_TYPE.equalsIgnoreCase(loadType[0])) {
			loadWebXML();
			JdbcUtil.init(driverName, url, username, password);
		} else if(LOAD_TYPE.equalsIgnoreCase(loadType[1])) {
			loadProperties(FILE_PATH);
			JdbcUtil.init(driverName, url, username, password);
		} else if(LOAD_TYPE.equalsIgnoreCase(loadType[2])) {
			int index = loadJDNI();
			JdbcUtil.init(dbAccess[index]);
		}
	}

	//��ȡWEN-INFĿ¼�µ�properties�ļ�
	private  Properties getPeoperties(String path) {
//		String prjPath = this.class.getClassLoader().getResource("/").getPath();//�õ�������WEB-INF/classes/·��
//		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));//��·���ַ�����ȡ������·��
//		InputStream in = InitDataBase.class.getClassLoader().getResourceAsStream(prjPath + path);
		String basePath = "/WEB-INF/";
		InputStream in = this.getServletContext().getResourceAsStream(basePath + path);  
		Properties props = new Properties();  
		
		try {
			props.load(in);
		} catch (IOException e) {
			//cann't load config file
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					//do nothing
				}
			}
		}
		return props;
	}
	
	//��Property�ļ��м������ݿ�������Ϣ
	private void loadProperties(String path) {
		Properties props = getPeoperties(path);
		driverName = props.getProperty("DRIVER");
		url = props.getProperty("URL");
		username = props.getProperty("USERNAME");
		password = props.getProperty("PASSWORD");
	}
	
	//��Web.xml�������ݿ�������Ϣ
	private void loadWebXML() {
		ServletContext context = this.getServletContext();
		driverName = context.getInitParameter("DRIVER");
		url = context.getInitParameter("URL");
		username = context.getInitParameter("USERNAME");
		password = context.getInitParameter("PASSWORD");
	}
	
	//META-INFĿ¼context.xml���ص����ݿ���Ϣ
	private int loadJDNI() {
		if(DATABASE.equalsIgnoreCase("sqlserver")) {
			return 0;
		} else if(DATABASE.equalsIgnoreCase("mysql")) {
			return 1;
		} else if(DATABASE.equalsIgnoreCase("oracle")) {
			return 2;
		}
		return 0;
	}	
}
