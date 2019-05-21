package com.hc.pojo;

import java.io.Serializable;

import com.hc.util.TimeContrastUtil;

/**
 * 机票
 * 
 */
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int ticketId;//id
	private Airport fromAirport;//出发地
	private Airport destinationAirport;//目的地
	private String departureTime;//出发时间
	private String arrivalTime;//到达时间
	private Aircraft aircraft;//航班
	private Cabin cabin;//舱位类型
	private int quantity;//票数
	private int sold;//已卖出
	private double Price;//价格
	
	
	
	public Ticket() {
		super();
	}
	public Ticket(int ticketId, Airport fromAirport, Airport destinationAirport, String departureTime,
			String arrivalTime, Aircraft aircraft, Cabin cabin, int quantity, int sold, double price) {
		super();
		this.ticketId = ticketId;
		this.fromAirport = fromAirport;
		this.destinationAirport = destinationAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.aircraft = aircraft;
		this.cabin = cabin;
		this.quantity = quantity;
		this.sold = sold;
		Price = price;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public Airport getFromAirport() {
		return fromAirport;
	}
	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}
	public Airport getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	
	public String getDepartureTime() {
		
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		try {
			this.departureTime = TimeContrastUtil.timeFormat(departureTime);
		}catch (Exception e) {
			this.departureTime = departureTime.replaceAll("T", " ");
			System.out.println(this.departureTime);
		}
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		try {
			this.arrivalTime = TimeContrastUtil.timeFormat(arrivalTime);
		}catch (Exception e) {
			this.arrivalTime = arrivalTime.replaceAll("T", " ");
			System.out.println(this.arrivalTime);
		}
	}
	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Cabin getCabin() {
		return cabin;
	}
	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", fromAirport=" + fromAirport + ", destinationAirport="
				+ destinationAirport + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", aircraft=" + aircraft + ", cabin=" + cabin + ", quantity=" + quantity + ", sold=" + sold
				+ ", Price=" + Price + "]";
	}
	
	
	
	
}
