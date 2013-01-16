package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//import gov.nih.nci.common.util.Constant;
//import gov.nih.nci.common.util.SecurityConfiguration;
//import gov.nih.nci.system.applicationservice.AuthenticationException;
//import gov.nih.nci.system.server.mgmt.SecurityEnabler;
import gov.nih.nci.security.exceptions.CSException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import gov.nih.nci.system.security.acegi.authentication.CSMUserDetailsService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginReset extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1234567890L;
//	private SecurityEnabler securityEnabler;
	private Map session;

	private static Logger log = Logger.getLogger(LoginReset.class
			.getName());

	WebApplicationContext ctx;
	
	public String execute() throws Exception {

		if (isInvalid(getUserName()) || isInvalid(getOldPassword()) || isInvalid(getNewPassword()) || !getNewPassword().equals(getRepeatPassword()))
			return INPUT;

		ServletContext context = ServletActionContext.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		CSMUserDetailsService userDetailsService = (CSMUserDetailsService) ctx.getBean("userDetailsService");
		try
		{
			userDetailsService.changePassword(getUserName(), getOldPassword(), getNewPassword(), getRepeatPassword());
			request.setAttribute("password_reset_successful", "true");
		}
		catch(CSException e)
		{
			e.printStackTrace();
			request.setAttribute("password_reset_error", e.getMessage());
		}
		
		return SUCCESS;
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	
}