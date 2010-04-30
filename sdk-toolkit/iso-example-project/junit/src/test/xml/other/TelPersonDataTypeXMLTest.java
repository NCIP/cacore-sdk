package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType;
import gov.nih.nci.iso21090.TelPerson;
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
public class TelPersonDataTypeXMLTest extends SDKISOTestBase
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
	public void testTelPersonValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPersonDataType.class);
		criteria.add(Property.forName("value1").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<TelPersonDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPersonValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType a where (a.value1 is not null and a.value1 <> 'invalid:8004226234') order by a.id asc asc");
		Collection<TelPersonDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPersonValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPersonDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.nullFlavor").isNotNull(), Property.forName("value2.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<TelPersonDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPersonValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<TelPersonDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelPersonValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelPersonDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<TelPersonDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType");
		assertValue1(result);
		assertValue2(result);
	}

	
	private void assertValue1(Collection<TelPersonDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelPersonDataType data : result)
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
					TelPersonDataType unmarshalledData = (TelPersonDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<TelPersonDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(TelPersonDataType data : result)
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
					TelPersonDataType unmarshalledData = (TelPersonDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(TelPersonDataType actual, TelPersonDataType result)
	{
		TelPerson aUri = actual.getValue1();
		assertNotNull(aUri);
		TelPerson rUri = result.getValue1();
		assertNotNull(rUri);
		return aUri.equals(rUri);
	}
	
	private boolean compareValue2(TelPersonDataType actual, TelPersonDataType result)
	{
		TelPerson aUri = actual.getValue2();
		assertNotNull(aUri);
		TelPerson rUri = result.getValue2();
		assertNotNull(rUri);
		return aUri.equals(rUri);
	}

}
