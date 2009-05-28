package test.security;

import java.util.ArrayList;
import java.util.Collection;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import junit.framework.TestCase;

/**
 * @author Satish Patel, Dan Dumitru
 *   
 */
public abstract class SDKSecurityTestBase extends TestCase {

	protected boolean enableAttributeLevelSecurity=false;
	protected boolean enableInstanceLevelSecurity=false;
	protected boolean enableCaGridLoginModule=false;
	protected String serverURL="";
	
	public static String getTestCaseName() {
		return "SDK Security Base Test Case";
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		enableAttributeLevelSecurity = Boolean.parseBoolean(System.getProperty("enableAttributeLevelSecurity"));
		enableInstanceLevelSecurity = Boolean.parseBoolean(System.getProperty("enableInstanceLevelSecurity"));
		enableCaGridLoginModule = Boolean.parseBoolean(System.getProperty("enableCaGridLoginModule"));
		serverURL = System.getProperty("serverURL");
		System.out.println("Login config = "+System.getProperty("java.security.auth.login.config"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	protected ApplicationService getAppSvcUser1() throws Exception {
		if (enableCaGridLoginModule){
			GridApplicationServiceProvider initializer = (GridApplicationServiceProvider)(Class.forName("test.security.GridApplicationServiceImpl")).newInstance();
			return initializer.getApplicationService("SDKUser1","Psat123!@#");
		}			
		return ApplicationServiceProvider.getApplicationService("user1","password");
	}
	
	protected ApplicationService getAppSvcUser2() throws Exception {
		if (enableCaGridLoginModule){
			GridApplicationServiceProvider initializer = (GridApplicationServiceProvider)(Class.forName("test.security.GridApplicationServiceImpl")).newInstance();
			return initializer.getApplicationService("SDKUser2","Psat123!@#");
		}		
		return ApplicationServiceProvider.getApplicationService("user2","password");
	}
	
	
	protected ApplicationService getAppSvcBadUser() throws Exception {
		if (enableCaGridLoginModule){
			return ApplicationServiceProvider.getApplicationService("badUser","password");
		}
		return ApplicationServiceProvider.getApplicationService("badUser","password");
	}
	
	protected ApplicationService getApplicationServiceGroup1() throws Exception {
		Collection<String> groups = new ArrayList<String>();
		groups.add("Group1");
		
		return ApplicationServiceProvider.getApplicationService(groups);
	}
	
	protected ApplicationService getApplicationServiceGroup2() throws Exception {
		Collection<String> groups = new ArrayList<String>();
		groups.add("Group2");
		return ApplicationServiceProvider.getApplicationService(groups);
	}
	
	protected ApplicationService getApplicationServiceGroup3() throws Exception {
		Collection<String> groups = new ArrayList<String>();
		groups.add("Group3");
		return ApplicationServiceProvider.getApplicationService(groups);
	}
	
	
	protected ApplicationService getApplicationServiceGroups1and3() throws Exception {
		Collection<String> groups = new ArrayList<String>();
		groups.add("Group1");
		groups.add("Group3");
		return ApplicationServiceProvider.getApplicationService(groups);
	}
	
	protected ApplicationService getApplicationServiceBadGroup() throws Exception {
		Collection<String> groups = new ArrayList<String>();
		groups.add("BadGroup");
		return ApplicationServiceProvider.getApplicationService(groups);
	}
}
