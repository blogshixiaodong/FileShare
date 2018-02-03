package com.sxd.fs.service;

import java.util.List;

import com.sxd.fs.bean.File;

public interface FileService extends IServiceBase {
	public int insert(java.io.File file, String author);
	public int delete(String downloadPath, int fileId);
	public List<File> select(String... fields);
}
