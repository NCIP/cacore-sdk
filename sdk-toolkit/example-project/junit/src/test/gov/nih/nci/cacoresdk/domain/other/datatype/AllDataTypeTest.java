package test.gov.nih.nci.cacoresdk.domain.other.datatype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;
import gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.nestedcriteria.NestedCriteriaPath;
import test.gov.nih.nci.cacoresdk.SDKTestBase;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class AllDataTypeTest extends SDKTestBase
{
	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "All Datatype Test Case";
	}

	/**
	 * Verifies that all the values in the object are present
	 * @param result
	 */
	private void validateObject(AllDataType result)
	{
		assertNotNull(result.getBooleanPrimitiveValue());
		assertNotNull(result.getBooleanValue());
		assertNotNull(result.getCharacterPrimitiveValue());
		assertNotNull(result.getCharacterValue());
		assertNotNull(result.getClobValue());
		assertNotNull(result.getDatePrimitiveValue());
		assertNotNull(result.getDateValue());
		assertNotNull(result.getDoublePrimitiveValue());
		assertNotNull(result.getDoubleValue());
		assertNotNull(result.getFloatPrimitiveValue());
		assertNotNull(result.getFloatValue());
		assertNotNull(result.getId());
		assertNotNull(result.getIntValue());
		assertNotNull(result.getIntPrimitiveValue());
		assertNotNull(result.getLongPrimitiveValue());
		assertNotNull(result.getLongValue());
		assertNotNull(result.getStringCollection());
		assertNotNull(result.getStringPrimitiveValue());
		assertNotNull(result.getStringValue());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
			validateObject((AllDataType)i.next());
	}

	/**
	 * Uses CQL Search Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);


		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
			validateObject((AllDataType)i.next());
	}


	/**
	 * Uses Class for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectClassName() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
			validateObject((AllDataType)i.next());
	}


	/**
	 * Uses Nested Search Criteria for search where there is no association between two objects
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testNestedSearchNoAssociation() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",new DoubleKey());
		}
		catch(ApplicationException ae)
		{
			flag = true;
		}
		assertTrue(flag);
	}

	/**
	 * Uses Nested Search Criteria for search using object list where list has zero object
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testNestedSearchObjectListZeroEntry() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			List objList = new ArrayList();
			Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",objList);
		}
		catch(ApplicationException ae)
		{
			flag = true;
		}
		assertTrue(flag);
	}

	/**
	 * Uses Nested Search Criteria for search using object list where list has only one object
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testNestedSearchObjectListOneEntry() throws ApplicationException
	{
		AllDataType searchObject1 = new AllDataType();
		searchObject1.setIntValue(new Integer(5));

		List objList = new ArrayList();
		objList.add(searchObject1);

		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",objList);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Nested Search Criteria for search using object list where list has two objects
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testNestedSearchObjectListTwoEntry() throws ApplicationException
	{
		AllDataType searchObject1 = new AllDataType();
		searchObject1.setIntValue(new Integer(5));

		AllDataType searchObject2 = new AllDataType();
		searchObject2.setBooleanValue(new Boolean(false));

		List objList = new ArrayList();
		objList.add(searchObject1);
		objList.add(searchObject2);

		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",objList);

		assertNotNull(results);
		assertEquals(3,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Boolean Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testBooleanPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setBooleanPrimitiveValue(true);
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Boolean data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testBooleanDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setBooleanValue(new Boolean(true));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Character Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testCharacterPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setCharacterPrimitiveValue('a');
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Character data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testCharacterDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setCharacterValue(new Character('a'));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Clob data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testClobDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setClobValue("0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012");
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
	}



	/**
	 * Uses Class for search
	 * Searches by the Date Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDatePrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setDatePrimitiveValue(new Date("10/1/2007"));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Date data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDateDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setDateValue(new Date("3/3/2003"));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Double Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDoublePrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setDoublePrimitiveValue(10001.00);
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Double data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDoubleDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setDoubleValue(new Double(555.55));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Float Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testFloatPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setFloatPrimitiveValue(new Float(10.01));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Float data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testFloatDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setFloatValue(new Float(555.55));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Integer Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testIntegerPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setIntPrimitiveValue(new Integer(11));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}
	/**
	 * Uses Class for search
	 * Searches by the Integer data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testIntegerDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setIntValue(new Integer(5));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the Long Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testLongPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setLongPrimitiveValue(new Long(1000001));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}
	/**
	 * Uses Class for search
	 * Searches by the Long data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testLongDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setLongValue(new Long(1000001));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testStringDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setStringValue(new String("String_Value5"));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testStringPrimitiveDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setStringPrimitiveValue(new String("abc"));
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Class for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testStringCollectionDataType() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();

//		Collection<String> col = new ArrayList<String>();
//		col.add("String_Collection 1");
//		col.add("String_Collection 2");
//		col.add("String_Collection 3");
//
//		searchObject.setStringCollection(col);

		searchObject.setId(1);
		Collection results = getApplicationService().search(AllDataType.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();){
			Collection col = ((AllDataType)i.next()).getStringCollection();
			assertEquals(3, col.size());
		}
	}
	/**
	 * Verifies size of the result set from query row count method where criteria is HQL type
	 *
	 * @throws ApplicationException
	 */
	public void testQueryRowCountHQL() throws ApplicationException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType a where a.intValue > 3");
		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");

		assertEquals(2,count);
	}


	/**
	 * Verifies size of the result set from query row count method where criteria is Detached Criteria type
	 *
	 * @throws ApplicationException
	 */
	public void testQueryRowCountDetachedCriteria() throws ApplicationException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(AllDataType.class);
		criteria.add(Property.forName("intValue").gt(new Integer(3)));

		int count = getApplicationService().getQueryRowCount(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");

		assertEquals(2,count);
	}

	/**
	 * Verifies size of the result set from query row count method where criteria is Nested Criteria type
	 *
	 * @throws ApplicationException
	 */
	public void testQueryRowCountNestedCriteria() throws ApplicationException
	{
		AllDataType searchObject = new AllDataType();
		searchObject.setIntValue(new Integer(5));
		List objList = new ArrayList();
		objList.add(searchObject);

		NestedCriteriaPath path = new NestedCriteriaPath("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType",objList);
		int count = getApplicationService().getQueryRowCount(path, "gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");

		assertEquals(1,count);
	}


	/**
	 * Uses CQL for search
	 * Searches by the Boolean data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testBooleanPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("booleanPrimitiveValue",CQLPredicate.EQUAL_TO,"true"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Boolean data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testBooleanDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("booleanValue",CQLPredicate.EQUAL_TO,"true"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Character Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testCharacterDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("characterValue",CQLPredicate.EQUAL_TO,"a"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Clob data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testClobDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("clobValue",CQLPredicate.LIKE,"0123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340112340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012340123456789012"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(5,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Character data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testCharacterPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("characterPrimitiveValue",CQLPredicate.EQUAL_TO,"a"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Date for search
	 * Searches by the Date data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDateDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("dateValue",CQLPredicate.EQUAL_TO,"03/03/2003"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses Date for search
	 * Searches by the Date data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDatePrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("datePrimitiveValue",CQLPredicate.EQUAL_TO,"10/1/2007"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Double data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDoubleDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("doubleValue",CQLPredicate.EQUAL_TO,"555.55"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Double Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testDoublePrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("doublePrimitiveValue",CQLPredicate.EQUAL_TO,"10001.00"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Float data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testFloatDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("floatValue",CQLPredicate.EQUAL_TO,"555.55"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Float Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testFloatPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("floatPrimitiveValue",CQLPredicate.EQUAL_TO,"10.01"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Integer data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testIntegerDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("intValue",CQLPredicate.EQUAL_TO,"5"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Integer Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testIntegerPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("intPrimitiveValue",CQLPredicate.EQUAL_TO,"11"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Long data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testLongDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("longValue",CQLPredicate.EQUAL_TO,"1000001"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the Long Primitive data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testLongPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("longPrimitiveValue",CQLPredicate.EQUAL_TO,"1000001"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testStringDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("stringValue",CQLPredicate.EQUAL_TO,"String_Value5"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

	/**
	 * Uses CQL for search
	 * Searches by the String data type
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testStringPrimitiveDataTypeCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType");
		object.setAttribute(new CQLAttribute("stringPrimitiveValue",CQLPredicate.EQUAL_TO,"def"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}
}