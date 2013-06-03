/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test;
import junit.framework.TestCase;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.util.ClassCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class SDKRESTfulTestBase extends TestCase {

	private ApplicationService appService;
	private ApplicationService appServiceFromUrl;
	protected String baseURL = "http://localhost:21080/examplerest";
	static String paths[] = {"application-config-client.xml"};
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			paths);;
	private ClassCache classCache;

	protected void setUp() throws Exception {
		super.setUp();
		//classCache = (ClassCache) ctx.getBean("ClassCache");
	}


	protected void tearDown() throws Exception
	{
		appService = null;
		super.tearDown();
	}

	protected ApplicationService getApplicationService()
	{
		if(appService == null)
		{
			try
			{
				appService = ApplicationServiceProvider.getApplicationService();
				//appService = ApplicationServiceProvider.getApplicationService("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser2","Psat123!@#");
			}
			catch(org.acegisecurity.AuthenticationCredentialsNotFoundException e)
			{
				System.out.println("Security is enabled...Getting secured application service...");
				try
				{
					appService = ApplicationServiceProvider.getApplicationService("SDKUser1","Psat123!@#");
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return appService;
	}

	public ClassCache getClassCache()
	{
		return classCache;
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
