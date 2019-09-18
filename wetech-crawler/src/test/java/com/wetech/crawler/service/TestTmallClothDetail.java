package com.wetech.crawler.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.service.processor.TmallClothDetail;

import us.codecraft.webmagic.Spider;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestTmallClothDetail {

	@Autowired
	private WetechTaobaoClothMapper mapper;
	
	@Test
	public void getTmallClothDetail() {
		
		//获取
		List<WetechTaobaoCloth> clothList = mapper.selectWetechTaobaoClothList(null);
		for (WetechTaobaoCloth wetechTaobaoCloth : clothList) {
			
			String url = "https:" + wetechTaobaoCloth.getClothLink();

			//
			String clothId = wetechTaobaoCloth.getClothLink();
			int start = clothId.indexOf("id=")+3;
			int end = clothId.indexOf("&skuId");
			clothId = clothId.substring(start, end);
			wetechTaobaoCloth.setClothId(clothId);
			
			Spider.create(new TmallClothDetail(mapper, wetechTaobaoCloth)).addUrl(url).run();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(url);
			
		}
		
	}
	
}
