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
		getScriptContext().getLogger().info("Generating pojo...");
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

	protected void runProcess(String _pojoPackageName, String _outputDir)
	{
		System.out.println("here 1");
		StringTemplate template = getScriptContext().getTemplateGroup().getInstanceOf("pojo");
		System.out.println("here 2");
		String domain = getScriptContext().getFocusDomain();
		System.out.println("here 3");
		String packageName = EcoreUtil.determinePackageName(domain);
		System.out.println("here 4");
		String className = EcoreUtil.determineClassName(domain);
		System.out.println("here 5");
		template.setAttribute("packageName", packageName + "." + _pojoPackageName);
		System.out.println("here 6");
		template.setAttribute("className", className);
		System.out.println("here 7");
		List<EAttribute> eAttributeList = EcoreUtil.getEClass(getScriptContext().getEPackage(), domain).getEAttributes();
		System.out.println("here 8");

		for (EAttribute eAttribute: eAttributeList)
		{
			System.out.println("here 9");
			StringTemplate pojoAttributeTemplate = getScriptContext().getTemplate("pojoAttributes");
			System.out.println("here A");
			pojoAttributeTemplate.setAttribute("name", eAttribute.getName());
			System.out.println("here B");

			System.out.println("Here is the Etype: " + eAttribute.getEType());
			
			pojoAttributeTemplate.setAttribute("type", eAttribute.getEType().getInstanceClassName());
			System.out.println("here C");

			template.setAttribute("pojoAttribute", pojoAttributeTemplate);
			System.out.println("here D");
			StringTemplate pojoOperationTemplate = getScriptContext().getTemplate("pojoOperations");
			System.out.println("here E");
			pojoOperationTemplate.setAttribute("name", eAttribute.getName());
			System.out.println("here F");
			pojoOperationTemplate.setAttribute("type", eAttribute.getEType().getInstanceClassName());
			System.out.println("here G");
			pojoOperationTemplate.setAttribute("operationName", GeneratorUtil.convertFirstCharToUpperCase(eAttribute.getName()));
			System.out.println("here H");
			template.setAttribute("pojoOperation", pojoOperationTemplate);
			System.out.println("here I");
		}
		
		GeneratorUtil.writeFile(_outputDir, className + ".java", template.toString());
	}

	protected void postProcess()
	{
		// TODO Auto-generated method stub
	}
}