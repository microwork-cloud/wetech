package com.wetech.crawler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.service.processor.TaobaoClothBrandHome;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestTaobaoClothBrandList {
	

	@Autowired
	private WetechTaobaoClothMapper mapper;
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://shop33558160.taobao.com/";

		
		WetechTaobaoCloth taobaoCloth = new WetechTaobaoCloth();
		taobaoCloth.setShopLink(url);
		taobaoCloth.setShopTitle("戎美");
		
        Spider.create(new TaobaoClothBrandHome(null,taobaoCloth))
        .addUrl(url)
        .run();
	}

}
