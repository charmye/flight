package com.hc.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Airport;

public interface AirportService {
	
	/**
	 * 查看机场数据库所有值
	 * */
	List<Airport> findAllAirport(); 
	
    public void save(Airport airport);

    public void delete(int id);

    public Airport getById(int id);

    public void update(Airport airport);
	
}
