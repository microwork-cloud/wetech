package com.wetech.crawler.service.simulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wetech.crawler.project.common.StringUtils;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.utils.BaiduEntryTmallUtil;
import com.wetech.crawler.utils.LoginUtil;
import com.wetech.crawler.utils.TextUtil;
import com.wetech.crawler.utils.WebdriverUtil;

import us.codecraft.webmagic.selector.Html;

@Service
public class TmallSearchService {
	
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int SLEEP_TIME = new Random().nextInt(6000) + 8000;
    private static final int ROLL_LENGTH = new Random().nextInt(500) + 500;
    private String lastListUrl;
    
    @Autowired
	private WetechTaobaoClothMapper mapper;

	
	public void getItemList(Integer pageNum) {
        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
        try {
            //已经获取天猫商品列表
            driver = BaiduEntryTmallUtil.baiduJumpTmall(driver);

            //获取ipList
           ArrayList<String> urlList = TextUtil.textReader("ipList.txt");

            //爬取页数
            pageNum = pageNum == null ? 20 : pageNum;
            for (int i = 0; i < pageNum; i++) {
                getUrlList(driver);
                logger.info("第"+i+"页获取完毕，10秒后进入下一页");

                //进入下一页
                WebElement tmallLink = driver.findElement(By.linkText("下一页>>"));
                Thread.sleep(10000);
                tmallLink.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
	}
	
	  public void getUrlList(WebDriver driver) throws Exception {
	        Thread.sleep(SLEEP_TIME);
	        logger.info("当前窗口为：" + driver.getTitle().trim());

	        //如果出现登录
	        if (driver.getTitle().trim().equals("天猫tmall.com--理想生活上天猫")) {
	        	LoginUtil.taobaoLogin(driver);
	            logger.info("登录完毕");
	            Thread.sleep(SLEEP_TIME * 3);
	            driver.get(lastListUrl);
	            logger.info("网址跳转完毕");
	            Thread.sleep(SLEEP_TIME * 3);
	            //下拉七次
	            for (int i = 0; i < 5; i++) {
	                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
	                logger.info("下移了第"+i+"次");
	                Thread.sleep(SLEEP_TIME);
	            }
	            logger.info("下拉完毕,接下来点击下一步");
	            //进入下一页
	            WebElement tmallLink = driver.findElement(By.linkText("下一页>>"));
	            Thread.sleep(10000);
	            tmallLink.click();
	        } else {
	            lastListUrl = driver.getCurrentUrl();
	            Thread.sleep(SLEEP_TIME);
	            logger.info("执行的else");
	        }

	        //下移次数
	        int rollCount = 7;
	        for (int i = 0; i < rollCount; i++) {
	            Thread.sleep(SLEEP_TIME*2);
	            logger.info("下移了第"+i+"次");
	            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + ROLL_LENGTH + ")");
	        }

	        Thread.sleep(SLEEP_TIME);

	        //存储本页url
	        this.obtainGoodsList(driver.getPageSource());

	        Thread.sleep(SLEEP_TIME);
	    }

	    private void obtainGoodsList(String pageContent) {
	        
	    	Html pageHtml = new Html(pageContent);
	        List<WetechTaobaoCloth> clothList = new ArrayList<WetechTaobaoCloth>();

			// 获取所有商品列表
			List<String> clothItem = pageHtml.xpath("//div[@class='product-iWrap']/html()").all();

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
				
				// 提取数字
				String regEx = "[^0-9|.]";
				Pattern p = Pattern.compile(regEx);
				
				//当前价格/优惠价
				if (StringUtils.isNotEmpty(clothSpecialPrice)) {
					Matcher m = p.matcher(clothSpecialPrice);
					clothSpecialPrice = m.replaceAll("").trim();
					cloth.setClothSpecialPrice(Double.valueOf(clothSpecialPrice).longValue());
				}

				if (StringUtils.isNotEmpty(amount)) {
					Matcher m = p.matcher(amount);
					amount = m.replaceAll("").trim();
					cloth.setClothAmount(Double.valueOf(amount).longValue());
				}
				
				cloth.setCreateTime(new Date());
				
				clothList.add(cloth);
			}
			
			
			for (WetechTaobaoCloth cloth : clothList) {
				mapper.insertWetechTaobaoCloth(cloth);
				System.out.println(cloth.getClothTitle()+"=="+cloth.getClothLink()+"=="+ cloth.getClothPrice() + "=="+cloth.getClothSpecialPrice()+"=="+cloth.getClothAmount());
			}
	        logger.info("----------------本页url存储成功----------------");
	    }
	    
}
