package com.hc;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hc.dao.AircraftDao;
import com.hc.dao.IdentificationDao;
import com.hc.dao.OrderDao;
import com.hc.dao.TicketDao;
import com.hc.dao.UserDao;
import com.hc.pojo.Order;
import com.hc.service.impl.AirportServiceImpl;
import com.hc.service.impl.TicketServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired TicketServiceImpl ticketServiceImpl;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired AirportServiceImpl airportServiceImpl;
	
	@Autowired AircraftDao aircraftDao;
	
	@Autowired OrderDao orderDao;
	
	@Autowired
	private IdentificationDao identificationDao;
	
	@Test
	public void contextLoads() throws ParseException {
		//userDao.findAll().forEach(System.out::println);
		//System.out.println((userDao.findUserById(1)).toString());
		//User user = new User("12", "1", "1", "2", "2" );
		//System.out.println(userDao.insert(user));
		//System.out.println("用户id"+user.getUserId());
		//userDao.update(new User(6,"11","22","3","4"));
		//userDao.delete(6);
		//System.out.println(userDao.findByUsername("444"));
		//ticketDao.findAllAirportByDestinationAirportId(2).forEach(System.out::println);
		//List<Airport> airport = ticketDao.findAllAirport();
		//System.out.println(airport.size());
		//airportServiceImpl.update(new Airport(27,"帝都星际空间站"));
		//ticketDao.findAllTicket().forEach(System.out::println);
		//ticketServiceImpl.ticketCheckByFromIdAndDestinationId(2, 5).forEach(System.out::println);
		//System.out.println(ticketDao.checkTicketById(6));
		//orderDao.getByUserId(1).forEach(System.out::println);
		//identificationDao.getByUserId(1).forEach(System.out::println);
		//System.out.println(orderDao.getOrderByOrderId(1));
		Order o = new Order();
		o.setState(0);
		o.setTotal(200);
		o.setUserId(1);
		System.out.println(orderDao.save(o));
		System.out.println(o.getOrderId());
		
	}

}
