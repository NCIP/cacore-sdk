package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType;
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
 * Test case for Pq (PQ) data type
 */
public class PqDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Pq Datatype Test Case";
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
		PqDataType searchObject = new PqDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType",searchObject );
		assertNotNull(results);
		assertEquals(27,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(27,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(10, result.size());
		List index = new ArrayList();
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		index.add("6");
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		assertValue1(result, index);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(10, result.size());
		List index = new ArrayList();
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		index.add("6");
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		index.add("11");
		assertValue1(result, index);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Property.forName("value2.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
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
	public void testPqValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where a.value2.value is not null order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		index.add("16");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue3ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.add(Restrictions.or(Property.forName("value3.value").isNotNull(), Property.forName("value3.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(11, result.size());
		List index = new ArrayList();
		index.add("17");
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		index.add("23");
		index.add("24");
		index.add("25");
		index.add("26");
		index.add("27");
		assertValue3(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue3ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType a where (a.value3.value is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertEquals(11, result.size());
		List index = new ArrayList();
		index.add("17");
		index.add("18");
		index.add("19");
		index.add("20");
		index.add("21");
		index.add("22");
		index.add("23");
		index.add("24");
		index.add("25");
		index.add("26");
		index.add("27");
		assertValue3(result, index);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testPqValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(PqDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<PqDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<PqDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);
		MathContext decimalContext = new MathContext(3);
		int counter = 1;
		for(PqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(1.12, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

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

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(2.12, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

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

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(3.12, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());
				
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

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(4.12, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

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

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(5.12, decimalContext), data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(1.12, decimalContext), data.getValue1().getValue());
				//From database, overriding global constant
				assertEquals("GALLON", data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(2.12, decimalContext), data.getValue1().getValue());
				//From database, overriding global constant
				assertEquals("GALLON", data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(3.12, decimalContext), data.getValue1().getValue());
				//From database, overriding global constant
				assertEquals("GALLON", data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(4.12, decimalContext), data.getValue1().getValue());
				//From database, overriding global constant
				assertEquals("GALLON", data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getNullFlavor());
				assertEquals(new BigDecimal(5.12, decimalContext), data.getValue1().getValue());
				//From database, overriding global constant
				assertEquals("GALLON", data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getUnit());
				assertNull(data.getValue1().getPrecision());
				//Local constant overriding global constant
				assertEquals(NullFlavor.NA, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<PqDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);
		MathContext decimalContext = new MathContext(3);
		int counter = 1;
		for(PqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals(new BigDecimal(1.23, decimalContext), data.getValue2().getValue());
				//Local constant
				assertEquals("UNIT", data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals(new BigDecimal(2.23, decimalContext), data.getValue2().getValue());
				//Local constant
				assertEquals("UNIT", data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals(new BigDecimal(3.23, decimalContext), data.getValue2().getValue());
				//Local constant
				assertEquals("UNIT", data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals(new BigDecimal(4.23, decimalContext), data.getValue2().getValue());
				//Local constant
				assertEquals("UNIT", data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getNullFlavor());
				assertEquals(new BigDecimal(5.23, decimalContext), data.getValue2().getValue());
				//Local constant
				assertEquals("UNIT", data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertNull(data.getValue2().getUnit());
				assertNull(data.getValue2().getPrecision());
				assertNull(data.getValue2().getValue());
				counter++;
			}
		}
	}
	
	private void assertValue3(Collection<PqDataType> result, List<Integer> index) throws ParseException
	{
		assertNotNull(result);
		MathContext decimalContext = new MathContext(3);
		int counter = 1;
		for(PqDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(1.34, decimalContext), data.getValue3().getValue());
				assertNull(data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 3rd record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(2.34, decimalContext), data.getValue3().getValue());
				assertNull(data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 4th record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(1.37, decimalContext), data.getValue3().getValue());
				//From database
				assertEquals("LITER", data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());
				
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(2.37, decimalContext), data.getValue3().getValue());
				//From database, overriding global and local constants
				assertEquals("LITER", data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(3.37, decimalContext), data.getValue3().getValue());
				//From database, overriding global and local constants
				assertEquals("LITER", data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

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
				assertEquals(new BigDecimal(1.38, decimalContext), data.getValue3().getValue());
				//From database, overriding global and local constants
				assertEquals("LITER", data.getValue3().getUnit());
				assertEquals(new Integer(2), data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(2.38, decimalContext), data.getValue3().getValue());
				//From database, overriding global and local constants
				assertEquals("LITER", data.getValue3().getUnit());
				assertEquals(new Integer(2), data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getNullFlavor());
				assertEquals(new BigDecimal(3.38, decimalContext), data.getValue3().getValue());
				//From database, overriding global and local constants
				assertEquals("LITER", data.getValue3().getUnit());
				assertEquals(new Integer(2), data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				//From database, overriding global constant
				assertEquals(NullFlavor.NA, data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getUnit());
				assertNull(data.getValue3().getPrecision());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}
	
}
