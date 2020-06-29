package com.ssc.mapper;

import java.util.List;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;

public interface ProjectCommentMapper {

//	public List<ProjectCommentBeanCustom> getCommentsListByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	
	public ProjectCommentBeanCustom getCommentByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	
	public void updateMergeProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void updateProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void insertProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void deleteProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	
	
}
