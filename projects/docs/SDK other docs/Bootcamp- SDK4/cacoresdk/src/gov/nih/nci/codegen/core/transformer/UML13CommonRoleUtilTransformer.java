
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
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Classifier;
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
public class UML13CommonRoleUtilTransformer implements Transformer , XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13CommonRoleUtilTransformer.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName, _omPkg;


    /**
     *
     */
    public UML13CommonRoleUtilTransformer() {
        super();
    }

    /**
     * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
     *      java.util.Collection)
     */
    public Collection execute(RefObject modelElement, Collection artifacts)
            throws TransformationException {
        if (modelElement == null) {
            throw new TransformationException("model element is null");
        }
        if (!(modelElement instanceof Model)) {
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

        String methodList = generateConfig(classifiers);


        newArtifacts.add(new BaseArtifact("CommonRoleUtility", modelElement, methodList ));

        return newArtifacts;

    }

    /**
	 * @param classifiers
	 * @return
     */

    private String generateConfig(Collection classifiers) {

		String methodList = "";
		String method = "";

		for (Iterator i = classifiers.iterator(); i.hasNext();) {
			Classifier klass = (Classifier) i.next();

			//System.out.println("classifier class name: " + klass.getName());


			for (Iterator j = UML13Utils.getAssociationEnds(klass).iterator(); j.hasNext();) {
				 AssociationEnd thisEnd = (AssociationEnd) j.next();
				 AssociationEnd otherEnd = UML13Utils.getOtherAssociationEnd(thisEnd);
				 UmlPackage pkg = null;
				 if (_pkgName != null) {
					 pkg = UML13Utils.getPackage(UML13Utils.getModel(klass),
							 _pkgName);
				 } else {
					 pkg = UML13Utils.getModel(klass);
				 }
				 String nn = UML13Utils.getNamespaceName(pkg, klass);

				if (_pkgName != null) {
					 pkg = UML13Utils.getPackage(UML13Utils.getModel((UmlClass)otherEnd.getType()),
							 _pkgName);
				 } else {
					 pkg = UML13Utils.getModel((UmlClass)otherEnd.getType());
				 }


				String temp1 = UML13Utils.getNamespaceName(pkg, (UmlClass)otherEnd.getType());
                method = "";
				// the other end is collection, form a string
				if( (UML13Utils.isOne2Many(thisEnd, otherEnd) ||  UML13Utils.isMany2Many(thisEnd, otherEnd) ) && otherEnd.isNavigable())
				{
					method = method + otherEnd.getName();
					method = method + nn;
					method = method +Constant.DOT;
//					method = method + ".impl.";
					method = method + thisEnd.getType().getName();
//					method = method + "Impl";
					method = method + Constant.EQUAL;
					method = method + temp1;
//					method = method + ".impl.";
					method = method + Constant.DOT;
					method = method + otherEnd.getType().getName();
//					method = method + "Impl";
					method = method + "\n";
					
					// 3.0.1 release
					method = method + otherEnd.getName();
					method = method + nn;
					method = method + ".impl.";
					method = method + thisEnd.getType().getName();
					method = method + "Impl";
					method = method + Constant.EQUAL;
					method = method + temp1;
					method = method + ".impl.";
					method = method + otherEnd.getType().getName();
					method = method + "Impl";
					method = method + "\n\n";
				
				}

				if(method != null && methodList != null)
				{
					methodList = methodList  + method;
				}
				else if(method != null && methodList == null)
				{
					methodList = method;
				}

			}

		}

		String setUp = "";
	    setUp = setUp + methodList;
		return setUp;
    }



    /**
     * @see gov.nih.nci.codegen.core.JDOMConfigurable#configure(org.jdom.Element)
     */
    public void configure(org.w3c.dom.Element config)
            throws ConfigurationException {

        org.w3c.dom.Element filterEl = XMLUtils.getChild(config, "filter");
        if (filterEl == null) {
            throw new ConfigurationException("no child filter element found");
        }

        String className = filterEl.getAttribute("className");
        if (className == null) {
            throw new ConfigurationException("no filter class name specified");
        }
        _pkgName = getParameter(config, "basePackage");
        log.debug("basePackage: " + _pkgName);

        try {
            _classifierFilt = (UML13ClassifierFilter) Class.forName(className)
                    .newInstance();
        } catch (Exception ex) {
        	log.error("Couldn't instantiate : " + ex.getMessage());
            throw new ConfigurationException("Couldn't instantiate "
                    + className);
        }

        _classifierFilt.configure(filterEl);
    }

    public String capFirst(String s){
			return s.substring(0, 1).toUpperCase() + s.substring(1);
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

	private String getPackage(UmlClass klass) {
		UmlPackage pkg = null;
		if (_omPkg != null) {
			pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _omPkg);
		} else {
			pkg = UML13Utils.getModel(klass);
		}
		return UML13Utils.getNamespaceName(pkg, klass);

    }
}
