package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Input;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Operation;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.mapping.model.Output;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;
import gov.nih.nci.restgen.util.JarHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.core.annotation.AnnotationUtils;

import com.predic8.schema.ComplexType;
import com.predic8.schema.Element;
import com.predic8.schema.Schema;
import com.predic8.schema.TypeDefinition;
import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Fault;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;

/**
 * RESTful resource generator
 * This generator works with EJB and SOAP web service mapping and generate RESTful resources. 
 * StringTemplate is used to for code template. Generator parsess mapping information and 
 * populates code template with values.
 * @author konkapv
 *
 */
public class RESTfulResourceGenerator extends Generator {
	
	/**
	 * Constructor 
	 * @param context
	 */
	public RESTfulResourceGenerator(GeneratorContext context) {
		super(context);
	}

	protected void init() {
		getContext().getLogger().info("Generating RESTful resource...");
	}

	protected void preProcess() {
		//getContext().getLogger().info("Generating RESTful resource...preProcess");
	}

	protected void validate() {
		//getContext().getLogger().info("Generating RESTful resource...validate");
	}

	/**
	 * Generate RESTful resource based on mapping to EJB or SOAP web service.
	 */
	public void runProcess() throws GeneratorException {
		//getContext().getLogger().info("Generating RESTful resource...runProcess started");
		Mapping mapping = context.getMapping();
		if (mapping.getOptions().getWrapperType().equals(Options.SOAP_SERVICE))
			runProcessSOAP();
		else if (mapping.getOptions().getWrapperType().equals(Options.EJB))
			runProcessEJB();
		else
			throw new GeneratorException("Invalid wrapper type.");
	}

