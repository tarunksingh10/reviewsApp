package com.reviews.app.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.reviews.app.models.GetOutputTableResponse;
import com.reviews.app.models.OutputTable;

import lombok.extern.slf4j.Slf4j;

@ManagedBean(name = "reviewService")
@ApplicationScoped
@Slf4j
public class ReviewService {

	@Value("${review.backend.url}")
	private String getOutputTableListUrl;

	private RestTemplate rest;

	public List<OutputTable> getOutputTableList() {
		final String METHOD_NAME = "getOutputTableList";
		log.info("ENTRY " + METHOD_NAME);
		List<OutputTable> getOutputTableList = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			log.info("Method Name :" + METHOD_NAME + " url :" + getOutputTableListUrl);
			ResponseEntity<GetOutputTableResponse> response = rest.exchange(getOutputTableListUrl, HttpMethod.GET,
					new HttpEntity<>(headers), GetOutputTableResponse.class);
			getOutputTableList = response.getBody().getListOutputTable();
		} catch (Exception e) {
			log.error("Method Name :" + METHOD_NAME + " Unable to fetch  details. Error: " + e.getMessage());
		}
		log.info("Exit " + METHOD_NAME);
		return getOutputTableList;
	}
}
