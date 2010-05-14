package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Sc (SC)data type
 */
public class ScDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Sc Datatype Test Case";
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
		ScDataType searchObject = new ScDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType",searchObject );
		assertNotNull(results);
		assertEquals(23,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(23,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(ScDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		index.add("6");
		assertValue1(result, index);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		index.add("6");
		assertValue1(result, index);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(ScDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value2.value").isNotNull(), Property.forName("value2.code.code").isNotNull());
		criteria.add(Restrictions.or(Property.forName("value2.nullFlavor").isNotNull(), exp1));
		criteria.addOrder(Order.asc("id"));
		
		criteria.addOrder(Order.asc("id"));

		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(6, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		index.add("12");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType a where (a.value2.code.code is not null or a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(6, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		index.add("12");
		assertValue2(result, index);
	}

	
	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(ScDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.value").isNotNull(), Property.forName("value3.code.code").isNotNull());
		criteria.add(Restrictions.or(Property.forName("value3.nullFlavor").isNotNull(), exp1));
		criteria.addOrder(Order.asc("id"));

		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(11, result.size());
		List index = new ArrayList();
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		index.add("17");
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		index.add("23");
		assertValue3(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType a where (a.value3.code.code is not null or a.value3.value is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertEquals(11, result.size());
		List index = new ArrayList();
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		index.add("17");
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		index.add("23");
		assertValue3(result, index);
	}
	
	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testScValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(ScDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<ScDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<ScDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(ScDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals("VALUE1_VALUE1", data.getValue1().getValue());
				assertNotNull(data.getValue1().getCode());
				assertNotNull(data.getValue1().getCode().getNullFlavor());
				//assertValue1Constants(data);

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

				assertEquals("VALUE1_VALUE2", data.getValue1().getValue());
				assertNotNull(data.getValue1().getCode());
				assertNotNull(data.getValue1().getCode().getNullFlavor());
				//assertValue1Constants(data);

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

				assertEquals("VALUE1_VALUE3", data.getValue1().getValue());
				assertNotNull(data.getValue1().getCode());
				assertNotNull(data.getValue1().getCode().getNullFlavor());
				//assertValue1Constants(data);

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

				assertEquals("VALUE1_VALUE4", data.getValue1().getValue());
				assertEquals("CODE1",data.getValue1().getCode().getCode());
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals("VALUE1_VALUE5", data.getValue1().getValue());
				assertEquals("CODE2",data.getValue1().getCode().getCode());
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getCode());
				assertNull(data.getValue1().getValue());
				assertNotNull(data.getValue1().getNullFlavor());
				assertEquals(NullFlavor.NA, data.getValue1().getNullFlavor());
				//assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue1Constants(ScDataType data)
	{
		assertNull(data.getValue1().getNullFlavor());
		
		//Global constant
		assertEquals("CD Code System", data.getValue1().getCode().getCodeSystem());
		//Global constant
		assertEquals("SC CD Code System Name", data.getValue1().getCode().getCodeSystemName());
		//Global constant
		assertEquals("1.1 HF2", data.getValue1().getCode().getCodeSystemVersion());
		assertNotNull(data.getValue1().getCode().getOriginalText());
		assertEquals(NullFlavor.NI, data.getValue1().getCode().getOriginalText().getNullFlavor());
		assertNotNull(data.getValue1().getCode().getDisplayName());
		assertEquals(NullFlavor.NI, data.getValue1().getCode().getDisplayName().getNullFlavor());
	}
	
	private void assertValue2(Collection<ScDataType> result, List<Integer> index)
	{

		assertNotNull(result);

		int counter = 1;
		for(ScDataType data : result)
		{
			//Validate 7th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				
				//From database, overriding global constant
				assertEquals(NullFlavor.UNK, data.getValue2().getNullFlavor());
				assertNull(data.getValue2().getValue());
				assertNull(data.getValue2().getCode());

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
				assertNotNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE1", data.getValue2().getValue());
				assertEquals("VALUE2_CODE_CODE1", data.getValue2().getCode().getCode());
				assertNull(data.getValue2().getCode().getCodeSystem());
				assertNull(data.getValue2().getCode().getCodeSystemName());
				assertNull(data.getValue2().getCode().getCodeSystemVersion());
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getDisplayName().getNullFlavor());

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
				assertNotNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE2", data.getValue2().getValue());
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertEquals("VALUE2_CODE_CODE2", data.getValue2().getCode().getCode());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM1", data.getValue2().getCode().getCodeSystem());
				assertNull(data.getValue2().getCode().getCodeSystemName());
				assertNull(data.getValue2().getCode().getCodeSystemVersion());
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getDisplayName().getNullFlavor());

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
				assertNotNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE3", data.getValue2().getValue());
				assertEquals("VALUE2_CODE_CODE3", data.getValue2().getCode().getCode());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM2", data.getValue2().getCode().getCodeSystem());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM_NAME1", data.getValue2().getCode().getCodeSystemName());
				assertNull(data.getValue2().getCode().getCodeSystemVersion());
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getDisplayName().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 11th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE4", data.getValue2().getValue());
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertEquals("VALUE2_CODE_CODE4", data.getValue2().getCode().getCode());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM3", data.getValue2().getCode().getCodeSystem());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM_NAME2", data.getValue2().getCode().getCodeSystemName());
				//From database, overriding global constant
				assertEquals("1.1", data.getValue2().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue2().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getDisplayName().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 12th record
			else if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNotNull(data.getValue2().getCode());
				
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE5", data.getValue2().getValue());
				//From database, overriding global constant
				assertNull(data.getValue2().getCode().getNullFlavor());
				assertEquals("VALUE2_CODE_CODE5", data.getValue2().getCode().getCode());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM4", data.getValue2().getCode().getCodeSystem());
				//From database, overriding global constant
				assertEquals("VALUE2_CODE_CODE_SYSTEM_NAME3", data.getValue2().getCode().getCodeSystemName());
				//From database, overriding global constant
				assertEquals("1.1", data.getValue2().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue2().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue2().getCode().getDisplayName());
				assertEquals(NullFlavor.NI, data.getValue2().getCode().getDisplayName().getNullFlavor());

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

	private void assertValue3(Collection<ScDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(ScDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getCode());
				
				assertEquals(NullFlavor.NA, data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getValue());

				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE1", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE1", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM1", data.getValue3().getCode().getCodeSystem());
				assertNull(data.getValue3().getCode().getCodeSystemName());
				assertNull(data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getDisplayName().getNullFlavor());
				
				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE2", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE1", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM1", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME1", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER1", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getDisplayName().getNullFlavor());
				
				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE1", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE1", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM1", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME1", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER1", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getDisplayName().getNullFlavor());
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE3", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE1", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM1", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME1", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE", data.getValue3().getCode().getDisplayName().getValue());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE4", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE5", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 8th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE6", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getValue());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE7", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getOriginalText().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE8", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getNullFlavor());
				assertEquals("VALUE3_CODE_ORIG_TXT_VALUE31", data.getValue3().getCode().getOriginalText().getValue());

				counter++;
				continue;
			}
			//Validate 11th record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getCode());
				
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("VALUE3_VALUE9", data.getValue3().getValue());
				assertEquals("VALUE3_CODE_CODE31", data.getValue3().getCode().getCode());
				assertEquals("VALUE3_CODE_CODE_SYSTEM31", data.getValue3().getCode().getCodeSystem());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_NAME31", data.getValue3().getCode().getCodeSystemName());
				assertEquals("VALUE3_CODE_CODE_SYSTEM_VER31", data.getValue3().getCode().getCodeSystemVersion());
				assertNotNull(data.getValue3().getCode().getDisplayName());
				assertNull(data.getValue3().getCode().getDisplayName().getNullFlavor());
				assertEquals("VALUE3_CODE_DISPLAY_VALUE31", data.getValue3().getCode().getDisplayName().getValue());
				assertNotNull(data.getValue3().getCode().getOriginalText());
				assertNull(data.getValue3().getCode().getOriginalText().getNullFlavor());
				assertEquals("VALUE3_CODE_ORIG_TXT_VALUE31", data.getValue3().getCode().getOriginalText().getValue());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getCode().getNullFlavor());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				//Even though NullFlavor is mapped, it would use
				//absolute constant value in case of all other values are null
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}
	
}
