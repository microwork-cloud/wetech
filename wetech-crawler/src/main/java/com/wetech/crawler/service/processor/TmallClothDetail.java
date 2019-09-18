package com.wetech.crawler.service.processor;

import java.util.List;

import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TmallClothDetail extends TaobaoSite implements PageProcessor {

	private WetechTaobaoClothMapper mapper;
	private WetechTaobaoCloth taobaoCloth;

	public TmallClothDetail(WetechTaobaoClothMapper mapper, WetechTaobaoCloth taobaoCloth) {
		this.mapper = mapper;
		this.taobaoCloth = taobaoCloth;
	}

	@Override
	public void process(Page page) {

		// 图片
		String clothImage = page.getHtml().xpath("//img[@id='J_ImgBooth']/@src").get();
		int index = clothImage.indexOf(".jpg");
		if (index > 0) {
			clothImage = clothImage.substring(0, index) + ".jpg";
		}
		System.out.println(clothImage);

		// 特征
		List<String> parameterList = page.getHtml().xpath("//ul[@id='J_AttrUL']/li/html()").all();
		StringBuffer sbf = new StringBuffer();
		sbf.append("{\"");
		for (String parameter : parameterList) {
			parameter = parameter.replaceAll("&nbsp;", "");
			parameter = parameter.replace(":", "\":\"");
			sbf.append(parameter);
			sbf.append("\",\"");
		}
		sbf.append("}");

		String clothDesc = sbf.toString().replace(",\"}", "}");

		System.out.println(clothDesc);

		taobaoCloth.setClothImage(clothImage);
		taobaoCloth.setClothDesc(clothDesc);
		mapper.updateWetechTaobaoCloth(taobaoCloth);
	}

	@Override
	public Site getSite() {
		return site;
	}

}
