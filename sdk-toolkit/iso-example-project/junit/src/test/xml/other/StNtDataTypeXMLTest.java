package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType;
import gov.nih.nci.iso21090.StNt;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
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
public class StNtDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "StNt Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStNtValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StNtDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<StNtDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStNtValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<StNtDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStNtValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StNtDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.value").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<StNtDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType");
		assertEquals(10, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStNtValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<StNtDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType");
		assertEquals(10, result.size());
		assertValue2(result);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStNtValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StNtDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<StNtDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType");
		assertValue1(result);
		assertValue2(result);
	}
	
	private void assertValue1(Collection<StNtDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(StNtDataType data : result)
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
					StNtDataType unmarshalledData = (StNtDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<StNtDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(StNtDataType data : result)
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
					StNtDataType unmarshalledData = (StNtDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(StNtDataType actual, StNtDataType result)
	{
		StNt aVal = actual.getValue1();
		assertNotNull(aVal);
		StNt rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(StNtDataType actual, StNtDataType result)
	{
		StNt aVal = actual.getValue2();
		assertNotNull(aVal);
		StNt rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

}
