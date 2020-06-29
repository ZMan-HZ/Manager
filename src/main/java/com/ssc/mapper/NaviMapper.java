package com.ssc.mapper;

import java.util.List;
import com.ssc.beans.NaviBeanCustom;
import com.ssc.beans.User;
import com.ssc.beans.UserQueryVo;

public interface NaviMapper {

	public List<NaviBeanCustom> fetchAllProdDateByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllProjectByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllProjectCountByGroupID(Integer groupID) throws Exception;
	
	public List<NaviBeanCustom> fetchAllOwnerByGroupID(Integer groupID) throws Exception;

	
	
}
