package test.security;

import gov.nih.nci.system.applicationservice.ApplicationService;

public interface GridApplicationServiceProvider {

	public ApplicationService getApplicationService(String username,
		   String password) throws Exception;

}
