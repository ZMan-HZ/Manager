package com.ssc.service;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;



@Transactional(rollbackFor=Exception.class)
@CacheEvict(value={"fileBrowserServiceImpl"}, allEntries=true,beforeInvocation=true)
public interface FileTransferService {

	
	public void insertFileToDB(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void updateFileDescriptionByID(Integer id, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void updateFileToObselete(Integer projectID,String filename,ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void deleteAttachmentByID(List<Integer> idList,ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
}
