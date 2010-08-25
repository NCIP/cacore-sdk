package gov.nih.nci.sdk.core.generator;

public class GeneratorContext {

	private String name;
	private String propertiesFile = "";
	private GeneratorProperties properties;
	//private EPackage ePackage;
	private ECOREDomain ePackage;
	
	//public GeneratorContext(String name, String propertiesFile, EPackage ePackage) {
	public GeneratorContext(String name, String propertiesFile, ECOREDomain ePackage) {
		this.name = name;
		this.propertiesFile = propertiesFile;
		this.ePackage = ePackage;
		initProperties();
	}

	
	public ECOREDomain getDomain() {
		return ePackage;
	}


	public void setDomain(ECOREDomain ePackage) {
		this.ePackage = ePackage;
	}


	private void initProperties() {
		properties = new GeneratorProperties(propertiesFile);
	}

	public GeneratorProperties getGeneratorProperties() {
		return properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
