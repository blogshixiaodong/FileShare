package com.sxd.fs.dao.impl;

import java.sql.SQLException;

import java.util.Map;

import com.sxd.bean.BeanUtil;
import com.sxd.fs.bean.User;

public class UserDaoImpl extends BaseDao<User> {
	private User user = null;
		
	public UserDaoImpl(User user) {
		this.user = user;
	}
	
	@Override
	public String getTableName() {
		return "fs_user";
	}

	@Override
	public String[] getPrimaryKeys() {
		return new String[] {"account"};
	}
	
	@Override
	public Class<User> getBeanClass() {
		return User.class;
	}

	@Override
	protected void beforeInsert() throws SQLException {
		if(null == this.user) {
			//User对象为空
			throw new SQLException("User 对象为空");
		}
		super.beforeInsert();
		fieldMap.put("account", user.getAccount());
		fieldMap.put("password", user.getPassword());
		fieldMap.put("name", user.getName());
		fieldMap.put("age", user.getAge());
		fieldMap.put("sex", user.getSex());
		fieldMap.put("tel", user.getTel());
		fieldMap.put("address", user.getAddress());
	}

	@Override
	protected void beforeDelete() throws SQLException {
		if(null == this.user) {
			//User对象为空
			throw new SQLException("User 对象为空");
		}
		super.beforeDelete();
		//根据主键删除记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(user, key));
		}
	}

	@Override
	protected void beforeUpdate() throws SQLException {
		if(null == this.user) {
			throw new SQLException("User 对象为空");
		}
		super.beforeUpdate();
		fieldMap.put("password", user.getPassword());
		fieldMap.put("name", user.getName());
		fieldMap.put("age", user.getAge());
		fieldMap.put("sex", user.getSex());
		fieldMap.put("tel", user.getTel());
		fieldMap.put("address", user.getAddress());
		//根据主键修改记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(user, key));
		}
	}

	@Override
	protected void beforeSelect(String... fields) throws SQLException {
		if(null == this.user) {
			throw new SQLException("User 对象为空");
		}
		super.beforeSelect();
		Map<String, Object> map = BeanUtil.getKeyValue(user);
		for(int i = 0; i < fields.length; i++) {
			conditionMap.put(fields[i], map.get(fields[i]));
		}
//		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
//		while(iter.hasNext()) {
//			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)iter.next();
//			if(null != entry.getValue()) {
//				conditionMap.put(entry.getKey(), entry.getValue());
//			}
//		}
	}
}
