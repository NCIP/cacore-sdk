package gov.nih.nci.sdk.example.generator;

import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;

public class PojoGenerator
   extends Generator
{	
	public PojoGenerator(ScriptContext _scriptContext)
	{
		super(_scriptContext);
	}

	protected void init()
	{
		System.out.println("Generating pojo...");
		// TODO Auto-generated method stub
	}

	protected void preProcess()
	{
		// TODO Auto-generated method stub
	}

	protected void validate()
	{
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{
		runProcess("pojo", "pojo", GeneratorUtil.getPojoPath(getScriptContext()));
	}

	protected void runProcess(String pojoTemplateName, String pojoPackageName, String outputDir)
	{	
		StringTemplate template = GeneratorUtil.getTemplate(TEMPLATES_PACKAGE_NAME, pojoTemplateName);
		String domain = getScriptContext().getFocusDomain();
		template.setAttribute("packageName", EcoreUtil.getPackageName(getScriptContext()) + "." + pojoPackageName);
		template.setAttribute("className", domain);
		List<EAttributes> eAttributeList = EcoreUtil.getEClass(domain).getEAttributes();

		template.setAttribute("ECOREElement", eAttributeList);
		GeneratorUtil.writeFile(outputDir, domain + ".java", template.toString());
	}

	protected void postProcess()
	{
		// TODO Auto-generated method stub
	}
}