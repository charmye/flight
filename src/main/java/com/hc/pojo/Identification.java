package com.hc.pojo;

public class Identification {

	private int identificationId;
	private String name;
	private String idcard;
	private User user;
	public int getIdentificationId() {
		return identificationId;
	}
	public void setIdentificationId(int identificationId) {
		this.identificationId = identificationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Identification [identificationId=" + identificationId + ", name=" + name + ", idcard=" + idcard
				+ ", user=" + user + "]";
	}
	
}
