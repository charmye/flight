package com.hc.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hc.pojo.Airport;
import com.hc.pojo.Ticket;

@Mapper
@CacheNamespace
public interface TicketDao {

	/**
	 * 查看票数据库所有值
	 * */
	@Select("SELECT * FROM ticket")
	@Results({
		@Result(property="fromAirport",column="fromAirportId",
			one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="destinationAirport",column="destinationAirportId",
		one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="aircraft",column="aircraftId",
		one=@One(select="com.hc.dao.AircraftDao.getById")),
		
		@Result(property="cabin",column="cabinId",
		one=@One(select="com.hc.dao.CabinDao.getById"))
	})
	List<Ticket> findAllTicket(); 
	
	/**
	 * 通过 出发地机场id 检索 目的地机场
	 * */
	@Select("SELECT * FROM ticket where fromAirportId = #{fromAirportId}")
	@Results({
		@Result(property="fromAirport",column="fromAirportId",
			one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="destinationAirport",column="destinationAirportId",
		one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="aircraft",column="aircraftId",
		one=@One(select="com.hc.dao.AircraftDao.getById")),
		
		@Result(property="cabin",column="cabinId",
		one=@One(select="com.hc.dao.CabinDao.getById"))
	})
	List<Ticket> findByFromId(Integer fromAirportId); 
	
	/**
	 * 通过 目的地机场id 检索 出发地机场
	 * */
	@Select("SELECT * from airport where airportId ="
			+ "ANY(SELECT fromAirportId FROM ticket where destinationAirportId = #{destinationAirportId})")
	List<Airport> findAllAirportByDestinationAirportId(Integer destinationAirportId); 
	
	/**
	 *  查询出发地机场
	 * */
	@Select("SELECT * from airport WHERE airportId = "
			+ "ANY(SELECT fromAirportId FROM ticket)")
	List<Airport> findAllAirport(); 
	
	/**
	 * 
	 * 通过出发地id 和 目的地 id 查询 所有机票的所有信息
	 * 
	 * */
	@Select("SELECT * FROM ticket where fromAirportId=#{fromAirportId} AND "
			+ "destinationAirportId=#{destinationAirportId} ORDER BY departureTime")
	@Results({
		@Result(property="fromAirport",column="fromAirportId",
			one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="destinationAirport",column="destinationAirportId",
		one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="aircraft",column="aircraftId",
		one=@One(select="com.hc.dao.AircraftDao.getById")),
		
		@Result(property="cabin",column="cabinId",
		one=@One(select="com.hc.dao.CabinDao.getById"))
	})
	List<Ticket> getTicketByFromAndDestination
	(@Param("fromAirportId") Integer fromId,@Param("destinationAirportId")Integer destinationId);
	
	
	
	/**
	 * 通过票id 查票详细信息
	 * */
	@Select("SELECT * FROM ticket where ticketId=#{ticketId}")
	@Results({
		@Result(property="fromAirport",column="fromAirportId",
			one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="destinationAirport",column="destinationAirportId",
		one=@One(select="com.hc.dao.AirportDao.getById")),
		
		@Result(property="aircraft",column="aircraftId",
		one=@One(select="com.hc.dao.AircraftDao.getById")),
		
		@Result(property="cabin",column="cabinId",
		one=@One(select="com.hc.dao.CabinDao.getById"))
	})
	Ticket checkTicketById(@Param("ticketId") Integer ticketId);
	
	/**
	 * 已卖出加一
	 * */
	@Update("UPDATE ticket SET sold = (sold+1) WHERE ticketId=#{ticketId}")
	void sold(@Param("ticketId") Integer ticketId);
	
	
	
	/**
	 * 已卖出减一
	 * 
	 * */
	@Update("UPDATE ticket SET sold = (sold-1) WHERE ticketId=#{ticketId}")
	void refund(@Param("ticketId") Integer ticketId);
	
	/**
	 * 添加
	 * */
	@Insert("insert into ticket(fromAirportId,destinationAirportId,departureTime,arrivalTime,aircraftId,cabinId,quantity,sold,Price) "
			+ "values (#{fromAirport.airportId},#{destinationAirport.airportId},#{departureTime},#{arrivalTime},#{aircraft.aircraftId},#{cabin.cabinId},#{quantity},0,#{Price}) ") // 添加
    public void saveTicket(Ticket ticket);

	
	/**
	 * 删除
	 * */
    @Delete("delete from ticket where ticketId=#{id} ") // 删除
    public void deleteTicket(int id);
    
    /**
	 * 修改
	 * */
    @Update("update ticket set fromAirportId = #{fromAirport.airportId} , destinationAirportId = #{destinationAirport.airportId} , departureTime = #{departureTime},"
    		+ "arrivalTime = #{arrivalTime} , aircraftId = #{aircraft.aircraftId} , cabinId = #{cabin.cabinId} , quantity = #{quantity}, sold = #{sold} , Price = #{Price} where ticketId=#{ticketId} ") // 修改
    public void updateTicket(Ticket ticket);
}









