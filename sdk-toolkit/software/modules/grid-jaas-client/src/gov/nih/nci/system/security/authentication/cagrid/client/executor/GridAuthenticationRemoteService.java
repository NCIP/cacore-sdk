/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid.client.executor;

import javax.security.auth.login.CredentialNotFoundException;

public interface GridAuthenticationRemoteService
{
	public void authenticate(String bindKey, String username, String password, String authenticationServiceURL, String dorianServiceURL) throws CredentialNotFoundException,Exception;

	public void authenticate(String bindKey, String username, String password) throws CredentialNotFoundException,Exception;
	
}