/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.text.ParseException;
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
public class RealDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Real Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.value").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertValue1(result);
		assertValue2(result);
	}
	
	private void assertValue1(Collection<RealDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(RealDataType data : result)
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
					RealDataType unmarshalledData = (RealDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<RealDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(RealDataType data : result)
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
					RealDataType unmarshalledData = (RealDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(RealDataType actual, RealDataType result)
	{
		Real aVal = actual.getValue1();
		assertNotNull(aVal);
		Real rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(RealDataType actual, RealDataType result)
	{
		Real aVal = actual.getValue2();
		assertNotNull(aVal);
		Real rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

}
