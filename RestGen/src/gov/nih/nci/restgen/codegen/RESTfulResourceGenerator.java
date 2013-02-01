package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.springframework.core.annotation.AnnotationUtils;

import com.predic8.schema.ComplexType;
import com.predic8.schema.Element;
import com.predic8.schema.Schema;
import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Fault;
import com.predic8.wsdl.Input;
import com.predic8.wsdl.Message;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Output;
import com.predic8.wsdl.Part;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;

public class RESTfulResourceGenerator extends Generator {
	public RESTfulResourceGenerator(GeneratorContext context) {
		super(context);
	}

	protected void init() {
		getContext().getLogger().info("Generating RESTful resource...");
	}

	protected void preProcess() {
	}

	protected void validate() {
	}

	public void runProcess() throws GeneratorException{
		Mapping mapping = context.getMapping();
		List<Resource> resources = mapping.getResources();
		String packageName = "gov.nih.nci.restgen.generated.resource";
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate getCollTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/GetMethodCollection");
		StringTemplate getTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/GetMethodSingle");
		StringTemplate postTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/PostMethod");
		StringTemplate deleteTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/DeleteMethod");
		StringTemplate putTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/putMethod");
		WSDLParser parser = new WSDLParser();

		Definitions defs = parser.parse(context.getMapping().getOptions()
				.getWsdlLocation());

		for (Resource resource : resources) {
			StringTemplate resourceTemplate = group
					.getInstanceOf("gov/nih/nci/restgen/templates/RESTfulResource");
			String resourceName = resource.getName();
			String resourcePath = resource.getPath();
			List<Method> methods = resource.getMethods();
			List<String> getMethodStr = new ArrayList<String>();
			List<String> putMethodStr = new ArrayList<String>();
			List<String> postMethodStr = new ArrayList<String>();
			List<String> deleteMethodStr = new ArrayList<String>();
			int counter = 1;
			for (Method method : methods) {
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
			resourceTemplate.setAttribute("packageName", packageName);
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

	private String generateMethod(String resourceName, Method method,
			StringTemplate template, String methodName, Definitions defs) throws GeneratorException {
		template.setAttribute("MethodName", methodName);
		template.setAttribute("ResourceName", resourceName);

		Implementation impl = method.getImplementation();

		Operation operation = null;
		boolean operationValid = false;
		for (PortType pt : defs.getPortTypes()) {
			System.out.println("Port name: " + pt.getName());
			for (Operation op : pt.getOperations()) {
				if (impl.getOperation().getName().equals(op.getName())) {
					operationValid = true;
					System.out.println(" -name " + op.getName());
					List<Fault> faults = op.getFaults();
					for(Fault fault : faults)
					{
						StringBuffer buffer = new StringBuffer();
						buffer.append("catch(gov.nih.nci.restgen.generated.client."+fault.getName()+" e)\n");
						buffer.append("{\n");
						buffer.append("\t e.printStackTrace();\n");
						buffer.append("\t throw new WebApplicationException(buildResponseError(\"GET_METHOD\", \"" +resourceName+ "\", \""+methodName+ "\", \"Failed to process GET method\"+ e.toString()).build());\n");
						buffer.append("}");	
						template.setAttribute("ResourceException", buffer.toString());

					}
				}
			}
		}

		if(!operationValid)
		{
			throw new GeneratorException("Operation name mapping is missing for "+ impl.getOperation().getName() +". Aborting the process!! ");
		}
		
		List<Service> services = defs.getServices();
		String portType = null;
		String portName = null;
		for (Service service : services) {
			System.out.println("service: " + service.getName());
			List<Port> ports = service.getPorts();

			for (Port port : ports) {
				System.out.println("port: " + port.getName());
				if (port.getName().equals(impl.getPortName())) {
					Binding binding = port.getBinding();
					System.out.println("binding.getType().getLocalPart(): "
							+ binding.getType().getLocalPart());
					System.out.println("binding.getType().getLocalPart(): "
							+ binding.getType().getPrefix());
					portType = binding.getType().getLocalPart();
					portName = port.getName();
					template.setAttribute("PortType", portType);
					template.setAttribute("PortName", portName.substring(0,1).toUpperCase()+portName.substring(1));
					List<String[]> params = getMethodParameters(portType, impl.getOperation().getName());
					String[] paramTypes = params.get(0);
					String returnType = getMethodReturnType(portType, impl.getOperation().getName(), defs);
					StringBuffer buffer = new StringBuffer();
					for(int i=0;i<paramTypes.length;i++)
					{
						buffer.append(paramTypes[i] + " param"+i);
						if(i<paramTypes.length-1)
							buffer.append(", ");
					}
					template.setAttribute("RequestType", buffer.toString());
					
					String[] paramTypeNames = params.get(1);
					buffer = new StringBuffer();
					for(int i=0;i<paramTypeNames.length;i++)
					{
						buffer.append("\""+paramTypeNames[i]+"\"");
						if(i<paramTypeNames.length-1)
							buffer.append(", ");
					}

					String pathParam = constructPathParam(paramTypeNames, paramTypes);
					String pathParamPath = constructPathParamPath(paramTypeNames, paramTypes);
					String opParams = constructOperationParams(paramTypeNames);
					
					template.setAttribute("OperationParameters", opParams);
					template.setAttribute("PathParamPath", pathParamPath);
					template.setAttribute("PathParam", pathParam);
					
					template.setAttribute("ParamNames", buffer.toString());
					template.setAttribute("ReturnType", returnType);

				}
			}
		}
		
		if(portType == null || portType.trim().length() == 0)
			throw new GeneratorException("Port type information is missing. Please check the WSDL!");
		
		//gov.nih.nci.restgen.mapping.model.Operation operation = impl.getOperation();
		template.setAttribute("ServiceName", impl.getName());
		template.setAttribute("OperationName", impl.getOperation()
				.getName());


		return template.toString();
	}

	private String constructPathParam(String[] paramNames, String[] paramTypes) throws GeneratorException
	{
		if((paramNames.length != paramTypes.length) || paramNames.length == 0)
			throw new GeneratorException("Failed to parse path paramters.");
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i< paramNames.length; i++)
		{
			buffer.append("@PathParam(\""+ paramNames[i] +"\") "+paramTypes[i] + " " +paramNames[i]);
			if(i< paramNames.length-1)
				buffer.append(", ");
		}
		return buffer.toString();
	}
	
	private String constructPathParamPath(String[] paramNames, String[] paramTypes) throws GeneratorException
	{
		if((paramNames.length != paramTypes.length) || paramNames.length == 0)
			throw new GeneratorException("Failed to parse path paramters.");
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i< paramNames.length; i++)
		{
			buffer.append("/{"+ paramNames[i] +"}");
		}
		return buffer.toString();
	}

	private String constructOperationParams(String[] paramNames) throws GeneratorException
	{
		if(paramNames == null || paramNames.length == 0)
			throw new GeneratorException("Failed to parse path paramters.");
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i< paramNames.length; i++)
		{
			buffer.append(paramNames[i]);
			if(i<paramNames.length-1)
				buffer.append(",");
		}
		
		return buffer.toString();
	}
	
	
	private String generatePutMethod(String resourceName, Method method) {
		String getStr = "";

		return getStr;
	}

