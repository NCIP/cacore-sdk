/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class WorkbenchPropertiesManager {
	
	private static final Logger log = Logger.getLogger(WorkbenchPropertiesManager.class);

	Properties deployProperties=null;

	public WorkbenchPropertiesManager(String deployPropsFilePath) {
		super();

		deployProperties = new Properties();
		try {
			  
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(deployPropsFilePath);
			if (is == null){
				is = new FileInputStream(deployPropsFilePath);
			}
			
			deployProperties.load(is);
		} catch (IOException e) {
			log.error("ERROR :: Failed to load properties from " + deployPropsFilePath);
			e.printStackTrace();
		}
	}
	
	public WorkbenchPropertiesManager(String[] deployPropertiesFilePathArr) {
		super();

		deployProperties = new Properties();
		try {
			for (String deployPropsFilePath : deployPropertiesFilePathArr){
				log.debug("* * * Loading properties for file: " + deployPropsFilePath);
				  
				InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(deployPropsFilePath);
				if (is == null){
					is = new FileInputStream(deployPropsFilePath);
				}
				
				deployProperties.load(is);
			}
			
			Enumeration<Object> keys = deployProperties.keys();
			while (keys.hasMoreElements()){
				String key = (String)keys.nextElement();
				log.debug("* * *  key: " + key + "; value: "+deployProperties.getProperty(key));
			}
				
		} catch (IOException e) {
			log.error("ERROR :: Failed to load properties from " + deployPropertiesFilePathArr);
			e.printStackTrace();
		}
	}

	public String getDeployPropertyValue(String propertyKey) {
		return deployProperties.getProperty(propertyKey);
	}

	public Properties getDeployProperties() {
		return deployProperties;
	}
	
}
