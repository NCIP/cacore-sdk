package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Dset<Ii> data type
 */
public class DsetIiDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset<Ii> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue1ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value1.item.extension").like("Extension1%"));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
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
	public void testDsetIiValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("Extension%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value1.item.extension like ? order by a.id asc", params);
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue2ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value2.item.extension").like("Extension%"));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
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
	public void testDsetIiValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("Extension%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value2.item.extension like ? order by a.id asc", params);
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(4, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue3ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Restrictions.or(Property.forName("value3.item.extension").isNotNull(), Property.forName("value3.item.identifier").isNotNull()));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}
*/	

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where (a.value3.item.extension is not null or a.value3.item.identifierName is not null) order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue4ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value4.item.root").isNotNull());

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue4(result);
	}
	
*/
	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value4.item.root is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue4(result);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value5.item.extension is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue5(result);
	}
	
	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value6.item.extension is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue6(result);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		Collection<DsetIiDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
		assertValue5(result);
		assertValue6(result);
	}
	
	private void assertValue1(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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


	private void assertValue4(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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

	private void assertValue5(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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

	private void assertValue6(Collection<DsetIiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
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
					DsetIiDataType unmarshalledData = (DsetIiDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue1();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue2();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue3();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue4(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue4();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue4();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue5(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue5();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue5();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue6(DsetIiDataType actual, DsetIiDataType result)
	{
		DSet<Ii> aVal = actual.getValue6();
		assertNotNull(aVal);
		DSet<Ii> rVal = result.getValue6();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
