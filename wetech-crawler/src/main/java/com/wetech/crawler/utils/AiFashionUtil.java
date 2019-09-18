package com.wetech.crawler.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.wetech.crawler.project.aifashion.AiFashionService;

/**
 * 
 * @author Levin
 *
 */
public class AiFashionUtil {
	private static final Logger log = LoggerFactory.getLogger(AiFashionService.class);
	

	private static String tagsAPITOKEN = null;
	private static String searchImageAPITOKEN = null;
	
	
	public static String getImageTags(String imageURL) {
		StringBuffer sbf = new StringBuffer();

		try {
			
			if( tagsAPITOKEN == null) {
				HttpResponse<JsonNode> response = Unirest.post("https://api.aifashion.com/oauth2/token")
						.header("accept", "application/json").header("content-type", "application/x-www-form-urlencoded")
						.body("grant_type=client_credentials&client_id=OFOoyOvTsdqw5LcH8eA72oSOADxWKYfG&client_secret=6FBGaOYPldrTjl6bVlmN2PcDodXvKPi3")
						.asJson();
				JsonNode jsonNode = response.getBody();
				tagsAPITOKEN = String.valueOf(jsonNode.getObject().get("access_token"));	
			}
			

			HttpResponse<JsonNode> response2 = Unirest.post("https://api.aifashion.com/fashion/tagging")
					.header("accept", "application/json").header("content-type", "application/json")
					.header("authorization", "Bearer " + tagsAPITOKEN)
					.body("{\"image_url\":\"" + imageURL + "\",\"dataset_name\":\"products\"}").asJson();

			JsonNode jsonNode2 = response2.getBody();
			String category = jsonNode2.getObject().getJSONObject("data").getJSONObject("object").getString("category");
			sbf.append("{");
			sbf.append("\"类别\":\"").append(category).append("\",");

			JSONArray jsonArray2 = jsonNode2.getObject().getJSONObject("data").getJSONArray("tags");
			for (int i = 0; i < jsonArray2.length(); i++) {
				String key = jsonArray2.getJSONObject(i).getString("key");
				sbf.append("\"").append(key).append("\":\"");
				String value = jsonArray2.getJSONObject(i).getJSONArray("values").getJSONObject(0).getString("value");
				sbf.append(value).append("\",");
			}
			sbf.append("}");

		} catch (UnirestException e) {
			log.error(imageURL+"=="+e);
		} catch (JSONException e) {
			log.error(imageURL+"=="+e);
		}
		String tags = sbf.toString().replace(",}", "}");
		return tags;
	}

}
