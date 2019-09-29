package com.wetech.crawler.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUtil {
    private static final int SLEEP_TIME = (int) (1000 + Math.random() * 1000);

   
    public static void taobaoLogin(WebDriver driver) throws Exception{
        Thread.sleep(SLEEP_TIME*3);
        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,60,10);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.xpath("//*[@id=\"J_Quick2Static\"]"));
            }
        });
        Thread.sleep(SLEEP_TIME);
        WebElement loginPage = driver.findElement(By.xpath("//*[@id=\"J_Quick2Static\"]"));
        loginPage.click();
        Thread.sleep(SLEEP_TIME);
        //输入账号
        WebElement accounts = driver.findElement(By.id("TPL_username_1"));
        Thread.sleep(SLEEP_TIME);
        //点击输入框
        accounts.click();
        //输入
        Thread.sleep(SLEEP_TIME);
        InputUtil.chineseInput(accounts, "******");
        Thread.sleep(SLEEP_TIME);
        //输入密码
        WebElement password = driver.findElement(By.id("TPL_password_1"));
        //点击输入框
        password.click();
        //输入
        Thread.sleep(SLEEP_TIME);
        InputUtil.chineseInput(password, "******");
        Thread.sleep(SLEEP_TIME);
        //点击登录
        WebElement tmSearchButton = driver.findElement(By.xpath("//*[@id=\"J_SubmitStatic\"]"));
        Thread.sleep(SLEEP_TIME);
        tmSearchButton.click();
        Thread.sleep(SLEEP_TIME*3);
    }
}
