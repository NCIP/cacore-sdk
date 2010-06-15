
package gov.nih.nci.codegen.core.handler;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;

import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.Artifact;
import gov.nih.nci.codegen.framework.ArtifactHandler;

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
 * Writes the value of a given {@link gov.nih.nci.codegen.framework.Artifact} to
 * a file on the local file system.
 * <p>
 * The content model for the configuration element is as follows:
 * <p>
 * <code>
 * <pre>
 * &lt;!ELEMENT artifact-handler (param+)&gt;
 * &lt;!ATTLIST artifact-handler
 *    className CDATA #FIXED gov.nih.nci.codegen.core.handler.SimpleFileHandler&gt;
 * &lt;!ELEMENT param EMPTY&gt;
 * &lt;!ATTLIST param
 *    name CDATA #REQUIRED
 *    value CDATA #REQUIRED&gt;
 * </pre>
 * </code>
 * <p>
 * It expects the following four param elements:
 * <p>
 * <ul>
 * <li>outputDir - the relative name directory into which the artifact will be
 * written</li>
 * <li>fileName (optional) - the name of the file to be written; if not
 * supplied, then the value of the Artifact's name attribute will be used</li>
 * <li>prefix (optional) - prepended to the name of the file to be written
 * </li>
 * <li>suffix (optional) - appended to the name of the file to be written</li>
 * </ul>
 * <p>
 *
 * @author caBIO Team
 * @version 1.0
 */
public class SimpleFileHandler implements ArtifactHandler, XMLConfigurable {
	
	private static Logger log = Logger.getLogger(SimpleFileHandler.class);

    private String _fileName, _prefix, _suffix;

    private String _baseDir;

    /**
     *
     */
    public SimpleFileHandler() {
        super();
    }

    /**
     * @see gov.nih.nci.codegen.framework.ArtifactHandler#execute(gov.nih.nci.codegen.framework.Artifact)
     */
    public void execute(Artifact artifact) {

        try {

            String target = (String) artifact.getTarget();
            File f = null;
            if (_fileName != null) {
                f = new File(_baseDir + '/' + _fileName);
            } else {
                String prefix = (_prefix == null || "".equals(_prefix.trim()) ? ""
                        : _prefix);
                String suffix = (_suffix == null || "".equals(_suffix.trim()) ? ""
                        : _suffix);
                f = new File(_baseDir + '/' + prefix + artifact.getName()
                        + suffix);
            }
            File p = new File(f.getParent());
            if (!p.exists()) {
                p.mkdirs();
            }
            f.createNewFile();
            FileWriter w = new FileWriter(f);
            w.write(target);
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
        _fileName = getParameter(config, "fileName");
        _prefix = getParameter(config, "prefix");
        _suffix = getParameter(config, "suffix");
        if (_fileName == null && !(_prefix != null || _suffix != null)) {
        	log.error("If fileName is not specified, then either prefix or suffix must be specified.");
            throw new ConfigurationException(
                    "If fileName is not specified, then either prefix or suffix must be specified.");
        }
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
