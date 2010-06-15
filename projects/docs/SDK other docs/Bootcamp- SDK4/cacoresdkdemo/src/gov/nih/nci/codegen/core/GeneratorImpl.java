
package gov.nih.nci.codegen.core;

import gov.nih.nci.codegen.framework.ArtifactHandler;
import gov.nih.nci.codegen.framework.Filter;
import gov.nih.nci.codegen.framework.ModelAccess;
import gov.nih.nci.codegen.framework.Transformer;

import java.io.FileInputStream;

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
 * @author caBIO Team
 * @version 1.0
 */
public class GeneratorImpl extends Generator {
	
	private static Logger log = Logger.getLogger(GeneratorImpl.class);

	/** 
	 * @see gov.nih.nci.codegen.core.Generator#newModelAccess(org.jdom.Element)
	 */
	public ModelAccess newModelAccess(Element modelAccessEl) {
		return (ModelAccess) newXMLConfigurable(modelAccessEl);
	}

	/** 
	 * @see gov.nih.nci.codegen.core.Generator#newFilter(org.jdom.Element)
	 */
	public Filter newFilter(Element filterEl) {
		return (Filter) newXMLConfigurable(filterEl);
	}

	/** 
	 * @see gov.nih.nci.codegen.core.Generator#newTransformer(org.jdom.Element)
	 */
	public Transformer newTransformer(Element transformerEl) {
		return (Transformer) newXMLConfigurable(transformerEl);
	}

	/** 
	 * @see gov.nih.nci.codegen.core.Generator#newArtifactHandler(org.jdom.Element)
	 */
	public ArtifactHandler newArtifactHandler(Element artifactHandlerEl) {
		return (ArtifactHandler) newXMLConfigurable(artifactHandlerEl);
	}

	private Object newXMLConfigurable(Element config) {
		if (config == null) {
			log.error("Element is null");
			throw new RuntimeException("Element is null");
		}
		String className = config.getAttribute("className");
		if (className == null || className.trim().length() == 0) {
			log.error("No className value specified.");
			throw new RuntimeException("No className value specified.");
		}
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
			if (obj instanceof XMLConfigurable) {
				((XMLConfigurable)obj).configure(config);
			}
		} catch (Exception ex) {
			log.error("Error initializing " + className + ": " + ex.getMessage());
			throw new RuntimeException("Error initializing " + className, ex);
		}
		return obj;
	}

	public static void main(String[] args) throws Exception {
		GeneratorImpl g = new GeneratorImpl();
		g.configure(new FileInputStream(args[0]));
		g.execute();
	}
}
