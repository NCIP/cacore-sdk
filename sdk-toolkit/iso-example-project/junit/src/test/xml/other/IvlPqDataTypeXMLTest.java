package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ts data type
 */
public class IvlPqDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl<Pq> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Property.forName("value1.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where a.value1.low.value is not null order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Property.forName("value2.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where a.value2.low.value is not null order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.low.value").isNotNull(), Property.forName("value3.low.precision").isNotNull());
		criteria.add(Restrictions.or(Property.forName("value3.nullFlavor").isNotNull(), exp1));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where (a.value3.low.value is not null or a.value3.low.precision is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue4ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Restrictions.or(Property.forName("value4.high.value").isNotNull(), Property.forName("value2.high.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue4(result);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where (a.value4.high.value is not null or a.value4.high.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		assertValue4(result);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
	}
	
	private void assertValue1(Collection<IvlPqDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(IvlPqDataType data : result)
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
					IvlPqDataType unmarshalledData = (IvlPqDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<IvlPqDataType> result)
	{
		assertNotNull(result);
		int counter = 1;
		for(IvlPqDataType data : result)
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
					IvlPqDataType unmarshalledData = (IvlPqDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<IvlPqDataType> result)
	{
		assertNotNull(result);
		int counter = 1;
		for(IvlPqDataType data : result)
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
					IvlPqDataType unmarshalledData = (IvlPqDataType)fromXML(xmlFileName);
					
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

	private void assertValue4(Collection<IvlPqDataType> result)
	{
		assertNotNull(result);
		int counter = 1;
		for(IvlPqDataType data : result)
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
					IvlPqDataType unmarshalledData = (IvlPqDataType)fromXML(xmlFileName);
					
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
	
	private boolean compareValue1(IvlPqDataType actual, IvlPqDataType result)
	{
		Ivl<Pq> aVal = actual.getValue1();
		assertNotNull(aVal);
		Ivl<Pq> rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(IvlPqDataType actual, IvlPqDataType result)
	{
		Ivl<Pq> aVal = actual.getValue2();
		assertNotNull(aVal);
		Ivl<Pq> rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(IvlPqDataType actual, IvlPqDataType result)
	{
		Ivl<Pq> aVal = actual.getValue3();
		assertNotNull(aVal);
		Ivl<Pq> rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue4(IvlPqDataType actual, IvlPqDataType result)
	{
		Ivl<Pq> aVal = actual.getValue4();
		assertNotNull(aVal);
		Ivl<Pq> rVal = result.getValue4();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
