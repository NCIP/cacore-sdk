/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk;

import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.util.ClassCache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import test.xml.mapping.SDKXMLDataTestBase;

/**
 * Base class used to support SDK ISO 21090 test cases
 *
 *
 */
public abstract class SDKISOTestBase extends TestCase {

	private ApplicationService appService;
	private ApplicationService appServiceFromUrl;
	protected String II_ROOT_GLOBAL_CONSTANT_VALUE = "2.16.12.123.456.1";
	protected NullFlavor NULL_FLAVOR_GLOBAL_CONSTANT_VALUE = NullFlavor.MSK;

	private static Logger log = Logger.getLogger(SDKXMLDataTestBase.class);

	protected boolean useGMETags;
	private String namespaceUriPrefix=null;

	private String uriPrefix = "gme://caCORE.caCORE/3.2/";
	private String filepathPrefix  = "c:/temp/";
	private String filepathSuffix  = "_test.xml";

	public static final String namespacePrefix = "gme://caCORE.caCORE/3.2/";
	public static final String jaxbContextName = "gov.nih.nci.cacoresdk.domain.other.datatype";
	static final Marshaller marshaller = new JAXBMarshaller(true,jaxbContextName,namespacePrefix);
	static final Unmarshaller unmarshaller = new JAXBUnmarshaller(true,jaxbContextName);
	static final XMLUtility myUtil = new XMLUtility(marshaller, unmarshaller);
	static String paths[] = {"application-config.xml"};
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			paths);;
	private ClassCache classCache;

	protected void setUp() throws Exception {
		super.setUp();
		appService = ApplicationServiceProvider.getApplicationService();

		useGMETags=Boolean.parseBoolean(System.getProperty("useGMETags"));
		namespaceUriPrefix=System.getProperty("namespaceUriPrefix");
		String outputDir = System.getProperty("outputDir");
		if(outputDir!=null){
			filepathPrefix=outputDir;
		}
		classCache = (ClassCache) ctx.getBean("ClassCache");

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

	public ClassCache getClassCache() {
		return classCache;
	}


	protected ApplicationService getApplicationServiceFromUrl() throws Exception
	{
		String url = "http://localhost:8080/isoExample";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}

	protected ApplicationService getBadApplicationServiceFromUrl() throws Exception
	{
		String url = "http://badhost:8080/badcontext";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}

	@SuppressWarnings("unchecked")
	protected Collection search(DetachedCriteria criteria, String type) throws ApplicationException
	{
		return appService.query(criteria, type);
	}

	@SuppressWarnings("deprecation")
	protected Collection search(HQLCriteria criteria, String type) throws ApplicationException
	{
		return appService.query(criteria, type);
	}

	@SuppressWarnings("unchecked")
	protected Collection search(String path, Object searchObject) throws ApplicationException
	{
		return appService.search(path, searchObject);
	}

	public static String getTestCaseName()
	{
		return "SDK Base ISO Test Case";
	}

	protected void toXML(String fileName, Object resultObj) throws XMLUtilityException, IOException
	{
		File myFile = new File(fileName);
		log.info("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);

		myUtil.toXML(resultObj, myWriter);
		myWriter.close();
	}

	protected Object fromXML(String xmlFileName) throws XMLUtilityException {
		File myFile = new File(xmlFileName);
		Object myObj = (Object) myUtil.fromXML(myFile);
		return myObj;
	}

	protected void validateClassElements(org.jdom.Document doc, Object resultObj) throws ClassNotFoundException{
		validateClassElements(doc, resultObj,Class.forName(getClassName(resultObj)).getSimpleName());
	}

	protected void validateClassElements(org.jdom.Document doc, Object resultObj, String gmeClassName) throws ClassNotFoundException{

		String klassName = getClassName(resultObj);

		Element klassElt = doc.getRootElement();

		if (useGMETags){
			assertEquals(gmeClassName, klassElt.getName());
		} else{
			assertEquals(Class.forName(klassName).getSimpleName(), klassElt.getName());
			assertEquals(klassElt.getNamespaceURI(),uriPrefix + Class.forName(klassName).getPackage().getName());
		}
	}

	protected String getClassName(Object resultObj){
		String klassName = resultObj.getClass().getName();
		if (klassName.indexOf('$') > 0) {
			return klassName.substring(0, klassName.indexOf('$'));
		}

		return klassName;
	}

	protected String getXMLFileName(Object resultObj, int index)
	{
		String className = resultObj.getClass().getSimpleName();
		if (className.indexOf('$') > 0) {
			className = className.substring(0, className.indexOf('$'));
		}

		String fileName = filepathPrefix + className;
		if (index != 0){
			fileName += ("_test"+index+".xml");
		} else {
			fileName += filepathSuffix;
		}

		return fileName;
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

	@SuppressWarnings("unchecked")
	protected void validateIsoElement(org.jdom.Document doc, String elementName, String elementAttributeName, Object elementAttributeValue)
	throws Exception {

		Element klassElt = doc.getRootElement();

		List<Element> children = klassElt.getChildren();
		assertNotNull(children);

		assertEquals(1,countChildren(children,elementName));
		Element element = locateChild(children,elementName);
		assertNotNull(element);
		assertEquals(element.getName(),elementName);

		String compareValue = null;
		if (elementAttributeValue != null)
			compareValue = elementAttributeValue.toString();

		assertEquals(element.getAttributeValue(elementAttributeName),compareValue);
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

	protected String getSchemaFileName(Class klass)
	{
		String schemaFileName=klass.getPackage().getName() + ".xsd";

		if (useGMETags){
			schemaFileName=namespaceUriPrefix+schemaFileName;
			schemaFileName=schemaFileName.replace("://", "_");
			schemaFileName=schemaFileName.replace("/", "_");
		}
		return schemaFileName;
	}

	protected boolean validateXMLData(Object resultObj, String schemaFilename, String xmlFileName) throws XMLUtilityException{
		File myFile = new File(xmlFileName);
		myUtil.fromXML(myFile);
		return !((JAXBUnmarshaller)unmarshaller).hasValidationExceptions();
	}


}
