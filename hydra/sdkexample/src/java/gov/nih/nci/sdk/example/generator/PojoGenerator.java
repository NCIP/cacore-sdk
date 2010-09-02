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
		System.out.println("Here 0");
		runProcess("pojo", GeneratorUtil.getPojoPath(getScriptContext()));
		System.out.println("Here 0.1");
	}

	protected void runProcess(String pojoPackageName, String outputDir)
	{
		System.out.println("Here 1");
		StringTemplate template = GeneratorUtil.getTemplate("pojo");
		System.out.println("Here 2");
		String domain = getScriptContext().getFocusDomain();
		System.out.println("Here 3");
		String packageName = EcoreUtil.determinePackageName(domain);
		System.out.println("Here 4");
		String className = EcoreUtil.determineClassName(domain);
		System.out.println("Here 5");
		template.setAttribute("packageName", packageName);
		System.out.println("Here 6");
		template.setAttribute("className", className);
		System.out.println("Here 7");
		List<EAttribute> eAttributeList = EcoreUtil.getEClass(getScriptContext().getEPackage(), domain).getEAttributes();
		System.out.println("Here 8");
		template.setAttribute("ECOREElement", eAttributeList);
		System.out.println("Here 9");
		GeneratorUtil.writeFile(outputDir, domain + ".java", template.toString());
		System.out.println("Here 10");
	}

	protected void postProcess()
	{
		// TODO Auto-generated method stub
	}
}