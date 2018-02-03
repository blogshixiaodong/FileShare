package com.sxd.database;

import com.sxd.database.impl.DataBase;
import com.sxd.database.impl.MySqlConn;
import com.sxd.database.impl.OracleConn;
import com.sxd.database.impl.SqlServerConn;
import com.sxd.util.StringUtil;


enum DB_TYPE {
	MYSQL,
	SQLSERVER,
	ORACLE
}

enum LOAD_TYPE {
	CONFIG,     //包含两种读取方式：WEBXML, PROPERTIES
	JDNI
}


public class JdbcUtil {
	
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	private static String JDNINAME;
	
	private static DB_TYPE dbType;
	private static LOAD_TYPE loadType;
	
	//通过Class.forName 加载数据库驱动
	public static void init(String driver, String url, String userName, String password) {
		DRIVER = driver;
		URL = url;
		USERNAME = userName;
		PASSWORD = password;
		AnalysisArgs();
	}
	
	//通过JDNI获取数据源
	public static void init(String jdniName) {
		JDNINAME = jdniName;
		AnalysisArgs();
	}
	
	//分析初始化参数（数据库类型，加载方式）
	private static void AnalysisArgs() {
		String DB_INFO = "";
		//加载方式判断
		if(StringUtil.isNullOrEmpty(DRIVER)) {
			loadType = LOAD_TYPE.JDNI;
			DB_INFO = JDNINAME;		
		} else {
			//驱动由配置信息中加载
			loadType = LOAD_TYPE.CONFIG;
			DB_INFO = DRIVER;	
		}
		/**
		 *  使用数据库判断    
		 *  	Config Driver: com.mysql.jdbc.Driver
		 *  	JDNI:          jdbc.MySql
		 */
		if(DB_INFO.toLowerCase().indexOf("sqlserver") != -1) {
			dbType = DB_TYPE.SQLSERVER;
		} else if(DB_INFO.toLowerCase().indexOf("mysql") != -1) {
			dbType = DB_TYPE.MYSQL;
		} else if(DB_INFO.toLowerCase().indexOf("oracle") != -1) {
			dbType = DB_TYPE.ORACLE;
		}
	}
	
	//实例化数据库
	public static DataBase newInstance() {
		if(dbType == DB_TYPE.SQLSERVER) {
			if(loadType == LOAD_TYPE.CONFIG) {
				return new SqlServerConn(DRIVER, URL, USERNAME, PASSWORD);
			} else if(loadType.equals(LOAD_TYPE.JDNI)) {
				return new SqlServerConn(JDNINAME);
			}	
		} else if(dbType == DB_TYPE.MYSQL) {
			if(loadType == LOAD_TYPE.CONFIG) {
				return new MySqlConn(DRIVER, URL, USERNAME, PASSWORD);
			} else if(loadType.equals(LOAD_TYPE.JDNI)) {
				return new MySqlConn(JDNINAME);
			}
		} else if(dbType == DB_TYPE.ORACLE) {
			if(loadType == LOAD_TYPE.CONFIG) {
				return new OracleConn(DRIVER, URL, USERNAME, PASSWORD);
			} else if(loadType.equals(LOAD_TYPE.JDNI)) {
				return new OracleConn(JDNINAME);
			}
		}
		return null;
	}
	
}
