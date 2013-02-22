package gov.nih.nci.restgen.codegen;

import java.util.List;

import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.cxf.tools.wsdlto.WSDLToJava;

import com.predic8.schema.Schema;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.WSDLParser;

public class WebserviceClientGenerator extends Generator {

	public WebserviceClientGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	public void runProcess() throws GeneratorException {
		Mapping mapping = context.getMapping();
		Options options = mapping.getOptions();
		if (!options.getWrapperType().equals(Options.SOAP_SERVICE))
			return;

		String outputPath = options.getOutputPath();
		String wsdlLocation = options.getWsdlLocation();
		try {
			WSDLParser parser = new WSDLParser();

			Definitions defs = parser.parse(wsdlLocation);
			List<Schema> schemas = defs.getSchemas();
			String[] ns = new String[schemas.size()*2];
			StringBuffer targetNamespaces = new StringBuffer();
			int i=0;
			for(Schema schema : schemas)
			{
				ns[i] = "-p";
				ns[i+1] = schema.getTargetNamespace()+"=gov.nih.nci.restgen.generated.client";
				i+=2;
			}
			String namespace = defs.getTargetNamespace();

			/*for (PortType pt : defs.getPortTypes()) {
				System.out.println(pt.getName());
				for (Operation op : pt.getOperations()) {
					System.out.println(" -" + op.getName());
				}
			}*/

			if (outputPath == null || outputPath.trim().length() == 0)
				throw new GeneratorException(
						"Failed to generate SOAP client: Invalid output folder");
			if (wsdlLocation == null || wsdlLocation.trim().length() == 0)
				throw new GeneratorException(
						"Failed to generate SOAP client: Invalid WSDL Location");
			if (namespace == null || namespace.trim().length() == 0)
				throw new GeneratorException(
						"Failed to generate SOAP client: Invalid namespace");

			
			if (mapping.getOptions().getWsdlBindingFile() != null) {
				int argsCount = 9 + ns.length;
				
				String[] args = new String[argsCount];
				int counter = 0;
				args[counter] = "-client";
				args[counter++] = "-compile";
				args[counter++] = "-d";
				args[counter++] = outputPath;
				args[counter++] = "-b";
				args[counter++] = mapping.getOptions().getWsdlBindingFile();
				args[counter++] = "-wsdlLocation";
				args[counter++] = wsdlLocation;
				for(int j=0;j<ns.length;j++)
				{
					args[counter++] = ns[j];
				}
				args[counter++] = wsdlLocation;
				WSDLToJava.main(args);
			} else {
				int argsCount = 7 + ns.length;
				
				String[] args = new String[argsCount];
				int counter = 0;
				args[counter++] = "-client";
				args[counter++] = "-compile";
				args[counter++] = "-d";
				args[counter++] = outputPath;
				args[counter++] = "-wsdlLocation";
				args[counter++] = wsdlLocation;
				for(int j=0;j<ns.length;j++)
				{
					args[counter++] = ns[j];
				}
				args[counter++] = wsdlLocation;
				WSDLToJava.main(args);
			}
		} catch (Exception e) {
			context.getLogger().error("Failed to generate SOAP client", e);
			throw new GeneratorException("Failed to generate SOAP client", e);
		}
		context.getLogger().info(
				"Generating SOAP Webservice Client..Completed. Output files are generated at "
						+ outputPath);
	}

	@Override
	protected void init() throws GeneratorException {
		getContext().getLogger().info("Generating SOAP web service client...Started!");
		// TODO Auto-generated method stub

	}

	@Override
	protected void preProcess() throws GeneratorException {
		// TODO Auto-generated method stub
		StringTemplateGroup group = new StringTemplateGroup("restful");
		StringTemplate template = group
				.getInstanceOf("gov/nih/nci/restgen/templates/binding");
		Mapping mapping = context.getMapping();

		GeneratorUtil.writeFile(mapping.getOptions().getOutputPath(),
				"binding.xml", template.toString());

	}

	@Override
	protected void validate() throws GeneratorException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void postProcess() {
		// TODO Auto-generated method stub
		//getContext().getLogger().info("Generating SOAP web service client...Completed!");
	}

}
