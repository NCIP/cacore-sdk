package test.gov.nih.nci.cacoresdk.domain.other.datatype;


import gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType;
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
 * Test case for BlNonNull ( BL.NONNULL ) data type
 */
public class BlNonNullDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "BlNonNull Datatype Test Case";
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
		BlNonNullDataType searchObject = new BlNonNullDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType",searchObject );
		assertNotNull(results);
		assertEquals(2,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(2,count);
	}
	

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.TRUE));

		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType a where a.value1.value = ?  order by a.id", params);
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		List index = new ArrayList();
		index.add("2");
		assertValue1(result, index);
	}


	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlValue() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(2, result.size());
		assertValue1(result, null);
	}

	private void assertValue1(Collection<BlNonNullDataType> result, List index)
	{
		assertNotNull(result);
		int counter = 1;
		for(BlNonNullDataType data :  result)
		{
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getValue());
				//Local constant overriding global constant
				assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
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
		}
	}

}
