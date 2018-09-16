package com.ssc.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.mapper.ProjectCommentMapper;
import com.ssc.service.ProjectCommentService;





public class ProjectCommentServiceImpl implements ProjectCommentService{

	private static Logger logger = Logger.getLogger(ProjectCommentServiceImpl.class);
	@Autowired
	ProjectCommentMapper projectCommentMapper;
	
//	@Override
//	public List<ProjectCommentBeanCustom> getCommentsListByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception {
//		return projectCommentMapper.getCommentsListByProjectNameAndDate(projectCommentVo);
//	}


	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public void updateMergeProjectComments(String projectName, String prodDate, ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception {
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
		logger.info("Updating and Merge Project Comments......+== "+ projectName+" : "+prodDate);
		projectCommentMapper.updateMergeProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void updateProjectComments(String projectName, String prodDate, ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception {
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
		logger.info("Updating Project Comments......+== "+ projectName+" : "+prodDate);
		projectCommentMapper.updateProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void insertProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom)throws Exception {
		logger.info("Inserting Project Comments To DB......");
		projectCommentMapper.insertProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void deleteProjectComments(String projectName, String prodDateOrigin) throws Exception {
		ProjectCommentBeanCustom projectCommentBeanCustom = new ProjectCommentBeanCustom();
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDateOrigin(sdf.parse(prodDateOrigin));
		logger.info("Deleting Project Comments From DB......");
		projectCommentMapper.deleteProjectComments(projectCommentBeanCustom);
	}

	
	
	
	
	
}
