package com.sxd.fs.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import com.sxd.bean.BeanUtil;
import com.sxd.fs.bean.File;


public class FileDaoImpl extends BaseDao<File> {
	
	public File file = null;
	
	public FileDaoImpl(File file) {
		this.file = file;
	}
	
	@Override
	public String getTableName() {
		return "fs_file";
	}

	@Override
	public Class<File> getBeanClass() {
		return File.class;
	}

	@Override
	public String[] getPrimaryKeys() {
		return new String[] {"fileId"};
	}

	@Override
	protected void beforeInsert() throws SQLException {
		if(null == this.file) {
			throw new SQLException("File 对象为空!");
		}
		super.beforeInsert();
		fieldMap.put("fileId", file.getFileId());
		fieldMap.put("fileName", file.getFileName());
		fieldMap.put("uploadAuthor", file.getUploadAuthor());
		fieldMap.put("fileType", file.getFileType());
		fieldMap.put("uploadTime", file.getUploadTime());
		
	}

	@Override
	protected void beforeDelete() throws SQLException {
		if(null == this.file) {
			//User对象为空
			throw new SQLException("File 对象为空");
		}
		super.beforeDelete();
		//根据主键删除记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(file, key));
		}
	}

	@Override
	protected void beforeUpdate() throws SQLException {
		if(null == this.file) {
			throw new SQLException("File 对象为空");
		}
		super.beforeUpdate();
		fieldMap.put("fileName", file.getFileName());
		fieldMap.put("uploadAuthor", file.getUploadAuthor());
		fieldMap.put("fileType", file.getFileType());
		fieldMap.put("uploadTime", file.getUploadTime());
	
		//根据主键修改记录
		String[] keys = getPrimaryKeys();
		for(String key : keys) {
			conditionMap.put(key, BeanUtil.getFieldValue(file, key));
		}
	}

	@Override
	protected void beforeSelect(String... fields) throws SQLException {
		if(null == this.file || null == fields || 0 == fields.length) {
			return;
		}
		super.beforeSelect();
		Map<String, Object> map = BeanUtil.getKeyValue(file);
		for(int i = 0; i < fields.length; i++) {
			conditionMap.put(fields[i], map.get(fields[i]));
		}
	}

	
}
