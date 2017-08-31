package com.pursuit.noteblog.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.TestCase;
import com.pursuit.noteblog.po.NoteUser;
import com.pursuit.noteblog.util.IdGenerator;

public class NoteUserTestCase extends TestCase {
	@Autowired
	NoteUserMapper noteUserMapper;
	@Test
	public void testAddUser(){
		System.out.println(noteUserMapper);
		NoteUser noteUser = new NoteUser();
		noteUser.setId(IdGenerator.UID.generateSessionId());
		noteUser.setAvatar("/sdfs/sdfs");
		noteUser.setCreateTime(new Date());
		noteUser.setLastUpdateTime(new Date());
		noteUser.setEmail("watgn111");
		noteUser.setPassword("1231");
		noteUser.setNickname("dfs");
		noteUser.setStatus(1);
		noteUser.setType(1);
		noteUserMapper.insert(noteUser);
	}
}
