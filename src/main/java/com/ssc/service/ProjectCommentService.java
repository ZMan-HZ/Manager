package com.ssc.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import com.ssc.beans.ProjectCommentBeanCustom;


@Transactional(rollbackFor=Exception.class)
@CacheEvict(value={"statusServiceImpl"},allEntries=true,beforeInvocation=true)
public interface ProjectCommentService {


//	public List<ProjectCommentBeanCustom> getCommentsListByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	
    public void updateMergeProjectComments(String projectName,String prodDate,ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void updateProjectComments(String projectName,String prodDate,ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void insertProjectComments(ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception;
	
	public void deleteProjectComments(String projectName,String prodDateOrigin) throws Exception;
	
}
