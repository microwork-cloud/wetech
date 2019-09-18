package com.wetech.crawler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wetech.crawler.project.aifashion.TagService;
import com.wetech.crawler.project.taobao.service.IWetechTaobaoClothService;


@Controller
@RequestMapping("/cloth")
public class ClothController {
	private String prefix = "cloth";
	@Autowired
	private TagService service;

    

	@GetMapping("/trendslab-tmall")
	public String getJDClothData(Model model) {
		
		model.addAttribute("brandTags", service.getBrandTags());
		model.addAttribute("patternTags", service.getPatternTags());
		model.addAttribute("topClothList", service.getTopClothList());
		
		return prefix +"/trendslab-tmall";

	}
}
