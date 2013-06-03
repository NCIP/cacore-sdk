/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.hbm.mapping;

import java.io.IOException;
import java.util.List;

import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import junit.framework.TestCase;

public abstract class SDKHBMMappingTestBase extends TestCase {

	private String uriPrefix = "gme://caCORE.caCORE/3.2/";

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected static String getTestCaseName() {
		return "SDK HBM Mapping Test Base";
	}

	public abstract Document getDoc();
	
	protected Document getDocument(String filename) {
		Document doc;
		try {
			SAXBuilder builder = new SAXBuilder();
			ClassPathResource classPathResource=new ClassPathResource(filename);
			builder.setEntityResolver(new HibernateDTDResolver());
			doc = builder.build(classPathResource.getInputStream());
		} catch (Exception ex) {
			throw new RuntimeException("Error reading XML Mapping file: " + filename,ex);
		}
		return doc;
	}

	protected List<Element> queryXMLMapping(Object obj, String xpath)
			throws JaxenException {

		// e.g.: xpath = "/*[local-name()='schema']";
		JDOMXPath path = new JDOMXPath(xpath);
		List<Element> elts = path.selectNodes(obj);
		return elts;
	}
	
	private class HibernateDTDResolver implements EntityResolver {
		public InputSource resolveEntity(String publicID, String systemID)
				throws SAXException, IOException {
			if (systemID.equals("http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd")) {
				// Return local copy of the hibernate-mapping-3.0.dtd file
				ClassPathResource resource=new ClassPathResource("org/hibernate/hibernate-mapping-3.0.dtd");
				return new InputSource(resource.getInputStream());
			}
			// If no match, returning null makes process continue normally
			return null;
		}
	}
}
