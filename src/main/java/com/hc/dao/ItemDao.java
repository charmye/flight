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

import com.hc.pojo.Item;

@Mapper
public interface ItemDao {

	/**
	 * 查看机场数据库所有值
	 * */
	@Select("SELECT * FROM item")
	List<Item> findAllItem(); 
	
	@Insert("insert into item(ticketId,orderId,identificationId) values (#{ticket.ticketId},#{orderId},#{identification.identificationId})") // 添加
    public void save(Item item);
	
    @Delete("delete from item where itemId=#{id}") // 删除
    public void delete(int id);

    /**
     * 通过订单项id查询一条数据
     * */
    @Select("select * from item where itemId=#{id}") // 查询一个
    @Results({
    	@Result(property="ticket",column="ticketId",
    		one=@One(select="com.hc.dao.TicketDao.checkTicketById")),
    	
    	@Result(property="identification",column="identificationId",
		one=@One(select="com.hc.dao.IdentificationDao.getById"))
    })
    public Item getByItemId(int id);
    
    /**
     * 通过订单id查询数据
     * */
    @Select("select * from item where orderId=#{id}")
    @Results({
    	@Result(property="ticket",column="ticketId",
    			one=@One(select="com.hc.dao.TicketDao.checkTicketById")),
    	
    	@Result(property="identification",column="identificationId",
    	one=@One(select="com.hc.dao.IdentificationDao.getById"))
    })
    public List<Item> getByOrderId(int id);

    
    @Update("update item set ticketId = #{ticket.ticketId},order = #{order.orderId},identificationId=#{identification.identificationId} where itemId=#{itemId} ") // 修改
    public void update(Item item);
	
}
