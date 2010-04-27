package test.gov.nih.nci.cacoresdk;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import junit.framework.TestCase;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Base class used to support SDK ISO 21090 test cases
 *
 * 
 */
public abstract class SDKISOTestBase extends TestCase {

	private ApplicationService appService;
	private ApplicationService appServiceFromUrl;

	protected void setUp() throws Exception {
		super.setUp();
		appService = ApplicationServiceProvider.getApplicationService();
	}


	protected void tearDown() throws Exception 
	{
		appService = null;
		super.tearDown();
	}
	
	protected ApplicationService getApplicationService()
	{
		return appService;
	}
	
	
	protected ApplicationService getApplicationServiceFromUrl() throws Exception
	{
		String url = "http://localhost:8080/isoExample";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}
	
	protected ApplicationService getBadApplicationServiceFromUrl() throws Exception
	{
		String url = "http://badhost:8080/badcontext";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}

	@SuppressWarnings("unchecked")
	protected Collection search(DetachedCriteria criteria, String type) throws ApplicationException
	{
		return appService.query(criteria, type);
	}

	@SuppressWarnings("deprecation")
	protected Collection search(HQLCriteria criteria, String type) throws ApplicationException
	{
		return appService.query(criteria, type);
	}

	@SuppressWarnings("unchecked")
	protected Collection search(String path, Object searchObject) throws ApplicationException
	{
		return appService.search(path, searchObject);
	}
	
	public static String getTestCaseName()
	{
		return "SDK Base Test Case";
	}
	
}
