package com.pursuit.noteblog.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pursuit.noteblog.TestCase;
import com.pursuit.noteblog.po.NoteConfig;
import com.pursuit.noteblog.util.IdGenerator;

public class NoteConfigTestCase extends TestCase {
	@Autowired
	NoteConfigMapper noteConfigMapper;
	@Test
	public void testAddNoteConfig(){
		System.out.println(noteConfigMapper);
		NoteConfig noteConfig  = new NoteConfig();
		noteConfig.setId(IdGenerator.UID.generateSessionId());
		noteConfig.setConfigKey("key");
		noteConfig.setConfigValue("value");
		noteConfig.setInfo("info");
		noteConfig.setStatus(1);
		noteConfig.setOrderId(1);
		noteConfigMapper.insert(noteConfig);
	}
}
