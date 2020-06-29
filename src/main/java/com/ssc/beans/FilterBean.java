package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class FilterBean implements Serializable{
	private String owner;
	private Date prodDate;
//	private Date prodDateOrigin;
//	private Date prodDateNew;
	private String projectName;
	private String isDelete;
	private String isClose;
	private String isOnlyShowLatestInfo;
	private String isReverse;
	private String[] isJobStatus;
	private String hideOrder;
	private String hideLeader;
	private String hideOwner;
	private String hideStatusInfo;
	private String hideDocument;
	private String hideProdDate;
	private String hideActivity;
	private String hidePercent;
	private String hideJobStatus;
	private String hideComment;
	private String hideCopyItem;
	private String isFilter;
//	private String isNewInsert; //  contorl Status Infor Column set font blue;
	private Map<String,String> filterStatus;
	
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getProdDate() {
		return prodDate;
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
//	public Date getProdDateOrigin() {
//		return prodDateOrigin;
//	}
//	public void setProdDateOrigin(Date prodDateOrigin) {
//		this.prodDateOrigin = prodDateOrigin;
//	}
//	public Date getProdDateNew() {
//		return prodDateNew;
//	}
//	public void setProdDateNew(Date prodDateNew) {
//		this.prodDateNew = prodDateNew;
//	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public String getIsOnlyShowLatestInfo() {
		return isOnlyShowLatestInfo;
	}
	public void setIsOnlyShowLatestInfo(String isOnlyShowLatInfo) {
		this.isOnlyShowLatestInfo = isOnlyShowLatInfo;
	}
	
	public String getIsReverse() {
		return isReverse;
	}
	public void setIsReverse(String isReverse) {
		this.isReverse = isReverse;
	}

	public String[] getIsJobStatus() {
		return isJobStatus;
	}
	public void setIsJobStatus(String[] isJobStatus) {
		this.isJobStatus = isJobStatus;
	}
	public String getHideOrder() {
		return hideOrder;
	}
	public void setHideOrder(String hideOrder) {
		this.hideOrder = hideOrder;
	}
	public String getHideLeader() {
		return hideLeader;
	}
	public void setHideLeader(String hideLeader) {
		this.hideLeader = hideLeader;
	}
	public String getHideOwner() {
		return hideOwner;
	}
	public void setHideOwner(String hideOwner) {
		this.hideOwner = hideOwner;
	}
	public String getHideStatusInfo() {
		return hideStatusInfo;
	}
	public void setHideStatusInfo(String hideStatusInfo) {
		this.hideStatusInfo = hideStatusInfo;
	}
	public String getHideDocument() {
		return hideDocument;
	}
	public void setHideDocument(String hideDocument) {
		this.hideDocument = hideDocument;
	}
	public String getHideProdDate() {
		return hideProdDate;
	}
	public void setHideProdDate(String hideProdDate) {
		this.hideProdDate = hideProdDate;
	}
	public String getHideActivity() {
		return hideActivity;
	}
	public void setHideActivity(String hideActivity) {
		this.hideActivity = hideActivity;
	}
	public String getHidePercent() {
		return hidePercent;
	}
	public void setHidePercent(String hidePercent) {
		this.hidePercent = hidePercent;
	}
	public String getHideJobStatus() {
		return hideJobStatus;
	}
	public void setHideJobStatus(String hideJobStatus) {
		this.hideJobStatus = hideJobStatus;
	}
	public String getHideComment() {
		return hideComment;
	}
	public void setHideComment(String hideComment) {
		this.hideComment = hideComment;
	}
	public String getHideCopyItem() {
		return hideCopyItem;
	}
	public void setHideCopyItem(String hideCopyItem) {
		this.hideCopyItem = hideCopyItem;
	}
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
//	public String getIsNewInsert() {
//		return isNewInsert;
//	}
//	public void setIsNewInsert(String isNewInsert) {
//		this.isNewInsert = isNewInsert;
//	}
	public Map<String, String> getFilterStatus() {
		return filterStatus;
	}
	public void setFilterStatus(Map<String, String> filterStatus) {
		this.filterStatus = filterStatus;
	}

	
	
	
	
	
	
	

}
