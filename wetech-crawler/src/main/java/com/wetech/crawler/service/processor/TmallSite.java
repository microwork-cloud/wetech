package com.wetech.crawler.service.processor;

import us.codecraft.webmagic.Site;

public abstract class TmallSite{
	  // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Accept-Language","zh-CN,zh;q=0.9")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Cookie","thw:cn;_l_g_:Ug%3D%3D;lgc:danxy2008;cookie1:AVYp%2BhVrfCmYa8Y8t3ovhRXXvSRL%2FDEI7eTDZQ1GQbQ%3D;cookie2:15924e0654248f038b0cbd7db75fdd4f;existShop:MTU2ODcxODI3NQ%3D%3D;sg:81d;cna:r6sHFiogiU4CAXQG6kJ29P4L;_mw_us_time_:1568718276736;skt:fbadf3c5a250dc6c;_tb_token_:783735e3e7eeb;dnk:danxy2008;uc1:cookie16=VFC%2FuZ9az08KUQ56dCrZDlbNdA%3D%3D&cookie21=Vq8l%2BKCLjA%2Bl&cookie15=V32FPkk%2Fw0dUvg%3D%3D&existShop=false&pas=0&cookie14=UoTaECEhGVhXwg%3D%3D&tag=8&lng=zh_CN;uc3:lg2=U%2BGCWk%2F75gdr5Q%3D%3D&id2=UU6ieaOf55Q%3D&nk2=B0PyC2aheM%2Fs&vt3=F8dByuKxpLoBsamrnJo%3D;tracknick:danxy2008;uc4:id4=0%40U2xvLY0R1dzIt4x1BeRDw%2FzMJA%3D%3D&nk4=0%40BQBQZbGPB5w%2FQkvg2vwd9HXaPrw%3D;mt:ci=1_1;unb:26354511;_cc_:URm48syIZQ%3D%3D;l:cBjfPDOgqBgyYjwkBOCahurza77OSIOYYuPzaNbMi_5w46T1Ji7Oks3Q6F96VsWdOnLB4LtazLp9-et8Z_RHDnR8E5vP.;cookie17:UU6ieaOf55Q%3D;_nk_:danxy2008;tg:0;t:056afe63ab08da61645e90fe0cf689c0;v:0;csg:bf091d6b;isg:BPT0I6OoFae3JIHrg-o3elByxrGmZZG28s7JOI5VgH8C-ZRDtt3oR6o7ffGEGlAP;")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36")
//            .addHeader("X-Anit-Forge-Code","0")
//            .addHeader("X-Anit-Forge-Token","None")
            .addHeader("X-Requested-With","XMLHttpRequest");

}
