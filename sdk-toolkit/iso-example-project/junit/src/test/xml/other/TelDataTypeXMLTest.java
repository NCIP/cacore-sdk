/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ts data type
 */
public class TelDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Tel Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelDataType.class);
		criteria.add(Property.forName("value1").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<TelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType a where a.value1 is not null order by a.id asc");
		Collection<TelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.nullFlavor").isNotNull(), Property.forName("value2.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<TelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc");
		Collection<TelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<TelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType");
		assertValue1(result);
		assertValue2(result);
	}
	
	private void assertValue1(Collection<TelDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelDataType data : result)
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
					TelDataType unmarshalledData = (TelDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<TelDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelDataType data : result)
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
					TelDataType unmarshalledData = (TelDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(TelDataType actual, TelDataType result)
	{
		Tel aVal = actual.getValue1();
		assertNotNull(aVal);
		Tel rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(TelDataType actual, TelDataType result)
	{
		Tel aVal = actual.getValue2();
		assertNotNull(aVal);
		Tel rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

}
