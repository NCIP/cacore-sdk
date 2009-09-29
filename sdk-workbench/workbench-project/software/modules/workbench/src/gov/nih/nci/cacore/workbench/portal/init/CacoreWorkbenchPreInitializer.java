package gov.nih.nci.cacore.workbench.portal.init;

import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.cagrid.grape.ApplicationInitializer;

public class CacoreWorkbenchPreInitializer implements ApplicationInitializer {
//	private static final int HELP_MENU = 4;
//	private static final int CONFIG_MENU = 3;

	public void intialize(org.cagrid.grape.model.Application app) throws Exception {
		
		URL url = CacoreWorkbenchPreInitializer.class.getResource("/log4j.properties");
		
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
