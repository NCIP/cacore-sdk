/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.client;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.ejb.CreateException;
import javax.ejb.EJBObject;
import javax.ejb.EJBHome;

import org.junit.Test;

import samples.ejb.stateless.simple.ejb.Greeter;
import samples.ejb.stateless.simple.ejb.GreeterHome;

public class EJBClientTest {

	@Test
	public void testEJB()
	{
        Greeter myGreeterBean; 
        GreeterHome myGreeterHome; 
        Greeter myGreeterRemote;
 
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial",
        "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url",
        "jnp://localhost:1099");
        props.setProperty("java.naming.factory.url.pkgs",
        "org.jboss.naming.client");
        props.setProperty("java.naming.factory.url.pkgs",
        "org.jboss.naming:org.jnp.interfaces");

        InitialContext initContext = null; 

        try { 
            initContext = new javax.naming.InitialContext(props); 
        }  
        catch (Exception e) { 
        	e.printStackTrace();
          return; 
        } 

        try { 
            String JNDIName = "java:comp/env/ejb/greeter"; 
            Object objref = initContext.lookup(JNDIName); 
            myGreeterHome = (GreeterHome)PortableRemoteObject.narrow(objref,
                                            GreeterHome.class);
        }  
        catch(Exception e) { 
        e.printStackTrace(); 
        return; 
        } 
        try { 
            myGreeterRemote = myGreeterHome.create();  
        } 
        catch(CreateException e) {
        	e.printStackTrace();
            return; 
        }  
        catch(RemoteException e) {
        	e.printStackTrace();
            return; 
        }  

        String theMessage=null;
		try {
			theMessage = myGreeterRemote.getGreeting();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("got : " + theMessage); 
		
	}
}
