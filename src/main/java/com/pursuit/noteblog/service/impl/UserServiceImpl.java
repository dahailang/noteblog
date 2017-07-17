package com.pursuit.noteblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.dao.UserDao;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User get(String uid) {
		return userDao.getUserById(uid);
	}
	@Override
	public User getByEmail(String email) {
		User usercondition = new User();
		usercondition.setEmail(email);
		List<User> users = userDao.getUserByCondition(usercondition);
		if(null!=users&&users.size()>0){
			return users.get(1);
		}
		return null;
	}
	@Override
	public User addUser(User user) {
		userDao.addUser(user);
		return user;
	}
	@Override
	public SuccessEnum update(User user) {
		userDao.updateUser(user);
		return SuccessEnum.SUCCESS;
	}
	@Override
	public SuccessEnum delete(String userId) {
		userDao.deleteUserById(userId);
		return SuccessEnum.SUCCESS;
	}


}
