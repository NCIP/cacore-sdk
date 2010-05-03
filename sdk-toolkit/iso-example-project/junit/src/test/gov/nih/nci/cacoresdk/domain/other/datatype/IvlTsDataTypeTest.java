package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Test case of IvlTs ( IVL<TS> ) data type
 */
public class IvlTsDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl Ts Datatype Test Case";
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
		IvlTsDataType searchObject = new IvlTsDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType",searchObject );
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(21,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.add(Restrictions.or(Property.forName("value1.low.value").isNotNull(), Property.forName("value1.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
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
	public void testIvlTsValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value1.low.value is not null or a.value1.high.value is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
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
	public void testIvlTsValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.high.value").isNotNull(), Property.forName("value2.lowClosed").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
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
	public void testIvlTsValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value2.high.value is not null or a.value2.lowClosed is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
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
	public void testIvlTsValue3ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.high.value").isNotNull(), Property.forName("value3.low.value").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.width.value").isNotNull(), Property.forName("value3.width.nullFlavor").isNotNull()); 
		criteria.add(Restrictions.or(exp1, exp2));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(9, result.size());
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
		assertValue3(result, index);
	}

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue3ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value3.low.value is not null or a.value3.high.value is not null or a.value3.width.value is not null or a.value3.width.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(9, result.size());
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
		assertValue3(result, index);
	}

	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<IvlTsDataType> result, List<Integer> index)  throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		for(IvlTsDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals(df.parse("03/11/2010 12:00:00 AM"), df.parse(df.format(data.getValue1().getLow().getValue())));
				assertNull(data.getValue1().getHigh().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getHigh().getNullFlavor());
				assertTrue(data.getValue1().getLow().getValue().equals(data.getValue1().getLow().getValue()));
				assertTrue(data.getValue1().getLow().getValue().clone().equals(data.getValue1().getLow().getValue()));
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

				assertEquals(df.parse("03/12/2010 12:00:00 AM"), df.parse(df.format(data.getValue1().getLow().getValue())));
				assertNull(data.getValue1().getHigh().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getHigh().getNullFlavor());
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

				assertEquals(df.parse("03/11/2010 12:00:00 AM"), df.parse(df.format(data.getValue1().getHigh().getValue())));
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

				assertEquals(df.parse("03/12/2000 12:00:00 AM"), df.parse(df.format(data.getValue1().getHigh().getValue())));
				assertNull(data.getValue1().getLow().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getLow().getNullFlavor());
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

				assertEquals(df.parse("03/03/2010 12:00:00 AM"), df.parse(df.format(data.getValue1().getLow().getValue())));
				assertEquals(df.parse("03/13/2010 12:00:00 AM"), df.parse(df.format(data.getValue1().getHigh().getValue())));
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getAny());
				assertNull(data.getValue1().getWidth());
				assertNull(data.getValue1().getHighClosed());
				assertNull(data.getValue1().getLowClosed());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				assertNull(data.getValue1().getLow());
				assertNull(data.getValue1().getHigh());
				
				counter++;
			}
		}
	}

	private void assertValue1Constants(IvlTsDataType data)
	{
		
		assertNull(data.getValue1().getAny());
		assertNull(data.getValue1().getWidth());
		assertNull(data.getValue1().getHighClosed());
		assertNull(data.getValue1().getLowClosed());
		assertNull(data.getValue1().getNullFlavor());
	}
	
	private void assertValue2(Collection<IvlTsDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		for(IvlTsDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(df.parse("03/11/2010 12:00:00 AM"), df.parse(df.format(data.getValue2().getHigh().getValue())));
				assertTrue(data.getValue2().getHigh().getValue().equals(data.getValue2().getHigh().getValue()));
				assertTrue(data.getValue2().getHigh().getValue().clone().equals(data.getValue2().getHigh().getValue()));
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

				assertEquals(df.parse("03/11/2001 12:00:00 AM"), df.parse(df.format(data.getValue2().getHigh().getValue())));
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

				assertEquals(df.parse("03/13/2010 12:00:00 AM"), df.parse(df.format(data.getValue2().getHigh().getValue())));
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

				assertEquals(df.parse("03/14/2010 12:00:00 AM"), df.parse(df.format(data.getValue2().getHigh().getValue())));
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
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

				assertEquals(df.parse("03/15/2010 12:00:00 AM"), df.parse(df.format(data.getValue2().getHigh().getValue())));
				assertEquals(Boolean.TRUE, data.getValue2().getLowClosed());
				assertValue2Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getAny());
				assertNull(data.getValue2().getWidth());
				assertNull(data.getValue2().getHighClosed());
				assertNull(data.getValue2().getLowClosed());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertNull(data.getValue2().getLow());
				assertNull(data.getValue2().getHigh());
				counter++;
			}
		}
	}


	private void assertValue2Constants(IvlTsDataType data)
	{
		assertNull(data.getValue2().getAny());
		assertNull(data.getValue2().getWidth());
		assertNull(data.getValue2().getHighClosed());
		assertNull(data.getValue2().getLow());
		assertNull(data.getValue2().getNullFlavor());
	}

	private void assertValue3(Collection<IvlTsDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		MathContext decimalContext = new MathContext(4);
		for(IvlTsDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(df.parse("03/11/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/01/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertTrue(data.getValue3().getHigh().getValue().compareTo(data.getValue3().getLow().getValue()) > 0);
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue3().getWidth()).getValue());
				//From database
				assertNull(data.getValue3().getWidth().getNullFlavor());
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
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(df.parse("03/12/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/02/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertFalse(data.getValue3().getHigh().getValue().compareTo(data.getValue3().getLow().getValue()) == 0);
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue3().getWidth()).getValue());
				//From database
				assertNull(data.getValue3().getWidth().getNullFlavor());
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
				assertNull(data.getValue3().getNullFlavor());
				
				assertEquals(df.parse("03/13/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/03/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertFalse(data.getValue3().getHigh().getValue().compareTo(data.getValue3().getLow().getValue()) == 0);
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue3().getWidth()).getValue());
				//From database
				assertNull(data.getValue3().getWidth().getNullFlavor());
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
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(df.parse("03/14/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/04/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertNotNull(data.getValue3().getWidth());
				//From database
				assertEquals(NullFlavor.NI, data.getValue3().getWidth().getNullFlavor());
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

				assertEquals(df.parse("03/15/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/05/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertNotNull(data.getValue3().getWidth());
				//From database
				assertEquals(NullFlavor.NI, data.getValue3().getWidth().getNullFlavor());
				assertValue3Constants(data);
				
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
				//From database
				assertNull(data.getValue3().getNullFlavor());

				assertNull(data.getValue3().getHigh().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getHigh().getNullFlavor());
				assertEquals(df.parse("03/01/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue3().getWidth()).getValue());
				//From database
				assertNull(data.getValue3().getWidth().getNullFlavor());
				assertValue3Constants(data);
				
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
				//From database
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(df.parse("03/22/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertNull(data.getValue3().getLow().getValue());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				assertNotNull(data.getValue3().getWidth());
				//From database
				assertEquals(NullFlavor.NI, data.getValue3().getWidth().getNullFlavor());
				assertValue3Constants(data);
				
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
				//From database
				assertEquals(NullFlavor.NA, data.getValue3().getNullFlavor());

				assertNull(data.getValue3().getHigh().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getLow().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				assertNotNull(data.getValue3().getWidth());
				//From database
				assertEquals(NullFlavor.NA, data.getValue3().getWidth().getNullFlavor());
				assertValue3Constants(data);
				
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
				//From database
				assertNull(data.getValue3().getNullFlavor());

				assertEquals(df.parse("03/24/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getHigh().getValue())));
				assertEquals(df.parse("03/04/2010 12:00:00 AM"), df.parse(df.format(data.getValue3().getLow().getValue())));
				assertEquals(new BigDecimal(4, decimalContext), ((gov.nih.nci.iso21090.Pq)data.getValue3().getWidth()).getValue());
				//From database
				assertNull(data.getValue3().getWidth().getNullFlavor());
				assertValue3Constants(data);
				
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNotNull(data.getValue3().getNullFlavor());
				/*
				assertNull(data.getValue3().getWidth());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getLow().getNullFlavor());
				//Global constant
				assertEquals(NullFlavor.NI, data.getValue3().getHigh().getNullFlavor());
				assertNull(data.getValue3().getWidth().getNullFlavor());
				assertNull(data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getAny());
				assertNull(data.getValue3().getHighClosed());
				assertNull(data.getValue3().getLowClosed());
				*/
				counter++;
				
			}
		}
	}

	private void assertValue3Constants(IvlTsDataType data)
	{
		assertNull(data.getValue3().getAny());
		assertNull(data.getValue3().getHighClosed());
		assertNull(data.getValue3().getLowClosed());
	}
}
