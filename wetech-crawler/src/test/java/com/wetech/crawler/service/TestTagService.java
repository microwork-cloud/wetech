package com.wetech.crawler.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wetech.crawler.WetechCrawlerApplication;
import com.wetech.crawler.project.aifashion.TagService;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WetechCrawlerApplication.class)
public class TestTagService {


	@Autowired
	private TagService service;
	
	@Autowired
	private WetechTaobaoClothMapper mapper;
	
	@Test
	public void getPatternTagsTest() {
		//service.getPatternTags();
		
		List<WetechTaobaoCloth> clothList = mapper.selectWetechTaobaoClothList(null);
		for (WetechTaobaoCloth wetechTaobaoCloth : clothList) {
			String shopTitle = wetechTaobaoCloth.getShopTitle();
			shopTitle = shopTitle.replace("<>", "").replaceAll(" ","").trim();
			wetechTaobaoCloth.setShopTitle(shopTitle);
			mapper.updateWetechTaobaoCloth(wetechTaobaoCloth);
		}
	}
}
