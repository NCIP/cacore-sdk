package gov.nih.nci.system.client.util.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

public class JAXBDomainObjectAdapter extends XmlAdapter<JAXBGenericPOJO, Object>{
	
	private static Logger log = Logger.getLogger(JAXBDomainObjectAdapter.class.getName());	

	public JAXBGenericPOJO marshal(Object source) throws Exception {
		if (source == null){
			log.debug("Marshal source is null; returning null");
			return null;
		}
		
		log.debug("In JAXB Domain Object adapter marshall: "+source.getClass().getName());
		
		if (Hibernate.isInitialized(source)){
			log.debug(source.getClass().getName() + " is initialized; returning it unmodified");
			
			String className = source.getClass().getName();
			if (className.indexOf('$') > 0) {
				boolean includeAssociations = false;
				return (JAXBGenericPOJO)XMLUtility.convertFromProxy(source,includeAssociations);
			}
			return (JAXBGenericPOJO)source;
		}

		log.debug(source.getClass().getName() + " is NOT initialized; returning null");
		return null;
	}

	public Object unmarshal(JAXBGenericPOJO dest) throws Exception {
		if (dest == null){
			log.debug("Unmarshal destination is null; returning null");
			return null;
		}
		log.debug("In JAXB child adapter unmarshall: "+dest.getClass().getName());
		return dest;
	}
}
