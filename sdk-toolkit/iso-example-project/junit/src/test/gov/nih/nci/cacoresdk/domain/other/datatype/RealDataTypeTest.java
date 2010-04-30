package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Real (REAL) data type
 * 
 */
public class RealDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Real Datatype Test Case";
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
		RealDataType searchObject = new RealDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType",searchObject );
		assertNotNull(results);
		assertEquals(12,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertEquals(12,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
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
	public void testRealValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
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
	public void testRealValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.value").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
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
	public void testRealValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
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
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testRealValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(RealDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<RealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType");
		assertValue1(result, null);
		assertValue2(result, null);
	}

	
	private void assertValue1(Collection<RealDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);
		MathContext decimalContext = new MathContext(6);
		int counter = 1;
		for(RealDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals(new BigDecimal(1001.15, decimalContext), data.getValue1().getValue());
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

				assertEquals(new BigDecimal(1002.15, decimalContext), data.getValue1().getValue());
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

				assertEquals(new BigDecimal(-1003), data.getValue1().getValue());
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

				assertEquals(new BigDecimal(1004), data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());

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

				assertEquals(new BigDecimal(1005.15, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				//Local constant overriding global constant
				assertEquals(NullFlavor.NA, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<RealDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);
		MathContext decimalContext = new MathContext(6);
		int counter = 1;
		for(RealDataType data : result)
		{
			//Validate 7th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(new BigDecimal(1101.15, decimalContext), data.getValue2().getValue());
				//From the database
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

				assertEquals(new BigDecimal(1102), data.getValue2().getValue());
				//From the database
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

				assertEquals(new BigDecimal(-1201.15, decimalContext), data.getValue2().getValue());
				//From the database
				assertNull(data.getValue2().getNullFlavor());

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

				assertNull(data.getValue2().getValue());
				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue2().getNullFlavor());

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

				assertNull(data.getValue2().getValue());
				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue2().getNullFlavor());

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

				assertNull(data.getValue2().getValue());
				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue2().getNullFlavor());

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
	
}
