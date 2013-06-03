/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
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
 * Test case for Ii data type
 */
public class IiDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ii Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value1.extension").eq("II_Extension"));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertNotNull(result);
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("II_Extension");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value1.extension = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").eq(NullFlavor.NI));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue2(result);
	}
	

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.NI);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value2.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue2(result);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value3.nullFlavor").eq(NullFlavor.UNK));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue3(result);
	}
	

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue3ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.UNK);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value3.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue3(result);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue4ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value4.nullFlavor").eq(NullFlavor.INV));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue4(result);
	}
	

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue4ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.INV);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value4.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertValue4(result);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue() throws ApplicationException
	{
		IiDataType searchObject = new IiDataType();
		Collection<IiDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType",searchObject );
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
		assertValue4(result);
	}
	
	private void assertValue1(Collection<IiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
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
					IiDataType unmarshalledData = (IiDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<IiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
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
					IiDataType unmarshalledData = (IiDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<IiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
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
					IiDataType unmarshalledData = (IiDataType)fromXML(xmlFileName);
					
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
	private void assertValue4(Collection<IiDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
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
					IiDataType unmarshalledData = (IiDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(IiDataType actual, IiDataType result)
	{
		Ii aVal = actual.getValue1();
		assertNotNull(aVal);
		Ii rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(IiDataType actual, IiDataType result)
	{
		Ii aVal = actual.getValue2();
		assertNotNull(aVal);
		Ii rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(IiDataType actual, IiDataType result)
	{
		Ii aVal = actual.getValue2();
		assertNotNull(aVal);
		Ii rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	private boolean compareValue4(IiDataType actual, IiDataType result)
	{
		Ii aVal = actual.getValue2();
		assertNotNull(aVal);
		Ii rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
