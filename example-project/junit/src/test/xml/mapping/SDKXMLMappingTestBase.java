package test.xml.mapping;

import java.util.List;

import junit.framework.TestCase;

import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Dan Dumitru
 */
public abstract class SDKXMLMappingTestBase extends TestCase {

	private String uriPrefix = "gme://caCORE.caCORE/3.2/";
	
	protected boolean useGMETags;

	protected void setUp() throws Exception {
		super.setUp();
		
		useGMETags=Boolean.parseBoolean(System.getProperty("useGMETags"));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected static String getTestCaseName() {
		return "SDK Base XML Mapping Test Case";
	}

	public abstract Document getDoc();

	protected Element getRootElement(String filename) {
		Element rootElement;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(filename);
			rootElement = doc.getRootElement();

		} catch (Exception ex) {
			throw new RuntimeException("Error reading XML Mapping file: " + filename,
					ex);
		}

		return rootElement;
	}

	protected Document getDocument(String filename) {
		Document doc;
		try {
			SAXBuilder builder = new SAXBuilder();
			ClassPathResource classPathResource=new ClassPathResource(filename);
			doc = builder.build(classPathResource.getInputStream());

		} catch (Exception ex) {
			throw new RuntimeException("Error reading XML Mapping file: " + filename,
					ex);
		}

		return doc;
	}

	protected List<Element> queryXMLMapping(Object obj, String xpath)
	throws JaxenException {

		// e.g.: xpath = "/*[local-name()='schema']";
		JDOMXPath path = new JDOMXPath(xpath);
		List<Element> elts = path.selectNodes(obj);

		// System.out.println("Elements Found: " + elts.size());

		return elts;

	}

	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * (schema, element, complexType) are present Verifies that the number of
	 * 'element' and 'complexType' elements match
	 * 
	 * @throws Exception
	 */
	protected void validateCommonSchemaElements() throws Exception {

		Document doc = getDoc();

		assertNotNull(doc);
		assertNotNull(doc.getRootElement());
		
		String xpath = "/?xml";
		List<Element> xmlElts = queryXMLMapping(doc, xpath);
		assertEquals(1, xmlElts.size());

		xpath = "/mapping";
		List<Element> mappingElts = queryXMLMapping(doc, xpath);
		assertEquals(1, mappingElts.size());

		xpath = "/mapping/class";
		List<Element> classElts = queryXMLMapping(doc, xpath);
		assertTrue(classElts.size() > 0);

	}

	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateClassElements(Class klass, String identity)
	throws Exception {
		validateClassElements(klass,klass.getSimpleName(),identity, true);
	}
	
	protected void validateClassElements(Class klass, String klassAlias, String identity)
	throws Exception {
		validateClassElements(klass,klassAlias,identity, true);
	}
	
	protected void validateClassElements(Class klass,String identity, boolean validateIdentity)
	throws Exception {
		validateClassElements(klass,klass.getSimpleName(),identity, validateIdentity);
	}

	protected void validateClassElements(Class klass, String klassAlias, String identity, boolean validateIdentity)
	throws Exception {

		Document doc = getDoc();

		String xpath = "/mapping/class[@name='" + klass.getName() + "']";

		List<Element> elts = queryXMLMapping(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);

//		Iterator iter = klassElt.getAttributes().iterator();
//		Attribute attribute;
//		while (iter.hasNext()) {
//			attribute = (Attribute)iter.next();
//			System.out.println(attribute.getName() + ": " + attribute.getValue());
//		}
//		
//		assertEquals(5, klassElt.getAttributes().size());//name, identity, access, auto-complete, verify-constructable

		assertTrue(klassElt.getAttributeValue("name").equals(klass.getName()));
		if (validateIdentity)
			assertTrue(klassElt.getAttributeValue("identity").equals(identity));

		xpath = "/mapping/class[@name='" + klass.getName() + "']"
			+ "/map-to[@xml='" + klassAlias + "']";		

		elts = queryXMLMapping(doc, xpath);
		assertEquals(1, elts.size());
		Element mapToElt = elts.get(0);
		
//		Iterator iter = mapToElt.getAttributes().iterator();
//		Attribute attribute;
//		while (iter.hasNext()) {
//			attribute = (Attribute)iter.next();
//			System.out.println(attribute.getName() + ": " + attribute.getValue());
//		}	
		
		assertEquals(3, mapToElt.getAttributes().size());//xml,ns-uri,element-definition
		assertTrue(mapToElt.getAttributeValue("xml").equals(klassAlias));
		
		if (!useGMETags)
			assertTrue(mapToElt.getAttributeValue("ns-uri").equals(uriPrefix + klass.getPackage().getName()));
	}

	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateSubclassElements(Class klass,String identity)
	throws Exception {
		validateSubclassElements(klass,klass.getSimpleName(),identity);
	}
	
	protected void validateSubclassElements(Class klass,String klassAlias,String identity)
	throws Exception {

		Document doc = getDoc();

		validateClassElements(klass,klassAlias,identity);

		String superklassName = klass.getSuperclass().getName();
//		System.out.println("Superclass for class " + klass.getSimpleName()
//				+ ": " + superklassName);
		
		String xpath = "/mapping/class[@name='" + klass.getName() + "']";		

		List<Element> elts = queryXMLMapping(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);
		assertTrue(klassElt.getAttributeValue("extends").equals(superklassName));

	}

