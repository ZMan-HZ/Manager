package com.ssc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.UserCustom;
import com.ssc.mapper.LoginMapper;
import com.ssc.service.LoginService;



public class LoginServiceImpl implements LoginService{

	private static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	@Autowired
	private LoginMapper loginMapper;
	
	
	@Override
	public UserCustom loginWithUserName(String userName) throws Exception {
		logger.info("Fetching User Validation From DB... ...+== "+ userName);
		return loginMapper.loginWithUserName(userName);
	}

}
