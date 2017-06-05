package com.pursuit.noteblog.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.SuccessEnum;
import com.pursuit.noteblog.repository.UserRepository;
import com.pursuit.noteblog.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User get(String userId) {
		return userRepository.findOne(userId);
	}
	@Override
	public User getByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User getByUsername(String username) {
		User users =userRepository.findByUsername(username);
		return users;
	}
	@Override
	public User findByUsernameOrEmail(String username,String email) {
		BasicDBList andList = new BasicDBList();//用于记录
		if (StringUtils.isNotBlank(username)) {
			andList.add(new BasicDBObject("username", username));
		}
		if (StringUtils.isNotBlank(email)) {
			andList.add(new BasicDBObject("email", email));
		}
		BasicDBObject andDBObject = new BasicDBObject("$and", andList);
		return mongoTemplate.findOne(new BasicQuery(andDBObject), User.class);
	}

	@Override
	public User save(User user) {
		user.setCreatedTime(new Date());
		user.setNoteListWidth(200);
		user.setNotebookWidth(200);
		// 发送验证邮箱 EmailService.registerSendActiveEmail
		return userRepository.save(user);
	}

	@Override
	public SuccessEnum update(User user) {
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(user.getId())), new Update().set("email", user.getEmail()), User.class);
		return SuccessEnum.SUCCESS;
	}

	@Override
	public SuccessEnum delete(String userId) {
		userRepository.delete(userId);
		return SuccessEnum.SUCCESS;
	}

}
