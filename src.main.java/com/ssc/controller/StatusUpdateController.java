package com.ssc.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.UpdateStatusBean;
import com.ssc.beans.UserCustom;
import com.ssc.service.StatusService;
import com.ssc.service.StatusUpdateService;




@Controller
public class StatusUpdateController {

	@Autowired
	private StatusService statusService;
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	
	
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
	
	
	@ModelAttribute("statusList")
	public List<StatusBasicBeanCustom> getAllProjectStatus(HttpSession session) throws Exception {
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
//		List<StatusBasicBeanCustom> statusList = statusService.getProjectStausByGroupID(groupID);
//		return statusList;
		return statusService.getProjectStausByGroupID(groupID);
	}
	
	
	
	@RequestMapping(value = "/updateStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateStatus(Integer id, HttpSession session) throws Exception{
		
		StatusBeanCustom statusBeanCustom = statusUpdateService.getStatusByID(id);
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
//		Integer groupID = userCustom.getGroupId();
//		List<StatusBasicBeanCustom> statusList = statusService.getProjectStausByGroupID(groupID); // Replaced by ModelAttribute
		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("statusList", statusList);
		modelAndView.addObject("UpdateBy", userCustom.getUserName());
		modelAndView.addObject("UpdateByName", userCustom.getLongName());
		modelAndView.addObject("statusBeanCustom", statusBeanCustom);
		modelAndView.setViewName("itemsStatus/updateItemStatus");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/updateStatusSubmit", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView updateStatusSubmit(Integer id,UpdateStatusBean updateStatusBean) throws Exception{
        
		StatusBeanCustom statusBeanCustom = statusUpdateService.getStatusByID(id);
//        mergeObject(updateStatusBean,statusBeanCustom);
        BeanUtils.copyProperties(updateStatusBean, statusBeanCustom);
//        StatusBeanCustom statusBeanCustom = new StatusBeanCustom();  //Not in Use Because it will copy all value including NULL
//        BeanUtils.copyProperties(statusBean, statusBeanCustom);
        statusBeanCustom.setId(id);
        statusBeanCustom.setUpdateTime(new Date());
        statusUpdateService.updateStatusRecordByID(id, statusBeanCustom);
        
        ModelAndView modelAndView = new ModelAndView();
        String isOpenFirst = "no";
        modelAndView.addObject("isOpenFirst", isOpenFirst);
        modelAndView.addObject("statusBeanCustom", statusBeanCustom);
        modelAndView.setViewName("itemsStatus/updateItemStatus");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/insertNewStatus", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertNewStatus(HttpSession session) throws Exception{
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
		List<StatusBasicBeanCustom> statusList = statusService.getProjectStausByGroupID(groupID);
		
		ModelAndView modelAndView = new ModelAndView();
		StatusBeanCustom statusBeanCustom = new StatusBeanCustom();
		//Default Values
		statusBeanCustom.setProjectName("DAS");
		statusBeanCustom.setJobStatus("DEV");
       if(groupID == 1){
    	   statusBeanCustom.setLeader("Lucy; Diana");
		}
		modelAndView.addObject("statusBeanCustom", statusBeanCustom);
		modelAndView.addObject("statusList", statusList);
		modelAndView.setViewName("itemsStatus/addNewItem");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/insertNewStatusSubmit", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insertNewStatusSubmit(HttpSession session,StatusBeanCustom statusBeanCustom) throws Exception{
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		if(statusBeanCustom.getOwner().isEmpty()){
			statusBeanCustom.setOwner(userCustom.getLongName());
		}
		if(statusBeanCustom.getCreateBy() == null){
			statusBeanCustom.setCreateBy(userCustom.getUserName());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null != statusBeanCustom.getStartDate() && statusBeanCustom.getStartDate().length() != 0){
			statusBeanCustom.setCreateTime(sdf.parse(statusBeanCustom.getStartDate()));
		}
		if(statusBeanCustom.getProdDateCustom().length()!= 0){
			statusBeanCustom.setProdDate(sdf.parse(statusBeanCustom.getProdDateCustom()));
		}
		if(statusBeanCustom.getItemDesc().isEmpty()){
			statusBeanCustom.setItemDesc("User didn't Enter Description");
		}
		statusBeanCustom.setUpdateBy(userCustom.getUserName());
		statusBeanCustom.setUpdateTime(statusBeanCustom.getCreateTime());
		
		statusUpdateService.insertStatusRecord(statusBeanCustom);
		ModelAndView modelAndView = new ModelAndView();
		String isOpenFirst = "no";
		modelAndView.addObject("isOpenFirst", isOpenFirst);
		modelAndView.addObject("statusBeanCustom", statusBeanCustom);
		modelAndView.setViewName("itemsStatus/addNewItem");
		
		return modelAndView;
	}
	
	
	/*
	public static void mergeObject(Object source, Object target) {
		Field[] sourceFields = source.getClass().getDeclaredFields();
		Field[] targetFields = target.getClass().getSuperclass().getDeclaredFields();
		for (Field srcField : sourceFields) {
			srcField.setAccessible(true);
			try {
				Object value = srcField.get(source);
//				System.out.println("value::"+value);
//				System.out.println("sourceField::"+srcField.getName());
				List<Field> targetFieldsList = Arrays.asList(targetFields);
				Field targetField = targetFieldsList.get(targetFieldsList.indexOf(srcField));
				targetField.setAccessible(true);
				if (srcField.getName().equalsIgnoreCase(targetField.getName())) {
						targetField.set(target, value);
//						break;
				}
//				for(Field targetField : targetFields){
//					targetField.setAccessible(true);
////					System.out.println("targetField::"+targetField.getName());
//					if (srcField.getName().equalsIgnoreCase(targetField.getName())) {
////						if (null != value && value.toString().length() != 0) {
//							targetField.set(target, value);
//							break;
////						}
//					}
//				}
				targetField.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			srcField.setAccessible(false);
			continue;
		}
	}
	*/
	
	
}
