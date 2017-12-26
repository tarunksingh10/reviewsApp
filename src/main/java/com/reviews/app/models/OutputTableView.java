package com.reviews.app.models;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import lombok.Getter;
import lombok.Setter;

import com.reviews.app.service.ReviewService;

@ManagedBean(name = "outputTableView")
@ApplicationScoped
public class OutputTableView implements Serializable {

	/**
	 * 
	 */
	
	public OutputTableView() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private List<OutputTable> outputTableList;
	@Getter
	@Setter
	private OutputTable selectedOutputTableEntry;
	@Getter
	@Setter
	private List<OutputTable> selectedOutputTableEntries;

	@Getter
	@Setter
	@ManagedProperty("#{reviewService}")
	private ReviewService reviewService;

	@PostConstruct
	public void init() {
		outputTableList = reviewService.getOutputTableList();
	}
	


}
