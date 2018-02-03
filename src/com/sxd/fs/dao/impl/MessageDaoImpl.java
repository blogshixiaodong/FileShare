package com.sxd.fs.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import com.sxd.bean.BeanUtil;
import com.sxd.fs.bean.Message;

public class MessageDaoImpl extends BaseDao<Message> {

	private Message message = null;
	
	public MessageDaoImpl(Message message) {
		this.message = message;
	}
	
	public MessageDaoImpl() {
	}

	@Override
	public String getTableName() {
		return "fs_message";
	}

	@Override
	public Class<Message> getBeanClass() {
		return Message.class;
	}

	@Override
	public String[] getPrimaryKeys() {
		return new String[] {"rowno"};
	}

	@Override
	protected void beforeInsert() throws SQLException {
		if(null == message) {
			throw new SQLException("Message 对象为空");
		}
		super.beforeInsert();
		fieldMap.put("rowno", message.getRowno());
		fieldMap.put("id", message.getId());
		fieldMap.put("time", message.getTime());
		fieldMap.put("response", message.getResponse());
		
	}

	@Override
	protected void beforeDelete() throws SQLException {
		if(null == message) {
			throw new SQLException("Message 对象为空");
		}
		super.beforeDelete();
		//根据主键删除记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(message, key));
		}
		
	}

	@Override
	protected void beforeUpdate() throws SQLException {
		if(null == message) {
			throw new SQLException("Message 对象为空");
		}
		super.beforeUpdate();
		fieldMap.put("id", message.getId());
		fieldMap.put("time", message.getTime());
		fieldMap.put("response", message.getResponse());
		//根据主键修改记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(message, key));
		}
		
	}

	@Override
	protected void beforeSelect(String... key) throws SQLException {
		if(null == message) {
			throw new SQLException("Message 对象为空");
		}
		super.beforeSelect();
		Map<String, Object> map = BeanUtil.getKeyValue(message);
		for(int i = 0; i < key.length; i++) {
			conditionMap.put(key[i], map.get(key[i]));
		}
	}

}
