package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType;
import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
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
 * Test case for EnPn data type
 */
public class EnPnDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "EnPn Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnPnValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnPnDataType.class);
		criteria.add(Property.forName("value1.part_0.value").isNotNull());

		Collection<EnPnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType");
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
	public void testEnPnValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType a where a.value1.part_0.value is not null order by a.id asc");
		Collection<EnPnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnPnValue() throws ApplicationException
	{
		EnPnDataType searchObject = new EnPnDataType();
		Collection<EnPnDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType",searchObject );
		assertValue1(result);
	}
	
	private void assertValue1(Collection<EnPnDataType> result)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnPnDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue1() == null)
					continue;
				
				try
				{
					//Test model is not modeled to have part type 
					//data.getValue1().getPart().get(counter-1).setType(EntityNamePartType.FAM);
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					EnPnDataType unmarshalledData = (EnPnDataType)fromXML(xmlFileName);
					
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


	private boolean compareValue1(EnPnDataType actual, EnPnDataType result)
	{
		EnPn aVal = actual.getValue1();
		assertNotNull(aVal);
		EnPn rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
}
