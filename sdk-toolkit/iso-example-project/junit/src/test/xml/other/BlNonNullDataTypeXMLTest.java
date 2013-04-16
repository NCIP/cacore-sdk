/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType;
import gov.nih.nci.iso21090.BlNonNull;
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
 * Test case for BlNonNull data type
 */
public class BlNonNullDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "BlNonNull Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNonNullValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.FALSE));

		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNonNullValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType a where a.value1.value = '0'");
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNotNullValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.TRUE));

		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNotNullValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.TRUE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType a where a.value1.value = ?  order by a.id", params);
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		assertValue1(result);
	}

	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNonNullValue() throws ApplicationException
	{
		BlNonNullDataType searchObject = new BlNonNullDataType();
		Collection<BlNonNullDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType",searchObject );
		assertEquals(2, result.size());
		assertValue1(result);
	}
	
	private void assertValue1(Collection<BlNonNullDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(BlNonNullDataType data : result)
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
					BlNonNullDataType unmarshalledData = (BlNonNullDataType)fromXML(xmlFileName);
					
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

	private boolean compareValue1(BlNonNullDataType actual, BlNonNullDataType result)
	{
		BlNonNull aVal = actual.getValue1();
		assertNotNull(aVal);
		BlNonNull rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
}
