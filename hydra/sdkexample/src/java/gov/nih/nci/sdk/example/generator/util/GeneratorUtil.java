package gov.nih.nci.sdk.example.generator.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.io.FileUtils;

import gov.nih.nci.sdk.core.GeneratorContext;
import gov.nih.nci.sdk.example.generator.Generator;

public class GeneratorUtil {

	public static StringTemplate getTemplate(GeneratorContext context, String templateName) {

		String projectRoot = context.getGeneratorProperties().getValue(
		"PROJECT_ROOT");

		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen",
				projectRoot + File.separator + Generator.TEMPLATES_PACKAGE_NAME);
		return group.getInstanceOf(templateName);
	}

	public static void writeFile(String outputDir, String fileName,
			String content) {
		try {
			if (outputDir.contains("."))
				outputDir = outputDir.replace(".", File.separator);

			createOutputDir(outputDir);
			File f = new File(outputDir, fileName);
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(content);
			bw.close();
			w.close();
		} catch (IOException ioe) {
			System.err.println("can't write file");
			ioe.printStackTrace(System.err);
		}
	}

	public static void createOutputDir(String outputDir) throws IOException {
		if (outputDir == null)
			return;

		if (outputDir.contains("."))
			outputDir = outputDir.replace(".", File.pathSeparator);

		File file = new File(outputDir);
		if (!file.exists())
			FileUtils.forceMkdir(file);
	}

	public static List getFiles(String dir, String[] extensions)
			throws MalformedURLException {
		List<String> files = new ArrayList();
		Iterator iter = FileUtils
				.iterateFiles(new File(dir), extensions, false);
		while (iter.hasNext()) {
			File file = (File) iter.next();
			files.add(file.toURI().toURL().getFile());
		}
		return files;
	}

	public static String getFiles(String dir, String[] extensions,
			String seperator) throws MalformedURLException {
		StringBuffer files = new StringBuffer();
		Iterator iter = FileUtils
				.iterateFiles(new File(dir), extensions, false);
		while (iter.hasNext()) {
			File file = (File) iter.next();
			files.append(file.toURI().toURL().getFile()).append(seperator);
		}
		return files.toString();
	}

	public static String getJaxbPojoPath(GeneratorContext context) {
		String jaxbPojoPath = getGeneratedPath(context)
				+ File.separator
				+ context.getDomain().getPackageName().replace(".", File.separator)
				+ File.separator + Generator.JAXBPOJO_PACKAGE_NAME;

		return jaxbPojoPath;
	}

	public static String getPojoPath(GeneratorContext context) {
		String jaxbPojoPath = getGeneratedPath(context)
				+ File.separator
				+ context.getDomain().getPackageName().replace(".", File.separator)
				+ File.separator + Generator.POJO_PACKAGE_NAME;

		return jaxbPojoPath;
	}
	
	public static String getServiceImplPath(GeneratorContext context) {
		String serviceImplPath = getImplPath(context)
				+ File.separator
				+ context.getDomain().getPackageName().replace(".", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;

		return serviceImplPath;
	}

	public static String getServiceClientPath(GeneratorContext context) {
		String serviceImplPath = getGeneratedPath(context)
				+ File.separator
				+ context.getDomain().getPackageName().replace(".", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME
				+ File.separator
				+ Generator.SERVICE_CLIENT_PACKAGE_NAME;

		return serviceImplPath;
	}
	
	public static String getServicePath(GeneratorContext context) {
		String servicePath = getGeneratedPath(context)
				+ File.separator
				+ context.getDomain().getPackageName().replace(".", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;

		return servicePath;
	}

	public static String getGeneratedPath(GeneratorContext context) {
		String pathStr = context.getGeneratorProperties().getValue(
				"PROJECT_ROOT")
				+ File.separator
				+ context.getGeneratorProperties().getValue("PROJECT_SRC")
				+ File.separator 
				+ Generator.GENERATED_PACKAGE_NAME;

		return pathStr;
	}

	public static String getImplPath(GeneratorContext context) {
		String pathStr = context.getGeneratorProperties().getValue(
				"PROJECT_ROOT")
				+ File.separator
				+ context.getGeneratorProperties().getValue("PROJECT_SRC")
				+ File.separator 
				+ Generator.IMPL_PACKAGE_NAME;

		return pathStr;
	}

	public static String getServicePackageName(GeneratorContext context)
	{
		return context.getDomain().getPackageName()+"."+Generator.SERVICE_PACKAGE_NAME;
	}

	public static String getJaxbPojoPackageName(GeneratorContext context)
	{
		return context.getDomain().getPackageName()+"."+Generator.JAXBPOJO_PACKAGE_NAME;
	}
	
	public static String getPojoPackageName(GeneratorContext context)
	{
		return context.getDomain().getPackageName()+"."+Generator.POJO_PACKAGE_NAME;
	}

	public static String getServiceClientPackageName(GeneratorContext context)
	{
		return context.getDomain().getPackageName()
			+ "."
			+ Generator.SERVICE_PACKAGE_NAME
			+ "."
			+ Generator.SERVICE_CLIENT_PACKAGE_NAME;
	}
	
	public static String getClassesPath(GeneratorContext context) {
		return context.getGeneratorProperties().getValue("PROJECT_ROOT")
				+ File.separator + "classes";
	}

	public static String getTemplatesPath(GeneratorContext context) {
		return context.getGeneratorProperties().getValue("PROJECT_ROOT")
				+ File.separator + "templates";
	}

	public static String convertFirstCharToUpperCase(String str)
	{
		if(str == null)
			return null;
		else if(str.length() > 1)
			return str.substring(0,1).toUpperCase() + str.substring(1, str.length());
		else 
			return str;
	}

	public static String convertFirstCharToLowerCase(String str)
	{
		if(str == null)
			return null;
		else if(str.length() > 1)
			return str.substring(0,1).toLowerCase() + str.substring(1, str.length());
		else 
			return str;
	}
}
