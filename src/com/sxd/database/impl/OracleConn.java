package com.sxd.database.impl;



public class OracleConn extends DataBase {

	public OracleConn(String JdniName) {
		super(JdniName);
	}
	
	public OracleConn(String driverName, String url, String userName, String password) {
		super(driverName, url, userName, password);
	}
}
