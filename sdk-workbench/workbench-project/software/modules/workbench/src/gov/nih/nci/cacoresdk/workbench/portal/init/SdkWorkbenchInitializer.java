package gov.nih.nci.cacoresdk.workbench.portal.init;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.cagrid.grape.ApplicationInitializer;

public class SdkWorkbenchInitializer implements ApplicationInitializer {
//	private static final int HELP_MENU = 4;
//	private static final int CONFIG_MENU = 3;

	public void intialize() throws Exception {
		
		URL url = SdkWorkbenchInitializer.class.getResource("/log4j.properties");
		
		System.out.println("* * * " + url.getFile());
		
		PropertyConfigurator.configure(url);

		setConfigurationOptions();
		prepareMenus();
	}

	private void setConfigurationOptions() {
		;
	}

	private void prepareMenus() {
		;
	}

}
