package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.JAXBUnmarshaller;
import gov.nih.nci.restgen.mapping.MappingGenerator;
import gov.nih.nci.restgen.mapping.XMLUtilityException;
import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Operation;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;
import gov.nih.nci.restgen.util.JarHelper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;

public class RESTfulWrapperGenerator extends Generator {

	public RESTfulWrapperGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	protected void init() throws GeneratorException {
		if (context.getMappingXMLPath() != null) {
			Mapping mapping = MappingGenerator.fromXML(context
					.getMappingXMLPath());
			context.setMapping(mapping);
		}
	}

	@Override
	protected void preProcess() throws GeneratorException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validate() throws GeneratorException {
		Mapping mapping = context.getMapping();
		if (mapping == null) {
			context.getLogger().error(
					"Mapping is missing. Aborting the process!");
			throw new GeneratorException(
					"Mapping is missing. Aborting the process!");
		}

		Options options = mapping.getOptions();

		if (options.getOutputPath() == null
				|| options.getOutputPath().trim().length() == 0) {
			context.getLogger()
					.error("No output path or Invalid output path. Aborting the process!");
			throw new GeneratorException(
					"No output path or Invalid output path. Aborting the process!");
		}

		if (options.getWrapperType() == null
				|| options.getWrapperType().trim().length() == 0) {
			context.getLogger()
					.error("No Wrapper type or Invalid Wrapper type. Aborting the process!");
			throw new GeneratorException(
					"No Wrapper type or Invalid Wrapper type. Aborting the process!");
		}

		if (!options.getWrapperType().equals(Options.EJB)
				&& !options.getWrapperType().equals(Options.SOAP_SERVICE)) {
			context.getLogger()
					.error("Invalid Wrapper type. Valid values are EJB or SOAP_SERVICE. Aborting the process!");
			throw new GeneratorException(
					"Invalid Wrapper type. Valid values are EJB or SOAP_SERVICE. Aborting the process!");
		}

		List<Resource> resources = mapping.getResources();
		if (resources == null || resources.size() == 0) {
			context.getLogger()
					.error("No resources available to generate. Aborting the process!");
			throw new GeneratorException(
					"No resources available to generate. Aborting the process!");
		}

		for (Resource resource : resources) {
			List<Method> methods = resource.getMethods();
			if (methods == null || methods.size() == 0) {
				context.getLogger().warn(
						"No methods available on resource: "
								+ resource.getName()
								+ ". Aborting the process!");
				throw new GeneratorException(
						"No methods available on resource: "
								+ resource.getName()
								+ ". Aborting the process!");
			}
			if (resource.getName() == null
					|| resource.getName().trim().length() == 0) {
				context.getLogger()
						.error("No resource name or Invalid resource name. Aborting the process!");
				throw new GeneratorException(
						"No resource name or Invalid resource name. Aborting the process!");
			}

			if (resource.getPath() == null
					|| resource.getPath().trim().length() == 0) {
				context.getLogger()
						.error("No resource path or Invalid resource path. Aborting the process!");
				throw new GeneratorException(
						"No resource path or Invalid resource path. Aborting the process!");
			}

			for (Method method : methods) {
				if (method.getName() == null
						|| method.getName().trim().length() == 0) {
					context.getLogger().error(
							"No method name or Invalid method name. "
									+ method.getName()
									+ "Aborting the process!");
					throw new GeneratorException(
							"No method name or Invalid method name. "
									+ method.getName()
									+ "Aborting the process!");
				}

				if (method.getName() != null
						&& (!(method.getName().equals(Method.POST)
								|| method.getName().equals(Method.GET)
								|| method.getName().equals(Method.DELETE) || method
								.getName().equals(Method.PUT)))) {
					context.getLogger().error(
							"Invalid method name. " + method.getName()
									+ "Aborting the process!");
					throw new GeneratorException("Invalid method name. "
							+ method.getName() + "Aborting the process!");
				}

				if (method.getImplementation() == null) {
					context.getLogger().error(
							"Missing method impelementation for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation for "
									+ method.getName()
									+ ". Aborting the process!");
				}
				Implementation impl = method.getImplementation();

				if (impl.getName() == null
						|| impl.getName().trim().length() == 0) {
					context.getLogger().error(
							"Missing method impelementation name for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation name for "
									+ method.getName()
									+ ". Aborting the process!");
				}

				if (impl.getType().equals(Options.SOAP_SERVICE)
						&& (impl.getPortName() == null || impl.getPortName()
								.trim().length() == 0)) {
					context.getLogger().error(
							"Missing method impelementation port name for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation port name for "
									+ method.getName()
									+ ". Aborting the process!");
				}

				if (impl.getType() == null
						|| impl.getType().trim().length() == 0) {
					context.getLogger().error(
							"Missing method impelementation type for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation type for "
									+ method.getName()
									+ ". Aborting the process!");
				}

				if (impl.getType().equals(Options.EJB)
						&& (impl.getClientType()
								.equals(Implementation.EJB_REMOTE))) {
					if (impl.getJndiName() == null
							|| impl.getJndiName().trim().length() == 0) {
						context.getLogger().error(
								"Missing EJB JNDI name for " + impl.getName()
										+ ". Aborting the process!");
						throw new GeneratorException(
								"Missing EJB JNDI name for " + impl.getName()
										+ ". Aborting the process!");
					}
					if (impl.getJndiProperties() == null
							|| impl.getJndiProperties().trim().length() == 0) {
						context.getLogger().error(
								"Missing EJB JNDI properties file for "
										+ impl.getName()
										+ ". Aborting the process!");
						throw new GeneratorException(
								"Missing EJB JNDI properties file for "
										+ impl.getName()
										+ ". Aborting the process!");
					}
				}

				if (impl.getOperation() == null) {
					context.getLogger().error(
							"Missing method impelementation operation for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation operation for "
									+ method.getName()
									+ ". Aborting the process!");
				}
				Operation operation = impl.getOperation();
				if (operation.getName() == null
						|| operation.getName().trim().length() == 0) {
					context.getLogger().error(
							"Missing method impelementation operation name for "
									+ method.getName()
									+ ". Aborting the process!");
					throw new GeneratorException(
							"Missing method impelementation operation name for "
									+ method.getName()
									+ ". Aborting the process!");
				}

			}

		}

	}

