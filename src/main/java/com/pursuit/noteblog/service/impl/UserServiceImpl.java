package com.pursuit.noteblog.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.dao.UserDao;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.UserService;
//@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User get(String userId) {
		return userDao.getUserById(uid);
	}
	@Override
	public User getByEmail(String email) {
		User user = userDao.getUserByEmail(email);
		return user;
	}

	@Override
	public User getByUsername(String username) {
		User users =userDao.getUserByname(username);
		return users;
	}
	@Override
	public User findByUsernameOrEmail(String username,String email) {
		if (StringUtils.isNotBlank(username)) {
		}
		if (StringUtils.isNotBlank(email)) {
		}
		return user.getUserByInfo(user);
	}


}