	/**
	 * Uses xpath to query the generated XSD
	 * Verifies that Subclass Association elements
	 * (extension, sequence) and attributes (base, ref) are present
	 * 
	 * @throws Exception
	 */
	public void validateClassAssociationElements(Class klass, Class associatedKlass, String rolename, boolean isCollection)
	throws Exception {
		validateClassAssociationElements(klass,klass.getSimpleName(), associatedKlass,associatedKlass.getSimpleName(), rolename,rolename, isCollection);
	}
	
	public void validateClassAssociationElements(Class klass,String klassAlias, Class associatedKlass,String associatedKlassName, String rolename,String rolenameAlias, boolean isCollection)
	throws Exception {

		Document doc = getDoc();
		
		String xpath = "/mapping/class[@name='" + klass.getName() + "']"
		+ "/field[@name='" + rolename + "']";

		List<Element> attributeElts = queryXMLMapping(doc, xpath);
		assertNotNull(attributeElts);
		assertEquals(1, attributeElts.size());
		
		Element fieldElt = attributeElts.get(0);
		
		if (associatedKlass.getName().equals("java.lang.String")){
			assertEquals(fieldElt.getAttributeValue("type"),"string");
		} else {
			assertEquals(fieldElt.getAttributeValue("type"),associatedKlass.getName());
		}
		
		if (isCollection){
			assertEquals(fieldElt.getAttributeValue("handler"),"gov.nih.nci.system.client.util.xml.CastorCollectionFieldHandler");
			assertEquals(fieldElt.getAttributeValue("collection"),"collection");
		} else {
			assertEquals(fieldElt.getAttributeValue("handler"),"gov.nih.nci.system.client.util.xml.CastorDomainObjectFieldHandler");
		}
		
		Element bindElt = fieldElt.getChild("bind-xml");

		if (associatedKlass.getName().equals("java.lang.String")){
			assertEquals(bindElt.getAttributeValue("name"),"string");
			assertEquals(bindElt.getAttributeValue("type"),"string");
		} else {
			assertEquals(bindElt.getAttributeValue("name"),associatedKlassName);
			assertEquals(bindElt.getAttributeValue("type"),associatedKlass.getName());
		}
		
		assertEquals(bindElt.getAttributeValue("location"),rolenameAlias);
		assertEquals(bindElt.getAttributeValue("node"),"element");

	}	
	
//	/**
//	 * Uses xpath to query the generated XSD
//	 * Verifies that Subclass Association elements
//	 * (extension, sequence) and attributes (base, ref) are present
//	 * 
//	 * @throws Exception
//	 */
//	public void validateSubclassAssociationElements(Class klass, Class associatedKlass, String rolename, String minOccurs, String maxOccurs)
//	throws Exception {
//
//		Document doc = getDoc();
//
//		validateClassElements(klass);
//
//		String superklassName = klass.getSuperclass().getSimpleName();
////		System.out.println("Superclass for class " + klass.getSimpleName()
////				+ ": " + superklassName);
//		
//		String xpath = "/xs:schema/xs:complexType[@name='" + klass.getSimpleName() + "']" 
//			+ "/xs:complexContent/xs:extension[@base='" + superklassName + "']"
//			+ "/xs:sequence/xs:element[@name='" + rolename + "']"
//			+ "/xs:complexType/xs:sequence/xs:element[@ref='" + associatedKlass.getSimpleName() + "']";
//
////		System.out.println("xpath: " + xpath);
//		
//		List<Element> elts = queryXMLMapping(doc, xpath);
//		assertEquals(1, elts.size());
//		
//		Element klassElt = elts.get(0);
//		assertEquals(3, klassElt.getAttributes().size()); // ref, minOccurs, maxOccurs
//		assertEquals(klassElt.getAttributeValue("ref"),associatedKlass.getSimpleName());
//		
//		// TODO :: change eventually to honor minOccurs value passed in
//		// ignore 'minOccurs' value for now, as the Code Generator always sets it to zero
//		assertEquals(klassElt.getAttributeValue("minOccurs"),"0");
//		assertEquals(klassElt.getAttributeValue("maxOccurs"),maxOccurs);
//
//	}		

	protected void validateFieldElement(Class klass,
			String attributeName, String attributeType) throws Exception {
		validateFieldElement(klass,attributeName,attributeName, attributeType);
	}
	protected void validateFieldElement(Class klass,
			String attributeName, String bindAttributeName, String attributeType) throws Exception {

		Document doc = getDoc();

		
		String xpath = "/mapping/class[@name='" + klass.getName() + "']"
				+ "/field[@name='" + attributeName + "']";

		List<Element> attributeElts = queryXMLMapping(doc, xpath);
		assertNotNull(attributeElts);
		assertEquals(1, attributeElts.size());

		Element fieldElt = attributeElts.get(0);
		assertEquals(fieldElt.getAttributeValue("type").toLowerCase(),attributeType.toLowerCase());
		
		Element bindElt = fieldElt.getChild("bind-xml");
		assertEquals(bindElt.getAttributeValue("name"),bindAttributeName);		
		assertEquals(bindElt.getAttributeValue("node"),"attribute");
		
	}
}
