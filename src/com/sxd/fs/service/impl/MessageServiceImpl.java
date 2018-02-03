package com.sxd.fs.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.sxd.fs.bean.Message;
import com.sxd.fs.dao.impl.BaseDao;
import com.sxd.fs.dao.impl.MessageDaoImpl;
import com.sxd.fs.service.MessageService;

public class MessageServiceImpl implements MessageService {

	

	private BaseDao<Message> dao = null;
	
	@Override
	public int insert(Message message) 
	{
		int thisRowno = getMaxRowno() + 1;
		message.setRowno(getMaxRowno() + 1);
		dao = new MessageDaoImpl(message);
		dao.insert();
		return thisRowno;
	}

	@Override
	public boolean delete(int rowno) {
		Message message = new Message();
		message.setRowno(rowno);
		dao = new MessageDaoImpl(message);
		dao.delete();
		return false;
	}
	
	@Override
	public List<Message> select(String... fields) {
		dao = new MessageDaoImpl(new Message());
		List<Message> list = dao.select(fields);
		//µ¹Ðò
		Collections.reverse(list);
		return list;
	}

	
	public int getMaxRowno() {
		String sql = "SELECT MAX(ROWNO) FROM fs_message";
		dao = new MessageDaoImpl();
		ResultSet rs = dao.select(sql);
		try {
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
