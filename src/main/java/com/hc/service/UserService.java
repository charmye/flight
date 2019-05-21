package com.hc.service;

import java.util.List;

import com.hc.pojo.Identification;
import com.hc.pojo.User;

public interface UserService {

	/**
	 * 查看所有用户 
	 */
	List<User> findAll();
	
	/**
	 * 通过id查询用户
	 */
	User findUserById(int id);
	
	/**
	 * 通过username查询用户是否存在
	 */
	public boolean checkUsername(String username);
	/**
	 * 
	 * 注册
	 * */
	public boolean register(User user);
	
	/**
	 * 登陆
	 * */
	public User login(User user);
	
	/**
	 * 通过id修改用户信息
	 */
	void update(User user);
	/**
	 * 通过username修改用户信息
	 */
	void updateByUsername(User user);
	
	/**
	 *
	 * 通过id删除用户
	 * 
	 */
	void delete(int userId);

	/**
	 * 通过userid查询Idcard
	 * */
	List<Identification> getListCardByUserId(int userId);
	
	/**
	 * 添加IdCard
	 * */
	void saveCard(Identification identification);
	
	/**
	 * 通过id删除IdCard
	 * */
	void deleteCard(int id);
}
