package com.ssc.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.exception.CustomException;
import com.ssc.mapper.ProjectCommentMapper;
import com.ssc.mapper.StatusMapper;
import com.ssc.service.StatusService;





public class StatusServiceImpl  implements StatusService{

	private static Logger logger = Logger.getLogger(StatusServiceImpl.class);
	
	@Autowired
	private StatusMapper statusMapper;
	@Autowired
	ProjectCommentMapper projectCommentMapper;
	
	@Override
	public List<StatusBeanCustom> fetchProjectNames() throws Exception {
		logger.info("Fetching All ProjectNames From DB... ...");
		return statusMapper.getProjectNames();
	}

	@Override
	public List<StatusBasicBeanCustom> fetchProjectStatusByGroupID(Integer groupID) throws Exception {
		logger.info("Fetching All Items Status By GroupID From DB... ...+== " + groupID );
		return statusMapper.getProjectStatusByGroupID(groupID);
	}

	@Override
	public List<StatusBeanCustom> fetchStatusByMultiParam(StatusBeanVo statusBeanVo) throws Exception {
		logger.info("Fetching All Items From DB... ...");
		return statusMapper.getStatusByMultiParam(statusBeanVo);
	}
	@Override
	public StatusBeanCustom fetchStatusByID(Integer id) throws Exception {
		
		if(id == null){
			throw new CustomException("Please select a item!!!");
		}
		logger.info("Fetching Status By ID From DB... ...+== " + id );
		StatusBean statusBean = statusMapper.getStatusByID(id);
		if(statusBean == null ){
			throw new CustomException("Record is not Exist!!!");
		}
		StatusBeanCustom statusBeanCustom = null;
		if(statusBean != null){
			statusBeanCustom = new StatusBeanCustom();
			BeanUtils.copyProperties(statusBean, statusBeanCustom);
		}
		return statusBeanCustom;
	}

	@Override
	public List<StatusBeanCustom> fetchStatusByIDList(StatusBeanVo statusBeanVo) throws Exception {
		if(statusBeanVo.getIdList().isEmpty()){
			throw new CustomException("Please select at least ONE item!!!");
		}
		logger.info("Fetching Status List By IDList From DB... ...+== " + statusBeanVo.getIdList().toString());
		return statusMapper.getStatusByIDList(statusBeanVo);
	}
	
	@Override
	public ProjectCommentBeanCustom fetchCommentByProjectNameAndDate(ProjectCommentVo projectCommentVo) throws Exception {
		logger.info("Fetching Comment By Project Name And Production Scheduled Date From DB... ...+== " + projectCommentVo.getProjectName()+" : "+ String.format("%tF", projectCommentVo.getProdDate()) );
		return projectCommentMapper.getCommentByProjectNameAndDate(projectCommentVo);
	}

}
	
