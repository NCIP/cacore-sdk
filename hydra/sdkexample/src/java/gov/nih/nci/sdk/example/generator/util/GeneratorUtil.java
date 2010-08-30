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

import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.Generator;

public class GeneratorUtil {

	public static StringTemplate getTemplate(String _templatePath, String _templateName)
	{
		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen", _templatePath);
		return group.getInstanceOf(_templateName);
	}

	public static void writeFile(String _outputDir, String _fileName, String _content)
	{
		BufferedWriter bufferedWriter = new BufferedWriter(w);

		try
		{
			createOutputDir(_outputDir);
			File file = new File(_outputDir, _fileName);
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
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
		try
		{
			StringBuffer files = new StringBuffer();
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
				+ EcoreUtil.getPackageName(_scriptContext).replaceAll("\\.", File.separator)
				+ File.separator + Generator.JAXBPOJO_PACKAGE_NAME;

		return jaxbPojoPath;
	}

	public static String getPojoPath(ScriptContext _scriptContext)
	{
		String jaxbPojoPath = getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.getPackageName(_scriptContext).replaceAll("\\.", File.separator)
				+ File.separator + Generator.POJO_PACKAGE_NAME;

		return jaxbPojoPath;
	}
	
	public static String getServiceImplPath(ScriptContext _scriptContext)
	{
		String serviceImplPath = getImplPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.getPackageName(_scriptContext).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;

		return serviceImplPath;
	}

	public static String getServiceClientPath(ScriptContext _scriptContext)
	{
		return getGeneratedPath(context)
				+ File.separator
				+ EcoreUtil.getPackageName(_scriptContext).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME
				+ File.separator
				+ Generator.SERVICE_CLIENT_PACKAGE_NAME;
	}
	
	public static String getServicePath(ScriptContext _scriptContext)
	{
		return getGeneratedPath(_scriptContext)
				+ File.separator
				+ EcoreUtil.getPackageName(_scriptContext).replaceAll("\\.", File.separator)
				+ File.separator 
				+ Generator.SERVICE_PACKAGE_NAME;
	}

	public static String getGeneratedPath(ScriptContext _scriptContext) {
		return _scriptContext.getGeneratorProperties().getValue(
				"PROJECT_ROOT")
				+ File.separator
				+ _scriptContext.getProperties().getValue("PROJECT_SRC")
				+ File.separator 
				+ Generator.GENERATED_PACKAGE_NAME;
	}

	public static String getImplPath(ScriptContext _scriptContext) {
		return _scriptContext.getProperties().getValue(
				"PROJECT_ROOT")
				+ File.separator
				+ _scriptContext.getProperties().getValue("PROJECT_SRC")
				+ File.separator 
				+ Generator.IMPL_PACKAGE_NAME;
	}

	public static String getServicePackageName(ScriptContext _scriptContext)
	{
		return EcoreUtil.getPackageName(_scriptContext.getFocusDomain(), _scriptContext) + "." + Generator.SERVICE_PACKAGE_NAME;
	}

	public static String getJaxbPojoPackageName(ScriptContext _scriptContext)
	{
		return EcoreUtil.getPackageName(_scriptContext.getFocusDomain(), _scriptContext) + "." + Generator.JAXBPOJO_PACKAGE_NAME;
	}
	
	public static String getPojoPackageName(ScriptContext _scriptContext)
	{
		return EcoreUtil.getPackageName(_scriptContext.getFocusDomain(), _scriptContext) + "." + Generator.POJO_PACKAGE_NAME;
	}

	public static String getServiceClientPackageName(ScriptContext _scriptContext)
	{
		return EcoreUtil.getPackageName(_scriptContext.getFocusDomain(), _scriptContext)
			+ "."
			+ Generator.SERVICE_PACKAGE_NAME
			+ "."
			+ Generator.SERVICE_CLIENT_PACKAGE_NAME;
	}
	
	public static String getClassesPath(ScriptContext _scriptContext)
	{
		return _scriptContext.getProperties().getValue("PROJECT_ROOT") + File.separator + "classes";
	}

	public static String getTemplatesPath()
	{
		return TEMPLATES_PACKAGE_NAME;
	}

	public static String convertFirstCharToUpperCase(String _string)
	{
		String capitalizedString = _string;

		if(_string != null && _string.length() > 0)
		{
			capitalizedString = _string.substring(0,1).toUpperCase();
			
			if (str.length() > 1)
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

			if (str.length() > 1)
			{
				capitalizedString += _string.substring(1, _string.length());
			}
		}

		return capitalizedString;
	}
}