package gov.nih.nci.restgen.codegen;

import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.w3c.dom.Document;

import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.WSDLParser;

public class EJBClientGenerator extends Generator {

	public EJBClientGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	public void runProcess() throws GeneratorException {
		Mapping mapping = context.getMapping();
		Options options = mapping.getOptions();
		if (!options.getWrapperType().equals(Options.EJB))
			return;

		context.getLogger().info("Generating EJB Client");

		String outputPath = options.getOutputPath();
        JarFile jarFile = new JarFile(file);
        JarEntry jarEntry = jarFile.getJarEntry("ejb-jar.xml");
        if (jarEntry != null) {
        	InputStream is = jarFile.getInputStream(jarEntry);
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(is);
        	doc.getDocumentElement().normalize();
        //do something with the doc
        	populateEntriesFromDoc(doc);
        }
		
		String wsdlLocation = options.getWsdlLocation();
		try {
			WSDLParser parser = new WSDLParser();

			Definitions defs = parser.parse(wsdlLocation);
			String namespace = defs.getTargetNamespace();

			for (PortType pt : defs.getPortTypes()) {
				System.out.println(pt.getName());
				for (Operation op : pt.getOperations()) {
					System.out.println(" -" + op.getName());
				}
			}

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
				WSDLToJava.main(new String[] { "-client", "-compile", "-d",
						outputPath, "-p",
						namespace + "=gov.nih.nci.restgen.generated.client",
						"-b", mapping.getOptions().getWsdlBindingFile(),
						// "-b",
						// mapping.getOptions().getOutputPath()+File.separator+"binding.xml",
						wsdlLocation });
			} else {
				WSDLToJava.main(new String[] { "-client", "-compile", "-d",
						outputPath, "-p",
						namespace + "=gov.nih.nci.restgen.generated.client",
						// "-b",
						// mapping.getOptions().getOutputPath()+File.separator+"binding.xml",
						wsdlLocation });
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

	}

}
