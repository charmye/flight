package com.hc.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hc.dao.OrderDao;
import com.hc.dao.TicketDao;
import com.hc.pojo.Airport;
import com.hc.pojo.Ticket;
import com.hc.service.TicketService;
import com.hc.util.TimeContrastUtil;

@CacheConfig(cacheNames = "tic")
@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketDao ticketDao;
	
	@Autowired OrderDao orderDao;
	
	@Override
	public List<Airport> findAllAirportByFromAirportId(Integer fromAirportId) {
		List<Ticket> ts = ticketDao.findByFromId(fromAirportId);
		List<Airport> airports = new ArrayList<>();
		for (Ticket t : ts) {
				if(t.getQuantity()>=t.getSold()&&TimeContrastUtil.TimeContrast(t.getDepartureTime())) {
					boolean flag = true;;
					for (Airport airport : airports) {
						if(airport.getAirportId()==t.getDestinationAirport().getAirportId()) {
							flag = false;
							break;
						}
					}
					if(flag) {
						airports.add(t.getDestinationAirport());
					}
				}
		}
		return airports;
	}


	@Override
	public List<Airport> findAllSellAirport() {
		List<Ticket> ts = ticketDao.findAllTicket();
		List<Airport> airports = new ArrayList<>();
		for (Ticket t : ts) {
			if(t.getQuantity()>=t.getSold()&&TimeContrastUtil.TimeContrast(t.getDepartureTime())) {
				boolean flag = true;;
				for (Airport airport : airports) {
					if(airport.getAirportId()==t.getFromAirport().getAirportId()) {
						flag = false;
						break;
					}
				}
				if(flag) {
					airports.add(t.getFromAirport());
				}
			}
		}
		
		return airports;
	}

	@Cacheable(cacheNames = {"tic"})
	@Override
	public List<Ticket> ticketCheckByFromIdAndDestinationId(Integer fromId,Integer destinationId) {
		List<Ticket> ts = ticketDao.getTicketByFromAndDestination(fromId,destinationId);
		List<Ticket> tickets = new ArrayList<>();
		for (Ticket t : ts) {
			if(t.getQuantity()>=t.getSold()&&TimeContrastUtil.TimeContrast(t.getDepartureTime())) {
				/*t.setArrivalTime(TimeContrastUtil.timeFormat(t.getArrivalTime()));
				t.setDepartureTime(TimeContrastUtil.timeFormat(t.getDepartureTime()));*/
				tickets.add(t);
			}
		}
		return tickets;
	}


	@Override
	public Ticket checkTicketById(Integer ticketId) {
		return ticketDao.checkTicketById(ticketId);
	}


	@Override
	public List<Ticket> findAllTicket() {
		
		return ticketDao.findAllTicket();
	}


	@Override
	public void saveTicket(Ticket ticket) {
		ticketDao.saveTicket(ticket);
		
	}


	@Override
	public void deleteTicket(int id) {
		ticketDao.deleteTicket(id);
		
	}


	@Override
	public void updateTicket(Ticket ticket) {
		ticketDao.updateTicket(ticket);
		
	}

	
}
