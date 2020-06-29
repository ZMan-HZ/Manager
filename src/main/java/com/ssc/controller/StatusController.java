package com.ssc.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.FilterBean;
import com.ssc.beans.ProjectAttachmentBeanCustom;
import com.ssc.beans.ProjectCommentBeanCustom;
import com.ssc.beans.ProjectCommentVo;
import com.ssc.beans.StatusBasicBeanCustom;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.beans.UserCustom;
import com.ssc.service.StatusService;
import com.ssc.service.StatusUpdateService;
import com.ssc.utils.BeanUtilsCustom;

@Controller
public class StatusController {

	private static Logger logger = Logger.getLogger(StatusController.class);
	
	@Autowired
	private StatusService statusService;
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	
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
	
	@ModelAttribute("projectComments")
	public List<String> getProjectCommentsBy(FilterBean filterBean) throws Exception {
				
		if ((filterBean.getProdDate() != null ) && (filterBean.getProjectName() != null && filterBean.getProjectName().length() != 0)) {
			ProjectCommentVo projectCommentVo = new ProjectCommentVo();
		
			projectCommentVo.setProdDate(filterBean.getProdDate());
			projectCommentVo.setProjectName(filterBean.getProjectName());
			ProjectCommentBeanCustom  projectCommentBeanCustom = statusService.fetchCommentByProjectNameAndDate(projectCommentVo);
			List<String> projectCommentsList = new ArrayList<String>();
		    if(projectCommentBeanCustom != null && projectCommentBeanCustom.getProjectComments() != null){
		    	String[] projectComments = null;
		    	projectComments = projectCommentBeanCustom.getProjectComments().split("\\n");
		    	if(projectComments != null){
		    		for(int i = 1; i <= projectComments.length; i++) {
		    			if(projectComments[i - 1].startsWith("*")) {
		    				projectCommentsList.add("<font color=\"red\">"+ projectComments[i - 1] + "</font><br>");
		    			} else {
		    				projectCommentsList.add("<font color=\"purple\">"+ projectComments[i - 1] + "</font><br>");
		    			}
		    		}
		    		return projectCommentsList;
		    	}
		    }else{
		    	 projectCommentsList.add("<font color=\"red\"> No Comments Yet! </font><br>");
		    	 return projectCommentsList;
		    }
		    
		}
		return null;
	}
	
