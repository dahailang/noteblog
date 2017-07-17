package com.pursuit.noteblog.service.impl;

import java.util.List;
import java.util.Map;

import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.entity.UserBlog;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.BlogService;

public class BlogServiceImpl implements BlogService {
	
    public void insert(User object,String collectionName) {  
    }  
  
  
    public List<User> findAll(Map<String,Object> params,String collectionName) {  
        return null;  
    }  
  
    public void update(Map<String,Object> params,String collectionName) {  
    }  
  
    public void createCollection(String name) {  
    }  
  
  
    public void remove(Map<String, Object> params,String collectionName) {  
    }  
	
	
	@Override
	public UserBlog findById(String id) {
		return null;
	}

	@Override
	public UserBlog save(UserBlog e) {
		return e;
	}

	@Override
	public SuccessEnum update(UserBlog e) {
		return null;
	}

	@Override
	public SuccessEnum deleteBy(String id) {
		return null;
	}

	@Override
	public UserBlog updateUserBlog(UserBlog UserBlog) {
		return null;
	}


	@Override
	public List<UserBlog> findByUserId(String userId) {
		return null;
	}
}