package gov.nih.nci.common.util;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.dao.impl.orm.ORMConnection;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;



public class WebStartupUtil extends GenericServlet
{
	private static final long serialVersionUID = 1L;

	 private static Logger log= Logger.getLogger(WebStartupUtil.class.getName());

	 
	/* 
	 * Initializes the connection factory so that when the application hits the server, 
	 * results are returned at faster pace for the first time user
	 */
	public void init() throws ServletException
	{
		super.init();
		try
		{
			synchronized (this)
			{
				log.info("Initializing SDK application");
				ORMConnection.getInstance();
				log.info("SDK Application startup completed");
			}
		} 
		catch (ApplicationException e)
		{
			log.error("Error in WebStartupUtil class :",e);
		}
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException
	{
		log.error("Invokation in the service method of WebStartupUtil class");
	}
}