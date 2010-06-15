package gov.nih.nci.system.proxy;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.security.util.StringUtilities;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CSMHTTPFilter implements Filter
{

	private FilterConfig filterConfig = null;
	private static SecurityEnabler securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());

	public void init(FilterConfig config) throws ServletException
	{
		this.filterConfig = config;
	}

	public void destroy()
	{
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
	
			String userName = (String)httpServletRequest.getParameter(Constant.USER_NAME);
			String password = (String)httpServletRequest.getParameter(Constant.PASSWORD);
			HttpSession session = httpServletRequest.getSession();
			if ( null != session.getAttribute(Constant.USER_NAME))
				userName = (String)session.getAttribute(Constant.USER_NAME);
			if ( null != session.getAttribute(Constant.PASSWORD))
				password = (String)session.getAttribute(Constant.PASSWORD);			
			ClientInfo clientInfo = new ClientInfo();
			clientInfo.setUserName(userName);
			clientInfo.setPassword(password);		
			ClientInfoThreadVariable.setClientInfo(clientInfo);
	
			// move on to the next
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

}