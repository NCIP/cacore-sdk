/*
 * Created on Jul 6, 2004
 *
 */
package gov.nih.nci.codegen.core.handler;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.jmi.reflect.RefObject;

import org.omg.uml.foundation.core.ModelElement;
import org.w3c.dom.Element;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;

import gov.nih.nci.codegen.core.util.UML13Utils;
import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.Artifact;
import gov.nih.nci.codegen.framework.ArtifactHandler;
import gov.nih.nci.common.util.Constant;

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
 * Expects to write Java source files. It writes the value of the Artifact's
 * target attribute to a file according to the Java rules for locating class
 * files. It use the qualified name of the model element that is the value of
 * the Artifact's source attribute. So, the UML class com.mycomp.Yadda will be
 * written to com/mycomp/Yadda.java.
 * <p>
 * The content model for this handler is as follows:
 * <p>
 * <code>
 * <pre>
 * &lt;!ELEMENT artifact-handler (param+)&gt;
 * &lt;!ATTLIST artifact-handler
 *    className CDATA #FIXED gov.nih.nci.codegen.core.handler.UML13ModelElement2JavaSourceHandler&gt;
 * &lt;!ELEMENT param EMPTY&gt;
 * &lt;!ATTLIST param
 *    name CDATA #REQUIRED
 *    value CDATA #REQUIRED&gt;
 * </pre>
 * </code> It expects these param elements:
 * <p>
 * <ul>
 * <li>outputDir - the relative name directory into which the artifact will be
 * written</li>
 * <li>prefix (optional) - prepended to the qualified name of the model element
 * </li>
 * <li>suffix (optional) - appended to the qualified name of the model element
 * </li>
 * </ul>
 * <p>
 *
 * @author caBIO Team
 * @version 1.0
 */
public class UML13ModelElement2JavaSourceHandler implements ArtifactHandler,
        XMLConfigurable {

	private static Logger log = Logger.getLogger(UML13ModelElement2JavaSourceHandler.class);

    private String _prefix, _suffix;

    private String _baseDir;

    /**
     *  Creates an UML13ModelElement2JavaSourceHandler instance
     */
    public UML13ModelElement2JavaSourceHandler() {
        super();
    }

    /**
     * @see gov.nih.nci.codegen.framework.ArtifactHandler#execute(gov.nih.nci.codegen.framework.Artifact)
     */
    public void execute(Artifact artifact) {
        RefObject ro = artifact.getSource();
        if (!(ro instanceof ModelElement)) {
        	log.error("Artifact source is not instance of " + "org.omg.uml.foundation.core.ModelElement");
            throw new RuntimeException("Artifact source is not instance of "
                    + "org.omg.uml.foundation.core.ModelElement");
        }
        ModelElement me = (ModelElement) ro;
        try {
            StringBuffer nameBuf = new StringBuffer();
            if (_prefix != null && _prefix.trim().length() > 0) {
                nameBuf.append(_prefix.trim());
                nameBuf.append(Constant.DOT);
            }
            nameBuf.append(UML13Utils.getQualifiedName(me));
            if (_suffix != null && _suffix.trim().length() > 0) {
                nameBuf.append('.');
                nameBuf.append(_suffix.trim());
            }
            File f = new File(_baseDir + '/'
                    + nameBuf.toString().replace('.', '/') + ".java");

            File p = f.getParentFile();
            if (!p.exists()) {
                p.mkdirs();
            }
            f.createNewFile();
            FileWriter w = new FileWriter(f);
            w.write(artifact.getTarget().toString());
            w.flush();
            w.close();
        } catch (Exception ex) {
        	log.error("error handling artifact" + ex.getMessage());
            throw new RuntimeException("error handling artifact", ex);
        }
    }

    /**
     * @see gov.nih.nci.codegen.core.JDOMConfigurable#configure(org.jdom.Element)
     */
    public void configure(Element config) throws ConfigurationException {
        _baseDir = getParameter(config, "outputDir");
        if (_baseDir == null) {
            throw new ConfigurationException("outputDir is null");
        }
        _prefix = getParameter(config, "prefix");
        _suffix = getParameter(config, "suffix");
    }

    private String getParameter(org.w3c.dom.Element config, String paramName) {
        String param = null;

        List params = XMLUtils.getChildren(config, "param");
        for (Iterator i = params.iterator(); i.hasNext();) {
            Element paramEl = (Element) i.next();
            if (paramName.equals(paramEl.getAttribute("name"))) {
                param = paramEl.getAttribute("value");
                break;
            }
        }

        return param;
    }

}
