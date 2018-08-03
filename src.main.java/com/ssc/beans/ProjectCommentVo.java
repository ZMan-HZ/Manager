package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;

public class ProjectCommentVo implements Serializable{

	
	private ProjectCommentBeanCustom projectCommentBeanCustom;
	private String projectName;
//	private String prodDate;
	private Date prodDate;
	
	
	
	public ProjectCommentBeanCustom getProjectCommentBeanCustom() {
		return projectCommentBeanCustom;
	}
	public void setProjectCommentBeanCustom(
			ProjectCommentBeanCustom projectCommentBeanCustom) {
		this.projectCommentBeanCustom = projectCommentBeanCustom;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
//	public String getProdDate() {
//		return prodDate;
//	}
//	public void setProdDate(String prodDate) {
//		this.prodDate = prodDate;
//	}
	public Date getProdDate() {
		return prodDate;
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
	
	
	
	
	
	
}
