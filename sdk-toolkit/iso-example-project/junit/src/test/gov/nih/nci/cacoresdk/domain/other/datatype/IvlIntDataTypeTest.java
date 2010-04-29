package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for IvlInt ( IVL<INT> ) data type
 */
public class IvlIntDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl Int Datatype Test Case";
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
		IvlIntDataType searchObject = new IvlIntDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType",searchObject );
		assertNotNull(results);
		assertEquals(21,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertEquals(21,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlIntValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlIntDataType.class);
		criteria.add(Restrictions.or(Property.forName("value1.low.value").isNotNull(), Property.forName("value1.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
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
	public void testIvlIntValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType a where (a.value1.low.value is not null or a.value1.high.value is not null) order by a.id asc asc");
		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
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
	public void testIvlIntValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlIntDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.lowClosed").isNotNull(), Property.forName("value2.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlIntValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType a where (a.value2.high.value is not null or value2.lowClosed is not null) order by a.id asc asc");
		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlIntValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlIntDataType.class);
		criteria.add(Property.forName("value3.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		assertValue3(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlIntValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType a where a.value3.low.value is not null order by a.id asc asc");
		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		assertValue3(result, index);
	}

	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlIntValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlIntDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlIntDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<IvlIntDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IvlIntDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				Int intData = new Int();
				intData.setValue(new Integer(1));
				assertEquals(intData, data.getValue1().getLow());
				assertNull(data.getValue1().getHigh());
				assertValue1Constants(data);

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

				Int intData = new Int();
				intData.setValue(new Integer(10));
				assertEquals(intData, data.getValue1().getHigh());
				assertNull(data.getValue1().getLow());
				assertValue1Constants(data);

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

				Int intData1 = new Int();
				intData1.setValue(new Integer(1));
				assertEquals(intData1, data.getValue1().getLow());
				Int intData2 = new Int();
				intData2.setValue(new Integer(10));
				assertEquals(intData2, data.getValue1().getHigh());
				assertValue1Constants(data);
				
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

				Int intData1 = new Int();
				intData1.setValue(new Integer(1));
				assertEquals(intData1, data.getValue1().getLow());
				Int intData2 = new Int();
				intData2.setValue(new Integer(1));
				assertEquals(intData2, data.getValue1().getHigh());
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

				Int intData1 = new Int();
				intData1.setValue(new Integer(2));
				assertEquals(intData1, data.getValue1().getLow());
				Int intData2 = new Int();
				intData2.setValue(new Integer(1));
				assertEquals(intData2, data.getValue1().getHigh());
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getHigh());
				assertNotNull(data.getValue1().getLow());
				assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue1Constants(IvlIntDataType data)
	{
		//Global constant
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		assertEquals(NullFlavor.NI, data.getValue1().getHigh().getNullFlavor());
		assertEquals(NullFlavor.NI, data.getValue1().getLow().getNullFlavor());
		
		assertNull(data.getValue1().getAny());
		assertNull(data.getValue1().getHighClosed());
		assertNull(data.getValue1().getLowClosed());
		assertNull(data.getValue1().getWidth());
	}

	private void assertValue2(Collection<IvlIntDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IvlIntDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				Int intData = new Int();
				intData.setValue(new Integer(10));
				assertEquals(intData, data.getValue2().getHigh());
				assertNull(data.getValue2().getLowClosed());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getHigh());
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				Int intData = new Int();
				intData.setValue(new Integer(10));
				assertEquals(intData, data.getValue2().getHigh());
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
				assertValue2Constants(data);
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				Int intData = new Int();
				intData.setValue(new Integer(1));
				assertEquals(intData, data.getValue2().getHigh());
				assertEquals(Boolean.FALSE, data.getValue2().getLowClosed());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				Int intData = new Int();
				intData.setValue(new Integer(1));
				assertEquals(intData, data.getValue2().getHigh());
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue2());
				assertNotNull(data.getValue2().getHigh());
				assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue2Constants(IvlIntDataType data)
	{
		//Global constant
		assertEquals(NullFlavor.NI, data.getValue2().getHigh().getNullFlavor());
		assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
		assertNull(data.getValue2().getLow());
		assertNull(data.getValue1().getAny());
		assertNull(data.getValue1().getHighClosed());
		assertNull(data.getValue1().getWidth());
	}
	
	private void assertValue3(Collection<IvlIntDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IvlIntDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				Int intData1 = new Int();
				intData1.setValue(new Integer(5));
				assertEquals(intData1, data.getValue3().getAny());
				Int intData2 = new Int();
				intData2.setValue(new Integer(11));
				assertEquals(intData2, data.getValue3().getLow());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				Int intData1 = new Int();
				intData1.setValue(new Integer(6));
				assertEquals(intData1, data.getValue3().getAny());
				Int intData2 = new Int();
				intData2.setValue(new Integer(12));
				assertEquals(intData2, data.getValue3().getLow());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				Int intData1 = new Int();
				intData1.setValue(new Integer(7));
				assertEquals(intData1, data.getValue3().getAny());
				Int intData2 = new Int();
				intData2.setValue(new Integer(13));
				assertEquals(intData2, data.getValue3().getLow());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertValue3Constants(data);
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				Int intData1 = new Int();
				intData1.setValue(new Integer(8));
				assertEquals(intData1, data.getValue3().getAny());
				Int intData2 = new Int();
				intData2.setValue(new Integer(14));
				assertEquals(intData2, data.getValue3().getLow());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				Int intData1 = new Int();
				intData1.setValue(new Integer(9));
				assertEquals(intData1, data.getValue3().getAny());
				Int intData2 = new Int();
				intData2.setValue(new Integer(15));
				assertEquals(intData2, data.getValue3().getLow());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getLow());
				assertNotNull(data.getValue3().getAny());
				assertNull(data.getValue3().getHighClosed());
				assertValue3Constants(data);
				counter++;
			}
		}
	}

	private void assertValue3Constants(IvlIntDataType data)
	{
		//Global constant
		assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
		assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
		assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
		
		assertNull(data.getValue3().getHigh());
		assertNull(data.getValue3().getLowClosed());
		assertNull(data.getValue3().getWidth());
	}
	
	
}
