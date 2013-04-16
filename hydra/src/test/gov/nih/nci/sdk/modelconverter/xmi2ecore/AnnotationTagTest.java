/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.util.SDKUtil;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test tags retrieval.
 * 
 * @author John Chen
 *
 */
public class AnnotationTagTest {
	protected static EPackage rootEPackage;
	
	@BeforeClass
	public static void loadModel() {
		rootEPackage = XMI2EcoreModelConverterTestHelper.readEPackageFromXMIFile();
	}

	@AfterClass
	public static void clearModel() {
		rootEPackage = null;
	}
	
	@Test
	public void verifyClassLevelTag() {
		String className = "gov.nih.nci.sdkexample.Organization";
		EModelElement organization = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(organization);
		
		assertEquals(1, organization.getEAnnotations().size());
		
		className = "gov.nih.nci.sdkexample.Person";
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
		
		assertEquals(1, person.getEAnnotations().size());
	}
	
	@Test
	public void verifyPropLevelTag() {
		String className = "gov.nih.nci.sdkexample.Person";
		String propName = "name";
		int totalAnnotationsAdded = 3;
		String annotationName1 = "prop.val.required";
		String annotationValue1 = "true";
		
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
		
		EList<EStructuralFeature> attributes = ((EClass)person).getEAllStructuralFeatures();
		Iterator<EStructuralFeature> itAttr = attributes.iterator();
		while (itAttr.hasNext()) {
			EStructuralFeature attr = itAttr.next();
			if (propName.equals(attr.getName())) {
				EList<EAnnotation> anns = attr.getEAnnotations();
				assertEquals(totalAnnotationsAdded, anns.size());
				assertEquals(annotationValue1, SDKUtil.getTagValue(attr, annotationName1));
			}
		}
	}
	
	@Test
	public void verifyOperLevelTag() {
		String className = "gov.nih.nci.sdkexample.Person";
		String operName = "getNickName";
		int totalAnnotationsAdded = 3;
		String annotationName1 = "oper.sec.role";
		String annotationValue1 = "AdminUser";
		String annotationName2 = "oper.mea.desc";
		String annotationValue2 = "This operation returns the nick name of the person or null if there is none.";
		String annotationName3 = "oper.ser.service";
		String annotationValue3 = "true";
		
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
		
		EList<EOperation> operations = ((EClass)person).getEAllOperations();
		Iterator<EOperation> itOper = operations.iterator();
		while (itOper.hasNext()) {
			EOperation oper = itOper.next();
			if (operName.equals(oper.getName())) {
				EList<EAnnotation> anns = oper.getEAnnotations();
				assertEquals(totalAnnotationsAdded, anns.size());
				assertEquals(annotationValue1, SDKUtil.getTagValue(oper, annotationName1));
				assertEquals(annotationValue2, SDKUtil.getTagValue(oper, annotationName2));
				assertEquals(annotationValue3, SDKUtil.getTagValue(oper, annotationName3));
			}
		}
	}
	
	@Test
	public void verifyRelGeneralizationLevelTag() {
		String className = "gov.nih.nci.sdkexample.Patient";
		String annotationName1 = "rel.mea.desc";
		String annotationValue1 = "Patient is a subclass of Person.";
		
		EModelElement patient = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(patient);
		
		EList<EAnnotation> anns = patient.getEAnnotations();
		
		assertEquals(1, anns.size());
		assertEquals(annotationValue1, SDKUtil.getTagValue(patient, annotationName1));
	}
}
