package com.sxd.fs.service.impl;

import com.sxd.fs.bean.User;
import com.sxd.fs.dao.impl.BaseDao;
import com.sxd.fs.dao.impl.UserDaoImpl;
import com.sxd.fs.service.UserService;

/**
 * @author    ShiXiaodong
 * @date      2018年1月3日
 * @describe  User业务层
 * @version   v1.0
 */

public class UserServiceImpl implements UserService {
	private User user = new User();
	private BaseDao<User> dao = null;
	
	/**
	 * 账户登陆校验
	 * @param account 账号
	 * @param password 密码
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
	 * 注册用户信息
	 * @param user注册用户
	 * @return
	 */
	@Override
	public boolean register(User user) {
		dao = new UserDaoImpl(user);
		return dao.insert();
	}

	/**
	 * 删除用户记录
	 * @param user删除对象
	 * @return
	 */
	@Override
	public boolean logOut(User user) {
		dao = new UserDaoImpl(user);
		return dao.delete();
	}

}
