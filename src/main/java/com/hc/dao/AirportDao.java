package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Airport;

@Mapper
public interface AirportDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM airport")
	List<Airport> findAllAirport(); 
	
	
	@Insert("insert into airport(airportName,region) values (#{airportName},#{region}) ") // 添加
    public void save(Airport airport);

    @Delete("delete from airport where airportId=#{id} ") // 删除
    public void delete(int id);

    @Select("select * from airport where airportId=#{id}  ") // 查询一个
    public Airport getById(int id);

    @Update("update airport set airportName = #{airportName},region = #{region} where airportId=#{airportId} ") // 修改
    public void update(Airport airport);
	
}
