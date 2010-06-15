package gov.nih.nci.codegen.core.filter;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.util.UML13Utils;
import gov.nih.nci.codegen.framework.Filter;
import gov.nih.nci.codegen.framework.FilteringException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Pattern;
import org.jaxen.XPath;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Interface;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
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
 * The most complex of the supplied filters. It allows one to create a "query"
 * for objects that implement org.omg.uml.foundation.core.Classifier. This
 * interface represents Interface or Class model elements, which you typically
 * find in UML Class Diagrams. The query is expressed in XML. See
 * {@link #configure(org.w3c.dom.Element)}for information about the format of
 * this query.
 * @author caBIO Team
 * @version 1.0
 */

public class UML13ClassifierFilter implements Filter, XMLConfigurable {

    private static Perl5Compiler _compiler = new Perl5Compiler();

    private static XPath _extendsExcludeXPath;

    private static XPath _extendsIncludeXPath;

    private static XPath _implementsExcludeXPath;

    private static XPath _implementsIncludeXPath;

    private static Logger log = Logger.getLogger(UML13ClassifierFilter.class);

    private static XPath _matchAbstractXPath;

    private static Perl5Matcher _matcher = new Perl5Matcher();

    private static XPath _matchInterfaceXPath;

    private static XPath _nameExcludeXPath;

    private static XPath _nameIncludeXPath;

    private static XPath _packageExcludeXPath;

    private static XPath _packageIncludeXPath;

    private static XPath _stereotypeExcludeXPath;

    private static XPath _stereotypeIncludeXPath;

    private Perl5Pattern _extendsExclude;

    private Perl5Pattern _extendsInclude;

    private Perl5Pattern _implementsExclude;

    private Perl5Pattern _implementsInclude;

    private Boolean _matchAbstract;

    private Boolean _matchInterface;

    private Perl5Pattern _nameExclude;

    private Perl5Pattern _nameInclude;

    private Perl5Pattern _packageExclude;

    private Perl5Pattern _packageInclude;

    private Perl5Pattern _stereotypeExclude;

    private Perl5Pattern _stereotypeInclude;

    /**
     *  
     */
    public UML13ClassifierFilter() {
        init();
    }

    private Perl5Pattern compile(String p) throws MalformedPatternException {
        return (Perl5Pattern) _compiler.compile(p);
    }

    private void configure(Element config) throws ConfigurationException {
        try {
            setPatterns(config);
        } catch (Exception ex) {
        	log.error(ex.getMessage());
            throw new ConfigurationException(ex);
        }
    }

    /**
     * The content model expected by this method is as follows.
     * 
     * <code>
     * <pre>
   *           &lt;!--
     *            filter:          
     *            The sub elements of filter represent criteria that will be
     *            ANDed together in the evaluation of each Classifier.
     *            --&gt;
     *           &lt;!ELEMENT filter (match-interface?, match-abstract?, package?, name?, 
     *                            stereotype?, extends?, implements?)&gt;
     *           &lt;!ATTLIST filter
     *              name CDATA #REQUIRED
     *              className CDATA #REQUIRED&gt;
     *          
     *           &lt;!--
     *             match-interface:
     *            
     *             If this element is not present, then both Interface and UmlClass
     *             model elements will be selected.
     *          
     *             If this element is present and the value of its value attribute is
     *             &quot;true&quot;, then only Interface model elements will be selected.
     *          
     *             If this element is present and the value of its value attribute is
     *             &quot;false&quot;, then no Interface model elements will be selected.
     *             --&gt;
     *           &lt;!ELEMENT match-interface EMPTY&gt;
     *           &lt;!ATTLIST match-interface
     *              value CDATA #REQUIRED&gt;
     *          
     *           &lt;!--
     *             match-abstract:
     *            
     *             If this element is not present, then both abstract and concrete
     *             Classifier objects will be selected.
     *           
     *             If this element is present and the value of its value attribute is
     *             &quot;true&quot;, then only abstract Classifier objects will be selected.
     *           
     *             If this element is present and the value of its value attribute is
     *             &quot;false&quot;, then no abstract Classifier objects will be selected.
     *             --&gt;
     *           &lt;!ELEMENT match-abstract EMPTY&gt;
     *           &lt;!ATTLIST match-abstract
     *              value CDATA #REQUIRED&gt;
     *          
     *           &lt;!--
     *             include, exclude:
     *           
     *             The elements package, name, stereotype, extends, and implements
     *             all may have zero or one include sub element and zero or one
     *             exclude sub element. One or the other of these two elements must
     *             be present.
     *          
     *             The content of the include and exclude elements is interpreted as
     *             a PERL 5 regular expression. This expression is matched against some
     *             string that is derived from the Classifier being evaluated. How that
     *             string is derived depends on the parent element.
     *           
     *             If the string matches the include expression and does not match the
     *             exclude expression, then the Classifier is selected.
     *             --&gt;
     *           &lt;!ELEMENT include (#PCDATA)&gt;
     *           &lt;!ELEMENT exclude (#PCDATA)&gt;
     *          
     *           &lt;!--
     *             package:
     *          
     *             The derived string is sequence of namespace names, from outermost
     *             to direct namespace of the Classifier, separated by '.' characters.
     *             --&gt;
     *           &lt;!ELEMENT package (include?, exclude?)&gt;
     *          
     *           &lt;!--
     *             name:
     *          
     *             The derived string is the unqualified name of the Classifier object.
     *             --&gt;
     *           &lt;!ELEMENT name (include?, exclude?)&gt;
     *          
     *           &lt;!--
     *             stereotype:
     *           
     *             The derived string is the unqualified name of the Stereotype that is
     *             associated with the Classifier object.
     *             --&gt;
     *           &lt;!ELEMENT stereotype (include?, exclude?)&gt;
     *          
     *           &lt;!--
     *             extends:
     *            
     *             The patterns in the include and exclude elements are compared to the
     *             qualified name of all Classifiers to the evaluated Classifier is associated
     *             by a generalization association. This is done recursively up the inheritance
     *             hierarchy.
     *             --&gt;
     *           &lt;!ELEMENT extends (include?, exclude?)&gt;
     *          
     *           &lt;!--
     *             implements:
     *          
     *             The patterns in the include and exclude elements are compared to the
     *             qualified name of all Classifiers to the evaluated Classifier is associated
     *             by a abstraction association. This is done recursively up the inheritance
     *             hierarchy.
     *             --&gt;
     *           &lt;!ELEMENT extends (include?, exclude?)&gt;
     * </pre>
     * </code>
     * 
     * @param config
     *            the config Element.
     * @see gov.nih.nci.codegen.core.XMLConfigurable#configure(org.w3c.dom.Element)
     */
    public void configure(org.w3c.dom.Element config)
            throws ConfigurationException {
        configure((new DOMBuilder().build(config)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.codegen.framework.Filter#execute(java.util.Collection)
     */
    public Collection execute(Collection modelElements)
            throws FilteringException {
        Collection results = new ArrayList();
        for (Iterator i = modelElements.iterator(); i.hasNext();) {
            Object obj = i.next();
            if (obj instanceof Classifier) {
                Classifier c = (Classifier) obj;

                if (matches(c)) {
                    results.add(c);
                } else {
                   ; // Do nothing?
                }

            }
        }
        return results;
    }

    private Perl5Pattern getExtendsExclude() {
        return _extendsExclude;
    }

    private Perl5Pattern getExtendsInclude() {
        return _extendsInclude;
    }

    private Perl5Pattern getImplementsExclude() {
        return _implementsExclude;
    }

    private Perl5Pattern getImplementsInclude() {
        return _implementsInclude;
    }

    private Boolean getMatchAbstract() {
        return _matchAbstract;
    }

    private Boolean getMatchInterface() {
        return _matchInterface;
    }

    private Perl5Pattern getNameExclude() {
        return _nameExclude;
    }

    private Perl5Pattern getNameInclude() {
        return _nameInclude;
    }

    private Perl5Pattern getPackageExclude() {
        return _packageExclude;
    }

    private Perl5Pattern getPackageInclude() {
        return _packageInclude;
    }

    private Perl5Pattern getStereotypeExclude() {
        return _stereotypeExclude;
    }

    private Perl5Pattern getStereotypeInclude() {
        return _stereotypeInclude;
    }

    private void init() {
        if (_packageIncludeXPath == null) {
            try {
                _matchAbstractXPath = new JDOMXPath("match-abstract");
                _matchInterfaceXPath = new JDOMXPath("match-interface");
                _packageIncludeXPath = new JDOMXPath("package/include");
                _packageExcludeXPath = new JDOMXPath("package/exclude");
                _nameIncludeXPath = new JDOMXPath("name/include");
                _nameExcludeXPath = new JDOMXPath("name/exclude");
                _stereotypeIncludeXPath = new JDOMXPath("stereotype/include");
                _stereotypeExcludeXPath = new JDOMXPath("stereotype/exclude");
                _extendsIncludeXPath = new JDOMXPath("extends/include");
                _extendsExcludeXPath = new JDOMXPath("extends/exclude");
                _implementsIncludeXPath = new JDOMXPath("implements/include");
                _implementsExcludeXPath = new JDOMXPath("implements/exclude");
            } catch (Exception ex) {
            	log.error(ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }

    private boolean matches(Classifier c) {
        String className = UML13Utils.getQualifiedName(c);
        boolean matches = true;
        if (matches) {
            matches = (getMatchInterface() == null || (c instanceof Interface) == getMatchInterface()
                    .booleanValue());
            if (!matches) {
                log.debug(className + " failed interface");
            } else {
                log.debug(className + " passed interface");
            }
        }

        if (matches) {
            matches = (getMatchAbstract() == null || c.isAbstract() == getMatchAbstract()
                    .booleanValue());
            if (!matches) {
                log.debug(className + " failed abstract");
            } else {
                log.debug(className + " passed abstract");
            }
        }
        if (matches) {

            matches = matches(getPackageInclude(), getPackageExclude(),
                    UML13Utils.getNamespaceName(c));
            if (!matches) {
                log.debug(className + " failed package");
            } else {
                log.debug(className + " passed package");
            }
        }
        if (matches) {
            matches = matches(getNameInclude(), getNameExclude(), c.getName());
            if (!matches) {
                log.debug(className + " failed name");
            } else {
                log.debug(className + " passed name");
            }
        }

        if (matches) {
            String stereotype = null;
            Stereotype ster = UML13Utils.getStereotype(c);
            if (ster != null) {
                stereotype = ster.getName();
            } else {
                stereotype = "";
            }
            matches = matches(getStereotypeInclude(), getStereotypeExclude(),
                    stereotype);
            if (!matches) {
                log.debug(className + " failed stereotype");
            } else {
                log.debug(className + " passed stereotype");
            }
        }

        if (matches) {
            UmlClass superClass = null;
            if (c instanceof UmlClass) {
                superClass = UML13Utils.getSuperClass((UmlClass) c);
            }

            if (superClass == null && getExtendsInclude() != null) {
                matches = false;
            } else if (superClass != null
                    && (getExtendsInclude() != null || getExtendsExclude() != null)) {

                /**
                 * Do excludes
                 */
                ArrayList notExcluded = new ArrayList();
                Perl5Pattern excludePatt = getExtendsExclude();
                while (superClass != null) {
                    String qName = UML13Utils.getQualifiedName(superClass);
                    if (excludePatt == null
                            || matches(null, getExtendsExclude(), qName)) {
                        notExcluded.add(superClass);
                    } else {
                        notExcluded.clear();
                        break;
                    }
                    superClass = UML13Utils.getSuperClass(superClass);
                }
                /**
                 * Do includes
                 */
                ArrayList included = new ArrayList();
                for (Iterator i = notExcluded.iterator(); i.hasNext();) {
                    UmlClass aSuperClass = (UmlClass) i.next();
                    String qName = UML13Utils.getQualifiedName(aSuperClass);
                    if (matches(getExtendsInclude(), null, qName)) {
                        included.add(aSuperClass);
                    }
                }

                if (included.size() == 0) {
                    matches = false;
                }
            }
            if (!matches) {
                log.debug(className + " failed extends");
            } else {
                log.debug(className + " passed extends");
            }
        }
        if (matches) {
            Collection interfaces = new ArrayList();
            if (c instanceof UmlClass) {
                interfaces = UML13Utils.getInterfaces((UmlClass) c, true);
            }

            if (interfaces.size() == 0 && getImplementsInclude() != null) {
                matches = false;
            } else if (interfaces.size() > 0
                    && (getImplementsInclude() != null || getImplementsExclude() != null)) {

                ArrayList notExcluded = new ArrayList();
                for (Iterator i = interfaces.iterator(); i.hasNext();) {
                    Interface ab = (Interface) i.next();
                    if (matches(null, getImplementsExclude(), UML13Utils
                            .getQualifiedName(ab))) {
                        notExcluded.add(ab);
                    }
                }
                ArrayList included = new ArrayList();
                for (Iterator i = notExcluded.iterator(); i.hasNext();) {
                    Interface ab = (Interface) i.next();
                    if (matches(getImplementsInclude(), null, UML13Utils
                            .getQualifiedName(ab))) {
                        included.add(ab);
                    }
                }

                if (included.size() == 0) {
                    matches = false;
                }
            }
            if (!matches) {
                log.debug(className + " failed implements");
            } else {
                log.debug(className + " passed implements");
            }
        }
        return matches;
    }

    private boolean matches(Perl5Pattern includePatt, Perl5Pattern excludePatt,
            String s) {
        boolean matches = false;
        if ((includePatt == null ? true : _matcher.matches(s, includePatt))
                && !(excludePatt == null ? false : _matcher.matches(s,
                        excludePatt))) {
            matches = true;
        }
        return matches;
    }

    private void setExtendsExclude(String p) throws MalformedPatternException {
        _extendsExclude = compile(p);
    }

    private void setExtendsInclude(String p) throws MalformedPatternException {
        _extendsInclude = compile(p);
    }

    private void setImplementsExclude(String p)
            throws MalformedPatternException {
        _implementsExclude = compile(p);
    }

    private void setImplementsInclude(String p)
            throws MalformedPatternException {
        _implementsInclude = compile(p);
    }

    private void setMatchAbstract(String b) {
        _matchAbstract = Boolean.valueOf("true".equalsIgnoreCase(b));
    }

    private void setMatchInterface(String b) {
        _matchInterface = Boolean.valueOf("true".equalsIgnoreCase(b));
    }

    private void setNameExclude(String p) throws MalformedPatternException {
        _nameExclude = compile(p);
    }

    private void setNameInclude(String p) throws MalformedPatternException {
        _nameInclude = compile(p);
    }

    private void setPackageExclude(String p) throws MalformedPatternException {
        _packageExclude = compile(p);
    }

    private void setPackageInclude(String p) throws MalformedPatternException {
        _packageInclude = compile(p);
    }

    private void setPatterns(Element node) throws Exception {

        Element ia = (Element) _matchAbstractXPath.selectSingleNode(node);
        if (ia != null) {
            String iaVal = ia.getAttributeValue("value");
            if (iaVal != null) {
                setMatchAbstract(iaVal);
            }
        }
        Element ii = (Element) _matchInterfaceXPath.selectSingleNode(node);
        if (ii != null) {
            String iiVal = ii.getAttributeValue("value");
            if (iiVal != null) {
                setMatchInterface(iiVal);
            }
        }
        Element packageIncludeEl = (Element) _packageIncludeXPath
                .selectSingleNode(node);
        if (packageIncludeEl != null) {
            setPackageInclude(packageIncludeEl.getTextTrim());
        }
        Element packageExcludeEl = (Element) _packageExcludeXPath
                .selectSingleNode(node);
        if (packageExcludeEl != null) {
            setPackageExclude(packageExcludeEl.getTextTrim());
        }
        Element nameIncludeEl = (Element) _nameIncludeXPath
                .selectSingleNode(node);
        if (nameIncludeEl != null) {
            setNameInclude(nameIncludeEl.getTextTrim());
        }
        Element nameExcludeEl = (Element) _nameExcludeXPath
                .selectSingleNode(node);
        if (nameExcludeEl != null) {
            setNameExclude(nameExcludeEl.getTextTrim());
        }
        Element stereotypeIncludeEl = (Element) _stereotypeIncludeXPath
                .selectSingleNode(node);
        if (stereotypeIncludeEl != null) {
            setStereotypeInclude(stereotypeIncludeEl.getTextTrim());
        }
        Element stereotypeExcludeEl = (Element) _stereotypeExcludeXPath
                .selectSingleNode(node);
        if (stereotypeExcludeEl != null) {
            setStereotypeExclude(stereotypeExcludeEl.getTextTrim());
        }
        Element extendsIncludeEl = (Element) _extendsIncludeXPath
                .selectSingleNode(node);
        if (extendsIncludeEl != null) {
            setExtendsInclude(extendsIncludeEl.getTextTrim());
        }
        Element extendsExcludeEl = (Element) _extendsExcludeXPath
                .selectSingleNode(node);
        if (extendsExcludeEl != null) {
            setExtendsExclude(extendsExcludeEl.getTextTrim());
        }
        Element implementsIncludeEl = (Element) _implementsIncludeXPath
                .selectSingleNode(node);
        if (implementsIncludeEl != null) {
            setImplementsInclude(implementsIncludeEl.getTextTrim());
        }
        Element implementsExcludeEl = (Element) _implementsExcludeXPath
                .selectSingleNode(node);
        if (implementsExcludeEl != null) {
            setImplementsExclude(implementsExcludeEl.getTextTrim());
        }
    }

    private void setStereotypeExclude(String p)
            throws MalformedPatternException {
        _stereotypeExclude = compile(p);
    }

    private void setStereotypeInclude(String p)
            throws MalformedPatternException {
        _stereotypeInclude = compile(p);
    }
}

