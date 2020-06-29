package com.ssc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectAttachmentVo;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.service.FileBrowserService;
import com.ssc.service.FileTransferService;
import com.ssc.service.StatusService;

@Controller
public class FileBrowserController {

	private static Logger logger = Logger.getLogger(FileBrowserController.class);
	
	@Autowired
	private FileBrowserService fileBrowserService;
	@Autowired
	private StatusService statusService;
	
	

	@ModelAttribute("projectName")
	public List<StatusBeanCustom> getAllProjectNames(HttpSession session) throws Exception {
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
		List<StatusBeanCustom> namesList = statusService.fetchProjectNames();
		List<StatusBeanCustom> projectName = new ArrayList<StatusBeanCustom>();
		for (StatusBeanCustom allNames : namesList) {
			if (allNames.getGroupId() == groupID) {
				projectName.add(allNames);
			}
		}
		return projectName;
	}
	
	
	@RequestMapping(value = "/resourceFiles", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getResourceFiles() throws Exception{
		
		ProjectAttachmentVo projectAttachmentVo = new ProjectAttachmentVo();
		projectAttachmentVo.setProjectName("Others");
		
		List<ProjectAttachmentBeanCustom> projectAttachmentBeanCustomList = fileBrowserService.fetchAllFilesByMultiParam(projectAttachmentVo);
		logger.info("Fetch All Resource Files Succeed!  Project:: Others");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectAttachmentList", projectAttachmentBeanCustomList);
		modelAndView.addObject("Total", projectAttachmentBeanCustomList.size());
		modelAndView.setViewName("file/resourceFiles");
		return modelAndView;
	}
		
	
	
	@RequestMapping(value = "/allFiles", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView fetchAllFiles(ProjectAttachmentVo projectAttachmentVo) throws Exception{
		
		List<ProjectAttachmentBeanCustom> projectAttachmentBeanCustomList = fileBrowserService.fetchAllFilesByMultiParam(projectAttachmentVo);
		logger.info("Fetch All Files Succeed!  Project:: All");
		ModelAndView modelAndView = new ModelAndView();
		String oderByItemDesc=  "b.Item_Desc";
		String oderByUploadTime=  "a.UPLOAD_TIME DESC";
		modelAndView.addObject("projectAttachmentList", projectAttachmentBeanCustomList);
		modelAndView.addObject("Total", projectAttachmentBeanCustomList.size());
		modelAndView.addObject("projectAttachmentVo", projectAttachmentVo);
		modelAndView.addObject("oderByItemDesc", oderByItemDesc);
		modelAndView.addObject("oderByUploadTime", oderByUploadTime);
		
		modelAndView.setViewName("file/allFiles");
		return modelAndView;
		
	}
		
	
	
}
