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

import gov.nih.nci.sdk.core.GeneratorContext;
import gov.nih.nci.sdk.example.generator.util.GeneratorDomainUtil;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

public class WebServiceGenerator extends Generator {

	String packageName;
	String serviceName;
	
	public WebServiceGenerator(GeneratorContext context) {
		super(context);
		packageName = context.getDomain().getPackageName();
		serviceName = context.getDomain().getName()+"Service";
	}

	protected void init() {
		System.out.println("Generating Webservice artifacts....");
	}

	protected void preProcess() {
		// Delete generated folder
		try {
			String generatedPath = GeneratorUtil.getServicePath(context);
			if (new File(generatedPath).exists())
				FileUtils.forceDelete(new File(generatedPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String classesPath = GeneratorUtil.getClassesPath(context);
			if (new File(classesPath).exists())
				FileUtils.forceDelete(new File(classesPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void validate() {
		// TODO Auto-generated method stub
	}

	public void runProcess() {
		StringTemplateGroup group = new StringTemplateGroup("sdkCodeGen",
				GeneratorUtil.getTemplatesPath(context));
		generateWebServiceInterface(group);
		generateWebServiceAbstract(group);
		generateWebServiceImpl(group);
		compileWebServiceInterface();
		generateWebServiceArtifacts(group);
		generateWebServiceClient(group);
		System.out.println("Webservice generation is completed!");
	}

	private void generateWebServiceInterface(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("WebServiceInterface");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(context));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", serviceName);
		ECOREElement[] elements = GeneratorDomainUtil.getServiceOperationsList(context.getDomain());
		template.setAttribute("ECOREElement", elements);

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(context), serviceName+".java", template.toString());
	}

	private void generateWebServiceAbstract(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("WebServiceAbstractImpl");

		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(context));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", serviceName);
		ECOREElement[] elements = GeneratorDomainUtil.getServiceOperationsList(context.getDomain());
		template.setAttribute("ECOREElement", elements);

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServicePath(context), serviceName+"ImplAbstract.java",
				template.toString());

	}

	private void generateWebServiceImpl(StringTemplateGroup group) {
		String filePath = GeneratorUtil.getServiceImplPath(context);
		// Check if the template file exist
		String fileName = filePath + File.separator + serviceName+"Impl.java";
		File file = new File(fileName);
		if (file.exists())
			return;

		StringTemplate template = group.getInstanceOf("WebServiceImpl");
		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServicePackageName(context));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("interfaceName", serviceName);

		//Generate and write the template output
		GeneratorUtil.writeFile(filePath, serviceName+"Impl.java", template.toString());
	}

	private void generateWebServiceClient(StringTemplateGroup group) {
		StringTemplate template = group.getInstanceOf("WebServiceClient");
		
		//Set template tokens
		template.setAttribute("packageName", GeneratorUtil.getServiceClientPackageName(context));
		template.setAttribute("importSt", getImportStmt());
		template.setAttribute("importSt", "import " + GeneratorUtil.getServicePackageName(context) + ".*;");
		template.setAttribute("clientName", serviceName+"Client");
		template.setAttribute("packagePath", GeneratorUtil.getServiceClientPackageName(context).replace(".", File.separator+File.separator)+File.separator+File.separator);

		//Generate and write the template output
		GeneratorUtil.writeFile(GeneratorUtil.getServiceClientPath(context), serviceName+"Client.java", template.toString());
	}

	@SuppressWarnings("unchecked")
	private void compileWebServiceInterface() {
		try {
			String jaxbPojoPath = GeneratorUtil.getJaxbPojoPath(context);
			String servicePath = GeneratorUtil.getServicePath(context);
			String serviceImplPath = GeneratorUtil.getServiceImplPath(context);
			String projectRoot = context.getGeneratorProperties().getValue("PROJECT_ROOT");
			
			List<String> compilerFiles = GeneratorUtil.getFiles(jaxbPojoPath,
					new String[] { "java" });
			compilerFiles.addAll(GeneratorUtil.getFiles(servicePath,
					new String[] { "java" }));
			compilerFiles.addAll(GeneratorUtil.getFiles(serviceImplPath,
					new String[] { "java" }));
			String[] files1 = compilerFiles.toArray(new String[compilerFiles
					.size()]);

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
			StandardJavaFileManager fileManager = compiler
					.getStandardFileManager(diagnostics, null, null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager
					.getJavaFileObjectsFromStrings(compilerFiles);
			JavaCompiler.CompilationTask task = compiler.getTask(null,
					fileManager, diagnostics, options, null, compilationUnits);
			boolean success = task.call();

			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				System.out.println(diagnostic.getCode());
				System.out.println(diagnostic.getKind());
				System.out.println(diagnostic.getPosition());
				System.out.println(diagnostic.getStartPosition());
				System.out.println(diagnostic.getEndPosition());
				System.out.println(diagnostic.getSource());
				System.out.println(diagnostic.getMessage(null));

			}

			try {
				fileManager.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void generateWebServiceArtifacts(StringTemplateGroup group) {
		String projectRoot = context.getGeneratorProperties().getValue(
				"PROJECT_ROOT");
		String cxfHome = context.getGeneratorProperties().getValue("CXF_HOME");
		String classesPath = projectRoot + File.separatorChar + "classes";
		String srcPath = projectRoot + File.separatorChar
				+ context.getGeneratorProperties().getValue("PROJECT_SRC")
				+ File.separatorChar + "generated";
		String cxfClassPath = cxfHome + File.separatorChar + "lib"
				+ File.separatorChar + "cxf-manifest.jar";
		String javaToolPath = context.getGeneratorProperties().getValue(
				"JAVA_HOME")
				+ File.separatorChar + "lib" + File.separatorChar + "tools.jar";
		String classPath = classesPath + File.pathSeparator + cxfClassPath
				+ File.pathSeparator + javaToolPath;
		String packageName = context.getDomain().getPackageName();

		String cmd = cxfHome + File.separator + "bin" + File.separator
				+ "java2ws.bat";
		String argStr = " -wsdl -server -client -ant -cp " + classPath;
		argStr += " -classdir " + classesPath;
		argStr += " -s " + srcPath;
		argStr += " -createxsdimports";
		argStr += " -d " + projectRoot + File.separator + "conf";
		argStr += " -servicename "+ serviceName;
		argStr += " -portname "+serviceName + " ";
		argStr += packageName + ".service."+serviceName+"Impl";
		try {
			runCommand(cmd + argStr);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void runCommand(String command) throws IOException {
		StringBuffer input = new StringBuffer();
		StringBuffer error = new StringBuffer();

		input.setLength(0); // erase input StringBuffer
		error.setLength(0); // erase error StringBuffer
		String s;
		if (command != null) {
			Runtime a = Runtime.getRuntime();
			java.lang.Process p = a.exec(command);

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

			// System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
				error.append(s);
			}
		} else {
			throw new NullPointerException();
		}
	}

	protected void postProcess() {
		// TODO Auto-generated method stub
	}

	private String getImportStmt()
	{
		return "import " + packageName + "."+Generator.JAXBPOJO_PACKAGE_NAME+".*;";
	}
}
