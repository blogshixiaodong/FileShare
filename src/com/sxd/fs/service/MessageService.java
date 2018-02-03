package com.sxd.fs.service;

import java.util.List;

import com.sxd.fs.bean.Message;
import com.sxd.fs.service.IServiceBase;

public interface MessageService extends IServiceBase {
	public int insert(Message message);
	public boolean delete(int rowno);
	public List<Message> select(String... fields);
}
