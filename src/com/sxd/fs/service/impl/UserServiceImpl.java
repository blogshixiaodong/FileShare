package com.sxd.fs.service.impl;

import com.sxd.fs.bean.User;
import com.sxd.fs.dao.impl.BaseDao;
import com.sxd.fs.dao.impl.UserDaoImpl;
import com.sxd.fs.service.UserService;

/**
 * @author    ShiXiaodong
 * @date      2018��1��3��
 * @describe  Userҵ���
 * @version   v1.0
 */

public class UserServiceImpl implements UserService {
	private User user = new User();
	private BaseDao<User> dao = null;
	
	/**
	 * �˻���½У��
	 * @param account �˺�
	 * @param password ����
	 * @return
	 */
	@Override
	public boolean loginCheck(String account, String password) {
		dao = new UserDaoImpl(user);
		user.setAccount(account);
		user.setPassword(password);
		User result = dao.select(new String[] {"account"}).get(0);
		if(password.equals(result.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * ע���û���Ϣ
	 * @param userע���û�
	 * @return
	 */
	@Override
	public boolean register(User user) {
		dao = new UserDaoImpl(user);
		return dao.insert();
	}

	/**
	 * ɾ���û���¼
	 * @param userɾ������
	 * @return
	 */
	@Override
	public boolean logOut(User user) {
		dao = new UserDaoImpl(user);
		return dao.delete();
	}

}
