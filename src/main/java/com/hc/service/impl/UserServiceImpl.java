package com.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hc.dao.IdentificationDao;
import com.hc.dao.UserDao;
import com.hc.pojo.Identification;
import com.hc.pojo.User;
import com.hc.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdentificationDao identificationDao;
	
	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}


	@Override
	public User findUserById(int id) {
		
		return userDao.findUserById(id);
	}


	@Override
	public boolean checkUsername(String username) {
		
		Integer id = userDao.findByUsername(username);
		if(id==null) {
			return true;
		}
			return false;
	}


	@Override
	public boolean register(User user) {
		Integer i =  userDao.insert(user);
		if(i!=null&&i==1) {
			return true;
		}
		return false;
	}


	@Override
	public User login(User user) {
		
		return userDao.findByUsernameAndPassowrd(user);
	}


	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void updateByUsername(User user) {
		userDao.updateByUsername(user);
	}

	@Override
	public void delete(int userId) {
		userDao.delete(userId);
	}
	
	@Override
	public List<Identification> getListCardByUserId(int userId) {
		return identificationDao.getByUserId(userId);
	}


	@Override
	public void saveCard(Identification identification) {
		identificationDao.save(identification);
	}


	@Override
	public void deleteCard(int id) {
		identificationDao.delete(id);
	}
	
	

}
