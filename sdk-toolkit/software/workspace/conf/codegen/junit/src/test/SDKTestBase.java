package test;

import junit.framework.TestCase;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Satish Patel, Daniel Dumitru
 * 
 */
public abstract class SDKTestBase extends TestCase {

	private ApplicationService appService;
	private ApplicationService appServiceFromUrl;

	protected void setUp() throws Exception {
		super.setUp();
		appService = ApplicationServiceProvider.getApplicationService();
	}


	protected void tearDown() throws Exception 
	{
		appService = null;
		super.tearDown();
	}
	
	protected ApplicationService getApplicationService()
	{
		return appService;
	}
	
	
	protected ApplicationService getApplicationServiceFromUrl() throws Exception
	{
		String url = "http://localhost:8080/example";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}
	
	protected ApplicationService getBadApplicationServiceFromUrl() throws Exception
	{
		String url = "http://badhost:8080/badcontext";
		appServiceFromUrl = ApplicationServiceProvider.getApplicationServiceFromUrl(url);
		return appServiceFromUrl;
	}
	
	public static String getTestCaseName()
	{
		return "SDK Base Test Case";
	}
	
	public static void printObject(Object obj, Class klass, boolean includeAssociation) throws Exception {
		System.out.println("\nPrinting "+ klass.getName());
		Method[] methods = klass.getMethods();
		for(Method method:methods)
		{
			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
			{
				System.out.print("\t"+method.getName().substring(3)+":");
				Object val = null;
				try {
				val = method.invoke(obj, (Object[])null);
				} catch(Exception e){
					val = "ERROR - unable to determine value"; 
						
				}
				if (val instanceof java.util.Set) {
					Collection list = (Collection)val;
					for(Object object: list){
						System.out.println(object.getClass().getName()+":");
						if (includeAssociation){
							printObject(object, object.getClass(), false);
						} else {
							System.out.println(" -- association has been excluded");
						}
					}	
					//System.out.println("size="+((Collection)val).size());
				}
				else if(val instanceof ArrayList)
				{
					Collection list = (ArrayList) val;
					System.out.println("\nPrinting Collection.....");
					for(Object object: list){
						System.out.println(object.getClass().getName()+":");
						if (includeAssociation){
							printObject(object, object.getClass(), false);
						} else {
							System.out.println(" -- association has been excluded");
						}
					}
				}
				else if(val != null && val.getClass().getName().startsWith("gov.nih.nci"))
				{
					if (includeAssociation){
						printObject(val, val.getClass(), false);
					} else {
						System.out.println(" -- association has been excluded");
					}
				}
				else
					System.out.println(val);
			}
		}
	}
	
	public void printObject(Object obj, Class klass) throws Exception {
		printObject(obj,klass,false);
	}
}
