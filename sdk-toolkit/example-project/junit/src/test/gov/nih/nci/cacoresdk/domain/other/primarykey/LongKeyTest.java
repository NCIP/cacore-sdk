/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import test.gov.nih.nci.cacoresdk.SDKTestBase;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class LongKeyTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Long Key Test Case";
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
		LongKey searchObject = new LongKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LongKey result = (LongKey)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Class for search
	 * Searches by the primary key
	 * Verifies size of the result set
	 *
	 * @throws ApplicationException
	 */
	public void testPrimaryKeyNestedSearch() throws ApplicationException
	{
		LongKey searchObject = new LongKey();
		searchObject.setId(new Long("1234567890987650000"));
		Collection results = getApplicationService().search(LongKey.class,searchObject );

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
	public void testPrimaryKeyCQL() throws ApplicationException
	{
		CQLQuery criteria = new CQLQuery();

		CQLObject object = new CQLObject();
		object.setName("gov.nih.nci.cacoresdk.domain.other.primarykey.LongKey");
		object.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1234567890987650000"));
		criteria.setTarget(object);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(criteria, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());
	}

}
