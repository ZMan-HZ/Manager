package com.ssc.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;



@Cacheable(value="statusServiceImpl",keyGenerator="cacheKeyGenerator")
public interface StatusService {

	
	public List<StatusBeanCustom> fetchProjectNames() throws Exception;
	
	public List<StatusBasicBeanCustom> fetchProjectStatusByGroupID(Integer groupID) throws Exception;
	
	public List<StatusBeanCustom> fetchStatusByMultiParam(StatusBeanVo statusBeanVo) throws Exception;
	
	public StatusBeanCustom fetchStatusByID(Integer id) throws Exception;
	
	public List<StatusBeanCustom> fetchStatusByIDList(StatusBeanVo statusBeanVo) throws Exception;
	
	public ProjectCommentBeanCustom fetchCommentByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
}
