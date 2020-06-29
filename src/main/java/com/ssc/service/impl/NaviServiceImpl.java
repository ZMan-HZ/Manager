package com.ssc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.NaviBeanCustom;
import com.ssc.mapper.NaviMapper;
import com.ssc.service.NaviService;


public class NaviServiceImpl implements NaviService{

	private static Logger logger = Logger.getLogger(NaviServiceImpl.class);
	@Autowired
	private NaviMapper naviMapper;

	@Override
	public List<NaviBeanCustom> fetchAllProdDateByGroupID(Integer groupID)	throws Exception {
		logger.info("Fetching All Productions Scheduled Date By GroupID From DB... ...+== "+ groupID);
		return naviMapper.fetchAllProdDateByGroupID(groupID);
	}

	@Override
	public List<NaviBeanCustom> fetchAllProjectByGroupID(Integer groupID) throws Exception {
		logger.info("Fetching All Project Names By GroupID From DB... ...+== "+ groupID);
		return naviMapper.fetchAllProjectByGroupID(groupID);
	}

	@Override
	public List<NaviBeanCustom> fetchAllProjectCountByGroupID(Integer groupID) throws Exception {
		logger.info("Fetching All Project Countings By GroupID From DB... ...+== "+ groupID);
		return naviMapper.fetchAllProjectCountByGroupID(groupID);
	}

	@Override
	public List<NaviBeanCustom> fetchAllOwnerByGroupID(Integer groupID) throws Exception {
		logger.info("Fetching All Owners By GroupID From DB... ...+== "+ groupID);
		return naviMapper.fetchAllOwnerByGroupID(groupID);
	}
	
	
	
}
