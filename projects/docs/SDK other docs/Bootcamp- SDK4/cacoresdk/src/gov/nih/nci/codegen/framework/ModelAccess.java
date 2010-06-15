
package gov.nih.nci.codegen.framework;

import java.io.IOException;

import javax.jmi.reflect.RefPackage;
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
 * Classes that implement this interface are responsible for
 * providing a reference into the MOF model instance.
 * @author caBIO Team
 * @version 1.0
 */
public interface ModelAccess {

    /**
     * Reads the MOF model named by the given modelName
     * located in the XMI file at the given URI.
     * 
     * @param uri                   the location of the XMI file.
     * @param modelName				the name of the model within the XMI file.
     * @throws IOException          if there is an error opening the XMI file.
     * @throws ModelAccessException if there is an error parsing the XMI file.
     */
	void readModel(String uri, String modelName) throws IOException, ModelAccessException;

	/**
	 * Returns a javax.jmi.reflect.RefPackage that is the entry point into the model.
	 * 
	 * @return the outermost RefPackage object.
	 */
	RefPackage getOutermostExtent();
	
	/**
	 * Should be called before calling readModel.
	 * 
	 * @throws ModelAccessException
	 */
	void beginTransaction() throws ModelAccessException;
	
	/**
	 * Should be called before exiting.
	 * 
	 * @throws ModelAccessException
	 */
	void commitTransaction() throws ModelAccessException;
	
	/**
	 * Should be called after an exception is thrown and before exiting.
	 * 
	 * @throws ModelAccessException
	 */
	void rollback() throws ModelAccessException;
}
