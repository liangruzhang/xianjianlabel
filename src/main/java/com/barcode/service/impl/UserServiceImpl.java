package com.barcode.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.barcode.dao.UserDao;
import com.barcode.model.security.SecuUser;
import com.barcode.service.UserService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Inject
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void save(SecuUser user) {
		userDao.persist(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean login(SecuUser user) {
		// TODO Auto-generated method stub
		return userDao.check(user);
	}


}