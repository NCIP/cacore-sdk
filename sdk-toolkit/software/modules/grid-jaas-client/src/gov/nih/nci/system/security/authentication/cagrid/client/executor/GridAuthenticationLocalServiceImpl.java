/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid.client.executor;

import java.util.Map;
import java.util.Random;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.security.auth.login.CredentialNotFoundException;
import java.lang.reflect.*;

import org.globus.gsi.GlobusCredential;

public class GridAuthenticationLocalServiceImpl implements GridAuthenticationService
{
	private GridAuthenticationRemoteService service;
	private Map securityMap;
	private Random generator = new Random();

	public GridAuthenticationLocalServiceImpl(GridAuthenticationRemoteService service,Map securityMap)
	{
		this.service = service;
		this.securityMap = securityMap;
	}

	public GlobusCredential authenticate(String username, String password, String authenticationServiceURL, String dorianServiceURL) throws CredentialNotFoundException,Exception
	{
		String bindKey = generateBindKey();
		service.authenticate(bindKey, username, password,authenticationServiceURL,dorianServiceURL);
		GlobusCredential credential = retrieveCredential(bindKey);
		return credential;
	}

	public GlobusCredential authenticate(String username, String password) throws CredentialNotFoundException,Exception
	{
		String bindKey = generateBindKey();
		service.authenticate(bindKey, username, password);
		GlobusCredential credential = retrieveCredential(bindKey);
		return credential;
	}

	private GlobusCredential retrieveCredential(String bindKey)
	{
		org.globus.gsi.GlobusCredential credential = null;
		try
		{
			Object obj = securityMap.get(bindKey);
			if(obj instanceof org.globus.gsi.GlobusCredential)
					credential = (org.globus.gsi.GlobusCredential)obj;
			else
			{
				Class cred = obj.getClass();
				Method method = cred.getMethod("getPrivateKey", null);
	    		Object o1 = method.invoke(obj, null);
				Method method2 = cred.getMethod("getCertificateChain", null);
	    		Object o2 = method2.invoke(obj, null);
	    		try
	    		{
					PrivateKey key = (PrivateKey)o1;
					X509Certificate[] certs = (X509Certificate[])o2;
					//Class newInstance = Class.forName("org.globus.gsi.GlobusCredential");
					//System.out.println("before.............5");
					//Constructor constructor = newInstance.getClass().getDeclaredConstructor(PrivateKey.class, X509Certificate[].class);
					//System.out.println("before.............6");
					//credential = (org.globus.gsi.GlobusCredential)constructor.newInstance(key, certs);
					//System.out.println("before.............7");
					credential = new org.globus.gsi.GlobusCredential(key, certs);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			/*
			try{
				castedcredential = Class.forName("org.globus.gsi.GlobusCredential").cast(securityMap.get(bindKey));
				}
    		catch (ClassNotFoundException e1){
				e1.printStackTrace();
			}
			securityMap.remove(bindKey);
			return castedcredential;
			*/
		}

		securityMap.remove(bindKey);
		return credential;
	}

	private String generateBindKey()
	{
		while(true)
		{
			String randomKey = generator.nextLong()+"";
			if(securityMap.get(randomKey)==null)
				return randomKey;
		}
	}
}