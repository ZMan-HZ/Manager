package com.ssc.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.service.ProjectCommentService;
import com.ssc.service.StatusService;
import com.ssc.service.StatusUpdateService;

@Controller
public class CommentUpdateController {
	private static Logger logger = Logger.getLogger(CommentUpdateController.class);
	
	@Autowired
	private StatusService statusService;
	@Autowired
	private StatusUpdateService statusUpdateService;
	@Autowired
    private ProjectCommentService projectCommentService;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping(value = "/updateComment", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateStatusComments(Integer projectID, HttpSession session) throws Exception{
			
		    if(projectID == null){
			  throw new CustomException("No Item be Selected");
		    }
			StatusBeanCustom statusBeanCustom = statusService.fetchStatusByID(projectID);
			logger.info("[updateStatusComments] ::: Fetch status Succeed");
			UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
			Integer groupID = userCustom.getGroupId();
			List<StatusBasicBeanCustom> statusList = statusService.fetchProjectStatusByGroupID(groupID);
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("statusList", statusList);
			modelAndView.addObject("UpdateBy", userCustom.getUserName());
			modelAndView.addObject("UpdateByName", userCustom.getLongName());
			modelAndView.addObject("statusBeanCustom", statusBeanCustom);
			
		    modelAndView.setViewName("itemsStatus/updateItemStatusComment");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/updateCommentSubmit", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView updateStatusSubmit(Integer id,StatusBeanCustom statusBeanCustom) throws Exception{
        
		String isOpenFirst = "no";
        
        if(statusBeanCustom.getReviewedTag() == null){
        	statusBeanCustom.setId(id);
        	statusUpdateService.updateStatusRecordByID(id, statusBeanCustom);
        	logger.info("[updateStatusSubmit] ::: Update status Succeed");
        }
        else{
        	statusBeanCustom.setId(id);
        	String comment = "\n[[=ALL REVIEWED=]]\n"+statusBeanCustom.getComments();
        	statusBeanCustom.setComments(comment);
        	statusUpdateService.updateStatusRecordByID(id, statusBeanCustom);
        	logger.info("[updateStatusSubmit] ::: Update status with Review Tag Succeed");
        }
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isOpenFirst", isOpenFirst);
        modelAndView.addObject("statusBeanCustom", statusBeanCustom);
        modelAndView.setViewName("itemsStatus/updateItemStatusComment");
        
		return modelAndView;
	}
	
	
	@RequestMapping(value="/projectComments", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getProjectComment(String projectName,String prodDate,String isOpenFirst) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		ProjectCommentVo projectCommentVo = new ProjectCommentVo();
		projectCommentVo.setProdDate(sdf.parse(prodDate));
		projectCommentVo.setProjectName(projectName);
		ProjectCommentBeanCustom projectCommentBeanCustom = statusService.fetchCommentByProjectNameAndDate(projectCommentVo);
		
	    if(projectCommentBeanCustom != null && projectCommentBeanCustom.getProjectComments() != null){
	    	logger.info("[getProjectComment]:: Fetch ProjectComments Succeed");
	    	modelAndView.addObject("projectCommentBeanCustom", projectCommentBeanCustom);
	    }else{
	    	logger.info("[getProjectComment]:: DB ProjectComments Not Exist");
	    	 projectCommentBeanCustom = new ProjectCommentBeanCustom();
	    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	 projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
	    	 projectCommentBeanCustom.setProjectComments("No Comments Yet!");
	    	 modelAndView.addObject("projectCommentBeanCustom", projectCommentBeanCustom);
	    }
	    modelAndView.addObject("projectCommentVo", projectCommentVo);
	    if(isOpenFirst != null){
	      modelAndView.addObject("isOpenFirst", isOpenFirst);
	      logger.info("[getProjectComment]::  Update ProjectComments Succeed");
	    }
	    modelAndView.setViewName("projectComments/projectComments");
		return modelAndView;
	}
	
	@RequestMapping(value="/updateProjectCommentsSubmit", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView updateProjectCommentsSubmit(HttpSession session,String projectName,String prodDate,String prodDateOrigin,ProjectCommentBeanCustom projectCommentBeanCustom) throws Exception{
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Boolean isAdmin = userCustom.getRole().equalsIgnoreCase("Admin");
		Boolean dateChange = false;
		String dateInUse = prodDateOrigin;
		
		if(isAdmin && !prodDate.equals(prodDateOrigin)){
			logger.info("Admin Update Prod Date");
			statusUpdateService.updateStatusProdDate(projectName, prodDate, prodDateOrigin);
			logger.info("[updateProjectCommentsSubmit] ::: update Status ProdDate Succeed from prodDate: "+ prodDateOrigin +"to: "+ prodDate);
//			FilterBean filterBean = new FilterBean();                     //Replaced by JS projectComments.jsp
//			filterBean.setProdDateNew(sdf.parse(prodDate));
//			filterBean.setProdDateOrigin(sdf.parse(prodDateOrigin));
//			session.setAttribute("filterBean", filterBean);
			dateChange = true;
			dateInUse = prodDate;
		}
	   
	   ProjectCommentVo projectCommentVo = new ProjectCommentVo();
	   projectCommentVo.setProdDate(sdf.parse(dateInUse));
	   projectCommentVo.setProjectName(projectName);
	   ProjectCommentBeanCustom projectCommentBeanCustomDB = statusService.fetchCommentByProjectNameAndDate(projectCommentVo);
	   
	   if(projectCommentBeanCustomDB == null){
		   projectCommentService.insertProjectComments(projectCommentBeanCustom);
		   logger.info("[updateProjectCommentsSubmit] ::: Insert ProjectComments into DB Succeed");
	   }else {
		      if(dateChange){
			    projectCommentService.updateMergeProjectComments(projectName, dateInUse, projectCommentBeanCustom);
			    logger.info("[updateProjectCommentsSubmit] :::Update and Merged ProjectComments after Prod Date:"+ prodDateOrigin + "Changed to :"+ dateInUse + "For Project:"+projectName+", Succeed");
			    projectCommentService.deleteProjectComments(projectName, prodDateOrigin);
			    logger.info("[updateProjectCommentsSubmit] ::: Origin ProjectComments Delete,Succeed! Origin==="+ prodDateOrigin);
		       }
		       else{
			     projectCommentService.updateProjectComments(projectName, dateInUse, projectCommentBeanCustom);
			     logger.info("[updateProjectCommentsSubmit] ::: Update ProjectComments,Succeed! Prod Date:"+ prodDateOrigin +"Project Name:" + projectName);
			     }
	   }
	   String isFirstOpen = "no";
		
		return getProjectComment(projectName,dateInUse,isFirstOpen);
		 
	}
	
	
	
	
	
	
	
	
}
