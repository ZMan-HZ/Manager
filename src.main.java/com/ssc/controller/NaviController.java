package com.ssc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssc.beans.FilterBean;
import com.ssc.beans.NaviBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.service.NaviService;

@Controller
public class NaviController {
	
	@Autowired
	private NaviService naviService;
	
	@RequestMapping(value="/navi",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getNavi(HttpSession session,HttpServletRequest request,String ismore) throws Exception{
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		ModelAndView modelAndView = new ModelAndView();
		Integer groupID = userCustom.getGroupId();
		
//		String isMore = (String)request.getParameter("ismore");
		if(ismore != null){
			ismore = "Yes";
			List<NaviBeanCustom> naviReleaseList = naviService.getAllProdDateByGroupID(groupID);
			modelAndView.addObject("prodReleaseList", naviReleaseList);
			modelAndView.addObject("ismore", ismore);
		}else{
			List<NaviBeanCustom> releaseList = naviService.getAllProdDateByGroupID(groupID);
			List<NaviBeanCustom> naviReleaseList = new ArrayList<NaviBeanCustom>();
			for(int index = 0; index < 10; index++){
				naviReleaseList.add(releaseList.get(index));
			}
			modelAndView.addObject("prodReleaseList", naviReleaseList);
			
			List<NaviBeanCustom> allProjectList = naviService.getAllProjectByGroupID(groupID);
			modelAndView.addObject("allProjectList", allProjectList);
			
			List<NaviBeanCustom> allStatusList = naviService.getAllProjectCountByGroupID(groupID);
			modelAndView.addObject("allStatusList", allStatusList);
			
			List<String> allOwnerList = getAllOwners(naviService.getAllOwnerGroupID(groupID));
			modelAndView.addObject("allOwnerList", allOwnerList);
			
		}
		modelAndView.setViewName("navi/navigator");
		return modelAndView;
	}
	/**
	 * @param allOwnerList
	 */
	public List<String> getAllOwners(List<String> allOwnerList) {
		String[] ownerTemp = null;
        Set<String> dateList = new HashSet<String>();
		for(String owners:allOwnerList){
			if(owners.indexOf(";")!=-1) {
				ownerTemp = owners.split(";");
			} else if(owners.indexOf("|")!=-1) {
				ownerTemp = owners.split("|");
			} else {
				ownerTemp = new String[]{owners};
			}
			for(String c:ownerTemp) {
				dateList.add(c.trim());
			}
		}
		List<String> dataListResult = new ArrayList<String>();
		dataListResult.addAll(dateList);
		return dataListResult;
	}
	
}
