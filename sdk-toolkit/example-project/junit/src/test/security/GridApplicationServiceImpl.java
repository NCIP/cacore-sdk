/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.security;

import org.globus.gsi.GlobusCredential;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.security.authentication.cagrid.client.GridAuthenticationHelper;

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