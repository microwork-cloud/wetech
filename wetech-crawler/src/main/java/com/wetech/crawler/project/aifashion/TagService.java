package com.wetech.crawler.project.aifashion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;

@Service
public class TagService {
	private static final Logger log = LoggerFactory.getLogger(AiFashionService.class);

	@Autowired
	private WetechTaobaoClothMapper mapper;
	
	public List<WetechTaobaoCloth> getTopClothList(){
		return mapper.getTopClothList();
	}
 	
	public List<Tags> getBrandTags() {
		return mapper.getBrandTags();
	}

	public List<Tags> getPatternTags() {

		List<Tags> tagsList = new ArrayList<Tags>();

		//先测试3种
		String pattern1 = "风格";
		String pattern2 = "图案";
		String pattern3 = "领型";
		Map<String, Long> tagsMap1 = new HashMap<String, Long>();
		Map<String, Long> tagsMap2 = new HashMap<String, Long>();
		Map<String, Long> tagsMap3 = new HashMap<String, Long>();


		List<WetechTaobaoCloth> clothList = mapper.selectWetechTaobaoClothList(null);
		for (WetechTaobaoCloth wetechTaobaoCloth : clothList) {
			String clothTags = wetechTaobaoCloth.getClothTags();
			
			if (StringUtils.isNotEmpty(clothTags)) {
				JSONObject jsonObject = null;
				try {
					jsonObject = JSONObject.parseObject(clothTags);
				}catch(Exception e) {
					log.error(wetechTaobaoCloth.getClothId());
					continue;
				}
				String tagVaue1 = jsonObject.getString(pattern1);
				String tagVaue2 = jsonObject.getString(pattern2);
				String tagVaue3 = jsonObject.getString(pattern3);
				
				if (tagsMap1.containsKey(tagVaue1)) {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						long clothAmount = tagsMap1.get(tagVaue1) + wetechTaobaoCloth.getClothAmount();
						tagsMap1.put(tagVaue1, clothAmount);
					}
				} else {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						tagsMap1.put(tagVaue1, wetechTaobaoCloth.getClothAmount());
					} else {
						tagsMap1.put(tagVaue1, (long)1);
					}
				}
				
				if (tagsMap2.containsKey(tagVaue2)) {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						long clothAmount = tagsMap2.get(tagVaue2) + wetechTaobaoCloth.getClothAmount();
						tagsMap2.put(tagVaue2, clothAmount);
					}
				} else {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						tagsMap2.put(tagVaue2, wetechTaobaoCloth.getClothAmount());
					} else {
						tagsMap2.put(tagVaue2, (long)1);
					}
				}				
				if (tagsMap3.containsKey(tagVaue3)) {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						long clothAmount = tagsMap3.get(tagVaue3) + wetechTaobaoCloth.getClothAmount();
						tagsMap3.put(tagVaue3, clothAmount);
					}
				} else {
					if (wetechTaobaoCloth.getClothAmount() != null) {
						tagsMap3.put(tagVaue3, wetechTaobaoCloth.getClothAmount());
					} else {
						tagsMap3.put(tagVaue3, (long)1);
					}
				}				
				
			}
		}

		tagsMap1 = sortByValueDescending(tagsMap1);
		tagsMap2 = sortByValueDescending(tagsMap2);
		tagsMap3 = sortByValueDescending(tagsMap3);

		for (Map.Entry<String, Long> entry : tagsMap1.entrySet()) {
			Tags tag = new Tags();
			if( entry.getKey() != null) {
				tag.setTagName(pattern1);
				tag.setTagValue(entry.getKey());
				tag.setTagWeight(entry.getValue());
			}
			tagsList.add(tag);
		}
		for (Map.Entry<String, Long> entry : tagsMap2.entrySet()) {
			Tags tag = new Tags();
			if( entry.getKey() != null) {
				tag.setTagName(pattern2);
				tag.setTagValue(entry.getKey());
				tag.setTagWeight(entry.getValue());
			}
			tagsList.add(tag);

		}
		for (Map.Entry<String, Long> entry : tagsMap3.entrySet()) {
			Tags tag = new Tags();
			if( entry.getKey() != null) {
				tag.setTagName(pattern3);
				tag.setTagValue(entry.getKey());
				tag.setTagWeight(entry.getValue());
			}
			tagsList.add(tag);

		}
		return tagsList;
	}

	// 降序排序
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				int compare = (o1.getValue()).compareTo(o2.getValue());
				return -compare;
			}
		});
		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
