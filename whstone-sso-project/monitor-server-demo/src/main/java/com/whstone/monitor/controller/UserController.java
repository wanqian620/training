package com.whstone.monitor.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@RequestMapping("/login/cas")
	//@ResponseBody
	public String login() {
		return "redirect:/index";
		//return "login";
	}

	@RequestMapping(value = "/index")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String index(HttpSession session) {
		return "index";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/admin")
	@ResponseBody
	public String welcom() {
		
		return "admin";
	}


}
