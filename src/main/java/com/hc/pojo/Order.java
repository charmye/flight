package com.hc.pojo;

import java.io.Serializable;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private int userId;//用户
	private double total;//订单的总计
	private int state;//订单的状态
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", total=" + total + ", state=" + state + "]";
	}
	
	
	
	
	
}
