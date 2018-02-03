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

////支持从WEB-INF目录下读读web.xml, config.peoperties, 以及META-INF目录下context.xml中配置的数据库
public class InitDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driverName; 
	private String url;
	private String username;
	private String password;
	
	private static String LOAD_TYPE;
	private static String DATABASE;
	private static String FILE_PATH;	//相对WEB-INF路径
	
	private final static String[] loadType = {"WEBXML", "PROPERTY", "JDNI"};
	private final static String[] dbAccess = {"jdbc/SqlServer", "jdbc/MySql", "jdbc/Oracle"};
	
	
//	//加载配置文件，读取数据库类型（SqlServer/MySql/Oracle），载入方式(Class.forName/JDNI)
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

	//获取WEN-INF目录下的properties文件
	private  Properties getPeoperties(String path) {
//		String prjPath = this.class.getClassLoader().getResource("/").getPath();//得到工程名WEB-INF/classes/路径
//		prjPath = prjPath.substring(1, prjPath.indexOf("classes"));//从路径字符串中取出工程路径
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
	
	//从Property文件中加载数据库配置信息
	private void loadProperties(String path) {
		Properties props = getPeoperties(path);
		driverName = props.getProperty("DRIVER");
		url = props.getProperty("URL");
		username = props.getProperty("USERNAME");
		password = props.getProperty("PASSWORD");
	}
	
	//从Web.xml加载数据库配置信息
	private void loadWebXML() {
		ServletContext context = this.getServletContext();
		driverName = context.getInitParameter("DRIVER");
		url = context.getInitParameter("URL");
		username = context.getInitParameter("USERNAME");
		password = context.getInitParameter("PASSWORD");
	}
	
	//META-INF目录context.xml加载的数据库信息
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
