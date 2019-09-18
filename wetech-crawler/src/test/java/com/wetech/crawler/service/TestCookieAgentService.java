package com.wetech.crawler.service;

import com.wetech.crawler.service.simulator.CookieAgentService;

public class TestCookieAgentService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CookieAgentService cas = new CookieAgentService();
		try {
			cas.cookieAgentTaobao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
