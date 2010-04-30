package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType;
import gov.nih.nci.iso21090.Pq;
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
public class PqDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Pq Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(10, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(10, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Property.forName("value2.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where a.value2.value is not null order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue3ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Restrictions.or(Property.forName("value3.value").isNotNull(), Property.forName("value3.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(11, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue3ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where (a.value3.value is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(11, result.size());
		assertValue3(result);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<PqDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(PqDataType data : result)
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
					PqDataType unmarshalledData = (PqDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<PqDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(PqDataType data : result)
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
					PqDataType unmarshalledData = (PqDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<PqDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(PqDataType data : result)
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
					PqDataType unmarshalledData = (PqDataType)fromXML(xmlFileName);
					
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
	
	private boolean compareValue1(PqDataType actual, PqDataType result)
	{
		Pq aVal = actual.getValue1();
		assertNotNull(aVal);
		Pq rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(PqDataType actual, PqDataType result)
	{
		Pq aVal = actual.getValue2();
		assertNotNull(aVal);
		Pq rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(PqDataType actual, PqDataType result)
	{
		Pq aVal = actual.getValue3();
		assertNotNull(aVal);
		Pq rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
