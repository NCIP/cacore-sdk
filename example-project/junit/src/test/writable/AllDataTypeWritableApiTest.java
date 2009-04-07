package test.writable;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;

public class AllDataTypeWritableApiTest  extends SDKWritableApiBaseTest{
	private static Logger log = Logger.getLogger(AllDataTypeWritableApiTest.class);
	public static String getTestCaseName() {
		return "All DataType WritableApi Test Case";
	}
	
	public void testSaveObjectAllDataType() {
		log.debug("\n------------saveObjectAllDataType-----------\n");
		AllDataType allDataType = new AllDataType();
		allDataType.setBooleanPrimitiveValue(new Boolean(true));
		allDataType.setBooleanValue(new Boolean(true));
		allDataType.setCharacterPrimitiveValue(new Character('c'));
		allDataType.setClobValue("clobValue");
		allDataType.setDatePrimitiveValue(new Date());
		allDataType.setDoublePrimitiveValue(new Double(10.2));
		allDataType.setDoubleValue(new Double(12));
		allDataType.setFloatPrimitiveValue(new Float(11.3));
		allDataType.setFloatValue(new Float(11.3));
		allDataType.setIntPrimitiveValue(new Integer(12));
		allDataType.setIntValue(23);
		allDataType.setLongPrimitiveValue(new Long(12));
		Collection<String> stringColl=new HashSet<String>();
		stringColl.add("stringcoll");
		allDataType.setStringCollection(stringColl);
		allDataType.setStringPrimitiveValue("stringprimitive");
		allDataType.setStringValue("stringvalue");
		
		save(allDataType);
		
		AllDataType result=(AllDataType)getObject(AllDataType.class, allDataType.getId());
		Assert.assertEquals(allDataType.getBooleanPrimitiveValue(), result.getBooleanPrimitiveValue());
		Assert.assertEquals(allDataType.getBooleanValue(), result.getBooleanValue());
		Assert.assertEquals(allDataType.getCharacterPrimitiveValue(), result.getCharacterPrimitiveValue());
		Assert.assertEquals(allDataType.getCharacterValue(), result.getCharacterValue());
		Assert.assertEquals(allDataType.getClobValue(), result.getClobValue());
		//Assert.assertEquals(allDataType.getDatePrimitiveValue(), result.getDatePrimitiveValue());
		Assert.assertEquals(allDataType.getDateValue(), result.getDateValue());
		Assert.assertEquals(allDataType.getDoublePrimitiveValue(), result.getDoublePrimitiveValue());
		Assert.assertEquals(allDataType.getDoubleValue(), result.getDoubleValue());
		Assert.assertEquals(allDataType.getFloatPrimitiveValue(), result.getFloatPrimitiveValue());
		Assert.assertEquals(allDataType.getFloatValue(), result.getFloatValue());
		Assert.assertEquals(allDataType.getLongPrimitiveValue(), result.getLongPrimitiveValue());
		Assert.assertEquals(allDataType.getLongValue(), result.getLongValue());
		Assert.assertEquals(allDataType.getStringPrimitiveValue(), result.getStringPrimitiveValue());
		Assert.assertEquals(allDataType.getStringCollection().toString(), result.getStringCollection().toString());
		Assert.assertEquals(allDataType.getStringPrimitiveValue(), result.getStringPrimitiveValue());
		Assert.assertEquals(allDataType.getStringValue(), result.getStringValue());
	}
}
