package com.ssc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.NaviBeanCustom;
import com.ssc.beans.UserCustom;
import com.ssc.service.NaviService;

@Controller
public class NaviController {
	
	private static Logger logger = Logger.getLogger(NaviController.class);
	
	@Autowired
	private NaviService naviService;
	
	@RequestMapping(value="/navi",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getNavi(HttpSession session,HttpServletRequest request,String ismore){
		
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		ModelAndView modelAndView = new ModelAndView();
		Integer groupID = userCustom.getGroupId();
		
		if(ismore != null){
			ismore = "Yes";
			try {
				List<NaviBeanCustom>  naviReleaseList = naviService.fetchAllProdDateByGroupID(groupID);
				modelAndView.addObject("prodReleaseList", naviReleaseList);
			} catch (Exception e) {
				logger.error("fetchAllProdDateByGroupID::"+ e);
			}
			modelAndView.addObject("ismore", ismore);
		}else{
			try {
				List<NaviBeanCustom> releaseList  = naviService.fetchAllProdDateByGroupID(groupID);
				List<NaviBeanCustom> naviReleaseList = new ArrayList<NaviBeanCustom>();
				for(int index = 0; index < 10; index++){
					naviReleaseList.add(releaseList.get(index));
				}
				modelAndView.addObject("prodReleaseList", naviReleaseList);
			} catch (Exception e) {
				logger.error("fetchAllProdDateByGroupID::"+ e);
			}
			
			try {
				List<NaviBeanCustom> allProjectList = naviService.fetchAllProjectByGroupID(groupID);
				modelAndView.addObject("allProjectList", allProjectList);
			} catch (Exception e) {
				logger.error("fetchAllProjectByGroupID::"+ e);
			}
			try {
				List<NaviBeanCustom> allStatusList = naviService.fetchAllProjectCountByGroupID(groupID);
				modelAndView.addObject("allStatusList", allStatusList);
			} catch (Exception e) {
				logger.error("fetchAllProjectCountByGroupID::"+ e);
			}
			try {
				List<String> allOwnerList  = getAllOwners(naviService.fetchAllOwnerByGroupID(groupID));
				modelAndView.addObject("allOwnerList", allOwnerList);
			} catch (Exception e) {
				logger.error("fetchAllOwnerByGroupID::"+ e);
			}
		}
		modelAndView.setViewName("navi/navigator");
		return modelAndView;
	}
	/**
	 * @param allOwnerList
	 */
	public List<String> getAllOwners(List<NaviBeanCustom> allOwnerList) {
		String[] ownerTemp = null;
        Set<String> ownerSet = new HashSet<String>();
        List<String> ownerList = new ArrayList<String>();
        allOwnerList.forEach(onwner -> ownerList.add(onwner.getOwner()));
		for(String owners:ownerList){
			if(owners.indexOf(";")!=-1) {
				ownerTemp = owners.split(";");
			} else if(owners.indexOf("|")!=-1) {
				ownerTemp = owners.split("|");
			} else {
				ownerTemp = new String[]{owners};
			}
			for(String c:ownerTemp) {
				ownerSet.add(c.trim());
			}
		}
		List<String> dataListResult = new ArrayList<String>();
		dataListResult.addAll(ownerSet);
		return dataListResult;
	}
	
}
