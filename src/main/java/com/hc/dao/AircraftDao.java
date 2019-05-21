package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Aircraft;

@Mapper
public interface AircraftDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM aircraft")
	List<Aircraft> findAllAircraft(); 
	
	
	@Insert("insert into aircraft(aircraftName) values (#{aircraftName}) ") // 添加
    public void save(Aircraft Aircraft);

    @Delete("delete from aircraft where aircraftId=#{id} ") // 删除
    public void delete(int id);

    @Select("select * from aircraft where aircraftId=#{id}  ") // 查询一个
    public Aircraft getById(int id);

    @Update("update aircraft set aircraftName = #{aircraftName} where aircraftId=#{aircraftId} ") // 修改
    public void update(Aircraft Aircraft);
	
}
