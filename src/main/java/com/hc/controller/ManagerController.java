package com.hc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hc.pojo.User;

@Controller
public class ManagerController {

	
	@Autowired HttpServletRequest request;
	/**
	 * @return
	 */
	
	@RequestMapping("goManage")
	public String goManage() {
		return "manage/index";
	}
	
	@RequestMapping("manageLeft")
	public String left() {
		return "manage/left";
	}
	
	@RequestMapping("manageTop")
	public String top() {
		return "manage/top";
	}
	
	@RequestMapping(value="/managerLogin",method=RequestMethod.POST)
	public ModelAndView managerLogin(String managerPSW) {
		ModelAndView mav = new ModelAndView();
		if(managerPSW!=null&&managerPSW.equals("000")) {
			request.getSession().setAttribute("managerPSW", managerPSW);
			mav.setViewName("manage/index");
		} else {
			mav.addObject("mse", "登录失败" );
			mav.setViewName("manage/login");
		}
		return mav;
	}
	
	
	
}
