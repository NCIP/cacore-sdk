package test.xml.mapping;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Dan Dumitru
 */
public abstract class SDKXSDTestBase extends TestCase {
	
	private static Logger log = Logger.getLogger(SDKXSDTestBase.class);

	private String namespace = "http://www.w3.org/2001/XMLSchema";
	private String prefix = "xs";
	
	protected boolean useGMETags;
	protected boolean usePermissibleValues;
	private String namespaceUriPrefix=null;

	protected void setUp() throws Exception {
		super.setUp();
		useGMETags=Boolean.parseBoolean(System.getProperty("useGMETags"));
		usePermissibleValues=Boolean.parseBoolean(System.getProperty("usePermissibleValues"));
		log.debug("useGMETags: " + useGMETags);
		log.debug("usePermissibleValues: " + usePermissibleValues);
		namespaceUriPrefix=System.getProperty("namespaceUriPrefix");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected static String getTestCaseName() {
		return "SDK Base XSD Test Case";
	}

	public abstract Document getDoc();

	protected Element getRootElement(String filename) {
		Element rootElement;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(filename);
			rootElement = doc.getRootElement();

		} catch (Exception ex) {
			throw new RuntimeException("Error reading XSD file: " + filename,
					ex);
		}

		return rootElement;
	}
	
	protected Document getDocument(String filename) {
		if (useGMETags){
			return getDocument(namespaceUriPrefix,filename);
		}
		return getDocument("",filename);
	}

	protected Document getDocument(String namespacePrefix,String filename) {
		Document doc;
		try {
			if (useGMETags){
				filename=namespacePrefix+filename;
				filename=filename.replace("://", "_");
				filename=filename.replace("/", "_");
			}
			log.debug("schema filename: "+filename);
			SAXBuilder builder = new SAXBuilder();
			ClassPathResource  classPathResource=new ClassPathResource(filename);
			doc = builder.build(classPathResource.getInputStream());
		} catch (Exception ex) {
			throw new RuntimeException("Error reading XSD file: " + filename,ex);
		}

		return doc;
	}

	protected List<Element> queryXSD(Object obj, String xpath)
	throws JaxenException {

		// e.g.: xpath = "/*[local-name()='schema']";

		JDOMXPath path = new JDOMXPath(xpath);
		path.addNamespace(prefix, namespace);
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
	public void validateCommonSchemaElements() throws Exception {

		Document doc = getDoc();

		assertNotNull(doc);
		assertNotNull(doc.getRootElement());
		String xpath = "/xs:schema";
		List<Element> schemaElts = queryXSD(doc, xpath);
		assertEquals(1, schemaElts.size());

		xpath = "/xs:schema/xs:element";
		List<Element> elts = queryXSD(doc, xpath);

		xpath = "/xs:schema/xs:complexType";
		List<Element> complexElts = queryXSD(doc, xpath);

		// Verifies that the number 'element' and 'complexType'  
		// Class elements match
		assertEquals(elts.size(), complexElts.size());

	}

	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	public void validateClassElements(Class klass) throws Exception{
		validateClassElements(klass, klass.getSimpleName());
	}
	public void validateClassElements(Class klass, String klassAlias)
	throws Exception {

		Document doc = getDoc();

		String klassName = klassAlias;
		String xpath = "/xs:schema/xs:element[@name='" + klassName + "']";

		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		Element klassElt = elts.get(0);
		
		if (!(null == klassElt.getAttribute("abstract")) && klassElt.getAttributeValue("abstract").equals("true")){
			assertEquals(3, klassElt.getAttributes().size());
		} else{
			assertEquals(2, klassElt.getAttributes().size());
		}
		
		assertTrue(klassElt.getAttributeValue("name").equals(klassName));
		assertTrue(klassElt.getAttributeValue("type").equals(klassName));

		xpath = "/xs:schema/xs:complexType[@name='" + klassName + "']";

		elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		Element complexTypeElt = elts.get(0);
		assertEquals(1, complexTypeElt.getAttributes().size());
		assertTrue(complexTypeElt.getAttributeValue("name").equals(klassName));
	}
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	public void validateClassIsAbstract(Class klass)
	throws Exception {

		Document doc = getDoc();

		String klassName = klass.getSimpleName();
		String xpath = "/xs:schema/xs:element[@name='" + klassName + "']";

		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		Element klassElt = elts.get(0);
		
		assertNotNull(klassElt.getAttributeValue("abstract"));
		assertTrue(klassElt.getAttributeValue("abstract").equals("true"));
	}

	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	public void validateSubclassElements(Class klass)
	throws Exception {
		validateSubclassElements(klass,klass.getSimpleName(),getSuperklassName(klass));
	}
	
	public void validateSubclassElements(Class klass, String klassAlias, String superklassName)
	throws Exception {

		Document doc = getDoc();

		validateClassElements(klass, klassAlias);
			
		String xpath = "/xs:schema/xs:complexType[@name='"
			+ klassAlias
			+ "']/xs:complexContent/xs:extension[@base='" + superklassName
			+ "']";

		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);
		assertEquals(1, klassElt.getAttributes().size());
		assertTrue(klassElt.getAttributeValue("base").equals(superklassName));

	}

