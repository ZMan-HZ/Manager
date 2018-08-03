package com.ssc.service;

import java.util.List;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.beans.UserCustom;

public interface StatusService {

	
	public List<StatusBeanCustom> getProjectNames() throws Exception;
	public List<StatusBasicBeanCustom> getProjectStausByGroupID(Integer groupID) throws Exception;
	public List<StatusBeanCustom> getStatusByMultiParam(StatusBeanVo statusBeanVo) throws Exception;
	public StatusBeanCustom getStatusByID(Integer id) throws Exception;
	public List<StatusBeanCustom> getStatusByIDList(StatusBeanVo statusBeanVo) throws Exception;
	
	
	public void updateUserByName(String userName,UserCustom userCustom) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
}
