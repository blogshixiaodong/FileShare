package com.sxd.database;

import java.sql.Connection;

public interface IDataBase {	
	public abstract Connection getConnection();
	public abstract void close();
	public abstract void closeQuickly();
}
