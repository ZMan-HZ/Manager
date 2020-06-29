package com.ssc.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;




@Transactional(rollbackFor=Exception.class)
@CacheEvict(value={"statusServiceImpl"},allEntries=true)
public interface StatusUpdateService {

	
//	public StatusBeanCustom getStatusByID(Integer id) throws Exception;
	
	public void updateStatusRecordByID(Integer id,StatusBeanCustom statusBeanCustom) throws Exception;
	
	public void updateStatusProdDate(String projectName, String prodDate,String prodDateOrigin) throws Exception;
	
	public void insertStatusRecord(StatusBeanCustom statusBeanCustom) throws Exception;
	
	public void updateUserByName(String userName,UserCustom userCustom) throws Exception;

	
}
