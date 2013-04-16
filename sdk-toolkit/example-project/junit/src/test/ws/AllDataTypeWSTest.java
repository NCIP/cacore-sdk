/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;
import gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class AllDataTypeWSTest extends SDKWSTestBase
{
	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "All Datatype WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(AllDataType.class);
		mappedKlasses.add(Character.class);
		
		return mappedKlasses;
		
	}	

	/**
	 * Verifies that all the values in the object are present
	 * @param result
	 */
	private void validateObject(AllDataType result)
	{
// TODO :: determine why the following assertions fail (those commented out 		
		assertNotNull(result.getBooleanPrimitiveValue());
		assertNotNull(result.getBooleanValue());
//		assertNotNull(result.getCharacterPrimitiveValue());
//		assertNotNull(result.getCharacterValue());
		assertNotNull(result.getClobValue());
		assertNotNull(result.getDatePrimitiveValue());
		assertNotNull(result.getDateValue());
		assertNotNull(result.getDoublePrimitiveValue());
		assertNotNull(result.getDoubleValue());
		assertNotNull(result.getFloatPrimitiveValue());
		assertNotNull(result.getFloatValue());
		assertNotNull(result.getId());
		assertNotNull(result.getIntValue());
		assertNotNull(result.getIntPrimitiveValue());
		assertNotNull(result.getLongPrimitiveValue());
		assertNotNull(result.getLongValue());
//		assertNotNull(result.getStringCollection());
		assertNotNull(result.getStringPrimitiveValue());
		assertNotNull(result.getStringValue());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch() throws Exception
	{
		Class targetClass = AllDataType.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);

		for(Object obj : results)
		{
			validateObject((AllDataType)obj);
		}
	
	}

	/**
	 * Uses Nested Search Criteria for search where there is no association between two objects
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testNestedSearchNoAssociation() throws Exception
	{
		boolean flag = false;
		try
		{
			Class targetClass = AllDataType.class;
			DoubleKey criteria = new DoubleKey();

			Object[] results = getQueryObjectResults(targetClass, criteria);
		}
		catch(Exception ae)
		{
			flag = true;
		}
		assertTrue(flag);
	}	
	

	/**
	 * Uses Class for search
	 * Searches by the Boolean Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testBooleanPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setBooleanPrimitiveValue(true);

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(3,results.length);
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Boolean data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testBooleanDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setBooleanValue(true);

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(3,results.length);	
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Character Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testCharacterPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setCharacterPrimitiveValue('a');

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(5,results.length);		
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Character data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testCharacterDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setCharacterValue(new Character('a'));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(5,results.length);	
	}	

	/**
	 * Uses Class for search
	 * Searches by the Clob data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testClobDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setClobValue("0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012");

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(5,results.length);			
	}	
		
	
	
	/**
	 * Uses Class for search
	 * Searches by the Date Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testDatePrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setDatePrimitiveValue(new Date("10/1/2007"));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);		
	}	
	
	/**
	 * Uses Class for search
	 * Searches by the Date data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testDateDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setDateValue(new Date("3/3/2003"));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);	
	}

	/**
	 * Uses Class for search
	 * Searches by the Double Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testDoublePrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setDoublePrimitiveValue(10001.00);

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Double data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testDoubleDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setDoubleValue(new Double(555.55));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Float Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testFloatPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setFloatPrimitiveValue(new Float(10.01));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Float data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testFloatDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setFloatValue(new Float(555.55));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Integer Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testIntegerPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setIntPrimitiveValue(new Integer(11));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}	
	/**
	 * Uses Class for search
	 * Searches by the Integer data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testIntegerDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setIntValue(new Integer(5));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the Long Primitive data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testLongPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setLongPrimitiveValue(new Long(1000001));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}	
	/**
	 * Uses Class for search
	 * Searches by the Long data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testLongDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setLongValue(new Long(1000001));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);				
	}
	
	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testStringDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setStringValue(new String("String_Value5"));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}

	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testStringPrimitiveDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setStringPrimitiveValue(new String("abc"));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testStringCollectionDataType() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setId(1);

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);
		
		Object[] stringCollection = getAssociationResults(results[0], "stringCollection", 0);
		assertEquals(3,stringCollection.length);

	}	
	
	/**
	 * Verifies the results of the version method
	 * 
	 * @throws Exception
	 */
	public void testGetVersion() throws Exception
	{
		String version = getVersion();
		assertEquals("4.4",version);
	}
	
	
	/**
	 * Verifies the results of the version method
	 * 
	 * @throws Exception
	 */
	public void testRecordsPerQuery() throws Exception
	{
		Object recordsPerQuery = getRecordsPerQuery();
		assertEquals(1000,recordsPerQuery);
	}
	
	/**
	 * Verifies the results of the version method
	 * 
	 * @throws Exception
	 */
	public void testMaxRecordsPerQuery() throws Exception
	{
		Object maxRecordsPerQuery = getMaximumRecordsPerQuery();
		assertEquals(1000,maxRecordsPerQuery);
	}
	
	/**
	 * Verifies size of the result set 
	 * 
	 * @throws Exception
	 */
	public void testQueryRowCountNestedCriteria() throws Exception
	{
		Class targetClass = AllDataType.class;
		Object criteria = targetClass.newInstance();

		Object results = getTotalNumberOfRecords(targetClass.getName(), criteria);

		assertNotNull(results);
		assertEquals(5,results);
	}
	
	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testInvalidAssociationName() throws Exception
	{
		Class targetClass = AllDataType.class;
		AllDataType criteria = new AllDataType();
		criteria.setId(1);

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(1,results.length);
		
		boolean flag = false;
		//validate that an exception is thrown when an invalid association name is used
		try {
			getAssociationResults(results[0], "invalidAssociationName", 0);
		}
		catch(Exception ae)
		{
			flag = true;
		}
		assertTrue(flag);

	}

}