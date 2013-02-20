package test.gov.nih.restgen.ui.actions;

import static org.junit.Assert.*;


import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UploadEJBJarActionTest {

	@Test
	public void testValidateEJBJar() throws Exception
	{
		ArrayList<String> EJBRemoteOperationsList = new ArrayList<String>();
	    ArrayList<String> EJBHomeOperationsList = new ArrayList<String>();
	    ArrayList<String> EJBNameList = new ArrayList<String>();
	    ArrayList<String> EJBBeanList = new ArrayList<String>();
		File file = new File("C:\\RESTFUL-WRAPPER\\sample-data\\ejb-jar\\BookCatalog.jar");
		
		if(!file.exists())
		{
			throw new Exception ("EJB jar not found");
		}
	    JarFile jarFile = new JarFile(file);
	    boolean ejbjarxml = false;
	    Enumeration jarEntries = jarFile.entries();
	    JarEntry jarEntry = null;
	    while (jarEntries.hasMoreElements())
	    {
	      jarEntry = (JarEntry)jarEntries.nextElement();
	      if(jarEntry.getName().contains("ejb-jar.xml"))
	      {
	    	  ejbjarxml = true;
	    	  break;
	      }
	    }
	    
	    
	   // JarEntry jarEntry = jarFile.getJarEntry("META-INF\\ejb-jar.xml");
	    if(ejbjarxml)
	    {
	    	InputStream is = jarFile.getInputStream(jarEntry);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(is);
	    	doc.getDocumentElement().normalize();
	    
	    
	    NodeList listOfSessionBeans = doc.getElementsByTagName("session");
	    
	    for(int s=0; s<listOfSessionBeans.getLength() ; s++){
	    	
	    	Node nNode = listOfSessionBeans.item(s);
	    		
	 		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    		Element firstSessionElement = (Element)nNode;
	    		NodeList ejbNameNodeList = firstSessionElement.getChildNodes();
	    		
	    		if(ejbNameNodeList != null && ejbNameNodeList.getLength() > 0) {
	    			for(int i=0; i<ejbNameNodeList.getLength() ; i++){
	    			Node node = ejbNameNodeList.item(i);
	    			String textVal = node.getNodeName();
	    			if (node.getNodeType() == Node.ELEMENT_NODE) {
	    				System.out.println("Node name....."+node.getNodeName());
	    			if(textVal.equals("remote"))
	    			{
	    				EJBRemoteOperationsList.add(node.getTextContent());
	    				System.out.println("remote interface>>>>:"+node.getTextContent());
	    			}
	    			else if(textVal.equals("home"))
	    			{
	    				EJBHomeOperationsList.add(node.getTextContent());
	    				System.out.println("home interface>>>>:"+node.getTextContent());
	    			}
	    			else if(textVal.equals("ejb-class"))
	    			{
	    				EJBBeanList.add(node.getTextContent());
	    				System.out.println("ejb class>>>>:"+node.getTextContent());
	    			}
	    			else if(textVal.equals("ejb-name"))
	    			{
	    				EJBNameList.add(node.getTextContent());
	    				System.out.println("ejb name>>>>:"+node.getTextContent());
	    			}
	    			
	    			}
	    			}
	    		}
	    		    			
	 	 }
	 		   
	    }

	    }
	    
	    Iterator<String> it = EJBRemoteOperationsList.iterator();
	    int j=0;
	    boolean ejbclassFound = false;
	    while(it.hasNext())
	    {
	    	String remoteInterfaceClass = (String)it.next();
	    	System.out.println("remote interface class name begin>>>>:"+remoteInterfaceClass);
	    	ejbclassFound = false;
           
            while (jarEntries.hasMoreElements())
            
              jarEntry = (JarEntry)jarEntries.nextElement();
              if(jarEntry.getName().equals(remoteInterfaceClass.replace('.','/')))
              {
            	  ejbclassFound = true;
            	  break;
              }

           }
	    	if(ejbclassFound)
	    	{
	    		
	            Enumeration e = jarFile.entries();

	            URL[] urls = { new URL("jar:file:" + file+"!/") };
	            URLClassLoader cl = URLClassLoader.newInstance(urls);

	            while (e.hasMoreElements()) {
	                JarEntry je = (JarEntry) e.nextElement();
	                if(je.isDirectory() || !je.getName().endsWith(".class")){
	                    continue;
	                }
	                // -6 because of .class
	                String className = je.getName().substring(0,je.getName().length()-6);
	                className = className.replace('/', '.');
	                Class c = cl.loadClass(className);
	                System.out.println("CLASS NAMES,,,,,:::::"+c.getName());
	                for(java.lang.reflect.Method method : c.getDeclaredMethods())
	            	{
	                		
	            		//Type [] args = (Type[]) method.getParameterTypes();
	            	java.lang.reflect.Type returnType  = method.getGenericReturnType();
	            	java.lang.reflect.Type[] args = method.getGenericParameterTypes();
	            	System.out.println("method generic string..."+method.toGenericString());
	            	System.out.println("method return type..."+returnType.toString());
	            		if(args!=null && args.length>0){
	            			for (int i=0;i<args.length;i++)
	            			{
	            			 if (args[i] instanceof ParameterizedType) {
	                            System.out.println("parametrized param type..."+args[i]);       // "java.util.Set<yourpackage.Cat>"
	                            for (java.lang.reflect.Type arg : ((ParameterizedType)returnType).getActualTypeArguments()) {
	                                System.out.println("parametrized param type1..."+arg); // "class yourpackage.Cat"
	                            }
	                        }
	           			
	            					System.out.println("method params type..."+((java.lang.reflect.Type)args[i]).toString());
	            			
	            			}
	            		}

	                	
	                	
	            	}

	            
	 

	    	}

	    
	    
	}

}
}
