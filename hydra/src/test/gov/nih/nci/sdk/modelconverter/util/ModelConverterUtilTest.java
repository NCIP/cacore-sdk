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
