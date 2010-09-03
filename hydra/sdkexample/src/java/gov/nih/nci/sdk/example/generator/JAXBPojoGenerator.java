package gov.nih.nci.sdk.example.generator;

import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

public class JAXBPojoGenerator extends PojoGenerator {
	
	public JAXBPojoGenerator(ScriptContext _scriptContext) {
		super(_scriptContext);
	}

	protected void init()
	{
		getScriptContext().logInfo("Generating JAXB pojo...");
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{
		super.runProcess("jaxb", GeneratorUtil.getJaxbPojoPath(getScriptContext()));
	}
}
