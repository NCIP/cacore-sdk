package gov.nih.nci.common.util;

import gov.nih.nci.system.applicationservice.ApplicationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Dan Dumitru
 * 
 */

public class ObjectFactory

{
	private static XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("SDKSpringBeanConfig.xml"));
	private static Logger log = Logger.getLogger(ObjectFactory.class.getName());

	private ObjectFactory() {

	}

	/**
	 * get Object instance from the class name.
	 * 
	 * @param classname
	 * @return
	 * @throws ApplicationException 
	 */

	public static Object getObject(String classname) throws ApplicationException{
		try
		{
			return factory.getBean(classname);
		}
		catch(Exception e)
		{
			log.info("No bean found for key = "+classname +"\n");
			throw new ApplicationException("No bean found for key = "+classname +"\n",e);
		}
	}

}
