package com.sxd.fs.dao;

import java.sql.ResultSet;
import java.util.List;

public interface IBaseDao<T> {
	public abstract boolean insert();
	public abstract boolean delete();
	public abstract boolean update();
	public abstract List<T> select(String... fields);
	public abstract ResultSet select(String sql);
}
