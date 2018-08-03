package com.ssc.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.mapper.ProjectCommentMapper;
import com.ssc.service.ProjectCommentService;

public class ProjectCommentServiceImpl implements ProjectCommentService{

	
	@Autowired
	ProjectCommentMapper projectCommentMapper;
	
//	@Override
//	public List<ProjectCommentBeanCustom> getCommentsListByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception {
//		return projectCommentMapper.getCommentsListByProjectNameAndDate(projectCommentVo);
//	}

	@Override
	public ProjectCommentBeanCustom getCommentByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception {
		return projectCommentMapper.getCommentByProjectNameAndDate(projectCommentVo);
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public void updateMergeProjectComments(String projectName, String prodDate, ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception {
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
		projectCommentMapper.updateMergeProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void updateProjectComments(String projectName, String prodDate, ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception {
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
		projectCommentMapper.updateProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void insertProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom)throws Exception {
		projectCommentMapper.insertProjectComments(projectCommentBeanCustom);
	}

	@Override
	public void deleteProjectComments(String projectName, String prodDateOrigin) throws Exception {
		ProjectCommentBeanCustom projectCommentBeanCustom = new ProjectCommentBeanCustom();
		projectCommentBeanCustom.setProjectName(projectName);
		projectCommentBeanCustom.setProdDateOrigin(sdf.parse(prodDateOrigin));
		projectCommentMapper.deleteProjectComments(projectCommentBeanCustom);
	}

	
	
	
	
	
}
