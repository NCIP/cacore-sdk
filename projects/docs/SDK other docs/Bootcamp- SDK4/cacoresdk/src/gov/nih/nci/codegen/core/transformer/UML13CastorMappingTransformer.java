package gov.nih.nci.codegen.core.transformer;

import gov.nih.nci.codegen.core.BaseArtifact;
import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.filter.UML13ClassifierFilter;
import gov.nih.nci.codegen.core.filter.UML13ModelElementFilter;
import gov.nih.nci.codegen.core.util.UML13Utils;
import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.FilteringException;
import gov.nih.nci.codegen.framework.TransformationException;
import gov.nih.nci.codegen.framework.Transformer;
import gov.nih.nci.common.exception.XMLUtilityException;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.caCOREMarshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml.modelmanagement.UmlPackage;

/**
 * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * Produces an XML schema for the project object model
 * <p>
 * <p>
 * The content model for this transformer's configuration element is as follows:
 * <p>
 * <code>
 * <pre>
 *
 *
 *
 *    &lt;!ELEMENT transformer (param, filter)&gt;
 *    &lt;!ATTLIST transformer
 *       name CDATA #REQUIRED
 *       className CDATA #FIXED gov.nih.nci.codegen.core.transformer.UML13CastorMappingTransformer&gt;
 *    &lt;!ELEMENT param EMPTY&gt;
 *    &lt;!ATTLIST param
 *       name CDATA #FIXED packageName
 *       value CDATA #REQUIRED&gt;
 *    &lt;!ELEMENT filter ... see {@link gov.nih.nci.codegen.core.filter.UML13ClassifierFilter#configure(org.w3c.dom.Element)} ...
 *
 *
 *
 * </pre>
 * </code>
 * <p>
 * As you can see, this transformer expects a nested filter element. The reason
 * is that this transformer produces a single Artifact (an XML file) from a
 * collection of model elements.
 * <p>
 * UML13OJBRepTransformer expects to be passed an instance of
 * org.omg.uml.modelmanagement.Model. It uses UML13ModelElementFilter to obtain
 * all model elements in the model. Then it use UML13Classifier to obtain the
 * classifiers selected by the contents of the nested filter element. Then it
 * iterates through these classifiers, building the class-descriptor elements.
 * <p>
 * A Collection containing a single Artifact is returned by this transformer's
 * execute method. The name attribute of the Artifact is set to "ojb_repository"
 * and its source attribute is set to the String that represents the XML
 * document.
 * <p>
 *
 * @author caBIO Team
 * @version 1.0
 */
