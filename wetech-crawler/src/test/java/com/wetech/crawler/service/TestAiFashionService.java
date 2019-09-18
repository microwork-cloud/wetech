package com.wetech.crawler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.project.aifashion.AiFashionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestAiFashionService {
	@Autowired
	private AiFashionService service;
	
	@Test
	public void updateTagsTest() {
		service.updateTags();
	}
}
