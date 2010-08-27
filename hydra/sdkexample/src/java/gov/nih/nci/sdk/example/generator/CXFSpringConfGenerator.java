package gov.nih.nci.sdk.example.generator;

import java.io.File;
import java.io.IOException;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.io.FileUtils;

import gov.nih.nci.sdk.core.GeneratorContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

public class CXFSpringConfGenerator extends Generator {
	public CXFSpringConfGenerator(GeneratorContext context) {
		super(context);
	}

	protected void init() {
		System.out.println("Generating CXF Spring configuration..");
	}

	protected void preProcess() {
		try {
			String outputDir = context.getGeneratorProperties().getValue(
			"PROJECT_ROOT")
			+ File.separatorChar
			+ "webapp";

			if (new File(outputDir).exists())
				FileUtils.forceDelete(new File(outputDir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void validate() {
		// TODO Auto-generated method stub
	}

	public void runProcess() {
		String templatePath = GeneratorUtil.getTemplatesPath(context);
		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen",
				templatePath);
		generateSpringServerConf(group);
		generateSpringClientConf(group);
		generateSpringWebConf(group);
		generateBuildScripts(group);
	}

	private void generateSpringServerConf(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("SpringBeanServerConf");
		SpringBean bean = new SpringBean();
		bean.setBeanId(GeneratorUtil.convertFirstCharToLowerCase(context.getDomain().getName()) + "Service");
		bean.setBeanImpl(GeneratorUtil.getServicePackageName(context)+"."+context.getDomain().getName()+"ServiceImpl");
		bean.setBeanAddress(context.getDomain().getName()+"Service");

		SpringBean[] elements = new SpringBean[] { bean };

		template.setAttribute("SpringBean", elements);
		String outputDir = context.getGeneratorProperties().getValue(
				"PROJECT_ROOT")
				+ File.separator
				+ "webapp"
				+ File.separator
				+ "WEB-INF";
		GeneratorUtil.writeFile(outputDir, "beans.xml", template.toString());
	}

	private void generateSpringClientConf(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("SpringBeanClientConf");
		SpringBean bean = new SpringBean();
		bean.setBeanId(context.getDomain().getName()+"Client");
		bean.setServiceClass(GeneratorUtil.getServicePackageName(context)+"."+context.getDomain().getName()+"Service");
		String address = "http://"+context.getGeneratorProperties().getValue("CATALINA_ADDRESS")
				+ ":"
				+ context.getGeneratorProperties().getValue("CATALINA_PORT")
				+ "/"
				+ context.getGeneratorProperties().getValue("APPLICATION_NAME")
				+ "/"
				+ context.getDomain().getName()+"Service";
		bean.setServiceAddress(address);

		SpringBean[] elements = new SpringBean[] { bean };

		template.setAttribute("SpringBean", elements);
		String outputDir = GeneratorUtil.getServiceClientPath(context);
		GeneratorUtil.writeFile(outputDir, "client-beans.xml",
				template.toString());
	}

	private void generateSpringWebConf(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("SpringWeb");
		String outputDir = context.getGeneratorProperties().getValue(
				"PROJECT_ROOT")
				+ File.separatorChar
				+ "webapp"
				+ File.separatorChar
				+ "WEB-INF";
		GeneratorUtil.writeFile(outputDir, "web.xml", template.toString());

	}

	private void generateBuildScripts(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("ant_common_build");
		template.setAttribute("hostName", context.getGeneratorProperties()
				.getValue("CATALINA_ADDRESS"));
		template.setAttribute("hostPort", context.getGeneratorProperties()
				.getValue("CATALINA_PORT"));
		String libDir = context.getGeneratorProperties().getValue("PROJECT_ROOT") + "/lib";
		template.setAttribute("generatorLibDir", libDir);
		
		String outputDir = context.getGeneratorProperties().getValue(
		"PROJECT_ROOT");

		GeneratorUtil.writeFile(outputDir + File.separator, "common_build.xml",
				template.toString());

		template = group.getInstanceOf("ant_build");
		template.setAttribute("serviceClient",
				GeneratorUtil.getServiceClientPackageName(context)+"."+ context.getDomain().getName()+"ServiceClient");
		template.setAttribute("archiveName", context.getGeneratorProperties()
				.getValue("APPLICATION_NAME"));
		GeneratorUtil.writeFile(outputDir + File.separator, "build.xml",
				template.toString());

	}

	protected void postProcess() {
		// TODO Auto-generated method stub
	}

}
