/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.hql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.system.applicationservice.ApplicationException;
import test.gov.nih.nci.cacoresdk.SDKTestBase;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class HQLCriteriaTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "HQLCriteria Test Case";
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testHQLCriteria1() throws ApplicationException
	{
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select card from gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand as hand "
						+ "left join hand.cardCollection as card where hand.id='1'");
		Collection results = getApplicationService().query(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.levelassociation.Card");

		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	public void testHQLCriteria2() throws ApplicationException
	{
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card");
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(53, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	public void testHQLCriteria3() throws ApplicationException
	{
		List params = new ArrayList();
		params.add("Ace");
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card where name = ?", params);
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(4, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}


	public void testHQLCriteria4() throws ApplicationException
	{
		List params = new ArrayList();
		params.add("Ace");
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card where name = ?", params, 2);
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	public void testHQLCriteria5() throws ApplicationException
	{
		List params = new ArrayList();
		params.add("%");
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card where name like ? order by id", params, 0,5);
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(5, results.size());
		int count = 0;
		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			System.out.println(result.getId());
			System.out.println(result.getName());
			assertNotNull(result);
			if(count == 0)
			{
				assertEquals(new Integer(result.getId()), new Integer(1));
				assertEquals(result.getName(), "Ace");
			}
			else if(count == 1)
			{
				assertEquals(new Integer(result.getId()), new Integer(2));
				assertEquals(result.getName(), "Two");
			}
			else if(count == 2)
			{
				assertEquals(new Integer(result.getId()), new Integer(3));
				assertEquals(result.getName(), "Three");
			}
			else if(count == 3)
			{
				assertEquals(new Integer(result.getId()), new Integer(4));
				assertEquals(result.getName(), "Four");
			}
			else if(count == 4)
			{
				assertEquals(new Integer(result.getId()), new Integer(5));
				assertEquals(result.getName(), "Five");
			}
			count++;
		}
	}

	public void testHQLCriteria6() throws ApplicationException
	{
		List params = new ArrayList();
		params.add("%");
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card where name like ? order by id", params, 5,5);
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(5, results.size());
		int count = 0;
		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			System.out.println(result.getId());
			System.out.println(result.getName());
			assertNotNull(result);
			if(count == 0)
			{
				assertEquals(new Integer(result.getId()), new Integer(6));
				assertEquals(result.getName(), "Six");
			}
			else if(count == 1)
			{
				assertEquals(new Integer(result.getId()), new Integer(7));
				assertEquals(result.getName(), "Seven");
			}
			else if(count == 2)
			{
				assertEquals(new Integer(result.getId()), new Integer(8));
				assertEquals(result.getName(), "Eight");
			}
			else if(count == 3)
			{
				assertEquals(new Integer(result.getId()), new Integer(9));
				assertEquals(result.getName(), "Nine");
			}
			else if(count == 4)
			{
				assertEquals(new Integer(result.getId()), new Integer(10));
				assertEquals(result.getName(), "Ten");
			}
			count++;
		}
	}


	public void testHQLCriteria7() throws ApplicationException
	{
		List params = new ArrayList();
		params.add("%");
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.levelassociation.Card where name like ? order by id", params, 49,5);
		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(4, results.size());
		int count = 0;
		for (Iterator i = results.iterator(); i.hasNext();) {
			Card result = (Card) i.next();
			System.out.println(result.getId());
			System.out.println(result.getName());
			assertNotNull(result);
			if(count == 0)
			{
				assertEquals(new Integer(result.getId()), new Integer(50));
				assertEquals(result.getName(), "Jack");
			}
			else if(count == 1)
			{
				assertEquals(new Integer(result.getId()), new Integer(51));
				assertEquals(result.getName(), "Queen");
			}
			else if(count == 2)
			{
				assertEquals(new Integer(result.getId()), new Integer(52));
				assertEquals(result.getName(), "King");
			}
			else if(count == 3)
			{
				assertEquals(new Integer(result.getId()), new Integer(53));
				assertEquals(result.getName(), "Joker");
			}
			count++;
		}
	}

}
