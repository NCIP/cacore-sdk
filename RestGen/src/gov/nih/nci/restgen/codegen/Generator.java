package gov.nih.nci.restgen.codegen;

import java.io.File;

public abstract class Generator {
	public static final String RESOURCE_PACKAGE_NAME = "service";
	public static final String SERVICE_CLIENT_PACKAGE_NAME = "client";
	public static final String JAXBPOJO_PACKAGE_NAME = "jaxb";
	public static final String POJO_PACKAGE_NAME = "pojo";
	public static final String TEMPLATES_PACKAGE_NAME = "templates";
	public static final String GENERATED_PACKAGE_NAME = "generated";

	public static final String GENERATOR_TARGET_PATH = File.separator + "src"
			+ File.separator + "gen" + File.separator + "java";
	public static final String WEBINF_PATH = File.separator
			+ GENERATOR_TARGET_PATH + File.separator + "WEB-INF";

	protected GeneratorContext context;

	public void setContext(GeneratorContext context) {
		this.context = context;
	}

	public GeneratorContext getContext() {
		return context;
	}

	public Generator(GeneratorContext context) {
		setContext(context);
	}

	protected abstract void init() throws GeneratorException;

	protected abstract void preProcess() throws GeneratorException;

	protected abstract void validate() throws GeneratorException;

	public abstract void runProcess() throws GeneratorException;

	protected abstract void postProcess() throws GeneratorException;

	public void generate() throws GeneratorException {
		context.getLogger().info("Executing init()");
		init();
		context.getLogger().info("Executing preProcess()");
		preProcess();
		context.getLogger().info("Executing validate()");
		validate();
		context.getLogger().info("Executing runProcess()");
		runProcess();
		context.getLogger().info("Executing postProcess()");
		postProcess();
		context.getLogger().info("Completed generate()");
	}

	protected String getFileOutputPath() {
		return File.separatorChar + "gov" + File.separatorChar + "nih"
				+ File.separatorChar + "nci" + File.separatorChar + "restgen"
				+ File.separatorChar + "generated" + File.separatorChar
				+ "resource";
	}

}
