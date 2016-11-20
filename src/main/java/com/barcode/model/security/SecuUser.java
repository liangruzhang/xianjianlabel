package com.barcode.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.barcode.model.common.AuditableEntity;


@Entity
@Table(name = "secu_company_user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"secu_company_code", "user_id"})
    } )
public class SecuUser extends AuditableEntity{
    @Column(name = "secu_company_code", length = 10, nullable = false)
    private String companyCode;
    
    @Column(name = "user_id", length = 10, nullable = false)
    private String userId;
    
    @Column(name = "user_name", length = 50, nullable=false)
    private String userName;
    
    @Column(name = "user_nm_localized", length = 50)
    private String userNameLocalized;
    
    @Column(name = "user_status", length = 10, nullable=false)
    private String userStatus;
    
    @Column(name = "user_description", length = 50)
    private String userDescription;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "password", length = 130)
    private String password;

    @Column(name = "password_effective_date")
    private Date passwordEffecDate;

    @Column(name = "password_effective_period_day", length = 6)
    private int passwordEffecPeriod;

    @Column(name = "last_password_reset_date")
    private Date passwordResetDate;

    @Column(name = "last_logon_date")
    private Date lastLogonDate;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordEffecDate() {
        return passwordEffecDate;
    }

    public void setPasswordEffecDate(Date passwordEffecDate) {
        this.passwordEffecDate = passwordEffecDate;
    }

    public int getPasswordEffecPeriod() {
        return passwordEffecPeriod;
    }

    public void setPasswordEffecPeriod(int passwordEffecPeriod) {
        this.passwordEffecPeriod = passwordEffecPeriod;
    }

    public Date getPasswordResetDate() {
        return passwordResetDate;
    }

    public void setPasswordResetDate(Date passwordResetDate) {
        this.passwordResetDate = passwordResetDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameLocalized() {
        return userNameLocalized;
    }

    public void setUserNameLocalized(String userNameLocalized) {
        this.userNameLocalized = userNameLocalized;
    }

	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}
    
}    
