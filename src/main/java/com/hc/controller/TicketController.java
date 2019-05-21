package com.hc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.dao.AircraftDao;
import com.hc.dao.AirportDao;
import com.hc.dao.CabinDao;
import com.hc.pojo.Ticket;
import com.hc.pojo.User;
import com.hc.service.impl.TicketServiceImpl;
import com.hc.service.impl.UserServiceImpl;

@Controller
public class TicketController {

	@Autowired TicketServiceImpl ticketServiceImpl;
	
	@Autowired AircraftDao aircraftDao;
	
	@Autowired AirportDao airportDao;
	
	@Autowired CabinDao cabinDao;
	
	@Autowired UserServiceImpl userServiceImpl;
	
	@Autowired HttpServletRequest request;
	
	@RequestMapping("/ticketCheck")
	public ModelAndView TicketCheck(@RequestParam(value="start",defaultValue="1")int start,
		@RequestParam(value = "size", defaultValue = "5") int size,Integer fromAirportId,Integer destinationAirportId,Integer cabinId){
		
		HttpSession session = request.getSession();
		if(fromAirportId!=null&&destinationAirportId!=null) {
			session.setAttribute("fromAirportId", fromAirportId);
			session.setAttribute("destinationAirportId", destinationAirportId);
		}
		ModelAndView mav = new ModelAndView();
		
		if(start<1) {
			start = 1;
		}
		
		mav.addObject("cabins", cabinDao.findAllCabin());
		List<Ticket> tickets = ticketServiceImpl.ticketCheckByFromIdAndDestinationId(
				(Integer)session.getAttribute("fromAirportId"),(Integer)session.getAttribute("destinationAirportId"));
		session.setAttribute("tickets", tickets);
		int max;
		if((tickets.size()+1)%5>0) {
			max=(tickets.size()+1)/5+1;
		}else {
			max=(tickets.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		
		PageHelper.startPage(start,size,"ticketId");
		List<Ticket> ts = new ArrayList<>();
		if(cabinId==null||cabinId==0) {
			ts = ticketServiceImpl.ticketCheckByFromIdAndDestinationId(
					(Integer)session.getAttribute("fromAirportId"),(Integer)session.getAttribute("destinationAirportId"));
		}else {
			for (Ticket ticket : tickets) {
				if(ticket.getCabin().getCabinId()==cabinId) {
					ts.add(ticket);
				}
				
			}
		}
		PageInfo<Ticket> page = new PageInfo<>(ts);
		mav.addObject("page", page);
		mav.setViewName("home/Ticket");
		return mav;
	}

	
	
	@RequestMapping("/reservation")
	public ModelAndView reservation(int ticketId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ticket", ticketServiceImpl.checkTicketById(ticketId));
		User user = (User) request.getSession().getAttribute("user");
		 mav.addObject("identifications",userServiceImpl.getListCardByUserId(user.getUserId()));
		mav.setViewName("home/confirm");
		return mav;
	}
	
	@RequestMapping("/goTicketManage")
	public ModelAndView goTicketManage(@RequestParam(value="start",defaultValue="1")int start,
		@RequestParam(value = "size", defaultValue = "5") int size){
		
		ModelAndView mav = new ModelAndView();
		
		if(start<1) {
			start = 1;
		}
		List<Ticket> tickets =ticketServiceImpl.findAllTicket();
		int max;
		if((tickets.size()+1)%5>0) {
			max=(tickets.size()+1)/5+1;
		}else {
			max=(tickets.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
        PageHelper.startPage(start,size,"ticketId");
        List<Ticket> ts = ticketServiceImpl.findAllTicket();
        PageInfo<Ticket> page = new PageInfo<>(ts);
        mav.addObject("page", page);
        mav.setViewName("manage/ticketManage/ticketManage");
		return mav;
	}
	
	@RequestMapping("/goTicketManageAdd")
	public ModelAndView goTicketManageAdd() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("aircrafts", aircraftDao.findAllAircraft());
		mav.addObject("airports",airportDao.findAllAirport());
		mav.addObject("cabins",cabinDao.findAllCabin());
		mav.setViewName("manage/ticketManage/ticketManageAdd");
		return mav;
	}

	@RequestMapping("/addTicket")
	public ModelAndView addTicket(Ticket ticket) {
		ModelAndView mav = new ModelAndView();
		//System.out.println(ticket.toString());
		ticketServiceImpl.saveTicket(ticket);
		mav.setViewName("redirect:goTicketManage");
		return mav;
	}
	
	@RequestMapping("deleteTicket")
	public ModelAndView deleteTicket(int ticketId) {
		ModelAndView mav = new ModelAndView();
		ticketServiceImpl.deleteTicket(ticketId);
		mav.setViewName("redirect:goTicketManage");
		return mav;
	}
	
	@RequestMapping("goTicketManageUpdate")
	public ModelAndView goTicketManageUpdate(int ticketId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ticket", ticketServiceImpl.checkTicketById(ticketId));
		mav.addObject("aircrafts", aircraftDao.findAllAircraft());
		mav.addObject("airports",airportDao.findAllAirport());
		mav.addObject("cabins",cabinDao.findAllCabin());
		mav.setViewName("manage/ticketManage/ticketManageUpdate");
		return mav;
	}
	@RequestMapping("updateTicket")
	public ModelAndView updateTicket(Ticket ticket) {
		ModelAndView mav = new ModelAndView();
		ticketServiceImpl.updateTicket(ticket);
		mav.setViewName("redirect:goTicketManage");
		return mav;
	}
	
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}   
	
}
