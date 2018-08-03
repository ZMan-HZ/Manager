package com.ssc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssc.beans.NaviBeanCustom;
import com.ssc.beans.UserQueryVo;
import com.ssc.mapper.NaviMapper;
import com.ssc.service.NaviService;

public class NaviServiceImpl implements NaviService{

	@Autowired
	private NaviMapper naviMapper;

	@Override
	public List<NaviBeanCustom> getAllProdDateByGroupID(Integer grouID)	throws Exception {
		return naviMapper.getAllProdDateByGroupID(grouID);
	}

	@Override
	public List<NaviBeanCustom> getAllProjectByGroupID(Integer grouID) throws Exception {
		return naviMapper.getAllProjectByGroupID(grouID);
	}

	@Override
	public List<NaviBeanCustom> getAllProjectCountByGroupID(Integer grouID) throws Exception {
		return naviMapper.getAllProjectCountByGroupID(grouID);
	}

	@Override
	public List<String> getAllOwnerGroupID(Integer grouID) throws Exception {
		return naviMapper.getAllOwnerGroupID(grouID);
	}
	
	
	
}
