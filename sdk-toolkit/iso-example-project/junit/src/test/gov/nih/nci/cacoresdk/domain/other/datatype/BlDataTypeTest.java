package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

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
		assertValue1(result, 2);
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
		assertValue1(result, 2);
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
		assertValue2(result, 4);
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
		assertValue2(result, 4);
	}

	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue() throws ApplicationException
	{
		BlDataType searchObject = new BlDataType();
		Collection<BlDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType",searchObject );
		assertValue1(result, 0);
		assertValue2(result, 0);
	}

	private void assertValue1(Collection<BlDataType> result, int index)
	{
		assertNotNull(result);

		//Record1
		BlDataType data = null;
		
		if(result.iterator().hasNext() && (index == 1 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue1());
			assertNull(data.getValue1().getValue());
			assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		}
		
		//Record2
		if(result.iterator().hasNext() && (index == 2 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue1());
			assertEquals(Boolean.TRUE, data.getValue1().getValue());
			assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		}
		
		//Record3
		if(result.iterator().hasNext() && (index == 3 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue1());
			assertNull(data.getValue1().getValue());
			assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		}
		
		//Record4
		if(result.iterator().hasNext() && (index == 4 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue1());
			assertNull(data.getValue1().getValue());
			assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		}
	}

	private void assertValue2(Collection<BlDataType> result, int index)
	{
		assertNotNull(result);

		//Record1
		BlDataType data = null;
		if(result.iterator().hasNext() && (index == 1 || index == 0))
		{
			data = result.iterator().next();
			assertNull(data.getValue2());
		}
		
		//Record2
		if(result.iterator().hasNext() && (index == 2 || index == 0))
		{
			data = result.iterator().next();
			assertNull(data.getValue2());
		}
		
		//Record3
		if(result.iterator().hasNext() && (index == 3 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue2());
			assertNull(data.getValue2().getValue());
			assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
		}

		//Record4
		if(result.iterator().hasNext() && (index == 4 || index == 0))
		{
			data = result.iterator().next();
			assertNotNull(data.getValue2());
			assertEquals(Boolean.FALSE, data.getValue2().getValue());
			assertNull(data.getValue2().getNullFlavor());
		}
	}
	
}
