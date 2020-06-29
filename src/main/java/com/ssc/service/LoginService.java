package com.ssc.service;

import com.ssc.beans.User;
import com.ssc.beans.UserCustom;
import com.ssc.beans.UserQueryVo;

public interface LoginService {
	
	public UserCustom loginWithUserName(String userName) throws Exception;
	
	
}
