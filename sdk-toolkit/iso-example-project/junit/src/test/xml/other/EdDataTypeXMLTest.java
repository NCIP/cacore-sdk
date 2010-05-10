package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ed data type
 */
public class EdDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ed Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		criteria.add(Property.forName("value1").isNotNull());

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where a.value1.data is not null order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.data").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where (a.value2.nullFlavor is not null or a.value2.data is not null) order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertEquals(5, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.data").isNotNull(), Property.forName("value3.nullFlavor").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.value").isNotNull(), Property.forName("value3.compression").isNotNull()); 
		criteria.add(Restrictions.or(exp1, exp2));

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where (a.value3.nullFlavor is not null or a.value3.data is not null or a.value3.compression is not null or a.value3.value is not null) order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertEquals(5, result.size());
		assertValue3(result);
	}
		
	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue() throws ApplicationException
	{
		EdDataType searchObject = new EdDataType();
		Collection<EdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<EdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
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
					EdDataType unmarshalledData = (EdDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<EdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
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
					EdDataType unmarshalledData = (EdDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<EdDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
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
					EdDataType unmarshalledData = (EdDataType)fromXML(xmlFileName);
					
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
	
	private boolean compareValue1(EdDataType actual, EdDataType result)
	{
		Ed aVal = actual.getValue1();
		assertNotNull(aVal);
		//Work around for Description as it does not exist in ISO impl.
		aVal.setDescription(null);
		Ed rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(EdDataType actual, EdDataType result)
	{
		Ed aVal = actual.getValue2();
		assertNotNull(aVal);
		//Work around for Description as it does not exist in ISO impl.
		aVal.setDescription(null);
		Ed rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(EdDataType actual, EdDataType result)
	{
		Ed aVal = actual.getValue3();
		assertNotNull(aVal);
		//Work around for Description as it does not exist in ISO impl.
		aVal.setDescription(null);
		Ed rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
