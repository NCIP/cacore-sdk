
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
import gov.nih.nci.common.util.Constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.Feature;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;
import org.omg.uml.modelmanagement.Model;




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
 * Produces an XML file that contains object-relational mapping configuration
 * information for use by the OJB tool ( <a
 * href="http://db.apache.org/ojb/" target="_blank">http://db.apache.org/ojb/ </a>). In
 * particular, it produces class-descriptor elements from a set classes defined
 * in a UML 1.3 model.
 * <p>
 * In order to use this transformer, the supplied UML model must contain certain
 * information, in the form of tagged values and stereotypes. This section
 * describes the control file configuration and how it relates to the code. It
 * does not describe how the UML model must be annotated (see the User's Guide
 * for that).
 * <p>
 * The content model for this transformer's configuration element is as follows:
 * <p>
 * <code>
 * <pre>
 * 
 *  &lt;!ELEMENT transformer (param, filter)&gt; 
 *  &lt;!ATTLIST transformer 
 *     name CDATA #REQUIRED 
 *     className CDATA #FIXED gov.nih.nci.codegen.core.transformer.OJBRepTransformer&gt; 
 *  &lt;!ELEMENT param EMPTY&gt; 
 *  &lt;!ATTLIST param 
 *     name CDATA #FIXED packageName 
 *     value CDATA #REQUIRED&gt;
 *  &lt;!ELEMENT filter ... see {@link gov.nih.nci.codegen.core.filter.UML13ClassifierFilter#configure(org.w3c.dom.Element)} ...
 *  
 * </pre>
 * </code>
 * <p>
 * As you can see, this transformer expects a nested filter element. The reason
 * is that this transformer produces a single Artifact (an XML file) from a
 * collection of model elements. 
 * <p>
 * UML13OJBRepTransformer expects to be passed an
 * instance of org.omg.uml.modelmanagement.Model. It uses
 * UML13ModelElementFilter to obtain all model elements in the model. Then it
 * use UML13Classifier to obtain the classifiers selected by the contents of the
 * nested filter element. Then it iterates through these classifiers, building
 * the class-descriptor elements.
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
public class UML13OJBRepTransformer implements Transformer, XMLConfigurable {

    private static Logger log = Logger
            .getLogger(UML13OJBRepTransformer.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName;

    /**
     * Creates an instance of UML13OJBRepTransformer
     */
    public UML13OJBRepTransformer() {
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
        Element rep = generateRepository(classifiers);
        XMLOutputter p = new XMLOutputter();
        p.setFormat(Format.getPrettyFormat());

        newArtifacts.add(new BaseArtifact("ojb_repository", modelElement, p
                .outputString(rep)));
        return newArtifacts;
    }

    private Element generateRepository(Collection classifiers) {
        Element root = new Element("class-descriptors");

        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            Classifier klass = (Classifier) i.next();

            
            Classifier table = getTable(klass);

          
            log.debug("creating class-descriptor for " + klass.getName());

            String classExp = "class-descriptor[@class='" + _pkgName + "impl."
                    + klass.getName() + "Impl']";
            Element classDescEl = null;
            try {
                classDescEl = (Element) (new JDOMXPath(classExp))
                        .selectSingleNode(root);
            } catch (JaxenException ex) {
            	log.error("error selecting " + classExp + ": "+ ex.getMessage());
                throw new RuntimeException("error selecting " + classExp, ex);
            }
            if (classDescEl == null) {

               
                classDescEl = new Element("class-descriptor");
                root.addContent(classDescEl);
                classDescEl.setAttribute("class", _pkgName + "impl."
                        + klass.getName() + "Impl");
            }

            classDescEl.setAttribute("table", table.getName());
            classDescEl.setAttribute("initialization-method", "initialize");

            HashMap assocMap = new HashMap();
            log.debug("creating field-descriptors...");

            /**
             * If this class is a subclass of some other class, then it must
             * define all inherited attributes and associations.
             * 
             * If class is a superclass, it should have an ojbConcreteClass
             * field-descriptor.
             */

            if (isSuperClass(klass)) {
                log.debug("created ojbConcreteClass field-descriptor for "
                        + klass.getName());
                Element fdEl = new Element("field-descriptor");
                classDescEl.addContent(fdEl);
                fdEl.setAttribute("name", "ojbConcreteClass");
                fdEl.setAttribute("column", "CLASS_NAME");
                fdEl.setAttribute("access", "anonymous");
                fdEl.setAttribute("jdbc-type", "VARCHAR");
            }

            Collection columns = UML13Utils.getAttributes((UmlClass) table);
            log.debug("got " + columns.size() + " columns");
            for (Iterator j = columns.iterator(); j.hasNext();) {
                Attribute column = (Attribute) j.next();
                String attName = getAttributeName(klass, column);
                if (attName == null) {
                    log.debug("no value for att name found for "
                            + klass.getName() + " -> " + table.getName() + Constant.DOT
                            + column.getName() + ", continuing...");
                    continue;
                }
                log.debug("creating field-descriptor for "
                        + klass.getName() + Constant.DOT + attName);
                String colName = column.getName();
                String dbType = column.getType().getName();
                if (dbType == null) {
                	log.error("no db type for "
                            + table.getName() + Constant.DOT + column.getName());
                    throw new RuntimeException("no db type for "
                            + table.getName() + Constant.DOT + column.getName());
                }
                String jdbcType = getJDBCType(dbType);

                
                String primaryKey = "false";

                Stereotype s = UML13Utils.getStereotype(column);
                if (s != null && "PK".equals(s.getName())
                        || "ID".equals(colName)) {
                    primaryKey = "true";
                }

                Element fieldDescEl = new Element("field-descriptor");
                classDescEl.addContent(fieldDescEl);
                fieldDescEl.setAttribute("name", attName);
                fieldDescEl.setAttribute("column", colName);
                fieldDescEl.setAttribute("jdbc-type", jdbcType);
                fieldDescEl.setAttribute("primarykey", primaryKey);

                String conversion = getConversion(column);
                if (conversion != null) {
                    fieldDescEl.setAttribute("conversion", conversion);
                }

             
                if (!hasAttribute(klass, attName, true)
                        && !"true".equals(primaryKey)) {
                    fieldDescEl.setAttribute("access", "anonymous");
                }

               
                String assocName = getImplementedAssociationName(klass, column,
                        attName);
           
                if (assocName != null) {
                    log.debug("adding assocName " + assocName
                            + " in assocMap");
                    assocMap.put(assocName, attName);
                }

            }
            log.debug("...done field-descriptors");
            
            log.debug("creating associations...");
            for (Iterator j = UML13Utils.getAssociationEnds(klass, true)
                    .iterator(); j.hasNext();) {
                AssociationEnd thisEnd = (AssociationEnd) j.next();
                AssociationEnd otherEnd = UML13Utils
                        .getOtherAssociationEnd(thisEnd);
                Classifier otherEndTable = getTable(otherEnd.getType());
                if (otherEndTable == null) {
                    log.warn(klass.getName() + Constant.DOT + otherEnd.getName()
                            + " -> " + otherEnd.getType().getName()
                            + " is not persistent, continuing...");
                    continue;
                }
          
                Element ordEl = null;
                if (UML13Utils.isOne2One(thisEnd, otherEnd)
                        || UML13Utils.isMany2One(thisEnd, otherEnd)) {
                   
                    log.debug("creating reference-descriptor "
                            + klass.getName() + Constant.DOT + otherEnd.getName());
               
                    Attribute fkColumn = getFKColumn(klass, table, otherEnd
                            .getName());
                    if (fkColumn == null) {
                        log.warn("no fk column found for "
                                + klass.getName() + " - " + table.getName()
                                + "->" + otherEnd.getName());
                        continue;
                    }
                    String fieldRefName = getAttributeName(klass, fkColumn);
                    if (fieldRefName == null) {
                        log.warn("no field ref name found for "
                                + klass.getName() + Constant.DOT + thisEnd.getName()
                                + "<->" + otherEnd.getName());
                        continue;
                    }
                    Element refDescEl = new Element("reference-descriptor");
                    classDescEl.addContent(refDescEl);
                    ordEl = refDescEl;
                    refDescEl.setAttribute("name", otherEnd.getName());
                    refDescEl.setAttribute("class-ref", _pkgName + "impl."
                            + otherEnd.getType().getName() + "Impl");
                    Element fkEl = new Element("foreignkey");
                    refDescEl.addContent(fkEl);
                    fkEl.setAttribute("field-ref", fieldRefName);
                } else if (UML13Utils.isOne2Many(thisEnd, otherEnd)) {
                   
                    log.debug("creating 1:n collection-descriptor "
                            + klass.getName() + Constant.DOT + otherEnd.getName());
                    String invFKFieldRef = getInvFKFieldRef(otherEnd.getType(),
                            otherEndTable, thisEnd.getName());
                    if (invFKFieldRef == null) {
                    	log.error( "couldn't find inv fk field ref for "
                                        + klass.getName() + Constant.DOT
                                        + thisEnd.getName() + "<->"
                                        + otherEnd.getName());
                        throw new RuntimeException(
                                "couldn't find inv fk field ref for "
                                        + klass.getName() + Constant.DOT
                                        + thisEnd.getName() + "<->"
                                        + otherEnd.getName());
                    }
                    Element colDescEl = new Element("collection-descriptor");
                    classDescEl.addContent(colDescEl);
                    ordEl = colDescEl;
                    colDescEl.setAttribute("name", otherEnd.getName());
                    colDescEl.setAttribute("element-class-ref", _pkgName
                            + "impl." + otherEnd.getType().getName() + "Impl");
                    Element invFKEl = new Element("inverse-foreignkey");
                    colDescEl.addContent(invFKEl);
                    invFKEl.setAttribute("field-ref", invFKFieldRef);
                } else if (UML13Utils.isMany2Many(thisEnd, otherEnd)) {
                    log.debug("creating m:n collection-descriptor "
                            + klass.getName() + Constant.DOT + otherEnd.getName());
                    Classifier indirTable = getCorrelationTable(table, thisEnd,
                            otherEnd);

                    if (indirTable == null) {
                        log.error("no indirection table found for "
                                + klass.getName() + Constant.DOT + thisEnd.getName()
                                + "<->" + otherEnd.getName() + Constant.DOT
                                + otherEnd.getType().getName());
                        continue;
                    }

                    Attribute fkPointingToThisClass = getFKColumn(otherEnd
                            .getType(), indirTable, thisEnd.getName());
                    Attribute fkPointingToElementClass = getFKColumn(thisEnd
                            .getType(), indirTable, otherEnd.getName());
                    Element colDescEl = new Element("collection-descriptor");
                    classDescEl.addContent(colDescEl);
                    ordEl = colDescEl;
                    colDescEl.setAttribute("name", otherEnd.getName());
                    colDescEl.setAttribute("element-class-ref", _pkgName
                            + "impl." + otherEnd.getType().getName() + "Impl");
                    colDescEl.setAttribute("indirection-table", indirTable
                            .getName());
                    Element fkpttcEl = new Element("fk-pointing-to-this-class");
                    colDescEl.addContent(fkpttcEl);
                    fkpttcEl.setAttribute("column", fkPointingToThisClass
                            .getName());
                    Element fkptecEl = new Element(
                            "fk-pointing-to-element-class");
                    colDescEl.addContent(fkptecEl);
                    fkptecEl.setAttribute("column", fkPointingToElementClass
                            .getName());
                } else {
                	log.error("unknown assoc multiplicity for " + klass.getName()
                            + Constant.DOT + thisEnd.getName() + "<->"
                            + otherEnd.getName());
                    throw new RuntimeException(
                            "unknown assoc multiplicity for " + klass.getName()
                                    + Constant.DOT + thisEnd.getName() + "<->"
                                    + otherEnd.getName());
                }

                ordEl.setAttribute("auto-retrieve", "false");

                Element recipRolEl = new Element("attribute");
                ordEl.addContent(recipRolEl);
                recipRolEl.setAttribute("attribute-name", "reciprocol-role");
                recipRolEl.setAttribute("attribute-value", thisEnd.getName());

                String navigableStr = Boolean.toString(otherEnd.isNavigable());
                Element navigableEl = new Element("attribute");
                ordEl.addContent(navigableEl);
                navigableEl.setAttribute("attribute-name", "navigable");
                navigableEl.setAttribute("attribute-value", navigableStr);

            }
            log.debug("...done associations.");

            
            String interfaceExp = "class-descriptor[@class='" + _pkgName
                    + klass.getName() + "']";
            Element interfaceEl = null;
            try {
                interfaceEl = (Element) (new JDOMXPath(interfaceExp))
                        .selectSingleNode(root);
            } catch (JaxenException ex) {
            	log.error("error selecting " + interfaceExp +" :" + ex.getMessage());
                throw new RuntimeException("error selecting " + interfaceExp,
                        ex);
            }
            if (interfaceEl == null) {
                interfaceEl = new Element("class-descriptor");
                root.addContent(interfaceEl);
                interfaceEl.setAttribute("class", _pkgName + klass.getName());
            }
            String extentExp = "extent-class[@class-ref='" + _pkgName + "impl."
                    + klass.getName() + "Impl']";
            Element extentEl = null;
            try {
                extentEl = (Element) (new JDOMXPath(extentExp))
                        .selectSingleNode(interfaceEl);
            } catch (JaxenException ex) {
            	log.error( "error selecting " + extentExp+ " : " + ex.getMessage());
                throw new RuntimeException("error selecting " + extentExp, ex);
            }
            if (extentEl == null) {
                extentEl = new Element("extent-class");
                interfaceEl.addContent(extentEl);
                extentEl.setAttribute("class-ref", _pkgName + "impl."
                        + klass.getName() + "Impl");
            }

            
            Classifier superClass = UML13Utils.getSuperClass((UmlClass) klass);
            while (superClass != null) {
               
                String intExp = "class-descriptor[@class='" + _pkgName
                        + "impl." + superClass.getName() + "Impl']";
                Element intEl = null;
                try {
                    intEl = (Element) (new JDOMXPath(intExp))
                            .selectSingleNode(root);
                } catch (JaxenException ex) {
                	log.error("error selecting " + intExp + " : " + ex.getMessage());
                    throw new RuntimeException("error selecting " + intExp, ex);
                }
                if (intEl == null) {

                    
                    intEl = new Element("class-descriptor");
                    root.addContent(intEl);
                    intEl.setAttribute("class", _pkgName + "impl."
                            + superClass.getName() + "Impl");
                }

               
                String extExp = "extent-class[@class-ref='" + _pkgName
                        + "impl." + klass.getName() + "Impl']";
                Element extEl = null;
                try {
                    extEl = (Element) (new JDOMXPath(extExp))
                            .selectSingleNode(intEl);
                } catch (JaxenException ex) {
                	log.error("error selecting " + extExp + " : " + ex.getMessage());
                    throw new RuntimeException("error selecting " + extExp, ex);
                }
                if (extEl == null) {

                  
                    extEl = new Element("extent-class");
                    intEl.addContent(extEl);
                    extEl.setAttribute("class-ref", _pkgName + "impl."
                            + klass.getName() + "Impl");
                }
                superClass = UML13Utils.getSuperClass((UmlClass) superClass);
            }

            /**
             * Interface inheritance
             */
            superClass = UML13Utils.getSuperClass((UmlClass) klass);
            while (superClass != null) {
                
                String intExp = "class-descriptor[@class='" + _pkgName
                        + superClass.getName() + "']";
                Element intEl = null;
                try {
                    intEl = (Element) (new JDOMXPath(intExp))
                            .selectSingleNode(root);
                } catch (JaxenException ex) {
                	log.error("error selecting " + intExp + " : " + ex.getMessage());
                    throw new RuntimeException("error selecting " + intExp, ex);
                }
                if (intEl == null) {

                   
                    intEl = new Element("class-descriptor");
                    root.addContent(intEl);
                    intEl
                            .setAttribute("class", _pkgName
                                    + superClass.getName());
                }

               
                String extExp = "extent-class[@class-ref='" + _pkgName
                        + klass.getName() + "']";
                Element extEl = null;
                try {
                    extEl = (Element) (new JDOMXPath(extExp))
                            .selectSingleNode(intEl);
                } catch (JaxenException ex) {
                	log.error("error selecting " + extExp + " : " + ex.getMessage());
                    throw new RuntimeException("error selecting " + extExp, ex);
                }
                if (extEl == null) {

                   
                    extEl = new Element("extent-class");
                    intEl.addContent(extEl);
                    extEl.setAttribute("class-ref", _pkgName + klass.getName());
                }
                superClass = UML13Utils.getSuperClass((UmlClass) superClass);
            }

        }

        return root;
    }

    private String getImplementedAssociationName(Classifier klass,
            Attribute column, String attName) {
        log.debug("class = " + klass.getName() + ", column = "
                + column.getName() + ", attName = " + attName);
        String assocName = null;
        uml.UmlPackage umlExtent = (uml.UmlPackage) klass.refOutermostPackage();
        Collection tvs = umlExtent.getFoundation().getExtensionMechanisms()
                .getAModelElementTaggedValue().getTaggedValue(column);
        log.debug("got " + tvs.size() + " tagged values");
        Classifier superClass = klass;
        search: while (superClass != null) {
            for (Iterator i = tvs.iterator(); i.hasNext();) {
                TaggedValue tv = (TaggedValue) i.next();
                log.debug("checking " + tv.getTag() + Constant.EQUAL + tv.getValue()
                        + " against " + superClass.getName() + Constant.DOT + attName);
                if ("implements-association".equals(tv.getTag())
                        && tv.getValue().equals(
                                superClass.getName() + Constant.DOT + attName)) {
                    assocName = tv.getValue().substring(
                            tv.getValue().indexOf(Constant.DOT) + 1);
                    break search;
                }
            }
            superClass = UML13Utils.getSuperClass((UmlClass) superClass);
        }
        return assocName;
    }

    private boolean hasAttribute(Classifier klass, String attName,
            boolean includeInherited) {
        
        boolean hasAtt = false;
        Classifier superClass = klass;
        search: while (superClass != null) {
            for (Iterator i = superClass.getFeature().iterator(); i.hasNext();) {
                Feature f = (Feature) i.next();
                if (f instanceof Attribute) {
                    Attribute att = (Attribute) f;
                    if (attName.equals(att.getName())) {
                        hasAtt = true;
                        break search;
                    }
                }
            }
            if (includeInherited) {
                superClass = UML13Utils.getSuperClass((UmlClass) superClass);
            } else {
                superClass = null;
            }
        }
        return hasAtt;

    }

    private String getConversion(Attribute column) {
        String convStr = null;
        TaggedValue convTv = UML13Utils.getTaggedValue(column, "conversion");
        if (convTv != null) {
            convStr = convTv.getValue();
        }
        return convStr;
    }

    private String getJDBCType(String dbType) {
        String jdbcType = null;
        if (dbType.startsWith("VARCHAR")) {
            jdbcType = "VARCHAR";
        } else if (dbType.startsWith("NUMBER")) {
            jdbcType = "BIGINT";
        } else {
        	log.error("Unknown db type: " + dbType);
            throw new RuntimeException("Unknown db type: " + dbType);
        }
        return jdbcType;
    }

    private String getAttributeName(Classifier klass, Attribute column) {
        log.debug("klass = " + klass.getName() + ", column = "
                + column.getName());
        String attName = null;
        uml.UmlPackage umlExtent = (uml.UmlPackage) klass.refOutermostPackage();
        Collection tvs = umlExtent.getFoundation().getExtensionMechanisms()
                .getAModelElementTaggedValue().getTaggedValue(column);
        log.debug("got " + tvs.size() + " tagged values");
        Classifier superClass = klass;
        search: while (superClass != null) {
            for (Iterator i = tvs.iterator(); i.hasNext();) {
                TaggedValue tv = (TaggedValue) i.next();
                log.debug("checking " + tv.getTag() + Constant.EQUAL + tv.getValue());
                if ("maps-to-attribute".equals(tv.getTag())
                        && tv.getValue().startsWith(superClass.getName())) {
                    attName = tv.getValue().substring(
                            tv.getValue().indexOf(Constant.DOT) + 1);
                    break search;
                }
            }
            superClass = UML13Utils.getSuperClass((UmlClass) superClass);
        }
        return attName;
    }

    private boolean isSuperClass(Classifier klass) {
        return klass.getSpecialization().size() > 0;
    }

    private Classifier getCorrelationTable(Classifier table,
            AssociationEnd thisEnd, AssociationEnd otherEnd) {
        Classifier corrTable = null;
        for (Iterator i = UML13Utils.getAssociationEnds(table).iterator(); i
                .hasNext();) {
            AssociationEnd te = (AssociationEnd) i.next();
            AssociationEnd oe = UML13Utils.getOtherAssociationEnd(te);
            Classifier c = oe.getType();
            if (c.getClientDependency().size() == 2) {
                Iterator j = c.getClientDependency().iterator();
                Dependency depOne = (Dependency) j.next();
                Stereotype depOneS = UML13Utils.getStereotype(depOne);
                Dependency depTwo = (Dependency) j.next();
                Stereotype depTwoS = UML13Utils.getStereotype(depTwo);

                if ("Correlation".equals(depOneS.getName())
                        && "Correlation".equals(depTwoS.getName())) {
                    log.debug("got the correlations");
                    Classifier suppOne = (Classifier) depOne.getSupplier()
                            .iterator().next();
                    Classifier suppTwo = (Classifier) depTwo.getSupplier()
                            .iterator().next();
                    log.debug("suppOne = " + suppOne.getName()
                            + ", suppTwo = " + suppTwo.getName());
                    if ((suppOne == thisEnd.getType() || suppOne == otherEnd
                            .getType())
                            && (suppTwo == thisEnd.getType() || suppTwo == otherEnd
                                    .getType())) {
                        log.debug("suppliers match");
                        if (getFKColumn(otherEnd.getType(), c, thisEnd
                                .getName()) != null
                                && getFKColumn(thisEnd.getType(), c, otherEnd
                                        .getName()) != null) {
                            corrTable = c;
                            break;
                        }
                    }
                }
            }
        }
        return corrTable;
    }

    private Attribute getFKColumn(Classifier klass, Classifier table,
            String name) {
        log.debug("class = " + klass.getName() + ", table = "
                + table.getName() + ", name = " + name);
        Attribute column = null;
        for (Iterator i = UML13Utils.getAttributes((UmlClass) table).iterator(); i
                .hasNext();) {
            Attribute aColumn = (Attribute) i.next();
            if (getImplementedAssociationName(klass, aColumn, name) != null) {
                column = aColumn;
                break;
            }
        }
        return column;
    }

    private Classifier getTable(Classifier klass) {
        Classifier table = null;
        Collection clients = new ArrayList();
        for (Iterator j = klass.getSupplierDependency().iterator(); j.hasNext();) {
            Dependency d = (Dependency) j.next();
            Stereotype s = UML13Utils.getStereotype(d);
            if (s != null && "DataSource".equals(s.getName())) {
                clients.addAll(d.getClient());
            }
        }
        if (clients.size() != 1) {
            log.error(clients.size() + " data sources found for "
                    + klass.getName());
        } else {
            table = (Classifier) clients.iterator().next();
        }
        return table;
    }

    private String getInvFKFieldRef(Classifier klass, Classifier table,
            String attName) {
        log.debug("class = " + klass.getName() + ", table = "
                + table.getName() + ", attName = " + attName);
        String fieldRef = null;
        for (Iterator i = UML13Utils.getAttributes((UmlClass) table).iterator(); i
                .hasNext();) {
            Attribute column = (Attribute) i.next();
            if (getImplementedAssociationName(klass, column, attName) != null) {
                fieldRef = getAttributeName(klass, column);
                break;
            }
        }
        return fieldRef;
    }

    /*
     * (non-Javadoc)
     * 
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
        org.w3c.dom.Element paramEl = XMLUtils.getChild(config, "param");
        _pkgName = paramEl.getAttribute("packageName");
        if (_pkgName == null) {
            _pkgName = "";
        } else {
            _pkgName = _pkgName + Constant.DOT;
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
}
