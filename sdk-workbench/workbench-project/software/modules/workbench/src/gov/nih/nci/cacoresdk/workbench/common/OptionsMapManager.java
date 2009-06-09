package gov.nih.nci.cacoresdk.workbench.common;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class OptionsMapManager {
	
	private static final Logger log = Logger.getLogger(OptionsMapManager.class);

	private static Map<String,String> valueToKeyMap = null;
	
    private static TreeMap<String,String> caGridTargetGridOptionsMap = null;
    private static TreeMap<String,String> clmDbTypeOptionsMap = null;
    private static TreeMap<String,String> dbUrlOptionsMap = null;
    private static TreeMap<String,String> dbTypeOptionsMap = null;
    private static TreeMap<String,String> deployTypeOptionsMap = null;
    private static TreeMap<String,String> jbossPortConfigurationOptionsMap = null;
    private static TreeMap<String,String> modelFileTypeOptionsMap = null;    
    private static TreeMap<String,String> remoteDeployEnvOptionsMap = null;
	private static TreeMap<String,String> serverTypeOptionsMap = null;
    
    public static Map<String,String> getValueToKeyMap(){
    	return valueToKeyMap;
    }

    public static Map<String,String> getCaGridTargetGridOptionsMap(){
    	return caGridTargetGridOptionsMap;
    }
    
    public static Map<String,String> getClmDbTypeOptionsMap(){
    	return clmDbTypeOptionsMap;
    }

    public static Map<String,String> getDbUrlOptionsMap(){
    	return dbUrlOptionsMap;
    }
    
    public static Map<String,String> getDbTypeOptionsMap(){
    	return dbTypeOptionsMap;
    }
    
    public static Map<String,String> getDeployTypeOptionsMap(){
    	return deployTypeOptionsMap;
    }
    
    public static Map<String,String> getJbossPortConfigurationOptionsMap(){
    	return jbossPortConfigurationOptionsMap;
    }
    
    public static Map<String,String> getModelFileTypeOptionsMap(){
    	return modelFileTypeOptionsMap;
    }
    
    public static Map<String,String> getRemoteDeployEnvOptionsMap(){
    	return remoteDeployEnvOptionsMap;
    }
    
    public static Map<String,String> getServerTypeOptionsMap(){
    	return serverTypeOptionsMap;
    }
    
    static {
    	ObjectFactory.initialize("drop-down-config.xml");
    	
    	//Value-to-Key Mappings
		try {
			valueToKeyMap = (Map<String,String>)ObjectFactory.getObject("valueToKeyMap");
		} catch (Exception e) {
			log.error("ERROR:  Unable to load the Value-to-Key mappings");
			e.printStackTrace();
		}	
		
		//caGrid Target Grid Mappings
		try {
			HashMap<String,String> tempCaGridTargetGridOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("caGridEnvironmentMap");
			caGridTargetGridOptionsMap = new TreeMap<String, String>(tempCaGridTargetGridOptionsMap);
		} catch (Exception e) {
			log.error("ERROR:  Unable to load the caGrid Environment drop-down mappings");
			e.printStackTrace();
		}
		
    	//CLM DB Type Options Mappings
    	try {
    		HashMap<String,String> tempClmDbTypeOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("clmDbTypeMap");
    		clmDbTypeOptionsMap = new TreeMap<String,String>(tempClmDbTypeOptionsMap);
    	} catch (Exception e) {
    		log.error("ERROR:  Unable to load the CLM Database Type drop-down information");
    		e.printStackTrace();
    	}
    	
    	//DB Type Options Mappings
    	try {
    		HashMap<String,String> tempDbTypeOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("dbTypeMap");
    		dbTypeOptionsMap = new TreeMap<String, String>(tempDbTypeOptionsMap);
    	} catch (Exception e) {
    		log.error("ERROR:  Unable to load the Database Type drop-down mappings");
    		e.printStackTrace();
    	}
    	
    	//DB URL Options Mapping
    	try {
    		HashMap<String,String> tempDbUrlOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("dbUrlMap");
    		dbUrlOptionsMap = new TreeMap<String, String>(tempDbUrlOptionsMap);
    	} catch (Exception e) {
    		log.error("ERROR:  Unable to load the Database Connection URL template mappings");
    		e.printStackTrace();
    	}
    	
    	//Deploy Type Options Mapping
    	try {
    		HashMap<String,String> tempDeployTypeOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("deployTypeMap");
    		deployTypeOptionsMap = new TreeMap<String, String>(tempDeployTypeOptionsMap);
    	} catch (Exception e) {
    		log.error("ERROR:  Unable to load the Deploy Type mappings");
    		e.printStackTrace();
    	}
    	
    	//JBoss Port Configuration Options Mapping
    	try {
    		HashMap<String,String> tempJbossPortConfigurationOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("jbossPortConfigMap");
    		jbossPortConfigurationOptionsMap = new TreeMap<String, String>(tempJbossPortConfigurationOptionsMap);
    	} catch (Exception e) {
    		log.error("ERROR:  Unable to load the JBoss Port Configuration mappings");
    		e.printStackTrace();
    	}
    	
    	//Model File Type Mappings
		try {
			HashMap<String,String> tempModelFileTypeOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("modelFileTypeMap");
			modelFileTypeOptionsMap = new TreeMap<String, String>(tempModelFileTypeOptionsMap);
		} catch (Exception e) {
			log.error("ERROR:  Unable to load the model file type drop-down mappings");
			e.printStackTrace();
		}	
		
		//Remote Deployment Environment Mappings
		try {
			HashMap<String,String> tempRemoteDeployEnvOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("remoteDeployEnvMap");
			remoteDeployEnvOptionsMap = new TreeMap<String, String>(tempRemoteDeployEnvOptionsMap);
		} catch (Exception e) {
			log.error("ERROR:  Unable to load the Remote Deployment drop-down mappings");
			e.printStackTrace();
		}
    	
    	//Server Type Mappings
		try {
			HashMap<String,String> tempServerTypeOptionsMap = (HashMap<String,String>)ObjectFactory.getObject("serverTypeMap");
			serverTypeOptionsMap = new TreeMap<String, String>(tempServerTypeOptionsMap);
		} catch (Exception e) {
			log.error("ERROR:  Unable to load the server type drop-down mappings");
			e.printStackTrace();
		}	
    }

}
