package com.barcode.service.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;







import com.barcode.dao.UserDao;
import com.barcode.exception.InvalidPasswordException;
import com.barcode.exception.SecurityServiceException;
import com.barcode.exception.UserNotFoundException;
import com.barcode.model.security.SecuUser;
import com.barcode.security.MD5Base64PasswordEncoder;
import com.barcode.security.PasswordEncoder;
import com.barcode.service.UserService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	private PasswordEncoder passwordEncoder = new MD5Base64PasswordEncoder();
	 
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public SecuUser checkPassword(String companyCode, String userId,
            String password) throws InvalidPasswordException,
            UserNotFoundException, SecurityServiceException {
        SecuUser user = findUser(companyCode, userId);
        if (user == null) {
            throw new UserNotFoundException("User: " + userId + " not found.");
        }
        String userPassword = user.getPassword();
        if (userPassword != null) {
            if (!userPassword.equals(password)) {
                throw new InvalidPasswordException("Invalid password.");
            }
        } else if (password != null) {
            throw new InvalidPasswordException("Invalid password.");
        }
        return user;
    }
	
	public SecuUser findUser(String companyCode, String userId) {
		return userDao.findUser(companyCode,userId);
	}

	public boolean hasCompanyAdminPermission(String secuUserPKId) {
		// TODO Auto-generated method stub
		return true;
	}

	public String encodePassword(String userId, String password) {
        return passwordEncoder.encode(userId, password);
    }

}
