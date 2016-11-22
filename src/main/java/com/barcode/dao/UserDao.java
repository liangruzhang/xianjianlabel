package com.barcode.dao;

import com.barcode.model.security.SecuUser;


public interface UserDao {
	SecuUser persist(SecuUser user);
	boolean check(SecuUser user);
	public SecuUser findUser(String companyCode, String userId);
}
