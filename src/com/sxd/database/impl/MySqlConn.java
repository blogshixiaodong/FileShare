package com.sxd.database.impl;


/**
 * @author shixiaodong
 *
 */

public class MySqlConn extends DataBase {

	/**
	 * 提供无参构造函数，通过JNDI获取数据源对象，使用JDBC连接池
	 */
	public MySqlConn(String JdniName) {
		super(JdniName);
	}
 	

	public MySqlConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
