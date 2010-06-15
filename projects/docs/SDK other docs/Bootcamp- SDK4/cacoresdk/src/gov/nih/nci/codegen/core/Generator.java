
package gov.nih.nci.codegen.core;

import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.Artifact;
import gov.nih.nci.codegen.framework.ArtifactHandler;
import gov.nih.nci.codegen.framework.Filter;
import gov.nih.nci.codegen.framework.ModelAccess;
import gov.nih.nci.codegen.framework.Transformer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.omg.uml.foundation.core.Classifier;
import org.w3c.dom.Element;

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
public abstract class Generator {

	public static final String MODEL_ACCESS_EL = "model-access";

	public static final String FILTER_CHAIN_EL = "filter-chain";

	public static final String FILTER_EL = "filter";

	public static final String TRANSFORMER_CHAIN_EL = "transformer-chain";

	public static final String TRANSFORMER_EL = "transformer";

	public static final String ARTIFACT_HANDLER_EL = "artifact-handler";

	private static Logger _logger = Logger.getLogger(Generator.class);

	private ModelAccess _modelAccess;
	private String _xmiURI;
	private String _modelName;

	private Map _filters;

	private List _filterNamesList;

	private Map _transformers;

	private List _transformerNamesList;

	private ArtifactHandler _artifactHandler;

	public void configure(InputStream in) throws IOException,
			ConfigurationException {

	
		Element root = null;
		try {

			root = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(in).getDocumentElement();
		} catch (Exception ex) {
			_logger.error("Error configuring generator" + ex.getMessage());
			throw new ConfigurationException("Error configuring generator", ex);
		}


		Element modelAccessEl = getChild(root, MODEL_ACCESS_EL);
		
		_xmiURI = modelAccessEl.getAttribute("xmiURI");
		_modelName = modelAccessEl.getAttribute("modelName");
		_modelAccess = newModelAccess(modelAccessEl);

		_filterNamesList = new ArrayList();
		_filters = new HashMap();
		Element filterChainEl = getChild(root, FILTER_CHAIN_EL);
		for (Iterator i = getChildren(filterChainEl, FILTER_EL).iterator(); i
				.hasNext();) {
			Element filterEl = (Element) i.next();
			String filtName = filterEl.getAttribute("name");
			_filterNamesList.add(filtName);
			Filter filter = newFilter(filterEl);
			_filters.put(filtName, filter);
		}

		_transformerNamesList = new ArrayList();
		_transformers = new HashMap();
		Element transChainEl = getChild(root, TRANSFORMER_CHAIN_EL);
		for (Iterator i = getChildren(transChainEl, TRANSFORMER_EL).iterator(); i
				.hasNext();) {
			Element transformerEl = (Element) i.next();
			String transName = transformerEl.getAttribute("name");
			_transformerNamesList.add(transName);
			Transformer transformer = newTransformer(transformerEl);
			_transformers.put(transName, transformer);
		}
		
		Element handlerEl = getChild(root, ARTIFACT_HANDLER_EL);
		_artifactHandler = newArtifactHandler(handlerEl);
	}

	/**
	 * @param filterChainEl
	 * @param filter_el2
	 * @return
	 */
	private List getChildren(Element parentEl, String name) {
		return XMLUtils.getChildren(parentEl, name);
	}

	/**
	 * @param root
	 * @param model_access_el2
	 * @return
	 */
	private Element getChild(Element parentEl, String name) {
		return XMLUtils.getChild(parentEl, name);
	}

	public void execute() throws GenerationException {
		try {


			_modelAccess.beginTransaction();
			_modelAccess.readModel(_xmiURI, _modelName);
			Collection modelElements = new ArrayList();
			RefPackage extent = _modelAccess.getOutermostExtent();
			if(extent == null){
				_logger.error("Outermost extent is null");
				throw new GenerationException("Outermost extent is null");
			}
			modelElements.add(extent);
			for (Iterator i = _filterNamesList.iterator(); i.hasNext();) {
				String filterName = (String) i.next();
				Filter f = (Filter) _filters.get(filterName);
				_logger.info("Executing filter " + filterName);
				modelElements = f.execute(modelElements);
				_logger.info(modelElements.size() + " elements selected by "
						+ filterName);
			}


			Collection artifacts = new ArrayList();
			for (Iterator i = modelElements.iterator(); i.hasNext();) {
				RefObject modelElement = (RefObject) i.next();
				for (Iterator j = _transformerNamesList.iterator(); j.hasNext();) {
					String transformerName = (String) j.next();
					Transformer t = (Transformer) _transformers
							.get(transformerName);
					String classifierName = modelElement instanceof Classifier? " for " + ((Classifier)modelElement).getName():"";
					_logger.info("Executing transformer " + transformerName + classifierName);
					
					artifacts = t.execute(modelElement, artifacts);
				}
				_logger.info(artifacts.size() + " artifacts produced.");


				for (Iterator j = artifacts.iterator(); j.hasNext();) {
					_artifactHandler.execute((Artifact) j.next());
				}
			}
			_modelAccess.commitTransaction();
		} catch (Exception ex) {
			_logger.error("Error generating - ", ex);
			throw new GenerationException("Error generating", ex);
		}
	}

	public abstract ModelAccess newModelAccess(Element modelAccessEl);

	public abstract Filter newFilter(Element filterEl);

	public abstract Transformer newTransformer(Element transformerEl);

	public abstract ArtifactHandler newArtifactHandler(Element artifactHandlerEl);

}
