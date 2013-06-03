/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.modelconverter.xmi2ecore;

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
public class XMI2EcoreModelConverterTest {
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
	public void verifyClassLoaded() {
		String className = "gov.nih.nci.sdkexample.Organization";
		EModelElement organization = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(organization);
		
		className = "gov.nih.nci.sdkexample.Person";
		EModelElement person = EcoreUtil.getModelElementForName(rootEPackage, className);
		assertNotNull(person);
	}
}
