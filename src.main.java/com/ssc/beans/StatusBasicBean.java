package com.ssc.beans;

import java.io.Serializable;

public class StatusBasicBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectStatus; 
	private int	id ;
	private int	parent_id ;
	private int	next_id ;
	private int	group_id ;
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getID() {
		return id;
	}
	public void setID(int iD) {
		this.id = iD;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getNext_id() {
		return next_id;
	}
	public void setNext_id(int next_id) {
		this.next_id = next_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	
	
	
	
}
