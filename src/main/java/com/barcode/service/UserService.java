package com.barcode.service;

import com.barcode.model.security.SecuUser;


public interface UserService {
	
	void save(SecuUser user);
	
	boolean login(SecuUser user);
	
}
