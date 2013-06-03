/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Dset<Tel> data type
 */
public class DsetTelDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset<Tel> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value1.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
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
	public void testDsetTelValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value1.item.value is not null order by a.id asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value2.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
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
	public void testDsetTelValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value2.item.value is not null order by a.id asc asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue3ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value3.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
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
	public void testDsetTelValue3ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value3.item.value is not null order by a.id asc asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}
	
	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<DsetTelDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
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
					DsetTelDataType unmarshalledData = (DsetTelDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<DsetTelDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
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
					DsetTelDataType unmarshalledData = (DsetTelDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<DsetTelDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
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
					DsetTelDataType unmarshalledData = (DsetTelDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(DsetTelDataType actual, DsetTelDataType result)
	{
		DSet<Tel> aVal = actual.getValue1();
		assertNotNull(aVal);
		DSet<Tel> rVal = result.getValue1();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null)
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
	
	private boolean compareValue2(DsetTelDataType actual, DsetTelDataType result)
	{
		DSet<Tel> aVal = actual.getValue2();
		assertNotNull(aVal);
		DSet<Tel> rVal = result.getValue2();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null)
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

	private boolean compareValue3(DsetTelDataType actual, DsetTelDataType result)
	{
		DSet<Tel> aVal = actual.getValue3();
		assertNotNull(aVal);
		DSet<Tel> rVal = result.getValue3();
		//XSD rule: all elements of set must be non-null
		if(aVal.getNullFlavor() != null)
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
	
}
