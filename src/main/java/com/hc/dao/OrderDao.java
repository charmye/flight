package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Order;

@Mapper
public interface OrderDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM orders")
	List<Order> findAllOrder(); 
	
	@Insert("insert into orders(userId,total,state) values (#{userId},#{total},#{state})") // 添加
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
    public int save(Order order);

    @Delete("delete from orders where orderId=#{id}") // 删除
    public void delete(int id);

    /**
     * 通过订单id查询一条数据
     * */
    @Select("select * from orders where orderId=#{id}") // 查询一个
    public Order getOrderByOrderId(int id);

    /**
     * 通过用户id查询数据
     * */
    @Select("select * from orders where userId=#{id}")
    public List<Order> getByUserId(int id);
    
    /**
     * 通过用户id查询state为0的数据
     * */
    @Select("select * from orders where userId=#{id},state=0")
    public List<Order> getByUserIdAndstate(int id);
    
    
    
    @Update("update orders set userId = #{userId},total = #{total},state = #{state} where orderId=#{orderId} ") // 修改
    public void update(Order Order);
    
    /**
     * 通过订单id修改state为1
     * */
    @Update("update orders set state = 1 where orderId=#{orderId} ") // 修改
    public void stateChangeOne(int orderId);
    
    
	
}
