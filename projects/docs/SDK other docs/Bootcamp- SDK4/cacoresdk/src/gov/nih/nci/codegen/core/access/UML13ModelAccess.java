
package gov.nih.nci.codegen.core.access;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.ModelAccess;
import gov.nih.nci.codegen.framework.ModelAccessException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.jmi.model.ModelPackage;
import javax.jmi.model.MofPackage;
import javax.jmi.reflect.RefPackage;

import org.apache.log4j.Logger;
import org.netbeans.api.mdr.MDRManager;
import org.netbeans.api.mdr.MDRepository;
import org.netbeans.api.xmi.XMIReader;
import org.netbeans.api.xmi.XMIReaderFactory;
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
 * Uses NetBeans MDR to read an XMI file and provide
 * a reference into an UML 1.3 model instance.
 * @author caBIO Team
 * @version 1.0
 * @see gov.nih.nci.codegen.framework.ModelAccess
 */
public class UML13ModelAccess implements ModelAccess, XMLConfigurable {
	
	private static final Logger log = Logger.getLogger(UML13ModelAccess.class);

	public static final String META_MODEL = "UML";

	public static final String META_MODEL_FILE = "01-12-02_Diff.xml";

	public static final String MODEL = "UMLInstance";

	private MofPackage _metaModel;

	private String _metaModelXmiFileName;

	private XMIReader _reader;

	private MDRepository _repository;

	/**
	 *  Creates an UML13ModelAccess instance
	 */
	public UML13ModelAccess() {
		initProperties();
		initRepository();
		initXmiReader();
		initMetaModel();
	}

	/**
	 * @see gov.nih.nci.codegen.framework.ModelAccess#beginTransaction()
	 */
	public void beginTransaction() throws ModelAccessException {
		try{
			_repository.beginTrans(true);
		}catch(Exception ex){
			log.error("Error beginning transaction -" + ex.getMessage());
			throw new ModelAccessException("Error beginning transaction", ex);
		}
	}

	/**
	 * @see gov.nih.nci.codegen.framework.ModelAccess#commitTransaction()
	 */
	public void commitTransaction() throws ModelAccessException {
		
		try{
			_repository.endTrans(false);
		}catch(Exception ex){
			log.error("Error ending transaction - " + ex.getMessage());
			throw new ModelAccessException("Error ending transaction", ex);
		}		
	}

	/**
	 * @see gov.nih.nci.codegen.core.XMLConfigurable#configure(org.w3c.dom.Element)
	 */
	public void configure(org.w3c.dom.Element config) throws ConfigurationException {
		
	}

	/**
	 * 
	 */
	private RefPackage getModelExtent() {
		RefPackage m = null;
		try{
			m = _repository.getExtent(MODEL);
		}catch(Exception ex){
			log.error("Error getting model extent - " + ex.getMessage());
			throw new RuntimeException("Error getting model extent", ex);
		}
		if(m == null){
			try{
				m = _repository.createExtent(MODEL, _metaModel);
			}catch(Exception ex){
				log.error("Error creating extent - " + ex.getMessage());
				throw new RuntimeException("Error creating extent", ex);
			}
		}
		return m;
	}
	
	private MofPackage getMofPackage(ModelPackage model, String pkgName) {
		MofPackage result = null;
		for (Iterator i = model.getMofPackage().refAllOfClass().iterator();
			i.hasNext();
			) {
			MofPackage pkg = (MofPackage) i.next();
			if (pkg.getContainer() == null && pkgName.equals(pkg.getName())) {
				result = pkg;
				break;
			}
		}
		return result;
	}

	/**
	 * @see gov.nih.nci.codegen.framework.ModelAccess#getOutermostExtent()
	 */
	public RefPackage getOutermostExtent() {
		return getModelExtent();
	}

	private String getParameter(org.w3c.dom.Element config, String paramName) {
		String param = null;
		
		List params = XMLUtils.getChildren(config, "param");
		for(Iterator i = params.iterator(); i.hasNext();){
			Element paramEl = (Element)i.next();
			if(paramName.equals(paramEl.getAttribute("name"))){
				param = paramEl.getAttribute("value");
				break;
			}
		}

		return param;
	}

