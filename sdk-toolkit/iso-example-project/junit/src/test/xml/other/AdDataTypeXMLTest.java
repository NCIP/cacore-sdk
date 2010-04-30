package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.Collection;

import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ad data type
 */
public class AdDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ad Datatype XML Test Case";
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value1.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value2.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue2(result);
	}
	
	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value3.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value4.part_0.value is not null or a.value4.part_1.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(7, result.size());
		assertValue4(result);
	}
	
	/**
	 * Search Value5 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value5.part_0.value is not null or a.value5.part_1.value is not null or a.value5.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue5(result);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value6.part_0.value is not null or a.value6.part_1.value is not null or a.value6.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue6(result);
	}

	/**
	 * Search Value7 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value7.part_0.value is not null or a.value7.part_1.value is not null or a.value7.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue7(result);
	}
	
	/**
	 * Search Value8 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value8.part_0.value is not null or a.value8.part_1.value is not null or a.value8.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue8(result);
	}

	/**
	 * Search Value9 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue9ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value9.part_0.value is not null or a.value9.part_1.value is not null or a.value9.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue9(result);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue() throws ApplicationException
	{
		AdDataType searchObject = new AdDataType();
		Collection<AdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
		assertValue5(result);
		assertValue6(result);
		assertValue7(result);
		assertValue8(result);
		assertValue9(result);
	}
	
	private void assertValue1(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
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

	private void assertValue4(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue4() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue4(data, unmarshalledData));
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

	private void assertValue5(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue5() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue5(data, unmarshalledData));
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

	private void assertValue6(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue6() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue6(data, unmarshalledData));
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

	private void assertValue7(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue7() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue7(data, unmarshalledData));
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

	private void assertValue8(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue8() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue8(data, unmarshalledData));
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

	private void assertValue9(Collection<AdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue9() == null)
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
					AdDataType unmarshalledData = (AdDataType)fromXML(xmlFileName);
					
					//Verify umarshalled object with original object
					assertTrue(compareValue9(data, unmarshalledData));
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

	private boolean compareValue1(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue1();
		assertNotNull(aVal);
		Ad rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue2();
		assertNotNull(aVal);
		Ad rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue3();
		assertNotNull(aVal);
		Ad rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue4(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue4();
		assertNotNull(aVal);
		Ad rVal = result.getValue4();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue5(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue5();
		assertNotNull(aVal);
		Ad rVal = result.getValue5();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue6(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue6();
		assertNotNull(aVal);
		Ad rVal = result.getValue6();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue7(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue7();
		assertNotNull(aVal);
		Ad rVal = result.getValue7();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue8(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue8();
		assertNotNull(aVal);
		Ad rVal = result.getValue8();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue9(AdDataType actual, AdDataType result)
	{
		Ad aVal = actual.getValue9();
		assertNotNull(aVal);
		Ad rVal = result.getValue9();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
}