	/**
	 * Uses xpath to query the generated XSD
	 * Verifies that Subclass Association elements
	 * (extension, sequence) and attributes (base, ref) are present
	 * 
	 * @throws Exception
	 */
	public void validateClassAssociationElements(Class klass, Class associatedKlass, String rolename, String minOccurs, String maxOccurs, boolean collectionRef)
	throws Exception {
		validateClassAssociationElements(klass,klass.getSimpleName(), associatedKlass,associatedKlass.getSimpleName(), rolename, minOccurs,maxOccurs, collectionRef);
	}

	public void validateClassAssociationElements(Class klass, Class associatedKlass, String rolename, String minOccurs, String maxOccurs)
	throws Exception {
		validateClassAssociationElements(klass,klass.getSimpleName(), associatedKlass,associatedKlass.getSimpleName(), rolename, minOccurs,maxOccurs, true);
	}
	
	public void validateClassAssociationElements(Class klass,String klassAlias,Class associatedKlass,String associatedKlassAlias, String rolename, String minOccurs, String maxOccurs, boolean collectionRef)
	throws Exception {

		Document doc = getDoc();

		validateClassElements(klass,klassAlias);
		String xpath = "";
		if(collectionRef)
		{
			xpath = "/xs:schema/xs:complexType[@name='" + klassAlias + "']" 
			+ "/xs:sequence/xs:element[@name='" + rolename + "']"
			+ "/xs:complexType/xs:sequence/xs:element[@ref='" + associatedKlassAlias + "']";
		}
		else
		{
			xpath = "/xs:schema/xs:complexType[@name='" + klassAlias + "']" 
			+ "/xs:sequence/xs:element[@name='" + rolename + "']";
		}
		log.debug("* * * xpath: " + xpath);
		
		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);
		if(collectionRef)
		{
			assertEquals(3, klassElt.getAttributes().size()); // ref, minOccurs, maxOccurs
			assertEquals(klassElt.getAttributeValue("ref"),associatedKlassAlias);
		}
		else
		{
			assertEquals(4, klassElt.getAttributes().size()); // name, type, minOccurs, maxOccurs
			assertEquals(klassElt.getAttributeValue("name"),rolename);
			assertEquals(klassElt.getAttributeValue("type"),associatedKlassAlias);
		}
		
