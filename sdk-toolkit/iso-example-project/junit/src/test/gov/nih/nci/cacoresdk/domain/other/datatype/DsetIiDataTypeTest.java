package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType;
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for DsetIi ( DSET<II> ) data type
 */
public class DsetIiDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Dset Ii Datatype Test Case";
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
		DsetIiDataType searchObject = new DsetIiDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType",searchObject );
		assertNotNull(results);
		assertEquals(29,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertEquals(29,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue1ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value1.item.extension").like("Extension1%"));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		
		List indexList = new ArrayList();
		indexList.add("1");
		indexList.add("2");
		indexList.add("3");
		indexList.add("4");
		indexList.add("5");
		assertValue1(result, indexList);
	}
*/
	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("Extension%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value1.item.extension like ? order by a.id asc", params);
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("1");
		indexList.add("2");
		indexList.add("3");
		indexList.add("4");
		indexList.add("5");
		assertValue1(result, indexList);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue2ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value2.item.extension").like("Extension%"));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("6");
		indexList.add("7");
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		assertValue2(result, indexList);
	}
	*/

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("Extension%");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value2.item.extension like ? order by a.id asc", params);
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("6");
		indexList.add("7");
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		assertValue2(result, indexList);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue3ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Restrictions.or(Property.forName("value3.item.extension").isNotNull(), Property.forName("value3.item.identifier").isNotNull()));

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("11");
		indexList.add("12");
		indexList.add("13");
		indexList.add("14");
		indexList.add("15");
		assertValue3(result, indexList);
	}
*/	

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue3ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where (a.value3.item.extension is not null or a.value3.item.identifierName is not null) order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("11");
		indexList.add("12");
		indexList.add("13");
		indexList.add("14");
		indexList.add("15");
		assertValue3(result, indexList);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
