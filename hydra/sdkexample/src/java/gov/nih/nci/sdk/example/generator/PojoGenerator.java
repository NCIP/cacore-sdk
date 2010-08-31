package gov.nih.nci.sdk.example.generator;

import java.util.List;
import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;
import org.eclipse.emf.ecore.EAttribute;

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
		runProcess("pojo", GeneratorUtil.getPojoPath(getScriptContext()));
	}

	protected void runProcess(String pojoPackageName, String outputDir)
	{	
		StringTemplate template = GeneratorUtil.getTemplate(TEMPLATES_PACKAGE_NAME, "pojo");
		String domain = getScriptContext().getFocusDomain();
		String packageName = EcoreUtil.determinePackageName(domain);
		String className = EcoreUtil.determineClassName(domain);
		template.setAttribute("packageName", packageName);
		template.setAttribute("className", className);
		List<EAttribute> eAttributeList = EcoreUtil.getEClass(getScriptContext().getEPackage(), domain).getEAttributes();

		template.setAttribute("ECOREElement", eAttributeList);
		GeneratorUtil.writeFile(outputDir, domain + ".java", template.toString());
	}

	protected void postProcess()
	{
		// TODO Auto-generated method stub
	}
}