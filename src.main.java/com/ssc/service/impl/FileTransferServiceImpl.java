package com.ssc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;
import com.ssc.mapper.FileTransferMapper;
import com.ssc.service.FileTransferService;

public class FileTransferServiceImpl implements FileTransferService{

	
	@Autowired
	FileTransferMapper fileTransferMapper;
	
	@Override
	public List<ProjectAttachmentBeanCustom> getAllFilesByMultiParam(ProjectAttachmentVo projectAttachmentQueryVo)throws Exception {
		return fileTransferMapper.getAllFilesByMultiParam(projectAttachmentQueryVo);
	}

	@Override
	public List<ProjectAttachmentBeanCustom> getFilesByProjectID(Integer projectStatusId)throws Exception {
		return fileTransferMapper.getFilesByProjectID(projectStatusId);
	}

	@Override
	public ProjectAttachmentBeanCustom getFileByID(Integer id) throws Exception {
		return fileTransferMapper.getFileByID(id);
	}

	@Override
	public void insertFileToDB(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		fileTransferMapper.insertFileToDB(projectAttachmentBeanCustom);
	}

	@Override
	public void updateFileDescriptionByID(Integer id, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		projectAttachmentBeanCustom.setId(id);
		fileTransferMapper.updateFileDescriptionByID(projectAttachmentBeanCustom);
	}

	@Override
	public void updateFileToObselete(Integer projectID, String filename, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		projectAttachmentBeanCustom.setProjectId(projectID);
		projectAttachmentBeanCustom.setFileName(filename.toUpperCase());
		fileTransferMapper.updateFileToObselete(projectAttachmentBeanCustom);
	}

	@Override
	public List<ProjectAttachmentBeanCustom> getAllFilesForSpecifiedProject(ProjectAttachmentVo projectAttachmentVo) throws Exception {
		return fileTransferMapper.getAllFilesForSpecifiedProject(projectAttachmentVo);
	}

	@Override
	public void deleteAttachmentByID(List<Integer> idList,ProjectAttachmentVo projectAttachmentVo) throws Exception {
		projectAttachmentVo.setIdList(idList);
		fileTransferMapper.deleteAttachmentByID(projectAttachmentVo);
	}

	@Override
	public List<ProjectAttachmentBeanCustom> getFileListByIDList(ProjectAttachmentVo projectAttachmentVo) throws Exception {
		return fileTransferMapper.getFileListByIDList(projectAttachmentVo);
	}

	
}
