package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;

public class NaviBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectName;
	private Date prodDate;
	private int count;
	private String job_status;
	private int resultNum;
	private int newOrder;
	
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public int getResultNum() {
		return resultNum;
	}
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}
	public int getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(int newOrder) {
		this.newOrder = newOrder;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getProdDate() {
		return prodDate;
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
	
	
}
