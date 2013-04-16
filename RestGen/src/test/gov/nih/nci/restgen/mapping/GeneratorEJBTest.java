/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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

public class GeneratorEJBTest {
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
		mapping.setName("Catalog");
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
		options.setOutputPath("C:\\DEV\\RestGen\\generatedejb");
		options.setRootPath("C:\\DEV\\RestGen");
		options.setWrapperType(Options.EJB);
		options.setEjbLocation("C:\\DEV\\RestGen\\examples\\catalog\\BookCatalog.jar");
		//options.setWsdlLocation("http://localhost:21080/wsdl_first/services/CustomerServicePort?WSDL");
		//options.setWsdlLocation("C:\\DEV\\RestGen\\examples\\customerservice\\CustomerService.wsdl");
		
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
		resource1.setName("Catalog");
		resource1.setPath("catalog");
		resource1.setPojoLocation("C:\\DEV\\RestGen\\examples\\catalog\\gov\\nih\\nci\\ejb\\book.class");
		List<Method> methods1 = new ArrayList<Method>();
		
		//GET: getCustomersByName
		Method readMethod = new Method();
		Implementation impl1 = new Implementation();
		impl1.setName("catalog");
		impl1.setType("EJB");
		impl1.setJndiName("catalog");
		impl1.setClientType(Implementation.EJB_REMOTE);
		impl1.setJndiProperties("C:\\DEV\\RestGen\\examples\\catalog\\jndi.properties");
		impl1.setJndiName("CatalogRemoteHome");
		impl1.setPath("C:\\DEV\\RestGen\\examples\\catalog\\BookCatalog.jar");
		Operation operation1 = new Operation();
		operation1.setName("getBooks");
		Output output = new Output();
		output.setType("java.util.List");
		operation1.setOutput(output);
		impl1.setOperation(operation1);
		readMethod.setImplementation(impl1);
		readMethod.setPathName("books");
		readMethod.setName(Method.GET);
		methods1.add(readMethod);

		//POST: addCustomer
		Method postMethod = new Method();
		postMethod.setName(Method.POST);
		Implementation impl2 = new Implementation();
		impl2.setName("catalog");
		impl2.setType("EJB");
		impl2.setJndiName("CatalogRemoteHome");
		impl2.setClientType(Implementation.EJB_REMOTE);
		impl2.setJndiProperties("C:\\DEV\\RestGen\\examples\\catalog\\jndi.properties");
		impl2.setJndiName("catalog");
		impl2.setPath("C:\\DEV\\RestGen\\examples\\BookCatalog.jar");
		Operation operation2 = new Operation();
		operation2.setName("addBook");
		Input input = new Input();
		input.setName("book");
		input.setType("gov.nih.nci.ejb.Book");
		List inputs = new ArrayList();
		inputs.add(input);
		operation2.setInputs(inputs);
		impl2.setOperation(operation2);
		postMethod.setImplementation(impl2);
		//postMethod.setPathName("books");
		methods1.add(postMethod);

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
