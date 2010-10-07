package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import java.util.Iterator;
import javax.xml.*;
import javax.xml.namespace.NamespaceContext;

public class SDKNamespaceContext implements NamespaceContext {

    public String getNamespaceURI(String prefix) {
        if (prefix == null) throw new NullPointerException("Null prefix");
        else if ("thecustomprofile".equals(prefix)) return "http://www.sparxsystems.com/profiles/thecustomprofile/1.0";
        else if ("uml".equals(prefix)) return "http://schema.omg.org/spec/UML/2.1";
        else if ("xmi".equals(prefix)) return "http://schema.omg.org/spec/XMI/2.1";
        else if ("xml".equals(prefix)) return XMLConstants.XML_NS_URI;
        return XMLConstants.NULL_NS_URI;
    }
    
    public String getPrefix(String uri) {
        throw new UnsupportedOperationException();
    }
    
    public Iterator<String> getPrefixes(String uri) {
        throw new UnsupportedOperationException();
    }
}