package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for BlNonNull ( BL.NONNULL ) data type
 */
public class BlNonNullDataTypeTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Bl NonNull Datatype Test Case";
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
	public void testBlNonNullValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.add(Property.forName("value1.value").isNull());

		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		BlNonNullDataType data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertNull(data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNonNullValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType a where a.value1.value is null");
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		BlNonNullDataType data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertNull(data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNotNullValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(BlNonNullDataType.class);
		criteria.add(Property.forName("value1.value").eq(Boolean.TRUE));

		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		BlNonNullDataType data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertEquals(Boolean.TRUE, data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNotNullValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(Boolean.TRUE);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType a where a.value1.value = ?  order by a.id", params);
		Collection<BlNonNullDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType");
		assertEquals(1, result.size());
		BlNonNullDataType data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertEquals(Boolean.TRUE, data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
	}

	/**
	 * Test Value1 for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testBlNonNullValue() throws ApplicationException
	{
		BlNonNullDataType searchObject = new BlNonNullDataType();
		Collection<BlNonNullDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType",searchObject );
		assertEquals(2, result.size());

		BlNonNullDataType data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertNull(data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		
		data = result.iterator().next();
		assertNotNull(data.getValue1());
		assertEquals(Boolean.TRUE, data.getValue1().getValue());
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());

	}
}
