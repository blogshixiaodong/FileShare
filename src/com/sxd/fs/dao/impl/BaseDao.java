package com.sxd.fs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sxd.database.JdbcUtil;
import com.sxd.database.impl.DataBase;
import com.sxd.fs.dao.IBaseDao;
import com.sxd.sql.statement.SqlStatement;
import com.sxd.sql.statement.impl.DeleteStatement;
import com.sxd.sql.statement.impl.InsertStatement;
import com.sxd.sql.statement.impl.SelectStatement;
import com.sxd.sql.statement.impl.UpdateStatement;

/**
 * @author shixiaodong
 *
 */

public abstract class BaseDao<T> implements IBaseDao<T> {
	private Connection conn = null;
	protected PreparedStatement ps = null;
	protected Map<String, Object> fieldMap = null;
	protected Map<String, Object> conditionMap = null;
	protected static DataBase db = null;
	private QueryRunner qRunner = null;
	private SqlStatement sqlStatement = null;
	
	public BaseDao() {
		db = JdbcUtil.newInstance();
		qRunner = new QueryRunner(db.getDataSource());
	}
	
	
	/**
	 * @return 获取数据库表名
	 */
	public abstract String getTableName();
	
	public abstract Class<T> getBeanClass();
	

	/**
	 * @return 获取数据库主键集合
	 */
	public abstract String[] getPrimaryKeys();
	
	
	/**
	 * @return Connection
	 */
	public Connection getConn() {
		try {
			if(null == conn || conn.isClosed()) {
				conn = db.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @return 获取是否自动提交
	 */
	public boolean isAutoCommit() {
		return true;
	}
	
	/**
	 * 顺序关闭资源  PreparedStatement, DataBase
	 */
	public void close() {
		try {	
			if(null != ps) {
				ps.close();
			}
			db.closeQuickly();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	private void rollback() {
		if(!isAutoCommit()) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//添加动作
	protected void beforeInsert() throws SQLException {
		fieldMap = new LinkedHashMap<String, Object>();
		getConn();
		conn.setAutoCommit(isAutoCommit());
	}
		
	protected void afterInsert() throws SQLException {
		if(!isAutoCommit()) {
			conn.commit();
		}
		close();
	}
	
	private  int doInsert() throws SQLException {	
		sqlStatement = new InsertStatement(getBeanClass(), getTableName());
		sqlStatement.setCondition(fieldMap.keySet());
		String sql = sqlStatement.createStatement();
		ps = conn.prepareStatement(sql);
		qRunner.fillStatement(ps, fieldMap.values().toArray());
		return ps.executeUpdate();
	}
	
	@Override
	public final boolean insert() {
		try {
			beforeInsert();
			doInsert();
			afterInsert();
		} catch (SQLException e) {	
			rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

	//删除动作
	protected void beforeDelete() throws SQLException {
		conditionMap = new LinkedHashMap<String, Object>();
		getConn();
		conn.setAutoCommit(isAutoCommit());
	}
	
	protected void afterDelete() throws SQLException {
		if(!isAutoCommit()) {
			conn.commit();
		}
		close();
	}
	
	private void doDelete() throws SQLException {
		sqlStatement = new DeleteStatement(getTableName());
		sqlStatement.setCondition(conditionMap.keySet());
		String sql = sqlStatement.createStatement();
		ps = conn.prepareStatement(sql);
		qRunner.fillStatement(ps, conditionMap.values().toArray());
		ps.executeUpdate();
	}
	
	@Override
	public final boolean delete() {
		try {
			beforeDelete();
			doDelete();
			afterDelete();
		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//更新动作
	protected void beforeUpdate() throws SQLException {
		fieldMap = new LinkedHashMap<String, Object>();
		conditionMap = new LinkedHashMap<String, Object>();
		getConn();
		conn.setAutoCommit(isAutoCommit());
	}
	
	protected void afterUpdate() throws SQLException {
		if(!isAutoCommit()) {
			conn.commit();
		}
		close();
	}
	
	private void doUpdate() throws SQLException {
		sqlStatement = new UpdateStatement(getBeanClass(), getTableName());
		sqlStatement.setField(fieldMap.keySet());
		sqlStatement.setCondition(conditionMap.keySet());
		String sql = sqlStatement.createStatement();
		
		//合并数组
		String[] updateFieldsArr = fieldMap.keySet().toArray(new String[0]);
		String[] whereFieldsArr = conditionMap.keySet().toArray(new String[0]);
		Object[] values = new Object[updateFieldsArr.length + whereFieldsArr.length];
		System.arraycopy(updateFieldsArr, 0, values, 0, updateFieldsArr.length);
		System.arraycopy(whereFieldsArr, 0, values, updateFieldsArr.length, whereFieldsArr.length);	
		ps = conn.prepareStatement(sql);
		qRunner.fillStatement(ps, values);
		ps.executeUpdate();
	}
	
	@Override
	public final boolean update() {
		try {
			beforeUpdate();
			doUpdate();
			afterUpdate();
		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected void beforeSelect(String...key) throws SQLException {
		conditionMap = new LinkedHashMap<String, Object>();
		getConn();
		conn.setAutoCommit(isAutoCommit());
	}
	
	protected void afterSelect() throws SQLException {
		if(!isAutoCommit()) {
			conn.commit();
		}
		close();
	}
	

	private List<T> doSelect(Boolean useCondition) throws SQLException {
		sqlStatement = new SelectStatement(getBeanClass(), getTableName());	
		List<T> list = null;	
		if(useCondition) {
			sqlStatement.setCondition(conditionMap.keySet());
			String sql = sqlStatement.createStatement();
			list =  qRunner.query(sql, new BeanListHandler<T>(getBeanClass()), conditionMap.values().toArray());
		} else {
			String sql = sqlStatement.createStatement();
			list =  qRunner.query(sql, new BeanListHandler<T>(getBeanClass()));
		}
 		return list;
	}
	
	@Override
	public final List<T> select(String... fields) {
		List<T> list = null;
		Boolean useCondition = fields == null ? false : (fields.length == 0 ? false : true);
		try {
			beforeSelect(fields);
			list = doSelect(useCondition);
			afterSelect();
		} catch (SQLException e) {
			rollback();
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public final ResultSet select(String sql) {
		ResultSet rs = null;
		getConn();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		return rs;
	}

}
