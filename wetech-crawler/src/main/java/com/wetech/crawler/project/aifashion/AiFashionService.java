package com.wetech.crawler.project.aifashion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wetech.crawler.project.common.StringUtils;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.utils.AiFashionUtil;

@Service
public class AiFashionService {

	private static final Logger log = LoggerFactory.getLogger(AiFashionService.class);

	@Autowired
	private WetechTaobaoClothMapper mapper;

	
	
	/**
	 * 根据图片更新标签
	 */
	public void updateTags() {

		List<WetechTaobaoCloth> clothList = mapper.selectWetechTaobaoClothList(null);
		for (WetechTaobaoCloth wetechTaobaoCloth : clothList) {
			String imageURL = wetechTaobaoCloth.getClothImage();
			if (StringUtils.isNotEmpty(imageURL)) {
				imageURL = "https:" + wetechTaobaoCloth.getClothImage();

				String clothTags = AiFashionUtil.getImageTags(imageURL);
				wetechTaobaoCloth.setClothTags(clothTags);
				
				
				String shopTitle = wetechTaobaoCloth.getShopTitle();
				if(StringUtils.isNotEmpty(shopTitle) ) {
					shopTitle = shopTitle.replace("<>", "").replaceAll(" ", "");
					wetechTaobaoCloth.setShopTitle(shopTitle);
				}
				String clothTitle = wetechTaobaoCloth.getClothTitle();
				if(StringUtils.isNotEmpty(clothTitle) ) {
					clothTitle = clothTitle.replace("<>", "").replaceAll(" ", "");
					wetechTaobaoCloth.setClothTitle(clothTitle);
				}		
				
				mapper.updateWetechTaobaoCloth(wetechTaobaoCloth);
			}
		}

	}
	
	
	

	
	
	

}
