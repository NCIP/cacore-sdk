package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class LevelAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Level association XML Data Test Case";
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
		Hand searchObject = new Hand();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Hand result = (Hand)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Hand result2 = (Hand)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
		Card searchObject = new Card();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(53,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card result = (Card)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"image",result.getImage());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Card result2 = (Card)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			if (result2.getId() == 1)
				assertNotNull(result2.getImage());// Only row with id=1 has an image
			assertNotNull(result2.getName());		
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
		Suit searchObject = new Suit();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Suit result = (Suit)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Suit result2 = (Suit)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
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
		Deck searchObject = new Deck();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Deck result = (Deck)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Deck result2 = (Deck)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}


	/**
	 * Verifies all the associations in the object graph
	 * 
	 * @throws Exception
	 */
	public void testNullAssociationsInBeans() throws Exception
	{
		Card searchObject = new Card();
		searchObject.setId(new Integer(53));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Card card = (Card)i.next();
		toXML(card);
		Card result2 = (Card)fromXML(card);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		
		Suit suit = result2.getSuit();
		assertNull(suit);
	}

	/**
	 * Verifies all the associations in the object graph
	 * 
	 * @throws Exception
	 */
	public void testAssociationsInBeans() throws Exception
	{
		Hand searchObject = new Hand();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Iterator i = results.iterator();
		Card card = (Card)i.next();
		toXML(card);
		Card result2 = (Card)fromXML(card);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		
		validateAssociation(card,"Suit","suit", true, false);
		
		Suit suit = result2.getSuit();
		
		assertNotNull(suit);
		assertNull(suit.getCardCollection());
//		assertEquals(suit.getCardCollection().size(),13);
//		
//		Deck deck = suit.getDeck();
//		
//		assertNotNull(deck);
//		assertEquals(deck.getSuitCollection().size(),4);
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
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card card = (Card)i.next();
			toXML(card);
			Card result2 = (Card)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card,gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Card card = (Card)i.next();
			toXML(card);
			Card result2 = (Card)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Suit card = (Suit)i.next();
			toXML(card);
			Suit result2 = (Suit)fromXML(card);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck,gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit,gov.nih.nci.cacoresdk.domain.other.levelassociation.Card",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Deck result = (Deck)i.next();
			toXML(result);
			Deck result2 = (Deck)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
		}
	}
}
