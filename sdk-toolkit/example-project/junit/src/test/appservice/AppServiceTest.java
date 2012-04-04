package test.appservice;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;
import gov.nih.nci.system.applicationservice.ApplicationException;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class AppServiceTest extends SDKTestBase
{

	protected static Logger log = Logger.getLogger(AppServiceTest.class.getName());

	public static String getTestCaseName()
	{
		return "Application Service Test Case";
	}


	/**
	 * Uses CQL Query for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute are null
	 * @throws ApplicationException
	 */
	public void testAppServiceFromURL() throws ApplicationException
	{

		Suit searchObject = new Suit();
		searchObject.setId(3);

		Collection results=null;
		try {
			results = getApplicationServiceFromUrl().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit",searchObject );
		} catch (Exception e) {
			e.printStackTrace();
			fail(getTestCaseName() + " failed with the following error: " + e.getMessage());
		}

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
//				for(java.lang.Object obj : cardCollection)
//				{
//					printObject(obj, Card.class);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				fail(getTestCaseName() + " failed with the following error: " + e.getMessage());
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
	public void testBadAppServiceFromURL() throws ApplicationException
	{

		Suit searchObject = new Suit();
		searchObject.setId(3);

		Collection results=null;
		boolean testSuccessful = false;
		try {
			results = getBadApplicationServiceFromUrl().search("gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit",searchObject );
		} catch (Exception e) {
			e.printStackTrace();
			testSuccessful=true;
		}

		assertTrue("Application should have failed as an invalid URL was used to obtain the ApplicationService instance",testSuccessful);
		assertNull("Resulting collection should be null as an invalid URL was used to obtain the ApplicationService Instance",results);

	}

//	protected void printObject(java.lang.Object obj, Class klass) throws Exception {
//		System.out.println("Printing "+ klass.getName());
//		Method[] methods = klass.getMethods();
//		for(Method method:methods)
//		{
//			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
//			{
//				System.out.print("\t"+method.getName().substring(3)+":");
//				java.lang.Object val = method.invoke(obj, (java.lang.Object[])null);
//				if(val instanceof java.util.Set)
//					System.out.println("size="+((Collection)val).size());
//				else
//					System.out.println(val);
//			}
//		}
//	}
}
