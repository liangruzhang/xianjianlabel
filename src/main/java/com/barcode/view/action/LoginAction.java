package com.barcode.view.action;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;








import com.barcode.model.security.SecuUser;
import com.barcode.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(LoginAction.class);
	private int id;
	private SecuUser secuUser;
	private UserService userService;

	
	public String add() {
		
		userService.save(secuUser);
		return SUCCESS;
	}
	
	public String login(){
		//获取request对象
		 HttpServletRequest request=ServletActionContext.getRequest();
		 //获取session对象
		 //HttpSession session=request.getSession();
		 String name=request.getParameter("userId");
		 
		 String pass=request.getParameter("password");
		 String company=request.getParameter("companyCode");
		 
		 secuUser = new SecuUser();
		 secuUser.setCompanyCode(company);
		 secuUser.setUserId(name);
		 secuUser.setPassword(pass);
		 
		if(StringUtils.isEmpty(secuUser.getCompanyCode())){
			request.setAttribute("errorMessage", "company code can't be null!");
			return INPUT;
		}
		
		if(StringUtils.isEmpty(secuUser.getUserId())){
			request.setAttribute("errorMessage", "user name can't be null!");
			return INPUT;
		}
		
		if(StringUtils.isEmpty(secuUser.getPassword())){
			request.setAttribute("errorMessage", "password can't be null!");
			return INPUT;
		}
		log.info("company:"+secuUser.getCompanyCode()+",user:"+secuUser.getUserId());
		if(userService.login(secuUser)){
			log.info("user:"+secuUser.getUserId()+" login success!");
			return SUCCESS;
		}
		return ERROR;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public SecuUser getSecuUser() {
		return secuUser;
	}

	public void setSecuUser(SecuUser secuUser) {
		this.secuUser = secuUser;
	}

	public UserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
