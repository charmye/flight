package com.hc.pojo;

import java.io.Serializable;

/**
 * 航班
 * */
public class Aircraft implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 航班id
	 * */
	private int aircraftId;
	/**
	 * 航班名
	 * */
	private String aircraftName;
	
	public int getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(int aircraftId) {
		this.aircraftId = aircraftId;
	}
	public String getAircraftName() {
		return aircraftName;
	}
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}
	@Override
	public String toString() {
		return "Aircraft [aircraftId=" + aircraftId + ", aircraftName=" + aircraftName + "]";
	}
	
}