	private void initMetaModel() {
		try {
			ModelPackage mm = (ModelPackage) _repository.getExtent(META_MODEL);
			if (mm == null) {
				
				mm = (ModelPackage) _repository.createExtent(META_MODEL);
			}
			_metaModel = getMofPackage(mm, META_MODEL);
			if (_metaModel == null) {
				
				URL url = Thread.currentThread().getContextClassLoader().getResource(_metaModelXmiFileName);
				if(url == null){
					log.error("Couldn't get resource " + _metaModelXmiFileName);
					throw new RuntimeException("Couldn't get resource " + _metaModelXmiFileName);
				}
				_reader.read(url.toString(), mm);
			}
			_metaModel = getMofPackage(mm, META_MODEL);
		} catch (Exception ex) {
			log.error("Error initializing meta model" + ex.getMessage());
			throw new RuntimeException("Error initializing meta model", ex);
		}
	}

	private void initProperties() {
		_metaModelXmiFileName = System.getProperty(
				"codegen.modelAccess.metaModelFileName", META_MODEL_FILE);
	}

	private void initRepository() {
		try {

			//Switch class loader
			ClassLoader oldLoader = Thread.currentThread()
					.getContextClassLoader();
			Thread.currentThread().setContextClassLoader(
					getClass().getClassLoader());

			System
					.setProperty(
							"org.netbeans.mdr.storagemodel.StorageFactoryClassName",
							"org.netbeans.mdr.persistence.memoryimpl.StorageFactoryImpl");

			
			System.setProperty("org.netbeans.lib.jmi.Logger.fileName",
					"mdr.log");
			MDRManager mgr = MDRManager.getDefault();

			
			Thread.currentThread().setContextClassLoader(oldLoader);

			_repository = mgr.getDefault().getDefaultRepository();
		} catch (Exception ex) {
			log.error("Error setting up MDR repository "+ ex.getMessage());
			throw new RuntimeException("Error setting up MDR repository", ex);
		}
	}

	private void initXmiReader() {
		try {
			_reader = XMIReaderFactory.getDefault().createXMIReader();
		} catch (Exception ex) {
			log.error("Error instantiating XMI reader " + ex.getMessage());
			throw new RuntimeException("Error instantiating XMI reader", ex);
		}
	}

	

	/** 
	 * @see gov.nih.nci.codegen.framework.ModelAccess#readModel(java.io.InputStream)
	 */
	public void readModel(String uriStr, String modelName) throws IOException,
			ModelAccessException {

		URI uri = null;
		try{
			uri = new URI(uriStr);	
		}catch(Exception ex){
			log.error("Couldn't create URI for " + uriStr, ex);
		}

		if(uri == null){
			try{
				uri = (new File(uriStr)).toURI();
			}catch(Exception ex){
				log.error("Couldn't create URI from file for " + uriStr, ex);
			}
		}
		if(uri == null){
			log.error("Couldn't create URI from " + uriStr);
			throw new ModelAccessException("Couldn't create URI from " + uriStr);
		}
		
		RefPackage modelExtent = getModelExtent();
		if(modelExtent == null){
			log.error("Model extent is null");
			throw new RuntimeException("Model extent is null");
		}
		
		try {
			_reader.read(uri.toString(), modelExtent);
		} catch (Exception ex) {
			log.error("Error reading model "+ ex.getMessage());
			throw new ModelAccessException("Error reading model", ex);
		}
	}

	/**
	 * @see gov.nih.nci.codegen.framework.ModelAccess#rollback()
	 */
	public void rollback() throws ModelAccessException {
		try{
			_repository.endTrans(false);
		}catch(Exception ex){
			log.error("Error rolling back transaction - "+ ex.getMessage());
			throw new ModelAccessException("Error rolling back transaction", ex);
		}		
	}
}
