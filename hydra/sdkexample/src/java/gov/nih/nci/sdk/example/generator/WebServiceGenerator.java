package gov.nih.nci.sdk.example.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.io.FileUtils;

import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

public class WebServiceGenerator
   extends Generator
{	
	public WebServiceGenerator(ScriptContext _scriptContext)
	{
		super(_scriptContext);
	}

	private String createPackageName(String _focusDomain)
	{
		return EcoreUtil.determinePackageName(_focusDomain);
	}

	private String createServiceName(String _focusDomain)
	{
		return EcoreUtil.determineClassName(_focusDomain) + "Service";
	}
	
	protected void init()
	{
		getScriptContext().logInfo("Generating Webservice artifacts....");
	}

	protected void preProcess()
	{
		if(getScriptContext().getMemory().get("preProcess") == null)
		{
			// Delete generated folder
			try
			{
				String generatedPath = GeneratorUtil.getServicePath(getScriptContext());
				if (new File(generatedPath).exists() == true) {	FileUtils.forceDelete(new File(generatedPath)); }	
			}
			catch (Throwable t)
			{
				getScriptContext().logError(t);
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			try
			{
				String classesPath = GeneratorUtil.getClassesPath(getScriptContext());

				if (new File(classesPath).exists() == true)
				{
					FileUtils.forceDelete(new File(classesPath));
				}
			}
			catch (Throwable t)
			{
				getScriptContext().logError(t);
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			getScriptContext().getMemory().put("preProcess", "");
		}
	}

	protected void validate()
	{
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{
		generateWebServiceInterface(getScriptContext().getTemplateGroup());
		generateWebServiceAbstract(getScriptContext().getTemplateGroup());
		generateWebServiceImpl(getScriptContext().getTemplateGroup());
		generateWebServiceClient(getScriptContext().getTemplateGroup());
		compileWebServiceInterface();
		
		getScriptContext().logInfo("Webservice generation is completed!");
	}

	private void generateWebServiceInterface(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceInterface");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", createServiceName(getScriptContext().getFocusDomain()));
		List<EOperation> eOperationList = new java.util.ArrayList();

		EClass eClass = EcoreUtil.getEClass(getScriptContext().getEPackage(), getScriptContext().getFocusDomain());
		EAnnotation defaultServiceAnnotation = eClass.getEAnnotation("oper.ser.service");

		for (EOperation eOperation: eClass.getEAllOperations())
		{
			EAnnotation eAnnotation = eOperation.getEAnnotation("oper.ser.service");
			if (eAnnotation != null)
			{
				//TODO this logic is not quite right as it does not
				//take the class level default into account.  We really
				//need to write a method to handle the assignment of
				//defaults.  This should really be in the SDKUtil class
				//John is workin on.  Once he has created this class I
				//will use this utility there.  Right now I just want
				//to get this doing something so that I can explore how
				//EOperations work.
				boolean isService = (eAnnotation != null && eAnnotation.getDetails().get("oper.ser.service").equalsIgnoreCase("false") == false) ? true : false;

				if (isService == true)
				{
					StringTemplate operationTemplate = new StringTemplate("public $returnType$ $name$($param; separator=\", \"$);");
					operationTemplate.setAttribute("returnType", eOperation.getEType().getInstanceClassName());
					operationTemplate.setAttribute("name", eOperation.getName());

					for (EParameter eParameter: eOperation.getEParameters())
					{
						operationTemplate.setAttribute("param", eParameter.getEType().getName() + " " + eParameter.getName());
					}
					
					template.setAttribute("operation", operationTemplate.toString());
				}
			}
		}

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(getScriptContext()), createServiceName(getScriptContext().getFocusDomain()) + ".java", template.toString());
	}

	private void generateWebServiceAbstract(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceAbstractImpl");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", createServiceName(getScriptContext().getFocusDomain()));


		EClass eClass = EcoreUtil.getEClass(getScriptContext().getEPackage(), getScriptContext().getFocusDomain());
		EAnnotation defaultServiceAnnotation = eClass.getEAnnotation("oper.ser.service");

		for (EOperation eOperation: eClass.getEAllOperations())
		{
			EAnnotation eAnnotation = eOperation.getEAnnotation("oper.ser.service");
			if (eAnnotation != null)
			{
				//TODO this logic is not quite right as it does not
				//take the class level default into account.  We really
				//need to write a method to handle the assignment of
				//defaults.  This should really be in the SDKUtil class
				//John is working on.  Once he has created this class I
				//will add this utility there.  Right now I just want
				//to get this doing something so that I can explore how
				//EOperations work.
				boolean isService = (eAnnotation != null && eAnnotation.getDetails().get("oper.ser.service").equalsIgnoreCase("false") == false) ? true : false;

				if (isService == true)
				{
					boolean voidOperation = ("void".equalsIgnoreCase(eOperation.getEType().getInstanceClassName()) == true);

					String operationTemplateName = (voidOperation == true) ? "WebServiceAbstractVoidOperation" : "WebServiceAbstractOperation";
					
					StringTemplate operationTemplate = _group.getInstanceOf(operationTemplateName);
					operationTemplate.setAttribute("name", eOperation.getName());
					
					if (voidOperation == false)
					{
						operationTemplate.setAttribute("returnType", eOperation.getEType().getInstanceClassName());
						operationTemplate.setAttribute("dummyValue", getDummyValue(eOperation.getEType().getInstanceClassName()));
					}

					for (EParameter eParameter: eOperation.getEParameters())
					{
						operationTemplate.setAttribute("param", eParameter.getEType().getName() + " " + eParameter.getName());
					}

					template.setAttribute("operation", operationTemplate.toString());
				}
			}
		}

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(getScriptContext()), createServiceName(getScriptContext().getFocusDomain()) + "ImplAbstract.java", template.toString());
	}

	private void generateWebServiceImpl(StringTemplateGroup _group)
	{
		String filePath = GeneratorUtil.getServiceImplPath(getScriptContext());
		// Check if the template file exist
		String fileName = filePath + File.separator + createServiceName(getScriptContext().getFocusDomain()) + "Impl.java";
		File file = new File(fileName);

		if (file.exists() != true)
		{
			StringTemplate template = _group.getInstanceOf("WebServiceImpl");
			//Set template tokens
			template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
			template.setAttribute("importSt", getImportStmt());
			template.setAttribute("interfaceName", createServiceName(getScriptContext().getFocusDomain()));

			//Generate and write the template output
			GeneratorUtil.writeFile(filePath, createServiceName(getScriptContext().getFocusDomain())+"Impl.java", template.toString());
		}
	}

	private void generateWebServiceClient(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceClient");
		
		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServiceClientPackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("importSt", "import " + GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()) + ".*;");
		template.setAttribute("clientName", createServiceName(getScriptContext().getFocusDomain())+"Client");
		template.setAttribute("packagePath", GeneratorUtil.getServiceClientPackageName(getScriptContext().getFocusDomain()).
							  replaceAll("\\.", File.separator + File.separator) + File.separator + File.separator);

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServiceClientPath(getScriptContext()), createServiceName(getScriptContext().getFocusDomain()) + "Client.java", template.toString());
	}

	private void compileWebServiceInterface()
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
			
			StandardJavaFileManager fileManager = null;

			try
			{
				String jaxbPojoPath = GeneratorUtil.getJaxbPojoPath(getScriptContext());
				String servicePath = GeneratorUtil.getServicePath(getScriptContext());
				String serviceImplPath = GeneratorUtil.getServiceImplPath(getScriptContext());
				String projectRoot = getScriptContext().getProperties().getProperty("PROJECT_ROOT");

				List<String> compilerFiles = GeneratorUtil.getFiles(jaxbPojoPath, new String[] { "java" });
				compilerFiles.addAll(GeneratorUtil.getFiles(servicePath, new String[] { "java" }));
				compilerFiles.addAll(GeneratorUtil.getFiles(serviceImplPath, new String[] { "java" }));

				getScriptContext().logInfo("Compiling files: " + compilerFiles);
				// Check if output directory exist, create it
				GeneratorUtil.createOutputDir(projectRoot + File.separator + "classes");

				List<String> options = new ArrayList<String>();
				options.add("-classpath");
				String classPathStr = GeneratorUtil.getFiles(new java.io.File(getScriptContext().getGeneratorBase()).getAbsolutePath() +
						File.separator + "lib",
						new String[] { "jar" }, File.pathSeparator)
						+ File.pathSeparator
						+ new java.io.File(projectRoot + File.separatorChar + "classes").getAbsolutePath();


				getScriptContext().logInfo("compiler classpath is: " + classPathStr);

				options.add(classPathStr);

				options.add("-d");
				options.add(projectRoot	+ File.separator + "classes");

				options.add("-s");
				options.add(projectRoot	+ File.separator + "src/generated");

				JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
				fileManager = compiler.getStandardFileManager(diagnostics, null, null);
				Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(compilerFiles);
				JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
				boolean success = task.call();

				for (Diagnostic diagnostic : diagnostics.getDiagnostics())
				{
					getScriptContext().logInfo(diagnostic.getCode());
					getScriptContext().logInfo(diagnostic.getKind().toString());
					getScriptContext().logInfo(diagnostic.getPosition() + "");
					getScriptContext().logInfo(diagnostic.getStartPosition() + "");
					getScriptContext().logInfo(diagnostic.getEndPosition() + "");
					getScriptContext().logInfo(diagnostic.getSource().toString());
					getScriptContext().logInfo(diagnostic.getMessage(null));
				}
			}
			catch (Throwable t)
			{
				getScriptContext().logError(t);
			}
			finally
			{
				try	{ fileManager.close(); } catch (Throwable t) {}
			}

			for (String focusDomain: getScriptContext().retrieveDomainSet())
			{
				getScriptContext().logWarning("Calling HALLOWEEN for: "+ focusDomain);
				generateWebServiceArtifacts(focusDomain);
			}
		}
	}

	private void generateWebServiceArtifacts(String _focusDomain)
	{
		String projectRoot = getScriptContext().getProperties().getProperty("PROJECT_ROOT");
		String cxfHome = getScriptContext().getProperties().getProperty("CXF_HOME");
		String classesPath = projectRoot + File.separatorChar + "classes";
		String srcPath = projectRoot + File.separatorChar + getScriptContext().getProperties().getProperty("PROJECT_SRC") +
						 File.separatorChar + "generated";
		String cxfClassPath = cxfHome + File.separatorChar + "lib" + File.separatorChar + "cxf-manifest.jar";
		String javaToolPath = getScriptContext().getProperties().getProperty("JAVA_HOME") +
							  File.separatorChar + "lib" + File.separatorChar + "tools.jar";
		String classPath = "\"" + classesPath + File.pathSeparator + cxfClassPath + File.pathSeparator + javaToolPath + "\"";
		String packageName = createPackageName(_focusDomain);
		
		String cmd = cxfHome + File.separator + "bin" + File.separator	+ "java2ws.bat";
		String argStr = " -wsdl -client -server -cp " + classPath;
		argStr += " -classdir " + classesPath;
		argStr += " -s " + srcPath;
		argStr += " -createxsdimports";
		argStr += " -d " + projectRoot + File.separator + "conf";
		argStr += " -servicename "+ createServiceName(_focusDomain);
		argStr += " -portname "+ createServiceName(_focusDomain) + " ";
		argStr += packageName + ".service."+createServiceName(_focusDomain)+"Impl";

		getScriptContext().logInfo("Command: " + cmd + argStr);
		
		runCommand(cmd + argStr);
	}

	public void runCommand(String _command)
	{
		try
		{
			StringBuffer input = new StringBuffer();

			input.setLength(0); // erase input StringBuffer
			String s;

			if (_command != null)
			{
				Runtime a = Runtime.getRuntime();
				java.lang.Process p = a.exec(_command);

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(
						p.getInputStream()));

				BufferedReader stdError = new BufferedReader(new InputStreamReader(
						p.getErrorStream()));

				// read the output from the command

				// System.out.println("Here is the standard output of the command:\n");
				while ((s = stdInput.readLine()) != null) {
					input.append(s);
				}

				// read any errors from the attempted command
				while ((s = stdError.readLine()) != null)
				{
					getScriptContext().logInfo(s);
				}
				
			}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			getScriptContext().logError(t); 
		}
	}

	protected void postProcess(){}

	private String getImportStmt()
	{
		return "import " + createPackageName(getScriptContext().getFocusDomain()) + "."+Generator.JAXBPOJO_PACKAGE_NAME+".*;";
	}

	private String getDummyValue(String _type)
	{
		String dummyValue = "\"\"";

		if ("byte".equals(_type) == true ||
				"java.lang.Byte".equals(_type) == true ||
				"int".equals(_type) == true ||
				"java.lang.Integer".equals(_type) == true ||
				"short".equals(_type) == true ||
			  "java.lang.Short".equals(_type) == true)
		{
			dummyValue = "0";
		}
		else if ("long".equals(_type) == true ||
				 "java.lang.Long".equals(_type) == true)
		{
			dummyValue = "0l";
		}
		else if ("double".equals(_type) == true ||
				"java.lang.Double".equals(_type) == true ||
				"float".equals(_type) == true ||
				 "java.lang.Float".equals(_type) == true)
		{
				dummyValue = "0.0";
		}
		else if ("char".equals(_type) == true ||
				 "java.lang.Character".equals(_type) == true)
		{
			dummyValue = "\'\'";
		}
		else if ("boolean".equals(_type) == true ||
				 "java.lang.Boolean".equals(_type) == true)
		{
			dummyValue = "\'\'";
		}
		else
		{
			dummyValue = "new " + _type + "()";
		}

		return dummyValue;
	}
}