package com.ssc.service;

import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;

public interface ProjectCommentService {


//	public List<ProjectCommentBeanCustom> getCommentsListByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	public ProjectCommentBeanCustom getCommentByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	
    public void updateMergeProjectComments(String projectName,String prodDate,ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void updateProjectComments(String projectName,String prodDate,ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void insertProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void deleteProjectComments(String projectName,String prodDateOrigin) throws Exception;
	
}
