package com.ssc.beans;

import java.util.List;

public class ProjectStatusVo {
	
	private ProjectStatusCustom projectStatusCustoml;
	private ProjectStatus projectStatus;
	private List<ProjectStatusCustom> projectStatusList ;
	
	
	public ProjectStatusCustom getProjectStatusCustoml() {
		return projectStatusCustoml;
	}
	public void setProjectStatusCustoml(ProjectStatusCustom projectStatusCustoml) {
		this.projectStatusCustoml = projectStatusCustoml;
	}
	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}
	public List<ProjectStatusCustom> getProjectStatusList() {
		return projectStatusList;
	}
	public void setProjectStatusList(List<ProjectStatusCustom> projectStatusList) {
		this.projectStatusList = projectStatusList;
	}
	
	
	

}
