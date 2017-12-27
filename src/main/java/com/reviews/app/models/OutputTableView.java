package com.reviews.app.models;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.reviews.app.service.ReviewService;

import lombok.Data;

@ManagedBean(name = "outputTableView")
@ViewScoped
@Data
public class OutputTableView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OutputTable> outputTableList;
	private OutputTable selectedOutputTableEntry;
	private List<OutputTable> selectedOutputTableEntries;

	@ManagedProperty("#{reviewService}")
	private ReviewService reviewService;

	@PostConstruct
	public void init() {
		outputTableList = reviewService.getOutputTableList();
	}

	public String editAction(OutputTable selectedOutputTableEntry) {
		selectedOutputTableEntry.setEditable(true);
		return null;
	}

	public String saveAction(OutputTable selectedOutputTableEntry) {
		selectedOutputTableEntry.setEditable(false);
		reviewService.updateOutputTableEntry(selectedOutputTableEntry);
		return null;
	}
}
