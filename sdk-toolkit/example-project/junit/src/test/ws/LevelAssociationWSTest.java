/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;

import java.util.ArrayList;
import java.util.Collection;


public class LevelAssociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Level Association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Card.class);
		mappedKlasses.add(Deck.class);
		mappedKlasses.add(Hand.class);
		mappedKlasses.add(Suit.class);
		
		return mappedKlasses;
	}	
	 
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch1() throws Exception
	{
		Class targetClass = Hand.class;
		Hand criteria = new Hand();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Hand result = (Hand)obj;
			assertNotNull(result);
			assertNotNull(result.getId());	
		}			
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Class targetClass = Card.class;
		Card criteria = new Card();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(53,results.length);
		
		for (Object obj : results){
			Card result = (Card)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			if (result.getId() == 1)
				assertNotNull(result.getImage());// Only row with id=1 has an image
			assertNotNull(result.getName());			
		}	
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch3() throws Exception
	{
		Class targetClass = Suit.class;
		Suit criteria = new Suit();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Suit result = (Suit)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());			
		}	
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch4() throws Exception
	{
		Class targetClass = Deck.class;
		Deck criteria = new Deck();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for (Object obj : results){
			Deck result = (Deck)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());			
		}		
	}

	/**
	 * Verifies all the associations in the object graph
	 * 
	 * @throws Exception
	 */
	public void testNullAssociationsInBeans() throws Exception
	{
		Class targetClass = Card.class;
		Card criteria = new Card();
		criteria.setId(new Integer(53));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Card result = (Card)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] suitResults = getAssociationResults(result, "suit", 0);
		assertEquals(0,suitResults.length);
	
	}

	/**
	 * Verifies all the associations in the object graph
	 * 
	 * @throws Exception
	 */
	public void testAssociationsInBeans() throws Exception
	{
		Class targetClass = Card.class;
		Hand criteria = new Hand();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		Card card = (Card)results[0];
		assertNotNull(card);
		assertNotNull(card.getId());
		assertNotNull(card.getName());
		
		Object[] suitResults = getAssociationResults(card, "suit", 0);
		Suit suit = (Suit)suitResults[0];
		assertNotNull(suit);
		
		Object[] cardCollection = getAssociationResults(suit, "cardCollection", 0);
		assertEquals(13,cardCollection.length);
		
		Object[] deckResults = getAssociationResults(suit, "deck", 0);
		Deck deck = (Deck)deckResults[0];
		assertNotNull(deck);
		
		Object[] suitCollection = getAssociationResults(deck, "suitCollection", 0);
		assertEquals(4,suitCollection.length);
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
		Class targetClass = Card.class;
		Hand criteria = new Hand();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Card result = (Card)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());			
		}			
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	public void testOneLevelAssociationNestedSearch2() throws Exception
	{
		Hand searchObject = new Hand();
		searchObject.setId(new Integer(1));
		Object[] results = getQueryObjectResults("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card,gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand",searchObject );

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for(Object obj : results)
		{
			Card result = (Card)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	public void testTwoLevelAssociationNestedSearch() throws Exception
	{
		Hand searchObject = new Hand();
		searchObject.setId(new Integer(1));
		Object[] results = getQueryObjectResults("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for(Object obj : results)
		{
			Suit result = (Suit)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * 
	 * @throws ApplicationException
	 */
	public void testThreeLevelAssociationNestedSearch() throws Exception
	{
		Hand searchObject = new Hand();
		searchObject.setId(new Integer(1));
		Object[] results = getQueryObjectResults("gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck,gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for(Object obj : results)
		{
			Deck result = (Deck)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}	
}
