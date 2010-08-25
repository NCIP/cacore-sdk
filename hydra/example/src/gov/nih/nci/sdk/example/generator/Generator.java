package gov.nih.nci.sdk.example.generator;

import gov.nih.nci.sdk.core.generator.GeneratorContext;

public abstract class Generator {

	public static final String SERVICE_PACKAGE_NAME="service";
	public static final String SERVICE_CLIENT_PACKAGE_NAME="client";
	public static final String JAXBPOJO_PACKAGE_NAME="jaxb";
	public static final String POJO_PACKAGE_NAME="pojo";
	public static final String TEMPLATES_PACKAGE_NAME="templates";
	public static final String GENERATED_PACKAGE_NAME="generated";
	public static final String IMPL_PACKAGE_NAME="impl";
	
	GeneratorContext context;

	public Generator(GeneratorContext context) {
		this.context = context;
	}

	protected abstract void init();

	protected abstract void preProcess();

	protected abstract void validate();

	public abstract void runProcess();

	public void generate() {
		init();
		preProcess();
		validate();
		runProcess();
		postProcess();
	}

	protected abstract void postProcess();
}
