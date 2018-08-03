package com.ssc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.mapper.StatusMapper;
import com.ssc.service.StatusUpdateService;

public class StatusUpdateServiceImpl implements StatusUpdateService{


	@Autowired
	StatusMapper statusMapper;
	
	
	@Override
	public StatusBeanCustom getStatusByID(Integer id) throws Exception {
		StatusBean statusBean = statusMapper.getStatusByID(id);
		if(statusBean == null ){
			throw new CustomException("No Record Exist!!!");
		}
		StatusBeanCustom statusBeanCustom = null;
		if(statusBean != null){
			statusBeanCustom = new StatusBeanCustom();
			BeanUtils.copyProperties(statusBean, statusBeanCustom);
		}
		
		return statusBeanCustom;
	}


	@Override
	public void updateStatusRecordByID(Integer id, StatusBeanCustom statusBeanCustom) throws Exception {

		if(id == null){
			throw new CustomException("ID is Null !!!");
		}
		statusBeanCustom.setId(id);
		statusMapper.updateStatusRecordByID(statusBeanCustom);
		
	}


	@Override
	public void insertStatusRecord(StatusBeanCustom statusBeanCustom) throws Exception {
		statusMapper.insertStatusRecord(statusBeanCustom);
	}


	@Override
	public void updateStatusProdDate(String projectName, String prodDate,String prodDateOrigin) throws Exception {
		ProjectCommentBeanCustom projectCommentBeanCustom = new ProjectCommentBeanCustom();
		projectCommentBeanCustom.setProjectName(projectName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
		projectCommentBeanCustom.setProdDateOrigin(sdf.parse(prodDateOrigin));
		projectCommentBeanCustom.setUpdateTime(new Date());
		
		statusMapper.updateStatusProdDate(projectCommentBeanCustom);
	}




//	@Override
//	public void updateStatusByID(Integer id, StatusBeanCustom statusBeanCustom) throws Exception {
//		if(id == null){
//			throw new Exception("ID is Null !!!");
//		}
//		statusBeanCustom.setId(id);
//		statusMapper.updateStatusByID(statusBeanCustom);
//		
//	}
	

}
