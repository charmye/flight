package com.hc.pojo;

import java.io.Serializable;

/**
 *机场 
 */
public class Airport implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 机场id
	 * */
	private int airportId;
	/**
	 * 机场名
	 * */
	private String airportName;
	
	private String region;
	public Airport() {
		super();
	}
	public int getAirportId() {
		return airportId;
	}
	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", region=" + region + "]";
	}
	
	
	
}
