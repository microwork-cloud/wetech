package com.wetech.crawler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.service.processor.TmallClothSearchList;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestTmallClothSearchList {
	

	@Autowired
	private WetechTaobaoClothMapper mapper;
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int page = 1;
		String url = "https://list.tmall.com/search_product.htm?q=%B3%C4%C9%C0%C5%AE&sort=new&s="+ (page-1)*60;

		
		WetechTaobaoCloth taobaoCloth = new WetechTaobaoCloth();
		taobaoCloth.setShopLink(url);
		taobaoCloth.setShopTitle("戎美");
		
        Spider.create(new TmallClothSearchList(null,taobaoCloth))
        .addUrl(url)
        .run();
	}

}
