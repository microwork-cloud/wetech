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

public class TaobaoClothBrandHome extends TaobaoSite implements PageProcessor {

	private WetechTaobaoClothMapper mapper;
	private WetechTaobaoCloth taobaoCloth;

	public TaobaoClothBrandHome(WetechTaobaoClothMapper mapper, WetechTaobaoCloth taobaoCloth) {
		this.mapper = mapper;
		this.taobaoCloth = taobaoCloth;
	}

	@Override
	public void process(Page page) {

		List<WetechTaobaoCloth> clothList = new ArrayList<WetechTaobaoCloth>();

		// 获取所有商品列表
		List<String> clothItem = page.getHtml().xpath("//dd[@class='detail']/html()").all();

		for (String item : clothItem) {
			Html itemHtml = Html.create(item);
			String clothLink = itemHtml.xpath("//a[@class='item-name']/@href").get();
			String clothTitle = itemHtml.xpath("//a[@class='item-name']/html()").get();
			String clothSpecialPrice = itemHtml.xpath("div[@class='attribute']/div[@class='cprice-area']/span[@class='c-price']/tidyText()").get();
			String clothPrice = itemHtml.xpath("div[@class='attribute']/div[@class='sprice-area']/span[@class='s-price']/tidyText()").get();
			String amount = itemHtml.xpath("div[@class='attribute']/div[@class='sale-area']/span[@class='sale-num']/tidyText()").get();

			WetechTaobaoCloth cloth = new WetechTaobaoCloth();
			cloth.setShopTitle(taobaoCloth.getShopTitle());
			cloth.setShopLink(taobaoCloth.getShopLink());
			cloth.setClothLink(clothLink);
			cloth.setClothTitle(clothTitle);
			
			//当前价格/优惠价
			if (StringUtils.isNotEmpty(clothSpecialPrice)) {
				cloth.setClothSpecialPrice(Double.valueOf(clothSpecialPrice).longValue());
			}
			//原价
			if (StringUtils.isNotEmpty(clothPrice)) {
				cloth.setClothPrice(Double.valueOf(clothPrice).longValue());
			}else {
				cloth.setClothPrice(cloth.getClothSpecialPrice());
			}
			if (StringUtils.isNotEmpty(amount)) {
				cloth.setClothAmount(Double.valueOf(amount).longValue());
			}
			
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
