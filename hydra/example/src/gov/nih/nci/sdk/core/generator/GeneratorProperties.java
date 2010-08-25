package gov.nih.nci.sdk.core.generator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneratorProperties {

	private String propertiesFile;
	private Properties properties;

	public GeneratorProperties(String file) {
		propertiesFile = file;
		init();
	}

	private void init() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(propertiesFile));
		} catch (IOException e) {

		}

	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
