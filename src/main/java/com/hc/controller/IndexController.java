package com.hc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hc.pojo.Airport;
import com.hc.service.impl.TicketServiceImpl;

@Controller
public class IndexController {
	
	@Autowired
	private TicketServiceImpl ticketServiceImpl;

	@GetMapping("/")
	public ModelAndView defaultEntry() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");
		return mav;
	}
	
	@GetMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Airport> airport = ticketServiceImpl.findAllSellAirport();
		if(airport==null) {
			mav.addObject("mse", "暂无票");
			mav.setViewName("mse");
			return mav;
		}
		mav.addObject("fromAirport", airport);
		request.setAttribute("user", null);
		mav.setViewName("home/index");
		return mav;
	}
	/*@GetMapping("/index2")
	public ModelAndView index2(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home/index2");
		return mav;
	}*/
	
	@ResponseBody
	@RequestMapping("/checkDestinationAirport")
	public List<Airport> checkUsername(Integer fromAirportId) {
		List<Airport> airport = ticketServiceImpl.findAllAirportByFromAirportId(fromAirportId);
		
		return airport;
	}
	
	
}
