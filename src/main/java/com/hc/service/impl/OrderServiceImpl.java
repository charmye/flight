package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hc.dao.IdentificationDao;
import com.hc.dao.ItemDao;
import com.hc.dao.OrderDao;
import com.hc.dao.TicketDao;
import com.hc.pojo.Identification;
import com.hc.pojo.Item;
import com.hc.pojo.Order;
import com.hc.pojo.Ticket;
import com.hc.pojo.User;
import com.hc.service.OrderService;
import com.hc.util.TimeContrastUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired TicketDao ticketDao;
	@Autowired OrderDao orderDao;
	@Autowired ItemDao itemDao;
	@Autowired IdentificationDao identificationDao;
	
	
	@Override
	@Transactional
	public Integer addOrder(int ticketId,User user,Integer[] passenger) {
		Ticket t = ticketDao.checkTicketById(ticketId);
		if(!TimeContrastUtil.TimeContrast(t.getDepartureTime())&&t.getQuantity()<=t.getSold()) {
			return 0;
		}
		System.out.println("passenger.length="+passenger.length);
		Order order = new Order();
		order.setState(0);
		order.setTotal(t.getPrice()*passenger.length);
		order.setUserId(user.getUserId());
		orderDao.save(order);
		System.out.println("添加返回orderId"+order.getOrderId());
		for(int i = 0;i<passenger.length;i++) {
			System.out.println("i="+i);
			Identification identification = identificationDao.getById(passenger[i]);
			Item item = new Item();
			item.setIdentification(identification);
			item.setOrderId(order.getOrderId());
			item.setTicket(t);
			itemDao.save(item);
			ticketDao.sold(ticketId);
		}
		return order.getOrderId();
	}

	@Override
	public List<Order> getOrderByUserId(User user) {
		return orderDao.getByUserId(user.getUserId());
	}

	@Override
	public List<Item> getItemByOrderId(int id) {
		
		return itemDao.getByOrderId(id);
	}
	
	@Override
	public List<Order> findAllOrder(){
		return orderDao.findAllOrder();
	}
	/**
	 * 查询未付款订单
	 * */
	@Override
    public List<Order> getUnpaidOrder(int id){
    	return orderDao.getByUserIdAndstate(id);
    }

	@Override
	public Order getOrderByOrderId(int id) {
		return orderDao.getOrderByOrderId(id);
	}

	@Override
	public void payment(int orderId) {
		orderDao.stateChangeOne(orderId);
	}

	/*
	 *删除订单 
	 */
	@Override
	public void delete(int id) {
		orderDao.delete(id);
	}
	
}
