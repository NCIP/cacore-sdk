package gov.nih.nci.system.servicelocator;



import gov.nih.nci.common.util.Parser;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 * ServiceLocator provides methods to retrieve information from an XML
 * configuration file
 * 
 * @author Satish Patel
 * @version 1.0
 */
public class ServiceLocator {

	private static Logger log = Logger.getLogger(ServiceLocator.class.getName());
	private Map domainObjectToDataSourceMap =  new Hashtable();
	private Map dataSourceConfigMap = new Hashtable();
	private int ormCount = 0;


	/**
	 * Creates a ServiceLocator instance
	 */
	public ServiceLocator() {
		loadConfiguration();
	}

	private void loadConfiguration()
	{
		Parser parser = new Parser("DAOConfig.xml");
		List list = parser.getList("/DAOConfiguration/domainObjects");
		
		for (Iterator i = list.iterator(); i.hasNext();) {
			Element element = (Element) i.next();
			Attribute attribute = element.getAttribute("name");
			String names = attribute.getValue();
			Element dsElement = element.getChild("DataSource");
			String dataSource = dsElement.getValue();
			log.info("Domain Object Mapping "+names+"====="+dataSource);

			StringTokenizer tokenizer = new StringTokenizer(names,",");
			if (dataSource.indexOf("ORM") != -1)
				ormCount++;
			
			while(tokenizer.hasMoreElements())
			{
				String token = (String)tokenizer.nextElement();
				if(domainObjectToDataSourceMap.get(token.trim())!=null)
					log.error("Duplicate Entry in DAO config file for: "+token);
				domainObjectToDataSourceMap.put(token.trim(),dataSource.trim());
				log.info("Mapping "+token+" to "+dataSource);
			}
			
			dataSourceConfigMap.put(dataSource.trim(),parser.listElement(element));
		}
	}
	

	public int getORMCount() {
		return ormCount;
	}

	public int getORMCounter(String domainObjectName) throws ServiceLocatorException {
		String dsName = getDataSource(domainObjectName);
		try {
			String index = dsName.substring(3);
			return Integer.parseInt(index);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceLocatorException(e.getMessage(),e);
		}
	}

	public String getDataSource(String domainObjectName) throws ServiceLocatorException {
		String dsName = (String)domainObjectToDataSourceMap.get(domainObjectName.trim());
			if(dsName==null)
				log.error("No entry in DAO config file for: "+domainObjectName);
		return dsName;
	}
	
	/**
	 * Gets the datasource configuration values
	 * 
	 * @param objectName
	 * @return
	 * @throws ServiceLocatorException 
	 * @throws ServiceLocatorException
	 */
	public Hashtable getDataSourceCollection(String domainObjectName) throws ServiceLocatorException{
		String dsName = getDataSource(domainObjectName);
		return (Hashtable)dataSourceConfigMap.get(dsName);
	}
}
