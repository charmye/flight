package com.hc.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Aircraft;
import com.hc.pojo.Airport;

public interface AircraftService {
	
	/**
	 * 查看机场数据库所有值
	 * */
	List<Aircraft> findAllAircraft(); 
	
	
    public void save(Aircraft Aircraft);

    public void delete(int id);

    public Aircraft getById(int id);

    public void update(Aircraft Aircraft);
	
}
