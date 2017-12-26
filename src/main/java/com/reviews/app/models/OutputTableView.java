package com.reviews.app.models;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.reviews.app.service.ReviewService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "outputTableView")
@ViewScoped
public class OutputTableView implements Serializable {

	/**
	 * 
	 */
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

	@ManagedProperty("#{reviewService}")
	private ReviewService reviewService;

	@PostConstruct
	public void init() {
		outputTableList = reviewService.getOutputTableList();
	}
	


}
