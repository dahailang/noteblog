package com.pursuit.noteblog.dao;

import java.util.List;

import com.pursuit.noteblog.entity.User;

/**
 * 用户表dao
 */
public interface UserDao {
	
	public User getUserById(String uid);
    public List<User> getAllUser();
    public List<User> getUserByCondition();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUserById(String uid);
    public void deleteUser(User user);

}
