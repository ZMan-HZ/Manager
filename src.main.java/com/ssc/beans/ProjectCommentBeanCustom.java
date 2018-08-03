package com.ssc.beans;

import java.util.Date;

public class ProjectCommentBeanCustom  extends ProjectCommentBean {

	
	private Date prodDateOrigin;

	private Date updateTime;
	
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getProdDateOrigin() {
		return prodDateOrigin;
	}

	public void setProdDateOrigin(Date prodDateOrigin) {
		this.prodDateOrigin = prodDateOrigin;
	}
	
    
	
	
	
}
