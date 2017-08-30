package com.pursuit.noteblog.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.util.IdGenerator;

public class NoteUserTestCase2{
	@Test
	public void testAddUser(){
		ClassPathXmlApplicationContext conteext = new ClassPathXmlApplicationContext("classpath:/spring/spring-online.xml");
		NoteUserMapper noteUserMapper = (NoteUserMapper) conteext.getBean("noteUserMapper");
		
		System.out.println(noteUserMapper);
		NoteUser noteUser = new NoteUser();
		noteUser.setId(IdGenerator.UID.generateSessionId());
		noteUser.setAvatar("/sdfs/sdfs");
		noteUser.setCreateTime(new Date());
		noteUser.setLastUpdateTime(new Date());
		noteUser.setEmail("watgn");
		noteUser.setPassword("1231");
		noteUser.setNickname("dfs");
		noteUser.setStatus(1);
		noteUser.setType(1);
		noteUserMapper.insert(noteUser);
	}
}
