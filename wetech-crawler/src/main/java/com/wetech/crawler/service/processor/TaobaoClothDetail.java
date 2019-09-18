package com.wetech.crawler.service.processor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.utils.MatcherUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class TaobaoClothDetail extends TaobaoSite implements PageProcessor {
	
	private WetechTaobaoClothMapper mapper;
	private WetechTaobaoCloth taobaoCloth;
	
	public TaobaoClothDetail(WetechTaobaoClothMapper mapper, WetechTaobaoCloth taobaoCloth) {
		this.mapper = mapper;
		this.taobaoCloth = taobaoCloth;
	}

	@Override
	public void process(Page page) {

		//Step1 提取JS值
		List<String> scriptList = page.getHtml().xpath("//script/html()").all();
		String g_config = null;
		for (String srcipt : scriptList) {
			if (srcipt.contains("var g_config")) {
				g_config = srcipt;
				break;
			}
		}
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(g_config.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));

			// 上架日期
			Date clothDate = null;
			String clothImage = null;

			// 提取数字
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			String dateFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("dbst")) {
					Matcher m = p.matcher(line);
					line = m.replaceAll("").trim();
					clothDate = new Date(Long.valueOf(line));
				}

				if (line.contains("auctionImages")) {
					line = line.replaceAll(" ", "").trim();
					clothImage = "{" + line + "}";
					break;
				}

			}
			
			taobaoCloth.setClothDate(clothDate);
			taobaoCloth.setClothImage(clothImage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Step2 提取特征值
		List<String> parameterList = page.getHtml().xpath("//div[@class='attributes']/ul/li/html()").all();
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


	}

	@Override
	public Site getSite() {
		return site;
	}

}
