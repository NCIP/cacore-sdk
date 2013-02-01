package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Operation;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class RESTfulWrapperGenerator extends Generator {

	public RESTfulWrapperGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	protected void init() throws GeneratorException {
		// TODO Auto-generated method stub

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

				if (method.getPathName() == null
						|| method.getPathName().trim().length() == 0) {
					context.getLogger()
							.error("No method path name or Invalid method path name. Aborting the process!");
					throw new GeneratorException(
							"No method path name or Invalid method path name. Aborting the process!");
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

				if (impl.getPortName() == null
						|| impl.getPortName().trim().length() == 0) {
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
		// Generate SOAP webservice client
		new WebserviceClientGenerator(context).generate();
		String libSrc = context.getMapping().getOptions().getRootPath()
				+ File.separator + "lib";
		String libDest = context.getMapping().getOptions().getOutputPath()
				+ File.separator + "web" + File.separator + "WEB-INF";
		String srcFolder = context.getMapping().getOptions().getOutputPath();
		String classFolder = context.getMapping().getOptions().getOutputPath()
				+ File.separator + "web" + File.separator + "WEB-INF"
				+ File.separator + "classes";

		// Generate RESTful resources
		new RESTfulResourceGenerator(context).generate();

		// Generate RESTful Web application resources
		new RESTfulWebResourceGenerator(context).generate();

		try {
			GeneratorUtil.copyDir(libSrc, libDest);
		} catch (IOException e) {
			throw new GeneratorException("Failed to copy lib folder contents",
					e);
		}
		boolean compileFlag = GeneratorUtil.compileJavaSource(srcFolder,
				classFolder, libDest + File.separator + "lib", context);
		if (!compileFlag)
			throw new GeneratorException(
					"Failed to compile generated java source. Check the log.");
		//try {
			//createJAR("restful.war", context.getMapping().getOptions().getOutputPath()+File.separator+"web", context.getMapping().getOptions().getOutputPath());
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	throw new GeneratorException("Failed to generate web archive file.", e);
		//}
	}

	@Override
	protected void postProcess() throws GeneratorException {
		// TODO Auto-generated method stub

	}

	public void createJar(String jarName, String folderName, String outputPath) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,
				"1.0");
		JarOutputStream target = new JarOutputStream(new FileOutputStream(outputPath+File.separator+jarName), manifest);
		add(new File(folderName), target);
		target.close();
	}

	private void add(File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = source.getPath().replace("\\", "/");
				if (!name.isEmpty()) {
					if (!name.endsWith("/"))
						name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles())
					add(nestedFile, target);
				return;
			}

			JarEntry entry = new JarEntry(source.getPath().replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1)
					break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null)
				in.close();
		}
	}
	
	public void createJAR(String jarName, String folderName, String outputPath) throws IOException
	{
	    File folder = new File(folderName);
	    File[] files = folder.listFiles();
	    File file=new File(outputPath+File.separator+jarName);
	    createJarArchive(file, files);		
	}
	
	private void createJarArchive(File jarFile, File[] listFiles) throws IOException{
	      byte b[] = new byte[10240];
	      FileOutputStream fout = new FileOutputStream(jarFile);
	      JarOutputStream out = new JarOutputStream(fout, new Manifest());
	      for (int i = 0; i < listFiles.length; i++) {
	        if (listFiles[i] == null || !listFiles[i].exists()|| listFiles[i].isDirectory())
	      System.out.println();
	        JarEntry addFiles = new JarEntry(listFiles[i].getName());
	        addFiles.setTime(listFiles[i].lastModified());
	        out.putNextEntry(addFiles);

	        FileInputStream fin = new FileInputStream(listFiles[i]);
	        while (true) {
	          int len = fin.read(b, 0, b.length);
	          if (len <= 0)
	            break;
	          out.write(b, 0, len);
	        }
	        fin.close();
	      }
	      out.close();
	      fout.close();
	      System.out.println("Jar File is created successfully.");
	  }	

}
