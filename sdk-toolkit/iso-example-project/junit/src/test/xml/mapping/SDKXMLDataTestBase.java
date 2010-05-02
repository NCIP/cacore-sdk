package test.xml.mapping;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.caCOREMarshaller;
import gov.nih.nci.system.client.util.xml.caCOREUnmarshaller;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.aop.framework.Advised;
import org.w3c.dom.Document;

/**
 * @author Dan Dumitru
 * 
 */
public abstract class SDKXMLDataTestBase extends TestCase {
	
	private static Logger log = Logger.getLogger(SDKXMLDataTestBase.class);
	
	protected boolean useGMETags;
	private String namespaceUriPrefix=null;
	
	private String uriPrefix = "gme://caCORE.caCORE/3.2/";
	private String filepathPrefix  = "c:/temp/";
	private String filepathSuffix  = "_test.xml";

	private ApplicationService appService;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	protected XMLUtility myUtil;
	
	protected String II_ROOT_GLOBAL_CONSTANT_VALUE = "2.16.12.123.456.1";

	protected void setUp() throws Exception {
		super.setUp();
		appService = ApplicationServiceProvider.getApplicationService();
//		String url = "http://localhost:8080/example";
//		appService = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		
		// JAXB
		boolean validate = true;
		boolean includeXmlDeclaration = true;
		String namespacePrefix = "gme://caCORE.caCORE/3.2/";
		String jaxbContextName = "gov.nih.nci.cacoresdk.domain.inheritance.abstrakt:gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation:gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable:gov.nih.nci.cacoresdk.domain.inheritance.implicit:gov.nih.nci.cacoresdk.domain.inheritance.multiplechild:gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable:gov.nih.nci.cacoresdk.domain.inheritance.onechild:gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable:gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation:gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable:gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance:gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable:gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel:gov.nih.nci.cacoresdk.domain.interfaze:gov.nih.nci.cacoresdk.domain.manytomany.bidirectional:gov.nih.nci.cacoresdk.domain.manytomany.unidirectional:gov.nih.nci.cacoresdk.domain.manytoone.unidirectional:gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin:gov.nih.nci.cacoresdk.domain.onetomany.bidirectional:gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin:gov.nih.nci.cacoresdk.domain.onetomany.selfassociation:gov.nih.nci.cacoresdk.domain.onetomany.unidirectional:gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin:gov.nih.nci.cacoresdk.domain.onetoone.bidirectional:gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin:gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation:gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin:gov.nih.nci.cacoresdk.domain.onetoone.unidirectional:gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin:gov.nih.nci.cacoresdk.domain.other.datatype:gov.nih.nci.cacoresdk.domain.other.differentpackage:gov.nih.nci.cacoresdk.domain.other.differentpackage.associations:gov.nih.nci.cacoresdk.domain.other.levelassociation:gov.nih.nci.cacoresdk.domain.other.primarykey:gov.nih.nci.cacoresdk.domain.other.validationtype";
		//Marshaller marshaller = new JAXBMarshaller(includeXmlDeclaration,jaxbContextName);  // Use this constructor if you plan to pass the namespace prefix with each call instead
		marshaller = new JAXBMarshaller(includeXmlDeclaration,jaxbContextName,namespacePrefix);
		unmarshaller = new JAXBUnmarshaller(validate,jaxbContextName);		

		// JAXB
		myUtil = new XMLUtility(marshaller, unmarshaller);
		
		useGMETags=Boolean.parseBoolean(System.getProperty("useGMETags"));
		namespaceUriPrefix=System.getProperty("namespaceUriPrefix");
		String outputDir = System.getProperty("outputDir");
		if(outputDir!=null){
			filepathPrefix=outputDir;
		}		
	}


	protected void tearDown() throws Exception 
	{
		appService = null;
		super.tearDown();
	}
	
	protected ApplicationService getApplicationService()
	{
		return appService;
	}
	
	public static String getTestCaseName()
	{
		return "SDK Base Test Case";
	}
	
	protected void toXML(Object resultObj) throws Exception {
		toXML(resultObj,0);
	}
	
