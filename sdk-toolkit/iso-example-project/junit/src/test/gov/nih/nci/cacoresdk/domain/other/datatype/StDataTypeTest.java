package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.StDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for St (ST)
 */
public class StDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "St Datatype Test Case";
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
		StDataType searchObject = new StDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.StDataType",searchObject );
		assertNotNull(results);
		assertEquals(16,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
		assertEquals(16,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<StDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
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
	public void testStValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.StDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<StDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
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
	public void testStValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StDataType.class);
		criteria.add(Property.forName("value2.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<StDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
		assertEquals(10, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.StDataType a where a.value2.value is not null order by a.id asc asc");
		Collection<StDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
		assertEquals(10, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		assertValue2(result, index);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testStValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(StDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<StDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.StDataType");
		assertValue1(result, null);
		assertValue2(result, null);
	}

	
	private void assertValue1(Collection<StDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(StDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertEquals("VALUE1_VALUE1", data.getValue1().getValue());

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

				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertEquals("VALUE1_VALUE2", data.getValue1().getValue());

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

				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertEquals("VALUE1_VALUE3", data.getValue1().getValue());

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

				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertEquals("VALUE1_VALUE4", data.getValue1().getValue());

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

				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertEquals("VALUE1_VALUE5", data.getValue1().getValue());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<StDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(StDataType data : result)
		{
			//Validate 7th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE1", data.getValue2().getValue());

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

				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE2", data.getValue2().getValue());

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

				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE3", data.getValue2().getValue());

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

				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE4", data.getValue2().getValue());

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

				assertNull(data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE5", data.getValue2().getValue());

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

				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE1", data.getValue2().getValue());

				counter++;
				continue;
			}
			//Validate 13th record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE2", data.getValue2().getValue());

				counter++;
				continue;
			}
			//Validate 14th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE3", data.getValue2().getValue());

				counter++;
				continue;
			}
			//Validate 15th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE4", data.getValue2().getValue());

				counter++;
				continue;
			}
			//Validate 16th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertEquals("VALUE2_VALUE5", data.getValue2().getValue());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue2().getValue());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}
	
}
