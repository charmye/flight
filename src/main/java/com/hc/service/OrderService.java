package com.hc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hc.pojo.Item;
import com.hc.pojo.Order;
import com.hc.pojo.User;

@Service
public interface OrderService {
	
	/**
	 * 添加订单
	 * */
	Integer addOrder(int ticketId,User user,Integer[] passenger);
	
	/**
	 * 查询用户历史订单
	 * */
	List<Order> getOrderByUserId(User user);
	
	List<Item> getItemByOrderId(int id);
	
	/**
	 * 查询所有订单
	 * */
	List<Order> findAllOrder(); 
	
	/**
	 * 查询未付款订单
	 * */
	List<Order> getUnpaidOrder(int id);
	/**
	 * 通过订单Id查询订单
	 * */
	Order getOrderByOrderId(int id);
	
	/**
	 * 付款
	 * */
	void payment(int orderid);
	
	/**
	 * 删除
	 * */
	void delete(int id);
}
