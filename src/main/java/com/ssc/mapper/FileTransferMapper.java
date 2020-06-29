package com.ssc.mapper;

import java.util.List;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;

public interface FileTransferMapper {

	
	
	public List<ProjectAttachmentBeanCustom> getAllFilesByMultiParam(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getFilesByProjectID(Integer projectStatusId)  throws Exception;
	
	public ProjectAttachmentBeanCustom getFileByID(Integer id) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getFileListByIDList(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public List<ProjectAttachmentBeanCustom> getAllFilesForSpecifiedProject(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	public void updateFileDescriptionByID(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void insertFileToDB(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void updateFileToObselete(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception;
	
	public void deleteAttachmentByID(ProjectAttachmentVo projectAttachmentVo) throws Exception;
	
	
}
