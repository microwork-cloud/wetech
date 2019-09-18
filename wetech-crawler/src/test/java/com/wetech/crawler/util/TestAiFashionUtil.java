package com.wetech.crawler.util;

import com.wetech.crawler.utils.AiFashionUtil;

public class TestAiFashionUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String imageURL = "https://img.alicdn.com/imgextra/i4/196993935/O1CN01LZGSa91ewH4g6Fuvr_!!0-item_pic.jpg";
		System.out.println(AiFashionUtil.getImageTags(imageURL));
	}

}
