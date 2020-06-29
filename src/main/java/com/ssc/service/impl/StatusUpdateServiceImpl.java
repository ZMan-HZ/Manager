package com.ssc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.mapper.StatusMapper;
import com.ssc.service.StatusUpdateService;





public class StatusUpdateServiceImpl implements StatusUpdateService{

	private static Logger logger = Logger.getLogger(StatusUpdateServiceImpl.class);
	@Autowired
	StatusMapper statusMapper;
	
	
//	@Cacheable(value="getStatusByID")
//	public StatusBeanCustom getStatusByID(Integer id) throws Exception {
//		StatusBean statusBean = statusMapper.getStatusByID(id);
//		if(statusBean == null ){
//			throw new CustomException("No Record Exist!!!");
//		}
//		StatusBeanCustom statusBeanCustom = null;
//		if(statusBean != null){
//			statusBeanCustom = new StatusBeanCustom();
//			BeanUtils.copyProperties(statusBean, statusBeanCustom);
//		}
//		
//		return statusBeanCustom;
//	}
	@Override
	public void updateUserByName(String userName,UserCustom userCustom) throws Exception {
		if(userName == null){
			throw new CustomException("can not Update DB, because username is not login!!!");
		}
		userCustom.setUserName(userName);
		logger.info("Updating DB User By Name... ...+== "+ userName);
		statusMapper.updateUserByName(userCustom);
	}

	@Override
	public void updateStatusRecordByID(Integer id, StatusBeanCustom statusBeanCustom) throws Exception {

		if(id == null){
			throw new CustomException("ID is Null !!!");
		}
		statusBeanCustom.setId(id);
		logger.info("Updating DB Status Record By ID... ...+== "+ id);
		statusMapper.updateStatusRecordByID(statusBeanCustom);
		
	}


	@Override
	public void insertStatusRecord(StatusBeanCustom statusBeanCustom) throws Exception {
		logger.info("Inserting Status Record To DB......+== ");
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
		logger.info("Updating DB Status Prod Date... ...+== "+ projectName+" : "+ prodDateOrigin +"-->"+ prodDate);
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
