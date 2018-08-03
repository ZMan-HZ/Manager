package com.ssc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ssc.beans.UserCustom;
import com.ssc.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public void login(String userName,String password,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		if(userName != null){
			UserCustom userCustom = loginService.loginWithUserName(userName);
			
			if(password.equals(userCustom.getPassword())){
				session.setAttribute("userCustom", userCustom);
				session.setAttribute("newSession", "newSession");
				response.sendRedirect("navi.action");
			}else{
				response.sendRedirect("login.action");
			}		
		}
		else{
			request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(request, response);
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "login/login";
	}

	
	
	
}
