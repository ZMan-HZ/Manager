package com.ssc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.UserCustom;
import com.ssc.exception.CustomException;
import com.ssc.service.LoginService;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public void login(String userName,String password,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		if(userName != null){
			UserCustom userCustom = loginService.loginWithUserName(userName);
			logger.info("Login validating.....");
			if(password.equals(userCustom.getPassword())){
				session.setAttribute("userCustom", userCustom);
				session.setAttribute("newSession", "newSession");
				response.sendRedirect("navi.action");
				logger.info(userName + " ====Login succeed");
			}else{
				logger.info(userName +"===Passwrod is not correct====");
				response.sendRedirect("login.action");
				throw new CustomException("密码错误");
			}		
		}
		else{
			logger.info("No ID entered,Login failed... ...");
			request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(request, response);
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		logger.info("Logout====");
		session.invalidate();
		return "login/login";
	}

	
	
	
}
