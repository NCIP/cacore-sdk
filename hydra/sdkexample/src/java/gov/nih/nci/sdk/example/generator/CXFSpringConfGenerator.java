/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.example.generator;

import java.io.File;
import java.io.IOException;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.io.FileUtils;

import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;
import gov.nih.nci.sdk.util.EcoreUtil;

public class CXFSpringConfGenerator
   extends Generator
{	
	public CXFSpringConfGenerator(ScriptContext _scriptContext)
	{
		super(_scriptContext);
	}

	protected void init()
	{
		System.out.println("Generating CXF Spring configuration..");
	}

	protected void preProcess()
	{
		try
		{
			File targetBase = new File(getScriptContext().getTargetBase());
			File generatedArtifactsDirectory = new File(targetBase.getAbsolutePath() + GENERATOR_TARGET_PATH);

			if (generatedArtifactsDirectory.exists() == true)
			{
				FileUtils.forceDelete(generatedArtifactsDirectory);
			}
		}
		catch (Throwable t)
		{
			getScriptContext().getErrorManager().add("sdkExample", "severe", t.getMessage());
			getScriptContext().abort();
			t.printStackTrace();
		}
	}

	protected void validate()
	{
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{

		java.util.Set<String> processedFocusDomainSet = (java.util.Set<String>) getScriptContext().getMemory().get("processedFocusDomainSet");

		if (processedFocusDomainSet == null)
		{
			processedFocusDomainSet = new java.util.HashSet<String>();
			getScriptContext().getMemory().put("processedFocusDomainSet", processedFocusDomainSet);
		}

		processedFocusDomainSet.add(getScriptContext().getFocusDomain());

		if (processedFocusDomainSet.containsAll(getScriptContext().retrieveDomainSet()) == true)
		{ //All domains have been processed so now we can compile and generate WSDL
			generateSpringServerConf();
			generateSpringClientConf(getScriptContext().getTemplateGroup());
			generateSpringWebConf(getScriptContext().getTemplateGroup());
			generateBuildScripts(getScriptContext().getTemplateGroup());
		}
	}

	private void generateSpringServerConf()
	{
		StringTemplate template = getScriptContext().getTemplateGroup().getInstanceOf("SpringBeanServerConf");

		for (String focusDomain: getScriptContext().retrieveDomainSet())
		{
			template.setAttribute("beanId", EcoreUtil.determineClassName(focusDomain) + "Service");
			template.setAttribute("beanImpl", EcoreUtil.determinePackageName(focusDomain) + ".service." + EcoreUtil.determineClassName(focusDomain) + "ServiceImpl");
			template.setAttribute("beanAddress", "/" + EcoreUtil.determineClassName(focusDomain) + "Service");
		}

		File targetBase = new File(getScriptContext().getTargetBase());
		String outputDir = getScriptContext().getProperties().getProperty(				"PROJECT_ROOT")
						   + File.separatorChar
						   + "webapp"
						   + File.separatorChar
						   + "WEB-INF";
		getScriptContext().getLogger().info("Beans.xml is being generated into directory: " + outputDir);
		GeneratorUtil.writeFile(outputDir, "beans.xml", template.toString());
	}

	private void generateSpringClientConf(StringTemplateGroup group)
	{
		StringTemplate template = group.getInstanceOf("SpringBeanClientConf");

		for (String focusDomain: getScriptContext().retrieveDomainSet())
		{
			template.setAttribute("beanId", focusDomain + "Client");
			template.setAttribute("serviceClass", focusDomain + "Service");
		
			String address = "http://" + getScriptContext().getProperties().getProperty("CATALINA_ADDRESS")
					+ ":"
					+ getScriptContext().getProperties().getProperty("CATALINA_PORT")
					+ "/"
					+ getScriptContext().getProperties().getProperty("APPLICATION_NAME")
					+ "/"
					+ EcoreUtil.determinePackageName(focusDomain) + ".service." + EcoreUtil.determineClassName(focusDomain) + "Service";

			template.setAttribute("serviceAddress", address);
		}
		
		String outputDir = GeneratorUtil.getServiceClientPath(getScriptContext());
		GeneratorUtil.writeFile(outputDir, "client-beans.xml", template.toString());
	}

	private void generateSpringWebConf(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("SpringWeb");
		String outputDir = getScriptContext().getProperties().getProperty(
				"PROJECT_ROOT")
				+ File.separatorChar
				+ "webapp"
				+ File.separatorChar
				+ "WEB-INF";
		GeneratorUtil.writeFile(outputDir, "web.xml", template.toString());
	}

	private void generateBuildScripts(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("ant_common_build");
		template.setAttribute("hostName", getScriptContext().getProperties().getProperty("CATALINA_ADDRESS"));
		template.setAttribute("hostPort", getScriptContext().getProperties().getProperty("CATALINA_PORT"));
		String libDir = getScriptContext().getProperties().getProperty("PROJECT_ROOT") + "/lib";
		template.setAttribute("generatorLibDir", libDir);
		
		String outputDir = getScriptContext().getProperties().getProperty("PROJECT_ROOT");

		GeneratorUtil.writeFile(outputDir + File.separator, "common_build.xml",
				template.toString());

		template = group.getInstanceOf("ant_build");
		template.setAttribute("serviceClient", GeneratorUtil.getServiceClientPackageName(getScriptContext().getFocusDomain()) +
							  "." + getScriptContext().getFocusDomain()+"ServiceClient");
		template.setAttribute("archiveName", getScriptContext().getProperties().getProperty("APPLICATION_NAME"));
		GeneratorUtil.writeFile(outputDir + File.separator, "build.xml", template.toString());

		template = group.getInstanceOf("project_build");
		template.setAttribute("cxfHome", getScriptContext().getProperties().getProperty("CXF_HOME"));
		template.setAttribute("catalinaHome", getScriptContext().getProperties().getProperty("CATALINA_HOME"));
		template.setAttribute("javaHome", getScriptContext().getProperties().getProperty("JAVA_HOME"));
		template.setAttribute("javaHomeLib", getScriptContext().getProperties().getProperty("JAVA_HOME_LIB"));
		GeneratorUtil.writeFile(outputDir + File.separator, "project.build", template.toString());


	}
	
	protected void postProcess() {
		// TODO Auto-generated method stub
	}
}