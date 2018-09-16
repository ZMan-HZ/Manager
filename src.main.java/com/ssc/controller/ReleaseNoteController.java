package com.ssc.controller;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.exception.CustomException;
import com.ssc.service.StatusService;

@Controller
public class ReleaseNoteController {

	
	private static Logger logger = Logger.getLogger(ReleaseNoteController.class);
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value = "/releaseNotes", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView releaseNote(Integer[] checkbox_id,String statusFlag,String statusToFlag) throws Exception{
		
 	    if(checkbox_id == null){
			  throw new CustomException("No Items be Selected");
		}
		StatusBeanVo statusBeanVo = new StatusBeanVo();
		statusBeanVo.setIdList(Arrays.asList(checkbox_id));
		List<StatusBeanCustom> statusBeanCutomList = statusService.fetchStatusByIDList(statusBeanVo);
		Set<ProjectAttachmentBeanCustom> attachmentBeanCustomList = new LinkedHashSet<>();
		for(int i = 0; i < statusBeanCutomList.size(); i++){
			StatusBeanCustom statusBeanCustom = statusBeanCutomList.get(i);
			if(statusBeanCustom.getItemDesc() != null){
				if(statusBeanCustom.getItemDesc().contains("[") && statusBeanCustom.getItemDesc().contains("]")){
					statusBeanCustom.setChangeType(statusBeanCustom.getItemDesc().substring(statusBeanCustom.getItemDesc().indexOf("[")));
					statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().substring(0, statusBeanCustom.getItemDesc().indexOf("[")));
				}
				statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\n", "<br/>"));
				statusBeanCutomList.set(i,statusBeanCustom);
			}
			if(statusBeanCustom.getAttachments_url() != null){
				String[] url = statusBeanCustom.getAttachments_url().split("@@@");
				for(String testcase:url){
					if(testcase.toUpperCase().contains("TESTCASE") || testcase.toUpperCase().contains("TEST_CASE")){
						if(!testcase.isEmpty()){
							ProjectAttachmentBeanCustom attachmentBeanCustom = new ProjectAttachmentBeanCustom();
							String[] adr = testcase.split("\\|");
							attachmentBeanCustom.setId( Integer.decode(adr[0]));
							attachmentBeanCustom.setProjectId(statusBeanCustom.getId());
							attachmentBeanCustom.setFileName(adr[1]);
							attachmentBeanCustomList.add(attachmentBeanCustom);
						}
					}
				}
			}
		}
		Boolean flag = false;
		Boolean itemAbleToRelease = true;
		if(statusFlag.equalsIgnoreCase(statusToFlag)){
			 flag = true;//PROD
			 logger.info("PROD Release Note ");
		}else{
			 flag = false;//UAT
			 logger.info("UAT Release Note ");
		}
		for(StatusBeanCustom statusBean: statusBeanCutomList){
			if(!(statusBean.getJobStatus().equalsIgnoreCase(statusFlag)||statusBean.getJobStatus().equalsIgnoreCase(statusToFlag))){
				itemAbleToRelease = false;
				break;
			} 
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("flag", flag);
		modelAndView.addObject("itemAbleToRelease", itemAbleToRelease);
		modelAndView.addObject("statusBeanCutomList", statusBeanCutomList);
		modelAndView.addObject("attachmentBeanCustomList", attachmentBeanCustomList);
		modelAndView.setViewName("release/releaseNotes");
		return modelAndView;
	}
	
	
	
	
	
}
