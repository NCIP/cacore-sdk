package gov.nih.nci.restgen.generated.resource;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;


public class RESTApplication extends Application
{

public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(gov.nih.nci.restgen.util.RESTContentHandler.class);
        $RESTResouce; separator="\n"$;
        return set;
    }
}
