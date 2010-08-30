package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import gov.nih.nci.sdk.modelconverter.util.ModelConverterUtil;

import org.eclipse.emf.ecore.EPackage;

public class XMI2EcoreModelConverterTestHelper {
	public static final String TEST_MODEL_XMI_FILE = "./test/model/sdkexample.xmi";
	
	/**
	 * Reads in the XMI file as Ecore model package.
	 */
	public static EPackage readEPackageFromXMIFile() {
		return ModelConverterUtil.getEPackageFromXMIFile(TEST_MODEL_XMI_FILE);
	}
}
