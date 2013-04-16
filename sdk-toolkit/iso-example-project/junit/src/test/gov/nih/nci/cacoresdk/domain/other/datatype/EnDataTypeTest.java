/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType;
import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for En ( EN ) data type
 */
public class EnDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "En Datatype Test Case";
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	public void testQueryRowCount() throws ApplicationException
	{
		EnDataType searchObject = new EnDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType",searchObject );
		assertNotNull(results);
		assertEquals(45,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		assertEquals(45,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
		criteria.add(Property.forName("value1.part_0.value").like("%John%"));

		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	public void testEnValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("%John%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where a.value1.part_0.value like ? order by a.id asc", params);
		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
		criteria.add(Property.forName("value2.part_0.value").like("%John%"));

		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("%John%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where a.value2.part_0.value like ? order by a.id asc", params);
		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
		criteria.add(Property.forName("value3.part_0.value").like("%Sarah%"));

		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue3ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("%Sarah%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where a.value3.part_0.value like ? order by a.id asc", params);
		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
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
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue4ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
		criteria.add(Restrictions.or(Property.forName("value4.part_0.value").isNotNull(), Property.forName("value4.part_0.qualifier").isNotNull()));

		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		indexList.add("21");
		assertValue4(result, indexList);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnValue4ByHQLCriteria() throws ApplicationException
	{
		@SuppressWarnings("unused")
		List params = new ArrayList(1);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where (a.value4.part_0.value is not null or a.value4.part_0.qualifier is not null)  order by a.id asc");
		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		indexList.add("21");
		assertValue4(result, indexList);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
//	public void testEnValue5ByDetachedCriteria() throws ApplicationException
//	{
//		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
//		criteria.add(Restrictions.or(Property.forName("value5.part_0.value").isNotNull(), Property.forName("value5.part_1.value").isNotNull()));
//
//		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
//		assertNotNull(result);
//		assertEquals(12, result.size());
//		List indexList = new ArrayList();
//		indexList.add("22");
//		indexList.add("23");
//		indexList.add("24");
//		indexList.add("25");
//		indexList.add("26");
//		indexList.add("27");
//		indexList.add("28");
//		indexList.add("29");
//		indexList.add("30");
//		indexList.add("31");
//		indexList.add("32");
//		indexList.add("33");
//		assertValue5(result, indexList);
//	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
//	@SuppressWarnings("unchecked")
//	public void testEnValue5ByHQLCriteria() throws ApplicationException
//	{
//		@SuppressWarnings("unused")
//		List params = new ArrayList(1);
//		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where (a.value5.part_0.value is not null or a.value5.part_1.value is not null)  order by a.id asc");
//		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
//		assertEquals(12, result.size());
//		List indexList = new ArrayList();
//		indexList.add("22");
//		indexList.add("23");
//		indexList.add("24");
//		indexList.add("25");
//		indexList.add("26");
//		indexList.add("27");
//		indexList.add("28");
//		indexList.add("29");
//		indexList.add("30");
//		indexList.add("31");
//		indexList.add("32");
//		indexList.add("33");
//		assertValue5(result, indexList);
//	}

	/**
	 * Search Value6 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
//	@SuppressWarnings("unchecked")
//	public void testEnValue6ByDetachedCriteria() throws ApplicationException
//	{
//		DetachedCriteria criteria = DetachedCriteria.forClass(EnDataType.class);
//		criteria.add(Restrictions.or(Property.forName("value6.part_0.value").isNotNull(), Property.forName("value6.part_1.value").isNotNull()));
//
//		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
//		assertNotNull(result);
//		assertEquals(12, result.size());
//		List indexList = new ArrayList();
//		indexList.add("34");
//		indexList.add("35");
//		indexList.add("36");
//		indexList.add("37");
//		indexList.add("38");
//		indexList.add("39");
//		indexList.add("40");
//		indexList.add("41");
//		indexList.add("42");
//		indexList.add("43");
//		indexList.add("44");
//		indexList.add("45");
//		assertValue6(result, indexList);
//	}

	/**
	 * Search Value6 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
//	@SuppressWarnings("unchecked")
//	public void testEnValue6ByHQLCriteria() throws ApplicationException
//	{
//		@SuppressWarnings("unused")
//		List params = new ArrayList(1);
//		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType a where (a.value6.part_0.value is not null or a.value6.part_1.value is not null)  order by a.id asc");
//		Collection<EnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
//		assertEquals(12, result.size());
//		List indexList = new ArrayList();
//		indexList.add("34");
//		indexList.add("35");
//		indexList.add("36");
//		indexList.add("37");
//		indexList.add("38");
//		indexList.add("39");
//		indexList.add("40");
//		indexList.add("41");
//		indexList.add("42");
//		indexList.add("43");
//		indexList.add("44");
//		indexList.add("45");
//		assertValue6(result, indexList);
//	}
/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
//	@SuppressWarnings("unchecked")
//	public void testEnValue() throws ApplicationException
//	{
//		EnDataType searchObject = new EnDataType();
//		Collection<EnDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType",searchObject );
//		assertValue1(result, null);
//		assertValue2(result, null);
//		assertValue3(result, null);
//		assertValue4(result, null);
//		assertValue5(result, null);
//		assertValue6(result, null);
//	}
	
	private void assertValue1(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr.", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
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
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Double Jr.", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
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
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Sr.", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
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
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Super Sr.", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
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
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("Mr. John Doe II", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertEquals(NullFlavor.NA, data.getValue1().getNullFlavor());
				assertNotNull(data.getValue1().getPart());
				assertEquals(0, data.getValue1().getPart().size());
				counter++;
			}
		}
	}

	private void assertValue1Constants(EnDataType data)
	{
		assertNull(data.getValue1().getNullFlavor());
		assertNull(data.getValue1().getPart().get(0).getCode());
		//Global constant
		assertEquals("ENXP Code System", data.getValue1().getPart().get(0).getCodeSystem());
		//Global constant
		assertEquals("ENXP Code System Version", data.getValue1().getPart().get(0).getCodeSystemVersion());
		assertNull(data.getValue1().getPart().get(0).getQualifier());
		assertNotNull(data.getValue1().getPart().get(0).getType());
	}
	
	private void assertValue2(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				
				assertNull(data.getValue2().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr1.", data.getValue2().getPart().get(0).getValue());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				
				assertNull(data.getValue2().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr2.", data.getValue2().getPart().get(0).getValue());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				
				assertEquals("JDJ3", data.getValue2().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr3.", data.getValue2().getPart().get(0).getValue());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				
				assertEquals("JDJ4", data.getValue2().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr4.", data.getValue2().getPart().get(0).getValue());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				
				assertEquals("JDJ5", data.getValue2().getPart().get(0).getCode());
				assertEquals("Mr. John Doe Jr5.", data.getValue2().getPart().get(0).getValue());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getPart());
				assertEquals(0, data.getValue2().getPart().size());
				assertEquals(NullFlavor.UNK, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2Constants(EnDataType data)
	{
		//Global constant
		assertEquals("ENXP Code System", data.getValue2().getPart().get(0).getCodeSystem());
		//Global constant
		assertEquals("ENXP Code System Version", data.getValue2().getPart().get(0).getCodeSystemVersion());
		assertNull(data.getValue2().getPart().get(0).getQualifier());
		assertNotNull(data.getValue2().getPart().get(0).getType());
		assertNull(data.getValue2().getNullFlavor());
	}
	
	private void assertValue3(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				
				assertNull(data.getValue3().getPart().get(0).getCode());
				assertEquals("Mrs. Sarah Doe I", data.getValue3().getPart().get(0).getValue());
				assertNull(data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getPart().get(0).getCodeSystemVersion());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				
				assertNull(data.getValue3().getPart().get(0).getCode());
				assertEquals("Mrs. Sarah Doe II", data.getValue3().getPart().get(0).getValue());
				assertNull(data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getPart().get(0).getCodeSystemVersion());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				
				assertEquals("MSD1", data.getValue3().getPart().get(0).getCode());
				assertEquals("Mrs. Sarah Doe III", data.getValue3().getPart().get(0).getValue());
				assertNull(data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getPart().get(0).getCodeSystemVersion());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				
				assertEquals("MSD2", data.getValue3().getPart().get(0).getCode());
				assertEquals("Mrs. Sarah Doe IV", data.getValue3().getPart().get(0).getValue());
				assertEquals("VALUE3_PN_CODE_SYSTEM1", data.getValue3().getPart().get(0).getCodeSystem());
				assertNull(data.getValue3().getPart().get(0).getCodeSystemVersion());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				
				assertEquals("MSD3", data.getValue3().getPart().get(0).getCode());
				assertEquals("Mrs. Sarah Doe V", data.getValue3().getPart().get(0).getValue());
				assertEquals("VALUE3_PN_CODE_SYSTEM2", data.getValue3().getPart().get(0).getCodeSystem());
				assertEquals("1.3", data.getValue3().getPart().get(0).getCodeSystemVersion());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getPart());
				assertEquals(0, data.getValue3().getPart().size());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3Constants(EnDataType data)
	{
		assertNull(data.getValue3().getPart().get(0).getQualifier());
		assertNotNull(data.getValue3().getPart().get(0).getType());
		assertNull(data.getValue3().getNullFlavor());
	}
	
	@SuppressWarnings("unchecked")
	private void assertValue4(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				
				assertEquals("VALUE4_PN_VALUE1", data.getValue4().getPart().get(0).getValue());
				assertNull(data.getValue4().getPart().get(0).getQualifier());
				assertValue4Constants(data);

				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());

				assertNull(data.getValue4().getPart().get(0).getValue());
				Set qSet = new HashSet();
				qSet.add(EntityNamePartQualifier.BR);
				assertEquals(qSet, data.getValue4().getPart().get(0).getQualifier());
				assertValue4Constants(data);

				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				
				assertEquals("VALUE4_PN_VALUE3", data.getValue4().getPart().get(0).getValue());
				Set qSet = new HashSet();
				qSet.add(EntityNamePartQualifier.BR);
				assertEquals(qSet, data.getValue4().getPart().get(0).getQualifier());
				assertValue4Constants(data);
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				
				assertEquals("VALUE4_PN_VALUE4", data.getValue4().getPart().get(0).getValue());
				Set qSet = new HashSet();
				qSet.add(EntityNamePartQualifier.BR);
				assertEquals(qSet, data.getValue4().getPart().get(0).getQualifier());
				assertValue4Constants(data);
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				
				assertEquals("VALUE4_PN_VALUE1", data.getValue4().getPart().get(0).getValue());
				Set qSet = new HashSet();
				qSet.add(EntityNamePartQualifier.SP);
				qSet.add(EntityNamePartQualifier.BR);
				qSet.add(EntityNamePartQualifier.NB);
				assertEquals(qSet, data.getValue4().getPart().get(0).getQualifier());
				assertValue4Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNotNull(data.getValue4().getPart());
				assertEquals(0, data.getValue4().getPart().size());
				assertEquals(NullFlavor.NI, data.getValue4().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue4Constants(EnDataType data)
	{
		assertNull(data.getValue4().getPart().get(0).getCode());
		//Global constant
		assertEquals("ENXP Code System", data.getValue4().getPart().get(0).getCodeSystem());
		//Global constant
		assertEquals("ENXP Code System Version", data.getValue4().getPart().get(0).getCodeSystemVersion());
		assertNotNull(data.getValue4().getPart().get(0).getType());
		assertNull(data.getValue4().getNullFlavor());
	}
	
	private void assertValue5(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertNull(data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe I", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("JDJ1", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe II", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("JDJ1", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe III", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("JDJ1", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe IV", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());
				assertEquals("2.1", data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}

			if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertNull(data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe V", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("VALUE5_PN2_CODE1", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VI", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("VALUE5_PN2_CODE2", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VII", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN2_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				
				assertEquals("VALUE5_PN2_CODE3", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VIII", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN2_CODE_SYSTEM2", data.getValue5().getPart().get(0).getCodeSystem());
				assertEquals("2.2", data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(1));
				
				assertNull(data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe I", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());

				assertNull(data.getValue5().getPart().get(1).getCode());
				assertEquals("Mr. John Doe V", data.getValue5().getPart().get(1).getValue());
				assertNull(data.getValue5().getPart().get(1).getCodeSystem());
				assertNull(data.getValue5().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(1).getQualifier());
				assertNull(data.getValue5().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 31) || (index != null && index.contains("31")))
			{
				if(index != null) 
					index.remove("31");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(1));
				
				assertEquals("VALUE5_PN_CODE1", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe II", data.getValue5().getPart().get(0).getValue());
				assertNull(data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());

				assertEquals("VALUE5_PN2_CODE1", data.getValue5().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VI", data.getValue5().getPart().get(1).getValue());
				assertNull(data.getValue5().getPart().get(1).getCodeSystem());
				assertNull(data.getValue5().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(1).getQualifier());
				assertNull(data.getValue5().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 32) || (index != null && index.contains("32")))
			{
				if(index != null) 
					index.remove("32");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(1));
				
				assertEquals("VALUE5_PN_CODE2", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe III", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN_CODE_SYSTEM1", data.getValue5().getPart().get(0).getCodeSystem());
				assertNull(data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());

				assertEquals("VALUE5_PN2_CODE2", data.getValue5().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VII", data.getValue5().getPart().get(1).getValue());
				assertEquals("VALUE5_PN2_CODE_SYSTEM1", data.getValue5().getPart().get(1).getCodeSystem());
				assertNull(data.getValue5().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(1).getQualifier());
				assertNull(data.getValue5().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 33) || (index != null && index.contains("33")))
			{
				if(index != null) 
					index.remove("33");

				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				assertNotNull(data.getValue5().getPart().get(0));
				assertNotNull(data.getValue5().getPart().get(1));
				
				assertEquals("VALUE5_PN_CODE3", data.getValue5().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VI", data.getValue5().getPart().get(0).getValue());
				assertEquals("VALUE5_PN_CODE_SYSTEM2", data.getValue5().getPart().get(0).getCodeSystem());
				assertEquals("2.1", data.getValue5().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(0).getQualifier());
				assertNull(data.getValue5().getPart().get(0).getType());
				assertNull(data.getValue5().getNullFlavor());

				assertEquals("VALUE5_PN2_CODE3", data.getValue5().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VIII", data.getValue5().getPart().get(1).getValue());
				assertEquals("VALUE5_PN2_CODE_SYSTEM2", data.getValue5().getPart().get(1).getCodeSystem());
				assertEquals("2.2", data.getValue5().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue5().getPart().get(1).getQualifier());
				assertNull(data.getValue5().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue5());
				assertNotNull(data.getValue5().getPart());
				assertEquals(0, data.getValue5().getPart().size());
				assertEquals(NullFlavor.NI, data.getValue5().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue6(Collection<EnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 34) || (index != null && index.contains("34")))
			{
				if(index != null) 
					index.remove("34");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertNull(data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe I", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 35) || (index != null && index.contains("35")))
			{
				if(index != null) 
					index.remove("35");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_PN_CODE1", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe II", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 36) || (index != null && index.contains("36")))
			{
				if(index != null) 
					index.remove("36");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_PN_CODE2", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe III", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_PN_CODE_SYSTEM1", data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 37) || (index != null && index.contains("37")))
			{
				if(index != null) 
					index.remove("37");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_PN_CODE3", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe IV", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_PN_CODE_SYSTEM2", data.getValue6().getPart().get(0).getCodeSystem());
				assertEquals("3.1", data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}

			if((index == null && counter == 38) || (index != null && index.contains("38")))
			{
				if(index != null) 
					index.remove("38");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertNull(data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe V", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 39) || (index != null && index.contains("39")))
			{
				if(index != null) 
					index.remove("39");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_ON_CODE1", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VI", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 40) || (index != null && index.contains("40")))
			{
				if(index != null) 
					index.remove("40");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_ON_CODE1", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VII", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_ON_CODE_SYSTEM1", data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 41) || (index != null && index.contains("41")))
			{
				if(index != null) 
					index.remove("41");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				
				assertEquals("VALUE6_ON_CODE2", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe VIII", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_ON_CODE_SYSTEM2", data.getValue6().getPart().get(0).getCodeSystem());
				assertEquals("1.1", data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 42) || (index != null && index.contains("42")))
			{
				if(index != null) 
					index.remove("42");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				assertNotNull(data.getValue6().getPart().get(0));
				assertNotNull(data.getValue6().getPart().get(1));
				
				assertNull(data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe I", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());

				assertNull(data.getValue6().getPart().get(1).getCode());
				assertEquals("Mr. John Doe V", data.getValue6().getPart().get(1).getValue());
				assertNull(data.getValue6().getPart().get(1).getCodeSystem());
				assertNull(data.getValue6().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(1).getQualifier());
				assertNull(data.getValue6().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 43) || (index != null && index.contains("43")))
			{
				if(index != null) 
					index.remove("43");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				assertNotNull(data.getValue6().getPart().get(0));
				assertNotNull(data.getValue6().getPart().get(1));
				
				assertEquals("VALUE6_PN_CODE1", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe II", data.getValue6().getPart().get(0).getValue());
				assertNull(data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());

				assertEquals("VALUE6_ON_CODE1", data.getValue6().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VI", data.getValue6().getPart().get(1).getValue());
				assertNull(data.getValue6().getPart().get(1).getCodeSystem());
				assertNull(data.getValue6().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(1).getQualifier());
				assertNull(data.getValue6().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 44) || (index != null && index.contains("44")))
			{
				if(index != null) 
					index.remove("44");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				assertNotNull(data.getValue6().getPart().get(0));
				assertNotNull(data.getValue6().getPart().get(1));
				
				assertEquals("VALUE6_PN_CODE2", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe III", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_PN_CODE_SYSTEM2", data.getValue6().getPart().get(0).getCodeSystem());
				assertNull(data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());

				assertEquals("VALUE6_ON_CODE2", data.getValue6().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VII", data.getValue6().getPart().get(1).getValue());
				assertEquals("VALUE6_ON_CODE_SYSTEM2", data.getValue6().getPart().get(1).getCodeSystem());
				assertNull(data.getValue6().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(1).getQualifier());
				assertNull(data.getValue6().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			else if((index == null && counter == 45) || (index != null && index.contains("45")))
			{
				if(index != null) 
					index.remove("45");

				assertNotNull(data);
				assertNotNull(data.getValue6());
				assertNotNull(data.getValue6().getPart());
				assertNotNull(data.getValue6().getPart().get(0));
				assertNotNull(data.getValue6().getPart().get(1));
				
				assertEquals("VALUE6_PN_CODE3", data.getValue6().getPart().get(0).getCode());
				assertEquals("Mr. John Doe IV", data.getValue6().getPart().get(0).getValue());
				assertEquals("VALUE6_PN_CODE_SYSTEM3", data.getValue6().getPart().get(0).getCodeSystem());
				assertEquals("2.1", data.getValue6().getPart().get(0).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(0).getQualifier());
				assertNull(data.getValue6().getPart().get(0).getType());
				assertNull(data.getValue6().getNullFlavor());

				assertEquals("VALUE6_ON_CODE3", data.getValue6().getPart().get(1).getCode());
				assertEquals("Mr. John Doe VIII", data.getValue6().getPart().get(1).getValue());
				assertEquals("VALUE6_ON_CODE_SYSTEM3", data.getValue6().getPart().get(1).getCodeSystem());
				assertEquals("1.1", data.getValue6().getPart().get(1).getCodeSystemVersion());
				assertNull(data.getValue6().getPart().get(1).getQualifier());
				assertNull(data.getValue6().getPart().get(1).getType());
				
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue6().getPart());
				assertEquals(0, data.getValue6().getPart().size());
				assertEquals(NullFlavor.NI, data.getValue6().getNullFlavor());
				counter++;
			}
		}
	}
	
	public void testEnValue5ByQBE() throws Exception {
		gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType testClass = new gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType();
		En en0 = new En();
		Enxp enxpfam2 = new Enxp();
		enxpfam2.setValue("Mr. John Doe I");
		enxpfam2.setType(EntityNamePartType.FAM);
		en0.addPart(enxpfam2);
		testClass.setValue5(en0);
		Collection<gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType> result = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType", testClass);
		gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType testResultClass = result.iterator().next();
		assertEquals(2, result.size());
		assertEquals("Mr. John Doe I", testResultClass.getValue5().getPart().get(0).getValue().toString());
	}
	
}
