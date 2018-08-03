package com.ssc.service;

import java.util.List;
import com.ssc.beans.NaviBeanCustom;
import com.ssc.beans.UserQueryVo;

public interface NaviService {

	public List<NaviBeanCustom> getAllProdDateByGroupID(Integer grouID) throws Exception;
	public List<NaviBeanCustom> getAllProjectByGroupID(Integer grouID) throws Exception;
	public List<NaviBeanCustom> getAllProjectCountByGroupID(Integer grouID) throws Exception;
	public List<String> getAllOwnerGroupID(Integer grouID) throws Exception;
	
	
}
