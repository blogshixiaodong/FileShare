package com.sxd.database.impl;


public class SqlServerConn extends DataBase {

		
	public SqlServerConn(String JdniName) {
		super(JdniName);
	}
	
	public SqlServerConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
