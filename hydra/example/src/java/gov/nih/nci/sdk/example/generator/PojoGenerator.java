package gov.nih.nci.sdk.example.generator;

import gov.nih.nci.sdk.core.generator.ECOREDomain;
import gov.nih.nci.sdk.core.generator.ECOREElement;
import gov.nih.nci.sdk.core.generator.GeneratorContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorDomainUtil;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;

public class PojoGenerator extends Generator{
	
	public PojoGenerator(GeneratorContext context) {
		super(context);
	}

	protected void init() {
		System.out.println("Generating pojo...");
		// TODO Auto-generated method stub
	}

	protected void preProcess() {
		// TODO Auto-generated method stub
	}

	protected void validate() {
		// TODO Auto-generated method stub
	}

	public void runProcess() {
		runProcess("pojo", "pojo", GeneratorUtil.getPojoPath(context));
	}

	protected void runProcess(String pojoTemplateName, String pojoPackageName, String outputDir) {
		
		StringTemplate template = GeneratorUtil.getTemplate(context, pojoTemplateName);
		ECOREDomain domain = context.getDomain();
		template.setAttribute("packageName", domain.getPackageName()+"."+pojoPackageName);
		template.setAttribute("className", domain.getName());
		ECOREElement[] elements = GeneratorDomainUtil.getAttributeList(domain);

		template.setAttribute("ECOREElement", elements);
		GeneratorUtil.writeFile(outputDir, domain.getName()+".java", template.toString());
	}

	protected void postProcess() {
		// TODO Auto-generated method stub
	}


}