	protected void toXML(Object resultObj, int counter) throws Exception {
		String filename = filepathPrefix + resultObj.getClass().getSimpleName();
		
		if (counter != 0){
			filename += ("_test"+counter+".xml");
		} else {
			filename += filepathSuffix;
		}
		
		File myFile = new File(filename);						
		log.info("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);
		Object convertedObject=myUtil.convertFromProxy(resultObj, true);
		myUtil.toXML(convertedObject, myWriter);
		myWriter.close();
	}
	
	protected Object fromXML(Object resultObj) throws Exception {
		File myFile = new File(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);						

		Object myObj = (Object) myUtil.fromXML(myFile);
		
		return myObj;
	}
	
	protected org.jdom.Document getDocument(String filename) {
		org.jdom.Document doc;
		try {
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(filename);
		} catch (Exception ex) {
			throw new RuntimeException("Error reading XML Data file: " + filename,ex);
		}

		return doc;
	}

	protected boolean validateXMLData(Object resultObj, Class klass) throws Exception {
		String schemaFilename=klass.getPackage().getName() + ".xsd";	
			
		if (useGMETags){
			schemaFilename=namespaceUriPrefix+schemaFilename;
			schemaFilename=schemaFilename.replace("://", "_");
			schemaFilename=schemaFilename.replace("/", "_");
		}
		log.debug("* * * schemaFilename: "+schemaFilename);
		return validateXMLData(resultObj, klass, schemaFilename);
	}
	
	protected boolean validateXMLData(Object resultObj, Class klass, String schemaFilename) throws Exception {
		File myFile = new File(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
		log.debug("myFile: "+myFile+"\n\n");
		log.debug("myFile absolute path: "+myFile.getAbsolutePath()+"\n\n");
		
		myUtil.fromXML(myFile);
		
		log.debug("Has validation exceptions: " +((JAXBUnmarshaller)unmarshaller).hasValidationExceptions() );
		
		return !((JAXBUnmarshaller)unmarshaller).hasValidationExceptions();

//		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//		Document document = parser.parse(myFile);
//
//		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//
//		try {
//			log.debug("Validating " + klass.getName() + " against the schema......\n\n");
//			log.debug("Schema filename is: "+schemaFilename+"\n\n");
//			Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFilename));
//			log.debug("Schema filename: "+schemaFile+"\n\n");
//			
//			Schema schema = factory.newSchema(schemaFile);
//
//			Validator validator = schema.newValidator();
//
//			//validator.validate(new DOMSource(document));
//			log.debug(klass.getName() + " has been validated!!!\n\n");
//		} catch (Exception e) {
//			log.error(klass.getName() + " has failed validation!!!  Error reason is: \n\n" + e.getMessage(),e);
//			throw e;
//		}
//		
//		return true;
	}
	
//  TODO :: figure out how to get xpath to work when the XML Data file does not contain a Namespace prefix
//	protected List<Element> queryXMLData(Object obj, String xpath)
//	throws JaxenException {
//
//		// e.g.: xpath = "/*[local-name()='schema']";
//		JDOMXPath path = new JDOMXPath(xpath);
//		path.addNamespace("", "gme://caCORE.caCORE/3.2/gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation");
////		log.debug("path: " + path.toString());
//		List<Element> elts = path.selectNodes(obj);
//
//		// log.debug("Elements Found: " + elts.size());
//		return elts;
//
//	}
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	
	protected void validateClassElements(Object resultObj) throws Exception{
		validateClassElements(resultObj,Class.forName(getClassName(resultObj)).getSimpleName());
	}
	
	protected void validateClassElements(Object resultObj, String gmeClassName)
	throws Exception {
		
		String klassName = getClassName(resultObj);
		
		org.jdom.Document doc = getDocument(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
		Element klassElt = doc.getRootElement();
		
		if (useGMETags){
			assertEquals(gmeClassName, klassElt.getName());
		} else{
			assertEquals(Class.forName(klassName).getSimpleName(), klassElt.getName());
			assertEquals(klassElt.getNamespaceURI(),uriPrefix + Class.forName(klassName).getPackage().getName());			
		}
	}
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateAttribute(Object resultObj, String attributeName, Object attributeValue)
	throws Exception {
		
//		log.debug("Validating Class attributes from: " + filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);

		org.jdom.Document doc = getDocument(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
//		TODO :: figure out how to get xpath to work when the XML Data file does not contain a Namespace prefix
//		String xpath = "" + Class.forName(klassName).getSimpleName();
//		log.debug("xpath: " + xpath);

//		List<Element> elts = queryXMLData(doc, xpath);
//		assertEquals(1, elts.size());
		Element klassElt = doc.getRootElement();
		
		String compareValue = null;
		if (attributeValue != null)
			compareValue = attributeValue.toString();
		
		assertEquals(klassElt.getAttributeValue(attributeName),compareValue);
	}
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateDateAttribute(Object resultObj, String attributeName, Date attributeValue)
	throws Exception {
		
//		log.debug("Validating Class attributes from: " + filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);

		org.jdom.Document doc = getDocument(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
//		TODO :: figure out how to get xpath to work when the XML Data file does not contain a Namespace prefix
//		String xpath = "" + Class.forName(klassName).getSimpleName();
//		log.debug("xpath: " + xpath);

//		List<Element> elts = queryXMLData(doc, xpath);
//		assertEquals(1, elts.size());
		Element klassElt = doc.getRootElement();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.S"); //e.g.:  2007-10-01T00:00:00.000-07:00
		String attributeDate = klassElt.getAttributeValue(attributeName).replace('T', ' ');
		
		Date xmlDate = sdf.parse(attributeDate.substring(0, 21));
		
		assertEquals(0,xmlDate.compareTo(attributeValue));
	}	
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateAssociation(Object resultObj, String associatedKlassName, String roleName)
	throws Exception {
		
//		log.debug("Validating Class association from: " + filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
		org.jdom.Document doc = getDocument(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);

		Element klassElt = doc.getRootElement();
		
		List<Element> children = klassElt.getChildren();
		assertNotNull(children);

		assertEquals(1,countChildren(children,roleName));
		Element roleNameElt = locateChild(children,roleName);
		assertNotNull(roleNameElt);
		assertEquals(roleNameElt.getName(),roleName);
		
		children = roleNameElt.getChildren();
		assertNotNull(children);

		Element associatedKlassElt = locateChild(children,associatedKlassName);
		assertNotNull(associatedKlassElt);
	}
	
	/**
	 * Uses xpath to query the generated XSD Verifies that common elements
	 * attributes(name, type) are present Verifies that the element 'name'
	 * attribute matches the class name
	 * 
	 * @throws Exception
	 */
	protected void validateIso90210Element(Object resultObj, String iso90210ElementName, String iso90210ElementAttributeName, Object iso90210ElementAttributeValue)
	throws Exception {
		
//		log.debug("Validating Class association from: " + filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);
		org.jdom.Document doc = getDocument(filepathPrefix + resultObj.getClass().getSimpleName() + filepathSuffix);

		Element klassElt = doc.getRootElement();
		
		List<Element> children = klassElt.getChildren();
		assertNotNull(children);

		assertEquals(1,countChildren(children,iso90210ElementName));
		Element iso90210Element = locateChild(children,iso90210ElementName);
		assertNotNull(iso90210Element);
		assertEquals(iso90210Element.getName(),iso90210ElementName);
		
		String compareValue = null;
		if (iso90210ElementAttributeValue != null)
			compareValue = iso90210ElementAttributeValue.toString();
		
		assertEquals(iso90210Element.getAttributeValue(iso90210ElementAttributeName),compareValue);
	}	
	
	@SuppressWarnings("unchecked")
	protected Collection search(DetachedCriteria criteria, String type)
			throws ApplicationException {
		return appService.query(criteria, type);
	}

	@SuppressWarnings("deprecation")
	protected Collection search(HQLCriteria criteria, String type)
			throws ApplicationException {
		return appService.query(criteria, type);
	}

	@SuppressWarnings("unchecked")
	protected Collection search(String path, Object searchObject)
			throws ApplicationException {
		return appService.search(path, searchObject);
	}
	
	private Element locateChild(List<Element> eltList, String roleName){
		
		for (Element elt:eltList){
			if (elt.getName().equals(roleName)) return elt;
		}
		
		return null;
	}
	
	
	private int countChildren(List<Element> eltList, String roleName){
		
		int counter=0;
		for (Element elt:eltList){
			if (elt.getName().equals(roleName)) counter++;
		}
		
		return counter;
	}
	
	protected String getClassName(Object resultObj){
		String klassName = resultObj.getClass().getName();
		if (klassName.indexOf('$') > 0) {
			return klassName.substring(0, klassName.indexOf('$'));
		}
			
		return klassName;
	}

}
