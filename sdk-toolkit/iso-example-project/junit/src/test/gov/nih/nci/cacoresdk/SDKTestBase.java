package test.gov.nih.nci.cacoresdk;
import junit.framework.TestCase;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;



/**
 * @author Satish Patel
 * 
 */
public abstract class SDKTestBase extends TestCase {

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
		String url = "http://localhost:8080/example";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}
	
	protected ApplicationService getBadApplicationServiceFromUrl() throws Exception
	{
		String url = "http://badhost:8080/badcontext";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}
	
	public static String getTestCaseName()
	{
		return "SDK Base Test Case";
	}

}
