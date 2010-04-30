package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for IvlPq ( IVL<PQ> ) data type
 */
public class IvlPqDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl Pq Datatype Test Case";
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
		IvlPqDataType searchObject = new IvlPqDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType",searchObject );
		assertNotNull(results);
		assertEquals(22,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(22,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Property.forName("value1.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
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
	public void testIvlPqValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where a.value1.low.value is not null order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
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
	public void testIvlPqValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Property.forName("value2.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
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
	public void testIvlPqValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where a.value2.low.value is not null order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
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
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Restrictions.or(Property.forName("value3.low.value").isNotNull(), Property.forName("value3.low.precision").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		index.add("17");
		assertValue3(result, index);
	}

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where (a.value3.low.value is not null or a.value3.low.precision is not null) order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		index.add("17");
		assertValue3(result, index);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue4ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.add(Property.forName("value4.low.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		assertValue4(result, index);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType a where a.value4.low.value is not null order by a.id asc asc");
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		assertValue4(result, index);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlPqValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlPqDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlPqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
	}

	
	private void assertValue1(Collection<IvlPqDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(2);
		for(IvlPqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals(new BigDecimal(1.1, decimalContext), data.getValue1().getLow().getValue());
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

				assertEquals(new BigDecimal(2.1, decimalContext), data.getValue1().getLow().getValue());
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

				assertEquals(new BigDecimal(3.1, decimalContext), data.getValue1().getLow().getValue());
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

				assertEquals(new BigDecimal(4.1, decimalContext), data.getValue1().getLow().getValue());
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

				assertEquals(new BigDecimal(5.1, decimalContext), data.getValue1().getLow().getValue());
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getLow().getValue());
				assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue1Constants(IvlPqDataType data)
	{
		assertNull(data.getValue1().getHigh());
		assertNull(data.getValue1().getAny());
		assertNull(data.getValue1().getNullFlavor());
		assertNull(data.getValue1().getHighClosed());
		assertNull(data.getValue1().getLowClosed());
		assertNull(data.getValue1().getWidth());
		assertNull(data.getValue1().getLow().getPrecision());
		assertEquals("GALLON", data.getValue1().getLow().getUnit());
	}
	
	private void assertValue2(Collection<IvlPqDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(4);
		for(IvlPqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(new BigDecimal(221.1, decimalContext), data.getValue2().getLow().getValue());
				assertNull(data.getValue2().getLow().getUnit());
				assertNull(data.getValue2().getLow().getPrecision());
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

				assertEquals(new BigDecimal(222.1, decimalContext), data.getValue2().getLow().getValue());
				assertEquals(new Integer(2), data.getValue2().getLow().getPrecision());
				assertNull(data.getValue2().getLow().getUnit());
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

				assertEquals(new BigDecimal(221.1, decimalContext), data.getValue2().getLow().getValue());
				assertEquals(new Integer(2), data.getValue2().getLow().getPrecision());
				assertEquals("Unit", data.getValue2().getLow().getUnit());
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

				assertEquals(new BigDecimal(222.1, decimalContext), data.getValue2().getLow().getValue());
				assertNull(data.getValue2().getLow().getPrecision());
				assertEquals("Unit", data.getValue2().getLow().getUnit());
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

				assertEquals(new BigDecimal(223.1, decimalContext), data.getValue2().getLow().getValue());
				assertEquals(new Integer(2), data.getValue2().getLow().getPrecision());
				assertEquals("Unit", data.getValue2().getLow().getUnit());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(new BigDecimal(224.1,decimalContext), data.getValue2().getLow().getValue());
				assertEquals(new Integer(2), data.getValue2().getLow().getPrecision());
				assertEquals("Unit", data.getValue2().getLow().getUnit());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertValue2Constants(data);
				assertNull(data.getValue2().getLow());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
		
				counter++;
			}
		}
	}

	private void assertValue2Constants(IvlPqDataType data)
	{
		assertNull(data.getValue2().getAny());
		assertNull(data.getValue2().getHighClosed());
		assertNull(data.getValue2().getLowClosed());
		assertNull(data.getValue2().getWidth());
		assertNull(data.getValue2().getHigh());
	}
	
	private void assertValue3(Collection<IvlPqDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(4);
		for(IvlPqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(224.1, decimalContext), data.getValue3().getLow().getValue());
				assertNull(data.getValue3().getLow().getPrecision());
				assertNull(data.getValue3().getLow().getUnit());
				assertValue3Constants(data);

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

				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(224.1, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(new Integer(2), data.getValue3().getLow().getPrecision());
				assertNull(data.getValue3().getLow().getUnit());
				assertValue3Constants(data);

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

				//From the database overriding global constant
				assertNull(data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getLow().getValue());
				assertEquals(new Integer(2), data.getValue3().getLow().getPrecision());
				assertEquals("Unit", data.getValue3().getLow().getUnit());
				assertValue3Constants(data);
				
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

				//From the database overriding global constant
				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(224.1, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(new Integer(2), data.getValue3().getLow().getPrecision());
				assertEquals("Unit", data.getValue3().getLow().getUnit());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getLow().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				assertNull(data.getValue3().getLow().getPrecision());
				assertNull(data.getValue3().getLow().getUnit());
				assertValue3Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());
				assertValue3Constants(data);
				assertNull(data.getValue3().getLow().getUnit());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3Constants(IvlPqDataType data)
	{
		assertNull(data.getValue3().getAny());
		assertNull(data.getValue3().getHighClosed());
		assertNull(data.getValue3().getLowClosed());
		assertNull(data.getValue3().getWidth());
		assertNull(data.getValue3().getHigh());
	}
	
	private void assertValue4(Collection<IvlPqDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(2);
		for(IvlPqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue4());

				assertNull(data.getValue4().getNullFlavor());
				
				assertEquals(new BigDecimal(5.1, decimalContext), data.getValue4().getHigh().getValue());
				assertEquals(new Integer(2), data.getValue4().getHigh().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_HIGH_UNIT1", data.getValue4().getHigh().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getHighClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getHigh().getNullFlavor());
				
				assertEquals(new BigDecimal(1.1, decimalContext), data.getValue4().getLow().getValue());
				assertEquals(new Integer(2), data.getValue4().getLow().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_LOW_UNIT", data.getValue4().getLow().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getLowClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getLow().getNullFlavor());

				assertEquals(new BigDecimal(5.1, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getValue());
				assertEquals(new Integer(2), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_WIDTH_UNIT1", ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getUnit());
				//From database, overriding global constant
				assertNull(data.getValue4().getWidth().getNullFlavor());

				assertNull(data.getValue4().getAny());

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

				//From database, overriding global constant
				assertNull(data.getValue4().getNullFlavor());
				assertEquals(new BigDecimal(6.1, decimalContext), data.getValue4().getHigh().getValue());
				assertEquals(new Integer(2), data.getValue4().getHigh().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_HIGH_UNIT2", data.getValue4().getHigh().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getHighClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getHigh().getNullFlavor());
				
				assertEquals(new BigDecimal(1.1, decimalContext), data.getValue4().getLow().getValue());
				assertEquals(new Integer(2), data.getValue4().getLow().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_LOW_UNIT", data.getValue4().getLow().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getLowClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getLow().getNullFlavor());

				assertEquals(new BigDecimal(5.1, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getValue());
				assertEquals(new Integer(2), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getPrecision());
				assertEquals("VALUE4_WIDTH_UNIT2", ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getUnit());
				assertNull(data.getValue4().getWidth().getNullFlavor());

				assertNull(data.getValue4().getAny());

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

				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue4().getNullFlavor());
				assertNull(data.getValue4().getHigh());
				assertNull(data.getValue4().getHighClosed());
				//From database, overriding global constant
				assertEquals(NullFlavor.NI, data.getValue4().getHigh().getNullFlavor());
				
				assertNull(data.getValue4().getLow());
				assertNull(data.getValue4().getLowClosed());
				//From database, overriding global constant
				assertEquals(NullFlavor.NI, data.getValue4().getLow().getNullFlavor());

				assertNull(data.getValue4().getWidth());				
				//From database, overriding global constant
				assertEquals(NullFlavor.NI, data.getValue4().getWidth().getNullFlavor());
				assertNull(data.getValue4().getAny());
				
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

				//From database, overriding global constant
				assertNull(data.getValue4().getNullFlavor());
				assertEquals(new BigDecimal(8.1, decimalContext), data.getValue4().getHigh().getValue());
				assertEquals(new Integer(2), data.getValue4().getHigh().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_HIGH_UNIT4", data.getValue4().getHigh().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getHighClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getHigh().getNullFlavor());
				
				assertNull(data.getValue4().getLow());
				assertEquals(Boolean.TRUE, data.getValue4().getLowClosed());
				//From database, overriding global constant
				assertEquals(NullFlavor.NI, data.getValue4().getLow().getNullFlavor());

				assertEquals(new BigDecimal(5, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getValue());				
				assertEquals(new Integer(2), ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_WIDTH_UNIT4", ((gov.nih.nci.iso21090.Pq)data.getValue4().getWidth()).getUnit());
				//From database, overriding global constant
				assertNull( data.getValue4().getWidth().getNullFlavor());
				assertNull(data.getValue4().getAny());

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

				//From database, overriding global constant
				assertNull(data.getValue4().getNullFlavor());
				assertEquals(new BigDecimal(9.1, decimalContext), data.getValue4().getHigh().getValue());
				assertEquals(new Integer(2), data.getValue4().getHigh().getPrecision());
				//From database, overriding global constant
				assertEquals("VALUE4_HIGH_UNIT5", data.getValue4().getHigh().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getHighClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getHigh().getNullFlavor());
				
				assertEquals(new BigDecimal(1.1, decimalContext), data.getValue4().getLow().getValue());
				assertEquals(new Integer(2), data.getValue4().getLow().getPrecision());
				assertNull(data.getValue4().getLow().getUnit());
				assertEquals(Boolean.TRUE, data.getValue4().getLowClosed());
				//From database, overriding global constant
				assertNull(data.getValue4().getLow().getNullFlavor());

				assertNull(data.getValue4().getWidth());				
				assertEquals(NullFlavor.NI, data.getValue4().getWidth().getNullFlavor());
				assertNull(data.getValue4().getAny());
				
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

}
