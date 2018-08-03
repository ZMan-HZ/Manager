package com.ssc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssc.beans.User;
import com.ssc.beans.UserCustom;
import com.ssc.beans.UserQueryVo;
import com.ssc.mapper.LoginMapper;
import com.ssc.service.LoginService;



public class LoginServiceImpl implements LoginService{

	
	@Autowired
	private LoginMapper loginMapper;
	
	
	@Override
	public UserCustom loginWithUserName(String userName) throws Exception {
		
		return loginMapper.loginWithUserName(userName);
	}

}