public class UML13CastorMappingTransformer implements Transformer, XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13CastorMappingTransformer.class);
    public static final String PROPERTIES_FILENAME = "xml.properties";
    public static final String PROPERTIES_CONTEXT_KEY = "context";
    public static final String PROPERTIES_CLASSIFICATION_KEY = "classification";
    public static final String PROPERTIES_VERSION_KEY = "version";
    public static final String PROPERTIES_NS_PREFIX_KEY = "ns_prefix";
    private UML13ClassifierFilter _classifierFilt;
    private String _pkgName;
    private boolean _includeAssociations = false;    
    private boolean _includeFieldHandler = false;    
    private Properties _properties;
    private String context;
    private String classification;
    private String version;
    private String nsprefix;
    
    /**
     *
     */
    public UML13CastorMappingTransformer() {
        super();
    }
    private String loadProperty(String key) throws IOException{
        if(_properties == null){
            try {
                _properties = new Properties();
                _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.PROPERTIES_FILENAME));
            } catch (IOException e) {
                throw new IOException("Error loading " + caCOREMarshaller.PROPERTIES_FILENAME + " file. Please make sure the file is in your classpath.");
            }
        }
         return _properties.getProperty(key);
    }
    /**
     * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
     *      java.util.Collection)
     */
    public Collection execute(RefObject modelElement, Collection artifacts) throws TransformationException {
        if (modelElement == null) {
        	log.error("model element is null");
            throw new TransformationException("model element is null");
        }
        if (!(modelElement instanceof Model)) {
        	log.error("model element not instance of Model");
            throw new TransformationException(
                    "model element not instance of Model");
        }
        ArrayList newArtifacts = new ArrayList();
        UML13ModelElementFilter meFilt = new UML13ModelElementFilter();
        ArrayList umlExtentCol = new ArrayList();
        umlExtentCol.add(modelElement.refOutermostPackage());
        Collection classifiers = null;
        try {
            classifiers = _classifierFilt.execute(meFilt.execute(umlExtentCol));
        } catch (FilteringException ex) {
        	log.error("couldn't filter model elements" + ex.getMessage());
            throw new TransformationException("couldn't filter model elements",
                    ex);
        }

        Document doc = generateRepository(classifiers);
        XMLOutputter p = new XMLOutputter();
        p.setFormat(Format.getPrettyFormat());

        newArtifacts.add(new BaseArtifact("castormapping", modelElement, p.outputString(doc)));
        return newArtifacts;
    }

    private Document generateRepository(Collection classifiers) {


            Element mappingEl = new Element("mapping");
            //DocType docType = new DocType("mapping","SYSTEM","mapping.dtd");
            DocType docType = new DocType("mapping","-//EXOLAB/Castor Object Mapping DTD Version 1.0//EN","http://www.castor.org/mapping.dtd");

            Document doc = new Document();
			doc.setDocType(docType);
			Collection sortedClassifier = sortClassifiers(classifiers);
	        for (Iterator i = sortedClassifier.iterator(); i.hasNext();) {
	            UmlClass klass = (UmlClass) i.next();
	            try {
	            	doMapping(klass, mappingEl);
	            } catch (XMLUtilityException ex) {
	            	log.error("XMLUtilityException: ", ex);
	            }
	        }
            doc.setRootElement(mappingEl);
            return doc;
	    }

    private Collection sortClassifiers(Collection classifiers)
	{
	    // The caCOREMarsheller has problem with forward references.   Sorting
		// the class with the list generalizations to the top.
		class caCOREComparer implements Comparator {
            public int compare(Object obj1, Object obj2)
            {
            	return determineWeight((UmlClass)obj1) - determineWeight((UmlClass)obj2);
            }
            
            private int determineWeight(UmlClass obj)
            {
            	int count = -1;
            	UmlClass superClass = obj;
            	do 
            	{
            		superClass = UML13Utils.getSuperClass(superClass);
            		count++;
            	}while (superClass!=null);
            	return count;
            }
		};

 		ArrayList list = new ArrayList(classifiers);
		Collections.sort(list, new caCOREComparer());
		return list;
	}
     
	private String getNamespaceURI(UmlClass klass) throws XMLUtilityException{
    	 StringBuffer nsURI = new StringBuffer();
    	 try {
    	 context = loadProperty(this.PROPERTIES_CONTEXT_KEY);
    	 classification = loadProperty(this.PROPERTIES_CLASSIFICATION_KEY);
    	 version = loadProperty(this.PROPERTIES_VERSION_KEY);
    	 nsprefix= loadProperty(this.PROPERTIES_NS_PREFIX_KEY);
     } catch (IOException e) {
         log.error("Error reading default xml mapping file " + e.getMessage());  //To change body of catch statement use File | Settings | File Templates.
         throw new XMLUtilityException("Error reading default xml mapping file " + e.getMessage(), e);
     }
    	 String packageName = getPackage(klass);
    	 nsURI.append(nsprefix);
    	 nsURI.append(classification);
    	 nsURI.append(Constant.DOT);
    	 nsURI.append(context);
    	 nsURI.append(Constant.FORWARD_SLASH);
    	 nsURI.append(version);
    	 nsURI.append(Constant.FORWARD_SLASH);
    	 nsURI.append(packageName);
    	 return nsURI.toString();
     }

     private void doMapping(UmlClass klass, Element mappingEl) throws XMLUtilityException {
    	 String superClassName =null;
    	 UmlClass superClass = UML13Utils.getSuperClass(klass);
         if (superClass != null) {
 		   superClassName = getPackage(superClass)+ Constant.DOT+superClass.getName();
         } 	    
         String classElName = "class";
         Element classEl = new Element(classElName);
         mappingEl.addContent(classEl);
         classEl.setAttribute("name", getPackage(klass)+Constant.DOT+klass.getName());
         classEl.setAttribute("identity", "id");
         if (superClassName!=null){
        	 classEl.setAttribute("extends", superClassName);
         }
         Element maptoelement = new Element("map-to");
         maptoelement.setAttribute("xml", klass.getName());
         String nsURI = getNamespaceURI(klass);
         maptoelement.setAttribute("ns-uri",nsURI);
         classEl.addContent(maptoelement);
         //Do properties
         for (Iterator i = UML13Utils.getAttributes(klass).iterator(); i.hasNext();) {
             org.omg.uml.foundation.core.Attribute att = (org.omg.uml.foundation.core.Attribute) i.next();
             Element field = new Element("field");
             field.setAttribute("name", att.getName());
             log.debug("Field name: " + att.getName());
             
             String qName = getQualifiedName(att.getType());
             if (qName.equalsIgnoreCase("collection")) {
            	 log.debug("Handling type 'collection' - qName: " + qName);            	 
            	 field.setAttribute("type", "string");
            	 field.setAttribute("collection", qName);
            	 Namespace namespace = Namespace.getNamespace(qName,nsURI);
            	 
                 Element bind = new Element("bind-xml");
            	 bind.setAttribute("name", qName + ":" + att.getName());
            	 bind.setAttribute("QName-prefix",qName,namespace);
                 bind.setAttribute("node", "attribute");
                 field.addContent(bind);
             } else {
            	 field.setAttribute("type", getQualifiedName(att.getType()));
                 Element bind = new Element("bind-xml");
                 bind.setAttribute("name", att.getName());
                 bind.setAttribute("node", "attribute");
                 field.addContent(bind);         	 
             }

             classEl.addContent(field);
	      }
         
         if (_includeAssociations) {
	         log.debug("*********** klass: " + klass.getName());
	         log.debug("*********** UML13Utils.getAssociationEnds(klass).size(): " + UML13Utils.getAssociationEnds(klass).size());
	         
	         for (Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();) {
	             AssociationEnd thisEnd = (AssociationEnd) i.next();
	             AssociationEnd otherEnd = UML13Utils.getOtherAssociationEnd(thisEnd);
	             addSequenceAssociationElement(classEl, klass,thisEnd, otherEnd);
	         }
         }
         
     }

     private void addSequenceAssociationElement(Element mappingEl, UmlClass klass, AssociationEnd thisEnd, AssociationEnd otherEnd) throws XMLUtilityException {
         if (otherEnd.isNavigable()) {
             /** If classes belong to the same package then do not qualify the association
              *
              */
    		 log.debug("mappingEl.getName(): " + mappingEl);
    		 log.debug("klass.getName(): " + klass.getName());
    		 log.debug("thisEnd.getName(): " + thisEnd.getName());
    		 log.debug("thisEnd.getType().getName(): " + thisEnd.getType().getName());    		 
    		 log.debug("otherEnd.getName(): " + otherEnd.getName()); 
    		 log.debug("otherEnd.getType().getName(): " + otherEnd.getType().getName());  
  		 
        	 if (UML13Utils.isMany2One(thisEnd, otherEnd)) {
       		 
            	 log.debug("UML13Utils.isMany2One(thisEnd, otherEnd): " + true);
        		 log.debug("lowerBound: " + UML13Utils.getLowerBound(thisEnd, otherEnd));
        		 log.debug("upperBound: " + UML13Utils.getUpperBound(thisEnd, otherEnd));

            	 Element field = new Element("field");
            	 field.setAttribute("name", otherEnd.getName());
            	 String associationPackage = getPackage(otherEnd.getType());
            	 field.setAttribute("type", associationPackage + Constant.DOT + otherEnd.getType().getName());
            	 if (_includeFieldHandler) {
            		 field.setAttribute("handler", "gov.nih.nci.common.util.CastorDomainObjectFieldHandler" );
            	 }
            	 Element bind = new Element("bind-xml");
                 bind.setAttribute("name", otherEnd.getType().getName());  //otherEnd.getName()
            	 bind.setAttribute("type", associationPackage + Constant.DOT+otherEnd.getType().getName());
                 bind.setAttribute("location", otherEnd.getName());	                 
                 bind.setAttribute("node", "element");
                 field.addContent(bind);
                 
                 mappingEl.addContent(field);

           	 } else if (UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){
            	 log.debug("UML13Utils.isMany2Many(thisEnd, otherEnd): " + UML13Utils.isMany2Many(thisEnd, otherEnd));
            	 log.debug("UML13Utils.isOne2Many(thisEnd, otherEnd): " + UML13Utils.isOne2Many(thisEnd, otherEnd));
        		 log.debug("lowerBound: " + UML13Utils.getLowerBound(thisEnd, otherEnd));
        		 log.debug("upperBound: " + UML13Utils.getUpperBound(thisEnd, otherEnd));
            	 Element field = new Element("field");
            	 field.setAttribute("name", otherEnd.getName());
            	 String associationPackage = getPackage(otherEnd.getType());
            	 field.setAttribute("type", associationPackage + Constant.DOT+otherEnd.getType().getName());
            	 field.setAttribute("collection", "collection" );
            	 if (_includeFieldHandler) {
            		 field.setAttribute("handler", "gov.nih.nci.common.util.CastorCollectionFieldHandler" );
            	 }
            	 
            	 //for container = false
            	 //field.setAttribute("container", "false" );

            	 Element bind = new Element("bind-xml");
                 //bind.setAttribute("auto-naming", "deriveByClass");
            	 
            	 // for container = false
            	 //bind.setAttribute("name", otherEnd.getName());

            	 
            	 // for container = true
            	 bind.setAttribute("name", otherEnd.getType().getName());
            	 bind.setAttribute("type", associationPackage+Constant.DOT+otherEnd.getType().getName());
                 bind.setAttribute("location", otherEnd.getName());
                 bind.setAttribute("node", "element");
                 
                 field.addContent(bind);
                 mappingEl.addContent(field);
        		 
        	 }
        	 
         }
     }
    /**
     * @param classifiers
     * @return
     */
    private Document generateConfig(Collection classifiers) {
        Element configEl = new Element("DAOConfiguration");

        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            Classifier klass = (Classifier) i.next();
            UmlPackage pkg = null;
            if (_pkgName != null) {
                pkg = UML13Utils.getPackage(UML13Utils.getModel(klass),
                        _pkgName);
            } else {
                pkg = UML13Utils.getModel(klass);
            }
        }

        Document doc = new Document();
        doc.setRootElement(configEl);
        return doc;
    }

    /**
     * @see gov.nih.nci.codegen.core.JDOMConfigurable#configure(org.jdom.Element)
     */
    public void configure(org.w3c.dom.Element config) throws ConfigurationException {
        org.w3c.dom.Element filterEl = XMLUtils.getChild(config, "filter");
        if (filterEl == null) {
        	log.error("no child filter element found");
            throw new ConfigurationException("no child filter element found");
        }

        String className = filterEl.getAttribute("className");
        if (className == null) {
        	log.error("no filter class name specified");
            throw new ConfigurationException("no filter class name specified");
        }
        _pkgName = getParameter(config, "basePackage");
        log.debug("basePackage: " + _pkgName);
        
        String isIncludeAssociations = getParameter(config, "includeAssociations");
        log.debug("includeAssociations: " + isIncludeAssociations);
        if (isIncludeAssociations != null && isIncludeAssociations.length() > 0) {
        	_includeAssociations = new Boolean(isIncludeAssociations).booleanValue();
        }        
        
        String isIncludeFieldHandler = getParameter(config, "includeFieldHandler");
        log.debug("includeFieldHandler: " + isIncludeFieldHandler);
        if (isIncludeFieldHandler != null && isIncludeFieldHandler.length() > 0) {
        	_includeFieldHandler = new Boolean(isIncludeFieldHandler).booleanValue();
        }

        try {
            _classifierFilt = (UML13ClassifierFilter) Class.forName(className)
                    .newInstance();
        } catch (Exception ex) {
        	log.error("Couldn't instantiate "
                    + className);
            throw new ConfigurationException("Couldn't instantiate "
                    + className);
        }

        _classifierFilt.configure(filterEl);
    }

