package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.sdk.util.EcoreUtil;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
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
		
		System.out.println("organization.getEAnnotations().size(): " + organization.getEAnnotations().size());
		assertEquals(1, organization.getEAnnotations().size());
		
		className = "gov.nih.nci.sdkexample.Person";
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
		
		System.out.println("person.getEAnnotations().size(): " + person.getEAnnotations().size());
		assertEquals(1, organization.getEAnnotations().size());
	}
	
	@Test
	public void verifyPropLevelTag() {
		String className = "gov.nih.nci.sdkexample.Organization";
		EModelElement organization = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(organization);
		
		System.out.println("organization.getEAnnotations().size(): " + organization.getEAnnotations().size());
		assertEquals(1, organization.getEAnnotations().size());
		
		className = "gov.nih.nci.sdkexample.Person";
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
		
		System.out.println("person.getEAnnotations().size(): " + person.getEAnnotations().size());
		assertEquals(1, organization.getEAnnotations().size());
	}
}
