package com.ssc.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import com.ssc.service.FileTransferService;
import com.ssc.service.StatusService;

@Controller
public class FileBrowserController {

	@Autowired
	private FileTransferService fileTransferService;
	@Autowired
	private StatusService statusService;
	
	

	@ModelAttribute("projectName")
	public List<StatusBeanCustom> getAllProjectNames(HttpSession session) throws Exception {
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
		List<StatusBeanCustom> namesList = statusService.getProjectNames();
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
		List<ProjectAttachmentBeanCustom> projectAttachmentBeanCustomList = fileTransferService.getAllFilesByMultiParam(projectAttachmentVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectAttachmentList", projectAttachmentBeanCustomList);
		modelAndView.addObject("Total", projectAttachmentBeanCustomList.size());
		modelAndView.setViewName("file/resourceFiles");
		return modelAndView;
	}
		
	
	
	@RequestMapping(value = "/allFiles", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getAllFiles(ProjectAttachmentVo projectAttachmentVo) throws Exception{
		
		List<ProjectAttachmentBeanCustom> projectAttachmentBeanCustomList = fileTransferService.getAllFilesByMultiParam(projectAttachmentVo);
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
