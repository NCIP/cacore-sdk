/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for TelEmail (TEL.EMAIL)
 */
public class TelEmailDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Tel Email Datatype Test Case";
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
		TelEmailDataType searchObject = new TelEmailDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType",searchObject );
		assertNotNull(results);
		assertEquals(11,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
		assertEquals(11,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelEmailValue1ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelEmailDataType.class);
		criteria.add(Property.forName("value1").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<TelEmailDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
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
	public void testTelEmailValue1ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType a where a.value1 is not null order by a.id asc asc");
		Collection<TelEmailDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
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
	public void testTelEmailValue2ByDetachedCriteria() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelEmailDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.nullFlavor").isNotNull(), Property.forName("value2.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<TelEmailDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
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
	public void testTelEmailValue2ByHQLCriteria() throws ApplicationException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<TelEmailDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
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
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTelEmailValue() throws ApplicationException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TelEmailDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<TelEmailDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType");
		assertValue1(result, null);
		assertValue2(result, null);
	}

	
	private void assertValue1(Collection<TelEmailDataType> result, List<Integer> index) throws URISyntaxException
	{
		assertNotNull(result);

		int counter = 1;
		for(TelEmailDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());

				assertEquals(new URI("mailto:jdoe1@nci.gov"), data.getValue1().getValue());
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

				assertEquals(new URI("mailto:jdoe2@nci.gov"), data.getValue1().getValue());
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

				assertEquals(new URI("mailto:jdoe3@nci.gov"), data.getValue1().getValue());
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

				assertEquals(new URI("mailto:jdoe4@nci.gov"), data.getValue1().getValue());
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

				assertEquals(new URI("mailto:jdoe5@nci.gov"), data.getValue1().getValue());
				assertValue1Constants(data);

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				assertEquals(NullFlavor.NA, data.getValue1().getNullFlavor());
				counter++;
			}
		}
	}
	
	private void assertValue1Constants(TelEmailDataType data)
	{
		assertNull(data.getValue1().getNullFlavor());
	}

	private void assertValue2(Collection<TelEmailDataType> result, List<Integer> index) throws URISyntaxException
	{
		assertNotNull(result);

		int counter = 1;
		for(TelEmailDataType data : result)
		{
			//Validate 7th record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				assertNotNull(data.getValue2());

				assertEquals(new URI("MailTo:jdoe1@nci.gov"), data.getValue2().getValue());
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

				assertEquals(new URI("MailTo:jdoe2@nci.gov"), data.getValue2().getValue());
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

				assertEquals(new URI("MailTo:jdoe3@nci.gov"), data.getValue2().getValue());
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

				assertEquals(new URI("MailTo:jdoe4@nci.gov"), data.getValue2().getValue());
				assertNull(data.getValue2().getNullFlavor());

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

				assertEquals(new URI("MailTo:jdoe5@nci.gov"), data.getValue2().getValue());
				assertNull(data.getValue2().getNullFlavor());

				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getValue());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				counter++;
			}
		}
	}
	
}
