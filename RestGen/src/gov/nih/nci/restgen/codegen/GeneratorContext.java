package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Mapping;

import org.apache.log4j.Logger;

public class GeneratorContext {
	private String outputPath;
	private Mapping mapping;
	private static Logger log = Logger.getLogger(Generator.class);

	public GeneratorContext(Mapping mapping) {
		this.mapping = mapping;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public Logger getLogger() {
		return log;
	}

}
