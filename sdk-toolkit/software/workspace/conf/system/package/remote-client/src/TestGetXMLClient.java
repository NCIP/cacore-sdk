import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLConnection;
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

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;


public class TestGetXMLClient extends TestClient
{
	public static void main(String args[])
	{
		TestGetXMLClient client = new TestGetXMLClient();
		try
		{
			client.testXML();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testXML() throws Exception
	{
		Collection<Class> classList = getClasses();
		String serverUrl = "@SERVER_URL@";
		for(Class klass:classList)
		{
			if (!Modifier.isAbstract(klass.getModifiers())){

				System.out.println("Searching for "+klass.getName());
				try
				{
					String searchUrl = serverUrl+"/GetXML?query="+klass.getName()+"&"+klass.getName();
					URL url = new URL(searchUrl);
					URLConnection conn = url.openConnection();

					//Uncomment following two lines for secured system and provide proper username and password
					//String base64 = "userId" + ":" + "password";
					//conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64(base64.getBytes())));

					File myFile = new File("./output/" + klass.getName() + "_test-getxml.xml");						

					FileWriter myWriter = new FileWriter(myFile);
					DataInputStream dis = new DataInputStream(conn.getInputStream());

					String s = null;
					while ((s = dis.readLine()) != null)
						myWriter.write(s);

					myWriter.close();
				}catch(Exception e)
				{
					System.out.println("Exception caught: " + e.getMessage());
					e.printStackTrace();
				}
				//break;
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