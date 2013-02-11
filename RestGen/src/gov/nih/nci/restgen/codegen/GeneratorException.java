package gov.nih.nci.restgen.codegen;

/**
 * GeneratorException thrown by Generator
 * @author konkapv
 *
 */
public class GeneratorException extends Exception {
	private static final long serialVersionUID = 4855421836404678121L;

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(String message, Throwable t) {
		super(message, t);
	}
}
