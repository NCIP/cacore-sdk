package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.system.client.proxy.ListProxy;

import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

public class JAXBCollectionObjectAdapter extends XmlAdapter<HashSet, Object>{
	
	private static Logger log = Logger.getLogger(JAXBCollectionObjectAdapter.class.getName());	

	public HashSet marshal(Object source) throws Exception {
		if (source == null){
			log.debug("Marshal source is null; returning null");
			return null;
		}
		log.debug("In JAXB Collection Object adapter marshall: "+source.getClass().getName());
		
		if (Hibernate.isInitialized(source)){		
			log.debug(source.getClass().getName() + " is initialized as a ListProxy; converting and returning as a HashSet");
			if (source instanceof ListProxy)
				return new HashSet((ArrayList)source);
			
			log.debug(source.getClass().getName() + " is initialized; returning it unmodified");
			return (HashSet)source;
		}

		log.debug(source.getClass().getName() + " is NOT initialized; returning null");
		return null;
	}

	public Object unmarshal(HashSet dest) throws Exception {
		if (dest == null){
			log.debug("Unmarshal destination is null; returning null");
			return null;
		}
		log.debug("In JAXB Collection Object adapter unmarshall: "+dest.getClass().getName());
		return dest;
	}

}
