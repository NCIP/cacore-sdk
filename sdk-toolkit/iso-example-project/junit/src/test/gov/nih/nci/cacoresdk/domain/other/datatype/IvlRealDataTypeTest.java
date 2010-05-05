package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.math.BigDecimal;
import java.math.MathContext;
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
 * Test case for IvlReal ( IVL<REAL> ) data type
 */
public class IvlRealDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl Real Datatype Test Case";
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
		IvlRealDataType searchObject = new IvlRealDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType",searchObject );
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertEquals(22,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlRealValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value1.low.value").isNotNull(), Property.forName("value1.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
	public void testIvlRealValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value1.low.value is not null or a.value1.high.value is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
	public void testIvlRealValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.high.value").isNotNull(), Property.forName("value2.lowClosed").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
	public void testIvlRealValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value2.high.value is not null or a.value2.lowClosed is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
	public void testIvlRealValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.high.value").isNotNull(), Property.forName("value3.low.value").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.width.value").isNotNull(), Property.forName("value3.width.nullFlavor").isNotNull()); 
		LogicalExpression exp3 = Restrictions.or(exp1, exp2);
		criteria.add(Restrictions.or(Property.forName("value3.nullFlavor").isNotNull(), exp3));
		
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertEquals(10, result.size());
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
		assertValue3(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlRealValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value3.low.value is not null or a.value3.high.value is not null or a.value3.width.value is not null or a.value3.width.nullFlavor is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertEquals(10, result.size());
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
		assertValue3(result, index);
	}

	
	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlRealValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<IvlRealDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(4);
		for(IvlRealDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(10.1, new MathContext(3)), data.getValue1().getLow().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getHigh().getNullFlavor());
				assertNull(data.getValue1().getHigh().getValue());
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

				assertEquals(new BigDecimal(1, decimalContext), data.getValue1().getLow().getValue());
				assertEquals(new BigDecimal(210.2, decimalContext), data.getValue1().getHigh().getValue());
				assertTrue(data.getValue1().getHigh().getValue().compareTo(data.getValue1().getLow().getValue()) > 0);
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

				assertEquals(new BigDecimal(220.2, decimalContext), data.getValue1().getHigh().getValue());
				assertNull(data.getValue1().getLow().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getLow().getNullFlavor());
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

				assertEquals(new BigDecimal(3, decimalContext), data.getValue1().getLow().getValue());
				assertEquals(new BigDecimal(230.2, decimalContext), data.getValue1().getHigh().getValue());
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

				assertEquals(new BigDecimal(3, decimalContext), data.getValue1().getHigh().getValue());
				assertEquals(new BigDecimal(230.2, decimalContext), data.getValue1().getLow().getValue());
				assertFalse(data.getValue1().getHigh().getValue().compareTo(data.getValue1().getLow().getValue()) > 0);
				assertValue1Constants(data);

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
				assertNull(data.getValue1().getLow());
				assertNull(data.getValue1().getHigh());
				counter++;
			}
		}
	}

	private void assertValue1Constants(IvlRealDataType data)
	{
		assertNull(data.getValue1().getAny());
		assertNull(data.getValue1().getWidth());
		assertNull(data.getValue1().getHighClosed());
		assertNull(data.getValue1().getLowClosed());
	}

	private void assertValue2(Collection<IvlRealDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(4);
		for(IvlRealDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(new BigDecimal(-310), data.getValue2().getHigh().getValue());
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

				assertEquals(new BigDecimal(40, decimalContext), data.getValue2().getHigh().getValue());
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

				assertNull(data.getValue2().getHigh().getValue());
				assertEquals(NullFlavor.NI, data.getValue2().getHigh().getNullFlavor());
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

				assertEquals(new BigDecimal(4222222), data.getValue2().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
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

				assertEquals(new BigDecimal(43, decimalContext), data.getValue2().getHigh().getValue());
				assertEquals(Boolean.FALSE, data.getValue2().getLowClosed());
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

				assertEquals(new BigDecimal(44,decimalContext), data.getValue2().getHigh().getValue());
				assertEquals(Boolean.FALSE, data.getValue2().getLowClosed());
				assertValue2Constants(data);

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
				assertNull(data.getValue2().getHigh());
				assertNull(data.getValue2().getLowClosed());
				assertValue2Constants(data);
				counter++;
			}
		}
	}

	private void assertValue2Constants(IvlRealDataType data)
	{
		assertNull(data.getValue2().getAny());
		assertNull(data.getValue2().getWidth());
		assertNull(data.getValue2().getHighClosed());
		assertNull(data.getValue2().getLow());
	}

	private void assertValue3(Collection<IvlRealDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		MathContext decimalContext = new MathContext(4);
		for(IvlRealDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(510, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(2, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(44, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

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
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(520, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(2, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(44, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

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
				assertNull(data.getValue3().getNullFlavor());
				
				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(530, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(2, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertNotNull(data.getValue3().getWidth());
				assertNull(((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());
				
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
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(540, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(2, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(44, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

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
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());

				assertNull(data.getValue3().getAny());
				assertNull(data.getValue3().getHigh());
				assertNull(data.getValue3().getHighClosed());
				assertNull(data.getValue3().getLow());
				assertNull(data.getValue3().getLowClosed());
				assertNull(data.getValue3().getWidth());

				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(610, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(251, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(620, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(252, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(630, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(253, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertNotNull(data.getValue3().getAny());
				assertEquals(NullFlavor.NI, data.getValue3().getAny().getNullFlavor());
				assertEquals(new BigDecimal(640, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(254, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(new BigDecimal(101, decimalContext), data.getValue3().getAny().getValue());
				assertEquals(new BigDecimal(650, decimalContext), data.getValue3().getHigh().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getHighClosed());
				assertEquals(new BigDecimal(255, decimalContext), data.getValue3().getLow().getValue());
				assertEquals(Boolean.TRUE, data.getValue3().getLowClosed());
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Real)data.getValue3().getWidth()).getValue());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				
				assertNull(data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getNullFlavor());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getNullFlavor());
				//assertEquals(NullFlavor.NI, data.getValue3().getHigh().getNullFlavor());
				//assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				
				counter++;
			}
		}
	}

}
