package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType;
import gov.nih.nci.iso21090.Bl;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Bl data type
 */
public class BlDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Bl Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.TRUE));

		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.TRUE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType a where a.value1.value = ?  order by a.id", params);
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.add(Property.forName("value2.value").eq(Boolean.TRUE));

		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.TRUE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType a where a.value2.value = ?  order by a.id", params);
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue21ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").isNotNull());

		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue21ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType a where a.value2.nullFlavor is not null order by a.id");
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		assertValue2(result);
	}
	
	
	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue() throws ApplicationException
	{
		BlDataType searchObject = new BlDataType();
		Collection<BlDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
	}
	
	private void assertValue1(Collection<BlDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(BlDataType data : result)
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
					BlDataType unmarshalledData = (BlDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<BlDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(BlDataType data : result)
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
					BlDataType unmarshalledData = (BlDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(BlDataType actual, BlDataType result)
	{
		Bl aVal = actual.getValue1();
		assertNotNull(aVal);
		Bl rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(BlDataType actual, BlDataType result)
	{
		Bl aVal = actual.getValue2();
		assertNotNull(aVal);
		Bl rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

}
