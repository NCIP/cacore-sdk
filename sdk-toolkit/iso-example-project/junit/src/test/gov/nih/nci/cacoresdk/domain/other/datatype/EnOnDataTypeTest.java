package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType;
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
 * Test case for EnOn ( EN.ON ) data type
 */
public class EnOnDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "EnOn Datatype Test Case";
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
		EnOnDataType searchObject = new EnOnDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType",searchObject );
		assertNotNull(results);
		assertEquals(6,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
		assertEquals(6,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnOnValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(EnOnDataType.class);
		criteria.add(Property.forName("value1.part_0.value").isNotNull());

		Collection<EnOnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
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
	public void testEnOnValue1ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType a where a.value1.part_0.value is not null order by a.id asc");
		Collection<EnOnDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType");
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
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testEnOnValue() throws ApplicationException
	{
		EnOnDataType searchObject = new EnOnDataType();
		Collection<EnOnDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType",searchObject );
		assertValue1(result, null);
	}
	
	private void assertValue1(Collection<EnOnDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(EnOnDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("NCI1", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
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
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("NCI2", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				if(index != null) 
					index.remove("4");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("NCI3", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			//Validate 2nd record
			else if((index == null && counter == 5) || (index != null && index.contains("5")))
			{
				if(index != null) 
					index.remove("5");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("NCI4", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			else if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNotNull(data.getValue1().getPart());
				
				assertNull(data.getValue1().getPart().get(0).getCode());
				assertEquals("NCI5", data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1().getPart());
				assertNull(data.getValue1().getPart().get(0).getValue());
				assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue1Constants(EnOnDataType data)
	{
		assertEquals(NullFlavor.NI, data.getValue1().getNullFlavor());
		assertNull(data.getValue1().getPart().get(0).getCode());
		assertNull(data.getValue1().getPart().get(0).getCodeSystem());
		assertNull(data.getValue1().getPart().get(0).getCodeSystemVersion());
		assertNull(data.getValue1().getPart().get(0).getQualifier());
		assertNull(data.getValue1().getPart().get(0).getType());
	}
}
