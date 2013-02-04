package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.util.GeneratorUtil;

import java.io.File;
import java.util.List;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

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
		// TODO Auto-generated method stub

	}

	@Override
	protected void preProcess() throws GeneratorException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validate() throws GeneratorException {

	}

	@Override
	public void runProcess() throws GeneratorException {
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate template = group
				.getInstanceOf("gov/nih/nci/restgen/templates/beans");

		Mapping mapping = context.getMapping();

		List<Resource> resources = mapping.getResources();
		for (Resource resource : resources) {
			String beanDefStr = "<bean id=\"" + resource.getName()
					+ "\" class=\"gov.nih.nci.restgen.generated.resource."
					+ resource.getName() + "Resource\" />";
			template.setAttribute("ResourceRefBeanDef", beanDefStr);
			String refBean = "<ref bean=\"" + resource.getName() + "\" />";
			template.setAttribute("ResourceRefBean", refBean);

		}

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

	}

	private String getUpperCaseStart(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
