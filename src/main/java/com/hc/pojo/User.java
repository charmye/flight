package com.hc.pojo;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userId;//用户id
	private String username;//用户名
	private String password;//密码
	private String phone;//电话
	private String email;//邮箱
	private String fileurl;//头像地址
	
	
	public User() {
		super();
	}
	
	public User(String username, String password, String phone, String email, String fileurl) {
		super();
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.fileurl = fileurl;
	}
	

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", fileurl=" + fileurl + "]";
	}
	
	
	
	
	
	
	
	
}
