package com.ssc.service;

import java.util.List;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;

public interface FileTransferService {

	

	public List<ProjectAttachmentBeanCustom> getAllFilesByMultiParam(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getFilesByProjectID(Integer projectStatusId)  throws Exception;
	
	public ProjectAttachmentBeanCustom getFileByID(Integer id) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getFileListByIDList(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getAllFilesForSpecifiedProject(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public void insertFileToDB(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void updateFileDescriptionByID(Integer id, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void updateFileToObselete(Integer projectID,String filename,ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void deleteAttachmentByID(List<Integer> idList,ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
}
