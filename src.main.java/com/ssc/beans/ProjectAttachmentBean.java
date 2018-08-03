package com.ssc.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class ProjectAttachmentBean implements Serializable{
	/**
	 * 
	 */
	private int id;
	private int projectId;
	private String fileName;
	private String filePath;
	private Date createTime;
	private String fileDesc;
	private String isOldFile;
	private String userNm;
	private String projectName;
	private String projectItemDesc;
	private Date uploadTime;
	
	
	
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public void setIsOldFile(String isOldFile) {
		this.isOldFile = isOldFile;
	}
	public String getIsOldFile() {
		return isOldFile;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectItemDesc(String projectItemDesc) {
		this.projectItemDesc = projectItemDesc;
	}
	public String getProjectItemDesc() {
		return projectItemDesc;
	}
	
}
