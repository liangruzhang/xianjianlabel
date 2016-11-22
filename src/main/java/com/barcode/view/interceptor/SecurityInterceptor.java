package com.barcode.view.interceptor;

import java.io.IOException;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.barcode.exception.InvalidPasswordException;
import com.barcode.exception.SecurityServiceException;
import com.barcode.exception.UserNotFoundException;
import com.barcode.model.security.SecuUser;
import com.barcode.security.BasicUserPrincipal;
import com.barcode.security.UserPrincipal;
import com.barcode.service.UserService;
import com.barcode.util.Base64Encoder;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(SecurityInterceptor.class);
	
private UserService userService;
	
	public UserService getUserService() {
	return userService;
}

@Inject
public void setUserService(UserService userService) {
	this.userService = userService;
}

	private String loginActionURL;
	
	/**
     * The default size used to create the hash map for storing the sessions.
     */
    private static final int DEFAULT_SESSION_TABLE_SIZE = 500;

    /**
     * The default session clean up interval in milliseconds.
     */
    private static final long DEFAULT_SESSION_CLEANUP_INTERVAL = 300000; // in

    // milliseconds

    /**
     * The default maximum session idle interval in milliseconds.
     */
    private static final long DEFAULT_MAX_SESSION_IDLE_INTERVAL = 900000; // in

    // milliseconds

    private Base64Encoder encoder = new Base64Encoder();

    /**
     * The session table.
     */
    private Map sessions = new HashMap(DEFAULT_SESSION_TABLE_SIZE);

    /**
     * A UID which uniquely identifies this instance of filter. This identifier
     * is used in conjunction with the lastCreatedSessionIndex to form a unique
     * session id across multiple machines.
     */
    private final UID instanceUid = new UID();

    /**
     * A session index incremented every time a new session is created. This
     * index is used in conjunction with the instanceUid to form a unique
     * session id across multiple machines.
     */
    private int lastCreatedSessionIndex = 0;

    /**
     * The session cleanup interval in milliseconds. This value can be
     * configured using init parameter "sessionCleanupInterval".
     */
    private long sessionCleanupInterval = DEFAULT_SESSION_CLEANUP_INTERVAL;
    
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		logger.info("SecurityInterceptor start.");
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();
		
		ActionContext context = invocation.getInvocationContext();
		
		return doFilter(request, response, context);
	}
	
	

    public long getSessionCleanupInterval() {
		return sessionCleanupInterval;
	}

	public void setSessionCleanupInterval(long sessionCleanupInterval) {
		this.sessionCleanupInterval = sessionCleanupInterval;
	}

	/**
     * The maximum session idle interval in milliseconds. This value can be
     * configured using init parameter "maxSessionIdleInterval".
     */
    private long maxSessionIdleInterval = DEFAULT_MAX_SESSION_IDLE_INTERVAL;

    public long getMaxSessionIdleInterval() {
		return maxSessionIdleInterval;
	}

	public void setMaxSessionIdleInterval(long maxSessionIdleInterval) {
		if(maxSessionIdleInterval>0){
			
			this.maxSessionIdleInterval = maxSessionIdleInterval;
		}
	}

	/**
     * The maximum session life in milliseconds.
     */
    private long maxSessionLifeTime = 0;

    public long getMaxSessionLifeTime() {
		return maxSessionLifeTime;
	}

	public void setMaxSessionLifeTime(long maxSessionLifeTime) {
		this.maxSessionLifeTime = maxSessionLifeTime;
	}

	/**
     * The session id cookie name. This value can be configured using init
     * parameter "sessionIdCookieName".
     */
    private String sessionIdCookieName = AuthenticatedSession.DEFAULT_SESSION_ID_COOKIE_NAME;

    public String getSessionIdCookieName() {
		return sessionIdCookieName;
	}

	public void setSessionIdCookieName(String sessionIdCookieName) {
		if(sessionIdCookieName!=null &&!sessionIdCookieName.trim().equals("")){
			
			this.sessionIdCookieName = sessionIdCookieName;
		}
	}

	/**
     * The default realm name.
     */
    private String defaultRealmName="lifetech";

    public String getDefaultRealmName() {
		return defaultRealmName;
	}

	public void setDefaultRealmName(String defaultRealmName) {
		if(defaultRealmName!=null &&!defaultRealmName.trim().equals("")){
		this.defaultRealmName = defaultRealmName;
		}
	}

	private Thread sessionCleanupThread;


    public SecurityInterceptor() {
    }

	public void init() {
		this.sessionCleanupThread = new SessionCleanupThread("SecurityInterceptor");
        this.sessionCleanupThread.start();

            logger.debug("defaultRealName = " + defaultRealmName);
            logger.debug("sessionCleanupInterval = "
                    + this.sessionCleanupInterval);
            logger.debug("maxSessionIdleInterval = "
                    + this.maxSessionIdleInterval);
            logger.debug("sessionIdCookieName = " + this.sessionIdCookieName);
	}

	/**
     * Initializes this servlet filter using the initialization parameters as
     * specified in the config.
     * 
     * @param config
     *            FilterConfig
     * @throws ServletException
     */
