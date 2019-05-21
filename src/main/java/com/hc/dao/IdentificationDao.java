package com.hc.dao;

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Identification;

@Mapper
public interface IdentificationDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM identification")
	@Results({
    	@Result(property="user",column="userId",
    		one=@One(select="com.hc.dao.UserDao.findUserById"))
    })
	List<Identification> findAllIdentification(); 
	
	
	@Insert("insert into identification(name,idcard,userId) values (#{name},#{idcard},#{user.userId}) ") // 添加
    public void save(Identification identification);

    @Delete("delete from identification where identificationId=#{id} ") // 删除
    public void delete(int id);
    
    
    @Select("select * from identification where identificationId=#{id}  ") // 通过identificationId查询一个
    @Results({
    	@Result(property="user",column="userId",
    		one=@One(select="com.hc.dao.UserDao.findUserById"))
    })
    public Identification getById(int id);
    
    @Select("select * from identification where userId=#{id}  ") // 通过userid查询
    @Results({
    	@Result(property="user",column="userId",
    		one=@One(select="com.hc.dao.UserDao.findUserById"))
    })
    public List<Identification> getByUserId(int id);

    @Update("update identification set name = #{name} ,idcard = #{idcard}  where identificationId=#{identificationId} ") // 修改
    public void update(Identification Identification);
	
}
