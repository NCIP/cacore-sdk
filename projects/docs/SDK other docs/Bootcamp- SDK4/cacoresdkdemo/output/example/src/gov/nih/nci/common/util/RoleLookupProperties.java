/*
 * Created on Aug 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.common.util;

import java.util.Properties;

/**
 * @author zengje
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleLookupProperties {

	private static RoleLookupProperties instance;
	
	private Properties roleLookupProp;
	/**
	 * 
	 */
	private RoleLookupProperties() throws Exception {
		
		roleLookupProp=new Properties();
		roleLookupProp.load(this.getClass().getClassLoader().getResourceAsStream("roleLookup.properties"));	
	}
	
	public synchronized static RoleLookupProperties getInstance() throws Exception
	{
		if (instance == null)
		{
			instance = new RoleLookupProperties();
		}
		return instance;
	}
	
	public Properties getProperties()
	{
		return roleLookupProp;
	}

}
