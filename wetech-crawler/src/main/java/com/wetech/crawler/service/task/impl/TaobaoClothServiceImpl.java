package com.wetech.crawler.service.task.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wetech.crawler.service.task.ITaobaoClothService;


@Service
public class TaobaoClothServiceImpl implements ITaobaoClothService {

	@Override
	public void getTaobaoClothBrandList() {
		
		//从首页获取淘宝服装商品清单
		List<String> brandHomeList = new ArrayList<String>();
		brandHomeList.add("https://shop33558160.taobao.com/"); //戎美
		brandHomeList.add("https://shop35650978.taobao.com/"); //茉莉雅集
		brandHomeList.add("https://shop143702550.taobao.com/"); //风家窄众女装

		
		
		
	}

	@Override
	public void getTaobaoClothDetail() {
		// TODO Auto-generated method stub
		
	}

}
