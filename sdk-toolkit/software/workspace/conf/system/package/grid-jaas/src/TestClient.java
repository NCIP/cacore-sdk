import gov.nih.nci.system.security.authentication.cagrid.client.GridAuthenticationHelper;
import org.globus.gsi.GlobusCredential;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.applicationservice.ApplicationService;


public class TestClient
{
	public static void main(String args[])
	{
		TestClient client = new TestClient();
		try
		{
			client.testSearch();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
	public ApplicationService getApplicationService() throws Exception
	{
		String username = "SDKUser1";
		String password = "Psat123!@#";
		GridAuthenticationHelper loginHelper = new GridAuthenticationHelper("@CAGRID_LOGIN_MODULE_NAME@");
		GlobusCredential proxy = loginHelper.login(username,password);
		System.out.println(proxy);
		System.out.println("Identity:"+proxy.getIdentity());

		return ApplicationServiceProvider.getApplicationService(proxy);
		
	}
	
	public void testSearch() throws Exception
	{
		ApplicationService appService = getApplicationService();
		
		Collection<Class> classList = getClasses();
		for(Class klass:classList)
		{
			if (!Modifier.isAbstract(klass.getModifiers())){
				Object o = klass.newInstance();
				System.out.println("Searching for "+klass.getName());
				try
				{
					Collection results = appService.search(klass, o);
					for(Object obj : results)
					{
						printObject(obj, klass);
						break;
					}
				}catch(Exception e)
				{
					System.out.println(">>>"+e.getMessage());
				}
			}
		}
	}
	
	void printObject(Object obj, Class klass) throws Exception {
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


	Collection<Class> getClasses() throws Exception
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