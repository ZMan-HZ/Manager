package com.ssc.mapper;

import java.util.List;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.beans.User;
import com.ssc.beans.UserCustom;

public interface StatusMapper {

	
	public StatusBean getStatusByID(Integer id) throws Exception;
	public List<StatusBeanCustom> getStatusByIDList(StatusBeanVo statusBeanVo) throws Exception;
	
	public List<StatusBean> getStatusByJobStatus(StatusBeanVo statusBeanVo) throws Exception;
	public List<User> getAllUsers() throws Exception;
	
//	public List<ProjectAttachmentBean> getFileByID(Integer id) throws Exception;
	
	public List<StatusBeanCustom> getStatusByMultiParam(StatusBeanVo statusBeanVo) throws Exception;
	
	public List<StatusBeanCustom> getProjectNames() throws Exception;
	public List<StatusBasicBeanCustom> getProjectStatusByGroupID(Integer groupID) throws Exception;
	
	public void updateUserByName(UserCustom userCustom) throws Exception;
	
	public void insertStatusRecord(StatusBeanCustom statusBeanCustom) throws Exception;
	
	public void updateStatusRecordByID(StatusBeanCustom statusBeanCustom) throws Exception;
	
	public void updateStatusProdDate(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	

	
	
}
