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

public class ShowMetadataAction extends ActionSupport {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(ShowMetadataAction.class
			.getName());
	
	public String execute() throws Exception {


		
		return SUCCESS;
	}

}