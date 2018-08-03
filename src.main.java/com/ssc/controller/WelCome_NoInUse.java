package com.ssc.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WelCome_NoInUse {
	
	@RequestMapping(value="/welcome1",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView split() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("navi/navigator1");
		return modelAndView;
	}
	

}
