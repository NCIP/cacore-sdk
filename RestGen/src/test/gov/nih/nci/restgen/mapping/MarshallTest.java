package test.gov.nih.nci.restgen.mapping;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.nih.nci.restgen.mapping.JAXBMarshaller;
import gov.nih.nci.restgen.mapping.JAXBUnmarshaller;
import gov.nih.nci.restgen.mapping.Marshaller;
import gov.nih.nci.restgen.mapping.XMLUtilityException;
import gov.nih.nci.restgen.mapping.model.Component;
import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Input;
import gov.nih.nci.restgen.mapping.model.Link;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Operation;
import gov.nih.nci.restgen.mapping.model.Output;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.mapping.model.Source;
import gov.nih.nci.restgen.mapping.model.Target;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class MarshallTest{

	@BeforeClass 
	public static void testSetup(){
	    // Preparation of the unit tests
	  }
	  
	  @AfterClass 
	  public static void testCleanup(){
	    // Teardown for data used by the unit tests
	  }	
	  
	  @Test
	  public void testMarshalling() throws XMLUtilityException
	  {
		  //Create Mapping
		  Mapping mapping = new Mapping();
		  mapping.setName("example");
		  mapping.setVersion("1.0");
		  mapping.setCreatedOn(new Date());
		  mapping.setLastUpdatedOn(new Date());
		  
		  List<Link> links = new ArrayList<Link>();
		  List<Resource> resources = new ArrayList<Resource>();
		  List<Component> components = new ArrayList<Component>();
		  
		  mapping.setLinks(links);
		  mapping.setComponents(components);
		  mapping.setResources(resources);
		  
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
		  resource1.setName("Specimen");
		  List<Method> methods1 = new ArrayList<Method>();
		  Method readMethod = new Method();
		  readMethod.setName("read");
		  Implementation impl1 = new Implementation();
		  impl1.setName("SpecimenService");
		  impl1.setType("SOAP");
		  Operation operation1 = new Operation();
		  operation1.setName("getSpecimen");
		  Output output = new Output();
		  output.setType("Specimen");
		  List<Input> inputs = new ArrayList<Input>();
		  Input input = new Input();
		  input.setType("int");
		  inputs.add(input);
		  operation1.setOutput(output);
		  operation1.setInputs(inputs);
		  impl1.setOperation(operation1);
		  readMethod.setImplementation(impl1);
		  methods1.add(readMethod);
		  resource1.setMethods(methods1);
		  resources.add(resource1);

		  Resource resource2 = new Resource();
		  resource2.setName("Site");
		  List<Method> methods2 = new ArrayList<Method>();
		  Method createMethod = new Method();
		  readMethod.setName("create");
		  Implementation impl2 = new Implementation();
		  impl2.setName("SpecimenService");
		  impl2.setType("SOAP");
		  Operation operation2 = new Operation();
		  operation2.setName("createSite");
		  Output output2 = new Output();
		  output2.setType("int");
		  List<Input> inputs2 = new ArrayList<Input>();
		  Input input2 = new Input();
		  input2.setType("Site");
		  inputs2.add(input2);
		  operation2.setOutput(output2);
		  operation2.setInputs(inputs2);
		  impl2.setOperation(operation2);
		  createMethod.setImplementation(impl2);
		  methods2.add(createMethod);
		  resource2.setMethods(methods2);
		  resources.add(resource2);
		  
		  JAXBMarshaller marshaller = new JAXBMarshaller(true, "gov.nih.nci.restgen.mapping.model", "gme://caCORE.caCORE.RESTFul/1.0/");
		  String xml = marshaller.toXML(mapping);
		  System.out.println(xml);
		  assertNotNull(xml);

		  JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller(true, "gov.nih.nci.restgen.mapping.model");
		  StringReader reader = new StringReader(xml);
		  Mapping uMapping = (Mapping)unmarshaller.fromXML(reader);
		  System.out.println("Unmarshalled: "+uMapping);
		  assertNotNull(uMapping);
		  assertEquals(mapping, uMapping);
	  }
}
