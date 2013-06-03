/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType;
import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for EnOn data type
 */
public class EnOnDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "EnOn Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnOnValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnOnDataType.class);
		criteria.add(Property.forName("value1.part_0.value").isNotNull());

		Collection<EnOnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
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
	public void testEnOnValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType a where a.value1.part_0.value is not null order by a.id asc");
		Collection<EnOnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnOnValue() throws ApplicationException
	{
		EnOnDataType searchObject = new EnOnDataType();
		Collection<EnOnDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType",searchObject );
		assertValue1(result);
	}
	
	private void assertValue1(Collection<EnOnDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnOnDataType data : result)
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
					EnOnDataType unmarshalledData = (EnOnDataType)fromXML(xmlFileName);
					
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


	private boolean compareValue1(EnOnDataType actual, EnOnDataType result)
	{
		EnOn aVal = actual.getValue1();
		assertNotNull(aVal);
		EnOn rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
