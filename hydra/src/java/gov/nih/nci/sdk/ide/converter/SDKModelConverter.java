package gov.nih.nci.sdk.ide.converter;

import gov.nih.nci.sdk.modelconverter.util.ModelConverterUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.ecore.EPackage;

public class SDKModelConverter {
	public static final String TEST_MODEL_XMI_FILE = "/model/sdkexample.xmi";

	/**
	 * Reads in the XMI file as Ecore model package.
	 */
	public static EPackage readEPackageFromXMIFile() {
		File xmiFile = createTempFileForTesting(TEST_MODEL_XMI_FILE);
		return ModelConverterUtil.getEPackageFromXMIFile(xmiFile
				.getAbsolutePath());
	}

	private static File createTempFileForTesting(String xmiResourcePath) {
		String tmpDir = System.getProperty("java.io.tmpdir");
		File outFile = new File(tmpDir + File.separatorChar + "_sdkexample.xmi");
		InputStream in = SDKModelConverter.class.getResourceAsStream(xmiResourcePath);
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