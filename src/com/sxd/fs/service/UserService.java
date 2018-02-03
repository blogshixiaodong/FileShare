package com.sxd.fs.service;

import com.sxd.fs.bean.User;

public interface UserService extends IServiceBase {
	public boolean loginCheck(String account, String password);
	public boolean register(User user);
	public boolean logOut(User user);
}
