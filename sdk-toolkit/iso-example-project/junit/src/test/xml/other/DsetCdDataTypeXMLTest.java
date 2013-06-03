/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType;
import gov.nih.nci.iso21090.Cd;
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
 * Test case for Dset<Cd> data type
 */
public class DsetCdDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset<Cd> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetCdValue1ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value1.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}
*/
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value1.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetCdValue2ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value2.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
	public void testDsetCdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where (a.value2.item.code is not null or a.value2.item.nullFlavor is not null) order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetCdValue3ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value3.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
	public void testDsetCdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value3.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value4.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value5.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		assertValue5(result);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value6.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		assertValue6(result);
	}
	

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value7.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		assertValue7(result);
	}
	
	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		Collection<DsetCdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
		assertValue5(result);
		assertValue6(result);
		assertValue7(result);
	}
	
	private void assertValue1(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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


	private void assertValue4(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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

	private void assertValue5(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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

	private void assertValue6(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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

	private void assertValue7(Collection<DsetCdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
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
					DsetCdDataType unmarshalledData = (DsetCdDataType)fromXML(xmlFileName);
					
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
	
	private boolean compareValue1(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue1();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue1();
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
	
	private boolean compareValue2(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue2();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue2();
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

	private boolean compareValue3(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue3();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue3();
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

	private boolean compareValue4(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue4();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue4();
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
	private boolean compareValue5(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue5();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue5();
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

	private boolean compareValue6(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue6();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue6();
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
	
	private boolean compareValue7(DsetCdDataType actual, DsetCdDataType result)
	{
		DSet<Cd> aVal = actual.getValue7();
		assertNotNull(aVal);
		DSet<Cd> rVal = result.getValue7();
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

	private boolean checkAllNullItems(DSet<Cd> rVal)
	{
		Set<Cd> item = rVal.getItem();
		if(item != null)
		{
			Iterator<Cd> list = item.iterator();
			while(list.hasNext())
			{
				Cd cd = list.next();
				if(cd.getNullFlavor() == null)
					return false;
			}
		}
		return true;
	}
	
}
