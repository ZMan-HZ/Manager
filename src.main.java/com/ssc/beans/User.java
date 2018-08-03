package com.ssc.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.statestr.util.StringUtil;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String role;
	private int groupId;
	private String otherConfig;
	private String longName;
	private String shortName;
	private boolean isUpdatable;
	private Map<String, String> memoryConfig = new HashMap<String, String>();
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGroupId() {
		return groupId;
	}
	
	public void setOtherConfig(String otherConfig) {
		this.otherConfig = otherConfig;
		
		if(StringUtil.isEmpty(otherConfig)==false) {
			String[] data = otherConfig.split("@");
			for(String keyPair:data) {
				if(keyPair!=null && keyPair.trim().length()>1) {
					String[] temp = keyPair.split(":");
					memoryConfig.put(temp[0], temp[1]);
				}
			}
		}
	}
	
	public String getOtherConfig() {
		return this.otherConfig;
	}
	
	public Map<String, String> getMemoryConfig() {
		return this.memoryConfig;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getLongName() {
		return longName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setUpdatable(boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}
	public boolean isUpdatable() {
		return isUpdatable;
	}
}
