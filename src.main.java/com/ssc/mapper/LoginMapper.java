package com.ssc.mapper;

import com.ssc.beans.UserCustom;
import com.ssc.beans.UserQueryVo;

public interface LoginMapper {

	public UserCustom loginWithUserName(String userName) throws Exception;
	
	
}
