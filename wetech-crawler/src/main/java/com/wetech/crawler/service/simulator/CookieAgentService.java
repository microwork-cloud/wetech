package com.wetech.crawler.service.simulator;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wetech.crawler.utils.LoginUtil;
import com.wetech.crawler.utils.TextUtil;
import com.wetech.crawler.utils.WebdriverUtil;

import java.util.ArrayList;
import java.util.Set;

public class CookieAgentService {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);

    public void cookieAgentTaobao() throws Exception {
        WebdriverUtil.fireFoxWebdriver();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://login.taobao.com/member/login.jhtml");
        
        
        //登录
        LoginUtil.taobaoLogin(driver);

        //存cookie
        Set<Cookie> cookies = driver.manage().getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.getName() + ":" + cookie.getValue()+";");
            System.out.println(cookie.getName() + "value :" + cookie.getValue());
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(sb.toString());
        TextUtil.textWriter("cookies.txt",list);
    }

}
