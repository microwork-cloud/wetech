package com.wetech.crawler.service.processor;

import us.codecraft.webmagic.Site;

public abstract class TaobaoSite {
	  // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Accept-Language","zh-CN,zh;q=0.9")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
           // .addHeader("Cookie","thw:cn;_l_g_:Ug%3D%3D;lgc:danxy2008;cookie1:AVYp%2BhVrfCmYa8Y8t3ovhRXXvSRL%2FDEI7eTDZQ1GQbQ%3D;cookie2:1941ddaa77be126410fd9a0f91681546;existShop:MTU2ODYxNTQ2MA%3D%3D;sg:81d;cna:FBoGFqZA2jMCAXQG6kIeqIIX;_mw_us_time_:1568615460884;skt:bbacbcf06fb382de;_tb_token_:79705be4394f3;dnk:danxy2008;uc1:cookie16=WqG3DMC9UpAPBHGz5QBErFxlCA%3D%3D&cookie21=VFC%2FuZ9ainBZ&cookie15=UIHiLt3xD8xYTw%3D%3D&existShop=false&pas=0&cookie14=UoTaECDQIjYzsQ%3D%3D&tag=8&lng=zh_CN;uc3:lg2=UtASsssmOIJ0bQ%3D%3D&vt3=F8dByuKwBg3BWpwwJCE%3D&id2=UU6ieaOf55Q%3D&nk2=B0PyC2aheM%2Fs;tracknick:danxy2008;uc4:nk4=0%40BQBQZbGPB5w%2FQkvg2vwcr0dmtJs%3D&id4=0%40U2xvLY0R1dzIt4x1BeWvY2G6ug%3D%3D;mt:ci=1_1;unb:26354511;_cc_:UIHiLt3xSw%3D%3D;l:cBNUgwheqB8F0UmEXOCwZuIRdJ7OSIOYYuPRwSAMi_5B56L1LoQOky2cYFp6VsWd9dYB4LtazLp9-et8i2v2_5A8E5vP.;cookie17:UU6ieaOf55Q%3D;_nk_:danxy2008;tg:0;t:39998963ea41c99bb14ea9df302d861e;v:0;csg:d3700dd9;isg:BAEBf1TpOC8jcVQb_xU5nAKGE07bhvwhN51892NW_YhnSiEcq36F8C_IKH6p2Q1Y;")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36")
//            .addHeader("X-Anit-Forge-Code","0")
//            .addHeader("X-Anit-Forge-Token","None")
            .addHeader("X-Requested-With","XMLHttpRequest");

}
