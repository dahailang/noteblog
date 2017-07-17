package com.pursuit.noteblog;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pursuit.noteblog.dao.UserDao;
import com.pursuit.noteblog.entity.User;
import com.pursuit.noteblog.enums.UserStatusEnum;
import com.pursuit.noteblog.enums.UserTypeEnum;
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteblogApplicationTests {
	@Autowired
	UserDao userDao;
	@Test
	public void addusertest() {
		User user = new User();
		user.setUid("2");
		user.setAvatar("1231");
		user.setCreatetime(new Date());
		user.setEmail("12131");
		user.setPassword("212");
		user.setStatus(UserStatusEnum.USER_RUNNING.getUserStatus());
		user.setType(UserTypeEnum.REGULAR_USER.getUserType());
		user.setNickname("jingnan");
		userDao.addUser(user);
	}
	@Test
	public void deleteusertest() {
		User user = new User();
		user.setUid("2");
		user.setAvatar("1231");
		user.setCreatetime(new Date());
		user.setEmail("12131");
		user.setPassword("212");
		user.setStatus(UserStatusEnum.USER_RUNNING.getUserStatus());
		user.setType(UserTypeEnum.REGULAR_USER.getUserType());
		user.setNickname("jingnan");
		userDao.deleteUser(user);
	}
	@Test
	public void deleteUserByIdtest() {
		userDao.deleteUserById("2");
	}
	@Test
	public void getAllUserTest(){
		List<User> allUser = userDao.getAllUser();
		System.out.println(allUser);
	}
	
}
