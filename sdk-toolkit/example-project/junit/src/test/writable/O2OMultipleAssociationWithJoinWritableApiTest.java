/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw;

public class O2OMultipleAssociationWithJoinWritableApiTest extends SDKWritableBaseTest{
	private static Logger log = Logger.getLogger(O2OMultipleAssociationWithJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One MultipleAssociation With Join WritableApi Test Case";
	}
	
	public void testSaveObjectBride(){
		log.debug("\n----------testSaveObjectChild()-------------\n");
		Bride bride=new Bride();
		bride.setName("bride");
		
		save(bride);
		
		Bride result=(Bride)getObject(Bride.class, bride.getId());
		Assert.assertEquals(bride.getName(), result.getName());
	}
	
	public void testSaveObjectBrideWithMultipleAssociation(){
		log.debug("\n----------testSaveObjectBrideWithMultipleAssociation()-------------\n");
		Bride bride=new Bride();
		bride.setName("bride");
		InLaw fatherInLaw=new InLaw();
		fatherInLaw.setName("fatherinlaw");
		InLaw motherInLaw=new InLaw();
		motherInLaw.setName("motherinLaw");
		bride.setFather(fatherInLaw);
		bride.setMother(motherInLaw);
		
		save(bride);
		
		Bride result=(Bride)getObjectAndMultipleLazyObjects(Bride.class, bride.getId(), "mother", "father");
		Assert.assertEquals(bride.getName(), result.getName());
		Assert.assertEquals(bride.getFather().getName(), result.getFather().getName());
		Assert.assertEquals(bride.getMother().getName(), result.getMother().getName());
	}
	
	public void testDeleteObjectChildWithMultipleAssociationCascadeDelete(){
		log.debug("\n----------testDeleteObjectChildWithMultipleAssociationCascadeDelete()-------------\n");
		Bride bride=new Bride();
		bride.setName("bride");
		InLaw fatherInLaw=new InLaw();
		fatherInLaw.setName("fatherinlaw");
		InLaw motherInLaw=new InLaw();
		motherInLaw.setName("motherinLaw");
		bride.setFather(fatherInLaw);
		bride.setMother(motherInLaw);
		
		save(bride);
		
		Bride deleteBride=(Bride)getObject(Bride.class, bride.getId());
		delete(deleteBride);
		
		Bride resultBride=(Bride)getObject(Bride.class, bride.getId());
		Assert.assertNull(resultBride);
	}
	
	public void testUpdateObjectBrideWithMultipleAssociationCascadeAll(){
		log.debug("\n----------testUpdateObjectBrideWithMultipleAssociationCascadeAll()-------------\n");
		Bride bride=new Bride();
		bride.setName("bride");
		InLaw fatherInLaw=new InLaw();
		fatherInLaw.setName("fatherinlaw");
		InLaw motherInLaw=new InLaw();
		motherInLaw.setName("motherinLaw");
		bride.setFather(fatherInLaw);
		bride.setMother(motherInLaw);
		
		save(bride);
		
		Bride updateBride=(Bride)getObjectAndMultipleLazyObjects(Bride.class, bride.getId(), "mother", "father");
		updateBride.setName("updateName");
		updateBride.getFather().setName("updateFatherInLaw");
		updateBride.getMother().setName("updateMotherInLaw");
		update(updateBride);
		
		Bride result=(Bride)getObjectAndMultipleLazyObjects(Bride.class, updateBride.getId(), "mother", "father");
		Assert.assertEquals(updateBride.getName(), result.getName());
		Assert.assertEquals(updateBride.getFather().getName(), result.getFather().getName());
		Assert.assertEquals(updateBride.getMother().getName(), result.getMother().getName());
	}
}
