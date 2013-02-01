package gov.nih.nci.restgen.codegen;

public class GeneratorException extends Exception {
	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(String message, Throwable t) {
		super(message, t);
	}
}
