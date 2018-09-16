package com.ssc.service;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;



@Cacheable(value="fileBrowserServiceImpl",keyGenerator="cacheKeyGenerator")
public interface FileBrowserService {

	public List<ProjectAttachmentBeanCustom> fetchAllFilesByMultiParam(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> fetchFilesByProjectID(Integer projectStatusId)  throws Exception;
	
	public ProjectAttachmentBeanCustom fetchFileByID(Integer id) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> fetchFileListByIDList(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> fetchAllFilesForSpecifiedProject(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	
}
