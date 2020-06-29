package com.ssc.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.FilterBean;
import com.ssc.beans.StatusBeanCustom;
import com.ssc.beans.StatusBeanVo;
import com.ssc.beans.UserCustom;
import com.ssc.service.StatusService;




@Controller
public class StatusReportController {

	private static Logger logger = Logger.getLogger(StatusReportController.class);
	
	@Autowired
	private StatusService statusService;
	
	
	@RequestMapping(value = "/statusReport", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView statusReport(HttpSession session,FilterBean filterBean) throws Exception {
	
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		Integer groupID = userCustom.getGroupId();
		StatusBeanVo statusBeanVo = new StatusBeanVo(); 
		statusBeanVo.setGroupId(groupID);
		statusBeanVo.setIsClosed(false);
		statusBeanVo.setIsDelete(false);
		statusBeanVo.setIsReverse(false);
		String[] openedItemsArray = new String[]{"Draft", "DEV", "UAT"};
		setStatus(openedItemsArray,statusBeanVo);
		List<StatusBeanCustom>  openedStatusBeanList = mainFormAndReleaseNote(statusBeanVo);
		logger.info("opened StatusBean List Generated Status :: " + openedItemsArray);
		
		String[] completedItemsArray =  new String[]{"PASS"};
		setStatus(completedItemsArray,statusBeanVo);
		List<StatusBeanCustom>  completedStatusBeanList = mainFormAndReleaseNote(statusBeanVo);
		logger.info("completed StatusBean List Generated Status::" + completedItemsArray);
		
		String[] onHoldItemsArray =  new String[]{"HOLD"};
		setStatus(onHoldItemsArray,statusBeanVo);
		List<StatusBeanCustom>  onHoldStatusBeanList = mainFormAndReleaseNote(statusBeanVo);
		logger.info("onHold StatusBean List Generated Status::" + onHoldItemsArray);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("openedStatusBeanList", openedStatusBeanList);
		modelAndView.addObject("TotalOpened", openedStatusBeanList.size());
		
		modelAndView.addObject("completedStatusBeanList", completedStatusBeanList);
		modelAndView.addObject("TotalCompleted", completedStatusBeanList.size());
		
		modelAndView.addObject("onHoldStatusBeanList", onHoldStatusBeanList);
		modelAndView.addObject("TotalonHold", onHoldStatusBeanList.size());
		
		modelAndView.setViewName("itemsStatus/itemsReport");
		return modelAndView;
	}
	

	public void setStatus(String[] josStatusArray,StatusBeanVo statusBeanVo) {
			statusBeanVo.setJobStatusList(Arrays.asList(josStatusArray));
	}

    public List<StatusBeanCustom> mainFormAndReleaseNote(StatusBeanVo statusBeanVo) throws Exception {
    	List<StatusBeanCustom> statusBeanCustomList = statusService.fetchStatusByMultiParam(statusBeanVo);
		for(int i = 0; i < statusBeanCustomList.size(); i++){
			StatusBeanCustom statusBeanCustom = statusBeanCustomList.get(i);
			if(statusBeanCustom.getItemDesc() != null){
				statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\n", "<br/>"));
				if(statusBeanCustom.getItemDesc().contains("[") && statusBeanCustom.getItemDesc().contains("]")){
					statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\]", "]</font> "));
					statusBeanCustom.setItemDesc(statusBeanCustom.getItemDesc().replaceAll("\\[", "<font color=\"blue\">["));
				}
				statusBeanCustomList.set(i,statusBeanCustom);
			}
			if(statusBeanCustom.getStatus() != null){
				statusBeanCustom.setStatus(statusBeanCustom.getStatus().replaceAll("\\n", "<br/>@##@"));
                statusBeanCustom.setStatusInforList(Arrays.asList(statusBeanCustom.getStatus().split("@##@")));
                statusBeanCustomList.set(i,statusBeanCustom);
			}
		}
		return statusBeanCustomList;
	}
	
}
