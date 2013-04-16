/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.datatype;

/*
 * Test case for DsetTel ( DSET<TEL> ) data type
 */
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class DsetTelDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset Tel Datatype Test Case";
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
		DsetTelDataType searchObject = new DsetTelDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType",searchObject );
		assertNotNull(results);
		assertEquals(15,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(15,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value1.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("1");
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		assertValue1(result, index);
	}
*/
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetTelValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value1.item.value is not null order by a.id asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("1");
		index.add("2");
		index.add("3");
		index.add("4");
		index.add("5");
		assertValue1(result, index);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value2.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("6");
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		assertValue2(result, index);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetTelValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value2.item.value is not null order by a.id asc asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("6");
		index.add("7");
		index.add("8");
		index.add("9");
		index.add("10");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetTelValue3ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.add(Property.forName("value3.item.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("11");
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		assertValue3(result, index);
	}
*/
	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetTelValue3ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType a where a.value3.item.value is not null order by a.id asc asc");
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertEquals(5, result.size());
		List index = new ArrayList();
		index.add("11");
		index.add("12");
		index.add("13");
		index.add("14");
		index.add("15");
		assertValue3(result, index);
	}
	
	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetTelDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<DsetTelDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType");
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
	}

	
	private void assertValue1(Collection<DsetTelDataType> result, List<Integer> index) throws URISyntaxException
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				if(index != null) 
					index.remove("1");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				Tel item = data.getValue1().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7891"), item.getValue());

				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				Tel item = data.getValue1().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

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
				Tel item = data.getValue1().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7893"), item.getValue());

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
				Tel item = data.getValue1().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7894"), item.getValue());

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
				Tel item = data.getValue1().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7895"), item.getValue());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getItem());
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<DsetTelDataType> result, List<Integer> index) throws URISyntaxException
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
		{
			//Validate 6th record
			if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				Tel item = data.getValue2().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7891"), item.getValue());

				counter++;
				continue;
			}
			//Validate 7th record
			else if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				Tel item = data.getValue2().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

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
				Tel item = data.getValue2().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7893"), item.getValue());

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
				Tel item = data.getValue2().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7894"), item.getValue());

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
				Tel item = data.getValue2().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7895"), item.getValue());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getItem());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}

	private void assertValue3(Collection<DsetTelDataType> result, List<Integer> index) throws URISyntaxException
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetTelDataType data : result)
		{
			//Validate 11th record
			if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				Tel item = data.getValue3().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

				counter++;
				continue;
			}
			//Validate 12th record
			else if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				Tel item = data.getValue3().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

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
				Tel item = data.getValue3().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

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
				Tel item = data.getValue3().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

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
				Tel item = data.getValue3().getItem().iterator().next();
				assertNull(item.getNullFlavor());
				assertEquals(new URI("tel://123-456-7892"), item.getValue());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertNull(data.getValue3().getItem());
				assertEquals(NullFlavor.NI, data.getValue3().getNullFlavor());
				counter++;
			}
		}
	}
	
}