	@RequestMapping(value = "/status", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView statusBrowser(HttpSession session,FilterBean filterBean) throws Exception {
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
		
		// Get Session from DB
		String newSession = (String) session.getAttribute("newSession");
		if(newSession != null){
			logger.info("==============Fetch Filter From DB=============");
			getFilterFromDB(filterBean, userCustom);
			session.removeAttribute("newSession");
		}
		FilterBean filterBeanFromSession = (FilterBean) session.getAttribute("filterBean");
		
		// this is for batch updated Prod Date  Repleace by JS projectComments.jsp
//		if(filterBeanFromSession != null && filterBeanFromSession.getProdDateNew() != null && filterBeanFromSession.getProdDateOrigin() != null){
//			mergeObjectExcludeHideAndIS(filterBean,filterBeanFromSession);
//			filterBean.setProdDate(filterBeanFromSession.getProdDateNew());
//		}
		// this is for filter merge Hide columns
		if(filterBean.getIsJobStatus() == null){
			if (filterBeanFromSession != null  && filterBean.getIsFilter() ==null){
//				mergeFilterObject(filterBean,filterBeanFromSession);
				List<String> ignoreHideAndIsFilterPropertiesList = ignoreHideAndIsFilterPropertiesList(filterBeanFromSession);
				String[] ignoreProperties = new String[ignoreHideAndIsFilterPropertiesList.size()];
				ignoreHideAndIsFilterPropertiesList.toArray(ignoreProperties);
				BeanUtilsCustom.copyPropertiesCustom(filterBeanFromSession,filterBean,true,ignoreProperties);
				logger.info("==============merge FilterObject=============");
			}
		}else{
			if (filterBeanFromSession != null  && filterBean.getIsFilter() ==null){
//				mergeEachHideCloumns(filterBean,filterBeanFromSession);
				List<String> ignorePropertiesList = ignoreHidePropertiesList(filterBeanFromSession);
				String[] ignoreProperties = new String[ignorePropertiesList.size()];
				ignorePropertiesList.toArray(ignoreProperties);
				BeanUtilsCustom.copyPropertiesCustom(filterBeanFromSession,filterBean,true,ignoreProperties);
				logger.info("==============merge Each HideCloumns=============");
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		StatusBeanVo statusBeanVo = filterSession(session, filterBean, modelAndView, groupID); 
		mainFormAndReleaseNote(modelAndView, statusBeanVo);
		//Save session into DB
		if(filterBeanFromSession != null && !isObjectFilterEqual(filterBean,filterBeanFromSession)){
			logger.info("===== DB Filter with Session Equals  === False " );
				updateDBFilterSession(filterBean, userCustom);
				logger.info("=====update DB Filter Session");
		}else{
			logger.info("===== DB Filter with Session Equals  === TRUE " );
		}
		modelAndView.setViewName("itemsStatus/items");
		
		return modelAndView;
	}

	/**
	 * @param filterBeanFromSession
	 * @return
	 */
	public List<String> ignoreHidePropertiesList(FilterBean filterBeanFromSession) {
		Field[] sourceFields = filterBeanFromSession.getClass().getDeclaredFields();
		List<String> ignoreProperties= new ArrayList<String>();
		for(int index = 0; index < sourceFields.length;index++){
			String fieldName = sourceFields[index].getName();
			if(fieldName.contains("hide")){
				ignoreProperties.add(fieldName);
			}
		}
		return ignoreProperties;
	}
	public List<String> ignoreHideAndIsFilterPropertiesList(FilterBean filterBeanFromSession) {
		Field[] sourceFields = filterBeanFromSession.getClass().getDeclaredFields();
		List<String> ignoreProperties= new ArrayList<String>();
		for(int index = 0; index < sourceFields.length;index++){
			String fieldName = sourceFields[index].getName();
			if(isFieldContainsHideOrIsFilter(fieldName) && !"isFilter".equalsIgnoreCase(fieldName)){
				ignoreProperties.add(fieldName);
			}
		}
		return ignoreProperties;
	}

	public void updateDBFilterSession(FilterBean filterBean, UserCustom userCustom) throws Exception {
		StringBuilder sb = new StringBuilder();
		Field[] filterFields = filterBean.getClass().getDeclaredFields();
		for (Field field : filterFields) {
			try {
				field.setAccessible(true);
				Object value = field.get(filterBean);
				String fieldName = field.getName();
				if((fieldName.contains("hide") || fieldName.contains("is")) && !"isFilter".equalsIgnoreCase(fieldName)){
					if (null != value && value.toString().length() != 0) {
						if (field.getType().isArray()) {
							sb.append(fieldName).append(":");
							Arrays.asList((String[]) value).forEach(status -> sb.append(status).append("&"));
							sb.append("@");
						}else{
							sb.append(fieldName).append(":").append(value).append("@");
						}
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		userCustom.setOtherConfig(sb.toString());
		statusUpdateService.updateUserByName(userCustom.getUserName(), userCustom);
	}

	public static boolean isObjectFilterEqual(Object source, Object target) {

		Field[] sourceFields = source.getClass().getDeclaredFields();
		Field[] targetFields = target.getClass().getDeclaredFields();
		
		List<Field> sourceFieldsList = Arrays.asList(sourceFields);
		List<Field> targetFieldsList = Arrays.asList(targetFields);
		List<Object> difField = new ArrayList<Object>();
		sourceFieldsList.forEach(srcField -> {srcField.setAccessible(true);
		                            if(isFieldContainsHideOrIsFilter(srcField.getName())){
		                             try {  Object valueofSource = srcField.get(source);
		                            		if(isValueNullorBlank(valueofSource)){ srcField.set(source, null);}
		                            		targetFieldsList.get(targetFieldsList.indexOf(srcField)).setAccessible(true);
		                            		Field targetField = targetFieldsList.get(targetFieldsList.indexOf(srcField));
		                            		Object valueofTarget = targetField.get(target);
		                            		if(isValueNullorBlank(valueofTarget)){targetField.set(target, null);}
		                            		if(srcField.getType().isArray()){
		                            			if(!Arrays.asList((String[]) valueofTarget).equals(Arrays.asList((String[]) valueofSource))){difField.add(srcField);}
		                            		}else if(valueofTarget != null && valueofSource !=null){
		                            			if(!valueofTarget.equals(valueofSource)){difField.add(srcField);}}
		                            		targetField.setAccessible(false);} catch (Exception e) { e.printStackTrace();}
		                            } srcField.setAccessible(false); } );
		logger.info("===== DB Filter Equals Session  ? ==== " );
		return difField.isEmpty()? true:false;
	}
	
	public static boolean isValueNullorBlank(Object value){
		return (value == null || value == "")? true:false;
	}
	public static boolean isFieldContainsHideOrIsFilter(String fieldName){
		return fieldName.toUpperCase().contains("HIDE")||fieldName.toUpperCase().contains("IS")?true:false;
	}
	
	public void getFilterFromDB(FilterBean filterBean, UserCustom userCustom) {
		Map<String,String> filterFromDBSession = userCustom.getMemoryConfig();
		for(Entry<String, String> entry:filterFromDBSession.entrySet()) {
				if(entry.getKey().contains("has")){
					String filterName = entry.getKey().substring(3);
					setFilterFromDB(filterBean, entry, filterName);
//					System.out.println(filterName+"===="+entry.getValue());
				}else
				if(entry.getKey().contains("hide")){
					String filterName = entry.getKey().substring(4); 
					setFilterFromDB(filterBean, entry, filterName);
//					System.out.println(filterName+"===="+ entry.getValue());
				}
			  if(entry.getKey().toUpperCase().contains("JOBSTATUS")){
				filterBean.setIsJobStatus( entry.getValue().split("&"));
			 }
		}
	}

	public void setFilterFromDB(FilterBean filterBean, Entry<String, String> entry, String filterName) {
		logger.info("=====Set Filter From DB======");
		Field[] filterField = filterBean.getClass().getDeclaredFields();
		for (Field field : filterField) {
			try {
				field.setAccessible(true);
				String fieldName = field.getName();
				if(fieldName.contains(filterName)){
						field.set(filterBean,entry.getValue());
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	public void mainFormAndReleaseNote(ModelAndView modelAndView, StatusBeanVo statusBeanVo) throws Exception {
		
		
		List<StatusBeanCustom> statusBeanList = statusService.fetchStatusByMultiParam(statusBeanVo);
		logger.info("=====Fetch Status From DB Completed======:: Fetched "+ statusBeanList.size()+"  Records");
		Set<ProjectAttachmentBeanCustom> attachmentBeanCustomList = new LinkedHashSet<>();
		Integer reviewedCount= 	0;
		//
		for(int i = 0; i < statusBeanList.size(); i++){
			StatusBeanCustom statusBeanCustom = statusBeanList.get(i);
			
			if(statusBeanCustom.getItemDesc() != null){
				statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\n", "<br/>"));
				if(statusBeanCustom.getItemDesc().contains("[") && statusBeanCustom.getItemDesc().contains("]")){
					statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\]", "]</font> "));
					statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\[", "<font color=\"lilac\">["));
				}
				statusBeanList.set(i,statusBeanCustom);
				if(i == statusBeanList.size()-1)
				logger.info("=====Set Item Description Completed ====== ");
			}
			if(statusBeanCustom.getStatus() != null){
				statusBeanCustom.setStatus(statusBeanCustom.getStatus().replaceAll("\\n", "<br/>@##@"));
                statusBeanCustom.setStatusInforList(Arrays.asList(statusBeanCustom.getStatus().split("@##@")));
				statusBeanList.set(i,statusBeanCustom);
				if(i == statusBeanList.size()-1)
				logger.info("=====Set Item Status Completed ====== ");
			}
			if(statusBeanCustom.getAttachments_url() != null){
				String[] url = statusBeanCustom.getAttachments_url().split("@@@");
				for(String testcase:url){
					if(testcase.toUpperCase().contains("TESTCASE") || testcase.toUpperCase().contains("TEST_CASE")){
						statusBeanCustom.setHasTestCase(true);
					}
					if(!testcase.isEmpty()){
						ProjectAttachmentBeanCustom attachmentBeanCustom = new ProjectAttachmentBeanCustom();
						String[] adr = testcase.split("\\|");
						attachmentBeanCustom.setId( Integer.decode(adr[0]));
						attachmentBeanCustom.setProjectId(statusBeanCustom.getId());
						attachmentBeanCustom.setFileName(adr[1]);
						attachmentBeanCustomList.add(attachmentBeanCustom);
					}
				}
			}else{
				statusBeanCustom.setHasTestCase(false);
			}
			String reviewed =null;
			if(statusBeanCustom.getComments() != null){
				if(statusBeanCustom.getComments().contains("[[=ALL REVIEWED=]]")){
					statusBeanCustom.setComments(statusBeanCustom.getComments().replaceAll("\\[\\[=ALL REVIEWED=\\]\\]","<br><span class=\"reviewTag\" >[[ALL REVIEWED]]</span><br>"));
					reviewed="reviewed";
					statusBeanCustom.setReviewedTag(reviewed);
				}
				statusBeanCustom.setComments(statusBeanCustom.getComments().replaceAll("\\n", "<br/>"));
				statusBeanList.set(i,statusBeanCustom);
				if(i == statusBeanList.size()-1)
				logger.info("=====Set Item Comments Completed ====== ");
			}
			if(statusBeanCustom.getDbScripts() != null){
				if(statusBeanCustom.getDbScripts().contains("[[=ALL REVIEWED=]]")){
					statusBeanCustom.setDbScripts(statusBeanCustom.getDbScripts().replaceAll("\\[\\[=ALL REVIEWED=\\]\\]","<span class=\"reviewTag\" >[[ALL REVIEWED]]</span><br>"));
					reviewed="reviewed";
					statusBeanCustom.setReviewedTag(reviewed);
				}
				statusBeanCustom.setDbScripts(statusBeanCustom.getDbScripts().replaceAll("\\n", "<br/>"));
				statusBeanList.set(i,statusBeanCustom);
				if(i == statusBeanList.size()-1)
				logger.info("=====Set Item DbScripts Completed ====== ");
			}
			if(statusBeanCustom.getRemainedQuestions() != null){
				if(statusBeanCustom.getRemainedQuestions().contains("[[=ALL REVIEWED=]]")){
					statusBeanCustom.setRemainedQuestions(statusBeanCustom.getRemainedQuestions().replaceAll("\\[\\[=ALL REVIEWED=\\]\\]","<span class=\"reviewTag\" >[[ALL REVIEWED]]</span><br>"));
					reviewed="reviewed";
					statusBeanCustom.setReviewedTag(reviewed);
				}
				statusBeanCustom.setRemainedQuestions(statusBeanCustom.getRemainedQuestions().replaceAll("\\n", "<br/>"));
				statusBeanList.set(i,statusBeanCustom);
				if(i == statusBeanList.size()-1)
				logger.info("=====Set Item RemainedQuestions Completed ====== ");
			}
			if(statusBeanCustom.getReviewedTag() != null){
				reviewedCount++;
			}
		}
		logger.info("=====main Form Generated Succeed ");
		modelAndView.addObject("reviewedTag", reviewedCount);
		modelAndView.addObject("attachmentBeanCustomList", attachmentBeanCustomList);
		modelAndView.addObject("statusBeanList", statusBeanList);
		modelAndView.addObject("totalRecords", statusBeanList.size());
	}

	public StatusBeanVo filterSession(HttpSession session, FilterBean filterBean, ModelAndView modelAndView, Integer groupID) throws Exception {
		
		List<StatusBasicBeanCustom> statusList = statusService.fetchProjectStatusByGroupID(groupID);
		modelAndView.addObject("statusList", statusList);
		
		Map<String,String> filterStatus = new HashMap<String,String>();
		StatusBeanVo statusBeanVo = new StatusBeanVo();
		statusBeanVo.setGroupId(groupID);
		setIsCloseIsDeleteIsReverse(filterBean, statusBeanVo);
		setOwnerDateNameStatus(filterBean, statusBeanVo);
		
		if (filterBean.getIsFilter() != null || filterBean.getIsJobStatus() != null) {
			// * this is filter form
			session.removeAttribute("filterBean");
			setFilterStatus(filterBean, modelAndView, statusList, filterStatus);
			//step Add filter Hide columns
			modelAndView.addObject("filterBean", filterBean);
			session.setAttribute("filterBean", filterBean);
		}
		return statusBeanVo;
	}

	public void setFilterStatus(FilterBean filterBean, ModelAndView modelAndView, List<StatusBasicBeanCustom> statusList, Map<String, String> filterStatus) {
		for(StatusBasicBeanCustom basicStatus:statusList){
			if(Arrays.asList(filterBean.getIsJobStatus()).contains(basicStatus.getProjectStatus())){
				filterStatus.put(basicStatus.getProjectStatus(), "checked='true'");
			}else{
				filterStatus.put(basicStatus.getProjectStatus(), "");
			}
		}
		modelAndView.addObject("filterStatus", filterStatus);//for jsp checked= true < Job Status:>
	}

	public void setOwnerDateNameStatus(FilterBean filterBean, StatusBeanVo statusBeanVo)  {
		if(filterBean.getOwner() != null){
			statusBeanVo.setUser(filterBean.getOwner());
		}
		if(filterBean.getProdDate() !=null){
			statusBeanVo.setProdDate(filterBean.getProdDate());
		}
		if(filterBean.getProjectName() != null){
			statusBeanVo.setProjectName(filterBean.getProjectName());
		}
		if(filterBean.getIsJobStatus()!= null && filterBean.getIsJobStatus().length != 0){
			statusBeanVo.setJobStatusList(Arrays.asList(filterBean.getIsJobStatus()));
		}
//		if(filterBean.getJobStatus()!= null){
//			if(filterBean.getJobStatus().length >= 2 ){
//			statusBeanVo.setJobStatusList(Arrays.asList(filterBean.getJobStatus()));
//			}
//			if(filterBean.getJobStatus().length == 1){
//				statusBeanVo.setJobStatus(filterBean.getJobStatus()[0]);
//			}
//		}
	}

	public void setIsCloseIsDeleteIsReverse(FilterBean filterBean, StatusBeanVo statusBeanVo) {
		if (filterBean.getIsClose() == null) {
			statusBeanVo.setIsClosed(false);
		} else {
			statusBeanVo.setIsClosed(true);
		}
		if (filterBean.getIsDelete() == null) {
			statusBeanVo.setIsDelete(false);
		} else {
			statusBeanVo.setIsDelete(true);
		}
		if (filterBean.getIsReverse() == null) {
			statusBeanVo.setIsReverse(false);
		} else {
			statusBeanVo.setIsReverse(true);
		}
	}

	// Using reflect to copy FilterBean each not NULL property from session to filterBean
//	public static void mergeFilterObject(FilterBean target, FilterBean source) {
//		Field[] sourceFields = source.getClass().getDeclaredFields();
//		for (Field field : sourceFields) {
//			try {
//				field.setAccessible(true);
//				Object value = field.get(source);
//				String fieldName = field.getName();
//				if(isFieldContainsHideOrIsFilter(fieldName) && !"isFilter".equalsIgnoreCase(fieldName)){
//					if (null != value && value.toString().length() != 0) {
//						field.set(target, value);
//					}
//				}
//				field.setAccessible(false);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e);
//			}
//		}
//	}
	
//	public static void mergeObjectExcludeHideAndIS(FilterBean target, FilterBean source) {
//		Field[] sourceFields = source.getClass().getDeclaredFields();
//		for (Field field : sourceFields) {
//			try {
//				field.setAccessible(true);
//				Object value = field.get(source);
//				String fieldName = field.getName();
//				if(!isFieldContainsHideOrIsFilter(fieldName)){
//					if (null != value && value.toString().length() != 0) {
//						field.set(target, value);
//					}
//				}
//				field.setAccessible(false);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e);
//			}
//		}
//	}
	
//	public static void mergeEachHideCloumns(FilterBean target,FilterBean source){
//		Field[] sourceFields = source.getClass().getDeclaredFields();
//		for (Field field : sourceFields) {
//			try {
//				field.setAccessible(true);
//				Object value = field.get(source);
//				String fieldName = field.getName();
//				if(fieldName.contains("hide")  && !"isFilter".equalsIgnoreCase(fieldName)){
//					if (null != value && value.toString().length() != 0) {
//						field.set(target, value);
//					}
//				}
//				field.setAccessible(false);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e);
//			}
//		}
//	}
//	
	
	

}
