package com.barcode.view.interceptor;

import java.util.Date;

import javax.security.auth.Subject;

public class AuthenticatedSession {
    public static final String
        DEFAULT_SESSION_ID_COOKIE_NAME = "JSESSIONID";    
    private String sessionId;
    private long creationTime;
    private long lastAccessedTime;
    private long userLogonActivityId;
    private Subject subject;
    private String remoteAddress;
    private boolean hasCompanyAdminPermission;
    private String companyCode;
    private String secuUserPKId;
    private String userLoginId;
    private Date lastLogonDate;
    private String loginLanguade;

    public Date getLastLogonDate() {
        return lastLogonDate;
    }
    public void setLastLogonDate(Date lastLogonDate) {
        this.lastLogonDate = lastLogonDate;
    }
    public String getSecuUserPKId() {
        return secuUserPKId;
    }
    public void setSecuUserPKId(String secuUserPKId) {
        this.secuUserPKId = secuUserPKId;
    }
    public String getUserLoginId() {
        return userLoginId;
    }
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }
    public String getSessionId() {
        return sessionId;
    }
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public boolean isHasCompanyAdminPermission() {
        return hasCompanyAdminPermission;
    }
    public void setHasCompanyAdminPermission(boolean hasCompanyAdminPermission) {
        this.hasCompanyAdminPermission = hasCompanyAdminPermission;
    }
    public AuthenticatedSession(String sessionId) {
        this.sessionId = sessionId;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessedTime = creationTime;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public String getRemoteAddress() {
        return this.remoteAddress;
    }
    public void setRemoteAddress(String addr) {
        this.remoteAddress = addr;
    }
    public long getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }
    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
    public long getUserLogonActivityId() {
        return userLogonActivityId;
    }
    public void setUserLogonActivityId(long userLogonActivityId) {
        this.userLogonActivityId = userLogonActivityId;
    }
    public String getLoginLanguade() {
        return loginLanguade;
    }
    public void setLoginLanguade(String loginLanguade) {
        this.loginLanguade = loginLanguade;
    }
}
