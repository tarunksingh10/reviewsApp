package com.reviews.app.service;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.reviews.app.models.OutputTable;

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
			OutputTable[] response =  rest.getForObject("http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry", OutputTable[].class);
			getOutputTableList = Arrays.asList(response);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to fetch  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);
		return getOutputTableList;
	}
	
	
	public void updateOutputTableEntry(OutputTable selectedOutputTableEntry){
		final String METHOD_NAME = "updateOutputTableEntry";
		log.info("ENTRY " + METHOD_NAME);
		try {
			log.info("Method Name :" + METHOD_NAME + " url :" + "http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry");
			 rest.put("http://nipsilon.us-east-2.elasticbeanstalk.com/review/outputTableEntry/"+selectedOutputTableEntry.getId(), selectedOutputTableEntry);
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to update  details. Error: " + e.getMessage());
			e.printStackTrace();
		}
		log.info("Exit " + METHOD_NAME);
		
	}
}
