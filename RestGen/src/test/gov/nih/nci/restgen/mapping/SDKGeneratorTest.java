/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.mapping;

import gov.nih.nci.restgen.codegen.GeneratorContext;
import gov.nih.nci.restgen.codegen.GeneratorException;
import gov.nih.nci.restgen.codegen.RESTfulWrapperGenerator;
import gov.nih.nci.restgen.mapping.model.Component;
import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Input;
import gov.nih.nci.restgen.mapping.model.Link;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Operation;
import gov.nih.nci.restgen.mapping.model.Options;
import gov.nih.nci.restgen.mapping.model.Output;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.mapping.model.Source;
import gov.nih.nci.restgen.mapping.model.Target;
import gov.nih.nci.restgen.util.XMLUtilityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SDKGeneratorTest {
	@BeforeClass
	public static void testSetup() {
		// Preparation of the unit tests
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test
	public void testGenerator() throws XMLUtilityException {

		// Create Mapping
		Mapping mapping = new Mapping();
		mapping.setName("ExampleService");
		mapping.setVersion("1.0");
		mapping.setCreatedOn(new Date());
		mapping.setLastUpdatedOn(new Date());

		List<Link> links = new ArrayList<Link>();
		List<Resource> resources = new ArrayList<Resource>();
		List<Component> components = new ArrayList<Component>();

		mapping.setLinks(links);
		mapping.setComponents(components);
		mapping.setResources(resources);
		Options options = new Options();
		options.setOutputPath("C:\\DEV\\RestGen\\generatedgridsoap");
		options.setRootPath("C:\\DEV\\RestGen");
		options.setWrapperType(Options.SOAP_SERVICE);
		//options.setWsdlLocation("http://localhost:29080/example/services/exampleService?WSDL");
		options.setWsdlLocation("http://localhost:8080/wsrf/services/cagrid/ExampleService?WSDL");
		
		//options.setWsdlLocation("C:\\DEV\\RestGen\\examples\\sdk\\exampleService2.xml");
		
		mapping.setOptions(options);
		Source source1 = new Source();
		source1.setId("S1");
		source1.setComponentId("CS1");
		Target target1 = new Target();
		target1.setId("T1");
		target1.setComponentId("CT1");
		Link link1 = new Link();
		link1.setSource(source1);
		link1.setTarget(target1);

		Source source2 = new Source();
		source2.setId("S2");
		source2.setComponentId("CS2");
		Target target2 = new Target();
		target2.setId("T2");
		target2.setComponentId("CT2");
		Link link2 = new Link();
		link2.setSource(source2);
		link2.setTarget(target2);
		links.add(link1);
		links.add(link2);

		Component component1 = new Component();
		component1.setId("C1");
		component1.setLocation("Location1");
		component1.setType("source");
		component1.setKind("xml");

		Component component2 = new Component();
		component2.setId("C2");
		component2.setLocation("Location2");
		component2.setType("target");
		component2.setKind("xml");
		components.add(component1);
		components.add(component2);

		Resource resource1 = new Resource();
		resource1.setName("ExampleService");
		resource1.setPath("example");
		List<Method> methods1 = new ArrayList<Method>();
		Method readMethod = new Method();
		readMethod.setName("read");
		Implementation impl1 = new Implementation();
		impl1.setName("WSQueryImplService");
		impl1.setType("SOAP");
		impl1.setPortName("exampleService");
		impl1.setPath("http://localhost:29080/example/services/exampleService?WSDL");
		Operation operation1 = new Operation();
		operation1.setName("queryObject");
		impl1.setOperation(operation1);
		readMethod.setImplementation(impl1);
		readMethod.setPathName("query");
		readMethod.setName(Method.GET);
		methods1.add(readMethod);
		resource1.setMethods(methods1);
		resources.add(resource1);
		
		GeneratorContext context = new GeneratorContext(mapping);
		RESTfulWrapperGenerator generator = new RESTfulWrapperGenerator(context);
		try {
			generator.generate();
		} catch (GeneratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
