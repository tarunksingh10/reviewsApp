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

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String category;
	private String city;
	private String sentiment;
	private String sentiment_text;
	private String rating;
	private String review;
	private String sentence;
	private String hotel;
	private String header;
	private String source;
	private String sentence_id;
	private String l2;
	private String l3;
	private String l0;
	private String l1;
	private Date date;
	private String unique_id;
	private String exec_Status;
	private String feedback;
	private boolean correctCategory;

	private boolean editable;

	public OutputTable() {
	}

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String setenceShort;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private String reviewShort;

	public String getSetenceShort() {
		String setenceShort = !StringUtils.isEmpty(this.sentence)
				? StringUtils.substring(this.sentence, 0, 50).concat("...") : this.sentence;
		return this.setenceShort = setenceShort;
	}

	public void setSetenceShort(String setenceShort) {
		this.setenceShort = setenceShort;
	}

	public String getReviewShort() {
		String reviewShort = !StringUtils.isEmpty(this.review) ? StringUtils.substring(this.review, 0, 50).concat("...")
				: this.review;
		return this.reviewShort = reviewShort;
	}

	public void setReviewShort(String reviewShort) {
		this.reviewShort = reviewShort;
	}

	/**
	 * @param id
	 * @param category
	 * @param city
	 * @param sentiment
	 * @param sentiment_text
	 * @param rating
	 * @param review
	 * @param sentence
	 * @param hotel
	 * @param header
	 * @param source
	 * @param sentence_id
	 * @param l2
	 * @param l3
	 * @param l0
	 * @param l1
	 * @param date
	 * @param unique_id
	 * @param exec_Status
	 * @param feedback
	 * @param correctCategory
	 * @param editable
	 * @param setenceShort
	 * @param reviewShort
	 */
	public OutputTable(String id, String category, String city, String sentiment, String sentiment_text, String rating,
			String review, String sentence, String hotel, String header, String source, String sentence_id, String l2,
			String l3, String l0, String l1, Date date, String unique_id, String exec_Status, String feedback,
			boolean correctCategory, boolean editable, String setenceShort, String reviewShort) {
		super();
		this.id = id;
		this.category = category;
		this.city = city;
		this.sentiment = sentiment;
		this.sentiment_text = sentiment_text;
		this.rating = rating;
		this.review = review;
		this.sentence = sentence;
		this.hotel = hotel;
		this.header = header;
		this.source = source;
		this.sentence_id = sentence_id;
		this.l2 = l2;
		this.l3 = l3;
		this.l0 = l0;
		this.l1 = l1;
		this.date = date;
		this.unique_id = unique_id;
		this.exec_Status = exec_Status;
		this.feedback = feedback;
		this.correctCategory = correctCategory;
		this.editable = editable;
		this.setenceShort = setenceShort;
		this.reviewShort = reviewShort;
	}

}
