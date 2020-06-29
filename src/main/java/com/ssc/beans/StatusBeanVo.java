package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @Description 包装类型
 * @author e633229
 *
 */
public class StatusBeanVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private StatusBeanCustom statusBeanCustom;
	private List<Integer> idList;
	private List<String> jobStatusList;
	private int groupId;
	private String user; 
	private Date prodDate;
	private String projectName;
	private String isReverse;
	private String isDelete; 
	private String isClose;
	private String jobStatus;
	
	
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public List<String> getJobStatusList() {
		return jobStatusList;
	}
	public void setJobStatusList(List<String> jobStatusList) {
		this.jobStatusList = jobStatusList;
	}
	public String getIsClosed() {
		return isClose;
	}
	public String getIsReverse() {
		return isReverse;
	}
	public void setIsReverse(boolean isReverse) {
		if(isReverse){
			this.isReverse = "YES";
		}else{
			this.isReverse = "NO";
		}
	}
	public void setIsClosed(boolean isClosed) {
		if(isClosed){
			this.isClose = "Y";
		}else{
			this.isClose = "N";
		}
	}
	
	public void setIsDelete(boolean isDelete) {
		if(isDelete){
			this.isDelete = "Y";
		}else{
			this.isDelete = "N";
		}
	}
	public String getIsDelete() {
		return isDelete;
	}
	
	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
	public StatusBeanCustom getProjectNameCustom() {
		return statusBeanCustom;
	}

	public void setProjectNameCustom(StatusBeanCustom statusBeanCustom) {
		this.statusBeanCustom = statusBeanCustom;
	}
	
	public StatusBeanCustom getStatusBeanCustom() {
		return statusBeanCustom;
	}
	public void setStatusBeanCustom(StatusBeanCustom statusBeanCustom) {
		this.statusBeanCustom = statusBeanCustom;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getProdDate() {
		return prodDate;
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
