package com.ssc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginUi_NoInUse {

	@RequestMapping(value="/login1",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/login");
		return modelAndView;
	}
	
}










