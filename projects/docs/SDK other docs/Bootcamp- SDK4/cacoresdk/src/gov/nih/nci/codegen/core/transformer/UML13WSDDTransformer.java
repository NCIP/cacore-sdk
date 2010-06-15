
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
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
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
public class UML13WSDDTransformer implements Transformer , XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13CommonRoleUtilTransformer.class);

    private UML13ClassifierFilter _classifierFilt;

    private String _pkgName, _omPkg, _svcName;
    String cache = "";


    /**
     *
     */
    public UML13WSDDTransformer() {
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
        	log.error("couldn't filter model elements " + ex.getMessage());
            throw new TransformationException("couldn't filter model elements",
                    ex);
        }

        String methodList = generateConfig(classifiers);


        newArtifacts.add(new BaseArtifact("ehcache", modelElement, methodList ));

        return newArtifacts;

    }

    /**
	 * @param classifiers
	 * @return
     */

    private String generateConfig(Collection classifiers) {
        cache = cache + "<deployment xmlns=\"http://xml.apache.org/axis/wsdd/\"";
        cache = cache + "\n";
		cache = cache + "	xmlns:java=\"http://xml.apache.org/axis/wsdd/providers/java\">";
		cache = cache + "\n";
  		cache = cache + "<handler name=\"CSMSOAPHandler\" type=\"java:gov.nih.nci.system.webservice.CSMSOAPHandler\"/>";
		cache = cache + "\n";
  		cache = cache + "<service name=\"" + _svcName + "\" style=\"java:RPC\" use=\"literal\">";
  		cache = cache + "\n";
		cache = cache + "	<requestFlow>";
  		cache = cache + "\n";
		cache = cache + "		<handler name=\"CSMSOAPHandler\" type=\"java:gov.nih.nci.system.webservice.CSMSOAPHandler\"/>";
  		cache = cache + "\n";
		cache = cache + "	</requestFlow>";
  		cache = cache + "\n";
    	cache = cache + "	<parameter name=\"className\" value=\"gov.nih.nci.system.webservice.WSQuery\"/>";
    	cache = cache + "\n";
    	cache = cache + "	<parameter name=\"allowedMethods\" value=\"*\"/>";
    	cache = cache + "\n";
    	cache = cache + "	<parameter name=\"extraClasses\"";
    	cache = cache + "\n";
		cache = cache + "	value=\"";

        String nn1 = new String();
		for (Iterator i = classifiers.iterator(); i.hasNext();) {
			Classifier klass = (Classifier) i.next();
			UmlPackage classPkg = null;
			if (_pkgName != null) {
				classPkg = UML13Utils.getPackage(UML13Utils.getModel(klass),_pkgName);
			} else {
				classPkg = UML13Utils.getModel(klass);
			}
			String name = UML13Utils.getNamespaceName(classPkg, klass);
			
			nn1 = name;
			nn1 = nn1 + ".ws.";
			nn1 = nn1 + klass.getName();
			nn1 = nn1 + Constant.COMMA;
			
			nn1 = nn1 + name;  //UML13Utils.getNamespaceName(classPkg, klass);
			nn1 = nn1 + ".ws.";
			nn1 = nn1 + klass.getName();
			nn1 = nn1 + "Impl";
			if (i.hasNext()) {
				nn1 += Constant.COMMA;
			}

			//fill in fullyqualified object name
			cache = cache + nn1;
			//cache = cache + "\n";

		}
		/*StringBuffer tmpStringBuffer = new StringBuffer(nn1);
		//System.out.println("String before: " + tmpStringBuffer.toString() + "\n");
		int length = tmpStringBuffer.lastIndexOf(",");
		String finalString = tmpStringBuffer.substring(0,length-1);
        //System.out.println("String before: " + finalString + "\n");
		*/

        cache = cache + "\"/>" + "\n";
        for (Iterator i = classifiers.iterator(); i.hasNext();) {
					Classifier klass = (Classifier) i.next();
					UmlPackage classPkg = null;
					if (_pkgName != null) {
						classPkg = UML13Utils.getPackage(UML13Utils.getModel(klass),_pkgName);
					} else {
						classPkg = UML13Utils.getModel(klass);
					}
					String tmp1_1 = "<beanMapping xmlns:myNS=\"urn:ws.";
					tmp1_1 = tmp1_1 + reversePackageName(UML13Utils.getNamespaceName(classPkg, klass)).replaceAll(":impl.",":ws.");
					tmp1_1 = tmp1_1 + "\" ";
					tmp1_1 = tmp1_1 + " qname=\"myNS:";
					tmp1_1 = tmp1_1 + klass.getName();
					tmp1_1 = tmp1_1 + "\" ";
					tmp1_1 = tmp1_1 + "languageSpecificType=\"java:";

					String nn2_1 = UML13Utils.getNamespaceName(classPkg, klass);
					nn2_1 = nn2_1 + ".ws.";
					nn2_1 = nn2_1 + klass.getName();
					tmp1_1 = tmp1_1 + nn2_1;
					tmp1_1 = tmp1_1 + "\" />";
					tmp1_1 = tmp1_1 + "\n";
					cache = cache + tmp1_1;
					
					

					String tmp1 = "<beanMapping xmlns:myNS=\"urn:ws.";
					tmp1 = tmp1 + reversePackageName(UML13Utils.getNamespaceName(classPkg, klass)).replaceAll(":impl.",":ws.");
					tmp1 = tmp1 + "\" ";
					//tmp1= tmp1 + "\n";
					tmp1 = tmp1 + " qname=\"myNS:";
					tmp1 = tmp1 + klass.getName();
					tmp1 = tmp1 + "Impl";
					tmp1 = tmp1 + "\" ";
					//tmp1= tmp1 + "\n";
					tmp1 = tmp1 + "languageSpecificType=\"java:";

					String nn2 = UML13Utils.getNamespaceName(classPkg, klass);
					nn2 = nn2 + ".ws.";
					nn2 = nn2 + klass.getName();
					nn2 = nn2 + "Impl";
		            tmp1 = tmp1 + nn2;
		            tmp1 = tmp1 + "\" />";
		            tmp1= tmp1 + "\n";
					cache = cache + tmp1;

		}

        cache = cache + "</service>";
        cache = cache + "\n";
		cache = cache + "</deployment>";
		cache = cache + "\n";



		String setUp = "";
	    setUp = setUp + cache;;
	    //System.out.println("The wsdd: " + setUp + "\n");
		return setUp;
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

        _svcName = getParameter(config, "webserviceName");

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

    public String capFirst(String s){
			return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public String reversePackageName(String s) {

		StringTokenizer st = new StringTokenizer(s,".");
		Vector myVector = new Vector();
		StringBuffer myStringBuffer = new StringBuffer();
		while (st.hasMoreTokens()) {
			     String t = st.nextToken();
			     myVector.add(t);

	    }

        for (int i = myVector.size(); i>0; i--) {
			  myStringBuffer.append(myVector.elementAt(i-1));
			  myStringBuffer.append(Constant.DOT);

	    }
	    int length1 = myStringBuffer.length();
	    String finalString1 = myStringBuffer.substring(0,length1-1);
        return finalString1;
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
