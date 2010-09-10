package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverterTestHelper;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SDKModelExplorerTest {
	public static void main(String[] args) {
		EPackage rootEPackage = XMI2EcoreModelConverterTestHelper.readEPackageFromXMIFile();
		SDKScreen test = new SDKModelExplorer(new Shell(new Display()), Constants.SDK_SCREEN_TITLE, rootEPackage);
		test.open();
	}
}
