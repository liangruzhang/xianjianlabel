package com.barcode.service;


import com.barcode.exception.InvalidPasswordException;
import com.barcode.exception.SecurityServiceException;
import com.barcode.exception.UserNotFoundException;
import com.barcode.model.security.SecuUser;


public interface UserService {
	public static final String DEFAULT_ADMIN_COMPANY = "admin";
    public static final String DEFAULT_ADMIN_COMPANY_NAME = "system admin default company";
    public static final String DEFAULT_SYSTEM_ADMIN_GROUP = "system Admin";

    public static final String DEFAULT_SYSTEM_USER = "system";
    public static final String COMPANY_ADMIN_PERMISSION="security.admin.company";
	void save(SecuUser user);
	
	boolean login(SecuUser user);
	public SecuUser checkPassword(String companyCode, String userId, String password)
            throws InvalidPasswordException, UserNotFoundException,
            SecurityServiceException;
	
	public boolean hasCompanyAdminPermission(String secuUserPKId);   
}
