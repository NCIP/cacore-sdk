package test.hbm.mapping;

import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;



public class AllHBMMappingTest extends SDKHBMMappingTestBase {

	private static Logger log = Logger.getLogger(AllHBMMappingTest.class);
	private Document doc = null;
	private String hibernateConfigFile = "hibernate.cfg.xml";

	
	public static String getTestCaseName() {
		return "All Pk Generator HBM Mapping Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	public Document getDoc() {
		return doc;
	}

	public void testValidatePKGeneratorTag() throws Exception {
		doc=getDocument(hibernateConfigFile);
		List<Element> mappingResources = queryXMLMapping(doc,"/hibernate-configuration/session-factory/mapping");
		for (Element element : mappingResources) {
			Attribute resource = element.getAttribute("resource");
			validatePKGeneratorTag(resource.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void validatePKGeneratorTag(String hbmfileName) throws Exception {
			log.debug("validating HBM Mapping file \t "+hbmfileName);
			doc=getDocument(hbmfileName);
			List<Element> ids = queryXMLMapping(doc, "/hibernate-mapping/class/id");
			for (Element element : ids) {
				assertNotNull(element.getAttribute("column"));
				assertNotNull(element.getAttribute("name"));
				assertNotNull(element.getAttribute("type"));

				List<Element> idgenerators = element.getChildren();
				for (Element generatorElement : idgenerators) {
					assertEquals("generator",generatorElement.getName());
				}
			}
	}
}	