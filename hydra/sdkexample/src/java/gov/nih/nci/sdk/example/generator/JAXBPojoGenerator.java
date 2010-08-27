package gov.nih.nci.sdk.example.generator;

import gov.nih.nci.sdk.core.GeneratorContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

public class JAXBPojoGenerator extends PojoGenerator {
	
	public JAXBPojoGenerator(GeneratorContext context) {
		super(context);
	}

	protected void init() {
		System.out.println("Generating JAXB pojo...");
		// TODO Auto-generated method stub
	}

	public void runProcess() {
		runProcess("JAXBPojo", "jaxb", GeneratorUtil.getJaxbPojoPath(context));
	}
}
