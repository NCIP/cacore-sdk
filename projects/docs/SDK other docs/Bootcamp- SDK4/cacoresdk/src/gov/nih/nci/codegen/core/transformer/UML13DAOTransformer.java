
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
import java.util.Iterator;
import java.util.List;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.omg.uml.foundation.core.Classifier;
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
 * Produces an XML file that contains object-relational mapping configuration
 * information for use by the OJB tool ( <a href="http://db.apache.org/ojb/"
 * target="_blank">http://db.apache.org/ojb/ </a>). In particular, it produces
 * class-descriptor elements from a set classes defined in a UML 1.3 model.
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
 *
 *
 *    &lt;!ELEMENT transformer (param, filter)&gt;
 *    &lt;!ATTLIST transformer
 *       name CDATA #REQUIRED
 *       className CDATA #FIXED gov.nih.nci.codegen.core.transformer.OJBRepTransformer&gt;
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
public class UML13DAOTransformer implements Transformer, XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13DAOTransformer.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName;

    private String _database;

    private String _external_server;


    /**
     *
     */
    public UML13DAOTransformer() {
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
        Document doc = generateConfig(classifiers);
        XMLOutputter p = new XMLOutputter();
        p.setFormat(Format.getPrettyFormat());

        newArtifacts.add(new BaseArtifact("DAOConfiguration", modelElement, p
                .outputString(doc)));
        return newArtifacts;
    }

    /**
     * @param classifiers
     * @return
     */
    private Document generateConfig(Collection classifiers) {
        Element configEl = new Element("DAOConfiguration");

        Element sessEl = new Element("domainObjects");

        Element dataSource = new Element("DataSource");
        Text dataSourceText = new Text("ORM1");

        Element tool = new Element("tool");
        Text toolText = new Text("hibernate");

        Element database = new Element("database");
        Text databaseText = new Text(_database);


        dataSource.addContent(dataSourceText);
        tool.addContent(toolText);
        database.addContent(databaseText);

        sessEl.addContent(dataSource);
        sessEl.addContent(tool);
        sessEl.addContent(database);



        String classList = "";
        for (Iterator i = classifiers.iterator(); i.hasNext();) {
            Classifier klass = (Classifier) i.next();
            UmlPackage pkg = null;
            if (_pkgName != null) {
                pkg = UML13Utils.getPackage(UML13Utils.getModel(klass),
                        _pkgName);
            } else {
                pkg = UML13Utils.getModel(klass);
            }

            String nn = UML13Utils.getNamespaceName(pkg, klass);
            String implNN = nn + ".impl";
            if (classList.equals("")) {
            	classList = nn + Constant.DOT + klass.getName();
            	classList = classList + Constant.COMMA + implNN + Constant.DOT + klass.getName()+"Impl";
            }
            else {
            	classList = classList + ", " + nn + Constant.DOT + klass.getName();
            	classList = classList + Constant.COMMA + implNN + Constant.DOT + klass.getName()+"Impl";
            }
        }
        if (classList.equals("")) { classList="UNKOWEN";}
        sessEl.setAttribute("name",classList);
        configEl.addContent(sessEl);

        Element external_El = new Element("domainObjects");
        external_El.setAttribute("name","gov.nih.nci.XYZ");

        Element external_DataSource = new Element("DataSource");
        Text external_DataSourceText = new Text("EXTERNALSERVER");

        Element external_Server = new Element("server");
        Text external_ServerText = new Text(_external_server);

        external_DataSource.addContent(external_DataSourceText);
        external_Server.addContent(external_ServerText);

        external_El.addContent(external_DataSource);
        external_El.addContent(external_Server);



        /****/
        configEl.addContent(external_El);

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
        _database = getParameter(config, "database");
        _external_server = getParameter(config, "externalServer");
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
