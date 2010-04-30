package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Cd data type
 */
public class CdDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Cd Datatype XML Test Case";
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value1.code is not null and a.value1.code <> 'CODE81' order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testCdValue2ByDetachedCriteria() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(CdDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").eq(NullFlavor.NI));

		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue2(result);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value2.nullFlavor is not null or (a.value2.code is not null and a.value2.code not like 'CODE6%')) order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testCdValue3ByDetachedCriteria() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(CdDataType.class);
		criteria.add(Property.forName("value3.code").isNotNull());

		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value3.code is not null  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value4.code is not null or a.value4.nullFlavor is not null)  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		assertValue4(result);
	}

	/**
	 * Search Value5 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value5.code is not null and a.value7.id is null)  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		assertValue5(result);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value6.code is not null  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(6, result.size());
		assertValue6(result);
	}
	

	/**
	 * Search Value7 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value7.code is not null order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		assertValue7(result);
	}
	
	/**
	 * Search Value8 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value8.code is not null order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		assertValue8(result);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		Collection<CdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
		assertValue5(result);
		assertValue6(result);
		assertValue7(result);
		assertValue8(result);
	}
	
	private void assertValue1(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue4(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue5(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue6(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue7(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private void assertValue8(Collection<CdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
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
					CdDataType unmarshalledData = (CdDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue1();
		assertNotNull(aVal);
		Cd rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue2();
		assertNotNull(aVal);
		Cd rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue3();
		assertNotNull(aVal);
		Cd rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue4(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue4();
		assertNotNull(aVal);
		Cd rVal = result.getValue4();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue5(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue5();
		assertNotNull(aVal);
		Cd rVal = result.getValue5();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue6(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue6();
		assertNotNull(aVal);
		Cd rVal = result.getValue6();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue7(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue7();
		assertNotNull(aVal);
		Cd rVal = result.getValue7();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue8(CdDataType actual, CdDataType result)
	{
		Cd aVal = actual.getValue8();
		assertNotNull(aVal);
		Cd rVal = result.getValue8();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
}
