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
	CONFIG,     //�������ֶ�ȡ��ʽ��WEBXML, PROPERTIES
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
	
	//ͨ��Class.forName �������ݿ�����
	public static void init(String driver, String url, String userName, String password) {
		DRIVER = driver;
		URL = url;
		USERNAME = userName;
		PASSWORD = password;
		AnalysisArgs();
	}
	
	//ͨ��JDNI��ȡ����Դ
	public static void init(String jdniName) {
		JDNINAME = jdniName;
		AnalysisArgs();
	}
	
	//������ʼ�����������ݿ����ͣ����ط�ʽ��
	private static void AnalysisArgs() {
		String DB_INFO = "";
		//���ط�ʽ�ж�
		if(StringUtil.isNullOrEmpty(DRIVER)) {
			loadType = LOAD_TYPE.JDNI;
			DB_INFO = JDNINAME;		
		} else {
			//������������Ϣ�м���
			loadType = LOAD_TYPE.CONFIG;
			DB_INFO = DRIVER;	
		}
		/**
		 *  ʹ�����ݿ��ж�    
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
	
	//ʵ�������ݿ�
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