	private String generatePostMethod(String resourceName, Method method) {
		String getStr = "";

		return getStr;
	}

	private String generateDeleteMethod(String resourceName, Method method) {
		String getStr = "";

		return getStr;
	}

	private List<String[]> getMethodParameters(String portName, String operationName)
	{
		File file = new File(context.getMapping().getOptions().getOutputPath()+File.separator);
		List<String[]> returnType = new ArrayList<String[]>();
		try {
		    // Convert File to a URL
		    @SuppressWarnings("deprecation")
			URL url = file.toURI().toURL();          // file:/c:/myclasses/
		    URL[] urls = new URL[]{url};

		    // Create a new class loader with the directory
		    ClassLoader cl = new URLClassLoader(urls);

		    // Load in the class; MyClass.class should be located in
		    // the directory file:/c:/myclasses/com/mycompany
		    Class klass = cl.loadClass("gov.nih.nci.restgen.generated.client."+portName);
		    System.out.println("Klass: "+klass.getName());
		    java.lang.reflect.Method[] methods = klass.getDeclaredMethods();
		    for(int i =0;i<methods.length;i++)
		    {
		    	java.lang.reflect.Method cMethod = methods[i];
		    	System.out.println("cMethod: "+cMethod.getName());
		    	System.out.println("operationName: "+operationName);
		    	if(cMethod.getName().equals(operationName))
		    	{
		    		Annotation[][] annotations = cMethod.getParameterAnnotations();
    				Class[] params = cMethod.getParameterTypes();
    				String[] strParams = new String[params.length];
    				String[] strParamNames = new String[params.length];
    				for(int x=0;x<params.length;x++)
    				{
    					strParams[x] = params[x].getName();
    		    		for(int j=0;j<annotations.length;j++)
    		    		{
    		    			System.out.println(annotations[x][j].annotationType().getName());
    		    			if(annotations[x][j].annotationType().getName().equals("javax.jws.WebParam"))
    		    			{
    		    				strParamNames[x] = (String)AnnotationUtils.getValue(annotations[x][j], "name");
    		    				
    		    				//Field field = annotations[x][j].annotationType().getDeclaredField("name");
    		    				System.out.println(AnnotationUtils.getValue(annotations[x][j], "name"));
    		    				System.out.println(AnnotationUtils.getValue(annotations[x][j], "targetNamespace"));
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
			//throw new GeneratorException("Failed to load compiled client classes", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new GeneratorException("Failed to load compiled client classes", e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	private String getMethodReturnType(String portName, String operationName, Definitions defs) throws GeneratorException
	{
		File file = new File(context.getMapping().getOptions().getOutputPath()+File.separator);

		try {
		    // Convert File to a URL
		    @SuppressWarnings("deprecation")
			URL url = file.toURI().toURL();         
		    URL[] urls = new URL[]{url};

		    ClassLoader cl = new URLClassLoader(urls);

		    Class klass = cl.loadClass("gov.nih.nci.restgen.generated.client."+portName);
		    System.out.println("Klass: "+klass.getName());
		    java.lang.reflect.Method[] methods = klass.getDeclaredMethods();
		    for(int i =0;i<methods.length;i++)
		    {
		    	java.lang.reflect.Method cMethod = methods[i];
		    	System.out.println("cMethod: "+cMethod.getName());
		    	System.out.println("operationName: "+operationName);
		    	if(cMethod.getName().equals(operationName))
		    	{
    				String rType = cMethod.getReturnType().getName();
    				//Collection, get generic type
    				if(rType.startsWith("java.util."))
    				{
    					String genericType = getOperationReturnTypeFromWSDL(portName, operationName, defs);
    					return rType+"<"+genericType+">";
    				}
    				else 
    					return rType;

		    	}
		    }
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new GeneratorException("Failed to load compiled client classes", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new GeneratorException("Failed to load compiled client classes", e);
		}		
		return null;
	}
	
	private String getOperationReturnTypeFromWSDL(String portName, String operationName, Definitions defs)
	{
		String returnType = null;
		for (PortType pt : defs.getPortTypes()) {
			System.out.println("Port name: " + pt.getName());
			for (Operation op : pt.getOperations()) {
				if (operationName.equals(op.getName())) {
					System.out.println(" -name " + op.getName());
					Output output = op.getOutput();
					Input input = op.getInput();
					System.out.println(" -output " + output.getName());
					List<Schema> schemas = defs.getSchemas();
					Message message = defs.getMessage(output.getName());
					
					List<Part> parts = message.getParts();
					for (Schema schema : schemas) {
						System.out.println("Schema: " + schema.toString());
						schema.getElement(output.getName());
						ComplexType complex = schema.getComplexType(output.getName());
						List<Element> elements = complex.getSequence().getElements();
						Element element = elements.get(0);
						String qName = element.getType().getQualifiedName();
						if(qName.contains(":"))
							qName = qName.substring(qName.indexOf(":")+1);
						
						String uqName = qName.substring(0,1).toUpperCase()+qName.substring(1);
						if(schema.getComplexType(qName) != null)
							return "gov.nih.nci.restgen.generated.client."+uqName;
						else
							return uqName;
					}
				}
			}
		}
		return returnType;
	}
	
	protected void postProcess() {
		// TODO Auto-generated method stub
	}
	
	

	
}