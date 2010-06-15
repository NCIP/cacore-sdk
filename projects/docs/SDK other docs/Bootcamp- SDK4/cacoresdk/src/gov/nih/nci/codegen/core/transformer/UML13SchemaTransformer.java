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
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
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
 *       className CDATA #FIXED gov.nih.nci.codegen.core.transformer.UML13SchemaTransformer&gt;
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
public class UML13SchemaTransformer implements Transformer, XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13SchemaTransformer.class);
    public static final String PROPERTIES_FILENAME = "xml.properties";
    public static final String PROPERTIES_CONTEXT_KEY = "context";
    public static final String PROPERTIES_CLASSIFICATION_KEY = "classification";
    public static final String PROPERTIES_VERSION_KEY = "version";
    public static final String PROPERTIES_NS_PREFIX_KEY = "ns_prefix";
    private UML13ClassifierFilter _classifierFilt;
    private String _pkgName;
    private Properties _properties;
    private String context;
    private String classification;
    private String version;
    private String nsprefix;


    /**
     *
     */;
    public UML13SchemaTransformer() {
        super();
    }

    /**
     * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
     *      java.util.Collection)
     */
    public Collection execute(RefObject modelElement, Collection artifacts)
            throws TransformationException {
        if (modelElement == null) {
            log.error("model element is null");
            throw new TransformationException("model element is null");
        }
        if (!(modelElement instanceof Model)) {
            log.error("model element not instance of Model");
            throw new TransformationException("model element not instance of Model");
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
            throw new TransformationException("couldn't filter model elements", ex);
        }
        //Split the classifiers up by package
        Hashtable packages = new Hashtable();

        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            UmlClass klass = (UmlClass) i.next();
            String packageName = getPackage(klass);
            if(!packages.containsKey(packageName)) {
                List classes = new ArrayList();
                classes.add(klass);
                packages.put(packageName, classes);
            } else {
                Collection existingCollection = (Collection)packages.get(packageName);
                existingCollection.add(klass);
            }
        }
        for (Enumeration e = packages.keys() ; e.hasMoreElements() ;) {
            String packageNameKey = (String)e.nextElement();
            Document doc = new Document();
            try {
                doc = generateRepository(classifiers, packageNameKey,(Collection)packages.get(packageNameKey));
            } catch (Exception ex) {
            	log.error("Exception: ", ex);
            }
            XMLOutputter p = new XMLOutputter();
            p.setFormat(Format.getPrettyFormat());
            newArtifacts.add(new BaseArtifact(packageNameKey, modelElement, p.outputString(doc)));
        }
        return newArtifacts;
    }

    private String loadProperty(String key) throws IOException{
        if(_properties == null){
            try {
                _properties = new Properties();
                _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.PROPERTIES_FILENAME));
            } catch (IOException e) {
            	log.error("Could not load xml.properties: \n " + e.getMessage());
                throw new IOException("Error loading " + caCOREMarshaller.PROPERTIES_FILENAME + " file. Please make sure the file is in your classpath.");
            }
        }
        return _properties.getProperty(key);
    }

    private String getPackage(ModelElement klass) {
        UmlPackage pkg = null;
        if (_pkgName != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _pkgName);
        } else {
            pkg = UML13Utils.getModel(klass);
        }
        return UML13Utils.getNamespaceName(pkg, klass);

    }


    private String getNamespaceURI() throws XMLUtilityException{
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
        nsURI.append(nsprefix);
        nsURI.append(classification);
        nsURI.append(Constant.DOT);
        nsURI.append(context);
        nsURI.append(Constant.FORWARD_SLASH);
        nsURI.append(version);
        nsURI.append(Constant.FORWARD_SLASH);
        return nsURI.toString();
    }
    private List getRelatedNamespaces(Collection classifiers, String packageName, String caBIGURI) {
        List namespaces = new ArrayList();
        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            UmlClass klass = (UmlClass) i.next();
            String relatedPackageName = getPackage(klass);
            if(!relatedPackageName.equals(packageName)) {
                String relatedURI = caBIGURI + relatedPackageName;
                Namespace relatedNamespace = Namespace.getNamespace(relatedPackageName,relatedURI);
                namespaces.add(relatedNamespace);
            }
        }
        return namespaces;
    }
    private HashSet getRelatedNamespacesImports(Collection classifiers, String packageName, String caBIGURI, Namespace w3cNS) {
        HashSet elements = new HashSet();
        Vector tmpList = new Vector();
        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            UmlClass klass = (UmlClass) i.next();
            String relatedPackageName = getPackage(klass);
            if(!relatedPackageName.equals(packageName)) {
                if (!tmpList.contains(relatedPackageName)) {
                    String relatedURI = caBIGURI + relatedPackageName;
                    Element importElement = new Element("import", w3cNS);
                    importElement.setAttribute("namespace",relatedURI);
                    importElement.setAttribute("schemaLocation", relatedPackageName+".xsd");
                    elements.add(importElement);
                    tmpList.add(relatedPackageName);
                }
            }
        }
        return elements;
    }
    private Document generateRepository(Collection classifiers, String packageName, Collection subclassifiers) throws XMLUtilityException{

        String caBIGNS_URI = getNamespaceURI()+packageName;
        Namespace w3cNS = Namespace.getNamespace("xs","http://www.w3.org/2001/XMLSchema");
        Element schemaElem = new Element("schema", w3cNS);
        Namespace caBIGNS = Namespace.getNamespace(caBIGNS_URI);
        schemaElem.addNamespaceDeclaration(caBIGNS);
        schemaElem.addNamespaceDeclaration(w3cNS);
        List relatedNamespaces = getRelatedNamespaces(classifiers, packageName, getNamespaceURI());
        for (Iterator i = relatedNamespaces.iterator(); i.hasNext();) {
            Namespace rNamespace = (Namespace) i.next();
            schemaElem.addNamespaceDeclaration(rNamespace);
        }
        Attribute targetNSAttr = new Attribute("targetNamespace", caBIGNS_URI);
        schemaElem.setAttribute(targetNSAttr);
        HashSet relatedNamespacesImports = getRelatedNamespacesImports(classifiers, packageName, getNamespaceURI(), w3cNS);
        Attribute formDefault = new Attribute("elementFormDefault","qualified");
        schemaElem.setAttribute(formDefault);
        
        for (Iterator j = relatedNamespacesImports.iterator(); j.hasNext();) {
            Element namespaceImport = (Element) j.next();
            schemaElem.addContent(namespaceImport);
        }
        for (Iterator i = subclassifiers.iterator(); i.hasNext();) {
            UmlClass klass = (UmlClass) i.next();
            doMapping(klass, schemaElem,w3cNS);
        }

        Document doc = new Document();
        doc.setRootElement(schemaElem);
        return doc;
    }

    private void doMapping(UmlClass klass, Element mappingEl, Namespace w3cNS) {
        //Get table
    	String superClassName = null;
    	UmlClass superClass = UML13Utils.getSuperClass(klass);
        if (superClass != null) {
           String klassPackage = null;
           String superClassPackage = null;
           klassPackage = getPackage(klass);
           superClassPackage = getPackage(superClass);
           if (klassPackage.equals(superClassPackage)){
        	   superClassName = superClass.getName();
           } else {
        	   superClassName = getPackage(superClass)+':'+superClass.getName();
           }
        } 	
        Element classEl = new Element("element", w3cNS);
        classEl.setAttribute("name",klass.getName());
        classEl.setAttribute("type", klass.getName());
        mappingEl.addContent(classEl);
        Element classE2 = new Element("complexType", w3cNS);

        classE2.setAttribute("name", klass.getName());
        mappingEl.addContent(classE2);
        
        if (superClassName!=null) {
        	Element complexContent = new Element("complexContent", w3cNS);
        	Element extension = new Element("extension",w3cNS);
        	extension.setAttribute("base", superClassName);
        	complexContent.addContent(extension);
        	classE2.addContent(complexContent);
        	Element sequence = new Element("sequence",w3cNS);
            extension.addContent(sequence);

            //Do properties
            for (Iterator i = UML13Utils.getAttributes(klass).iterator(); i.hasNext();) {
                org.omg.uml.foundation.core.Attribute att = (org.omg.uml.foundation.core.Attribute) i.next();
                log.debug("att.getName(): " + att.getName()); 
                
                // Only process non-static attributes
            	log.debug("isStatic: " + UML13Utils.isStatic(att));
                if (!UML13Utils.isStatic(att)){
	                Element attributeElement = new Element("attribute", w3cNS);
	                attributeElement.setAttribute("name", att.getName());
	                if (getName(att.getType()).equals("xs:collection")) {
	                    attributeElement.setAttribute("type","xs:string");
	                } else {
	                    attributeElement.setAttribute("type", getName(att.getType()));
	                }
	                extension.addContent(attributeElement);
                }
            }
            
            for (Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();) {
                AssociationEnd thisEnd = (AssociationEnd) i.next();
                AssociationEnd otherEnd = UML13Utils.getOtherAssociationEnd(thisEnd);
                addSequenceAssociationElement(sequence, klass,thisEnd, otherEnd,w3cNS);
            }
        } else {
        Element sequence = new Element("sequence",w3cNS);
        classE2.addContent(sequence);

        //Do properties
        for (Iterator i = UML13Utils.getAttributes(klass).iterator(); i.hasNext();) {
            org.omg.uml.foundation.core.Attribute att = (org.omg.uml.foundation.core.Attribute) i.next();
            log.debug("att.getName(): " + att.getName());
            
            // Only process non-static attributes
        	log.debug("isStatic: " + UML13Utils.isStatic(att));
            if (!UML13Utils.isStatic(att)){

	            Element attributeElement = new Element("attribute", w3cNS);
	            attributeElement.setAttribute("name", att.getName());
	
	            if (getName(att.getType()).equals("xs:collection")) {
	                attributeElement.setAttribute("type","xs:string");
	            } else {
	                attributeElement.setAttribute("type", getName(att.getType()));
	            }
	            classE2.addContent(attributeElement);
            }
        }
        
        for (Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();) {
            AssociationEnd thisEnd = (AssociationEnd) i.next();
            AssociationEnd otherEnd = UML13Utils.getOtherAssociationEnd(thisEnd);
            addSequenceAssociationElement(sequence, klass,thisEnd, otherEnd,w3cNS);
        }
       }
    }
    
    private void addSequenceAssociationElement(Element sequence, UmlClass klass, AssociationEnd thisEnd, AssociationEnd otherEnd,Namespace w3cNS) {
        if (otherEnd.isNavigable()) {

            log.debug("sequence name: " + sequence.getName());
            log.debug("otherEnd name: " + otherEnd.getName());
            log.debug("otherEnd type: " + otherEnd.getType().getName());
            
            Element associationElement = new Element("element", w3cNS);
            sequence.addContent(associationElement);

            associationElement.setAttribute("name", otherEnd.getName());

            String associationPackage = getPackage(otherEnd.getType());
            
            String maxOccurs = UML13Utils.getUpperBound(thisEnd, otherEnd);
            log.debug("maxOccurs: " + maxOccurs);
            
            // A collection - model association as an element with the association name that 
            // has a sequence of the associated type.  See GForge #1311.
            associationElement.setAttribute("minOccurs","0");   
            associationElement.setAttribute("maxOccurs","1");
                
            Element complexType = new Element("complexType",w3cNS);
            Element innerSequence = new Element("sequence", w3cNS);
            Element associatedObjElement = new Element("element", w3cNS);
                
            associatedObjElement.setAttribute("ref", otherEnd.getType().getName());
            associatedObjElement.setAttribute("minOccurs","0");   
            associatedObjElement.setAttribute("maxOccurs",maxOccurs);  
                
            innerSequence.addContent(associatedObjElement);
            complexType.addContent(innerSequence);
            associationElement.addContent(complexType);           	

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
    public void configure(org.w3c.dom.Element config)
            throws ConfigurationException {

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
    private String getName(ModelElement me) {
        String qName = null;
        UmlPackage pkg = null;
        if (_pkgName != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(me), _pkgName);
        } else {
            pkg = UML13Utils.getModel(me);
        }
        qName = UML13Utils.getNamespaceName(pkg, me) + Constant.DOT + me.getName();

        int subString = qName.lastIndexOf(Constant.DOT);
        String finalType = "xs:";
        if (qName.endsWith("collection")) {
            finalType = finalType + "string";
        } else {
            finalType = finalType + qName.substring(subString+1).toLowerCase();
            if (finalType.equals("xs:date")) {
            	finalType = "xs:dateTime";
            }
        }
        return finalType;
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
