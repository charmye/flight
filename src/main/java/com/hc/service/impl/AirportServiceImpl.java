package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.dao.AirportDao;
import com.hc.pojo.Airport;
import com.hc.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired AirportDao airportDao;
	
	@Override
	public List<Airport> findAllAirport() {
		return airportDao.findAllAirport();
	}

	@Override
	public void save(Airport airport) {
		airportDao.save(airport);
	}

	@Override
	public void delete(int id) {
		airportDao.delete(id);
		
	}

	@Override
	public Airport getById(int id) {
		
		return airportDao.getById(id);
	}

	@Override
	public void update(Airport airport) {

		airportDao.update(airport);
		
	}

}