private String getQualifiedName(ModelElement me) {
        String qName = null;
        UmlPackage pkg = null;
        if (_pkgName != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(me), _pkgName);
        } else {
            pkg = UML13Utils.getModel(me);
        }
        qName = UML13Utils.getNamespaceName(pkg, me) + Constant.DOT + me.getName();
        int i = qName.lastIndexOf(Constant.DOT);
        if (qName.startsWith(".") || qName.startsWith("java")) {
			qName = qName.substring(i+1);
		}
        
        if ("HashSet".equalsIgnoreCase(qName)) {
        	return "set";
        }
        
        if ("HashMap".equalsIgnoreCase(qName)){
        	return "map";
        }
        
        log.debug("*** qName: " + qName);
        return qName.toLowerCase();
    }

    /**
     * @param klass
     * @return
     */
/*
    private String getPackage(UmlClass klass) {
        UmlPackage pkg = null;
        if (_pkgName != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _pkgName);
        } else {
            pkg = UML13Utils.getModel(klass);
        }
        return UML13Utils.getNamespaceName(pkg, klass);

    }
    */
    private String getPackage(ModelElement klass) {
        UmlPackage pkg = null;
        if (_pkgName != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _pkgName);
        } else {
            pkg = UML13Utils.getModel(klass);
        }
        return UML13Utils.getNamespaceName(pkg, klass);

    }
    

    private String getParameter(org.w3c.dom.Element config, String paramName) {
        String param = null;

        List params = XMLUtils.getChildren(config, "param");
        for (Iterator i = params.iterator(); i.hasNext();) {
            org.w3c.dom.Element paramEl = (org.w3c.dom.Element) i.next();
            if (paramName.equals(paramEl.getAttribute("name"))) {
                param = paramEl.getAttribute("value");
                break;
            }
        }

        return param;
    }

}
