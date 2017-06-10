package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	/*
	 * public void setOffersDao(OffersDAO offersDao) { this.offersDao =
	 * offersDao; }
	 */


	public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String username) {
		// TODO Auto-generated method stub
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return usersDao.getAllUsers();
	}

	
}