	/**
	 * Generate RESTful resource based on SOAP web service mapaping
	 * 
	 * @throws GeneratorException
	 */
	private void runProcessSOAP() throws GeneratorException {
		Mapping mapping = context.getMapping();
		List<Resource> resources = mapping.getResources();
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate putTemplate = null;
		StringTemplate postTemplate = null;
		StringTemplate getTemplate = null;
		StringTemplate deleteTemplate = null;
		
		WSDLParser parser = new WSDLParser();

		Definitions defs = parser.parse(context.getMapping().getOptions()
				.getWsdlLocation());

		for (Resource resource : resources) {
			StringTemplate resourceTemplate = group
					.getInstanceOf("gov/nih/nci/restgen/templates/RESTfulResource");
			String resourceName = resource.getName();
			List<Method> methods = resource.getMethods();
			List<String> getMethodStr = new ArrayList<String>();
			List<String> putMethodStr = new ArrayList<String>();
			List<String> postMethodStr = new ArrayList<String>();
			List<String> deleteMethodStr = new ArrayList<String>();
			int counter = 1;
			for (Method method : methods) {
				getTemplate = group
						.getInstanceOf("gov/nih/nci/restgen/templates/GetMethodSOAP");
				postTemplate = group
						.getInstanceOf("gov/nih/nci/restgen/templates/PostMethodSOAP");
				deleteTemplate = group
						.getInstanceOf("gov/nih/nci/restgen/templates/DeleteMethodSOAP");
				putTemplate = group
						.getInstanceOf("gov/nih/nci/restgen/templates/PutMethodSOAP");
				String methodName = resource.getName() + counter;
				if (method.getName().equals(Method.GET)) {
					String getString = generateMethod(resourceName, method,
							getTemplate, methodName, defs);
					getMethodStr.add(getString);
				} else if (method.getName().equals(Method.PUT)) {
					String putString = generateMethod(resourceName, method,
							putTemplate, methodName, defs);
					putMethodStr.add(putString);
				} else if (method.getName().equals(Method.POST)) {
					String postString = generateMethod(resourceName, method,
							postTemplate, methodName, defs);
					postMethodStr.add(postString);
				} else if (method.getName().equals(Method.DELETE)) {
					String deleteString = generateMethod(resourceName, method,
							deleteTemplate, methodName, defs);
					deleteMethodStr.add(deleteString);
				}
				counter++;
			}
			resourceTemplate.setAttribute("packageName", Constants.GENERATOR_PKG_NAME);
			resourceTemplate.setAttribute("ResourcePath", resource.getPath());
			resourceTemplate.setAttribute("ResourceName", resourceName);

			for (String str : getMethodStr) {
				resourceTemplate.setAttribute("GetMethods", str);
			}

			for (String str : putMethodStr) {
				resourceTemplate.setAttribute("PutMethods", str);
			}

			for (String str : postMethodStr) {
				resourceTemplate.setAttribute("PostMethods", str);
			}

			for (String str : deleteMethodStr) {
				resourceTemplate.setAttribute("DeleteMethods", str);
			}

			
			resourceTemplate.setAttribute("importStatements", "import gov.nih.nci.restgen.generated.client.*;");
			GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
					+ getFileOutputPath(), resourceName + "Resource.java",
					resourceTemplate.toString());
		}
	}

	private void processPojo(Resource resource) throws GeneratorException {
		if (context.getMapping().getOptions().getWrapperType()
				.equals(Options.EJB)) {
			
			String libDest = context.getMapping().getOptions().getOutputPath()
					+ File.separator + "web" + File.separator + "WEB-INF";

			String sourcePath = resource.getPojoLocation();
			File jarSrcFile = new File(sourcePath);
			String jarFileName = null;
			if (sourcePath.lastIndexOf(File.separator) > 0)
				jarFileName = sourcePath.substring(
						sourcePath.lastIndexOf(File.separator) + 1,
						sourcePath.length());
			else
				jarFileName = sourcePath;

			StringTemplateGroup group = new StringTemplateGroup("restful");
			StringTemplate indexTemplate = group
					.getInstanceOf("gov/nih/nci/restgen/templates/JAXBIndex");
			
			jarFileName = jarFileName.substring(0, jarFileName.indexOf("."))
					+ ".jar";
			if (sourcePath.endsWith(".jar")) {
				File destFile = new File(libDest + File.separator + "lib"
						+ File.separator + jarFileName);
				try {
					JarFile file = new JarFile(jarSrcFile);
					String oldDir = "";
					String dirName = "";
					boolean firstIter = true;
					boolean generate = false;
					for (Enumeration<JarEntry> enums = file.entries(); enums.hasMoreElements();) {   
					    JarEntry entry = (JarEntry) enums.nextElement();
					    
					    if(entry.getName().endsWith(".class"))
					    {
					    	dirName = entry.getName().substring(0,entry.getName().lastIndexOf("/"));
					    	if(firstIter)
					    	{
					    		firstIter = false;
					    		oldDir = dirName;
					    	}
					    	String fileName = entry.getName().substring(entry.getName().lastIndexOf("/")+1, entry.getName().indexOf(".class"));
					    	generate = true;
					    	indexTemplate.setAttribute("ClassName", fileName);
					    	if(!oldDir.equals(dirName))
					    	{
					    		dirName = dirName.replace("/", File.separator);
								GeneratorUtil.writeFile(context.getMapping().getOptions().getOutputPath()
										+ getFileOutputPath() + File.separator + "web" + File.separator + "WEB-INF" +
										File.separator + "classes"+ File.separator+dirName, "jaxb.index",
										indexTemplate.toString());
								indexTemplate = group
										.getInstanceOf("gov/nih/nci/restgen/templates/JAXBIndex");	
								generate = false;
					    	}
					    }
					    
					    //System.out.println(entry.getName());   
					}
					if(generate)
					{
						dirName = dirName.replace("/", File.separator);
						
						GeneratorUtil.writeFile(context.getMapping().getOptions().getOutputPath()
								+ File.separator + "web" + File.separator + "WEB-INF" +
								File.separator + "classes"+ File.separator+dirName, "jaxb.index",
								indexTemplate.toString());
					}
					GeneratorUtil.copyFile(jarSrcFile, destFile);
				} catch (IOException e) {
					e.printStackTrace();
					throw new GeneratorException(
							"Failed to copy POJO jar into output lib folder");
				}
			} else {
				try {
					String pojoLocation = resource.getPojoLocation();
					File file = new File(pojoLocation);
					InputStream is = new FileInputStream(file);
					ClassParser cp = new ClassParser(is, file.getName());
					JavaClass javaClass = cp.parse();
					String packageName = javaClass.getPackageName();
					String dirStart = null;
					if (packageName.indexOf(".") > 0) {
						dirStart = packageName.substring(0,
								packageName.indexOf("."));
					} else
						dirStart = packageName;
					String jarSrcDir = pojoLocation.substring(
							0,
							pojoLocation.indexOf(File.separator + dirStart
									+ File.separator)
									+ (File.separator + dirStart).length());

					File jarDestFile = new File(libDest + File.separator
							+ "classes");
					File jarSrcFolder = new File(jarSrcDir);
					GeneratorUtil.copyDir(jarSrcDir, libDest + File.separator
							+ "classes");
					String dirName = packageName.replace(".", File.separator);
					String entry = javaClass.getFileName().substring(0, javaClass.getFileName().indexOf(".class"));
					indexTemplate.setAttribute("ClassName", entry);
					
					GeneratorUtil.writeFile(context.getMapping().getOptions().getOutputPath()
							+ File.separator + "web" + File.separator + "WEB-INF" +
							File.separator + "classes"+ File.separator+dirName, "jaxb.index",
							indexTemplate.toString());
					
				} catch (IOException e) {
					e.printStackTrace();
					throw new GeneratorException(
							"Failed to generate web archive file.", e);
				}
			}
		}
	}

	private void processJNDIProperties(Method method) throws GeneratorException {
		if (method.getImplementation().getClientType() != null
				&& method.getImplementation().getClientType()
						.equals(Implementation.EJB_REMOTE)) {
			String libDest = context.getMapping().getOptions().getOutputPath()
					+ File.separator + "web" + File.separator + "WEB-INF"
					+ File.separator + "lib";
			String jndiFilePath = method.getImplementation()
					.getJndiProperties();
			String jndiFileName = jndiFilePath.substring(jndiFilePath
					.lastIndexOf(File.separator) + 1);
			File destFile = new File(libDest + File.separator + jndiFileName);
			File srcFile = new File(jndiFilePath);
			try {
				GeneratorUtil.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
				throw new GeneratorException(
						"Failed to copy POJO jar into output lib folder");
			}
		}
	}

	private void runProcessEJB() throws GeneratorException {
		Mapping mapping = context.getMapping();
		List<Resource> resources = mapping.getResources();
		StringTemplateGroup group = new StringTemplateGroup("restfulejb");
		StringTemplate getTemplate = null;
		StringTemplate postTemplate = null;
		StringTemplate deleteTemplate = null;
		StringTemplate putTemplate = null;
		StringTemplate resourceTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/RESTfulResource");
		StringTemplate indexTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/JAXBIndex");

		for (Resource resource : resources) {
			String resourceName = resource.getName();
			List<Method> methods = resource.getMethods();
			List<String> getMethodStr = new ArrayList<String>();
			List<String> putMethodStr = new ArrayList<String>();
			List<String> postMethodStr = new ArrayList<String>();
			List<String> deleteMethodStr = new ArrayList<String>();
			int counter = 1;
			for (Method method : methods) {
				String methodName = resource.getName() + counter;
				if (method.getName().equals(Method.GET)) {
					if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_LOCAL))
					{
						getTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/GetMethodEJBLocal");
					}
					else if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_REMOTE))
					{
						getTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/GetMethodEJBRemote");
					}
					String getString = generateMethodEJB(resourceName, method,
							methodName, getTemplate);
					getMethodStr.add(getString);
				} else if (method.getName().equals(Method.PUT)) {
					if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_LOCAL))
					{
						putTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/PutMethodEJBLocal");
					}
					else if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_REMOTE))
					{
						putTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/PutMethodEJBRemote");
					}
					String putString = generateMethodEJB(resourceName, method,
							methodName, putTemplate);
					putMethodStr.add(putString);
				} else if (method.getName().equals(Method.POST)) {
					if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_LOCAL))
					{
						postTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/PostMethodEJBLocal");
					}
					else if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_REMOTE))
					{
						postTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/PostMethodEJBRemote");
					}
					String postString = generateMethodEJB(resourceName, method,
							methodName, postTemplate);
					postMethodStr.add(postString);
				} else if (method.getName().equals(Method.DELETE)) {
					if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_LOCAL))
					{
						deleteTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/DeleteMethodEJBLocal");
					}
					else if (method.getImplementation().getClientType()
							.equals(Implementation.EJB_REMOTE))
					{
						deleteTemplate = group
								.getInstanceOf("gov/nih/nci/restgen/templates/DeleteMethodEJBRemote");
					}
					String deleteString = generateMethodEJB(resourceName,
							method, methodName, deleteTemplate);
					deleteMethodStr.add(deleteString);
				}
				processJNDIProperties(method);
				processPojo(resource);
				processEJBJar(method);
				counter++;
			}
			resourceTemplate.setAttribute("packageName", Constants.GENERATOR_PKG_NAME);
			resourceTemplate.setAttribute("ResourcePath", resource.getPath());
			resourceTemplate.setAttribute("ResourceName", resourceName);

			for (String str : getMethodStr) {
				resourceTemplate.setAttribute("GetMethods", str);
			}

			for (String str : putMethodStr) {
				resourceTemplate.setAttribute("PutMethods", str);
			}

			for (String str : postMethodStr) {
				resourceTemplate.setAttribute("PostMethods", str);
			}

			for (String str : deleteMethodStr) {
				resourceTemplate.setAttribute("DeleteMethods", str);
			}

			GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
					+ getFileOutputPath(), resourceName + "Resource.java",
					resourceTemplate.toString());
		}
	}

	private void processEJBJar(Method method) throws GeneratorException
	{
		if (context.getMapping().getOptions().getWrapperType()
				.equals(Options.EJB)) {
			String ejbLoc = context.getMapping().getOptions().getEjbLocation();
			File src = new File(ejbLoc);
			String ejbFileName = ejbLoc.substring(ejbLoc
					.lastIndexOf(File.separator) + 1);
			String jarDest = context.getMapping().getOptions().getOutputPath()
					+ File.separator + "web" + File.separator + "WEB-INF"
					+ File.separator + "lib" + File.separator + ejbFileName;
			File dest = new File(jarDest);
			try {
				GeneratorUtil.copyFile(src, dest);
				String outputPath = context.getMapping().getOptions().getOutputPath()
						+ File.separator + "web" + File.separator + "temp";
				if(method.getImplementation().getClientType().equals(Implementation.EJB_REMOTE))
				{
					String deletePathName = outputPath + File.separator + "META-INF"; 
					new JarHelper().removeJarEntry(jarDest, deletePathName, outputPath);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new GeneratorException(
						"Failed to copy EJB jar to output lib folder");
			}
		}
		
	}
	
	private String generateMethodEJB(String resourceName, Method method,
			String methodName, StringTemplate template)
			throws GeneratorException {
		Implementation impl = method.getImplementation();

		template.setAttribute("MethodName", methodName);
		template.setAttribute("ResourceName", resourceName);

		JarFile jarFile = null;
		org.jdom2.Document doc = null;
		try {
			jarFile = new JarFile(context.getMapping().getOptions().getEjbLocation());
			JarEntry jarEntry = jarFile.getJarEntry("META-INF/ejb-jar.xml");
			if (jarEntry != null) {
				InputStream is = jarFile.getInputStream(jarEntry);
				SAXBuilder sax = new SAXBuilder();
				doc = sax.build(is);

			} else
				throw new GeneratorException(
						"Invalid EJB JAR path. Unable to load ejb-jar.xml");
		} catch (IOException e) {
			throw new GeneratorException("Failed to load EJB JAR. ", e);
		} catch (JDOMException e) {
			throw new GeneratorException("Failed to load EJB JAR ejb-jar.xml ",
					e);
		}

		org.jdom2.Element root = doc.getRootElement();
		org.jdom2.Element eBeans = root.getChild("enterprise-beans");
		List<org.jdom2.Element> sessionBeans = eBeans.getChildren("session");
		String ejbHomeName = null;
		String ejbRemoteName = null;

		boolean foundService = false;
		for (org.jdom2.Element sessionBean : sessionBeans) {
			org.jdom2.Element ejbName = sessionBean.getChild("ejb-name");

			if (ejbName.getValue().equals(impl.getName())) {
				foundService = true;
				org.jdom2.Element ejbHome = sessionBean.getChild("home");
				ejbHomeName = ejbHome.getValue();
				org.jdom2.Element ejbRemote = sessionBean.getChild("remote");
				ejbRemoteName = ejbRemote.getValue();
				break;
			}
		}

		if (!foundService)
			throw new GeneratorException(
					"Unable to find EJB from ejb-jar.xml for " + impl.getName());

		String returnType = getOperationReturnType(impl);
		if(!returnType.equals("void"))
		{
			template.setAttribute("ReturnTypeNotVoid", true);
			template.setAttribute("PostReturnType", returnType);
			template.setAttribute("PutReturnType", returnType);
			template.setAttribute("DeleteReturnType", returnType);
		}
		else
		{
			template.setAttribute("ReturnTypeResponse", true);
			template.setAttribute("PostReturnType", "Response");
			template.setAttribute("PutReturnType", "Response");
			template.setAttribute("DeleteReturnType", "Response");
			
		}

		template.setAttribute("ReturnType", returnType);
		template.setAttribute("PathParamPath",
				getOperationPath(impl, method.getName()));
		template.setAttribute("PathParam", getOperationPathParams(method, impl));
		template.setAttribute("HomeInterface", ejbHomeName);
		template.setAttribute("RemoteInterface", ejbRemoteName);
		template.setAttribute("OperationName", impl.getOperation().getName());
		String operationParams = getOperationParams(impl);
		//if (operationParams != null)
		//	operationParams = operationParams + ", ";
		template.setAttribute("OperationParameters", operationParams);
		template.setAttribute("RequestType", getRequestTypes(impl));
		template.setAttribute("OperationParamNames", getOperationParams(impl));

		if (impl.getClientType().equals(Implementation.EJB_REMOTE)) {
			String jndiPath = impl.getJndiProperties();
			String fileName = jndiPath.substring(jndiPath
					.lastIndexOf(File.separator) + 1);
			template.setAttribute("JNDIProperties", fileName);
		}
		template.setAttribute("JNDIName", impl.getJndiName());
		return template.toString();
	}

	private String getOperationReturnType(Implementation impl) {
		Operation operation = impl.getOperation();
		Output output = operation.getOutput();
		if (output == null)
			return "void";
		else
			return output.getType();
	}

	private String getOperationParams(Implementation impl) {
		Operation operation = impl.getOperation();
		List<Input> inputs = operation.getInputs();
		if (inputs == null || inputs.size() == 0)
			return null;

		StringBuffer buffer = new StringBuffer();
		Iterator<Input> iterator = inputs.iterator();
		while (iterator.hasNext()) {
			Input input = (Input) iterator.next();
			buffer.append(input.getName());
			if (iterator.hasNext())
				buffer.append(", ");
		}

		return buffer.toString();
	}

	private String getRequestTypes(Implementation impl) {
		Operation operation = impl.getOperation();
		List<Input> inputs = operation.getInputs();
		if (inputs == null || inputs.size() == 0)
			return null;

		StringBuffer buffer = new StringBuffer();
		Iterator<Input> iterator = inputs.iterator();
		while (iterator.hasNext()) {
			Input input = (Input) iterator.next();
			buffer.append(input.getType() + " " + input.getName());
			if (iterator.hasNext())
				buffer.append(", ");
		}

		return buffer.toString();
	}

	private String getOperationPathParams(Method method, Implementation impl) {
		Operation operation = impl.getOperation();
		List<Input> inputs = operation.getInputs();

		if (inputs == null || inputs.size() == 0)
			return null;

		StringBuffer buffer = new StringBuffer();
		Iterator<Input> iterator = inputs.iterator();
		boolean append = false;
		while (iterator.hasNext()) {
			Input input = (Input) iterator.next();
			buffer.append("@PathParam(\"" + input.getName() + "\") ");
			buffer.append(input.getType() + " " + input.getName());
			append = true;
			if (iterator.hasNext())
				buffer.append(", ");
		}
		if(!method.getName().equals(Method.GET))
			append = false;
		if(append)
			return buffer.toString() + ",";
		else
			return buffer.toString();
	}

	private String getOperationPath(Implementation impl, String methodName) {
		if (methodName.equals(Method.POST) || methodName.equals(Method.PUT)) {
			if (impl.getPath() != null && impl.getPath().trim().length() > 0)
				return impl.getPath();
		}
		Operation operation = impl.getOperation();
		List<Input> inputs = operation.getInputs();
		String path = null;

		if (impl.getPath() != null && impl.getPath().trim().length() > 0)
			path = "/" + impl.getPath();
		
		//if (methodPath != null && methodPath.trim().length() > 0)
		//	path = "/" + methodPath;

		if (inputs == null || inputs.size() == 0)
			return path;

		StringBuffer buffer = new StringBuffer();
		Iterator<Input> iterator = inputs.iterator();
		while (iterator.hasNext()) {
			Input input = (Input) iterator.next();
			buffer.append("{" + input.getName() + "}");
			if (iterator.hasNext())
				buffer.append("/");
		}

		if (path != null)
			return path + "/" + buffer.toString();
		else
			return buffer.toString();
	}

	private String generateMethod(String resourceName, Method method,
			StringTemplate template, String methodName, Definitions defs)
			throws GeneratorException {
		template.setAttribute("MethodName", methodName);
		template.setAttribute("ResourceName", resourceName);

		Implementation impl = method.getImplementation();

		boolean operationValid = false;
		for (PortType pt : defs.getPortTypes()) {
			for (com.predic8.wsdl.Operation op : pt.getOperations()) {
				if (impl.getOperation().getName().equals(op.getName())) {
					operationValid = true;
					List<Fault> faults = op.getFaults();
					for (Fault fault : faults) {
						StringBuffer buffer = new StringBuffer();
						String faultName = fault.getName();
						if(!fault.getName().endsWith("Exception"))
							faultName = fault.getName() + "_Exception";
						
						buffer.append("catch(gov.nih.nci.restgen.generated.client."
								+ faultName + " e)\n");
						buffer.append("{\n");
						buffer.append("\t e.printStackTrace();\n");
						buffer.append("\t throw new WebApplicationException(buildResponseError(\"GET_METHOD\", \""
								+ resourceName
								+ "\", \""
								+ methodName
								+ "\", \"Failed to process GET method\"+ e.toString()).build());\n");
						buffer.append("}");
						template.setAttribute("ResourceException",
								buffer.toString());

					}
				}
			}
		}

		if (!operationValid) {
			throw new GeneratorException(
					"Operation name mapping is missing for "
							+ impl.getOperation().getName()
							+ ". Aborting the process!! ");
		}

		List<Service> services = defs.getServices();
		String portType = null;
		String portName = null;
		for (Service service : services) {
			List<Port> ports = service.getPorts();

			for (Port port : ports) {
				if (port.getName().equals(impl.getPortName())) {
					Binding binding = port.getBinding();
					portType = binding.getType().getLocalPart();
					portName = port.getName();
					template.setAttribute("PortType", portType);
					template.setAttribute("PortName", portName.substring(0, 1)
							.toUpperCase() + portName.substring(1));
					List<String[]> params = getMethodParameters(portType, impl
							.getOperation().getName());
					String[] paramTypes = params.get(0);
					String[] paramTypeNames = params.get(1);
					String returnType = getMethodReturnType(portType, impl
							.getOperation().getName(), defs);
					StringBuffer buffer = new StringBuffer();
					for (int i = 0; i < paramTypes.length; i++) {
						buffer.append(paramTypes[i] + " " +paramTypeNames[i]);
						if (i < paramTypes.length - 1)
							buffer.append(", ");
					}
					template.setAttribute("RequestType", buffer.toString());

					buffer = new StringBuffer();
					for (int i = 0; i < paramTypeNames.length; i++) {
						buffer.append("\"" + paramTypeNames[i] + "\"");
						if (i < paramTypeNames.length - 1)
							buffer.append(", ");
					}

					String pathParam = constructPathParam(paramTypeNames,
							paramTypes, method.getName());
					String pathParamPath = constructPathParamPath(impl,
							paramTypeNames, paramTypes, method.getName());
					String opParams = constructOperationParams(paramTypeNames);

					if(!returnType.equals("void"))
					{
						template.setAttribute("ReturnTypeNotVoid", true);
						template.setAttribute("PostReturnType", returnType);
						template.setAttribute("PutReturnType", returnType);
						template.setAttribute("DeleteReturnType", returnType);
					}
					else
					{
						template.setAttribute("ReturnTypeResponse", true);
						template.setAttribute("PostReturnType", "Response");
						template.setAttribute("PutReturnType", "Response");
						template.setAttribute("DeleteReturnType", "Response");
					}
					template.setAttribute("ReturnType", returnType);
					template.setAttribute("OperationParameters", opParams);
					template.setAttribute("PathParamPath", pathParamPath);
					template.setAttribute("PathParam", pathParam);
					template.setAttribute("ParamNames", buffer.toString());
				}
			}
		}

		if (portType == null || portType.trim().length() == 0)
			throw new GeneratorException(
					"Port type information is missing. Please check the WSDL!");

		template.setAttribute("ServiceName", impl.getName());
		template.setAttribute("OperationName", impl.getOperation().getName());
		template.setAttribute("ServiceNamespace", defs.getNamespaceContext().toString());
		template.setAttribute("WSDLURL", context.getMapping().getOptions().getWsdlLocation());

		return template.toString();
	}

	private String constructPathParam(String[] paramNames, String[] paramTypes, String methodName)
			throws GeneratorException {
		if ((paramNames.length != paramTypes.length))
			throw new GeneratorException("Failed to parse path paramters.");
		StringBuffer buffer = new StringBuffer();
		boolean found = false;
		for (int i = 0; i < paramNames.length; i++) {
			buffer.append("@PathParam(\"" + paramNames[i] + "\") "
					+ paramTypes[i] + " " + paramNames[i]);
			found = true;
			if (i < paramNames.length - 1)
				buffer.append(", ");
		}
		if(found && methodName.equals(Method.GET))
			return buffer.toString()+",";
		else
			return buffer.toString();
	}

	private String constructPathParamPath(Implementation impl, String[] paramNames,
			String[] paramTypes, String methodName) throws GeneratorException {

		if (methodName.equals(Method.POST) || methodName.equals(Method.PUT)) {
			if (impl.getPath() != null && impl.getPath().trim().length() > 0)
				return impl.getPath();
		}
		
		StringBuffer buffer = new StringBuffer();
		if(impl.getPath() != null && impl.getPath().trim().length() > 0)
			buffer.append("/"+impl.getPath());
		
		if ((paramNames.length != paramTypes.length))
			throw new GeneratorException("Failed to parse path paramters.");
		
		for (int i = 0; i < paramNames.length; i++) {
			buffer.append("/{" + paramNames[i] + "}");
		}
		return buffer.toString();
	}

	private String constructOperationParams(String[] paramNames)
			throws GeneratorException {
		if (paramNames == null || paramNames.length == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < paramNames.length; i++) {
			buffer.append(paramNames[i]);
			if (i < paramNames.length - 1)
				buffer.append(",");
		}

		return buffer.toString();
	}

	private List<String[]> getMethodParameters(String portName,
			String operationName) throws GeneratorException {
		File file = new File(context.getMapping().getOptions().getOutputPath()
				+ File.separator);
		List<String[]> returnType = new ArrayList<String[]>();
		try {
			// Convert File to a URL
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };

			// Create a new class loader with the directory
			ClassLoader cl = new URLClassLoader(urls);

			Class klass = cl.loadClass("gov.nih.nci.restgen.generated.client."
					+ portName);
			java.lang.reflect.Method[] methods = klass.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				java.lang.reflect.Method cMethod = methods[i];
				if (cMethod.getName().equals(operationName)) {
					Annotation[][] annotations = cMethod
							.getParameterAnnotations();
					Class[] params = cMethod.getParameterTypes();
					String[] strParams = new String[params.length];
					String[] strParamNames = new String[params.length];
					for (int x = 0; x < params.length; x++) {
						strParams[x] = params[x].getName();
						for (int j = 0; j < annotations.length; j++) {
							if (annotations[x][j].annotationType().getName()
									.equals("javax.jws.WebParam")) {
								strParamNames[x] = (String) AnnotationUtils
										.getValue(annotations[x][j], "name");
								break;
							}
						}
					}
					returnType.add(strParams);
					returnType.add(strParamNames);

					return returnType;
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to load compiled client classes", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to load compiled client classes", e);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to load compiled client classes", e);
		}
		return null;
	}

	private String getMethodReturnType(String portName, String operationName,
			Definitions defs) throws GeneratorException {
		File file = new File(context.getMapping().getOptions().getOutputPath()
				+ File.separator);

		try {
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };

			ClassLoader cl = new URLClassLoader(urls);

			Class klass = cl.loadClass("gov.nih.nci.restgen.generated.client."
					+ portName);
			java.lang.reflect.Method[] methods = klass.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				java.lang.reflect.Method cMethod = methods[i];
				if (cMethod.getName().equals(operationName)) {
					String rType = cMethod.getReturnType().getName();
					if (rType.startsWith("java.util.")) {
						String genericType = getOperationReturnTypeFromWSDL(
								portName, operationName, defs);
						return rType + "<" + genericType + ">";
					} else
						return rType;

				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to load compiled client classes", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to load compiled client classes", e);
		}
		return null;
	}

	private String getOperationReturnTypeFromWSDL(String portName,
			String operationName, Definitions defs) throws GeneratorException {
		String returnType = null;
		for (PortType pt : defs.getPortTypes()) {
			for (com.predic8.wsdl.Operation op : pt.getOperations()) {
				if (operationName.equals(op.getName())) {
					com.predic8.wsdl.Output output = op.getOutput();
					List<Schema> schemas = defs.getSchemas();
					String outputName = output.getName();
					if(outputName == null)
						outputName = output.getMessage().getName();
					for (Schema schema : schemas) {
						//if(schema.getNamespaceContext() != null)
						//	continue;
						List<ComplexType> types = schema.getComplexTypes();
						ComplexType complex = null;
						if(types.size() != 0)
						{
						  complex = schema.getComplexType(outputName);
						}
						
						if(complex == null)
						{
							try
							{
								Element schemaElement = schema.getElement(outputName);
								complex = (ComplexType)schemaElement.getEmbeddedType();
							}
							catch(Exception e)
							{
								continue;
							}
						}
						if(complex == null)
							throw new GeneratorException("Failed to parse complex type for "+output.getName());
						List<Element> elements = complex.getSequence()
								.getElements();
						Element element = elements.get(0);
						String qName = element.getType().getQualifiedName();
						if (qName.contains(":"))
							qName = qName.substring(qName.indexOf(":") + 1);

						String uqName = qName.substring(0, 1).toUpperCase()
								+ qName.substring(1);
						if (schema.getComplexType(qName) != null)
							return "gov.nih.nci.restgen.generated.client."
									+ uqName;
						else
							return uqName;
					}
				}
			}
		}
		return returnType;
	}

	protected void postProcess() {
		getContext().getLogger().info("Generating RESTful resource...Completed!");		
	}

}