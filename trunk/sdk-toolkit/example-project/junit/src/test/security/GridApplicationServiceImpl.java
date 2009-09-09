package test.security;

import org.globus.gsi.GlobusCredential;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.security.authentication.cagrid.GridAuthenticationHelper;

public class GridApplicationServiceImpl implements GridApplicationServiceProvider{

	public ApplicationService getApplicationService(String username,
			String password) throws Exception {

		GridAuthenticationHelper loginHelper = new GridAuthenticationHelper("grid");
		GlobusCredential proxy = loginHelper.login(username,password);
		System.out.println(proxy);
		System.out.println("Identity:"+proxy.getIdentity());
		return ApplicationServiceProvider.getApplicationService(proxy);
	}
}