package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.Any;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for DsetAd ( DSET<AD> ) data type
 */
public class DsetAdDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset Ad Datatype Test Case";
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertEquals(62,count);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value1.item item  where  item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
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
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value2.item item  where (item.part_0.code is not null or item.part_0.value is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
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
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value3.item item  where  (item.part_0.value is not null or item.part_0.code is not null or item.part_0.codeSystem is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
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
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value4.item item  where  (item.part_0.value is not null or item.part_1.value is not null) order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(9, result.size());
		List indexList = new ArrayList();
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		indexList.add("21");
		indexList.add("22");
		indexList.add("23");
		indexList.add("24");
		indexList.add("25");
		assertValue4(result, indexList);
	}
	
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value5.item item  where item.part_0.value is not null order by dest.id asc");
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(10, result.size());
		List indexList = new ArrayList();
		indexList.add("26");
		indexList.add("27");
		indexList.add("28");
		indexList.add("29");
		indexList.add("30");
		indexList.add("31");
		indexList.add("32");
		indexList.add("33");
		indexList.add("34");
		indexList.add("35");
		assertValue5(result, indexList);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value6.item item  where  (item.part_0.value is not null or item.part_1.value is not null or item.part_2.value is not null) order by dest.id asc");
		
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(12, result.size());
		List indexList = new ArrayList();
		indexList.add("36");
		indexList.add("37");
		indexList.add("38");
		indexList.add("39");
		indexList.add("40");
		indexList.add("41");
		indexList.add("42");
		indexList.add("43");
		indexList.add("44");
		indexList.add("45");
		indexList.add("46");
		indexList.add("47");
//		assertValue6(result, indexList);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue7ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value7.item item  where item.part_0.value is not null order by dest.id asc");
		
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(10, result.size());
		List indexList = new ArrayList();
		indexList.add("48");
		indexList.add("49");
		indexList.add("50");
		indexList.add("51");
		indexList.add("52");
		indexList.add("53");
		indexList.add("54");
		indexList.add("55");
		indexList.add("56");
		indexList.add("57");
		assertValue7(result, indexList);
	}

	
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue8ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("select distinct dest from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dest inner join dest.value8.item item  where item.part_0.value is not null order by dest.id asc");
		
		Collection<DsetAdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		assertNotNull(result);
		assertEquals(4, result.size());
		List indexList = new ArrayList();
		indexList.add("58");
		indexList.add("59");
		indexList.add("60");
		indexList.add("61");
		assertValue8(result, indexList);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetAdValue() throws ApplicationException
	{
		DsetAdDataType searchObject = new DsetAdDataType();
		Collection<DsetAdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
		assertValue5(result, null);
		//assertValue6(result, null);
		assertValue7(result, null);
		assertValue8(result, null);
	}
	
	private void assertValue1(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");
				
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getItem());
				Ad address = data.getValue1().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("1 Jefferson Street", ad.getValue());
				assertEquals("CODE", ad.getCode());
				assertEquals("CODESYSTEM", ad.getCodeSystem());
				
				assertNull(data.getValue1().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue1().getItem());
				Ad address = data.getValue1().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("2 Jefferson Street", ad.getValue());
				assertEquals("CODE", ad.getCode());
				assertEquals("CODESYSTEM", ad.getCodeSystem());
				assertNull(data.getValue1().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue1().getItem());
				Ad address = data.getValue1().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("3 Jefferson Street", ad.getValue());
				assertEquals("CODE", ad.getCode());
				assertEquals("CODESYSTEM", ad.getCodeSystem());
				assertNull(data.getValue1().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue1().getItem());
				Ad address = data.getValue1().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("4 Sun Street", ad.getValue());
				assertEquals("CODE", ad.getCode());
				assertEquals("CODESYSTEM", ad.getCodeSystem());
				assertNull(data.getValue1().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue1().getItem());
				Ad address = data.getValue1().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("5 Sun Street", ad.getValue());
				assertEquals("CODE", ad.getCode());
				assertEquals("CODESYSTEM", ad.getCodeSystem());
				assertNull(data.getValue1().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getItem());
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");
				
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getItem());
				Ad address = data.getValue2().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.DAL, ad.getType());
				assertEquals("Suite 100", ad.getValue());
				assertNull(ad.getCode());
				assertEquals("ADXP Code System", ad.getCodeSystem());
				assertNull(data.getValue2().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue2().getItem());
				Ad address = data.getValue2().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.DAL, ad.getType());
				assertEquals("Suite 101", ad.getValue());
				assertNull(ad.getCode());
				assertEquals("ADXP Code System", ad.getCodeSystem());
				assertNull(data.getValue2().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue2().getItem());
				Ad address = data.getValue2().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNull(ad.getValue());

				assertEquals(AddressPartType.DAL, ad.getType());
				assertEquals("CODE1", ad.getCode());
				assertNull(ad.getValue());
				assertEquals("ADXP Code System", ad.getCodeSystem());
				assertNull(data.getValue2().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue2().getItem());
				Ad address = data.getValue2().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.DAL, ad.getType());
				assertEquals("Suite 103", ad.getValue());
				assertEquals("CODE2", ad.getCode());
				assertEquals("ADXP Code System", ad.getCodeSystem());
				assertNull(data.getValue2().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue2().getItem());
				Ad address = data.getValue2().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.DAL, ad.getType());
				assertEquals("Suite 104", ad.getValue());
				assertEquals("CODE3", ad.getCode());
				assertEquals("ADXP Code System", ad.getCodeSystem());
				assertNull(data.getValue2().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");
				
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getItem());
				Ad address = data.getValue3().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("1 Jefferson Street", ad.getValue());
				assertNull(ad.getCode());
				assertNull(ad.getCodeSystem());
				assertNull(data.getValue3().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue3().getItem());
				Ad address = data.getValue3().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("2 Jefferson Street", ad.getValue());
				assertEquals("CODE1", ad.getCode());
				assertNull(ad.getCodeSystem());
				assertNull(data.getValue3().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue3().getItem());
				Ad address = data.getValue3().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("CODE2", ad.getCode());
				assertNull(ad.getValue());
				assertNull(ad.getCodeSystem());
				assertNull(data.getValue3().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue3().getItem());
				Ad address = data.getValue3().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("3 Jefferson Street", ad.getValue());
				assertEquals("CODE3", ad.getCode());
				assertNull(ad.getCodeSystem());
				assertNull(data.getValue3().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue3().getItem());
				Ad address = data.getValue3().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad = address.getPart().get(0);
				assertNotNull(ad.getType());
				assertNotNull(ad.getValue());

				assertEquals(AddressPartType.AL, ad.getType());
				assertEquals("4 Jefferson Street", ad.getValue());
				assertEquals("CODE4", ad.getCode());
				assertEquals("CODE_SYSTEM", ad.getCodeSystem());
				assertNull(data.getValue3().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue4(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");
				
				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("101 Jefferson Street", ad0.getValue());
				assertEquals("NCI101", ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("102 Jefferson Street", ad0.getValue());
				assertEquals("NCI102", ad0.getCode());
				assertEquals("ADXP_AL1_CODESYSTEM1", ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("200 Executive Blvd", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("201 Executive Blvd", ad0.getValue());
				assertEquals("NCI201", ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("202 Executive Blvd", ad0.getValue());
				assertEquals("NCI202", ad0.getCode());
				assertEquals("ADXP_AL2_CODESYSTEM1", ad0.getCodeSystem());
				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
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
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertEquals(AddressPartType.AL, ad1.getType());
				assertEquals("200 Executive Blvd", ad1.getValue());
				assertNull(ad1.getCode());
				assertNull(ad1.getCodeSystem());

				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("101 Jefferson Street", ad0.getValue());
				assertEquals("NCI101", ad0.getCode());
				assertNull(ad0.getCodeSystem());
				assertEquals(AddressPartType.AL, ad1.getType());
				assertEquals("201 Executive Blvd", ad1.getValue());
				assertEquals("NCI201", ad1.getCode());
				assertNull(ad1.getCodeSystem());

				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getItem());
				Ad address = data.getValue4().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("102 Jefferson Street", ad0.getValue());
				assertEquals("NCI102", ad0.getCode());
				assertEquals("ADXP_AL1_CODESYSTEM1", ad0.getCodeSystem());
				assertEquals(AddressPartType.AL, ad1.getType());
				assertEquals("202 Executive Blvd", ad1.getValue());
				assertEquals("NCI202", ad1.getCode());
				assertEquals("ADXP_AL2_CODESYSTEM1", ad1.getCodeSystem());

				assertNull(data.getValue4().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals(NullFlavor.NI, data.getValue4().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue5(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");
				
				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("101 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL101", ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("102 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL102", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM1", ad0.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNull(ad1.getValue());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("103 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL103", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM2", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertNull(ad1.getValue());
				assertEquals("NCIDAL100", ad1.getCode());
				assertNull(ad1.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNull(ad1.getValue());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("104 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL104", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM3", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertNull(ad1.getValue());
				assertEquals("NCIDAL101", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM1", ad1.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 31) || (index != null && index.contains("31")))
			{
				if(index != null) 
					index.remove("31");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("105 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL105", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM4", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 500", ad1.getValue());
				assertEquals("NCIDAL102", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM2", ad1.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null) 
					index.remove("32");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("106 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL106", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM5", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 501", ad1.getValue());
				assertEquals("NCIDAL103", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM3", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertNull(ad2.getCode());
				assertNull(ad2.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null) 
					index.remove("33");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("107 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL107", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM6", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 502", ad1.getValue());
				assertEquals("NCIDAL104", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM4", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertNull(ad2.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("108 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL108", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM7", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 503", ad1.getValue());
				assertEquals("NCIDAL105", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertEquals("RCK_CODE_SYS", ad2.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getItem());
				Ad address = data.getValue5().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("108 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL108", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM7", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 503", ad1.getValue());
				assertEquals("NCIDAL105", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertEquals("RCK_CODE_SYS", ad2.getCodeSystem());

				assertNull(data.getValue5().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertEquals(NullFlavor.NI, data.getValue5().getNullFlavor());
				counter++;
			}
		}
	}
/*
	private void assertValue6(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");
				
				System.out.println("************" + "29");
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
					System.out.println("************" + i);
					System.out.println("************" + typeValue);
					System.out.println("************" + codeName);
					System.out.println("************" + data.getValue6().getPart().get(i));
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
					{
						System.out.println("++************" + data.getValue6().getPart());
						System.out.println("++************" + data.getValue6().getPart().get(i));
						assertNull(data.getValue6().getPart().get(i).getCode());
						System.out.println("++************" + data.getValue6().getPart().get(i).getCode());

					}
					
					if(type.equals(AddressPartType.BNS) ||
							type.equals(AddressPartType.DAL) ||
							type.equals(AddressPartType.INT) ||
							type.equals(AddressPartType.STB) ||
							type.equals(AddressPartType.ZIP))
						assertEquals(codeSystem, data.getValue6().getPart().get(i).getCodeSystem());
					else
						assertNull(data.getValue6().getPart().get(i).getCodeSystem());
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
						assertNull(data.getValue6().getPart().get(i).getCodeSystem());
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
						assertNull(data.getValue6().getPart().get(i).getCodeSystem());
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
						assertNull(data.getValue6().getPart().get(i).getCodeSystem());
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
						assertNull(data.getValue6().getPart().get(i).getCodeSystem());
				}
				assertNull(data.getValue6().getNullFlavor());
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
*/
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

	private void assertValue7(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 49) || (index != null && index.contains("48")))
			{
				if(index != null) 
					index.remove("48");
				
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 50) || (index != null && index.contains("49")))
			{
				if(index != null) 
					index.remove("49");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("101 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL101", ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 51) || (index != null && index.contains("50")))
			{
				if(index != null) 
					index.remove("50");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("102 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL102", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM1", ad0.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 52) || (index != null && index.contains("51")))
			{
				if(index != null) 
					index.remove("51");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("103 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL103", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM2", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertNull(ad1.getValue());
				assertEquals("NCIDAL101", ad1.getCode());
				assertNull(ad1.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 53) || (index != null && index.contains("52")))
			{
				if(index != null) 
					index.remove("52");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("104 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL104", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM3", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertNull(ad1.getValue());
				assertEquals("NCIDAL102", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());
				
				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 54) || (index != null && index.contains("53")))
			{
				if(index != null) 
					index.remove("53");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("105 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL105", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM4", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 501", ad1.getValue());
				assertEquals("NCIDAL103", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 55) || (index != null && index.contains("54")))
			{
				if(index != null) 
					index.remove("54");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("106 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL106", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM5", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 502", ad1.getValue());
				assertEquals("NCIDAL104", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertNull(ad2.getCode());
				assertNull(ad2.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 56) || (index != null && index.contains("55")))
			{
				if(index != null) 
					index.remove("55");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("107 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL107", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM6", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 503", ad1.getValue());
				assertEquals("NCIDAL105", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertNull(ad2.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 57) || (index != null && index.contains("56")))
			{
				if(index != null) 
					index.remove("56");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("108 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL108", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM7", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 504", ad1.getValue());
				assertEquals("NCIDAL106", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertEquals("RCK_CODE_SYS", ad2.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 58) || (index != null && index.contains("57")))
			{
				if(index != null) 
					index.remove("57");

				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertNotNull(data.getValue7().getItem());
				Ad address = data.getValue7().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertNotNull(address.getPart().get(1));
				Adxp ad1 = address.getPart().get(1);
				assertNotNull(ad1.getType());
				assertNotNull(ad1.getValue());
				
				assertNotNull(address.getPart().get(2));
				Adxp ad2 = address.getPart().get(2);
				assertNotNull(ad2.getType());
				assertNotNull(ad2.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("109 Jefferson Street", ad0.getValue());
				assertEquals("NCIAL108", ad0.getCode());
				assertEquals("ADXP_AL_CODESYSTEM8", ad0.getCodeSystem());

				assertEquals(AddressPartType.DAL, ad1.getType());
				assertEquals("Suite 505", ad1.getValue());
				assertEquals("NCIDAL107", ad1.getCode());
				assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

				assertEquals(AddressPartType.CTY, ad2.getType());
				assertEquals("Rockville", ad2.getValue());
				assertEquals("RCK", ad2.getCode());
				assertEquals("RCK_CODE_SYS", ad2.getCodeSystem());

				assertNull(data.getValue7().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue7());
				assertEquals(NullFlavor.NI, data.getValue7().getNullFlavor());
				counter++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void assertValue8(Collection<DsetAdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetAdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 59) || (index != null && index.contains("58")))
			{
				if(index != null) 
					index.remove("58");
				
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getItem());
				Ad address = data.getValue8().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue8().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 60) || (index != null && index.contains("59")))
			{
				if(index != null) 
					index.remove("59");

				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getItem());
				Ad address = data.getValue8().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue8().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 61) || (index != null && index.contains("60")))
			{
				if(index != null) 
					index.remove("60");

				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getItem());
				Ad address = data.getValue8().getItem().iterator().next();
				assertNotNull(address.getPart().get(0));
				Adxp ad0 = address.getPart().get(0);
				assertNotNull(ad0.getType());
				assertNotNull(ad0.getValue());

				assertEquals(AddressPartType.AL, ad0.getType());
				assertEquals("100 Jefferson Street", ad0.getValue());
				assertNull(ad0.getCode());
				assertNull(ad0.getCodeSystem());

				assertNull(data.getValue8().getItem().iterator().next().getNullFlavor());
				counter++;
				continue;
			}
			else if((index == null && counter == 62) || (index != null && index.contains("61")))
			{
				if(index != null) 
					index.remove("61");

				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertNotNull(data.getValue8().getItem());
				Set<Ad> items = data.getValue8().getItem();

				
					Ad address = getAddress("106 Jefferson Street", items);
					assertNotNull(address.getPart().get(0));
					Adxp ad0 = address.getPart().get(0);
					assertNotNull(ad0.getType());
					assertNotNull(ad0.getValue());
	
					assertNotNull(address.getPart().get(1));
					Adxp ad1 = address.getPart().get(1);
					assertNotNull(ad1.getType());
					assertNotNull(ad1.getValue());
					
					assertNotNull(address.getPart().get(2));
					Adxp ad2 = address.getPart().get(2);
					assertNotNull(ad2.getType());
					assertNotNull(ad2.getValue());
	
					assertEquals(AddressPartType.AL, ad0.getType());
					assertEquals("106 Jefferson Street", ad0.getValue());
					assertEquals("NCIAL106", ad0.getCode());
					assertEquals("ADXP_AL_CODESYSTEM5", ad0.getCodeSystem());
	
					assertEquals(AddressPartType.DAL, ad1.getType());
					assertEquals("Suite 501", ad1.getValue());
					assertEquals("NCIDAL103", ad1.getCode());
					assertEquals("ADXP_DAL_CODESYSTEM3", ad1.getCodeSystem());
	
					assertEquals(AddressPartType.CTY, ad2.getType());
					assertEquals("Rockville", ad2.getValue());
					assertNull(ad2.getCode());
					assertNull(ad2.getCodeSystem());

					Ad address2 = getAddress("107 Jefferson Street", items);
					assertNotNull(address2.getPart().get(0));
					ad0 = address2.getPart().get(0);
					assertNotNull(ad0.getType());
					assertNotNull(ad0.getValue());

					assertNotNull(address.getPart().get(1));
					ad1 = address2.getPart().get(1);
					assertNotNull(ad1.getType());
					assertNotNull(ad1.getValue());
					
					assertNotNull(address.getPart().get(2));
					ad2 = address2.getPart().get(2);
					assertNotNull(ad2.getType());
					assertNotNull(ad2.getValue());

					assertEquals(AddressPartType.AL, ad0.getType());
					assertEquals("107 Jefferson Street", ad0.getValue());
					assertEquals("NCIAL107", ad0.getCode());
					assertEquals("ADXP_AL_CODESYSTEM6", ad0.getCodeSystem());

					assertEquals(AddressPartType.DAL, ad1.getType());
					assertEquals("Suite 502", ad1.getValue());
					assertEquals("NCIDAL104", ad1.getCode());
					assertEquals("ADXP_DAL_CODESYSTEM4", ad1.getCodeSystem());

					assertEquals(AddressPartType.CTY, ad2.getType());
					assertEquals("Rockville", ad2.getValue());
					assertEquals("RCK", ad2.getCode());
					assertNull(ad2.getCodeSystem());

					Ad address3 = getAddress("108 Jefferson Street", items);
					assertNotNull(address3.getPart().get(0));
					ad0 = address3.getPart().get(0);
					assertNotNull(ad0.getType());
					assertNotNull(ad0.getValue());

					assertNotNull(address.getPart().get(1));
					ad1 = address3.getPart().get(1);
					assertNotNull(ad1.getType());
					assertNotNull(ad1.getValue());
					
					assertNotNull(address.getPart().get(2));
					ad2 = address3.getPart().get(2);
					assertNotNull(ad2.getType());
					assertNotNull(ad2.getValue());

					assertEquals(AddressPartType.AL, ad0.getType());
					assertEquals("108 Jefferson Street", ad0.getValue());
					assertEquals("NCIAL108", ad0.getCode());
					assertEquals("ADXP_AL_CODESYSTEM7", ad0.getCodeSystem());

					assertEquals(AddressPartType.DAL, ad1.getType());
					assertEquals("Suite 503", ad1.getValue());
					assertEquals("NCIDAL105", ad1.getCode());
					assertEquals("ADXP_DAL_CODESYSTEM5", ad1.getCodeSystem());

					assertEquals(AddressPartType.CTY, ad2.getType());
					assertEquals("Rockville", ad2.getValue());
					assertEquals("RCK", ad2.getCode());
					assertEquals("RCK_CODE_SYS", ad2.getCodeSystem());
					
					assertNull(data.getValue8().getItem().iterator().next().getNullFlavor());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue8());
				assertEquals(NullFlavor.NI, data.getValue8().getNullFlavor());
				counter++;
			}
		}
	}
	
	private Ad getAddress(String adxpAlValue, Set<Ad> items)
	{
		for(Ad address : items)
		{
			Adxp adxp = address.getPart().get(0);
			if(adxp.getValue().equals(adxpAlValue))
				return address;
		}
		return null;
	}
	
}
