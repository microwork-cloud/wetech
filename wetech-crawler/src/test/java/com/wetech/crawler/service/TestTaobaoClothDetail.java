package com.wetech.crawler.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.service.processor.TaobaoClothDetail;

import us.codecraft.webmagic.Spider;

public class TestTaobaoClothDetail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(1568635219);

		System.out.println(sdf.format(date));

		String url = "https://item.taobao.com/item.htm?id=601792711621";

		WetechTaobaoCloth taobaoCloth = new WetechTaobaoCloth();
		taobaoCloth.setShopLink(url);
		taobaoCloth.setShopTitle("戎美");
		taobaoCloth.setClothLink(url);

		Spider.create(new TaobaoClothDetail(null, taobaoCloth)).addUrl(url).run();
	}
}
