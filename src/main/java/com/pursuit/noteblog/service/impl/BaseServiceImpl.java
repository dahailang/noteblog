package com.pursuit.noteblog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.pursuit.noteblog.entity.BaseEntity;
import com.pursuit.noteblog.entity.User;

public abstract  class BaseServiceImpl<T extends BaseEntity>  {
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public void insert(T t) {  
		mongoTemplate.insert(t);  
	}  
    public void insert(T object,String collectionName) {  
        mongoTemplate.insert(object, collectionName);  
    }
    
    public void remove(T t) {
    	mongoTemplate.remove(t);
    }
    public void remove(T t,String collectionName) {
    	mongoTemplate.remove(t, collectionName);
    }
    public void remove(String id,String collectionName) {
    	mongoTemplate.remove(new Query(Criteria.where("id").is(id)), collectionName);
    }
    public void remove(Map<String, Object> params,String collectionName) {  
        mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))),User.class,collectionName);  
    }
    
	public void updtate(T t){
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(t.getId())), new Update(), t.getClass());
	}
	public void updtate(T t,String collectionName){
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(t.getId())), new Update(), collectionName);
	}
    public void update(Map<String,Object> params,String collectionName) {  
        mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))), new Update().set("name", params.get("name")), User.class,collectionName);  
    }
    
    
    
    public User findOne(Map<String,Object> params,String collectionName,T b) {  
         
    	return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))),User.class,collectionName);    
    }  
  
    public List<User> findAll(Map<String,Object> params,String collectionName) {  
        List<User> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), User.class,collectionName);  
        return result;  
    }  
  

  
    public void createCollection(String name) {  
        mongoTemplate.createCollection(name);  
    }  

}
