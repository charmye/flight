package com.hc.pojo;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private int itemId;
	private Ticket ticket;
	private Identification identification;
	private int orderId;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Identification getIdentification() {
		return identification;
	}
	public void setIdentification(Identification identification) {
		this.identification = identification;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", ticket=" + ticket + ", identification=" + identification + ", order="
				+ orderId + "]";
	}
	
}
