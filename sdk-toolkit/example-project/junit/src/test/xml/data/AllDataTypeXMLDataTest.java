package test.xml.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;
import gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey;

import test.xml.mapping.SDKXMLDataTestBase;

public class AllDataTypeXMLDataTest extends SDKXMLDataTestBase
{
	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "All Datatype XML Data Test Case";
	}

	/**
	 * Verifies that all the values in the object are present
	 * @param result
	 */
	private void validateObject(AllDataType result) throws Exception
	{
		assertNotNull(result.getBooleanPrimitiveValue());
		assertNotNull(result.getBooleanValue());
		assertNotNull(result.getCharacterPrimitiveValue());
		assertNotNull(result.getCharacterValue());
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
		
		if (result.getId()==1)
			assertNotNull(result.getStringCollection());
		
		assertNotNull(result.getStringPrimitiveValue());
		assertNotNull(result.getStringValue());

	}
	
	/**
	 * Verifies that all the values in the object are present
	 * @param result
	 */
	private void validateAttributes(AllDataType result) throws Exception
	{
		validateAttribute(result,"booleanPrimitiveValue",result.getBooleanPrimitiveValue());
		validateAttribute(result,"booleanValue",result.getBooleanValue());
		validateAttribute(result,"characterPrimitiveValue",result.getCharacterPrimitiveValue());
		validateAttribute(result,"characterValue",result.getCharacterValue());
		validateAttribute(result,"clobValue",result.getClobValue());
		
//		System.out.println("datePrimitiveValue: " + result.getDatePrimitiveValue());
//		System.out.println("datePrimitiveValue.toGMTString: " + result.getDatePrimitiveValue().toGMTString());
//		System.out.println("datePrimitiveValue.toLocaleString: " + result.getDatePrimitiveValue().toLocaleString());
		
		validateDateAttribute(result,"datePrimitiveValue",result.getDatePrimitiveValue());
		validateDateAttribute(result,"dateValue",result.getDateValue());
		
		validateAttribute(result,"doublePrimitiveValue",result.getDoublePrimitiveValue());
		validateAttribute(result,"doubleValue",result.getDoubleValue());
		validateAttribute(result,"floatPrimitiveValue",result.getFloatPrimitiveValue());
		validateAttribute(result,"floatValue",result.getFloatValue());
		validateAttribute(result,"id",result.getId());
		validateAttribute(result,"intValue",result.getIntValue());
		validateAttribute(result,"intPrimitiveValue",result.getIntPrimitiveValue());
		validateAttribute(result,"longPrimitiveValue",result.getLongPrimitiveValue());
		validateAttribute(result,"longValue",result.getLongValue());
		
		validateAttribute(result,"stringPrimitiveValue",result.getStringPrimitiveValue());
		validateAttribute(result,"stringValue",result.getStringValue());
	
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
		AllDataType searchObject = new AllDataType();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();){
			AllDataType result = (AllDataType)i.next();
			toXML(result);

			//validateClassElements(result);
			validateAttributes(result);
			assertTrue(validateXMLData(result, searchObject.getClass()));
						
			AllDataType result2 = (AllDataType)fromXML(result);
			
			if (result2.getId()==1)
				validateAssociation(result,"string","stringCollection", true, false);

			validateObject(result2);
		}
	}

}