package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType;
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for DsetCd ( DSET<CD> ) data type
 */
public class DsetCdDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset Cd Datatype Test Case";
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
		DsetCdDataType searchObject = new DsetCdDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType",searchObject );
		assertNotNull(results);
		assertEquals(35,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(35,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetCdValue1ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value1.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
*/
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value1.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
/*	public void testDsetCdValue2ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value2.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
	public void testDsetCdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value2.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
/*	public void testDsetCdValue3ByDetachedCriteria() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetCdDataType.class);
		criteria.add(Property.forName("value3.item.code").isNotNull());

		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
	public void testDsetCdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value3.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
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
	public void testDsetCdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value4.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("16");
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value5.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("21");
		indexList.add("22");
		indexList.add("23");
		indexList.add("24");
		indexList.add("25");
		assertValue5(result, indexList);
	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value6.item.code is not null  order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("26");
		indexList.add("27");
		indexList.add("28");
		indexList.add("29");
		indexList.add("30");
		assertValue6(result, indexList);
	}
	

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType a where a.value7.item.code is not null order by a.id asc");
		Collection<DsetCdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("31");
		indexList.add("32");
		indexList.add("33");
		indexList.add("34");
		indexList.add("35");
		assertValue7(result, indexList);
	}
	
	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetCdValue() throws ApplicationException
	{
		DsetCdDataType searchObject = new DsetCdDataType();
		Collection<DsetCdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
		assertValue5(result, null);
		assertValue6(result, null);
		assertValue7(result, null);
	}
	
	private void assertValue1(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				if(index != null) 
					index.remove("1");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				Cd item = data.getValue1().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
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
				Cd item = data.getValue1().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
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
				Cd item = data.getValue1().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
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
				Cd item = data.getValue1().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
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
				Cd item = data.getValue1().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue1());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				Cd item = data.getValue2().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue2().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue2().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue2().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue2().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue2());
				counter++;
			}
		}
	}

	private void assertValue3(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 11th record
			if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				Cd item = data.getValue3().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertEquals("CODE_SYSTEM1", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME1", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue3().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertEquals("CODE_SYSTEM2", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME2", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue3().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertEquals("CODE_SYSTEM3", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME3", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue3().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertEquals("CODE_SYSTEM4", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME4", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
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
				Cd item = data.getValue3().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertEquals("CODE_SYSTEM5", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME5", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				Cd item = data.getValue3().getItem().iterator().next();
				assertNull(item);
				
				assertEquals("CODESYSTEM", item.getCodeSystem());
				assertEquals("CODESYSTEMNAME", item.getCodeSystemName());
				assertEquals("CODESYSTEMVERSION", item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue4(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 16th record
			if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				Cd item = data.getValue4().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertEquals("CODE_SYSTEM1", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME1", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION1", item.getCodeSystemVersion());
				assertEquals("CODE1", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal1", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc1", item.getOriginalText().getDescription().getValue());
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
				Cd item = data.getValue4().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertEquals("CODE_SYSTEM2", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME2", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION2", item.getCodeSystemVersion());
				assertEquals("CODE2", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal2", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc2", item.getOriginalText().getDescription().getValue());
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
				Cd item = data.getValue4().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertEquals("CODE_SYSTEM3", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME3", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION3", item.getCodeSystemVersion());
				assertEquals("CODE3", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal3", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc3", item.getOriginalText().getDescription().getValue());
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
				Cd item = data.getValue4().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertEquals("CODE_SYSTEM4", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME4", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION4", item.getCodeSystemVersion());
				assertEquals("CODE4", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal4", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc4", item.getOriginalText().getDescription().getValue());
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
				Cd item = data.getValue4().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertEquals("CODE_SYSTEM5", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME5", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION5", item.getCodeSystemVersion());
				assertEquals("CODE5", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal5", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc5", item.getOriginalText().getDescription().getValue());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue4());
				counter++;
			}
		}
	}

	private void assertValue5(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 21st record
			if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				Cd item = data.getValue5().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertEquals("CODE_SYSTEM1", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME1", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION1", item.getCodeSystemVersion());
				assertEquals("CODE1", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal1", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc1", item.getOriginalText().getDescription().getValue());
				counter++;
				continue;
			}
			//Validate 22nd record
			else if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				Cd item = data.getValue5().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertEquals("CODE_SYSTEM2", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME2", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION2", item.getCodeSystemVersion());
				assertEquals("CODE2", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal2", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc2", item.getOriginalText().getDescription().getValue());
				counter++;
				continue;
			}
			//Validate 23rd record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				Cd item = data.getValue5().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertEquals("CODE_SYSTEM3", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME3", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION3", item.getCodeSystemVersion());
				assertEquals("CODE3", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal3", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc3", item.getOriginalText().getDescription().getValue());
				counter++;
				continue;
			}
			//Validate 24th record
			else if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				Cd item = data.getValue5().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertEquals("CODE_SYSTEM4", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME4", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION4", item.getCodeSystemVersion());
				assertEquals("CODE4", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal4", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc4", item.getOriginalText().getDescription().getValue());
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
				Cd item = data.getValue5().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertEquals("CODE_SYSTEM5", item.getCodeSystem());
				assertEquals("CODE_SYSTEM_NAME5", item.getCodeSystemName());
				assertEquals("CODE_SYSTEM_VERSION5", item.getCodeSystemVersion());
				assertEquals("CODE5", item.getDisplayName().getValue());
				assertNull(item.getDisplayName().getNullFlavor());
				assertNull(item.getNullFlavor());
				assertEquals("OrgTextVal5", item.getOriginalText().getValue());
				assertEquals("OrgTextDesc5", item.getOriginalText().getDescription().getValue());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue5());
				counter++;
			}
		}
	}

	private void assertValue6(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				Cd item = data.getValue6().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				Cd item = data.getValue6().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				Cd item = data.getValue6().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				Cd item = data.getValue6().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				Cd item = data.getValue6().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue6());
				counter++;
			}
		}
	}

	private void assertValue7(Collection<DsetCdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetCdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 31) || (index != null && index.contains("31")))
			{
				if(index != null) 
					index.remove("31");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				Cd item = data.getValue7().getItem().iterator().next();
				assertEquals("CODE1", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null) 
					index.remove("32");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				Cd item = data.getValue7().getItem().iterator().next();
				assertEquals("CODE2", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null) 
					index.remove("33");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				Cd item = data.getValue7().getItem().iterator().next();
				assertEquals("CODE3", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				Cd item = data.getValue7().getItem().iterator().next();
				assertEquals("CODE4", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				Cd item = data.getValue7().getItem().iterator().next();
				assertEquals("CODE5", item.getCode());
				
				assertNull(item.getCodeSystem());
				assertNull(item.getCodeSystemName());
				assertNull(item.getCodeSystemVersion());
				assertNull(item.getDisplayName());
				assertNull(item.getOriginalText());
				assertNull(item.getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue7());
				counter++;
			}
		}
	}
}
