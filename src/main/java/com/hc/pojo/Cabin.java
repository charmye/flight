package com.hc.pojo;

import java.io.Serializable;

public class Cabin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer cabinId;
	private String cabinName;
	public Integer getCabinId() {
		return cabinId;
	}
	public void setCabinId(Integer cabinId) {
		this.cabinId = cabinId;
	}
	public String getCabinName() {
		return cabinName;
	}
	public void setCabinName(String cabinName) {
		this.cabinName = cabinName;
	}
	@Override
	public String toString() {
		return "Cabin [cabinId=" + cabinId + ", cabinName=" + cabinName + "]";
	}
	
	
	
}
