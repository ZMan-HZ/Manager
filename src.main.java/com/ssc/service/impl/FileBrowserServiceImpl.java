package com.ssc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;
import com.ssc.mapper.FileTransferMapper;
import com.ssc.service.FileBrowserService;


public class FileBrowserServiceImpl implements FileBrowserService{

	private static Logger logger = Logger.getLogger(FileBrowserServiceImpl.class);
	@Autowired
	FileTransferMapper fileTransferMapper;
	

	@Override
	public List<ProjectAttachmentBeanCustom> fetchAllFilesByMultiParam(ProjectAttachmentVo projectAttachmentQueryVo)throws Exception {
		logger.info("Fetching All Files From DB... ...");
		return fileTransferMapper.getAllFilesByMultiParam(projectAttachmentQueryVo);
	}

	@Override
	public List<ProjectAttachmentBeanCustom> fetchFilesByProjectID(Integer projectStatusId)throws Exception {
		logger.info("Fetching Files By Project ID From DB... ...+== " + projectStatusId);
		return fileTransferMapper.getFilesByProjectID(projectStatusId);
	}

	@Override
	public ProjectAttachmentBeanCustom fetchFileByID(Integer id) throws Exception {
		logger.info("Fetching File By ID From DB... ...+== " + id);
		return fileTransferMapper.getFileByID(id);
	}


	@Override
	public List<ProjectAttachmentBeanCustom> fetchFileListByIDList(ProjectAttachmentVo projectAttachmentVo) throws Exception {
		logger.info("Fetching File List By IDList From DB... ...+== " + projectAttachmentVo.getIdList().toString());
		return fileTransferMapper.getFileListByIDList(projectAttachmentVo);
	}

	@Override
	public List<ProjectAttachmentBeanCustom> fetchAllFilesForSpecifiedProject(ProjectAttachmentVo projectAttachmentVo) throws Exception {
		logger.info("Fetching All Files For Specified Project from DB... ...+== " + projectAttachmentVo.getProjectName());
		return fileTransferMapper.getAllFilesForSpecifiedProject(projectAttachmentVo);
	}

	
}
