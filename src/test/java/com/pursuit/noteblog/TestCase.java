package com.pursuit.noteblog;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@ActiveProfiles("online")
public abstract class TestCase {
	
}