package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Mapping;

import org.apache.log4j.Logger;

public class GeneratorContext {
	private Mapping mapping;
	private String mappingXMLPath;

	private static Logger log = Logger.getLogger(Generator.class);

	public GeneratorContext(Mapping mapping) {
		this.mapping = mapping;
	}

	public GeneratorContext(String mappingXMLPath) {
		this.mappingXMLPath = mappingXMLPath;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	public Logger getLogger() {
		return log;
	}

	public String getMappingXMLPath() {
		return mappingXMLPath;
	}
}
