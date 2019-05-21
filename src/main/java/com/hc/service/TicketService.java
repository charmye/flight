package com.hc.service;

import java.util.List;

import com.hc.pojo.Airport;
import com.hc.pojo.Ticket;

public interface TicketService {
	/**
	 * 通过 出发地机场id 检索 目的地机场
	 * */
	List<Airport> findAllAirportByFromAirportId(Integer fromAirportId); 
	
	
	/**
	 *  查询出发地机场
	 * */
	List<Airport> findAllSellAirport(); 
	/**
	 * 
	 * 通过出发地id 和 目的地 id 查询 所有机票的所有信息
	 * */
	List<Ticket> ticketCheckByFromIdAndDestinationId(Integer fromId,Integer destinationId);
	
	/**
	 * 通过票id 查票详细信息
	 * */
	Ticket checkTicketById(Integer ticketId);
	
	//List<Ticket> canSell();
	
	List<Ticket> findAllTicket(); 
	
	
	/**
	 * 添加
	 * */
	void saveTicket(Ticket ticket);
	
	/**
	 * 删除
	 * */
	void deleteTicket(int id);
	
	/**
	 * 修改
	 * */
	void updateTicket(Ticket ticket);
	
}
