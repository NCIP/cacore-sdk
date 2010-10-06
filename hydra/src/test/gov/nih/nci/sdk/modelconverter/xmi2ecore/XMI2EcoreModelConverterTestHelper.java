package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import gov.nih.nci.sdk.modelconverter.util.ModelConverterUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.ecore.EPackage;

public class XMI2EcoreModelConverterTestHelper {
	public static final String TEST_MODEL_XMI_FILE = "/model/sdkexample.xmi";

	/**
	 * Reads in the XMI file as Ecore model package.
	 */
	public static EPackage readEPackageFromXMIFile() {
		return readEPackageFromXMIFile(TEST_MODEL_XMI_FILE);
	}

	/**
	 * Reads in the XMI file as Ecore model package.
	 */
	public static EPackage readEPackageFromXMIFile(String xmiFileClassPath) {
		File xmiFile = createTempFileForTesting(xmiFileClassPath);
		System.out.println("MMMMM test file: " + xmiFile.getAbsolutePath());
		return ModelConverterUtil.getEPackageFromXMIFile(xmiFile.getAbsolutePath());
	}

	private static File createTempFileForTesting(String xmiResourcePath) {
		String tmpDir = System.getProperty("java.io.tmpdir");
		File outFile = new File(tmpDir + File.separatorChar + "_sdkexample.xmi");
		InputStream in = XMI2EcoreModelConverterTestHelper.class.getResourceAsStream(xmiResourcePath);
		try {
			OutputStream out = new FileOutputStream(outFile);
			byte buf[] = new byte[4096];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to read file "
					+ xmiResourcePath + ": " + ex.getMessage());
		}

		return outFile;
	}
}