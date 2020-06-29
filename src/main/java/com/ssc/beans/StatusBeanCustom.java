package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  @Description: 扩展类 
 * @author e6332s29
 *
 */
public class StatusBeanCustom  extends StatusBean implements Serializable{

	/**
	 * 
	 */
	private List<String> statusInforList;
	private List<String> testCaseList;
	private String reviewedTag;
	private String fileName;
	private String description;
	private String startDate;
//	private String prodDateCustom;
    private String changeType;
//    private String itemDescwithoutChangeType;
//    
//	
//	public String getItemDescwithoutChangeType() {
//		return itemDescwithoutChangeType;
//	}
//	public void setItemDescwithoutChangeType(String itemDescwithoutChangeType) {
//		this.itemDescwithoutChangeType = itemDescwithoutChangeType;
//	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
//	public String getProdDateCustom() {
//		return prodDateCustom;
//	}
//	public void setProdDateCustom(String prodDateCustom) {
//		this.prodDateCustom = prodDateCustom;
//	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReviewedTag() {
		return reviewedTag;
	}

	public void setReviewedTag(String reviewedTag) {
		this.reviewedTag = reviewedTag;
	}

	public List<String> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(List<String> testCaseList) {
		this.testCaseList = testCaseList;
	}

	public List<String> getStatusInforList() {
		return statusInforList;
	}

	public void setStatusInforList(List<String> statusInforList) {
		this.statusInforList = statusInforList;
	}
	
	
	
}
