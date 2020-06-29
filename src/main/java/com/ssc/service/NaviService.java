package com.ssc.service;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import com.ssc.beans.NaviBeanCustom;



@Cacheable(value="naviServiceImpl",key="#root.methodName")
public interface NaviService {

	public List<NaviBeanCustom> fetchAllProdDateByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllProjectByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllProjectCountByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllOwnerByGroupID(Integer groupID) throws Exception;
	
	
}
