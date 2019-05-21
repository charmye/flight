package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Cabin;

@Mapper
public interface CabinDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM cabin")
	List<Cabin> findAllCabin(); 
	
	
	@Insert("insert into cabin(cabinName) values (#{cabinName}) ") // 添加
    public void save(Cabin cabin);

    @Delete("delete from cabin where cabinId=#{id} ") // 删除
    public void delete(int id);

    @Select("select * from cabin where cabinId=#{id}  ") // 查询一个
    public Cabin getById(int id);

    @Update("update Cabin set cabinName = #{cabinName} where cabinId=#{cabinId} ") // 修改
    public void update(Cabin cabin);
	
}
