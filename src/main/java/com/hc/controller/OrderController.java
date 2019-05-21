package com.hc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.pojo.Item;
import com.hc.pojo.Order;
import com.hc.pojo.User;
import com.hc.service.impl.OrderServiceImpl;

@Controller
public class OrderController {

	@Autowired OrderServiceImpl orderServiceImpl;
	
	@RequestMapping("/purchase")
	public ModelAndView purchase(int ticketId,Integer[] passenger,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		ModelAndView mav = new ModelAndView();
		Integer orderId = orderServiceImpl.addOrder(ticketId,user,passenger);
		if(orderId!=null&&orderId!=0) {
			mav.addObject("order", orderServiceImpl.getOrderByOrderId(orderId));
			mav.setViewName("home/Payment");
		}else {
			mav.addObject("mse", "下单失败,有票已失效，请重新选择");
			mav.setViewName("mse");
		}
		return mav;
	}
	@RequestMapping("/payment")
	public ModelAndView payment(int orderId) {
		ModelAndView mav = new ModelAndView();
		orderServiceImpl.payment(orderId);
		mav.addObject("mse", "购票成功");
		mav.setViewName("redirect:/index");
		return mav;
		
	}

	@RequestMapping("/historicalOrder")
	public ModelAndView historicalOrder(@RequestParam(value="start",defaultValue="1")int start,@RequestParam(value = "size", defaultValue = "5") int size,HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if(start<1) {
			start+=1;
		}
		List<Order> orders = orderServiceImpl.getOrderByUserId(user);
		int max;
		if((orders.size()+1)%5>0) {
			max=(orders.size()+1)/5+1;
		}else {
			max=(orders.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		ModelAndView mav = new ModelAndView();
        PageHelper.startPage(start,size,"orderId");
        PageInfo<Order> page = new PageInfo<>(orders);
        mav.addObject("page", page);
        mav.setViewName("home/order");
		return mav;
    }
	@RequestMapping("/listItem")
	public ModelAndView listItem(@RequestParam(value="start",defaultValue="1")int start,@RequestParam(value = "size", defaultValue = "5") int size,int orderId) throws Exception {
		if(start<1) {
			start+=1;
		}
		List<Item> items = orderServiceImpl.getItemByOrderId(orderId);
		int max;
		if((items.size()+1)%5>0) {
			max=(items.size()+1)/5+1;
		}else {
			max=(items.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(start,size,"itemId");
		PageInfo<Item> page = new PageInfo<>(items);
		mav.addObject("page", page);
		mav.addObject("orderId",items.get(0).getOrderId());
		mav.setViewName("home/item");
		return mav;
	}
	
	@RequestMapping("/goOrderManage")
	public ModelAndView goOrderManage(@RequestParam(value="start",defaultValue="1")int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		if(start<1) {
			start+=1;
		}
		List<Order> orders = orderServiceImpl.findAllOrder();
		int max;
		if((orders.size()+1)%5>0) {
			max=(orders.size()+1)/5+1;
		}else {
			max=(orders.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		ModelAndView mav = new ModelAndView();
        PageHelper.startPage(start,size,"orderId");
        PageInfo<Order> page = new PageInfo<>(orders);
        mav.addObject("page", page);
        mav.setViewName("manage/orderManage/orderManage");
		return mav;
    }
	@RequestMapping("/goOrderItme")
	public ModelAndView goOrderItme(@RequestParam(value="start",defaultValue="1")int start,@RequestParam(value = "size", defaultValue = "5") int size,int orderId) throws Exception {
		if(start<1) {
			start+=1;
		}
		List<Item> items = orderServiceImpl.getItemByOrderId(orderId);
		int max;
		if((items.size()+1)%5>0) {
			max=(items.size()+1)/5+1;
		}else {
			max=(items.size()+1)/5;
		}
		if(start>max) {
			start=max;
		}
		ModelAndView mav = new ModelAndView();
		PageHelper.startPage(start,size,"itemId");
		PageInfo<Item> page = new PageInfo<>(items);
		mav.addObject("page", page);
		mav.addObject("orderId",items.get(0).getOrderId());
		mav.setViewName("manage/orderManage/item");
		return mav;
	}
	
	   @RequestMapping("/delOrder")
	    public String delOrder(int orderId)throws Exception{
		   orderServiceImpl.delete(orderId);
	        return "redirect:goOrderManage";  //删除成功,重定向到分类查询页面
	    }
	
}
