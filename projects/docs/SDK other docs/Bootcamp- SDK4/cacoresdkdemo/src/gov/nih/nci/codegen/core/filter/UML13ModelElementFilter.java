
package gov.nih.nci.codegen.core.filter;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.framework.Filter;
import gov.nih.nci.codegen.framework.FilteringException;

import java.util.Collection;

import uml.UmlPackage;
import org.w3c.dom.Element;
import org.apache.log4j.*;


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
 * Selects all instances of org.omg.uml.foundation.core.ModelElement
 * from within an instance of uml.UmlPackage.
 * @author caBIO Team
 * @version 1.0
 */
 
 
public class UML13ModelElementFilter implements Filter, XMLConfigurable {
	
	private static Logger log = Logger.getLogger(UML13ModelElementFilter.class);

	/**
	 *  
	 */
	public UML13ModelElementFilter() {
		super();
	}

	/**
	 * Selects all instances of org.omg.uml.foundation.core.ModelElement
     * from within an instance of uml.UmlPackage.
	 * 
	 * @param modelElements       the uml.UmlPackage.
	 * @return                    the Collection of ModelElement objects.
	 * @throws FilteringException if the given Collection != 1 or
	 *                            the item in the Collection is no an instance
	 *                            of uml.UmlPackage. 
	 * @see gov.nih.nci.codegen.framework.Filter#execute(java.util.Collection)
	 */
	public Collection execute(Collection modelElements)
			throws FilteringException {
		if (modelElements.size() != 1) {
			log.error("modelElements size != 1");
			throw new FilteringException("modelElements size != 1");
		}
		Object obj = modelElements.iterator().next();
		if (!(obj instanceof UmlPackage)) {
			log.error("modelElement not instance of UmlPackage");
			throw new FilteringException(
					"modelElement not instance of UmlPackage");
		}
		UmlPackage umlExtent = (UmlPackage) obj;
		return umlExtent.getFoundation().getCore().getModelElement()
				.refAllOfType();
	}

	/**
	 * An empty implementation. All content is ignored.
	 * 
	 * @see gov.nih.nci.codegen.core.XMLConfigurable#configure(org.w3c.dom.Element)
	 */
	public void configure(Element config) throws ConfigurationException {
		
	}
}
