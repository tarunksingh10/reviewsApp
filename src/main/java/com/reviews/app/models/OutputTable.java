package com.reviews.app.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OutputTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category;
	private String City;
	private String Sentiment_y;
	private String Sentiment_x;
	private String Verbatim;
	private String Sentence;
	private String Source;
	private String sentence_id;
	private String L2;
	private String L3;
	private String L0;
	private String L1;
	private Date Date;
	private String Property;
	private String Unique_id;
	
	public OutputTable(){}

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
			java.util.Date date, String property, String unique_id) {
		super();
		this.category = category;
		City = city;
		Sentiment_y = sentiment_y;
		Sentiment_x = sentiment_x;
		Verbatim = verbatim;
		Sentence = sentence;
		Source = source;
		this.sentence_id = sentence_id;
		L2 = l2;
		L3 = l3;
		L0 = l0;
		L1 = l1;
		Date = date;
		Property = property;
		Unique_id = unique_id;
	}
	
	

}
