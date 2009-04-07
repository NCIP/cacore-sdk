package test.cql;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLGroup;
import gov.nih.nci.system.query.cql.CQLLogicalOperator;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class CQLQueryTest extends SDKTestBase
{
	
	protected static Logger log = Logger.getLogger(CQLQueryTest.class.getName());
	
	public static String getTestCaseName()
	{
		return "CQL With Association Query Test Case";
	}
	
	
	/**
	 * Uses CQL Query for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute are null
	 * @throws ApplicationException
	 */
	public void testCqlQuery() throws ApplicationException
	{
	
		CQLQuery cqlQuery = new CQLQuery();

		CQLObject target = new CQLObject();
		target.setName("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit");

		CQLAttribute attribute = new CQLAttribute();
		attribute.setName("id");
		attribute.setValue("3");  // Suit = Diamond
		attribute.setPredicate(CQLPredicate.EQUAL_TO);

		target.setAttribute(attribute);

		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);
		
		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Suit suit = (Suit)i.next();
			assertNotNull(suit);
			assertNotNull(suit.getId());
			assertNotNull(suit.getName());
			
			assertTrue("Suit id must equal '3':",suit.getId()==3);
			
			Collection cardCollection = suit.getCardCollection();
			assertNotNull(cardCollection);
			assertTrue(cardCollection.size() == 13); //a suit has 13 cards
			
//			try {
//				System.out.println("Number of cards in Suit("+suit.getId()+"): " + cardCollection.size());
//				for(Object obj : cardCollection)
//				{
//					printObject(obj, Card.class);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			boolean cardFound = false;
			for (Iterator j = cardCollection.iterator();j.hasNext();){
				Card card = (Card)j.next();
				
				if (card.getId()==32)//suit 3 has card with id '32'
					cardFound = true;
			}
			assertTrue("Card with id 32 not found",cardFound);
			
			Deck deck = suit.getDeck();
			assertNotNull(deck);
			assertNotNull(deck.getId());
			assertNotNull(deck.getName());
		}
	}
	
	/**
	 * Uses CQL Query for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute are null
	 * @throws ApplicationException
	 */
	public void testCqlQueryWithAssociation() throws ApplicationException
	{

		CQLQuery cqlQuery = new CQLQuery();
		
		CQLObject target = new CQLObject();
		target.setName("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit");
		
		//Create an Association to a Card instance in the Spade Suit
		CQLAssociation association1 = new CQLAssociation();
		association1.setName("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card");
		CQLAttribute attribute1 = new CQLAttribute();
		attribute1.setName("id");
		attribute1.setValue("2"); //Part of the Spade suit
		attribute1.setPredicate(CQLPredicate.EQUAL_TO);
		
		association1.setTargetRoleName("cardCollection");
		association1.setSourceRoleName("suit");
		association1.setAttribute(attribute1);
		
		//Create a second Association to a Card instance in the Diamond Suit
		CQLAssociation association2 = new CQLAssociation();
		association2.setName("gov.nih.nci.cacoresdk.domain.other.levelassociation.Card");
		CQLAttribute attribute2 = new CQLAttribute();
		attribute2.setName("id");
		attribute2.setValue("32");  //Part of the Diamond suit
		attribute2.setPredicate(CQLPredicate.EQUAL_TO);
		
		association2.setTargetRoleName("cardCollection");
		association2.setSourceRoleName("suit");
		association2.setAttribute(attribute2);
		
		//Add both associations to a Group
		CQLGroup group = new CQLGroup();
		group.addAssociation(association1);
		group.addAssociation(association2);
		group.setLogicOperator(CQLLogicalOperator.OR);
 		
		target.setGroup(group);
		
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);
		
		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Suit suit = (Suit)i.next();
			assertNotNull(suit);
			assertNotNull(suit.getId());
			assertNotNull(suit.getName());
			
			Collection cardCollection = suit.getCardCollection();
			assertNotNull(cardCollection);
			assertTrue(cardCollection.size() == 13);
			
//			try {
//				System.out.println("Number of cards in Suit("+suit.getId()+"): " + cardCollection.size());
//				for(Object obj : cardCollection)
//				{
//					printObject(obj, Card.class);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			boolean cardFound = false;
			for (Iterator j = cardCollection.iterator();j.hasNext();){
				Card card = (Card)j.next();
				
				if (card.getId()==2 || card.getId()==32)
					cardFound = true;
			}
			assertTrue("Card with id 2 or 32 not found",cardFound);
			
			Deck deck = suit.getDeck();
			assertNotNull(deck);
			assertNotNull(deck.getId());
			assertNotNull(deck.getName());
		}
	}
	
	protected void printObject(Object obj, Class klass) throws Exception {
		System.out.println("Printing "+ klass.getName());
		Method[] methods = klass.getMethods();
		for(Method method:methods)
		{
			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
			{
				System.out.print("\t"+method.getName().substring(3)+":");
				Object val = method.invoke(obj, (Object[])null);
				if(val instanceof java.util.Set)
					System.out.println("size="+((Collection)val).size());
				else
					System.out.println(val);
			}
		}
	}
}
