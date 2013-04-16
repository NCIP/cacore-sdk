/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid.executor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationClient;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.system.security.authentication.cagrid.client.executor.GridAuthenticationService;

import javax.security.auth.login.CredentialNotFoundException;

import org.apache.log4j.Logger;
import org.cagrid.gaards.dorian.client.GridUserClient;
import org.cagrid.gaards.dorian.federation.CertificateLifetime;
import org.globus.gsi.GlobusCredential;

public class GridAuthenticationServiceImpl implements GridAuthenticationService {

	protected static Logger log = Logger.getLogger(GridAuthenticationServiceImpl.class.getName());

	String authenticationServiceURL;
	String dorianServiceURL;
	Integer proxyLifetime;
	Integer delegationPathLength;

	public GridAuthenticationServiceImpl(String authenticationServiceURL,String dorianServiceURL, Integer proxyLifetime,Integer delegationPathLength)
	{
		this.authenticationServiceURL = authenticationServiceURL;
		this.dorianServiceURL = dorianServiceURL;
		this.proxyLifetime = proxyLifetime;
		this.delegationPathLength = delegationPathLength;
	}

	public GlobusCredential authenticate(String username, String password) throws CredentialNotFoundException,Exception
	{
		return authenticate(username,password,authenticationServiceURL,dorianServiceURL,proxyLifetime,delegationPathLength);
	}

	public GlobusCredential authenticate(String username, String password, String authenticationServiceURL, String dorianServiceURL) throws CredentialNotFoundException,Exception
	{
		return authenticate(username,password,authenticationServiceURL,dorianServiceURL,proxyLifetime,delegationPathLength);
	}

	protected GlobusCredential authenticate(String username, String password, String authenticationServiceURL,String dorianServiceURL, Integer proxyLifetime,Integer delegationPathLength) throws CredentialNotFoundException,Exception
	{
		try{
			Credential cred = new Credential();
			BasicAuthenticationCredential bac = new BasicAuthenticationCredential();
			bac.setUserId(username);
			bac.setPassword(password);
			cred.setBasicAuthenticationCredential(bac);

			//Requested Grid Credential lifetime (12 hours)
			CertificateLifetime lifetime = new CertificateLifetime();
			lifetime.setHours(proxyLifetime.intValue());

			//Authenticate to the IdP (DorianIdP) using credential
			AuthenticationClient authClient = new AuthenticationClient(authenticationServiceURL, cred);
			SAMLAssertion saml = authClient.authenticate();
			//Request Grid Credential
			GridUserClient dorian = new GridUserClient(dorianServiceURL);
			GlobusCredential proxy = dorian.requestUserCertificate(saml, lifetime);
			return proxy;
		}
		catch(InvalidCredentialFault icf)
		{
			icf.printStackTrace();
			log.error(printStackTrace(icf));
			throw new CredentialNotFoundException("Invalid credentials");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(printStackTrace(e));
			throw new Exception(e.getMessage());
		}
	}

	private String printStackTrace(Exception e) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		PrintStream p = new PrintStream( b);
		e.printStackTrace( p );
		p.flush();
		return b.toString();
	}
}
