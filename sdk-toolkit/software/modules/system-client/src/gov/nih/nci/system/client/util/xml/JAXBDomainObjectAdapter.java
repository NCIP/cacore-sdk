package gov.nih.nci.system.client.util.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

public class JAXBDomainObjectAdapter<T> extends XmlAdapter<T, Object>{
	
	private static Logger log = Logger.getLogger(JAXBDomainObjectAdapter.class.getName());	

	public T marshal(Object source) throws Exception {
		if (source == null){
			log.debug("Marshal source is null; returning null");
			return null;
		}
		
		log.debug("In JAXB Domain Object adapter marshall: "+source.getClass().getName());
		
		if (Hibernate.isInitialized(source)){
			log.debug(source.getClass().getName() + " is initialized");
			
			String className = source.getClass().getName();
			if (className.indexOf('$') > 0) {
				log.debug(source.getClass().getName() + " is a proxy object; converting now");
				boolean includeAssociations = false;
				return (T)XMLUtility.convertFromProxy(source,includeAssociations);
			}
			return (T)source;
		}

		log.debug(source.getClass().getName() + " is NOT initialized; returning null");
		return null;
	}

	public Object unmarshal(T dest) throws Exception {
		if (dest == null){
			log.debug("Unmarshal destination is null; returning null");
			return null;
		}
		log.debug("In JAXB child adapter unmarshall: "+dest.getClass().getName());
		return dest;
	}
}
