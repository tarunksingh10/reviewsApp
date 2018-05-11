package com.reviews.app.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestingDisplay implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String city;
	private String P1;
	private String P2;
	private String P3;
	private String P4;
	private String P5;
	@Getter(AccessLevel.NONE)
	private Date Review_Date;
	private String rating;
	private String review;
	private String Review_Daterating;
	private String hotel;
	private String source;
	private String N1;
	private String N2;
	private String N3;
	private String N4;
	private String N5;
	private String unique_id;
	private String feedback;
	private boolean correctCategory;
	private boolean editable;


	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String reviewShort;
	
	
	public String getReview_Date() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d = df.format(Review_Date);
		return d;
	}
	
	
	public String getReviewShort() {
		String reviewShort = !StringUtils.isEmpty(this.review) ? StringUtils.substring(this.review, 0, 50).concat("...")
				: this.review;
		return this.reviewShort = reviewShort;
	}

	public void setReviewShort(String reviewShort) {
		this.reviewShort = reviewShort;
	}

}
