package com.wetech.crawler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.service.simulator.TmallSearchService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestTmallSearchService {

	
	@Autowired
	TmallSearchService service;
	
	
	@Test
	public void getItemListTest() {
		service.getItemList(80);
	}
	
	

}
