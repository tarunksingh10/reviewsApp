package com.reviews.app.service;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.reviews.app.models.OutputTable;

import lombok.extern.slf4j.Slf4j;

@ManagedBean(name = "reviewService")
@ViewScoped
@Slf4j
public class ReviewService {

	@Value("${review.backend.url}")
	private String getOutputTableListUrl;

	@Autowired
	private RestTemplate rest = new RestTemplate();

	public List<OutputTable> getOutputTableList() {
		final String METHOD_NAME = "getOutputTableList";
		log.info("ENTRY " + METHOD_NAME);
		List<OutputTable> getOutputTableList = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			log.info("Method Name is  :" + METHOD_NAME + " url :" + "http://nipsilon.us-east-2.elasticbeanstalk.com");
			headers.add("Authorization", "Basic bWFudGhhbjpHKlpLQWd7OHZUY0h5N1Jz");
			HttpEntity<?> httpentity = new HttpEntity<>(headers);
			ResponseEntity<OutputTable[]> apiResponse = rest.exchange("http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry",
					HttpMethod.GET, httpentity, OutputTable[].class);
			OutputTable[] responsebody = apiResponse.getBody();
			getOutputTableList = Arrays.asList(responsebody);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to fetch  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);
		return getOutputTableList;
	}

	public void updateOutputTableEntry(OutputTable selectedOutputTableEntry) {
		final String METHOD_NAME = "updateOutputTableEntry";
		log.info("ENTRY " + METHOD_NAME);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic bWFudGhhbjpHKlpLQWd7OHZUY0h5N1Jz");
			HttpEntity<OutputTable> httpentity = new HttpEntity<>(selectedOutputTableEntry, headers);
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
}
