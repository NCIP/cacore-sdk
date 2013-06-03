/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType;
import gov.nih.nci.iso21090.TelPhone;
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
public class TelPhoneDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "TelPhone Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPhoneValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPhoneDataType.class);
		criteria.add(Property.forName("value1").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<TelPhoneDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPhoneValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType a where a.value1 is not null order by a.id asc asc");
		Collection<TelPhoneDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPhoneValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPhoneDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.nullFlavor").isNotNull(), Property.forName("value2.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<TelPhoneDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPhoneValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<TelPhoneDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPhoneValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPhoneDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<TelPhoneDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType");
		assertValue1(result);
		assertValue2(result);
	}

	
	private void assertValue1(Collection<TelPhoneDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelPhoneDataType data : result)
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
					TelPhoneDataType unmarshalledData = (TelPhoneDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<TelPhoneDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelPhoneDataType data : result)
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
					TelPhoneDataType unmarshalledData = (TelPhoneDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(TelPhoneDataType actual, TelPhoneDataType result)
	{
		TelPhone aUri = actual.getValue1();
		assertNotNull(aUri);
		TelPhone rUri = result.getValue1();
		assertNotNull(rUri);
		return aUri.equals(rUri);
	}
	
	private boolean compareValue2(TelPhoneDataType actual, TelPhoneDataType result)
	{
		TelPhone aUri = actual.getValue2();
		assertNotNull(aUri);
		TelPhone rUri = result.getValue2();
		assertNotNull(rUri);
		return aUri.equals(rUri);
	}

}