		// TODO :: change to honor minOccurs value passed in
		// ignore 'minOccurs' value for now, as the Code Generator always sets it to zero
		assertEquals(klassElt.getAttributeValue("minOccurs"),"0");
		assertEquals(klassElt.getAttributeValue("maxOccurs"),maxOccurs);

	}	
	
	/**
	 * Uses xpath to query the generated XSD
	 * Verifies that Subclass Association elements
	 * (extension, sequence) and attributes (base, ref) are present
	 * 
	 * @throws Exception
	 */
	public void validateSubclassAssociationElements(Class klass, Class associatedKlass, String rolename, String minOccurs, String maxOccurs, boolean collectionRef)
	throws Exception {
		validateSubclassAssociationElements(klass,klass.getSimpleName(), associatedKlass,associatedKlass.getSimpleName(),klass.getSuperclass().getSimpleName(),rolename,minOccurs,maxOccurs,collectionRef);
	}

	public void validateSubclassAssociationElements(Class klass, Class associatedKlass, String rolename, String minOccurs, String maxOccurs)
	throws Exception {
		validateSubclassAssociationElements(klass,klass.getSimpleName(), associatedKlass,associatedKlass.getSimpleName(),klass.getSuperclass().getSimpleName(),rolename,minOccurs,maxOccurs, true);
	}
	public void validateSubclassAssociationElements(Class klass,String klassAlias,Class associatedKlass,String associatedKlassAlias,String superklassName,String rolename,String minOccurs,String maxOccurs, boolean collectionRef)
	throws Exception {

		Document doc = getDoc();

		validateClassElements(klass,klassAlias);
		String xpath = "";
		
		if(collectionRef)
		{
			xpath = "/xs:schema/xs:complexType[@name='" + klassAlias + "']" 
				+ "/xs:complexContent/xs:extension[@base='" + superklassName + "']"
				+ "/xs:sequence/xs:element[@name='" + rolename + "']"
				+ "/xs:complexType/xs:sequence/xs:element[@ref='" + associatedKlassAlias + "']";
		}
		else
		{
			xpath = "/xs:schema/xs:complexType[@name='" + klassAlias + "']" 
			+ "/xs:complexContent/xs:extension[@base='" + superklassName + "']"
			+ "/xs:sequence/xs:element[@name='" + rolename + "']";
		}
		
		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);
		
		if(collectionRef)
		{
			assertEquals(3, klassElt.getAttributes().size()); // ref, minOccurs, maxOccurs
			assertEquals(klassElt.getAttributeValue("ref"),associatedKlassAlias);
		}
		else
		{
			assertEquals(4, klassElt.getAttributes().size()); // name, type, minOccurs, maxOccurs
			assertEquals(klassElt.getAttributeValue("name"),rolename);
			assertEquals(klassElt.getAttributeValue("type"),associatedKlassAlias);
		}
		
		// TODO :: change eventually to honor minOccurs value passed in
		// ignore 'minOccurs' value for now, as the Code Generator always sets it to zero
		assertEquals(klassElt.getAttributeValue("minOccurs"),"0");
		assertEquals(klassElt.getAttributeValue("maxOccurs"),maxOccurs);

	}		

	protected void validateAttributeElement(Class klass,String attributeName, String attributeType) throws Exception {
		validateAttributeElement(klass,klass.getSimpleName(),attributeName, attributeType);
	}
	
	protected void validateAttributeElement(Class klass, String klassAlias,
			String attributeName, String attributeType) throws Exception {

		Document doc = getDoc();

		String xpath = "/xs:schema/xs:complexType[@name='"
			+ klassAlias + "']/xs:attribute[@name='"
			+ attributeName + "']";

		List<Element> attributeElts = queryXSD(doc, xpath);
		assertNotNull(attributeElts);
		assertEquals(1, attributeElts.size());

		Element elt = attributeElts.get(0);
		assertEquals(elt.getAttributeValue("type").toLowerCase(),"xs:"+attributeType.toLowerCase());
	}
	
	protected void validateAttributeWithRestriction(Class klass,
			String attributeName,String attributeType,String permissibleValue) throws Exception {

		Document doc = getDoc();

		String xpath = "/xs:schema/xs:complexType[@name='"
			+ klass.getSimpleName() + "']/xs:attribute[@name='"
			+ attributeName + "']/xs:simpleType/xs:restriction";

		List<Element> restrictionElts = queryXSD(doc, xpath);
		assertNotNull(restrictionElts);
		assertEquals(1, restrictionElts.size());

		Element restrictionElt = restrictionElts.get(0);
		assertEquals(restrictionElt.getAttributeValue("base").toLowerCase(),"xs:"+attributeType.toLowerCase());
		
		List<Element> enumerationElts = restrictionElt.getChildren();
		assertNotNull(enumerationElts);
		log.debug("enumerationElts.size(): "+enumerationElts.size());

		Element enumerationElt = locateChild(enumerationElts,permissibleValue);
		
		assertNotNull(enumerationElt);
		assertEquals(enumerationElt.getAttributeValue("value"),permissibleValue);
	}

	protected void validateSubclassAttributeElement(Class klass,
			String attributeName, String attributeType) throws Exception {
		validateSubclassAttributeElement(klass, klass.getSimpleName(),getSuperklassName(klass),
				attributeName, attributeType);
	}
	protected void validateSubclassAttributeElement(Class klass, String klassAlias, String superklassName,
			String attributeName, String attributeType) throws Exception {

		Document doc = getDoc();
		
		String xpath = "/xs:schema/xs:complexType[@name='"
			+ klassAlias
			+ "']/xs:complexContent/xs:extension[@base='" + superklassName
			+ "']/xs:attribute[@name='" + attributeName + "']";

//		Element elt = queryXSD(doc, xpath).get(0);
//		xpath = "xs:attribute[@name='" + attributeName + "']";

		List<Element> attributeElts = queryXSD(doc, xpath);
		assertNotNull(attributeElts);
		assertEquals(1, attributeElts.size());

		Element elt = attributeElts.get(0);
		assertEquals(elt.getAttributeValue("type").toLowerCase(),"xs:"+attributeType.toLowerCase());
	}
	
	private String getSuperklassName(Class klass){

		if (klass.getPackage().getName().equals(klass.getSuperclass().getPackage().getName()))
			return klass.getSuperclass().getSimpleName();

		return klass.getSuperclass().getPackage().getName() + ":" + klass.getSuperclass().getSimpleName();
	}
	
	private Element locateChild(List<Element> eltList, String permissibleValue){
		for (Element elt:eltList){
			log.debug("* * * "+elt.getAttributeValue("value")+"; permissibleValue: "+permissibleValue);
			if (elt.getAttributeValue("value").equals(permissibleValue)){
				log.debug("Child found for permissible value: " +elt.getAttributeValue("value"));
				return elt;
			}
		}
		
		return null;
	}
}
