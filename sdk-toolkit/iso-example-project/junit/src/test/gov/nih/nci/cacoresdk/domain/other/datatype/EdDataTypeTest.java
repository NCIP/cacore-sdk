/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType;
import gov.nih.nci.iso21090.Compression;
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
 * Test case for Ed ( ED ) data type
 */
public class EdDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ed Datatype Test Case";
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
		EdDataType searchObject = new EdDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType",searchObject );
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
		assertEquals(16,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		criteria.add(Property.forName("value1").isNotNull());

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	public void testEdValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where a.value1.data is not null order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	public void testEdValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.data").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	public void testEdValue2ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where (a.value2.nullFlavor is not null or a.value2.data is not null) order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	public void testEdValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EdDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.data").isNotNull(), Property.forName("value3.nullFlavor").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.value").isNotNull(), Property.forName("value3.compression").isNotNull()); 
		criteria.add(Restrictions.or(exp1, exp2));
		criteria.addOrder(Order.asc("id"));

		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType a where (a.value3.nullFlavor is not null or a.value3.data is not null or a.value3.compression is not null or a.value3.value is not null) order by a.id asc");
		Collection<EdDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType");
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
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEdValue() throws ApplicationException
	{
		EdDataType searchObject = new EdDataType();
		Collection<EdDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}
	
	private void assertValue1(Collection<EdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");
				
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getData());
				String aVal = "110101010010";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue1().getData());
				
				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
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
				String aVal = "100101010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue1().getData());

				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
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
				String aVal = "100001010010";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue1().getData());
				
				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
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
				String aVal = "110001010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue1().getData());
				
				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
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
				String aVal = "110001011110";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue1().getData());
				
				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertNull(data.getValue1().getData());
				assertNull(data.getValue1().getCompression());
				assertNull(data.getValue1().getValue());
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<EdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
		{
			//Validate 7th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertNull(data.getValue2().getData());
				assertNull(data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
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
				String aVal = "110101010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue2().getData());
				
				assertNull(data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
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
				String aVal = "010101010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue2().getData());
				
				assertNull(data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
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
				String aVal = "000101010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue2().getData());
				
				assertEquals(Compression.GZ, data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
				assertNull(data.getValue2().getNullFlavor());
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

				String aVal = "110001010010";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue2().getData());
				assertEquals(Compression.GZ, data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
				assertNull(data.getValue2().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());

				assertNull(data.getValue2().getData());
				assertNull(data.getValue2().getCompression());
				assertNull(data.getValue2().getValue());
				counter++;
			}
		}
	}

	private void assertValue3(Collection<EdDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EdDataType data : result)
		{
			//Validate 12th record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue3());

				assertNull(data.getValue3().getData());
				assertEquals(Compression.GZ, data.getValue3().getCompression());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 13th record
			else if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getData());
				
				assertNull(data.getValue3().getCompression());
				assertNull(data.getValue3().getValue());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 14th record
			else if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				String aVal = "110001010111";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue3().getData());
				
				assertEquals(Compression.GZ, data.getValue3().getCompression());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 15th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				String aVal = "110001010011";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue3().getData());
				
				assertEquals(Compression.GZ, data.getValue3().getCompression());
				assertNull(data.getValue3().getValue());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate 16th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				String aVal = "110001010010";
				byte[] bytes = aVal.getBytes();
				org.junit.Assert.assertArrayEquals(bytes, data.getValue3().getData());
				assertEquals(Compression.GZ, data.getValue3().getCompression());
				assertEquals("VALUE3_VALUE_A", data.getValue3().getValue());
				assertNull(data.getValue3().getNullFlavor());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());

				assertNull(data.getValue3().getData());
				assertNull(data.getValue3().getCompression());
				assertNull(data.getValue3().getValue());
				counter++;
			}
		}
	}
}
