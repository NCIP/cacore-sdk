/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;
import gov.nih.nci.iso21090.Ii;

import java.util.Collection;
import java.util.Iterator;


public class LevelAssociationXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Level association XML Data Test Case";
	}
	 
	/**
	 * Verifies all the associations in the object graph
	 * 
	 * @throws Exception
	 */
	public void testNullAssociationsInBeans() throws Exception
	{
		Card searchObject = new Card();
		Ii ii = new Ii();
		ii.setExtension("53");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Card card = (Card)i.next();
		toXML(card);
		Card result2 = (Card)fromXML(card);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());  
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		
		Suit suit = result2.getSuit();
		assertNull(suit);
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testOneLevelAssociationNestedSearch1() throws Exception
	{
		Hand searchObject = new Hand();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card card = (Card)i.next();
			toXML(card);
			Card result2 = (Card)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testOneLevelAssociationNestedSearch2() throws Exception
	{
		Hand searchObject = new Hand();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card,gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card card = (Card)i.next();
			toXML(card);
			Card result2 = (Card)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testTwoLevelAssociationNestedSearch() throws Exception
	{
		Hand searchObject = new Hand();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Suit card = (Suit)i.next();
			toXML(card);
			Suit result2 = (Suit)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testThreeLevelAssociationNestedSearch() throws Exception
	{
		Hand searchObject = new Hand();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck,gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Deck result = (Deck)i.next();
			toXML(result);
			Deck result2 = (Deck)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		}
	}
}
