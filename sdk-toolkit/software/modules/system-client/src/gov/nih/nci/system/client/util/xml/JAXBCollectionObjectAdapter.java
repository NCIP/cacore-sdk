package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.system.client.proxy.ListProxy;

import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

public class JAXBCollectionObjectAdapter<T> extends XmlAdapter<T, Object>{
	
	private static Logger log = Logger.getLogger(JAXBCollectionObjectAdapter.class.getName());	

	public T marshal(Object source) throws Exception {
		if (source == null){
			log.debug("Marshal source is null; returning null");
			return null;
		}
		log.debug("In JAXB Collection Object adapter marshall: "+source.getClass().getName());
		
		if (Hibernate.isInitialized(source)){		
			log.debug(source.getClass().getName() + " is initialized as a ListProxy");
			if (source instanceof ListProxy){
				log.debug(source.getClass().getName() + " is initialized as a ListProxy");
				return (T)source;
			}
			
			log.debug(source.getClass().getName() + " is initialized; returning it unmodified");
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
		log.debug("In JAXB Collection Object adapter unmarshall: "+dest.getClass().getName());
		return dest;
	}

}
