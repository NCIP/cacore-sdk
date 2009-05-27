package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor;

public class ParentWithAssociationWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(ParentWithAssociationWritableApiTest.class);
	public static String getTestCaseName() {
		return "Parent With Association WritableApi Test Case";
	}
	
	public void testSaveObjectParentWithAssociationInheritance(){
		log.debug("\n----testSaveObjectParentWithAssociationInheritance----\n");
		Collection<Assistant> profassistants=new HashSet<Assistant>();
		Assistant assistant1=new Assistant();
		assistant1.setName("assistant");
		profassistants.add(assistant1);
		
		Collection<Assistant> tenuredProfassistants=new HashSet<Assistant>();
		Assistant assistant2=new Assistant();
		assistant2.setName("assistant");
		tenuredProfassistants.add(assistant2);
		
		Collection<Assistant> associatedProfassistants=new HashSet<Assistant>();
		Assistant assistant3=new Assistant();
		assistant3.setName("assistant");
		associatedProfassistants.add(assistant3);
		
		Collection<Assistant> assistantProfassistants=new HashSet<Assistant>();
		Assistant assistant4=new Assistant();
		assistant4.setName("assistant");
		assistantProfassistants.add(assistant4);
		
		Professor professor=new Professor();
		professor.setName("professor");
		professor.setAssistantCollection(profassistants);
		
		TenuredProfessor tenuredProfessor=new TenuredProfessor();
		tenuredProfessor.setName("tenuredProfessor");
		tenuredProfessor.setTenuredYear(20);
		tenuredProfessor.setAssistantCollection(tenuredProfassistants);
		
		AssociateProfessor associateProfessor=new AssociateProfessor();
		associateProfessor.setName("associatedProfessor");
		associateProfessor.setYearsServed(35);
		associateProfessor.setAssistantCollection(associatedProfassistants);
		
		AssistantProfessor assistantProfessor=new AssistantProfessor();
		assistantProfessor.setName("assistantProfessor");
		assistantProfessor.setJoiningYear(2002);
		assistantProfessor.setAssistantCollection(assistantProfassistants);
		
		createParentWithAssociationHierarchy(assistant1, assistant2,assistant3, assistant4, professor, tenuredProfessor,associateProfessor, assistantProfessor);
		assertTestCase(assistant1, assistant2, assistant3, assistant4,professor, tenuredProfessor, associateProfessor,assistantProfessor);
	}
	
	public void testUpdateObjectParentWithAssociationInheritance(){
		log.debug("\n-----testUpdateObjectParentWithAssociationInheritance-----\n");
		Collection<Assistant> profassistants=new HashSet<Assistant>();
		Assistant assistant1=new Assistant();
		assistant1.setName("assistant");
		profassistants.add(assistant1);
		
		Collection<Assistant> tenuredProfassistants=new HashSet<Assistant>();
		Assistant assistant2=new Assistant();
		assistant2.setName("assistant");
		tenuredProfassistants.add(assistant2);
		
		Collection<Assistant> associatedProfassistants=new HashSet<Assistant>();
		Assistant assistant3=new Assistant();
		assistant3.setName("assistant");
		associatedProfassistants.add(assistant3);
		
		Collection<Assistant> assistantProfassistants=new HashSet<Assistant>();
		Assistant assistant4=new Assistant();
		assistant4.setName("assistant");
		assistantProfassistants.add(assistant4);
		
		Professor professor=new Professor();
		professor.setName("professor");
		professor.setAssistantCollection(profassistants);
		
		TenuredProfessor tenuredProfessor=new TenuredProfessor();
		tenuredProfessor.setName("tenuredProfessor");
		tenuredProfessor.setTenuredYear(20);
		tenuredProfessor.setAssistantCollection(tenuredProfassistants);
		
		AssociateProfessor associateProfessor=new AssociateProfessor();
		associateProfessor.setName("associatedProfessor");
		associateProfessor.setYearsServed(35);
		associateProfessor.setAssistantCollection(associatedProfassistants);
		
		AssistantProfessor assistantProfessor=new AssistantProfessor();
		assistantProfessor.setName("assistantProfessor");
		assistantProfessor.setJoiningYear(2002);
		assistantProfessor.setAssistantCollection(assistantProfassistants);
		
		createParentWithAssociationHierarchy(assistant1, assistant2,assistant3, assistant4, professor, tenuredProfessor,associateProfessor, assistantProfessor);
		
		Professor updateProfessor=(Professor)getObjectAndLazyCollection(Professor.class, professor.getId(),"assistantCollection");
		updateProfessor.setName("updateProfessor");
		Assistant updateProfAssistant=updateProfessor.getAssistantCollection().iterator().next();
		updateProfAssistant.setName("updateAssistant");
		
		TenuredProfessor updateTenuredProfessor=(TenuredProfessor)getObjectAndLazyCollection(TenuredProfessor.class, tenuredProfessor.getId(),"assistantCollection");
		updateTenuredProfessor.setName("updateTenuredProfessor");
		Assistant updateTenuredProfAssistant=updateTenuredProfessor.getAssistantCollection().iterator().next();
		updateTenuredProfAssistant.setName("updateAssistant");
		
		AssociateProfessor updateAssociateProfessor=(AssociateProfessor)getObjectAndLazyCollection(AssociateProfessor.class, associateProfessor.getId(),"assistantCollection");
		updateAssociateProfessor.setName("updateAssociateProfessor");
		Assistant updateAssocProfAssistant=updateAssociateProfessor.getAssistantCollection().iterator().next();
		updateAssocProfAssistant.setName("updateAssistant");
		
		AssistantProfessor updateAssistantProfessor=(AssistantProfessor)getObjectAndLazyCollection(AssistantProfessor.class, assistantProfessor.getId(),"assistantCollection");
		updateAssistantProfessor.setName("updateAssistantProfessor");
		Assistant updateAssistProfAssistant=updateAssistantProfessor.getAssistantCollection().iterator().next();
		updateAssistProfAssistant.setName("updateAssistant");
		
		updateParentWithAssociationHierarchy(updateProfessor,updateProfAssistant, updateTenuredProfessor,updateTenuredProfAssistant, updateAssociateProfessor,updateAssocProfAssistant, updateAssistantProfessor,updateAssistProfAssistant);
		assertTestCase(updateProfAssistant, updateTenuredProfAssistant, updateAssocProfAssistant, updateAssistProfAssistant,updateProfessor, updateTenuredProfessor, updateAssociateProfessor,updateAssistantProfessor);
	}
	
	public void testDeleteObjectParentWithAssociationInheritance(){
		log.debug("\n-----testUpdateObjectParentWithAssociationInheritance-----\n");
		Collection<Assistant> profassistants=new HashSet<Assistant>();
		Assistant assistant1=new Assistant();
		assistant1.setName("assistant");
		profassistants.add(assistant1);
		
		Collection<Assistant> tenuredProfassistants=new HashSet<Assistant>();
		Assistant assistant2=new Assistant();
		assistant2.setName("assistant");
		tenuredProfassistants.add(assistant2);
		
		Collection<Assistant> associatedProfassistants=new HashSet<Assistant>();
		Assistant assistant3=new Assistant();
		assistant3.setName("assistant");
		associatedProfassistants.add(assistant3);
		
		Collection<Assistant> assistantProfassistants=new HashSet<Assistant>();
		Assistant assistant4=new Assistant();
		assistant4.setName("assistant");
		assistantProfassistants.add(assistant4);
		
		Professor professor=new Professor();
		professor.setName("professor");
		professor.setAssistantCollection(profassistants);
		
		TenuredProfessor tenuredProfessor=new TenuredProfessor();
		tenuredProfessor.setName("tenuredProfessor");
		tenuredProfessor.setTenuredYear(20);
		tenuredProfessor.setAssistantCollection(tenuredProfassistants);
		
		AssociateProfessor associateProfessor=new AssociateProfessor();
		associateProfessor.setName("associatedProfessor");
		associateProfessor.setYearsServed(35);
		associateProfessor.setAssistantCollection(associatedProfassistants);
		
		AssistantProfessor assistantProfessor=new AssistantProfessor();
		assistantProfessor.setName("assistantProfessor");
		assistantProfessor.setJoiningYear(2002);
		assistantProfessor.setAssistantCollection(assistantProfassistants);
		
		createParentWithAssociationHierarchy(assistant1, assistant2,assistant3, assistant4, professor, tenuredProfessor,associateProfessor, assistantProfessor);
		
		Professor deleteProfessor=(Professor)getObjectAndLazyCollection(Professor.class, professor.getId(),"assistantCollection");
		TenuredProfessor deleteTenuredProfessor=(TenuredProfessor)getObjectAndLazyCollection(TenuredProfessor.class, tenuredProfessor.getId(),"assistantCollection");
		AssociateProfessor deleteAssociateProfessor=(AssociateProfessor)getObjectAndLazyCollection(AssociateProfessor.class, associateProfessor.getId(),"assistantCollection");
		AssistantProfessor deleteAssistantProfessor=(AssistantProfessor)getObjectAndLazyCollection(AssistantProfessor.class, assistantProfessor.getId(),"assistantCollection");
		
		delete(deleteProfessor);
		delete(deleteTenuredProfessor);
		delete(deleteAssociateProfessor);
		delete(deleteAssistantProfessor);
		
		Professor resultProfessor=(Professor)getObjectAndLazyCollection(Professor.class, deleteProfessor.getId(),"assistantCollection");
		TenuredProfessor resultTenuredProfessor=(TenuredProfessor)getObjectAndLazyCollection(TenuredProfessor.class, deleteTenuredProfessor.getId(),"assistantCollection");
		AssociateProfessor resultAssociateProfessor=(AssociateProfessor)getObjectAndLazyCollection(AssociateProfessor.class, deleteAssociateProfessor.getId(),"assistantCollection");
		AssistantProfessor resultAssistantProfessor=(AssistantProfessor)getObjectAndLazyCollection(AssistantProfessor.class, deleteAssistantProfessor.getId(),"assistantCollection");
		
		Assert.assertNull(resultProfessor);
		Assert.assertNull(resultTenuredProfessor);
		Assert.assertNull(resultAssociateProfessor);
		Assert.assertNull(resultAssistantProfessor);
	}

	private void updateParentWithAssociationHierarchy(
			Professor updateProfessor, Assistant updateProfAssistant,
			TenuredProfessor updateTenuredProfessor,
			Assistant updateTenuredProfAssistant,
			AssociateProfessor updateAssociateProfessor,
			Assistant updateAssocProfAssistant,
			AssistantProfessor updateAssistantProfessor,
			Assistant updateAssistProfAssistant) {
		update(updateProfAssistant);
		update(updateTenuredProfAssistant);
		update(updateAssocProfAssistant);
		update(updateAssistProfAssistant);
		update(updateProfessor);
		update(updateTenuredProfessor);
		update(updateAssociateProfessor);
		update(updateAssistantProfessor);
	}




	private void assertTestCase(Assistant assistant1, Assistant assistant2,
			Assistant assistant3, Assistant assistant4, Professor professor,
			TenuredProfessor tenuredProfessor,
			AssociateProfessor associateProfessor,
			AssistantProfessor assistantProfessor) {
		Professor resultProfessor=(Professor)getObjectAndLazyCollection(Professor.class, professor.getId(),"assistantCollection");
		Assert.assertEquals(resultProfessor.getName(), resultProfessor.getName());
		Assert.assertEquals(assistant1.getName(), resultProfessor.getAssistantCollection().iterator().next().getName());
	
		TenuredProfessor resultTenuredProfessor=(TenuredProfessor)getObjectAndLazyCollection(TenuredProfessor.class, tenuredProfessor.getId(),"assistantCollection");
		Assert.assertEquals(tenuredProfessor.getName(), resultTenuredProfessor.getName());
		Assert.assertEquals(assistant2.getName(), resultTenuredProfessor.getAssistantCollection().iterator().next().getName());
		
		AssociateProfessor resultAssociateProfessor=(AssociateProfessor)getObjectAndLazyCollection(AssociateProfessor.class, associateProfessor.getId(),"assistantCollection");
		Assert.assertEquals(associateProfessor.getName(), resultAssociateProfessor.getName());
		Assert.assertEquals(assistant3.getName(), resultAssociateProfessor.getAssistantCollection().iterator().next().getName());
		
		AssistantProfessor resultAssistantProfessor=(AssistantProfessor)getObjectAndLazyCollection(AssistantProfessor.class, assistantProfessor.getId(),"assistantCollection");
		Assert.assertEquals(assistantProfessor.getName(), resultAssistantProfessor.getName());
		Assert.assertEquals(assistant4.getName(), resultAssistantProfessor.getAssistantCollection().iterator().next().getName());
	}

	private void createParentWithAssociationHierarchy(Assistant assistant1,
			Assistant assistant2, Assistant assistant3, Assistant assistant4,
			Professor professor, TenuredProfessor tenuredProfessor,
			AssociateProfessor associateProfessor,
			AssistantProfessor assistantProfessor) {
		save(assistant1);
		save(assistant2);
		save(assistant3);
		save(assistant4);
		save(professor);
		save(tenuredProfessor);
		save(associateProfessor);
		save(assistantProfessor);
	}
}
