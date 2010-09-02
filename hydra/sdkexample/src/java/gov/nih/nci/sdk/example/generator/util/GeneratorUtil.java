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

import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.Generator;

public class GeneratorUtil {

	public static StringTemplate getTemplate(String _templateName)
	{
		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen");
		return group.getInstanceOf(_templateName);
	}

	public static void writeFile(String _outputDir, String _fileName, String _content)
	{
		BufferedWriter bufferedWriter = null;

		try
		{
			createOutputDir(_outputDir);
			File file = new File(_outputDir, _fileName);
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(_content);
		}
		catch (Throwable t)
		{
			throw new RuntimeException(t);
		}
		finally
		{
			if (bufferedWriter != null) { try { bufferedWriter.close(); } catch (Throwable t) { } }
		}
	}

	public static void createOutputDir(String _outputDir)
	{
		try
		{
			File file = new File(_outputDir);
			
			if (!file.exists() == true)
			{
				FileUtils.forceMkdir(file);
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
	}

	public static List getFiles(String _dir, String[] _extensions)
	{
		List<String> files = new ArrayList();
		try
		{
			Iterator iter = FileUtils.iterateFiles(new File(_dir), _extensions, false);
			
			while (iter.hasNext() == true)
			{
				File file = (File) iter.next();
				files.add(file.getAbsolutePath());
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
		
		return files;
	}

	public static String getFiles(String _dir, String[] _extensions, String _seperator)
	{
		StringBuffer files = new StringBuffer();
		
		try
		{
			Iterator iter = FileUtils.iterateFiles(new File(_dir), _extensions, false);
			
			while (iter.hasNext() == true)
			{
				File file = (File) iter.next();
				files.append(file.getAbsolutePath()).append(_seperator);
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
		
		return files.toString();
	}

	public static String getJaxbPojoPath(ScriptContext _scriptContext)
	{
		String jaxbPojoPath = getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.determinePackageName(_scriptContext.getFocusDomain()).replaceAll("\\.", File.separator)
				+ File.separator + Generator.JAXBPOJO_PACKAGE_NAME;

		return jaxbPojoPath;
	}

	public static String getPojoPath(ScriptContext _scriptContext)
	{
		System.out.println("Getting pojo path for domain: " + _scriptContext.getFocusDomain());
		String jaxbPojoPath = getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.determinePackageName(_scriptContext.getFocusDomain()).replaceAll("\\.", File.separator)
				+ File.separator + Generator.POJO_PACKAGE_NAME;

		System.out.println("Getting pojo path: " + jaxbPojoPath);
		return jaxbPojoPath;
	}
	
	public static String getServiceImplPath(ScriptContext _scriptContext)
	{
		String serviceImplPath = getImplPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.determinePackageName(_scriptContext.getFocusDomain()).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;

		return serviceImplPath;
	}

	public static String getServiceClientPath(ScriptContext _scriptContext)
	{
		return getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.determinePackageName(_scriptContext.getFocusDomain()).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME
				+ File.separator
				+ Generator.SERVICE_CLIENT_PACKAGE_NAME;
	}
	
	public static String getServicePath(ScriptContext _scriptContext)
	{
		return getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.determinePackageName(_scriptContext.getFocusDomain()).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;
	}

	public static String getGeneratedPath(ScriptContext _scriptContext) {

		System.out.println("Getting generated path for focus domain: " + _scriptContext.getFocusDomain());
		String returnString =  _scriptContext.getProperties().getProperty("PROJECT_ROOT")
				+ File.separator
				+ _scriptContext.getProperties().getProperty("PROJECT_SRC")
				+ File.separator 
							   + Generator.GENERATED_PACKAGE_NAME;

		System.out.println("Generated path: " + returnString);
		return returnString;
	}

	public static String getImplPath(ScriptContext _scriptContext) {
		return _scriptContext.getProperties().getProperty("PROJECT_ROOT")
				+ File.separator
				+ _scriptContext.getProperties().getProperty("PROJECT_SRC")
				+ File.separator 
				+ Generator.IMPL_PACKAGE_NAME;
	}

	public static String getServicePackageName(String _fullyQualifiedClassName)
	{
		return EcoreUtil.determinePackageName(_fullyQualifiedClassName) + "." + Generator.SERVICE_PACKAGE_NAME;
	}

	public static String getJaxbPojoPackageName(String _fullyQualifiedClassName)
	{
		return EcoreUtil.determinePackageName(_fullyQualifiedClassName) + "." + Generator.JAXBPOJO_PACKAGE_NAME;
	}
	
	public static String getPojoPackageName(String _fullyQualifiedClassName)
	{
		return EcoreUtil.determinePackageName(_fullyQualifiedClassName) + "." + Generator.POJO_PACKAGE_NAME;
	}

	public static String getServiceClientPackageName(String _fullyQualifiedClassName)
	{
		return EcoreUtil.determinePackageName(_fullyQualifiedClassName)
			+ "."
			+ Generator.SERVICE_PACKAGE_NAME
			+ "."
			+ Generator.SERVICE_CLIENT_PACKAGE_NAME;
	}
	
	public static String getClassesPath(ScriptContext _scriptContext)
	{
		return _scriptContext.getProperties().getProperty("PROJECT_ROOT") + File.separator + "classes";
	}

	public static String getTemplatesPath()
	{
		return gov.nih.nci.sdk.example.generator.Generator.TEMPLATES_PACKAGE_NAME;
	}

	public static String convertFirstCharToUpperCase(String _string)
	{
		String capitalizedString = _string;

		if(_string != null && _string.length() > 0)
		{
			capitalizedString = _string.substring(0,1).toUpperCase();
			
			if (_string.length() > 1)
			{
				capitalizedString += _string.substring(1, _string.length());
			}
		}

		return capitalizedString;
	}

	public static String convertFirstCharToLowerCase(String _string)
	{
		String capitalizedString = _string;

		if(_string != null && _string.length() > 0)
		{
			capitalizedString = _string.substring(0,1).toLowerCase();

			if (_string.length() > 1)
			{
				capitalizedString += _string.substring(1, _string.length());
			}
		}

		return capitalizedString;
	}
}