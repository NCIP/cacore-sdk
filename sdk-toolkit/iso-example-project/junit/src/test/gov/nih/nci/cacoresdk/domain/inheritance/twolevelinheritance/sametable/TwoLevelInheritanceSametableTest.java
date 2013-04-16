/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class TwoLevelInheritanceSametableTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Same Table Test Case";
	}
	

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch1() throws ApplicationException
	{
		Goverment searchObject = new Goverment();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Goverment result = (Goverment)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch2() throws ApplicationException
	{
		CommunistGovt searchObject = new CommunistGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CommunistGovt result = (CommunistGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch3() throws ApplicationException
	{
		DemocraticGovt searchObject = new DemocraticGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch4() throws ApplicationException
	{
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ParliamantaryGovt result = (ParliamantaryGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());			
			assertNotNull(result.getPrimeMinister());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch5() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());	
			assertNotNull(result.getPresident());
		}
	}
	
	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment");

		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Goverment result = (Goverment) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());
		}
	}
	
	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			CommunistGovt result = (CommunistGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	
	public void testEntireObjectHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			ParliamantaryGovt result = (ParliamantaryGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	

	public void testEntireObjectHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			DemocraticGovt result = (DemocraticGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}


	public void testEntireObjectHQL5() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			PresidentialGovt result = (PresidentialGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getPresident());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociationNestedSearch() throws ApplicationException
	{
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		Goverment searchObject = new Goverment();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CommunistGovt result = (CommunistGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}		
	}

	public void testZeroAssociationHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt where primeMinister='George Bush'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");

		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch3() throws ApplicationException
	{
		DemocraticGovt searchObject = new DemocraticGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch4() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	
	public void testAssociationNestedSearch5() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	
	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt where primeMinister='Tony Blair'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			DemocraticGovt result = (DemocraticGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}


	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt where id='2'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			ParliamantaryGovt result = (ParliamantaryGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	
	public void testAssociationHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt where country='USA'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			DemocraticGovt result = (DemocraticGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}


	public void testAssociationHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt where id='1'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			PresidentialGovt result = (PresidentialGovt) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		}
	}
	
	public void testAssociationHQL5() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment govt" +
				" where govt.id='2' or govt.id='1'");

		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Government");
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Goverment result = (Goverment) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getCountry());
		}
	}
}