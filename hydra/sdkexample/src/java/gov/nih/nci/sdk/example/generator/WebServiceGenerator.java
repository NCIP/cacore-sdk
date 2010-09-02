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
	String packageName;
	String serviceName;
	
	public WebServiceGenerator(ScriptContext _scriptContext)
	{
		super(_scriptContext);
		packageName = EcoreUtil.determinePackageName(_scriptContext.getFocusDomain());
		serviceName = EcoreUtil.determineClassName(_scriptContext.getFocusDomain()) + "Service";
	}

	protected void init()
	{
		System.out.println("Generating Webservice artifacts....");
	}

	protected void preProcess()
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
	}

	protected void validate()
	{
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{
		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen", GeneratorUtil.getTemplatesPath());
		
		generateWebServiceInterface(group);
		generateWebServiceAbstract(group);
		generateWebServiceImpl(group);
		compileWebServiceInterface();
		generateWebServiceArtifacts(group);
		generateWebServiceClient(group);
		
		getScriptContext().logInfo("Webservice generation is completed!");
	}

	private void generateWebServiceInterface(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceInterface");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", serviceName);
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
				//will add this utility there.  Right now I just want
				//to get this doing something so that I can explore how
				//EOperations work.
				boolean isService = (eAnnotation != null && eAnnotation.getDetails().get("oper.ser.service").equalsIgnoreCase("false") == false) ? true : false;

				if (isService == true)
				{
					StringTemplate operationTemplate = new StringTemplate("public $returnType$ $name$($param; separator=\", \"$);");
					operationTemplate.setAttribute("returnType", eOperation.getEType());
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
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(getScriptContext()), serviceName + ".java", template.toString());
	}

	private void generateWebServiceAbstract(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceAbstractImpl");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", serviceName);


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
				//will add this utility there.  Right now I just want
				//to get this doing something so that I can explore how
				//EOperations work.
				boolean isService = (eAnnotation != null && eAnnotation.getDetails().get("oper.ser.service").equalsIgnoreCase("false") == false) ? true : false;

				if (isService == true)
				{
					StringTemplate operationTemplate = new StringTemplate("public $returnType$ $name$($param; separator=\", \"$);");
					operationTemplate.setAttribute("returnType", eOperation.getEType());
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
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(getScriptContext()), serviceName + "ImplAbstract.java", template.toString());
	}

	private void generateWebServiceImpl(StringTemplateGroup _group)
	{
		String filePath = GeneratorUtil.getServiceImplPath(getScriptContext());
		// Check if the template file exist
		String fileName = filePath + File.separator + serviceName + "Impl.java";
		File file = new File(fileName);

		if (file.exists() != true)
		{
			StringTemplate template = _group.getInstanceOf("WebServiceImpl");
			//Set template tokens
			template.setAttribute("packageName", GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()));
			template.setAttribute("importSt", getImportStmt());
			template.setAttribute("interfaceName", serviceName);

			//Generate and write the template output
			GeneratorUtil.writeFile(filePath, serviceName+"Impl.java", template.toString());
		}
	}

	private void generateWebServiceClient(StringTemplateGroup _group)
	{
		StringTemplate template = _group.getInstanceOf("WebServiceClient");
		
		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServiceClientPackageName(getScriptContext().getFocusDomain()));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("importSt", "import " + GeneratorUtil.getServicePackageName(getScriptContext().getFocusDomain()) + ".*;");
		template.setAttribute("clientName", serviceName+"Client");
		template.setAttribute("packagePath", GeneratorUtil.getServiceClientPackageName(getScriptContext().getFocusDomain()).
							  replaceAll("\\.", File.separator + File.separator) + File.separator + File.separator);

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServiceClientPath(getScriptContext()), serviceName + "Client.java", template.toString());
	}

	private void compileWebServiceInterface()
	{
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
			String[] files1 = compilerFiles.toArray(new String[compilerFiles.size()]);

			// Check if output directory exist, create it
			GeneratorUtil.createOutputDir(projectRoot + File.separator + "classes");

			List<String> options = new ArrayList<String>();
			options.add("-classpath");
			String classPathStr = GeneratorUtil.getFiles(projectRoot +
					File.separator + "lib",
					new String[] { "jar" }, File.pathSeparator)
					+ File.pathSeparator
					+ projectRoot 
					+ File.separatorChar + "classes";
			options.add(classPathStr);

			options.add("-d");
			options.add(projectRoot	+ File.separator + "classes");

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
	}

	private void generateWebServiceArtifacts(StringTemplateGroup _group)
	{
		String projectRoot = getScriptContext().getProperties().getProperty("PROJECT_ROOT");
		String cxfHome = getScriptContext().getProperties().getProperty("CXF_HOME");
		String classesPath = projectRoot + File.separatorChar + "classes";
		String srcPath = projectRoot + File.separatorChar + getScriptContext().getProperties().getProperty("PROJECT_SRC") +
						 File.separatorChar + "generated";
		String cxfClassPath = cxfHome + File.separatorChar + "lib" + File.separatorChar + "cxf-manifest.jar";
		String javaToolPath = getScriptContext().getProperties().getProperty("JAVA_HOME") +
							  File.separatorChar + "lib" + File.separatorChar + "tools.jar";
		String classPath = classesPath + File.pathSeparator + cxfClassPath + File.pathSeparator + javaToolPath;
		String packageName = EcoreUtil.determinePackageName(getScriptContext().getFocusDomain());

		String cmd = cxfHome + File.separator + "bin" + File.separator	+ "java2ws.bat";
		String argStr = " -wsdl -server -client -ant -cp " + classPath;
		argStr += " -classdir " + classesPath;
		argStr += " -s " + srcPath;
		argStr += " -createxsdimports";
		argStr += " -d " + projectRoot + File.separator + "conf";
		argStr += " -servicename "+ serviceName;
		argStr += " -portname "+serviceName + " ";
		argStr += packageName + ".service."+serviceName+"Impl";
		
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
					System.out.println(s);
				}

				// read any errors from the attempted command
				while ((s = stdError.readLine()) != null)
				{
					getScriptContext().logError(s);
				}
			}
		}
		catch(Throwable t)
		{
			getScriptContext().logError(t); 
		}
	}

	protected void postProcess(){}

	private String getImportStmt()
	{
		return "import " + packageName + "."+Generator.JAXBPOJO_PACKAGE_NAME+".*;";
	}
}