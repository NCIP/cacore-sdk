package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for EdText data type
 */
public class EdTextDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "EdText Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdTextDataType.class);
		criteria.add(Property.forName("value1").isNotNull());

		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType a where a.value1.value is not null order by a.id asc");
		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdTextDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").eq(NullFlavor.NI));

		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList();
		params.add(NullFlavor.NI);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType a where a.value2.nullFlavor = ? order by a.id asc", params);
		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdTextDataType.class);
		criteria.add(Property.forName("value3.nullFlavor").eq(NullFlavor.NI));

		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue3ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList();
		params.add(NullFlavor.NI);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType a where a.value3.nullFlavor = ? order by a.id asc", params);
		Collection<EdTextDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}
	
	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdTextValue() throws ApplicationException
	{
		EdTextDataType searchObject = new EdTextDataType();
		Collection<EdTextDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<EdTextDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdTextDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue1() == null)
					continue;
				
				try
				{
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					EdTextDataType unmarshalledData = (EdTextDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue1(data, unmarshalledData));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				catch(XMLUtilityException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				counter++;
		}
	}

	private void assertValue2(Collection<EdTextDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdTextDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue2() == null)
					continue;
				
				try
				{
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					EdTextDataType unmarshalledData = (EdTextDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue2(data, unmarshalledData));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				catch(XMLUtilityException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				counter++;
		}
	}

	private void assertValue3(Collection<EdTextDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdTextDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue3() == null)
					continue;
				
				try
				{
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					EdTextDataType unmarshalledData = (EdTextDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue3(data, unmarshalledData));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				catch(XMLUtilityException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				counter++;
		}
	}
	
	private boolean compareValue1(EdTextDataType actual, EdTextDataType result)
	{
		EdText aVal = actual.getValue1();
		assertNotNull(aVal);
		EdText rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(EdTextDataType actual, EdTextDataType result)
	{
		EdText aVal = actual.getValue2();
		assertNotNull(aVal);
		EdText rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(EdTextDataType actual, EdTextDataType result)
	{
		Ed aVal = actual.getValue3();
		assertNotNull(aVal);
		Ed rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
