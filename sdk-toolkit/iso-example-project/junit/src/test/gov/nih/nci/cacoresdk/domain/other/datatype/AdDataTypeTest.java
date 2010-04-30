package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType;
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ad ( AD ) data type
 */
public class AdDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ad Datatype Test Case";
	}

	/**
	 * Uses HQL for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	public void testQueryRowCountHQL() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertEquals(48,count);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value1.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("2");
		indexList.add("3");
		indexList.add("4");
		indexList.add("5");
		indexList.add("6");
		assertValue1(result, indexList);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value2.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("7");
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		indexList.add("11");
		assertValue2(result, indexList);
	}
	
	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where a.value3.part_0.value is not null order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("12");
		indexList.add("13");
		indexList.add("14");
		indexList.add("15");
		indexList.add("16");
		assertValue3(result, indexList);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value4.part_0.value is not null or a.value4.part_1.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(7, result.size());
		List indexList = new ArrayList();
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		indexList.add("21");
		indexList.add("22");
		indexList.add("23");
		assertValue4(result, indexList);
	}
	
	/**
	 * Search Value5 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value5.part_0.value is not null or a.value5.part_1.value is not null or a.value5.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("24");
		indexList.add("25");
		indexList.add("26");
		indexList.add("27");
		indexList.add("28");
		assertValue5(result, indexList);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value6.part_0.value is not null or a.value6.part_1.value is not null or a.value6.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("29");
		indexList.add("30");
		indexList.add("31");
		indexList.add("32");
		indexList.add("33");
		assertValue6(result, indexList);
	}

	/**
	 * Search Value7 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value7.part_0.value is not null or a.value7.part_1.value is not null or a.value7.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("34");
		indexList.add("35");
		indexList.add("36");
		indexList.add("37");
		indexList.add("38");
		assertValue7(result, indexList);
	}
	
	/**
	 * Search Value8 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value8.part_0.value is not null or a.value8.part_1.value is not null or a.value8.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("39");
		indexList.add("40");
		indexList.add("41");
		indexList.add("42");
		indexList.add("43");
		assertValue8(result, indexList);
	}

	/**
	 * Search Value9 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue9ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType a where (a.value9.part_0.value is not null or a.value9.part_1.value is not null or a.value9.part_2.value is not null) order by a.id asc");
		Collection<AdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("44");
		assertValue9(result, indexList);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testAdValue() throws ApplicationException
	{
		AdDataType searchObject = new AdDataType();
		Collection<AdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
		assertValue5(result, null);
		assertValue6(result, null);
		assertValue7(result, null);
		assertValue8(result, null);
		assertValue9(result, null);
	}
	
	private void assertValue1(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");
				
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				assertNotNull(data.getValue1().getPart().get(0));
				assertNotNull(data.getValue1().getPart().get(0).getType());
				assertNotNull(data.getValue1().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue1().getPart().get(0).getType());
				assertEquals("1 Jefferson Street", data.getValue1().getPart().get(0).getValue());
				assertNull(data.getValue1().getPart().get(0).getCode());
				//Global constant
				assertEquals("ADXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
				//NullFlavor global constant will be assigned if value1.part is null
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 3) || (index != null && index.contains("3")))
			{
				if(index != null) 
					index.remove("3");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				assertNotNull(data.getValue1().getPart().get(0));
				assertNotNull(data.getValue1().getPart().get(0).getType());
				assertNotNull(data.getValue1().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue1().getPart().get(0).getType());
				assertEquals("2 E Jefferson Street", data.getValue1().getPart().get(0).getValue());
				assertNull(data.getValue1().getPart().get(0).getCode());
				//Global constant
				assertEquals("ADXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				if(index != null) 
					index.remove("4");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				assertNotNull(data.getValue1().getPart().get(0));
				assertNotNull(data.getValue1().getPart().get(0).getType());
				assertNotNull(data.getValue1().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue1().getPart().get(0).getType());
				assertEquals("3 Jefferson Street", data.getValue1().getPart().get(0).getValue());
				assertNull(data.getValue1().getPart().get(0).getCode());
				//Global constant
				assertEquals("ADXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 5) || (index != null && index.contains("5")))
			{
				if(index != null) 
					index.remove("5");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				assertNotNull(data.getValue1().getPart().get(0));
				assertNotNull(data.getValue1().getPart().get(0).getType());
				assertNotNull(data.getValue1().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue1().getPart().get(0).getType());
				assertEquals("4 Sun Street", data.getValue1().getPart().get(0).getValue());
				assertNull(data.getValue1().getPart().get(0).getCode());
				//Global constant
				assertEquals("ADXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				assertNotNull(data.getValue1().getPart().get(0));
				assertNotNull(data.getValue1().getPart().get(0).getType());
				assertNotNull(data.getValue1().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue1().getPart().get(0).getType());
				assertEquals("5 Sun Street", data.getValue1().getPart().get(0).getValue());
				assertNull(data.getValue1().getPart().get(0).getCode());
				//Global constant
				assertEquals("ADXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");
				
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertNotNull(data.getValue2().getPart().get(0));
				assertNotNull(data.getValue2().getPart().get(0).getType());
				assertNotNull(data.getValue2().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue2().getPart().get(0).getType());
				assertEquals("5th Floor", data.getValue2().getPart().get(0).getValue());
				assertNull(data.getValue2().getPart().get(0).getCode());
				//Local constant
				assertEquals("CODESYSTEM", data.getValue2().getPart().get(0).getCodeSystem());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertNotNull(data.getValue2().getPart().get(0));
				assertNotNull(data.getValue2().getPart().get(0).getType());
				assertNotNull(data.getValue2().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue2().getPart().get(0).getType());
				assertEquals("6th Floor", data.getValue2().getPart().get(0).getValue());
				assertNull(data.getValue2().getPart().get(0).getCode());
				//Local constant
				assertEquals("CODESYSTEM", data.getValue2().getPart().get(0).getCodeSystem());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertNotNull(data.getValue2().getPart().get(0));
				assertNotNull(data.getValue2().getPart().get(0).getType());
				assertNotNull(data.getValue2().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue2().getPart().get(0).getType());
				assertEquals("7th Floor", data.getValue2().getPart().get(0).getValue());
				assertNull(data.getValue2().getPart().get(0).getCode());
				//Local constant
				assertEquals("CODESYSTEM", data.getValue2().getPart().get(0).getCodeSystem());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertNotNull(data.getValue2().getPart().get(0));
				assertNotNull(data.getValue2().getPart().get(0).getType());
				assertNotNull(data.getValue2().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue2().getPart().get(0).getType());
				assertEquals("8th Floor", data.getValue2().getPart().get(0).getValue());
				assertEquals("NCI5", data.getValue2().getPart().get(0).getCode());
				//Local constant
				assertEquals("CODESYSTEM", data.getValue2().getPart().get(0).getCodeSystem());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertNotNull(data.getValue2().getPart().get(0));
				assertNotNull(data.getValue2().getPart().get(0).getType());
				assertNotNull(data.getValue2().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue2().getPart().get(0).getType());
				assertEquals("9th Floor", data.getValue2().getPart().get(0).getValue());
				assertEquals("NCI6", data.getValue2().getPart().get(0).getCode());
				//Local constant
				assertEquals("CODESYSTEM", data.getValue2().getPart().get(0).getCodeSystem());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");
				
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertNotNull(data.getValue3().getPart().get(0));
				assertNotNull(data.getValue3().getPart().get(0).getType());
				assertNotNull(data.getValue3().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue3().getPart().get(0).getType());
				assertEquals("E Jefferson Street", data.getValue3().getPart().get(0).getValue());
				assertNull(data.getValue3().getPart().get(0).getCode());
				assertNull(data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertNotNull(data.getValue3().getPart().get(0));
				assertNotNull(data.getValue3().getPart().get(0).getType());
				assertNotNull(data.getValue3().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue3().getPart().get(0).getType());
				assertEquals("E Jefferson Street", data.getValue3().getPart().get(0).getValue());
				assertEquals("NCI", data.getValue3().getPart().get(0).getCode());
				assertNull(data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertNotNull(data.getValue3().getPart().get(0));
				assertNotNull(data.getValue3().getPart().get(0).getType());
				assertNotNull(data.getValue3().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue3().getPart().get(0).getType());
				assertEquals("Jefferson Street", data.getValue3().getPart().get(0).getValue());
				assertEquals("NCI1", data.getValue3().getPart().get(0).getCode());
				assertEquals("codeSystem1", data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertNotNull(data.getValue3().getPart().get(0));
				assertNotNull(data.getValue3().getPart().get(0).getType());
				assertNotNull(data.getValue3().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue3().getPart().get(0).getType());
				assertEquals("F Jefferson Street", data.getValue3().getPart().get(0).getValue());
				assertEquals("NCI2", data.getValue3().getPart().get(0).getCode());
				assertEquals("codeSystem2", data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertNotNull(data.getValue3().getPart().get(0));
				assertNotNull(data.getValue3().getPart().get(0).getType());
				assertNotNull(data.getValue3().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue3().getPart().get(0).getType());
				assertEquals("G Jefferson Street", data.getValue3().getPart().get(0).getValue());
				assertEquals("NCI3", data.getValue3().getPart().get(0).getCode());
				assertEquals("codeSystem3", data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue4(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");
				
				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("E Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("NCI1", data.getValue4().getPart().get(0).getCode());
				assertEquals("codeSystem1", data.getValue4().getPart().get(0).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("F Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("NCI2", data.getValue4().getPart().get(0).getCode());
				assertEquals("codeSystem2", data.getValue4().getPart().get(0).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("E Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("NCI-DC1", data.getValue4().getPart().get(0).getCode());
				assertEquals("codeSystem2", data.getValue4().getPart().get(0).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(1).getType());
				assertNotNull(data.getValue4().getPart().get(1).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(1).getType());
				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("F Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("F Jefferson Street", data.getValue4().getPart().get(1).getValue());
				assertEquals("NCI-DC2", data.getValue4().getPart().get(1).getCode());
				assertEquals("codeSystem2", data.getValue4().getPart().get(1).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(1).getType());
				assertNotNull(data.getValue4().getPart().get(1).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(1).getType());
				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("G Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("G Jefferson Street", data.getValue4().getPart().get(1).getValue());
				assertEquals("NCI3", data.getValue4().getPart().get(0).getCode());
				assertEquals("NCI-DC3", data.getValue4().getPart().get(1).getCode());
				assertEquals("codeSystem2", data.getValue4().getPart().get(1).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(1).getType());
				assertNotNull(data.getValue4().getPart().get(1).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(1).getType());
				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("H Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("H Jefferson Street", data.getValue4().getPart().get(1).getValue());
				assertEquals("NCI4", data.getValue4().getPart().get(0).getCode());
				assertEquals("NCI-DC4", data.getValue4().getPart().get(1).getCode());
				assertEquals("codeSystem4", data.getValue4().getPart().get(0).getCodeSystem());
				assertEquals("codeSystem2", data.getValue4().getPart().get(1).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(0));

				assertNotNull(data.getValue4().getPart().get(0));
				assertNotNull(data.getValue4().getPart().get(0).getType());
				assertNotNull(data.getValue4().getPart().get(0).getValue());

				assertNotNull(data.getValue4().getPart().get(1));
				assertNotNull(data.getValue4().getPart().get(1).getType());
				assertNotNull(data.getValue4().getPart().get(1).getValue());

				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(1).getType());
				assertEquals(AddressPartType.AL, data.getValue4().getPart().get(0).getType());
				assertEquals("I Jefferson Street", data.getValue4().getPart().get(0).getValue());
				assertEquals("I Jefferson Street", data.getValue4().getPart().get(1).getValue());
				assertEquals("NCI5", data.getValue4().getPart().get(0).getCode());
				assertEquals("NCI-DC5", data.getValue4().getPart().get(1).getCode());
				assertEquals("codeSystem5", data.getValue4().getPart().get(0).getCodeSystem());
				assertEquals("codeSystem2", data.getValue4().getPart().get(1).getCodeSystem());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue4());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue4().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue5(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");
				
				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());

				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(0).getType());
				assertNotNull(data.getValue5().getPart().get(0).getValue());
				assertEquals(AddressPartType.AL, data.getValue5().getPart().get(0).getType());
				assertEquals("Address line1", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCode());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(1));
				assertNotNull(data.getValue5().getPart().get(1).getType());
				assertNotNull(data.getValue5().getPart().get(1).getValue());
				assertEquals(AddressPartType.DAL, data.getValue5().getPart().get(1).getType());
				assertEquals("Delivery line1", data.getValue5().getPart().get(1).getValue());
				assertNull(data.getValue5().getPart().get(1).getCode());
				assertNull(data.getValue5().getPart().get(1).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(2));
				assertNotNull(data.getValue5().getPart().get(2).getType());
				assertNotNull(data.getValue5().getPart().get(2).getValue());
				assertEquals(AddressPartType.CTY, data.getValue5().getPart().get(2).getType());
				assertEquals("Rockville", data.getValue5().getPart().get(2).getValue());
				assertNull(data.getValue5().getPart().get(2).getCode());
				assertNull(data.getValue5().getPart().get(2).getCodeSystem());

				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());

				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(0).getType());
				assertNotNull(data.getValue5().getPart().get(0).getValue());
				assertEquals(AddressPartType.AL, data.getValue5().getPart().get(0).getType());
				assertEquals("Address line1", data.getValue5().getPart().get(0).getValue());
				assertEquals("AL_CODE1", data.getValue5().getPart().get(0).getCode());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(1));
				assertNotNull(data.getValue5().getPart().get(1).getType());
				assertNotNull(data.getValue5().getPart().get(1).getValue());
				assertEquals(AddressPartType.DAL, data.getValue5().getPart().get(1).getType());
				assertEquals("Delivery line1", data.getValue5().getPart().get(1).getValue());
				assertEquals("DAL_CODE1", data.getValue5().getPart().get(1).getCode());
				assertNull(data.getValue5().getPart().get(1).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(2));
				assertNotNull(data.getValue5().getPart().get(2).getType());
				assertNotNull(data.getValue5().getPart().get(2).getValue());
				assertEquals(AddressPartType.CTY, data.getValue5().getPart().get(2).getType());
				assertEquals("Rockville", data.getValue5().getPart().get(2).getValue());
				assertEquals("CITY_CODE1", data.getValue5().getPart().get(2).getCode());
				assertNull(data.getValue5().getPart().get(2).getCodeSystem());

				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());

				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(0).getType());
				assertNotNull(data.getValue5().getPart().get(0).getValue());
				assertEquals(AddressPartType.AL, data.getValue5().getPart().get(0).getType());
				assertEquals("Address line1", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCode());
				assertEquals("AL_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(1));
				assertNotNull(data.getValue5().getPart().get(1).getType());
				assertNotNull(data.getValue5().getPart().get(1).getValue());
				assertEquals(AddressPartType.DAL, data.getValue5().getPart().get(1).getType());
				assertEquals("Delivery line1", data.getValue5().getPart().get(1).getValue());
				assertNull(data.getValue5().getPart().get(1).getCode());
				assertEquals("DAL_CODE_SYSTEM1", data.getValue5().getPart().get(1).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(2));
				assertNotNull(data.getValue5().getPart().get(2).getType());
				assertNotNull(data.getValue5().getPart().get(2).getValue());
				assertEquals(AddressPartType.CTY, data.getValue5().getPart().get(2).getType());
				assertEquals("Rockville", data.getValue5().getPart().get(2).getValue());
				assertNull(data.getValue5().getPart().get(2).getCode());
				assertEquals("CITY_CODE_SYSTEM", data.getValue5().getPart().get(2).getCodeSystem());

				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());

				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(0).getType());
				assertNotNull(data.getValue5().getPart().get(0).getValue());
				assertEquals(AddressPartType.AL, data.getValue5().getPart().get(0).getType());
				assertEquals("Address line1", data.getValue5().getPart().get(0).getValue());
				assertEquals("AL_CODE2", data.getValue5().getPart().get(0).getCode());
				assertEquals("AL_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(1));
				assertNotNull(data.getValue5().getPart().get(1).getType());
				assertNotNull(data.getValue5().getPart().get(1).getValue());
				assertEquals(AddressPartType.DAL, data.getValue5().getPart().get(1).getType());
				assertEquals("Delivery line1", data.getValue5().getPart().get(1).getValue());
				assertEquals("DAL_CODE2", data.getValue5().getPart().get(1).getCode());
				assertEquals("DAL_CODE_SYSTEM2", data.getValue5().getPart().get(1).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(2));
				assertNotNull(data.getValue5().getPart().get(2).getType());
				assertNotNull(data.getValue5().getPart().get(2).getValue());
				assertEquals(AddressPartType.CTY, data.getValue5().getPart().get(2).getType());
				assertEquals("Rockville", data.getValue5().getPart().get(2).getValue());
				assertEquals("CITY_CODE2", data.getValue5().getPart().get(2).getCode());
				assertEquals("CITY_CODE_SYSTEM", data.getValue5().getPart().get(2).getCodeSystem());

				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());

				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(0).getType());
				assertNotNull(data.getValue5().getPart().get(0).getValue());
				assertEquals(AddressPartType.AL, data.getValue5().getPart().get(0).getType());
				assertEquals("Address line1", data.getValue5().getPart().get(0).getValue());
				assertEquals("AL_CODE3", data.getValue5().getPart().get(0).getCode());
				assertEquals("AL_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(1));
				assertNotNull(data.getValue5().getPart().get(1).getType());
				assertNotNull(data.getValue5().getPart().get(1).getValue());
				assertEquals(AddressPartType.DAL, data.getValue5().getPart().get(1).getType());
				assertEquals("Delivery line1", data.getValue5().getPart().get(1).getValue());
				assertEquals("DAL_CODE3", data.getValue5().getPart().get(1).getCode());
				assertEquals("DAL_CODE_SYSTEM3", data.getValue5().getPart().get(1).getCodeSystem());

				assertNotNull(data.getValue5().getPart().get(2));
				assertNotNull(data.getValue5().getPart().get(2).getType());
				assertNotNull(data.getValue5().getPart().get(2).getValue());
				assertEquals(AddressPartType.CTY, data.getValue5().getPart().get(2).getType());
				assertEquals("Rockville", data.getValue5().getPart().get(2).getValue());
				assertEquals("CITY_CODE3", data.getValue5().getPart().get(2).getCode());
				assertEquals("CITY_CODE_SYSTEM", data.getValue5().getPart().get(2).getCodeSystem());

				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue5());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue5().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue6(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		int recordIndex = 29;
		for(AdDataType data : result)
		{
			if((index == null && counter == recordIndex) || (index != null && index.contains(Integer.toString(recordIndex))))
			{
				if(index != null) 
					index.remove(Integer.toString(recordIndex));
				
				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());

				for(int i=0;i<28;i++)
				{
					assertNotNull(data.getValue6().getPart().get(i));
					assertNotNull(data.getValue6().getPart().get(i).getType());
					assertNotNull(data.getValue6().getPart().get(i).getValue());
					String typeValue = "VALUE6_"+getPartTypeName(i)+"_VALUE1";
					String codeName = "VALUE6_"+getPartTypeName(i)+"_CODE1";
					String codeSystem = "VALUE6_"+getPartTypeName(i)+"_CODESYSTEM1";
					AddressPartType type = getPartType(i);
					System.out.println("type: "+type);
					System.out.println("codeName: "+codeName);
					System.out.println("codeSystem: "+codeSystem);
					System.out.println("typeValue: "+typeValue);
					assertEquals(type, data.getValue6().getPart().get(i).getType());
					assertEquals(typeValue, data.getValue6().getPart().get(i).getValue());
					if(type.equals(AddressPartType.ADL) ||
						type.equals(AddressPartType.BNS) ||
						type.equals(AddressPartType.DAL) ||
						type.equals(AddressPartType.INT) ||
						type.equals(AddressPartType.STB) ||
						type.equals(AddressPartType.ZIP))
						assertEquals(codeName, data.getValue6().getPart().get(i).getCode());
					else
					{
						assertNull(data.getValue6().getPart().get(i).getCode());
					}
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
					{
						//Global constant
						assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
					}
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());

				for(int i=0;i<28;i++)
				{
					assertNotNull(data.getValue6().getPart().get(i));
					assertNotNull(data.getValue6().getPart().get(i).getType());
					assertNotNull(data.getValue6().getPart().get(i).getValue());
					String typeValue = "VALUE6_"+getPartTypeName(i)+"_VALUE2";
					String codeName = "VALUE6_"+getPartTypeName(i)+"_CODE2";
					String codeSystem = "VALUE6_"+getPartTypeName(i)+"_CODESYSTEM2";
					AddressPartType type = getPartType(i);
					assertEquals(type, data.getValue6().getPart().get(i).getType());
					assertEquals(typeValue, data.getValue6().getPart().get(i).getValue());
					if(type.equals(AddressPartType.ADL) ||
						type.equals(AddressPartType.BNS) ||
						type.equals(AddressPartType.DAL) ||
						type.equals(AddressPartType.INT) ||
						type.equals(AddressPartType.STB) ||
						type.equals(AddressPartType.ZIP))
						assertEquals(codeName, data.getValue6().getPart().get(i).getCode());
					else
						assertNull(data.getValue6().getPart().get(i).getCode());
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
					{
						//Global constant
						assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
					}
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 31) || (index != null && index.contains("31")))
			{
				if(index != null) 
					index.remove("31");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());

				for(int i=0;i<28;i++)
				{
					assertNotNull(data.getValue6().getPart().get(i));
					assertNotNull(data.getValue6().getPart().get(i).getType());
					assertNotNull(data.getValue6().getPart().get(i).getValue());
					String typeValue = "VALUE6_"+getPartTypeName(i)+"_VALUE3";
					String codeName = "VALUE6_"+getPartTypeName(i)+"_CODE3";
					String codeSystem = "VALUE6_"+getPartTypeName(i)+"_CODESYSTEM3";
					AddressPartType type = getPartType(i);
					assertEquals(type, data.getValue6().getPart().get(i).getType());
					assertEquals(typeValue, data.getValue6().getPart().get(i).getValue());
					if(type.equals(AddressPartType.ADL) ||
						type.equals(AddressPartType.BNS) ||
						type.equals(AddressPartType.DAL) ||
						type.equals(AddressPartType.INT) ||
						type.equals(AddressPartType.STB) ||
						type.equals(AddressPartType.ZIP))
						assertEquals(codeName, data.getValue6().getPart().get(i).getCode());
					else
						assertNull(data.getValue6().getPart().get(i).getCode());
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
					{
						//Global constant
						assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
					}
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null)
					index.remove("32");
				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());

				for(int i=0;i<28;i++)
				{
					assertNotNull(data.getValue6().getPart().get(i));
					assertNotNull(data.getValue6().getPart().get(i).getType());
					assertNotNull(data.getValue6().getPart().get(i).getValue());
					String typeValue = "VALUE6_"+getPartTypeName(i)+"_VALUE4";
					String codeName = "VALUE6_"+getPartTypeName(i)+"_CODE4";
					String codeSystem = "VALUE6_"+getPartTypeName(i)+"_CODESYSTEM4";
					AddressPartType type = getPartType(i);
					assertEquals(type, data.getValue6().getPart().get(i).getType());
					assertEquals(typeValue, data.getValue6().getPart().get(i).getValue());
					if(type.equals(AddressPartType.ADL) ||
						type.equals(AddressPartType.BNS) ||
						type.equals(AddressPartType.DAL) ||
						type.equals(AddressPartType.INT) ||
						type.equals(AddressPartType.STB) ||
						type.equals(AddressPartType.ZIP))
						assertEquals(codeName, data.getValue6().getPart().get(i).getCode());
					else
						assertNull(data.getValue6().getPart().get(i).getCode());
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
					{
						//Global constant
						assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
					}
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null)
					index.remove("33");
				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());

				for(int i=0;i<28;i++)
				{
					assertNotNull(data.getValue6().getPart().get(i));
					assertNotNull(data.getValue6().getPart().get(i).getType());
					assertNotNull(data.getValue6().getPart().get(i).getValue());
					String typeValue = "VALUE6_"+getPartTypeName(i)+"_VALUE5";
					String codeName = "VALUE6_"+getPartTypeName(i)+"_CODE5";
					String codeSystem = "VALUE6_"+getPartTypeName(i)+"_CODESYSTEM5";
					AddressPartType type = getPartType(i);
					assertEquals(type, data.getValue6().getPart().get(i).getType());
					assertEquals(typeValue, data.getValue6().getPart().get(i).getValue());
					if(type.equals(AddressPartType.ADL) ||
						type.equals(AddressPartType.BNS) ||
						type.equals(AddressPartType.DAL) ||
						type.equals(AddressPartType.INT) ||
						type.equals(AddressPartType.STB) ||
						type.equals(AddressPartType.ZIP))
						assertEquals(codeName, data.getValue6().getPart().get(i).getCode());
					else
						assertNull(data.getValue6().getPart().get(i).getCode());
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
					{
						//Global constant
						assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
					}
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				for(int i=0;i<data.getValue6().getPart().size();i++)
				{
					assertNull(data.getValue6().getPart().get(i).getType());
					assertNull(data.getValue6().getPart().get(i).getValue());
					assertEquals("ADXP Code System", data.getValue6().getPart().get(i).getCodeSystem());
				}
				assertNull(data.getValue6().getNullFlavor());
				counter++;
			}
			recordIndex++;
		}
	}

	private String getPartTypeName(int index)
	{
		switch (index)
		{
		case 0:
			return "ADL";
		case 1:
			return "AL";
		case 2:
			return "BNN";
		case 3:
			return "BNR";
		case 4:
			return "BNS";
		case 5:
			return "CAR";
		case 6:
			return "CEN";
		case 7:
			return "CNT";
		case 8:
			return "CPA";
		case 9:
			return "CTY";
		case 10:
			return "DAL";
		case 11:
			return "DEL";
		case 12:
			return "DINSTA";
		case 13:
			return "DINSTQ";
		case 14:
			return "DIR";
		case 15:
			return "DMOD";
		case 16:
			return "DMODID";
		case 17:
			return "INT";
		case 18:
			return "POB";
		case 19:
			return "PRE";
		case 20:
			return "SAL";
		case 21:
			return "STA";
		case 22:
			return "STB";
		case 23:
			return "STR";
		case 24:
			return "STTYP";
		case 25:
			return "UNID";
		case 26:
			return "UNIT";
		case 27:
			return "ZIP";
		default: 
			return null;
		}
	}

	private AddressPartType getPartType(int index)
	{
		switch (index)
		{
		case 0:
			return AddressPartType.ADL;
		case 1:
			return AddressPartType.AL;
		case 2:
			return AddressPartType.BNN;
		case 3:
			return AddressPartType.BNR;
		case 4:
			return AddressPartType.BNS;
		case 5:
			return AddressPartType.CAR;
		case 6:
			return AddressPartType.CEN;
		case 7:
			return AddressPartType.CNT;
		case 8:
			return AddressPartType.CPA;
		case 9:
			return AddressPartType.CTY;
		case 10:
			return AddressPartType.DAL;
		case 11:
			return AddressPartType.DEL;
		case 12:
			return AddressPartType.DINSTA;
		case 13:
			return AddressPartType.DINSTQ;
		case 14:
			return AddressPartType.DIR;
		case 15:
			return AddressPartType.DMOD;
		case 16:
			return AddressPartType.DMODID;
		case 17:
			return AddressPartType.INT;
		case 18:
			return AddressPartType.POB;
		case 19:
			return AddressPartType.PRE;
		case 20:
			return AddressPartType.SAL;
		case 21:
			return AddressPartType.STA;
		case 22:
			return AddressPartType.STB;
		case 23:
			return AddressPartType.STR;
		case 24:
			return AddressPartType.STTYP;
		case 25:
			return AddressPartType.UNID;
		case 26:
			return AddressPartType.UNIT;
		case 27:
			return AddressPartType.ZIP;
		default: 
			return null;
		}
	}

	private void assertValue7(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getPart());
				assertEquals(1, data.getValue7().getPart().size());
				assertNotNull(data.getValue7().getPart().get(0));
				assertNotNull(data.getValue7().getPart().get(0).getType());
				assertNotNull(data.getValue7().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue7().getPart().get(0).getType());
				assertEquals("AL_VALUE1", data.getValue7().getPart().get(0).getValue());
				assertEquals("AL_CODE1", data.getValue7().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM1", data.getValue7().getPart().get(0).getCodeSystem());

				assertNull(data.getValue7().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getPart());
				assertEquals(2, data.getValue7().getPart().size());
				assertNotNull(data.getValue7().getPart().get(0));
				assertNotNull(data.getValue7().getPart().get(0).getType());
				assertNotNull(data.getValue7().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue7().getPart().get(0).getType());
				assertEquals("DAL_VALUE1", data.getValue7().getPart().get(0).getValue());
				assertEquals("DAL_CODE1", data.getValue7().getPart().get(0).getCode());
				assertEquals("DAL_CODESYSTEM1", data.getValue7().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue7().getPart().get(1));
				assertNotNull(data.getValue7().getPart().get(1).getType());
				assertNotNull(data.getValue7().getPart().get(1).getValue());

				assertEquals(AddressPartType.CTY, data.getValue7().getPart().get(1).getType());
				assertEquals("CTY_VALUE1", data.getValue7().getPart().get(1).getValue());
				assertEquals("CTY_CODE1", data.getValue7().getPart().get(1).getCode());
				assertEquals("CTY_CODESYSTEM1", data.getValue7().getPart().get(1).getCodeSystem());
				
				assertNull(data.getValue7().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 36) || (index != null && index.contains("36")))
			{
				if(index != null) 
					index.remove("36");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getPart());
				assertEquals(2, data.getValue7().getPart().size());
				assertNotNull(data.getValue7().getPart().get(0));
				assertNotNull(data.getValue7().getPart().get(0).getType());
				assertNotNull(data.getValue7().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue7().getPart().get(0).getType());
				assertEquals("AL_VALUE2", data.getValue7().getPart().get(0).getValue());
				assertEquals("AL_CODE2", data.getValue7().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM2", data.getValue7().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue7().getPart().get(1));
				assertNotNull(data.getValue7().getPart().get(1).getType());
				assertNotNull(data.getValue7().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue7().getPart().get(1).getType());
				assertEquals("DAL_VALUE2", data.getValue7().getPart().get(1).getValue());
				assertEquals("DAL_CODE2", data.getValue7().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM2", data.getValue7().getPart().get(1).getCodeSystem());
				
				assertNull(data.getValue7().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 37) || (index != null && index.contains("37")))
			{
				if(index != null) 
					index.remove("37");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getPart());
				assertEquals(3, data.getValue7().getPart().size());
				assertNotNull(data.getValue7().getPart().get(0));
				assertNotNull(data.getValue7().getPart().get(0).getType());
				assertNotNull(data.getValue7().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue7().getPart().get(0).getType());
				assertEquals("AL_VALUE3", data.getValue7().getPart().get(0).getValue());
				assertEquals("AL_CODE3", data.getValue7().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM3", data.getValue7().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue7().getPart().get(1));
				assertNotNull(data.getValue7().getPart().get(1).getType());
				assertNotNull(data.getValue7().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue7().getPart().get(1).getType());
				assertEquals("DAL_VALUE3", data.getValue7().getPart().get(1).getValue());
				assertEquals("DAL_CODE3", data.getValue7().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM3", data.getValue7().getPart().get(1).getCodeSystem());
				
				assertNotNull(data.getValue7().getPart().get(2));
				assertNotNull(data.getValue7().getPart().get(2).getType());
				assertNotNull(data.getValue7().getPart().get(2).getValue());

				assertEquals(AddressPartType.CTY, data.getValue7().getPart().get(2).getType());
				assertEquals("CTY_VALUE3", data.getValue7().getPart().get(2).getValue());
				assertEquals("CTY_CODE3", data.getValue7().getPart().get(2).getCode());
				assertEquals("CTY_CODESYSTEM3", data.getValue7().getPart().get(2).getCodeSystem());

				assertNull(data.getValue7().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 38) || (index != null && index.contains("38")))
			{
				if(index != null) 
					index.remove("38");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getPart());
				assertEquals(3, data.getValue7().getPart().size());
				assertNotNull(data.getValue7().getPart().get(0));
				assertNotNull(data.getValue7().getPart().get(0).getType());
				assertNotNull(data.getValue7().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue7().getPart().get(0).getType());
				assertEquals("AL_VALUE4", data.getValue7().getPart().get(0).getValue());
				assertEquals("AL_CODE4", data.getValue7().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM4", data.getValue7().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue7().getPart().get(1));
				assertNotNull(data.getValue7().getPart().get(1).getType());
				assertNotNull(data.getValue7().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue7().getPart().get(1).getType());
				assertEquals("DAL_VALUE4", data.getValue7().getPart().get(1).getValue());
				assertEquals("DAL_CODE4", data.getValue7().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM4", data.getValue7().getPart().get(1).getCodeSystem());
				
				assertNotNull(data.getValue7().getPart().get(2));
				assertNotNull(data.getValue7().getPart().get(2).getType());
				assertNotNull(data.getValue7().getPart().get(2).getValue());

				assertEquals(AddressPartType.CTY, data.getValue7().getPart().get(2).getType());
				assertEquals("CTY_VALUE4", data.getValue7().getPart().get(2).getValue());
				assertEquals("CTY_CODE4", data.getValue7().getPart().get(2).getCode());
				assertEquals("CTY_CODESYSTEM4", data.getValue7().getPart().get(2).getCodeSystem());

				assertNull(data.getValue7().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue7());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue7().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue8(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 39) || (index != null && index.contains("39")))
			{
				if(index != null) 
					index.remove("39");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getPart());
				assertEquals(1, data.getValue8().getPart().size());
				assertNotNull(data.getValue8().getPart().get(0));
				assertNotNull(data.getValue8().getPart().get(0).getType());
				assertNotNull(data.getValue8().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue8().getPart().get(0).getType());
				assertEquals("AL_VALUE1", data.getValue8().getPart().get(0).getValue());
				assertEquals("AL_CODE1", data.getValue8().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM1", data.getValue8().getPart().get(0).getCodeSystem());

				assertNull(data.getValue8().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 40) || (index != null && index.contains("40")))
			{
				if(index != null) 
					index.remove("40");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getPart());
				assertEquals(2, data.getValue8().getPart().size());
				assertNotNull(data.getValue8().getPart().get(0));
				assertNotNull(data.getValue8().getPart().get(0).getType());
				assertNotNull(data.getValue8().getPart().get(0).getValue());

				assertEquals(AddressPartType.DAL, data.getValue8().getPart().get(0).getType());
				assertEquals("DAL_VALUE1", data.getValue8().getPart().get(0).getValue());
				assertEquals("DAL_CODE1", data.getValue8().getPart().get(0).getCode());
				assertEquals("DAL_CODESYSTEM1", data.getValue8().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue8().getPart().get(1));
				assertNotNull(data.getValue8().getPart().get(1).getType());
				assertNotNull(data.getValue8().getPart().get(1).getValue());

				assertEquals(AddressPartType.CTY, data.getValue8().getPart().get(1).getType());
				assertEquals("CTY_VALUE1", data.getValue8().getPart().get(1).getValue());
				assertEquals("CTY_CODE1", data.getValue8().getPart().get(1).getCode());
				assertEquals("CTY_CODESYSTEM1", data.getValue8().getPart().get(1).getCodeSystem());
				
				assertNull(data.getValue8().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 41) || (index != null && index.contains("41")))
			{
				if(index != null) 
					index.remove("41");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getPart());
				assertEquals(2, data.getValue8().getPart().size());
				assertNotNull(data.getValue8().getPart().get(0));
				assertNotNull(data.getValue8().getPart().get(0).getType());
				assertNotNull(data.getValue8().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue8().getPart().get(0).getType());
				assertEquals("AL_VALUE2", data.getValue8().getPart().get(0).getValue());
				assertEquals("AL_CODE2", data.getValue8().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM2", data.getValue8().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue8().getPart().get(1));
				assertNotNull(data.getValue8().getPart().get(1).getType());
				assertNotNull(data.getValue8().getPart().get(1).getValue());

				assertEquals(AddressPartType.CTY, data.getValue8().getPart().get(1).getType());
				assertEquals("CTY_VALUE2", data.getValue8().getPart().get(1).getValue());
				assertEquals("CTY_CODE2", data.getValue8().getPart().get(1).getCode());
				assertEquals("CTY_CODESYSTEM2", data.getValue8().getPart().get(1).getCodeSystem());
				
				assertNull(data.getValue8().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 42) || (index != null && index.contains("42")))
			{
				if(index != null) 
					index.remove("42");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getPart());
				assertEquals(2, data.getValue8().getPart().size());
				assertNotNull(data.getValue8().getPart().get(0));
				assertNotNull(data.getValue8().getPart().get(0).getType());
				assertNotNull(data.getValue8().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue8().getPart().get(0).getType());
				assertEquals("AL_VALUE3", data.getValue8().getPart().get(0).getValue());
				assertEquals("AL_CODE3", data.getValue8().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM3", data.getValue8().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue8().getPart().get(1));
				assertNotNull(data.getValue8().getPart().get(1).getType());
				assertNotNull(data.getValue8().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue8().getPart().get(1).getType());
				assertEquals("DAL_VALUE3", data.getValue8().getPart().get(1).getValue());
				assertEquals("DAL_CODE3", data.getValue8().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM3", data.getValue8().getPart().get(1).getCodeSystem());
				
				assertNull(data.getValue8().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 43) || (index != null && index.contains("43")))
			{
				if(index != null) 
					index.remove("43");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getPart());
				assertEquals(3, data.getValue8().getPart().size());
				assertNotNull(data.getValue8().getPart().get(0));
				assertNotNull(data.getValue8().getPart().get(0).getType());
				assertNotNull(data.getValue8().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue8().getPart().get(0).getType());
				assertEquals("AL_VALUE4", data.getValue8().getPart().get(0).getValue());
				assertEquals("AL_CODE4", data.getValue8().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM4", data.getValue8().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue8().getPart().get(1));
				assertNotNull(data.getValue8().getPart().get(1).getType());
				assertNotNull(data.getValue8().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue8().getPart().get(1).getType());
				assertEquals("DAL_VALUE4", data.getValue8().getPart().get(1).getValue());
				assertEquals("DAL_CODE4", data.getValue8().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM4", data.getValue8().getPart().get(1).getCodeSystem());
				
				assertNotNull(data.getValue8().getPart().get(2));
				assertNotNull(data.getValue8().getPart().get(2).getType());
				assertNotNull(data.getValue8().getPart().get(2).getValue());

				assertEquals(AddressPartType.CTY, data.getValue8().getPart().get(2).getType());
				assertEquals("CTY_VALUE4", data.getValue8().getPart().get(2).getValue());
				assertEquals("CTY_CODE4", data.getValue8().getPart().get(2).getCode());
				assertEquals("CTY_CODESYSTEM4", data.getValue8().getPart().get(2).getCodeSystem());

				assertNull(data.getValue8().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue8());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue8().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue9(Collection<AdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		for(AdDataType data : result)
		{
			//Validate 1st record
			if((index == null) || (index != null && index.contains("44")))
			{
				assertNotNull(data);
				assertNotNull(data.getValue9());
				assertNotNull(data.getValue9().getPart());
				assertEquals(3, data.getValue9().getPart().size());
				assertNotNull(data.getValue9().getPart().get(0));
				assertNotNull(data.getValue9().getPart().get(0).getType());
				assertNotNull(data.getValue9().getPart().get(0).getValue());

				assertEquals(AddressPartType.AL, data.getValue9().getPart().get(0).getType());
				assertEquals("AL_VALUE4", data.getValue9().getPart().get(0).getValue());
				assertEquals("AL_CODE4", data.getValue9().getPart().get(0).getCode());
				assertEquals("AL_CODESYSTEM4", data.getValue9().getPart().get(0).getCodeSystem());

				assertNotNull(data.getValue9().getPart().get(1));
				assertNotNull(data.getValue9().getPart().get(1).getType());
				assertNotNull(data.getValue9().getPart().get(1).getValue());

				assertEquals(AddressPartType.DAL, data.getValue9().getPart().get(1).getType());
				assertEquals("DAL_VALUE4", data.getValue9().getPart().get(1).getValue());
				assertEquals("DAL_CODE4", data.getValue9().getPart().get(1).getCode());
				assertEquals("DAL_CODESYSTEM4", data.getValue9().getPart().get(1).getCodeSystem());
				assertNotNull(data.getValue9().getPart().get(2));
				assertNotNull(data.getValue9().getPart().get(2).getType());
				assertNotNull(data.getValue9().getPart().get(2).getValue());

				assertEquals(AddressPartType.CTY, data.getValue9().getPart().get(2).getType());
				assertEquals("CTY_VALUE4", data.getValue9().getPart().get(2).getValue());
				assertEquals("CTY_CODE4", data.getValue9().getPart().get(2).getCode());
				assertEquals("CTY_CODESYSTEM4", data.getValue9().getPart().get(2).getCodeSystem());

				assertNull(data.getValue9().getNullFlavor());
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue9());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue9().getNullFlavor());
			}
		}
	}
	
}
