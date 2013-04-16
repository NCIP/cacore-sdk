/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;

public class AllDataTypeWritableApiTest  extends SDKWritableBaseTest{
	private static Logger log = Logger.getLogger(AllDataTypeWritableApiTest.class);
	public static String getTestCaseName() {
		return "All DataType WritableApi Test Case";
	}

	public void testSaveObjectAllDataType() throws Exception{
		log.debug("\n------------saveObjectAllDataType-----------\n");
		AllDataType allDataType = new AllDataType();
		//allDataType.setBooleanPrimitiveValue(new Boolean(true));
		//allDataType.setBooleanValue(new Boolean(true));
		allDataType.setCharacterPrimitiveValue(new Character('c'));
		allDataType.setClobValue("clobValue");

		byte[] bytearray = "bytearray".getBytes();
		Byte[] BigByteArray = new Byte[bytearray.length];
		for(int i = 0; i < bytearray.length; i++)
		{
			BigByteArray[i] = new Byte(bytearray[i]);
		}

		allDataType.setBlobValue(BigByteArray);
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


		Byte[] BigByteArray1 = allDataType.getBlobValue();
		byte[] bytearray1 = new byte[BigByteArray1.length];
		for(int i = 0; i < BigByteArray1.length; i++)
		{
			bytearray1[i] = BigByteArray1[i].byteValue();
		}

		Byte[] BigByteArray2 = result.getBlobValue();
		byte[] bytearray2 = new byte[BigByteArray2.length];
		for(int i = 0; i < BigByteArray2.length; i++)
		{
			bytearray2[i] = BigByteArray2[i].byteValue();
		}


		Assert.assertEquals(new String(bytearray1), new String(bytearray2));
		Assert.assertEquals(allDataType.getDatePrimitiveValue(), result.getDatePrimitiveValue());
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
