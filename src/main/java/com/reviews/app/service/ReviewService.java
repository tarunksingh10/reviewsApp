package com.reviews.app.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.reviews.app.models.TestingDisplay;

import lombok.extern.slf4j.Slf4j;

@ManagedBean(name = "reviewService")
@ViewScoped
@Slf4j
public class ReviewService {

	@Value("${review.backend.url}")
	private String getOutputTableListUrl;

	@Autowired
	private RestTemplate rest = new RestTemplate();

	public List<TestingDisplay> getOutputTableList() {
		final String METHOD_NAME = "getOutputTableList";
		log.info("ENTRY " + METHOD_NAME);
		List<TestingDisplay> getOutputTableList = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			log.info("Method Name is  :" + METHOD_NAME + " url :" + "http://nipsilon.us-east-2.elasticbeanstalk.com");
			headers.add("Authorization", "Basic bWFudGhhbjpHKlpLQWd7OHZUY0h5N1Jz");
			HttpEntity<?> httpentity = new HttpEntity<>(headers);
			ResponseEntity<TestingDisplay[]> apiResponse = rest.exchange(
					"http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry", HttpMethod.GET, httpentity,
					TestingDisplay[].class);
			TestingDisplay[] responsebody = apiResponse.getBody();
			getOutputTableList = Arrays.asList(responsebody);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to fetch  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);
		return getOutputTableList;
	}

	public Map<String, Integer> getDataForMap() {

		Map<String, Integer> positiveMap = new HashMap<String, Integer>();
		Map<String, Integer> negativeMap = new HashMap<String, Integer>();
		List<TestingDisplay> list = getOutputTableList();
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		int negative = 0;
		int positive = 0;

		/*
		 * for (TestingDisplay outputTable : list) { String l0 =
		 * outputTable.getL0();
		 * 
		 * if (StringUtils.equalsIgnoreCase("Positive",
		 * outputTable.getSentiment_text())) { positive = positive + 1;
		 * 
		 * if (positiveMap.containsKey(l0)) { int count = positiveMap.get(l0) +
		 * 1; positiveMap.put(l0, count);
		 * 
		 * } else { positiveMap.put(l0, 1); }
		 * 
		 * } else {
		 * 
		 * negative = negative + 1; if (negativeMap.containsKey(l0)) { int count
		 * = negativeMap.get(l0) + 1; negativeMap.put(l0, count); } else {
		 * negativeMap.put(l0, 1); } } }
		 */

		dataMap.put("negative", negative);
		dataMap.put("positive", positive);
		return dataMap;

	}

	public void updateOutputTableEntry(TestingDisplay selectedOutputTableEntry) {
		final String METHOD_NAME = "updateOutputTableEntry";
		log.info("ENTRY " + METHOD_NAME);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic bWFudGhhbjpHKlpLQWd7OHZUY0h5N1Jz");
			HttpEntity<TestingDisplay> httpentity = new HttpEntity<>(selectedOutputTableEntry, headers);
			log.info("Method Name :" + METHOD_NAME + " url :"
					+ "http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry");
			rest.put("http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry/"
					+ selectedOutputTableEntry.getId(), httpentity);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to update  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);

	}

	public List<TestingDisplay> getFilteredOutputTable(String city, String hotel, String source, Date reviewFromDate,
			Date reviewToDate) {
		final String METHOD_NAME = "getFilteredOutputTable";
		log.info("ENTRY " + METHOD_NAME);
		List<TestingDisplay> getOutputTableList = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			log.info("Method Name is  :" + METHOD_NAME + " url :" + "http://nipsilon.us-east-2.elasticbeanstalk.com");
			headers.add("Authorization", "Basic bWFudGhhbjpHKlpLQWd7OHZUY0h5N1Jz");
			HttpEntity<?> httpentity = new HttpEntity<>(headers);
			ResponseEntity<TestingDisplay[]> apiResponse = rest.exchange(
					"http://nipsilon.us-east-2.elasticbeanstalk.com/review/getFilteredOutputTable?city=" + city + "&hotel=" + hotel + "&source="
							+ source + "&fromDate=" + reviewFromDate + "&toDate=" + reviewToDate,
					HttpMethod.GET, httpentity, TestingDisplay[].class);
			TestingDisplay[] responsebody = apiResponse.getBody();
			getOutputTableList = Arrays.asList(responsebody);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to fetch  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);
		return getOutputTableList;
	}
}
