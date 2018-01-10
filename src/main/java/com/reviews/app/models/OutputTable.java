package com.reviews.app.models;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OutputTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String category;
	private String city;
	private String sentiment_y;
	private String sentiment_x;
	private String verbatim;
	private String sentence;
	private String source;
	private String sentence_id;
	private String l2;
	private String l3;
	private String l0;
	private String l1;
	private Date date;
	private String property;
	private String unique_id;
	private String feedback;
	private boolean correctCategory;
	
	private boolean editable;
	
	public OutputTable(){}
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String setenceShort;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String reviewShort;

	/**
	 * @param category
	 * @param city
	 * @param sentiment_y
	 * @param sentiment_x
	 * @param verbatim
	 * @param sentence
	 * @param source
	 * @param sentence_id
	 * @param l2
	 * @param l3
	 * @param l0
	 * @param l1
	 * @param date
	 * @param property
	 * @param unique_id
	 */
	public OutputTable(String category, String city, String sentiment_y, String sentiment_x, String verbatim,
			String sentence, String source, String sentence_id, String l2, String l3, String l0, String l1,
			java.util.Date date, String property, String unique_id,String setenceShort,String reviewShort) {
		super();
		this.category = category;
		city = city;
		sentiment_y = sentiment_y;
		sentiment_x = sentiment_x;
		verbatim = verbatim;
		sentence = sentence;
		source = source;
		this.sentence_id = sentence_id;
		l2 = l2;
		l3 = l3;
		l0 = l0;
		l1 = l1;
		date = date;
		property = property;
		unique_id = unique_id;
		this.setenceShort=setenceShort;
		this.reviewShort=reviewShort;
	}
	
	
	
	public String getSetenceShort() {
		
		String setenceShort  = !StringUtils.isEmpty(this.sentence) ? StringUtils.substring(this.sentence,0,50).concat("...") : this.sentence;
		
		
		return setenceShort=setenceShort;
	}



	public void setSetenceShort(String setenceShort) {
		this.setenceShort = setenceShort;
	}



	public String getReviewShort() {
		
		String reviewShort  = !StringUtils.isEmpty(this.verbatim) ? StringUtils.substring(this.verbatim,0,50).concat("...") : this.verbatim;
		
		
		return reviewShort=reviewShort;
	}



	public void setReviewShort(String reviewShort) {
		this.reviewShort = reviewShort;
	}

	
}
