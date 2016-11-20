package com.barcode.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.barcode.dao.UserDao;
import com.barcode.model.security.SecuUser;


@Repository
public class UserDaoImpl implements UserDao {
	private Logger logger = Logger.getLogger(UserDaoImpl.class);
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public SecuUser persist(SecuUser user) {
		entityManager.persist(user);
		return user;
	}

	public boolean check(SecuUser user) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery("select u.USER_ID from secu_company_user u where"+
										"	u.DELETED_FLAG='N' "+
										"	and u.USER_ID=? "+
										"	and u.PASSWORD=? "+
										"	and u.SECU_COMPANY_CODE=?");
		query.setParameter(1, user.getUserId());
		query.setParameter(2, user.getPassword());
		query.setParameter(3, user.getCompanyCode());
		try{
			
			String userId = (String)query.getSingleResult();
		}catch(NoResultException e){
			logger.error("get user error", e);
		}catch(NonUniqueResultException e){
			logger.error("get user error", e);
		}catch(Exception e){
			logger.error("get user error", e);
		}
		return false;
	}

	
}
