/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.struts.action;

import java.util.Map;

//import gov.nih.nci.common.util.Constant;
//import gov.nih.nci.common.util.SecurityConfiguration;
//import gov.nih.nci.system.applicationservice.AuthenticationException;
//import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1234567890L;
//	private SecurityEnabler securityEnabler;
	private Map session;

	private static Logger log = Logger.getLogger(Login.class
			.getName());
	
	public String execute() throws Exception {

		if (isInvalid(getUsername()) || isInvalid(getPassword()))
			return INPUT;

		
		return SUCCESS;
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSession(Map session) {
		this.session = session;
	}
}