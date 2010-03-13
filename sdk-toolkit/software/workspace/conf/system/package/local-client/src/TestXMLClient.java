import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.caCOREMarshaller;
import gov.nih.nci.system.client.util.xml.caCOREUnmarshaller;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;


public class TestXMLClient extends TestClient
{
	public static void main(String args[])
	{
		TestXMLClient client = new TestXMLClient();
		try
		{
			client.testXMLUtility();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void testXMLUtility() throws Exception {
		// Application Service retrieval for secured system
		// ApplicationService appService =
		// ApplicationServiceProvider.getApplicationService("userId","password");

		ApplicationService appService = ApplicationServiceProvider
				.getApplicationService();
		Collection<Class> classList = getClasses();

		// Castor
		Marshaller marshaller = new caCOREMarshaller("xml-mapping.xml", false);
		Unmarshaller unmarshaller = new caCOREUnmarshaller(
				"unmarshaller-xml-mapping.xml", false);

		// JAXB
		boolean validate = false;
		Marshaller marshaller2 = new JAXBMarshaller(validate);
		Unmarshaller unmarshaller2 = new JAXBUnmarshaller(validate);		

		// Castor
		// XMLUtility myUtil = new XMLUtility(marshaller, unmarshaller);

		// JAXB
		XMLUtility myUtil = new XMLUtility(marshaller2, unmarshaller2);

		for (Class klass : classList) {
			if (!Modifier.isAbstract(klass.getModifiers())) {

				Object o = klass.newInstance();
				System.out.println("Searching for " + klass.getName());
				try {
					Collection results = appService.search(klass, o);
					for (Object obj : results) {
						
						boolean includeAssociations = true;
						Object convertedObj = XMLUtility.convertFromProxy(obj, includeAssociations);
//						File myFile = new File("./output/" + klass.getName()
//								+ "_test.xml");
						File myFile = new File("./output/" + convertedObj.getClass().getName()
								+ "_test.xml");

						FileWriter myWriter = new FileWriter(myFile);

						myUtil.toXML(convertedObj, myWriter);
						myWriter.close();
						// printObject(obj, klass);
						DocumentBuilder parser = DocumentBuilderFactory
								.newInstance().newDocumentBuilder();

						System.out.println("Can read " + myFile.getName()
								+ "? " + myFile.canRead());

						//Uncomment for independent validation
//						Document document = parser.parse(myFile);
//						SchemaFactory factory = SchemaFactory
//								.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//						
//
//						try {
//							System.out.println("Validating " + obj.getClass().getName()
//									+ " against the schema......\n\n");
//							Source schemaFile = new StreamSource(Thread
//									.currentThread().getContextClassLoader()
//									.getResourceAsStream(
//											obj.getClass().getPackage().getName()
//													+ ".xsd"));
//							Schema schema = factory.newSchema(schemaFile);
//							Validator validator = schema.newValidator();
//
//							validator.validate(new DOMSource(document));
//							System.out.println(obj.getClass().getName()
//									+ " has been validated!!!\n\n");
//						} catch (Exception e) {
//							System.out
//									.println(obj.getClass().getName()
//											+ " has failed validation!!!  Error reason is: \n\n"
//											+ e.getMessage());
//						}

						System.out.println("Un-marshalling " + convertedObj.getClass().getName()
								+ " from " + myFile.getName() + "......\n\n");

						// Castor Object myObj = (Object)
						// myUtil.fromXML(myFile);

						// JAXB
						//Object myObj = (Object) myUtil.fromXML(obj.getClass(), myFile);						// using class name
						Object myObj = (Object) myUtil.fromXML(convertedObj.getClass().getPackage().getName(), myFile);  // using jaxb.index context file

						printObject(myObj, convertedObj.getClass());
						break;
					}
				} catch (Exception e) {
					System.out.println("Exception caught processing class "
							+ klass.getName() + ": ");
					e.printStackTrace();
				}
				//break;
			}
		}
	}
	
	public void printObject(Object obj, Class klass) throws Exception {
		System.out.println("Printing "+ klass.getName());
		Method[] methods = klass.getMethods();
		for(Method method:methods)
		{
			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
			{
				System.out.print("\t"+method.getName().substring(3)+":");
				Object val = method.invoke(obj, (Object[])null);
				if(val instanceof java.util.Set)
					System.out.println("size="+((Collection)val).size());
				else
					System.out.println(val);
			}
		}
	}


	public Collection<Class> getClasses() throws Exception
	{
		Collection<Class> list = new ArrayList<Class>();
		JarFile file = null;
		int count = 0;
		for(File f:new File("lib").listFiles())
		{
			if(f.getName().endsWith("-beans.jar"))
			{
				file = new JarFile(f);
				count++;
			}
		}
		if(file == null) throw new Exception("Could not locate the bean jar");
		if(count>1) throw new Exception("Found more than one bean jar");
		
		Enumeration e = file.entries();
		while(e.hasMoreElements())
		{
			JarEntry o = (JarEntry) e.nextElement();
			if(!o.isDirectory())
			{
				String name = o.getName();
				if(name.endsWith(".class"))
				{
					String klassName = name.replace('/', '.').substring(0, name.lastIndexOf('.'));
					list.add(Class.forName(klassName));
				}
			}
		}
		return list;
	}

}