	@Override
	public void runProcess() throws GeneratorException {

		Options options = context.getMapping().getOptions();
		if (options.getWrapperType().equals(Options.SOAP_SERVICE)) {
			// Generate SOAP web service client
			new WebserviceClientGenerator(context).generate();
		}

		String libSrc = context.getMapping().getOptions().getRootPath()
				+ File.separator + "lib";
		String libDest = context.getMapping().getOptions().getOutputPath()
				+ File.separator + "web" + File.separator + "WEB-INF";
		String srcFolder = context.getMapping().getOptions().getOutputPath();
		String classFolder = context.getMapping().getOptions().getOutputPath()
				+ File.separator + "web" + File.separator + "WEB-INF"
				+ File.separator + "classes";

		try {
			GeneratorUtil.copyDir(libSrc, libDest);
		} catch (IOException e) {
			throw new GeneratorException("Failed to copy lib folder contents",
					e);
		}

		// Generate RESTful resources
		new RESTfulResourceGenerator(context).generate();

		// Generate RESTful Web application resources
		new RESTfulWebResourceGenerator(context).generate();
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
				String deletePathName = outputPath + File.separator + "META-INF"; 
				new JarHelper().removeJarEntry(jarDest, deletePathName, outputPath);
			} catch (IOException e) {
				e.printStackTrace();
				throw new GeneratorException(
						"Failed to copy EJB jar to output lib folder");
			}
		}
		boolean compileFlag = GeneratorUtil.compileJavaSource(srcFolder,
				classFolder, libDest + File.separator + "lib", context);
		if (!compileFlag)
			throw new GeneratorException(
					"Failed to compile generated java source. Check the log.");
		
		//Do not need j2ee.jar during execution
		File j2eeFile = new File(libDest + File.separator + "lib"+ File.separator + "j2ee-1.4.jar"); 
		FileUtils.deleteQuietly(j2eeFile);
		
		try {
			File dirOrFile2Jar = new File(context.getMapping().getOptions()
					.getOutputPath()
					+ File.separator + "web");
			File destJar = new File(context.getMapping().getOptions()
					.getOutputPath()
					+ File.separator + "restful.war");
			new JarHelper().jarDir(dirOrFile2Jar, destJar);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GeneratorException(
					"Failed to generate web archive file.", e);
		}
	}

	@Override
	protected void postProcess() throws GeneratorException {

	}

	public Mapping readMappingXML(String mappingXML) throws GeneratorException {
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller(true,
				"gov.nih.nci.restgen.mapping.model");
		StringReader reader = new StringReader(mappingXML);
		try {
			return (Mapping) unmarshaller.fromXML(reader);
		} catch (XMLUtilityException e) {
			e.printStackTrace();
			throw new GeneratorException("Failed to read mapping file: ", e);
		}
	}

}