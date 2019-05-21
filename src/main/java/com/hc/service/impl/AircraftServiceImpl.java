package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.dao.AircraftDao;
import com.hc.pojo.Aircraft;
import com.hc.service.AircraftService;

@Service
public class AircraftServiceImpl implements AircraftService {
	@Autowired AircraftDao aircraftDao;
	
	@Override
	public List<Aircraft> findAllAircraft() {
		return aircraftDao.findAllAircraft();
	}

	@Override
	public void save(Aircraft Aircraft) {
		aircraftDao.save(Aircraft);
	}

	@Override
	public void delete(int id) {
		aircraftDao.delete(id);
	}

	@Override
	public Aircraft getById(int id) {
		return aircraftDao.getById(id);
	}

	@Override
	public void update(Aircraft Aircraft) {
		aircraftDao.update(Aircraft);
	}

}
