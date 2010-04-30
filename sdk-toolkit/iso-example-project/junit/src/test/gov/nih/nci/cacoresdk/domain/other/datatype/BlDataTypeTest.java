package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType;
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
 * Test case for Bl ( BL ) data type
 */
public class BlDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Bl Datatype Test Case";
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
		BlDataType searchObject = new BlDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType",searchObject );
		assertNotNull(results);
		assertEquals(4,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(4,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.TRUE));

		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		List index = new ArrayList();
		index.add("2");
		assertValue1(result, index);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.TRUE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType a where a.value1.value = ?  order by a.id", params);
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		List index = new ArrayList();
		index.add("2");
		assertValue1(result, index);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.add(Property.forName("value2.value").eq(Boolean.FALSE));

		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		List index = new ArrayList();
		index.add("4");
		assertValue2(result, index);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.FALSE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType a where a.value2.value = ?  order by a.id", params);
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(1, result.size());
		List index = new ArrayList();
		index.add("4");
		assertValue2(result, index);
	}

	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<BlDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType");
		assertEquals(4, result.size());
		assertValue1(result, null);
		assertValue2(result, null);
	}

	private void assertValue1(Collection<BlDataType> result, List index)
	{
		assertNotNull(result);
		int counter = 1;
		for(BlDataType data :  result)
		{
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				//Local constant overriding global constant
				assertEquals(NullFlavor.UNK, data.getValue1().getNullFlavor());
				if(index != null) index.remove("1");
				counter++;
			}
			
			//Record2
			else if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				assertNotNull(data.getValue1());
				assertEquals(Boolean.TRUE, data.getValue1().getValue());
				assertNull(data.getValue1().getNullFlavor());
				if(index != null) index.remove("2");
				counter++;
			}
			
			//Record3
			else if((index == null && counter == 3) || (index != null && index.contains("3")))
			{
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				//Local constant overriding global constant
				assertEquals(NullFlavor.UNK, data.getValue1().getNullFlavor());
				if(index != null) index.remove("3");
				counter++;
			}
			
			//Record4
			else if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				//Local constant overriding global constant
				assertEquals(NullFlavor.UNK, data.getValue1().getNullFlavor());
				if(index != null) index.remove("4");
				counter++;
			}
		}
	}

	private void assertValue2(Collection<BlDataType> result, List index)
	{
		assertNotNull(result);
		int counter = 1;
		for(BlDataType data :  result)
		{
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				assertNull(data.getValue2());
				if(index != null) index.remove("1");
				counter++;
			}
			
			//Record2
			else if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				assertNull(data.getValue2());
				if(index != null) index.remove("2");
				counter++;
			}
			
			//Record3
			else if((index == null && counter == 3) || (index != null && index.contains("3")))
			{
				assertNotNull(data.getValue2());
				assertEquals(Boolean.TRUE, data.getValue2().getValue());
				//From database 
				assertNull(data.getValue2().getNullFlavor());
				if(index != null) index.remove("3");
				counter++;
			}
	
			//Record4
			else if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				assertNotNull(data.getValue2());
				assertEquals(Boolean.FALSE, data.getValue2().getValue());
				//From the database
				assertEquals(NullFlavor.INV, data.getValue2().getNullFlavor());
				if(index != null) index.remove("4");
				counter++;
			}
		}
	}
	
}
