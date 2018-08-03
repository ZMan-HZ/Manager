package com.ssc.service;

import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;

public interface StatusUpdateService {

	
	public StatusBeanCustom getStatusByID(Integer id) throws Exception;
	
	public void updateStatusRecordByID(Integer id,StatusBeanCustom statusBeanCustom) throws Exception;
	
	public void updateStatusProdDate(String projectName, String prodDate,String prodDateOrigin) throws Exception;
	
	public void insertStatusRecord(StatusBeanCustom statusBeanCustom) throws Exception;
	

	
}
