package com.wetech.crawler.service;

import java.io.File;

import com.wetech.crawler.service.ipserver.IpAgentService;

import us.codecraft.webmagic.Spider;

public class TestIpAgentService {

	public static void main(String[] args) {
        //每次获取IP都删除之前的IP
        File file = new File(IpAgentService.URLTXT_PATH+"ipList.txt");
        //判断是否待删除目录是否存在
        if(!file.exists()){
            System.err.println("The dir are not exists!");
        }

        Spider.create(new IpAgentService())
                .addUrl("http://www.xicidaili.com/nn/1")
                .run();
    }

}
