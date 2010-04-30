package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType;
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
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
 * Test case for Ii ( II ) data type
 * 
 */
public class IiDataTypeTest extends SDKISOTestBase{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ii Datatype Test Case";
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
		IiDataType searchObject = new IiDataType();
		Collection results = search("gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType",searchObject );
		assertNotNull(results);
		assertEquals(19,results.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertEquals(19,count);
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue1ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value1.extension").eq("II_Extension"));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		assertNotNull(result);
		
		List indexList = new ArrayList();
		indexList.add("2");
		assertValue1(result, indexList);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue1ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add("II_Extension");
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value1.extension = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("2");
		assertValue1(result, indexList);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue2ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value2.nullFlavor").eq(NullFlavor.NI));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("4");
		indexList.add("5");
		indexList.add("6");
		assertValue2(result, indexList);
	}
	

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue2ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.NI);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value2.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("4");
		indexList.add("5");
		indexList.add("6");
		assertValue2(result, indexList);
	}

	/**
	 * Search Value3 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue3ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value3.nullFlavor").eq(NullFlavor.UNK));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		indexList.add("11");
		indexList.add("12");
		assertValue3(result, indexList);
	}
	

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue3ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.UNK);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value3.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("8");
		indexList.add("9");
		indexList.add("10");
		indexList.add("11");
		indexList.add("12");
		assertValue3(result, indexList);
	}

	/**
	 * Search Value4 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue4ByDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IiDataType.class);
		criteria.add(Property.forName("value4.nullFlavor").eq(NullFlavor.INV));

		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("14");
		indexList.add("15");
		indexList.add("16");
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		assertValue4(result, indexList);
	}
	

	/**
	 * Search Value4 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue4ByHQLCriteria() throws ApplicationException
	{
		List params = new ArrayList(1);
		params.add(NullFlavor.INV);
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType a where a.value4.nullFlavor = ? order by a.id asc", params);
		Collection<IiDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType");
		List indexList = new ArrayList();
		indexList.add("14");
		indexList.add("15");
		indexList.add("16");
		indexList.add("17");
		indexList.add("18");
		indexList.add("19");
		assertValue4(result, indexList);
	}
	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIiValue() throws ApplicationException
	{
		IiDataType searchObject = new IiDataType();
		Collection<IiDataType> result = search("gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType",searchObject );
		assertValue1(result, null);
		assertValue2(result, null);
		assertValue3(result, null);
		assertValue4(result, null);
	}
	
	private void assertValue1(Collection<IiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
		{
			//Validate 2nd record
			if((index == null && counter == 2) || (index != null && index.contains("2")))
			{
				if(index != null) 
					index.remove("2");

				assertNotNull(data);

				assertEquals("II_Extension", data.getValue1().getExtension());
				assertValue1Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue1());
				assertNull(data.getValue1().getExtension());
				assertValue1Constants(data);
				counter++;
			}
		}
	}

	private void assertValue1Constants(IiDataType data)
	{
		//Local constant
		assertNull(data.getValue1().getNullFlavor());
		//Global constant
		assertEquals("2.16.12.123.456.1", data.getValue1().getRoot());
		assertNull(data.getValue1().getIdentifierName());
		//Global constant
		assertEquals(IdentifierReliability.ISS, data.getValue1().getReliability());
		//Global constant
		assertEquals(IdentifierScope.OBJ, data.getValue1().getScope());
		//Global constant
		assertEquals(Boolean.TRUE, data.getValue1().getDisplayable());
	}
	
	private void assertValue2(Collection<IiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
		{
			//Validate 4th record
			if((index == null && counter == 4) || (index != null && index.contains("4")))
			{
				if(index != null) 
					index.remove("4");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertEquals(NullFlavor.NI, data.getValue2().getNullFlavor());
				assertNull(data.getValue2().getRoot());
				assertNull(data.getValue2().getExtension());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 5th record
			else if((index == null && counter == 5) || (index != null && index.contains("5")))
			{
				if(index != null) 
					index.remove("5");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("II_VALUE2_ROOT", data.getValue2().getRoot());
				assertNull(data.getValue2().getExtension());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate 6th record
			else if((index == null && counter == 6) || (index != null && index.contains("6")))
			{
				if(index != null) 
					index.remove("6");

				assertNotNull(data);
				assertNotNull(data.getValue2());
				assertNull(data.getValue2().getNullFlavor());
				assertEquals("II_VALUE2_ROOT", data.getValue2().getRoot());
				assertEquals("II_VALUE2_EXTENSION", data.getValue2().getExtension());
				assertValue2Constants(data);
				counter++;
				continue;
			}
			//Validate all remaining record
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue2());
	
				assertNull(data.getValue2().getExtension());
				//From global constant
				assertEquals("2.16.12.123.456.1", data.getValue2().getRoot());
				assertValue2Constants(data);
				counter++;
			}
		}
	}

	private void assertValue2Constants(IiDataType data)
	{
		assertNull(data.getValue2().getIdentifierName());
		//Global constant
		assertEquals(IdentifierReliability.ISS, data.getValue2().getReliability());
		//Global constant
		assertEquals(IdentifierScope.OBJ, data.getValue2().getScope());
		//Global constant
		assertEquals(Boolean.TRUE, data.getValue2().getDisplayable());
		assertNull(data.getValue2().getNullFlavor());
	}

	private void assertValue3(Collection<IiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
		{
			//Validate 8th record
			if((index == null && counter == 8) || (index != null && index.contains("8")))
			{
				if(index != null) 
					index.remove("8");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				//From database
				assertEquals(NullFlavor.UNK, data.getValue3().getNullFlavor());
				assertNull(data.getValue3().getExtension());
				assertNull(data.getValue3().getIdentifierName());
				assertNull(data.getValue3().getReliability());
				assertNull(data.getValue3().getScope());
				assertNull(data.getValue3().getDisplayable());

				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 9th record
			else if((index == null && counter == 9) || (index != null && index.contains("9")))
			{
				if(index != null) 
					index.remove("9");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("II_VALUE3_EXTENSION", data.getValue3().getExtension());
				assertNull(data.getValue3().getIdentifierName());
				assertNull(data.getValue3().getReliability());
				assertNull(data.getValue3().getScope());
				assertNull(data.getValue3().getDisplayable());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 10th record
			else if((index == null && counter == 10) || (index != null && index.contains("10")))
			{
				if(index != null) 
					index.remove("10");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("II_VALUE3_EXTENSION", data.getValue3().getExtension());
				assertEquals("II_VALUE3_IDENTIFIER_NAME", data.getValue3().getIdentifierName());
				assertNull(data.getValue3().getReliability());
				assertNull(data.getValue3().getScope());
				assertNull(data.getValue3().getDisplayable());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//Validate 11th record
			else if((index == null && counter == 11) || (index != null && index.contains("11")))
			{
				if(index != null) 
					index.remove("11");

				assertNotNull(data);
				assertNotNull(data.getValue3());
				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("II_VALUE3_EXTENSION", data.getValue3().getExtension());
				assertEquals("II_VALUE3_IDENTIFIER_NAME", data.getValue3().getIdentifierName());
				//From database
				assertEquals(IdentifierReliability.ISS, data.getValue3().getReliability());
				//From database
				assertEquals(IdentifierScope.BUSN, data.getValue3().getScope());
				assertNull(data.getValue3().getDisplayable());
				assertValue3Constants(data);
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
				//From database
				assertNull(data.getValue3().getNullFlavor());
				assertEquals("II_VALUE3_EXTENSION", data.getValue3().getExtension());
				assertEquals("II_VALUE3_IDENTIFIER_NAME", data.getValue3().getIdentifierName());
				//From database, overriding global constant
				assertEquals(IdentifierReliability.VRF, data.getValue3().getReliability());
				//From database, overriding global constant
				assertEquals(IdentifierScope.BUSN, data.getValue3().getScope());
				//Overriding Global constant
				assertEquals(Boolean.FALSE, data.getValue3().getDisplayable());
				assertValue3Constants(data);
				counter++;
				continue;
			}
			//All remaining records
			else
			{
				assertNotNull(data);
				assertNotNull(data.getValue3());
				assertValue3Constants(data);
				assertNull(data.getValue3().getNullFlavor());
				//Global constant
				assertNull(data.getValue3().getReliability());
				//Global constant
				assertNull(data.getValue3().getScope());
				//Global constant
				assertNull(data.getValue3().getDisplayable());
				assertNull(data.getValue3().getExtension());
				assertNull(data.getValue3().getIdentifierName());
				counter++;
			}
		}
	}

	private void assertValue3Constants(IiDataType data)
	{
		//From local constant overriding global constant
		assertEquals("2.16.12.123.456", data.getValue3().getRoot());
	}
	
	private void assertValue4(Collection<IiDataType> result, List<Integer> index)
	{
		assertNotNull(result);

		int counter = 1;
		for(IiDataType data : result)
		{
			//Validate 14th record
			if((index == null && counter == 14) || (index != null && index.contains("14")))
			{
				if(index != null) 
					index.remove("14");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertEquals(NullFlavor.INV, data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertNull(data.getValue4().getRoot());
				assertNull(data.getValue4().getExtension());
				assertNull(data.getValue4().getIdentifierName());
				assertNull(data.getValue4().getReliability());
				assertNull(data.getValue4().getScope());
				assertNull(data.getValue4().getDisplayable());
				counter++;
				continue;
			}
			//Validate 15th record
			else if((index == null && counter == 15) || (index != null && index.contains("15")))
			{
				if(index != null) 
					index.remove("15");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNull(data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertEquals("II_VALUE4_ROOT", data.getValue4().getRoot());
				assertEquals("II_VALUE4_EXTENSION", data.getValue4().getExtension());
				assertNull(data.getValue4().getIdentifierName());
				assertNull(data.getValue4().getReliability());
				assertNull(data.getValue4().getScope());
				assertNull(data.getValue4().getDisplayable());
				counter++;
				continue;
			}
			//Validate 16th record
			else if((index == null && counter == 16) || (index != null && index.contains("16")))
			{
				if(index != null) 
					index.remove("16");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNull(data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertEquals("II_VALUE4_ROOT", data.getValue4().getRoot());
				assertEquals("II_VALUE4_EXTENSION", data.getValue4().getExtension());
				assertEquals("II_VALUE4_IDENTIFIER_NAME", data.getValue4().getIdentifierName());
				assertNull(data.getValue4().getReliability());
				assertNull(data.getValue4().getScope());
				assertNull(data.getValue4().getDisplayable());
				counter++;
				continue;
			}
			//Validate 17th record
			else if((index == null && counter == 17) || (index != null && index.contains("17")))
			{
				if(index != null) 
					index.remove("17");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNull(data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertEquals("II_VALUE4_ROOT", data.getValue4().getRoot());
				assertEquals("II_VALUE4_EXTENSION", data.getValue4().getExtension());
				assertEquals("II_VALUE4_IDENTIFIER_NAME", data.getValue4().getIdentifierName());
				assertEquals(IdentifierReliability.UNV, data.getValue4().getReliability());
				assertNull(data.getValue4().getScope());
				assertNull(data.getValue4().getDisplayable());
				counter++;
				continue;
			}

			//Validate 18th record
			else if((index == null && counter == 18) || (index != null && index.contains("18")))
			{
				if(index != null) 
					index.remove("18");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNull(data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertEquals("II_VALUE4_ROOT", data.getValue4().getRoot());
				assertEquals("II_VALUE4_EXTENSION", data.getValue4().getExtension());
				assertEquals("II_VALUE4_IDENTIFIER_NAME", data.getValue4().getIdentifierName());
				assertEquals(IdentifierReliability.ISS, data.getValue4().getReliability());
				assertEquals(IdentifierScope.BUSN, data.getValue4().getScope());
				assertNull(data.getValue4().getDisplayable());
				counter++;
				continue;
			}
			//Validate 19th record
			else if((index == null && counter == 19) || (index != null && index.contains("19")))
			{
				if(index != null) 
					index.remove("19");

				assertNotNull(data);
				assertNotNull(data.getValue4());
				assertNull(data.getValue4().getNullFlavor());
				//From the database - Overriding global constant
				assertEquals("II_VALUE4_ROOT", data.getValue4().getRoot());
				assertEquals("II_VALUE4_EXTENSION", data.getValue4().getExtension());
				assertEquals("II_VALUE4_IDENTIFIER_NAME", data.getValue4().getIdentifierName());
				assertEquals(IdentifierReliability.VRF, data.getValue4().getReliability());
				assertEquals(IdentifierScope.BUSN, data.getValue4().getScope());
				assertEquals(Boolean.FALSE, data.getValue4().getDisplayable());
				counter++;
				continue;
			}
			//All remaining records
			else
			{
				assertNotNull(data);
				assertNull(data.getValue4());
				counter++;
			}
		}
	}
	
}
