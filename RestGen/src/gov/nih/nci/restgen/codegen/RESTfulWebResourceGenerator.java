/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

import com.predic8.schema.ComplexType;
import com.predic8.schema.Schema;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;

public class RESTfulWebResourceGenerator extends Generator {

	public RESTfulWebResourceGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	protected void init() throws GeneratorException {
		getContext().getLogger().info("Generating RESTful web artifacts...Started!");
		// TODO Auto-generated method stub

	}

	@Override
	protected void preProcess() throws GeneratorException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validate() throws GeneratorException {

	}

	public void runProcess() throws GeneratorException {
		Mapping mapping = context.getMapping();
		if (mapping.getOptions().getWrapperType().equals(Options.EJB))
			runProcessEJB();
		else
			runProcessSOAP();
	}

	private void runProcessEJB() throws GeneratorException {
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate template = group
				.getInstanceOf("gov/nih/nci/restgen/templates/beans");
		StringTemplate indexTemplate = group
				.getInstanceOf("gov/nih/nci/restgen/templates/JAXBIndex");

		Mapping mapping = context.getMapping();
		try {

			List<Resource> resources = mapping.getResources();
			if (context.getMapping().getOptions().getWrapperType()
					.equals(Options.EJB)) 
			{
				template.setAttribute("ProviderRef", "<ref bean=\"jaxbXmlContentProvider\"/>");
				template.setAttribute("ProviderBean", "<bean id=\"jaxbXmlContentProvider\" class=\"gov.nih.nci.restgen.util.RESTContentHandler\"/>");
			}
			
			for (Resource resource : resources) {

				String pojoLocation = resource.getPojoLocation();
				if (pojoLocation.endsWith(".class")) {
					File file = new File(pojoLocation);
					InputStream is;
					is = new FileInputStream(file);
					ClassParser cp = new ClassParser(is, file.getName());
					JavaClass javaClass = cp.parse();
					String className = javaClass.getClassName();
					String resourceClassName = className.substring(className.indexOf(javaClass.getPackageName())+javaClass.getPackageName().length()+1);
					String beanDefStr = "<bean id=\"" + resource.getName()
							+ "\" class=\"" +Constants.GENERATOR_PKG_NAME +"."+ resource.getName() + "Resource\" />";
					template.setAttribute("ResourceRefBeanDef", beanDefStr);
					String refBean = "<ref bean=\"" + resource.getName()
							+ "\" />";
					template.setAttribute("ResourceRefBean", refBean);
					String value = "<value>" + 
							className + "</value>";
					template.setAttribute("ResourcePojo", value);
					template.setAttribute("ResourceName", resource.getName());
					
					
				} else {
					JarFile jarFile = new JarFile(pojoLocation);
					Enumeration<?> en = jarFile.entries();
					ArrayList<String> classList = new ArrayList<String>();
					while (en.hasMoreElements()) {
						JarEntry entry = (JarEntry) en.nextElement();
						if (entry.getName().endsWith(".class")) {
							InputStream input = jarFile.getInputStream(entry);
							ClassParser cp = new ClassParser(input,
									entry.getName());
							JavaClass javaClass = cp.parse();
							String className = javaClass.getClassName();
							String resourceClassName = className.substring(className.indexOf(javaClass.getPackageName())+javaClass.getPackageName().length()+1);
							String beanDefStr = "<bean id=\"" + resource.getName()
									+ "\" class=\"" + Constants.GENERATOR_PKG_NAME + "." + resourceClassName + "Resource\" />";
							template.setAttribute("ResourceRefBeanDef",
									beanDefStr);
							String refBean = "<ref bean=\""
									+ resource.getName() + "\" />";
							template.setAttribute("ResourceRefBean", refBean);
							
							String value = "<value>" + 
									className + "</value>";
							template.setAttribute("ResourcePojo", value);
							template.setAttribute("ResourceName", resource.getName());
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new GeneratorException(
					"Failed to generated web resource file with spring mappings for EJB POJO class",
					e);
		} catch (ClassFormatException e) {
			throw new GeneratorException(
					"Failed to generated web resource file with spring mappings for EJB POJO class",
					e);
		} catch (IOException e) {
			throw new GeneratorException(
					"Failed to generated web resource file with spring mappings for EJB POJO class",
					e);
		}

		if (mapping.getOptions().getWsdlLocation() != null) {
			WSDLParser parser = new WSDLParser();

			Definitions defs = parser.parse(context.getMapping().getOptions()
					.getWsdlLocation());
			List<Schema> schemas = defs.getTypes().getSchemas();
			for (Schema schema : schemas) {
				List<ComplexType> complexTypes = schema.getComplexTypes();
				for (ComplexType cType : complexTypes) {
					String value = "<value>gov.nih.nci.restgen.generated.client."
							+ getUpperCaseStart(cType.getName()) + "</value>";
					template.setAttribute("ResourcePojo", value);
				}

			}
		}

		GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
				+ File.separatorChar + "web" + File.separatorChar + "WEB-INF",
				"cxf-beans.xml", template.toString());

		StringTemplate templateWeb = group
				.getInstanceOf("gov/nih/nci/restgen/templates/web");

		GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
				+ File.separatorChar + "web" + File.separatorChar + "WEB-INF",
				"web.xml", templateWeb.toString());

	}

	private void runProcessSOAP() throws GeneratorException {
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate template = group
				.getInstanceOf("gov/nih/nci/restgen/templates/beans");

		Mapping mapping = context.getMapping();

		List<Resource> resources = mapping.getResources();
		for (Resource resource : resources) {
			String beanDefStr = "<bean id=\"" + resource.getName()
					+ "\" class=\"" + Constants.GENERATOR_PKG_NAME + "."
					+ resource.getName() + "Resource\" />";
			template.setAttribute("ResourceRefBeanDef", beanDefStr);
			String refBean = "<ref bean=\"" + resource.getName() + "\" />";
			template.setAttribute("ResourceRefBean", refBean);
			template.setAttribute("ResourceName", resource.getName());
		}
		template.setAttribute("ProviderRef", "<ref bean=\"jaxbXmlContentProvider\"/>");
		template.setAttribute("ProviderBean", "<bean id=\"jaxbXmlContentProvider\" class=\"gov.nih.nci.restgen.util.RESTContentHandler\"/>");

		/*
		 * if(mapping.getOptions().getWsdlLocation() != null) { WSDLReader
		 * reader = WSDLFactory.newInstance().newWSDLReader(); Description desc
		 * = reader.read(new URL(mapping.getOptions().getWsdlLocation()));
		 * 
		 * List<Schema> schemas = desc.getTypes().getSchemas();
		 * 
		 * for (Schema schema : schemas) { List<ComplexType> complexTypes =
		 * schema.getComplexTypes(); for(ComplexType cType: complexTypes) {
		 * String value =
		 * "<value>gov.nih.nci.restgen.generated.client."+cType.getName
		 * ()+"</value>"; template.setAttribute( "ResourcePojo", value); }
		 * 
		 * } }
		 */
		if (mapping.getOptions().getWsdlLocation() != null) {
			WSDLParser parser = new WSDLParser();

			Definitions defs = parser.parse(context.getMapping().getOptions()
					.getWsdlLocation());
			List<Schema> schemas = defs.getTypes().getSchemas();
			for (Schema schema : schemas) {
				List<ComplexType> complexTypes = schema.getComplexTypes();
				for (ComplexType cType : complexTypes) {
					String value = "<value>gov.nih.nci.restgen.generated.client."
							+ getUpperCaseStart(cType.getName()) + "</value>";
					template.setAttribute("ResourcePojo", value);
				}

			}
		}

		GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
				+ File.separatorChar + "web" + File.separatorChar + "WEB-INF",
				"cxf-beans.xml", template.toString());

		StringTemplate templateWeb = group
				.getInstanceOf("gov/nih/nci/restgen/templates/web");

		GeneratorUtil.writeFile(mapping.getOptions().getOutputPath()
				+ File.separatorChar + "web" + File.separatorChar + "WEB-INF",
				"web.xml", templateWeb.toString());

	}

	@Override
	protected void postProcess() throws GeneratorException {
		// TODO Auto-generated method stub
		getContext().getLogger().info("Generating RESTful web artifacts...Completed!");

	}

	private String getUpperCaseStart(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
