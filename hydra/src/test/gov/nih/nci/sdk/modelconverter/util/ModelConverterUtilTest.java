package gov.nih.nci.sdk.modelconverter.util;

import static org.junit.Assert.assertNotNull;

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
		String xmiFileName = "./test/model/sdkexample.xmi";
		EPackage rootEPackage = ModelConverterUtil.getEPackageFromXMIFile(xmiFileName);
		assertNotNull(rootEPackage);
	}
}
