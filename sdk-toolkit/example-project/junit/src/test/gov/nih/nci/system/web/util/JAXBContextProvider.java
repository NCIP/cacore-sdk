package test.gov.nih.nci.system.web.util;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JAXBContextProvider implements ContextResolver<JAXBContext> {
    private JAXBContext context = null;

    public JAXBContext getContext(Class<?> type) {
    	
        if(context == null) {
           try {
               context = JAXBContext.newInstance(gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher.class,gov.nih.nci.system.web.SDKQuery.class);
           } catch (JAXBException e) {
               // log warning/error; null will be returned which indicates that this
               // provider won't/can't be used.
           }
       }
       return context;
   }
}