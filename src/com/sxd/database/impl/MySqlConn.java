package com.sxd.database.impl;


/**
 * @author shixiaodong
 *
 */

public class MySqlConn extends DataBase {

	/**
	 * �ṩ�޲ι��캯����ͨ��JNDI��ȡ����Դ����ʹ��JDBC���ӳ�
	 */
	public MySqlConn(String JdniName) {
		super(JdniName);
	}
 	

	public MySqlConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
