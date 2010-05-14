package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Cd ( CD ) data type
 */
public class CdDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Cd Datatype Test Case";
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
/*	public void testQueryRowCount() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType",searchObject );
		assertNotNull(results);
		assertEquals(44,results.size());
	}
*/
	/**
	 * Uses HQL for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	public void testQueryRowCountHQL() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(50,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testCdValue1ByDetachedCriteria() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(CdDataType.class);
		criteria.add(Property.forName("value1.code").isNotNull());

		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(10, result.size());
		List indexList = new ArrayList();
		indexList.add("1");
		indexList.add("2");
		indexList.add("3");
		indexList.add("4");
		indexList.add("5");
		indexList.add("40");
		indexList.add("41");
		indexList.add("42");
		indexList.add("43");
		indexList.add("44");
		assertValue1(result, indexList);
	}
*/
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value1.code is not null and a.value1.code <> 'CODE81' order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("1");
		indexList.add("2");
		indexList.add("3");
		indexList.add("4");
		indexList.add("5");
		assertValue1(result, indexList);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testCdValue2ByDetachedCriteria() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(CdDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").eq(NullFlavor.NI));

		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("6");
		indexList.add("7");
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		assertValue2(result, indexList);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value2.nullFlavor is not null or (a.value2.code is not null and a.value2.code not like 'CODE6%')) order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("6");
		indexList.add("7");
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		assertValue2(result, indexList);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testCdValue3ByDetachedCriteria() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(CdDataType.class);
		criteria.add(Property.forName("value3.code").isNotNull());

		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("11");
		indexList.add("12");
		indexList.add("13");
		indexList.add("14");
		indexList.add("15");
		assertValue3(result, indexList);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value3.code is not null  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("11");
		indexList.add("12");
		indexList.add("13");
		indexList.add("14");
		indexList.add("15");
		assertValue3(result, indexList);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value4.code is not null or a.value4.nullFlavor is not null)  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		List indexList = new ArrayList();
		indexList.add("16");
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
	public void testCdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where (a.value5.code is not null and a.value7.id is null)  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		List indexList = new ArrayList();
		indexList.add("24");
		indexList.add("25");
		indexList.add("26");
		indexList.add("27");
		indexList.add("28");
		indexList.add("29");
		indexList.add("30");
		indexList.add("31");
		assertValue5(result, indexList);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value6.code is not null  order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(6, result.size());
		List indexList = new ArrayList();
		indexList.add("45");
		indexList.add("46");
		indexList.add("47");
		indexList.add("48");
		indexList.add("49");
		indexList.add("50");
		assertValue6(result, indexList);
	}
	

	/**
	 * Search Value7 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value7.code is not null order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(8, result.size());
		List indexList = new ArrayList();
		indexList.add("32");
		indexList.add("33");
		indexList.add("34");
		indexList.add("35");
		indexList.add("36");
		indexList.add("37");
		indexList.add("38");
		indexList.add("39");
		assertValue7(result, indexList);
	}
	
	/**
	 * Search Value8 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType a where a.value8.code is not null order by a.id asc");
		Collection<CdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("40");
		assertValue8(result, indexList);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testCdValue() throws ApplicationException
	{
		CdDataType searchObject = new CdDataType();
		Collection<CdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
		assertValue5(result, null);
		assertValue6(result, null);
		assertValue7(result, null);
		assertValue8(result, null);
	}
	
	private void assertValue1(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				if(index != null) 
					index.remove("1");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals("CODE1", data.getValue1().getCode());
				
				assertValue1Constants(data);
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals("CODE2", data.getValue1().getCode());
				
				assertValue1Constants(data);
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 3) || (index != null && index.contains("3")))
			{
				if(index != null) 
					index.remove("3");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals("CODE3", data.getValue1().getCode());
				
				assertValue1Constants(data);
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				if(index != null) 
					index.remove("4");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals("CODE4", data.getValue1().getCode());
				
				assertValue1Constants(data);
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 5) || (index != null && index.contains("5")))
			{
				if(index != null) 
					index.remove("5");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals("CODE5", data.getValue1().getCode());
				
				assertValue1Constants(data);
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 40th record
			if((index == null && counter == 40) || (index != null && index.contains("40")))
			{
				if(index != null) 
					index.remove("40");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getCode());
				
				//assertValue1Constants(data);
				assertNotNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getCode());
				
				//assertValue1Constants(data);
				//Global constant
				assertNotNull(data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue1Constants(CdDataType data)
	{
		//Global constants
		assertEquals("CD Code System", data.getValue1().getCodeSystem());
		assertEquals("CD Code System Name", data.getValue1().getCodeSystemName());
		assertEquals("1.1 HF2", data.getValue1().getCodeSystemVersion());
		assertNotNull(data.getValue1().getDisplayName());
		assertNull(data.getValue1().getDisplayName().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getDisplayName().getNullFlavor());
		assertNotNull(data.getValue1().getOriginalText());
		assertEquals(NullFlavor.NI, data.getValue1().getOriginalText().getNullFlavor());
		assertNull(data.getValue1().getOriginalText().getValue());
	}
	
	private void assertValue2(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE1", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE2", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE3", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getCodeSystem());
				assertNull(data.getValue2().getCodeSystemName());
				assertNull(data.getValue2().getCodeSystemVersion());
				assertNull(data.getValue2().getDisplayName());
				assertNull(data.getValue2().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getCodeSystem());
				assertNull(data.getValue2().getCodeSystemName());
				assertNull(data.getValue2().getCodeSystemVersion());
				assertNull(data.getValue2().getDisplayName());
				assertNull(data.getValue2().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 45)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE61", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 46)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE62", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 47)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE63", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 48)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE64", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 49)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE65", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			else if(index == null && counter == 50)
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE66", data.getValue2().getCode());
				
				assertValue2Constants(data);
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getCode());
				assertNull(data.getValue2().getCodeSystem());
				assertNull(data.getValue2().getCodeSystemName());
				assertNull(data.getValue2().getCodeSystemVersion());
				assertNull(data.getValue2().getDisplayName());
				assertNull(data.getValue2().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2Constants(CdDataType data)
	{
		assertEquals("CD Code System", data.getValue2().getCodeSystem());
		assertEquals("CD Code System Name", data.getValue2().getCodeSystemName());
		assertEquals("1.1 HF2", data.getValue2().getCodeSystemVersion());
		assertNotNull(data.getValue2().getDisplayName());
		assertNull(data.getValue2().getDisplayName().getValue());
		assertEquals(NullFlavor.NI, data.getValue2().getDisplayName().getNullFlavor());
		assertNotNull(data.getValue2().getOriginalText());
		assertEquals(NullFlavor.NI, data.getValue2().getOriginalText().getNullFlavor());
		assertNull(data.getValue2().getOriginalText().getValue());
	}
	
	private void assertValue3(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 11th record
			if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals("CODE1", data.getValue3().getCode());
				
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 12th record
			else if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals("CODE2", data.getValue3().getCode());
				
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 13th record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals("CODE3", data.getValue3().getCode());
				
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 14th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals("CODE4", data.getValue3().getCode());
				
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 15th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals("CODE5", data.getValue3().getCode());
				
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data);
				assertNull(data.getValue3().getCode());
				assertNull(data.getValue3().getCodeSystem());
				assertNull(data.getValue3().getCodeSystemName());
				assertNull(data.getValue3().getCodeSystemVersion());
				assertNull(data.getValue3().getDisplayName());
				assertNull(data.getValue3().getOriginalText());
				
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3Constants(CdDataType data)
	{
		assertEquals("CODESYSTEM", data.getValue3().getCodeSystem());
		assertEquals("CODESYSTEMNAME", data.getValue3().getCodeSystemName());
		assertEquals("CODESYSTEMVERSION", data.getValue3().getCodeSystemVersion());
		assertNotNull(data.getValue3().getDisplayName());
		assertNull(data.getValue3().getDisplayName().getValue());
		assertEquals(NullFlavor.NI, data.getValue3().getDisplayName().getNullFlavor());
		assertNotNull(data.getValue3().getOriginalText());
		assertEquals(NullFlavor.NI, data.getValue3().getOriginalText().getNullFlavor());
		assertNull(data.getValue3().getOriginalText().getValue());
	}
	
	private void assertValue4(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 16th record
			if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals(NullFlavor.NI, data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 17th record
			else if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE2", data.getValue4().getCode());
				
				assertNull(data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE1", data.getValue4().getDisplayName().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getValue());
				assertEquals(NullFlavor.NI, data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 18th record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE3", data.getValue4().getCode());
				
				assertNull(data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE2", data.getValue4().getDisplayName().getValue());
				assertNull(data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertEquals(NullFlavor.NI, data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 19th record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE4", data.getValue4().getCode());
				
				assertNull(data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE2", data.getValue4().getDisplayName().getValue());
				assertEquals("VALUE4_ORIG_TXT_VALUE1", data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 20th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE5", data.getValue4().getCode());
				
				assertEquals("VALUE4_CODE_SYSTEM", data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE3", data.getValue4().getDisplayName().getValue());
				assertEquals("VALUE4_ORIG_TXT_VALUE2", data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 21st record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE6", data.getValue4().getCode());
				
				assertEquals("VALUE4_CODE_SYSTEM", data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE4", data.getValue4().getDisplayName().getValue());
				assertEquals("VALUE4_ORIG_TXT_VALUE3", data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 22nd record
			else if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE7", data.getValue4().getCode());
				
				assertEquals("VALUE4_CODE_SYSTEM", data.getValue4().getCodeSystem());
				assertEquals("VALUE4_CODE_SYSTEM_VERSION1", data.getValue4().getCodeSystemVersion());
				assertNull(data.getValue4().getCodeSystemName());
				assertEquals("VALUE4_DISPLAY_VALUE4", data.getValue4().getDisplayName().getValue());
				assertEquals("VALUE4_ORIG_TXT_VALUE4", data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 23rd record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals("CODE8", data.getValue4().getCode());
				
				assertEquals("VALUE4_CODE_SYSTEM", data.getValue4().getCodeSystem());
				assertEquals("VALUE4_CODE_SYSTEM_NAME", data.getValue4().getCodeSystemName());
				assertEquals("VALUE4_CODE_SYSTEM_VERSION1", data.getValue4().getCodeSystemVersion());
				assertEquals("VALUE4_DISPLAY_VALUE4", data.getValue4().getDisplayName().getValue());
				assertEquals("VALUE4_ORIG_TXT_VALUE5", data.getValue4().getOriginalText().getValue());
				assertNull(data.getValue4().getDisplayName().getNullFlavor());
				assertNull(data.getValue4().getOriginalText().getNullFlavor());
				assertNull(data.getValue4().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data);
				assertNull(data.getValue4().getCode());
				
				assertNull(data.getValue4().getCodeSystem());
				assertNull(data.getValue4().getCodeSystemName());
				assertNull(data.getValue4().getCodeSystemVersion());
				assertNull(data.getValue4().getDisplayName());
				assertNull(data.getValue4().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue4().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue5(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 24th record
			if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE1", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM1", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertNotNull(data.getValue5().getDisplayName());
				assertNull(data.getValue5().getDisplayName().getValue());
				assertEquals(NullFlavor.NI, data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 25th record
			else if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE2", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM2", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE1", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 26th record
			else if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE3", data.getValue5().getCode());
				
				assertNull(data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME1", data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE2", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 27th record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE4", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM3", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE2", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE1", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 28th record
			else if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE5", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME2", data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE3", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE2", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 29st record
			else if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE6", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE3", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 30th record
			else if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE7", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertEquals("VALUE5_CODE_SYSTEM_VERSION1", data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE5", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 31st record
			else if((index == null && counter == 31) || (index != null && index.contains("31")))
			{
				if(index != null) 
					index.remove("31");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE8", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME", data.getValue5().getCodeSystemName());
				assertEquals("VALUE5_CODE_SYSTEM_VERSION1", data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE5", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null) 
					index.remove("32");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE1", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM1", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertNotNull(data.getValue5().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null) 
					index.remove("33");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE2", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM2", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE1", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE3", data.getValue5().getCode());
				
				assertNull(data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME1", data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE2", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue5().getOriginalText());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE4", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM3", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE2", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE1", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 28th record
			else if((index == null && counter == 36) || (index != null && index.contains("36")))
			{
				if(index != null) 
					index.remove("36");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE5", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME2", data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE3", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE2", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 29st record
			else if((index == null && counter == 37) || (index != null && index.contains("37")))
			{
				if(index != null) 
					index.remove("37");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE6", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE3", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 30th record
			else if((index == null && counter == 38) || (index != null && index.contains("38")))
			{
				if(index != null) 
					index.remove("38");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE7", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertEquals("VALUE5_CODE_SYSTEM_VERSION1", data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE5", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 31st record
			else if((index == null && counter == 39) || (index != null && index.contains("39")))
			{
				if(index != null) 
					index.remove("39");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals("CODE8", data.getValue5().getCode());
				
				assertEquals("VALUE5_CODE_SYSTEM", data.getValue5().getCodeSystem());
				assertEquals("VALUE5_CODE_SYSTEM_NAME", data.getValue5().getCodeSystemName());
				assertEquals("VALUE5_CODE_SYSTEM_VERSION1", data.getValue5().getCodeSystemVersion());
				assertEquals("VALUE5_DISPLAY_VALUE5", data.getValue5().getDisplayName().getValue());
				assertNull(data.getValue5().getDisplayName().getNullFlavor());
				assertEquals("VALUE5_ORIG_TXT_VALUE5", data.getValue5().getOriginalText().getValue());
				assertNull(data.getValue5().getOriginalText().getNullFlavor());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data);
				assertNull(data.getValue5().getCode());
				
				assertNull(data.getValue5().getCodeSystem());
				assertNull(data.getValue5().getCodeSystemName());
				assertNull(data.getValue5().getCodeSystemVersion());
				assertNull(data.getValue5().getDisplayName());
				assertNull(data.getValue5().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue5().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue6(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 45th record
			if((index == null && counter == 45) || (index != null && index.contains("45")))
			{
				if(index != null) 
					index.remove("45");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE61", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE1", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertNull(data.getValue6().getCodeSystem());
				assertNull(data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE1", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertNull(data.getValue6().getOriginalText().getValue());
				assertEquals(NullFlavor.NI, data.getValue6().getOriginalText().getNullFlavor());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 46th record
			else if((index == null && counter == 46) || (index != null && index.contains("46")))
			{
				if(index != null) 
					index.remove("46");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE62", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE2", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertNull(data.getValue6().getCodeSystem());
				assertNull(data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE2", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE1", data.getValue6().getOriginalText().getValue());
				assertNull(data.getValue6().getOriginalText().getNullFlavor());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 47th record
			else if((index == null && counter == 47) || (index != null && index.contains("47")))
			{
				if(index != null) 
					index.remove("47");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE63", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE3", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertNull(data.getValue6().getCodeSystem());
				assertNull(data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE3", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE2", data.getValue6().getOriginalText().getValue());
				assertNull(data.getValue6().getOriginalText().getNullFlavor());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 48th record
			else if((index == null && counter == 48) || (index != null && index.contains("48")))
			{
				if(index != null) 
					index.remove("48");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE64", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE4", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertEquals("CODE_SYSTEM_61", data.getValue6().getCodeSystem());
				assertNull(data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE4", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE3", data.getValue6().getOriginalText().getValue());
				assertNull(data.getValue6().getOriginalText().getNullFlavor());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 49th record
			else if((index == null && counter == 49) || (index != null && index.contains("49")))
			{
				if(index != null) 
					index.remove("49");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE65", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE5", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertEquals("CODE_SYSTEM_62", data.getValue6().getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME_61", data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE5", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE4", data.getValue6().getOriginalText().getValue());
				assertNull(data.getValue6().getOriginalText().getNullFlavor());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 50th record
			else if((index == null && counter == 50) || (index != null && index.contains("50")))
			{
				if(index != null) 
					index.remove("50");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals("CODE66", data.getValue2().getCode());
				assertNotNull(data.getValue6());
				assertEquals("CODE1", data.getValue6().getCode());
				assertNull(data.getValue6().getNullFlavor());
				
				assertEquals("CODE_SYSTEM_63", data.getValue6().getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME_62", data.getValue6().getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION_61", data.getValue6().getCodeSystemVersion());
				assertEquals("VALUE6_DISPLAY_VALUE6", data.getValue6().getDisplayName().getValue());
				assertNull(data.getValue6().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue6().getOriginalText());
				assertNotNull(data.getValue6().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data);
				assertNull(data.getValue6().getCode());
				
				assertNull(data.getValue6().getCodeSystem());
				assertNull(data.getValue6().getCodeSystemName());
				assertNull(data.getValue6().getCodeSystemVersion());
				assertNull(data.getValue6().getDisplayName());
				assertNull(data.getValue6().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue6().getNullFlavor());				
				counter++;
			}
		}
	}

	private void assertValue7(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 32nd record
			if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null) 
					index.remove("32");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE1", data.getValue7().getCode());
				
				assertNull(data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE1", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE1", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());
				
				counter++;
				continue;
			}
			//Validate 33rd record
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null) 
					index.remove("33");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE2", data.getValue7().getCode());
				
				assertNull(data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE2", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE2", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}

			//Validate 34th record
			else if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE3", data.getValue7().getCode());
				
				assertEquals("CODE_SYSTEM_1", data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE3", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE3", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}

			//Validate 35th record
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE4", data.getValue7().getCode());
				
				assertEquals("CODE_SYSTEM_2", data.getValue7().getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME_1", data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE4", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE4", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}

			//Validate 36th record
			else if((index == null && counter == 36) || (index != null && index.contains("36")))
			{
				if(index != null) 
					index.remove("36");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE5", data.getValue7().getCode());
				
				assertEquals("CODE_SYSTEM_3", data.getValue7().getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME_2", data.getValue7().getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION_1", data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE5", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE5", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}

			//Validate 37st record
			else if((index == null && counter == 37) || (index != null && index.contains("37")))
			{
				if(index != null) 
					index.remove("37");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE6", data.getValue7().getCode());
				
				assertNull(data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE6", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertNull(data.getValue7().getOriginalText().getValue());
				assertEquals(NullFlavor.NI, data.getValue7().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}

			//Validate 38th record
			else if((index == null && counter == 38) || (index != null && index.contains("38")))
			{
				if(index != null) 
					index.remove("38");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE7", data.getValue7().getCode());
				
				assertNull(data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertNull(data.getValue7().getDisplayName().getValue());
				assertEquals(NullFlavor.NI, data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE7", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}

			//Validate 39th record
			else if((index == null && counter == 39) || (index != null && index.contains("39")))
			{
				if(index != null) 
					index.remove("39");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals("CODE8", data.getValue7().getCode());
				
				assertEquals("CODE_SYSTEM_4", data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertEquals("VALUE7_DISPLAY_VALUE8", data.getValue7().getDisplayName().getValue());
				assertNull(data.getValue7().getDisplayName().getNullFlavor());
				assertEquals("ORIG_TXT_VALUE8", data.getValue7().getOriginalText().getValue());
				assertNull(data.getValue7().getOriginalText().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data);
				assertNull(data.getValue7().getCode());
				
				assertNull(data.getValue7().getCodeSystem());
				assertNull(data.getValue7().getCodeSystemName());
				assertNull(data.getValue7().getCodeSystemVersion());
				assertNull(data.getValue7().getDisplayName());
				assertNull(data.getValue7().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue7().getNullFlavor());				counter++;
			}
		}
	}

	private void assertValue8(Collection<CdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(CdDataType data : result)
		{
			//Validate 40th record
			if((index == null && (counter >= 40 && counter <= 44)) || (index != null && index.contains("40")))
			{
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNull(data.getValue8().getNullFlavor());
				
				assertEquals("CODE1", data.getValue8().getCode());
				assertNull(data.getValue8().getCodeSystem());
				assertNull(data.getValue8().getCodeSystemName());
				assertNull(data.getValue8().getCodeSystemVersion());
				assertEquals("VALUE8_DISPLAY_VALUE1", data.getValue8().getDisplayName().getValue());
				assertNull(data.getValue8().getDisplayName().getNullFlavor());
				assertNull(data.getValue8().getOriginalText().getValue());
				assertEquals(NullFlavor.NI, data.getValue8().getOriginalText().getNullFlavor());
				counter++;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNull(data.getValue8().getCode());
				
				assertNull(data.getValue8().getCodeSystem());
				assertNull(data.getValue8().getCodeSystemName());
				assertNull(data.getValue8().getCodeSystemVersion());
				assertNull(data.getValue8().getDisplayName());
				assertNull(data.getValue8().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue8().getNullFlavor());				
				counter++;
			}
		}
	}

}
