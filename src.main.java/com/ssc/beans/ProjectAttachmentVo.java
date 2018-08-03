package com.ssc.beans;

import java.io.Serializable;
import java.util.List;

public class ProjectAttachmentVo implements Serializable{

	/**
	 * 
	 */
	private ProjectAttachmentBeanCustom projectAttachmentBeanCustom;
	private ProjectAttachmentBean projectAttachmentBean;
	private String projectName;
	private String fileName;
	private String itemDesc;
	private String orderBy;
	private int projectId;
	private String isOldFile;
	private List<Integer> idList;
	private String orderByUploadTime;
	
	
	
	public String getOrderByUploadTime() {
		return orderByUploadTime;
	}
	public void setOrderByUploadTime(String orderByUploadTime) {
		this.orderByUploadTime = orderByUploadTime;
	}
	
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	public String getIsOldFile() {
		return isOldFile;
	}
	public void setIsOldFile(String isOldFile) {
		this.isOldFile = isOldFile;
	}
	public ProjectAttachmentBeanCustom getProjectAttachmentBeanCustom() {
		return projectAttachmentBeanCustom;
	}
	public void setProjectAttachmentBeanCustom(
			ProjectAttachmentBeanCustom projectAttachmentBeanCustom) {
		this.projectAttachmentBeanCustom = projectAttachmentBeanCustom;
	}
	public ProjectAttachmentBean getProjectAttachmentBean() {
		return projectAttachmentBean;
	}
	public void setProjectAttachmentBean(ProjectAttachmentBean projectAttachmentBean) {
		this.projectAttachmentBean = projectAttachmentBean;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	
	
	
}
