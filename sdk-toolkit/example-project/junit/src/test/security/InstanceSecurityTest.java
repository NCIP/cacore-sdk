/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.security;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class InstanceSecurityTest extends SDKSecurityTestBase
{
	
	public static String getTestCaseName()
	{
		return "Instance Security Test Case";
	}
	
	/**
	 * Uses Query by Example for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testClassListSearch() throws Exception
	{
		Card card = new Card();
		
		List<Card> objList = new ArrayList<Card>();
		objList.add(card);
		
		Collection results = getAppSvcUser2().search(Card.class,objList);

		assertNotNull(results);
		
		if (enableInstanceLevelSecurity)
			assertEquals(52,results.size()); //Make sure that all cards are returned if not traversed via Deck -> Suit -> Card filter path
		else 
			assertEquals(53,results.size()); //Make sure that all cards are returned if not traversed via Deck -> Suit -> Card filter path
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card result = (Card)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			if (enableAttributeLevelSecurity){//user2 does not have access to image attribute if attribute level security is enabled
				assertNull(result.getImage());
			} else{
//				if (result.getId() == 1)
//					assertNotNull(result.getImage());// Only row with id=1 has an image
			}
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
	public void testThreeLevelAssociationNestedSearch() throws Exception
	{
		// When traversed via Deck -> Suit -> Card path, only cards with Name = 'Ace' should be returned
		Deck searchObject = new Deck();
		Collection results = getAppSvcUser2().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Deck deck = (Deck)i.next();
			assertNotNull(deck);
			assertNotNull(deck.getId());
			assertEquals(new Integer(1),deck.getId());

			if (enableAttributeLevelSecurity){
				assertNull(deck.getName());
			} else{
				assertNotNull(deck.getName());
			}
			
			for(Iterator j = deck.getSuitCollection().iterator();i.hasNext();){
				Suit suit = (Suit)j.next();
				assertNotNull(suit);
				assertNotNull(suit.getId());

				// Only cards with Name = 'Ace' should be returned
				assertEquals(1, suit.getCardCollection().size());

				for(Iterator k = suit.getCardCollection().iterator();i.hasNext();){
					Card card = (Card)k.next();
					assertNotNull(card);
					assertNotNull(card.getId());
					assertNotNull(card.getName());

					// Only cards with Name = 'Ace' should be returned
					assertEquals(card.getName(),"Ace");
				}
			}
		}
	}

}
