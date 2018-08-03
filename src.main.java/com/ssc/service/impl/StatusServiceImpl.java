package com.ssc.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.mapper.StatusMapper;
import com.ssc.service.StatusService;


public class StatusServiceImpl  implements StatusService{

	@Autowired
	private StatusMapper statusMapper;
	
	
	@Override
	public List<StatusBeanCustom> getProjectNames() throws Exception {
		return statusMapper.getProjectNames();
	}

	@Override
	public List<StatusBasicBeanCustom> getProjectStausByGroupID(Integer groupID) throws Exception {
		return statusMapper.getProjectStausByGroupID(groupID);
	}

	@Override
	public List<StatusBeanCustom> getStatusByMultiParam(StatusBeanVo statusBeanVo) throws Exception {
		return statusMapper.getStatusByMultiParam(statusBeanVo);
	}
	@Override
	public StatusBeanCustom getStatusByID(Integer id) throws Exception {
		
		if(id == null){
			throw new CustomException("Please select a item!!!");
		}
		
		StatusBean statusBean = statusMapper.getStatusByID(id);
		if(statusBean == null ){
			throw new CustomException("Record is not Exist!!!");
		}
		StatusBeanCustom statusBeanCustom = null;
		if(statusBean != null){
			statusBeanCustom = new StatusBeanCustom();
			BeanUtils.copyProperties(statusBean, statusBeanCustom);
		}
		return statusBeanCustom;
	}

	@Override
	public List<StatusBeanCustom> getStatusByIDList(StatusBeanVo statusBeanVo) throws Exception {
		if(statusBeanVo.getIdList().isEmpty()){
			throw new CustomException("Please select at least ONE item!!!");
		}
		return statusMapper.getStatusByIDList(statusBeanVo);
	}
	
	@Override
	public void updateUserByName(String userName,UserCustom userCustom) throws Exception {
		if(userName == null){
			throw new CustomException("can not Update DB, because username is not login!!!");
		}
		userCustom.setUserName(userName);
		statusMapper.updateUserByName(userCustom);
	}

	
	
}
	
