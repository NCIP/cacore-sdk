package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Dset<Ad> data type
 */
public class DsetAdDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset<Ad> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value1.item item  where  item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
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
	public void testDsetAdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value2.item item  where (item.part_0.code is not null or item.part_0.value is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue2(result);
	}
	
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value3.item item  where  (item.part_0.value is not null or item.part_0.code is not null or item.part_0.codeSystem is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value4.item item  where  (item.part_0.value is not null or item.part_1.value is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(9, result.size());
		assertValue4(result);
	}
	
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value5.item item  where item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(10, result.size());
		assertValue5(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value6.item item  where  (item.part_0.value is not null or item.part_1.value is not null or item.part_2.value is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(12, result.size());
		assertValue6(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value7.item item  where item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(10, result.size());
		assertValue7(result);
	}

	
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select distinct dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value8.item item  where item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(4, result.size());
		assertValue8(result);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue() throws ApplicationException
	{
		DsetAdDataType searchObject = new DsetAdDataType();
		Collection<DsetAdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
		assertValue5(result);
		assertValue6(result);
		assertValue7(result);
		assertValue8(result);
	}
	
	private void assertValue1(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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


	private void assertValue4(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private void assertValue5(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private void assertValue6(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private void assertValue7(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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
	
	private void assertValue8(Collection<DsetAdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
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
					DsetAdDataType unmarshalledData = (DsetAdDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue1();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue1();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}
	
	private boolean compareValue2(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue2();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue2();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}

	private boolean compareValue3(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue3();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue3();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}

	private boolean compareValue4(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue4();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue4();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}
	private boolean compareValue5(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue5();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue5();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}

	private boolean compareValue6(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue6();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue6();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}
	
	private boolean compareValue7(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue7();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue7();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}

	private boolean compareValue8(DsetAdDataType actual, DsetAdDataType result)
	{
		DSet<Ad> aVal = actual.getValue8();
		assertNotNull(aVal);
		DSet<Ad> rVal = result.getValue8();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null || checkAllNullItems(aVal))
		{
			assertNull(rVal);
			return true;
		}
		else
		{
			assertNotNull(rVal);
			return aVal.equals(rVal);
		}
	}

	private boolean checkAllNullItems(DSet<Ad> rVal)
	{
		Set<Ad> item = rVal.getItem();
		if(item != null)
		{
			Iterator<Ad> list = item.iterator();
			while(list.hasNext())
			{
				Ad ad = list.next();
				if(ad.getNullFlavor() == null)
					return false;
			}
		}
		return true;
	}
	
}
