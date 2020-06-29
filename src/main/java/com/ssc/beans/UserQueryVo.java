package com.ssc.beans;

import java.io.Serializable;

public class UserQueryVo implements Serializable{

	private UserCustom userCustom;
	private User user;
	
	
	public UserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
