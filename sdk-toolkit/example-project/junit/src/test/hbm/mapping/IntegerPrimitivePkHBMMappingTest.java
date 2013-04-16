/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.hbm.mapping;


import java.util.List;
import org.jdom.Document;
import org.jdom.Element;



public class IntegerPrimitivePkHBMMappingTest extends SDKHBMMappingTestBase {

	private Document doc = null;

	public static String getTestCaseName() {
		return "Integer Primitive PkValidator HBM Mapping Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		String hbmfileName="/gov/nih/nci/cacoresdk/domain/other/primarykey/IntegerPrimitiveKey.hbm.xml";
		doc=getDocument(hbmfileName);
	}

	@Override
	public Document getDoc() {
		return doc;
	}
	
	public void testAssignedPkGeneratorClassParams() throws Exception {
	        
			List<Element> ids = queryXMLMapping(doc, "/hibernate-mapping/class/id");
			for (Element element : ids) {
				assertNotNull(element.getAttribute("column"));
				assertNotNull(element.getAttribute("name"));
				assertNotNull(element.getAttribute("type"));
	
				List<Element> idgenerators = element.getChildren();
				for (Element generatorElement : idgenerators) {
					assertEquals("assigned", generatorElement.getAttributeValue("class"));
				}
			}
	}
}