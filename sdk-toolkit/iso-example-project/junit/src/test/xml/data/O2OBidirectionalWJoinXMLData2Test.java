/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class O2OBidirectionalWJoinXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional With Join XML Data Test Case";
	}
	
	public void testGetMethods1() throws Exception
	{
		Pendant searchObject = new Pendant();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pendant result = (Pendant)results.iterator().next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		assertEquals("1",result2.getChain().getId().getExtension());

		Ii ii2 = new Ii();
		ii2.setExtension("2");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		toXML(result);
		result2 = (Pendant)fromXML(result);
		assertEquals("2",result2.getChain().getId().getExtension());
		
		Ii ii3 = new Ii();
		ii3.setExtension("3");
		searchObject.setId(ii);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		toXML(result);
		result2 = (Pendant)fromXML(result);
		assertNotNull(result2.getChain());		
	}


	public void testGetMethods2() throws Exception
	{
		
		Chain searchObject = new Chain();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Chain result = (Chain)results.iterator().next();
		toXML(result);
		Chain result2 = (Chain)fromXML(result);
		assertEquals("1",result2.getPendant().getId().getExtension());

		Ii ii2 = new Ii();
		ii2.setExtension("1");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		toXML(result);
		result2 = (Chain)fromXML(result);
		assertEquals("1",result2.getPendant().getId().getExtension());

		Ii ii3 = new Ii();
		ii3.setExtension("1");
		searchObject.setId(ii3);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		toXML(result);
		result2 = (Chain)fromXML(result);
		assertNotNull(result2.getPendant());
		
	}

	public void testGetMethodsHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");

		assertNotNull(results);
		assertEquals(1, results.size());

		Chain result = (Chain) results.iterator().next();
		toXML(result);
		Chain result2 = (Chain) fromXML(result);
		assertEquals("1",result2.getPendant().getId().getExtension());
		
		HQLCriteria hqlCriteria2 = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain where id='2'");
		results = search(hqlCriteria2,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");

		assertNotNull(results);
		assertEquals(1, results.size());

		result = (Chain) results.iterator().next();
		toXML(result);
		result2 = (Chain) fromXML(result);
		assertEquals("2", result2.getPendant().getId().getExtension());

		HQLCriteria hqlCriteria3 = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain where id='3'");
		results = search(hqlCriteria3,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");

		assertNotNull(results);
		assertEquals(1, results.size());

		result = (Chain) results.iterator().next();
		toXML(result);
		result2 = (Chain) fromXML(result);
		assertNotNull(result2.getPendant());
	}

	public void testGetAssociation1() throws Exception
	{

		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Chain chain;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			toXML(result);
			Pendant result2 = (Pendant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getShape());
			
			if (new Integer(result2.getId().getExtension()) != 3){ // Pendant id = 3 does not have an associated Chain	
				
				validateAssociation(result,"Chain","chain", false);
				
				chain = result2.getChain();
				assertNotNull(chain);
				assertNotNull(chain.getId());
				assertNotNull(chain.getMetal());
			}
		}
	}
	
	public void testGetAssociation2() throws Exception
	{

		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Pendant pendant;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			toXML(result);
			Chain result2 = (Chain)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getMetal());
			
			if (new Integer(result2.getId().getExtension()) != 3){ // Chain id = 3 does not have an associated Pendant
				
				validateAssociation(result,"Pendant","pendant", false);
				
				pendant = result2.getPendant();
				assertNotNull(pendant);
				assertNotNull(pendant.getId());
				assertNotNull(pendant.getShape());
			}
		}
	}		
}
