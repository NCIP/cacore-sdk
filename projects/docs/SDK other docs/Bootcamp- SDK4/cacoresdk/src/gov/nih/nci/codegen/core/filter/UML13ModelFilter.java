
package gov.nih.nci.codegen.core.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.omg.uml.modelmanagement.Model;
import org.w3c.dom.Element;

import uml.UmlPackage;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.util.XMLUtils;

import gov.nih.nci.codegen.framework.Filter;
import gov.nih.nci.codegen.framework.FilteringException;
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
 * Locates a named Model within a UmlPackage.
 * @author caBIO Team
 * @version 1.0
 * @see gov.nih.nci.codegen.framework.Filter
 */

 

public class UML13ModelFilter implements Filter, XMLConfigurable {

    private static Logger log = Logger.getLogger(UML13ModelFilter.class);

    /** The name of the Model to be selected by this Filter. */
    private String _modelName;

    /**
     *  Creates an UML13ModelFilter instance
     */
    public UML13ModelFilter() {
        super();
    }

    /**
     * Navigates the given uml.UmlPackage object to find the
     * org.omg.uml.modelmanagement.Model with name stored in {@link #_modelName},
     * which is set during the call to {@link #configure(Element)}.
     * 
     * @param modelElements
     *            a Collection of size one where the sole item is an object of
     *            type uml.UmlPackage.
     * @return a Collection containing an object of type
     *         org.omg.uml.modelmanagement.Model, if found.
     * @throws FilteringException
     *             if there is an error during filtering or if the UmlPackage
     *             contains more than one Model.
     * @see gov.nih.nci.codegen.framework.Filter#execute(java.util.Collection)
     */
    public Collection execute(Collection modelElements)
            throws FilteringException {

        Model model = null;
        if (modelElements.size() != 1) {
        	log.error("modelElements size > 1");
            throw new FilteringException("modelElements size > 1");
        }
        Object obj = modelElements.iterator().next();
        if (!(obj instanceof UmlPackage)) {
        	log.error("modelElement not instance of UmlPackage");
            throw new FilteringException(
                    "modelElement not instance of UmlPackage");
        }
        log.debug("Looking for model: " + _modelName);
        UmlPackage umlExtent = (UmlPackage) obj;
        for (Iterator i = umlExtent.getModelManagement().getModel()
                .refAllOfClass().iterator(); i.hasNext();) {
            Model m = (Model) i.next();
            log.debug("Comparing with: " + m.getName());
            if (_modelName.equals(m.getName())) {
                model = m;
                break;
            }
        }

        ArrayList mes = new ArrayList();
        if (model != null) {
            mes.add(model);
        }
        return mes;
    }

    /**
     * Sets {@link #_modelName}to the name of the Model that will be selected
     * by this Filter. <P/>The content model of the filter Element is as
     * follows: <P/><code>
     * <pre>
     *   &lt;!ELEMENT filter (param)&gt;
     *   &lt;!ATTLIST filter
     *      name CDATA #REQUIRED&gt;
     *   &lt;!ELEMENT param EMPTY&gt;
     *   &lt;!ATTLIST param
     *      name CDATA #REQUIRED
     *      value CDATA #REQUIRED&gt;
     * </pre>
     * </code>
     * 
     * @param config
     *            the filter Element
     */
    public void configure(Element config) throws ConfigurationException {
        Element paramEl = XMLUtils.getChild(config, "param");
        if (paramEl == null) {
        	log.error( "A modelName param Element is required.");
            throw new ConfigurationException(
                    "A modelName param Element is required.");
        }
        if (!"modelName".equals(paramEl.getAttribute("name"))) {
        	log.error("A modelName param Element is required.");
            throw new ConfigurationException(
                    "A modelName param Element is required.");
        }
        _modelName = paramEl.getAttribute("value");
        if (_modelName == null) {
        	log.error("modelName value is null");
            throw new ConfigurationException("modelName value is null");
        }
    }

    public static void main(String[] args) {
    }
}
