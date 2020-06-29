package com.ssc.beans;

import java.io.Serializable;
import java.util.Date;

public class ProjectCommentBean implements Serializable{

	
	    private String projectName;

	    private Date prodDate;

	    private String projectComments;

	    
	    public String getProjectComments() {
	        return projectComments;
	    }

	    public void setProjectComments(String projectComment) {
	        this.projectComments = projectComment == null ? null : projectComment.trim();
	    }
	    
	    public String getProjectName() {
	        return projectName;
	    }
		public void setProjectName(String projectName) {
	        this.projectName = projectName == null ? null : projectName.trim();
	    }

	    public Date getProdDate() {
	        return prodDate;
	    }

	    public void setProdDate(Date prodDate) {
	        this.prodDate = prodDate;
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
