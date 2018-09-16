package com.ssc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;
import com.ssc.mapper.FileTransferMapper;
import com.ssc.service.FileTransferService;



public class FileTransferServiceImpl implements FileTransferService{

	private static Logger logger = Logger.getLogger(FileTransferServiceImpl.class);
	@Autowired
	FileTransferMapper fileTransferMapper;
	
	@Override
	public void updateFileDescriptionByID(Integer id, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		projectAttachmentBeanCustom.setId(id);
		logger.info("Updating DB File Description By ID... ...+== "+ id);
		fileTransferMapper.updateFileDescriptionByID(projectAttachmentBeanCustom);
	}
	
	@Override
	public void updateFileToObselete(Integer projectID, String filename, ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		projectAttachmentBeanCustom.setProjectId(projectID);
		projectAttachmentBeanCustom.setFileName(filename.toUpperCase());
		logger.info("Updating DB File To Obselete... ...+== "+ projectID+" : "+filename);
		fileTransferMapper.updateFileToObselete(projectAttachmentBeanCustom);
	}
	@Override
	public void deleteAttachmentByID(List<Integer> idList,ProjectAttachmentVo projectAttachmentVo) throws Exception {
		projectAttachmentVo.setIdList(idList);
		logger.info("Deleting Attachment By IDList... ...+== "+ idList.toString());
		fileTransferMapper.deleteAttachmentByID(projectAttachmentVo);
	}

	@Override
	public void insertFileToDB(ProjectAttachmentBeanCustom projectAttachmentBeanCustom) throws Exception {
		logger.info("Inserting File To DB......+== ");
		fileTransferMapper.insertFileToDB(projectAttachmentBeanCustom);
	}

	
}