//    public void init(FilterConfig config) throws ServletException {
//        String loginActionConfigURL = config.getInitParameter("loginActionURL");
//        if (loginActionConfigURL != null || !"".equals(loginActionConfigURL)) {
//            this.loginActionURL=loginActionConfigURL;
//        }
//        if (!loginActionURL.startsWith("/")) {
//            loginActionURL="/"+loginActionURL;
//        }
//
//        try {
//            this.sessionCleanupInterval = Long.parseLong(config
//                    .getInitParameter("sessionCleanupInterval"));
//        } catch (Exception e) {
//        }
//
//        try {
//            this.maxSessionLifeTime = Long.parseLong(config
//                    .getInitParameter("maxSessionLifeTime"));
//        } catch (Exception e) {
//        }
//
//        try {
//            this.maxSessionIdleInterval = Long.parseLong(config
//                    .getInitParameter("maxSessionIdleInterval"));
//        } catch (Exception e) {
//        }
//
//        this.sessionIdCookieName = config
//                .getInitParameter("sessionIdCookieName");
//        if (sessionIdCookieName == null) {
//            sessionIdCookieName = AuthenticatedSession.DEFAULT_SESSION_ID_COOKIE_NAME;
//        }
//
//        this.defaultRealmName = config.getInitParameter("defaultRealmName");
//        if (defaultRealmName == null) {
//            defaultRealmName = config.getServletContext()
//                    .getServletContextName();
//        }
//        this.sessionCleanupThread = new SessionCleanupThread(config
//                .getFilterName());
//        this.sessionCleanupThread.start();
//
//        thisConf = config;
//            logger.debug("defaultRealName = " + defaultRealmName);
//            logger.debug("sessionCleanupInterval = "
//                    + this.sessionCleanupInterval);
//            logger.debug("maxSessionIdleInterval = "
//                    + this.maxSessionIdleInterval);
//            logger.debug("sessionIdCookieName = " + this.sessionIdCookieName);
//
//    }

    public String getLoginActionURL() {
		return loginActionURL;
	}

	public void setLoginActionURL(String loginActionURL) {
		this.loginActionURL = loginActionURL;
		if (!loginActionURL.startsWith("/")) {
			this.loginActionURL="/"+loginActionURL;
        }
	}

	public String doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, ActionContext context)
            throws Exception,IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
            logger.debug("Received HTTP request: " + request.getPathInfo());
            logger.debug("Method: " + request.getMethod());
            logger.debug("Content-Type: " + request.getHeader("Content-Type"));
            logger.debug("Content-Length: "
                    + request.getHeader("Content-Length"));
            logger.debug("Content-Encoding: "
                    + request.getHeader("Content-Encoding"));
            logger.debug("Transfer-Encoding: "
                    + request.getHeader("Transfer-Encoding"));
            logger
                    .debug("Authorization: "
                            + request.getHeader("Authorization"));
            logger.debug("Path: " + request.getPathInfo());
        AuthenticatedSession session = null;
        String sessionID = null;
        // Check if the request carries a session id:
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (sessionIdCookieName.equals(cookie.getName())) {
                    sessionID = cookie.getValue();
                    session = (AuthenticatedSession) this.sessions
                            .get(sessionID);
                    break;
                }
            }
        }
        String requestUrl = getRequestUrl(request);
        
        if (isLoginActionUrl(requestUrl)) {
            String action = request.getParameter("action");
            if ("login".equals(action)) {
              return doAuthenticate(sessionID, request, response, context);
            } else if ("logoff".equals(action)) {
                //clear cache data
                this.doLogOff(session);
                return Action.INPUT;
            } 
//            chain.doFilter(request, response);
            context.getActionInvocation().invoke();
            return Action.SUCCESS;

        }
        if (session == null) {
            
            	request.setAttribute("errorMessage", "session time out,please login in again!");
            	return "timeout";
//                ((HttpServletResponse) response).sendRedirect(loginActionURL+"?action=timeout&errorMessage='session time out , please login in again'");
                        
        } else { // session != null
                logger.debug("Session found: principal="
                        + session.getSubject().getPrincipals());
            doRequest(session, request, response, context);
        }
        // if (session!=null) {
        // doRequest(session, request, response, chain);
        // } else {
        // chain.doFilter(request, response);
        // }
        return Action.SUCCESS;
    }

    private boolean isLoginActionUrl(String requestUrl) {
        if (this.loginActionURL.equals(requestUrl)) {
            return true;
        }
        return false;
    }

    private boolean isGWTServiceUrl(String requestUrl) {
        if (requestUrl != null && requestUrl.startsWith("/service/")) {
            return true;
        }
        return false;
    }

    private String getRequestUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url == null) {
            url = "/";
        }
        if (!url.startsWith("/")) {
            url = "/"+url.substring(1);
        }
            logger.debug("Request [" + url + "]");
        return url;
    }

    private void setLoginInfoAttribute(HttpServletRequest request,
            HttpServletResponse response,  ActionContext context,String message)
            throws ServletException, IOException {
        String companyCode = request.getParameter("companyCode");
        String userId = request.getParameter("userId");
        request.setAttribute("errorMessage", message);
        request.setAttribute("companyCode", companyCode);
        request.setAttribute("userId", userId);
    }
    private void doLogOff (AuthenticatedSession session) {
        if (session==null) return;
        String sessionID = session.getSessionId();
        synchronized (this) {
            sessions.remove(sessionID);
        }
//        long userLogonActivityId = session.getUserLogonActivityId();
//        try {
//            wmsSecurityService.endUserActivityLog(userLogonActivityId,UserActivityEndStatusPolicy.USER_INITIATED);
//        } catch (Exception ex) {
//            logger.error("Failed to log user activity", ex);
//        }        
    }
    private String doAuthenticate(String sessionID, HttpServletRequest request,
            HttpServletResponse response, ActionContext context)
            throws IOException, ServletException {
        String companyCode = request.getParameter("companyCode");
        if (companyCode == null || "".equals(companyCode)) {
            companyCode = userService.DEFAULT_ADMIN_COMPANY;
        }
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String loginLanguade = request.getParameter("languageRadio");
        // String password =
        // base64Encoder.encodeString(request.getParameter("password"));
            logger.debug("Attempt to login : " + userId);

        Exception exception = null;
        SecuUser user = null;
        boolean hasCompanyAdminPermission = false;
        try {
            user = userService.checkPassword(companyCode, userId,
                    password);
            hasCompanyAdminPermission = userService
                    .hasCompanyAdminPermission(user.getId());
        } catch (InvalidPasswordException e) {
            exception = e;
            setLoginInfoAttribute(request, response,context, "Invalid Password");
            return Action.ERROR;
        } catch (UserNotFoundException e) {
            exception = e;
            setLoginInfoAttribute(request, response, context, "user: " + userId
                    + " don't exist");
            return Action.ERROR;
        } catch (SecurityServiceException e) {
            exception = e;
            logger.error("Failed to check user password", e);
            setLoginInfoAttribute(request, response,context,
                    "server error,Failed to check user password");
            return Action.ERROR;
        } catch (Exception e) {
            exception = e;
            logger.error("Failed to check user password", e);
            setLoginInfoAttribute(request, response,context,
                    "server error,Failed to check user password");
            return Action.ERROR;
        } finally {
            if (sessionID == null) {
                String machine = request.getRemoteHost() + "/"
                        + request.getRemoteAddr();
                String status = "Y";
                String message = "";
                if (exception != null) {
                    status = "N";
                    message = exception.getClass().getName() + ": "
                            + exception.toString();
                }
                // try {
                // UserAccountPolicy.getInstance().logLoginAttempt(
                // userId, machine, status, message);
                // } catch (Exception e) {
                // logger.error("Failed to log login attempt", e);
                // }
            }
        }

        // create or update the sessions map
        if (sessionID == null) {
            sessionID = composeSessionId(instanceUid, lastCreatedSessionIndex++);
            addSessionIdCookie(sessionID, response);
        }
        AuthenticatedSession session = new AuthenticatedSession(sessionID);
        session.setCompanyCode(user.getCompanyCode());
        session.setSecuUserPKId(user.getId());
        session.setUserLoginId(user.getUserId());
        session.setHasCompanyAdminPermission(hasCompanyAdminPermission);
        session.setLastLogonDate(user.getLastLogonDate());
        session.setLoginLanguade(loginLanguade);
        Subject subject = createClientSubject(defaultRealmName, userId,
                password);
        if (subject == null) {
            // Failed to create a client subject; remove the created
            // session and send HTTP error to indicate it is an
            // internal server problem.
            synchronized (this) {
                sessions.remove(sessionID);
            }
//            ((HttpServletResponse) response).sendRedirect(loginActionURL);
            return  Action.ERROR;
        }
        session.setSubject(subject);
        sessions.put(sessionID, session);
        request.setAttribute(
                AuthenticatedSession.DEFAULT_SESSION_ID_COOKIE_NAME,
                session);
            logger.debug("login okay");
            return Action.SUCCESS;
    }

    protected Subject createClientSubject(String realm, String userName,
            String password) {
        Subject subject = new Subject();
        UserPrincipal p = new BasicUserPrincipal(userName);
        subject.getPrincipals().add(p);
        return subject;
    }

    private void addSessionIdCookie(String sessionId,
            HttpServletResponse response) {
        Cookie cookie = new Cookie(sessionIdCookieName, sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(-1); // non-persistent cookie
        response.addCookie(cookie);
    }

    /**
     * Compose a session id using the specified the global UID and session id.
     * 
     * @param uid
     *            UID
     * @param index
     *            int
     * @return String
     */
    private String composeSessionId(UID uid, int index) {
        String token = uid.toString() + ":" + index;
        byte[] csum = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(token.getBytes());
            csum = md5.digest();
        } catch (Exception e) {
            logger.error("Unable to calaculate checksum for session id", e);
            return null;
        }
        return token + ":" + encoder.encodeBytes(csum);
    }

    protected void doRequest(AuthenticatedSession session,
            HttpServletRequest request, HttpServletResponse response,
            final ActionContext context) throws IOException, ServletException {
        final HttpServletRequest thisRequest = new BasicServletRequestWrapper(
                request, session.getSubject());
        final HttpServletResponse thisResponse = response;

        try {
            session.setLastAccessedTime(System.currentTimeMillis());
            request.setAttribute(
                    AuthenticatedSession.DEFAULT_SESSION_ID_COOKIE_NAME,
                    session);
            PrivilegedExceptionAction action = new PrivilegedExceptionAction() {
                public Object run() throws Exception,IOException, ServletException {
//                    chain.doFilter(thisRequest, thisResponse);
                    return context.getActionInvocation().invoke();
                }
            };

            Object secuIdx = null;
            try {
                UserPrincipal userPrincipal = null;
                Principal jassPrincipal = getPrincipal(session.getSubject());
                if (jassPrincipal != null) {
                    if (jassPrincipal instanceof UserPrincipal) {
                        userPrincipal = (UserPrincipal) jassPrincipal;
                    } else {
                        userPrincipal = new BasicUserPrincipal(jassPrincipal
                                .getName());
                    }
                    // secuIdx =
                    // SecurityContext.getInstance().push(session.getSubject(),
                    // userPrincipal);
                }
                runAs(session.getSubject(), action);
            } finally {
                // if (secuIdx != null) {
                // SecurityContext.getInstance().pop(secuIdx);
                // }
            }
        } catch (PrivilegedActionException e) {
            Exception cause = e.getException();
            if (cause instanceof IOException) {
                throw (IOException) cause;
            }
            if (cause instanceof ServletException) {
                throw (ServletException) cause;
            }
            throw new ServletException("Failed to perform runAs()", e);
        }
    }

    public void destroy() {
        // Stop session cleanup thread:
        Thread thread = this.sessionCleanupThread;
        if (thread != null) {
            this.sessionCleanupThread = null;
            thread.interrupt();
        }
    }

    /**
     * Runs the action as the specified subject. This method can be overridden
     * to provide an implementation appropriate for the actual runtime
     * environment. The default is to run the action using
     * Subject.runAs(subject, action).
     * 
     * @param subject
     *            Subject
     * @param action
     *            PrivilegedExceptionAction
     * @throws PrivilegedActionException
     */
    protected void runAs(Subject subject, PrivilegedExceptionAction action)
            throws PrivilegedActionException {
        Subject.doAs(subject, action);
    }

    /**
     * Returns a principal from the specified subject. This method returns the
     * first principal from the principal set of the specified subject. A
     * subclass can override this method to return a principal which is
     * appropriate to the actual runtime environment.
     * 
     * @param subject
     *            Subject
     * @return Principal
     */
    protected Principal getPrincipal(Subject subject) {
        Set principals = subject.getPrincipals();
        if (principals.size() > 0) {
            return (Principal) principals.iterator().next();
        }
        return null;
    }

    /**
     * Cleans up the session table. Any session with last accessed time less
     * than the current time minus the max session idle time will be removed
     * from the table.
     */
    private synchronized void cleanupSessions() {
            logger.debug("Start cleaning up wms sessions.");
        int total = sessions.size();
        int removed = 0;
        Iterator it = sessions.entrySet().iterator();
        long time = System.currentTimeMillis();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            AuthenticatedSession session = (AuthenticatedSession) entry
                    .getValue();
            if (session != null
                    && session.getLastAccessedTime() < time
                            - maxSessionIdleInterval) {
                it.remove();
                removed++;
                    logger.debug("Authenticated session: "
                            + session.getSessionId() + " removed.");
            }
        }
            logger.debug("Number of Authenticated sessions removed: " + removed
                    + ", " + (total - removed) + " remained.");
    }

    private class BasicServletRequestWrapper extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        private Subject subject;

        private Principal principal;

        public BasicServletRequestWrapper(HttpServletRequest request,
                Subject subject) throws ServletException {
            super(request);
            this.request = request;
            this.subject = subject;
            this.principal = getPrincipal(subject);
        }

        public String getRemoteUser() {
            if (principal != null) {
                return principal.getName();
            }
            return null;
        }

        public Principal getUserPrincipal() {
            return principal;
        }
    }

    private class SessionCleanupThread extends Thread {
        public SessionCleanupThread(String filterName) {
            super(filterName + " cleanup thread");
            this.setDaemon(true);
        }

        public void run() {
            Thread thread = Thread.currentThread();
            while (thread == sessionCleanupThread) {
                try {
                    cleanupSessions();
                } catch (Exception e) {
                    logger
                            .error(
                                    "Exception encountered while doing session cleanup.",
                                    e);
                }
                try {
                    Thread.sleep(sessionCleanupInterval);
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
