package com.wetech.crawler.service.processor;

import java.util.ArrayList;
import java.util.List;

import com.wetech.crawler.project.common.StringUtils;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class TmallClothSearchList extends TmallSite implements PageProcessor{

	private WetechTaobaoClothMapper mapper;
	private WetechTaobaoCloth taobaoCloth;

	public TmallClothSearchList(WetechTaobaoClothMapper mapper, WetechTaobaoCloth taobaoCloth) {
		this.mapper = mapper;
		this.taobaoCloth = taobaoCloth;
	}
	
	@Override
	public void process(Page page) {
		
		System.out.println(page.getHtml());

		List<WetechTaobaoCloth> clothList = new ArrayList<WetechTaobaoCloth>();

		// 获取所有商品列表
		List<String> clothItem = page.getHtml().xpath("//div[@class='product-iWrap']/html()").all();

		for (String item : clothItem) {
			Html itemHtml = Html.create(item);
			String clothLink = itemHtml.xpath("//div[@class='productImg-wrap']/a/@href").get();
			String clothImage =  itemHtml.xpath("//div[@class='productImg-wrap']/a/img/@data-ks-lazyload").get();
			
			String clothTitle = itemHtml.xpath("//p[@class='productTitle']/a/tidyText()").get();
			String clothSpecialPrice = itemHtml.xpath("//p[@class='productPrice']/em/tidyText()").get();
			
			String amount = itemHtml.xpath("//p[@class='productStatus']/span/em").get();

			String shopTitle = itemHtml.xpath("//a[@class='productShop-name']/tidyText()").get();
			String shopLink  = itemHtml.xpath("//a[@class='productShop-name']/@href").get();
			
			WetechTaobaoCloth cloth = new WetechTaobaoCloth();
			cloth.setShopTitle(shopTitle);
			cloth.setShopLink(shopLink);
			cloth.setClothLink(clothLink);
			cloth.setClothTitle(clothTitle);
			cloth.setClothImage(clothImage);
			
			//当前价格/优惠价
			if (StringUtils.isNotEmpty(clothSpecialPrice)) {
				cloth.setClothSpecialPrice(Double.valueOf(clothSpecialPrice).longValue());
			}

//			if (StringUtils.isNotEmpty(amount)) {
//				cloth.setClothAmount(Double.valueOf(amount).longValue());
//			}
			
			clothList.add(cloth);
		}
		
		for (WetechTaobaoCloth cloth : clothList) {
			mapper.insertWetechTaobaoCloth(cloth);
			System.out.println(cloth.getClothTitle()+"=="+cloth.getClothLink()+"=="+ cloth.getClothPrice() + "=="+cloth.getClothSpecialPrice()+"=="+cloth.getClothAmount());
		}

		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
