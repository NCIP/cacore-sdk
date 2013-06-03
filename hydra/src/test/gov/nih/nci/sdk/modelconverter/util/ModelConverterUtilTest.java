/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.modelconverter.util;

import static org.junit.Assert.assertNotNull;
import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverterTestHelper;

import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

/**
 * Test ModelConverterUtilTest.java.
 * 
 * @author John Chen
 *
 */
public class ModelConverterUtilTest {
	
	@Test
	public void getEPackageFromXMIFile() {
		EPackage rootEPackage = XMI2EcoreModelConverterTestHelper.readEPackageFromXMIFile();
		assertNotNull(rootEPackage);
	}
}