/*	public void testDsetIiValue4ByDetachedCriteria() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		DetachedCriteria criteria = DetachedCriteria.forClass(DsetIiDataType.class);
		criteria.add(Property.forName("value4.item.root").isNotNull());

		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("16");
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		assertValue4(result, indexList);
	}
	
*/
	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue4ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value4.item.root is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("16");
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		indexList.add("20");
		assertValue4(result, indexList);
	}

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue5ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value5.item.extension is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("21");
		indexList.add("22");
		indexList.add("23");
		indexList.add("24");
		indexList.add("25");
		assertValue5(result, indexList);
	}
	
	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue6ByHQLCriteria() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType a where a.value6.item.extension is not null order by a.id asc");
		Collection<DsetIiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType");
		assertNotNull(result);
		assertEquals(5, result.size());
		List indexList = new ArrayList();
		indexList.add("26");
		indexList.add("27");
		indexList.add("28");
		indexList.add("29");
		indexList.add("30");
		assertValue6(result, indexList);
	}

	/**
	 * Test all columns and rows for correct values
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testDsetIiValue() throws ApplicationException
	{
		DsetIiDataType searchObject = new DsetIiDataType();
		Collection<DsetIiDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
		assertValue5(result, null);
		assertValue6(result, null);
	}
	
	private void assertValue1(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 1) || (index != null && index.contains("1")))
			{
				if(index != null) 
					index.remove("1");

				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertEquals("Extension2", item.getExtension());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 3) || (index != null && index.contains("3")))
			{
				if(index != null) 
					index.remove("3");

				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertEquals("Extension3", item.getExtension());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				if(index != null) 
					index.remove("4");

				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 5) || (index != null && index.contains("5")))
			{
				if(index != null) 
					index.remove("5");

				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				Ii item = data.getValue1().getItem().iterator().next();
				assertNotNull(item);
				assertNull(item.getNullFlavor());
				//Local constant
				assertEquals("2.16.12.123.456", item.getRoot());
	
				assertNull(item.getExtension());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
			}
		}
	}

	private void assertValue2(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertNull(item.getExtension());
			
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertNull(item.getIdentifierName());
				assertNull(item.getRoot());
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 7) || (index != null && index.contains("7")))
			{
				if(index != null) 
					index.remove("7");

				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertEquals("Extension2", item.getExtension());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertEquals("Extension3", item.getExtension());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
			
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				Ii item = data.getValue2().getItem().iterator().next();
				assertNotNull(item);
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertNull(item.getExtension());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
			}
		}
	}
	
	private void assertValue3(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				Ii item = data.getValue3().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				assertNull(item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getIdentifierName());
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 12) || (index != null && index.contains("12")))
			{
				if(index != null) 
					index.remove("12");

				assertNotNull(data);
				Ii item = data.getValue3().getItem().iterator().next();
				assertNull(item.getExtension());
				assertEquals("IDENTIFIER_NAME2", item.getIdentifierName());
				assertNull(item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 13) || (index != null && index.contains("13")))
			{
				if(index != null) 
					index.remove("13");

				assertNotNull(data);
				Ii item = data.getValue3().getItem().iterator().next();
				assertEquals("Extension3", item.getExtension());
				assertEquals("IDENTIFIER_NAME3", item.getIdentifierName());
				assertNull(item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				Ii item = data.getValue3().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
				assertEquals("IDENTIFIER_NAME4", item.getIdentifierName());
				assertNull(item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				Ii item = data.getValue3().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
				assertEquals("IDENTIFIER_NAME5", item.getIdentifierName());
				assertNull(item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getIdentifierName());
				assertNull(item.getReliability());
				assertNull(item.getScope());
				assertNull(item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				Ii item = data.getValue3().getItem().iterator().next();
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				//Global constant
				assertEquals("2.16.12.123.456.1", item.getRoot());
			
				assertNull(item.getIdentifierName());
				assertNull(item.getExtension());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
			}
		}
	}

	private void assertValue4(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				Ii item = data.getValue4().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				assertEquals("Root1", item.getRoot());
				assertEquals(Boolean.TRUE, item.getDisplayable());
			
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertNull(item.getIdentifierName());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				Ii item = data.getValue4().getItem().iterator().next();
				assertEquals("Extension2", item.getExtension());
				assertEquals("IDENTIFIER_NAME2", item.getIdentifierName());
				assertEquals(Boolean.TRUE, item.getDisplayable());

				assertNull(item.getNullFlavor());
				//Global constants
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				Ii item = data.getValue4().getItem().iterator().next();
				assertNull(item.getExtension());
				assertEquals("IDENTIFIER_NAME3", item.getIdentifierName());
				assertEquals("Root3", item.getRoot());
				
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertNull(item.getIdentifierName());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				//Global constants
				assertEquals(IdentifierScope.OBJ, item.getScope());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				Ii item = data.getValue4().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
				assertEquals("IDENTIFIER_NAME4", item.getIdentifierName());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertEquals("Root4", item.getRoot());
				
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.BUSN, item.getScope());
				assertEquals(Boolean.FALSE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 20) || (index != null && index.contains("20")))
			{
				if(index != null) 
					index.remove("20");

				assertNotNull(data);
				Ii item = data.getValue4().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
				assertEquals("IDENTIFIER_NAME5", item.getIdentifierName());
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertEquals("Root5", item.getRoot());
				
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.BUSN, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue4());
				counter++;
			}
		}
	}

	private void assertValue5(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 21) || (index != null && index.contains("21")))
			{
				if(index != null) 
					index.remove("21");

				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 22) || (index != null && index.contains("22")))
			{
				if(index != null) 
					index.remove("22");

				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertEquals("Extension2", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 23) || (index != null && index.contains("23")))
			{
				if(index != null) 
					index.remove("23");

				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertEquals("Extension3", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 24) || (index != null && index.contains("24")))
			{
				if(index != null) 
					index.remove("24");

				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 25) || (index != null && index.contains("25")))
			{
				if(index != null) 
					index.remove("25");

				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				Ii item = data.getValue5().getItem().iterator().next();
				assertNotNull(item);
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
	
				assertNull(item.getExtension());
				counter++;
			}
		}
	}

	private void assertValue6(Collection<DsetIiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(DsetIiDataType data : result)
		{
			//Validate 1st record
			if((index == null && counter == 26) || (index != null && index.contains("26")))
			{
				if(index != null) 
					index.remove("26");

				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 2nd record
			if((index == null && counter == 27) || (index != null && index.contains("27")))
			{
				if(index != null) 
					index.remove("27");

				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 3rd record
			if((index == null && counter == 28) || (index != null && index.contains("28")))
			{
				if(index != null) 
					index.remove("28");

				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertEquals("Extension1", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 4th record
			if((index == null && counter == 29) || (index != null && index.contains("29")))
			{
				if(index != null) 
					index.remove("29");

				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertEquals("Extension4", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate 5th record
			if((index == null && counter == 30) || (index != null && index.contains("30")))
			{
				if(index != null) 
					index.remove("30");

				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertEquals("Extension5", item.getExtension());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				Ii item = data.getValue6().getItem().iterator().next();
				assertNotNull(item);
				assertEquals(NullFlavor.NI, item.getNullFlavor());
				assertNull(item.getNullFlavor());
				assertNull(item.getIdentifierName());
				//Global constants
				assertEquals("2.16.12.123.456.1", item.getRoot());
				assertEquals(IdentifierReliability.ISS, item.getReliability());
				assertEquals(IdentifierScope.OBJ, item.getScope());
				assertEquals(Boolean.TRUE, item.getDisplayable());
				assertNull(item.getExtension());
				counter++;
			}
		}
	}
	
}
