package com.reviews.app.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.FileUploadEvent;

import com.reviews.app.service.ReviewService;

import lombok.Data;

@ManagedBean(name = "testingDisplayView")
@ViewScoped
@Data
public class TestingDisplayView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TestingDisplay> outputTableList;
	private TestingDisplay selectedOutputTableEntry;
	private List<TestingDisplay> selectedOutputTableEntries;

	private String hotel;
	private String city;
	private String source;
	private Date reviewFromDate;
	private Date reviewToDate;

	@ManagedProperty("#{reviewService}")
	private ReviewService reviewService;

	@PostConstruct
	public void init() {
		outputTableList = reviewService.getOutputTableList();
	}

	public String editAction(TestingDisplay selectedOutputTableEntry) {
		selectedOutputTableEntry.setEditable(true);
		return null;
	}

	public String saveAction(TestingDisplay selectedOutputTableEntry) {
		selectedOutputTableEntry.setEditable(false);
		reviewService.updateOutputTableEntry(selectedOutputTableEntry);
		return null;
	}

	public String searchReviews() {
		outputTableList = null;
		outputTableList = reviewService.getFilteredOutputTable(city, hotel, source, reviewFromDate, reviewToDate);
		return null;
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("data has been uploaded");
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
