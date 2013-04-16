/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

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
	protected String serverURL="http://localhost:21080/example";
	
	public static String getTestCaseName() {
		return "SDK Security Base Test Case";
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		enableAttributeLevelSecurity = Boolean.parseBoolean(System.getProperty("enableAttributeLevelSecurity"));
		enableInstanceLevelSecurity = Boolean.parseBoolean(System.getProperty("enableInstanceLevelSecurity"));
		enableCaGridLoginModule = Boolean.parseBoolean(System.getProperty("enableCaGridLoginModule"));
		String tempServerURL = System.getProperty("serverURL");
		if(tempServerURL!=null){
			serverURL=tempServerURL;
		}
		System.out.println("SERVER URL  "+serverURL+"  Login config = "+System.getProperty("java.security.auth.login.config"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public String getServerURL() {
		return serverURL;
	}
	
	protected ApplicationService getAppSvcUser1() throws Exception {
		if (enableCaGridLoginModule){
			GridApplicationServiceProvider initializer = (GridApplicationServiceProvider)(Class.forName("test.security.GridApplicationServiceImpl")).newInstance();
			return initializer.getApplicationService("SDKUser1","Psat123!@#");
		}			
		return ApplicationServiceProvider.getApplicationService("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser1","Psat123!@#");
	}
	
	protected ApplicationService getAppSvcUser2() throws Exception {
		if (enableCaGridLoginModule){
			GridApplicationServiceProvider initializer = (GridApplicationServiceProvider)(Class.forName("test.security.GridApplicationServiceImpl")).newInstance();
			return initializer.getApplicationService("SDKUser2","Psat123!@#");
		}		
		return ApplicationServiceProvider.getApplicationService("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser2","Psat123!@#");
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
