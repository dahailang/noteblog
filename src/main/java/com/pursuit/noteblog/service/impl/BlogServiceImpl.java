package com.pursuit.noteblog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.entity.UserBlog;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.service.BlogService;

public class BlogServiceImpl implements BlogService {
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	
    public void insert(User object,String collectionName) {  
        mongoTemplate.insert(object, collectionName);  
    }  
  
  
    public List<User> findAll(Map<String,Object> params,String collectionName) {  
        List<User> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), User.class,collectionName);  
        return result;  
    }  
  
    public void update(Map<String,Object> params,String collectionName) {  
        mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))), new Update().set("name", params.get("name")), User.class,collectionName);  
    }  
  
    public void createCollection(String name) {  
        mongoTemplate.createCollection(name);  
    }  
  
  
    public void remove(Map<String, Object> params,String collectionName) {  
        mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))),User.class,collectionName);  
    }  
	
	
	@Override
	public UserBlog findById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),UserBlog.class);
	}

	@Override
	public UserBlog save(UserBlog e) {
		mongoTemplate.insert(e, "user_bolgs");  
		return e;
	}

	@Override
	public SuccessEnum update(UserBlog e) {
		//mongoTemplate.upsert(query, update, collectionName)
		return null;
	}

	@Override
	public SuccessEnum deleteBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBlog updateUserBlog(UserBlog UserBlog) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UserBlog> findByUserId(String userId) {
		return mongoTemplate.find(new Query(Criteria.where("userId").is(userId)),UserBlog.class);
	}
}