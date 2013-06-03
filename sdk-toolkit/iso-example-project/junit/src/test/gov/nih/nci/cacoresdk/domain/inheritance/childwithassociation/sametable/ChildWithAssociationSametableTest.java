/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class ChildWithAssociationSametableTest extends SDKISOTestBase
{
	protected String DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE = "DesignerShoesRoot LocalConstant";
	protected String SHOES_ROOT_LOCAL_CONSTANT_VALUE = "ShoesRoot LocalConstant";
	protected String SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE = "SportShoesRoot LocalConstant";	
	
	public static String getTestCaseName()
	{
		return "Child With Association Same Table Test Case";
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
		Shoes searchObject = new Shoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Shoes result = (Shoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertShoesRoot(result);
			assertNotNull(result.getColor());
		}
	}

	private void assertShoesRoot(Shoes shoes)
	{
		if (shoes instanceof DesignerShoes)
		{
			assertEquals(DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE, shoes.getId().getRoot());
		}
		else if (shoes instanceof SportsShoes)
		{
			assertEquals(SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE, shoes.getId().getRoot());
		}
		else
		{
			assertEquals(SHOES_ROOT_LOCAL_CONSTANT_VALUE, shoes.getId().getRoot());
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
		SportsShoes searchObject = new SportsShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			SportsShoes result = (SportsShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(result.getColor());
			assertNotNull(result.getSportsType());
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
		DesignerShoes searchObject = new DesignerShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DesignerShoes result = (DesignerShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(result.getColor());
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
	public void testGetAssociation() throws ApplicationException
	{
		DesignerShoes searchObject = new DesignerShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Designer designer;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DesignerShoes result = (DesignerShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(result.getColor());
			
			designer = result.getDesigner();
			
			assertNotNull(designer);
			assertNotNull(designer.getId());
			assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(designer.getName());
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
		Designer searchObject = new Designer();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Designer result = (Designer)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
		
	
	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			SportsShoes result = (SportsShoes) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(result.getColor());
		}
	}

	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Shoes result = (Shoes) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertShoesRoot(result);
			assertNotNull(result.getColor());
		}
	}

	public void testEntireObjectHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");

		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			DesignerShoes result = (DesignerShoes) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
			assertNotNull(result.getColor());
		}
	}


	public void testEntireObjectHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer");

		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Designer result = (Designer) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
		SportsShoes searchObject = new SportsShoes();
		St st=new St();
		st.setValue("Non-existent name");
		searchObject.setColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	public void testZeroAssociationHQL() throws ApplicationException {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes where color='1'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
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
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		Shoes searchObject = new Shoes();
		St st=new St();
		st.setValue("Red");
		searchObject.setColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		SportsShoes result = (SportsShoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("2", result.getId().getExtension());
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
		SportsShoes searchObject = new SportsShoes();
		St st=new St();
		st.setValue("Red");
		searchObject.setColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Shoes result = (Shoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("2", result.getId().getExtension());
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
		Shoes searchObject = new Shoes();
		St st=new St();
		st.setValue("Black");
		searchObject.setColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		DesignerShoes result = (DesignerShoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());	}

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
		DesignerShoes searchObject = new DesignerShoes();
		St st=new St();
		st.setValue("Black");
		searchObject.setColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Shoes result = (Shoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());
	}
	

	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		assertNotNull(results);
		assertEquals(1, results.size());
		
		Shoes result = (Shoes) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertShoesRoot(result);
		assertEquals("2", result.getId().getExtension());
	}


	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		assertNotNull(results);
		assertEquals(1,results.size());
		
		SportsShoes result = (SportsShoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),SPORTS_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("2", result.getId().getExtension());
	}
	
	public void testAssociationHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes where designer.name='Prada'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");

		assertNotNull(results);
		assertEquals(1, results.size());

		Shoes result = (Shoes) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("1", result.getId().getExtension());
	}
	
	public void testAssociationHQL4() throws ApplicationException {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes where designer.name='Sergio Rossi'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");
		assertNotNull(results);
		assertEquals(1, results.size());

		DesignerShoes result = (DesignerShoes) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),DESIGNER_SHOES_ROOT_LOCAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());
	}
}
