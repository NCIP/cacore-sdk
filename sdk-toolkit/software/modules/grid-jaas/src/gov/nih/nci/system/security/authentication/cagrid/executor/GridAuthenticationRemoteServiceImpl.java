/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid.executor;

import gov.nih.nci.system.security.authentication.cagrid.client.executor.GridAuthenticationRemoteService;
import gov.nih.nci.system.security.authentication.cagrid.client.executor.GridAuthenticationService;

import java.util.Map;

import javax.security.auth.login.CredentialNotFoundException;


public class GridAuthenticationRemoteServiceImpl implements GridAuthenticationRemoteService
{
	GridAuthenticationService service;
	Map securityMap;

	public GridAuthenticationRemoteServiceImpl(GridAuthenticationService service, Map securityMap)
	{
		this.service = service;
		this.securityMap = securityMap;
	}

	public void authenticate(String bindKey, String username, String password, String authenticationServiceURL, String dorianServiceURL) throws CredentialNotFoundException, Exception
	{
		org.globus.gsi.GlobusCredential credential = service.authenticate(username, password, authenticationServiceURL,dorianServiceURL);
		bindCredential(bindKey,credential);
	}

	public void authenticate(String bindKey, String username, String password) throws CredentialNotFoundException, Exception
	{
		org.globus.gsi.GlobusCredential credential = (org.globus.gsi.GlobusCredential) service.authenticate(username, password);
		bindCredential(bindKey,credential);
	}

	private void bindCredential(String bindKey, org.globus.gsi.GlobusCredential credential)
	{
		securityMap.put(bindKey, credential);
	}

}