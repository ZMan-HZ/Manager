package com.ssc.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssc.beans.FilterBean;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.service.ProjectCommentService;
import com.ssc.service.StatusService;
import com.ssc.service.StatusUpdateService;

@Controller
public class CommentUpdateController {

	
	@Autowired
	private StatusService statusService;
	@Autowired
	private StatusUpdateService statusUpdateService;
	@Autowired
    private ProjectCommentService projectCommentService;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@RequestMapping(value = "/updateComment", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateStatusComments(Integer projectID, HttpSession session) throws Exception{
			
			StatusBeanCustom statusBeanCustom = statusUpdateService.getStatusByID(projectID);
			UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
			Integer groupID = userCustom.getGroupId();
			List<StatusBasicBeanCustom> statusList = statusService.getProjectStausByGroupID(groupID);
			
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
        }
        else{
        	statusBeanCustom.setId(id);
        	String comment = "\n[[=ALL REVIEWED=]]\n"+statusBeanCustom.getComments();
        	statusBeanCustom.setComments(comment);
        	statusUpdateService.updateStatusRecordByID(id, statusBeanCustom);
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
		ProjectCommentBeanCustom projectCommentBeanCustom = projectCommentService.getCommentByProjectNameAndDate(projectCommentVo);
		
	    if(projectCommentBeanCustom != null && projectCommentBeanCustom.getProjectComments() != null){
	    		modelAndView.addObject("projectCommentBeanCustom", projectCommentBeanCustom);
	    }else{
	    	 projectCommentBeanCustom = new ProjectCommentBeanCustom();
	    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	 projectCommentBeanCustom.setProdDate(sdf.parse(prodDate));
	    	 projectCommentBeanCustom.setProjectComments("No Comments Yet!");
	    	 modelAndView.addObject("projectCommentBeanCustom", projectCommentBeanCustom);
	    }
	    modelAndView.addObject("projectCommentVo", projectCommentVo);
	    if(isOpenFirst != null){
	      modelAndView.addObject("isOpenFirst", isOpenFirst);
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
			statusUpdateService.updateStatusProdDate(projectName, prodDate, prodDateOrigin);
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
	   ProjectCommentBeanCustom projectCommentBeanCustomDB = projectCommentService.getCommentByProjectNameAndDate(projectCommentVo);
	   
	   if(projectCommentBeanCustomDB == null){
		   projectCommentService.insertProjectComments(projectCommentBeanCustom);
	   }else {
		      if(dateChange){
			    projectCommentService.updateMergeProjectComments(projectName, dateInUse, projectCommentBeanCustom);
			    projectCommentService.deleteProjectComments(projectName, prodDateOrigin);
		       }
		       else{
			     projectCommentService.updateProjectComments(projectName, dateInUse, projectCommentBeanCustom);
			     }
	   }
	   String isFirstOpen = "no";
		
		return getProjectComment(projectName,dateInUse,isFirstOpen);
		 
	}
	
	
	
	
	
	
	
	
}
