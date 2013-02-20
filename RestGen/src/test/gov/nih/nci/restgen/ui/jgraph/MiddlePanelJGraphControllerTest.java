package test.gov.nih.nci.restgen.ui.jgraph;

import static org.junit.Assert.*;
import gov.nih.nci.restgen.mapping.model.Component;
import gov.nih.nci.restgen.mapping.model.ComponentType;
import gov.nih.nci.restgen.mapping.model.KindType;
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
import gov.nih.nci.restgen.ui.common.MappableNode;
import gov.nih.nci.restgen.ui.common.UIHelper;
import gov.nih.nci.restgen.ui.jgraph.MiddlePanelJGraphController.MethodType;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultPort;
import org.junit.Test;

public class MiddlePanelJGraphControllerTest {
	private Mapping mappingData = null;
	
	public class MethodType {

    	public Method method;
    	public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
		public String getResourceName() {
			return resourceName;
		}
		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}
		public String resourceName;
		public String resourcePath;
		public String resourceLocation;
		
		public String getResourcePath() {
			return resourcePath;
		}
		public void setResourcePath(String resourcePath) {
			this.resourcePath = resourcePath;
		}
		public String getResourceLocation() {
			return resourceLocation;
		}
		public void setResourceLocation(String resourceLocation) {
			this.resourceLocation = resourceLocation;
		}
    	
    }

	@Test
	public void retrieveMappingData() throws JAXBException {
		String [] sourceNodes = {"Create(PUT","Update(POST","Read(GET)","Delete(DELETE)"};
		ArrayList<String> inputType1 = new ArrayList();
		ArrayList<String> inputType2 = new ArrayList();
		inputType1.add("{http://www.w3.org/2001/XMLSchema}string");
		inputType1.add("{http://www.w3.org/2001/XMLSchema}anyType");
		inputType2.add("");
		
		if(mappingData!=null)
		{
			mappingData= null;
		}
		List<MethodType> methodList = new ArrayList();
		// clear out the data before adding.
		if (mappingData==null)
		{
			mappingData = new Mapping();
			mappingData.setName("sample-mapping");
			mappingData.setVersion("1.0");
			mappingData.setDescription("sample-mapping");
			mappingData.setCreatedOn(Calendar.getInstance().getTime());
			mappingData.setLastUpdatedOn(Calendar.getInstance().getTime());
		}
		
// add source and destination components here PV start...
		
	    gov.nih.nci.restgen.mapping.model.Component startComp = new gov.nih.nci.restgen.mapping.model.Component();
	    startComp.setKind(KindType.XML.value());
	    startComp.setId(getNewComponentId(mappingData));
       // endComp.setLocation(schemaParser.getSchemaURI());
	    startComp.setLocation("C:\\RESTFUL-WRAPPER\\sample-data\\POJO-classes\\Book.class");
        //endComp.setRootElement(e);
	    startComp.setType(ComponentType.SOURCE.value());
	    if(mappingData.getComponents()==null)
	    {	
	    	List<gov.nih.nci.restgen.mapping.model.Component> components = new ArrayList();
	    	components.add(startComp);
	    	mappingData.setComponents(components);
	    }
        
        
        gov.nih.nci.restgen.mapping.model.Component endComp = new gov.nih.nci.restgen.mapping.model.Component();
        endComp.setKind(KindType.XML.value());
        endComp.setId(getNewComponentId(mappingData));
       // endComp.setLocation(schemaParser.getSchemaURI());
        endComp.setLocation("");
        //endComp.setRootElement(e);
        endComp.setType(ComponentType.TARGET.value());
        if(mappingData.getComponents()!=null)
	    {
        	mappingData.getComponents().add(endComp);
	    }
// add source and destination components here PV end...		
		
		//List<DefaultEdge> graphEdgeLinks = this.getMiddlePanel().retrieveLinks();
        List<DefaultEdge> graphEdgeLinks = new ArrayList<DefaultEdge>();
        DefaultTargetTreeNode childElement = null;
		for (int i=0 ;i<2; i++)
		{
			DefaultEdge linkEdge = new DefaultEdge();
			DefaultPort defaultSrcPort = new DefaultPort();
			DefaultPort defaultTgtPort = new DefaultPort();
			DefaultSourceTreeNode CreateClass = new DefaultSourceTreeNode(sourceNodes[i]);
    	    CreateClass.setResourceLocation("C:\\RESTFUL-WRAPPER\\sample-data\\POJO-classes\\Book.class");
    	    CreateClass.setResourceName("Book.class");
    	    CreateClass.setResourcePathLocation("");
    	    defaultSrcPort.setUserObject(CreateClass);
    	    linkEdge.setSource(defaultSrcPort);
    	    if(i==0){
    	    childElement = new DefaultTargetTreeNode("{http://www.w3.org/2001/XMLSchema}int getRecordsPerQuery()");
    	    childElement.setOperationName("queryObject");
	    	childElement.setInputType(inputType1);
	    	childElement.setOutputType("{http://localhost:29080/example/services/exampleService}ArrayOf_xsd_anyType");
	    	childElement.setServiceName("WSQueryImplService");
	    	childElement.setEndPoint("http://localhost:29080/example/services/exampleService");
	    	childElement.setOperationStyle("");
	    	childElement.setImplementationType("SOAP");
	    	childElement.setPortName("exampleService");
	    	defaultTgtPort.setUserObject(childElement);
	    	linkEdge.setTarget(defaultTgtPort);
    	    }
    	    else
    	    {
    	    	childElement = new DefaultTargetTreeNode("{http://www.w3.org/2001/XMLSchema}int getRecordsPerQuery()");
        	    childElement.setOperationName("getRecordsPerQuery");
    	    	childElement.setInputType(inputType2);
    	    	childElement.setOutputType("{http://www.w3.org/2001/XMLSchema}int");
    	    	childElement.setServiceName("WSQueryImplService");
    	    	childElement.setEndPoint("http://localhost:29080/example/services/exampleService");
    	    	childElement.setOperationStyle("");
    	    	childElement.setImplementationType("SOAP");
    	    	defaultTgtPort.setUserObject(childElement);
    	    	linkEdge.setTarget(defaultTgtPort);
    	    }
    	    graphEdgeLinks.add(linkEdge);
		}
		
		for (DefaultEdge linkEdge : graphEdgeLinks) {

			DefaultPort srcPort = (DefaultPort) linkEdge.getSource();
			String srcComponentId = "";
			String srcPath = "";
			//String path = (String)getMiddlePanel().getGraph().getModel().getValue(linkEdge);
			String path = "";
			MappableNode sourceNode = (MappableNode) srcPort.getUserObject();
			srcPath = UIHelper.getPathStringForNode((DefaultSourceTreeNode) sourceNode);
			DefaultPort trgtPort = (DefaultPort) linkEdge.getTarget();
			String tgtComponentId = "";
			String tgtPath = "";
			MappableNode targetNode = (MappableNode) trgtPort.getUserObject();
			srcComponentId = ((DefaultSourceTreeNode) sourceNode).toString();
			tgtComponentId = ((DefaultTargetTreeNode) targetNode).getOperationName();
			tgtPath = UIHelper.getPathStringForNode((DefaultTargetTreeNode) targetNode);
			addLink(mappingData, srcComponentId, srcPath,tgtComponentId, tgtPath, path);
			addOptions(mappingData);
			MethodType method = addMethods((DefaultSourceTreeNode) sourceNode, (DefaultTargetTreeNode) targetNode, path);
			methodList.add(method);
		    }
		    
		addResources(mappingData,methodList);
				        
		// save mapping data into file here
		
		saveMapping(new File("C:\\RESTFUL-WRAPPER\\output\\sample-mapping.xml"), mappingData);
		
		///
		
		
	}

	
	private static String getNewComponentId(Mapping m){
        int num = 0;
        if(m.getComponents()!=null)
        {
        Iterator it = m.getComponents().iterator();
        while(it.hasNext())
        {
         
            try{
            	Component c = (Component)it.next(); 
                num = Integer.parseInt(c.getId());
            }catch(Exception ignored){
            	
            }
            
        }
        num = num+1;
        }
        return String.valueOf(num);
    }
	
	
	public void addOptions(Mapping m)
	{
		
			if(m.getOptions()==null)
			{
				Options options = new Options();
				/*if(getMappingPanel().getOptionsPath()!=null)
				{
					options.setOutputPath(getMappingPanel().getOptionsPath());
				}
				else
				{
					options.setOutputPath("");
				}*/
				options.setOutputPath("");
				if("".equals("EJB"))
				{
					options.setWrapperType(Options.EJB);
					/*if(getMappingPanel().getMappingTargetFile()!=null)
					{
						options.setEjbLocation(getMappingPanel().getMappingTargetFile().getPath());
					}*/
				}
				else
				{
					options.setWrapperType(Options.SOAP_SERVICE);
					/*if(getMappingPanel().getMappingTargetFile()!=null)
					{
						options.setWsdlLocation(getMappingPanel().getWsdlURL());
					}
					if(getMappingPanel().getWSDLBindingFilePath()!=null)
					{
						options.setWsdlBindingFile(getMappingPanel().getWSDLBindingFilePath());
					}*/
					options.setWsdlLocation("file:///C:\\RESTFUL-WRAPPER\\sample-data\\data-02-07\\exampleService.zip\\exampleService.wsdl");
					options.setWsdlBindingFile("");
				}
				
				final String dir = System.getProperty("user.dir");
		        System.out.println("current dir is..... " + dir);
		        if(dir!=null)
		        {
		        	options.setRootPath(dir);
		        }
				
				m.setOptions(options);
			}
		
		
	}
	
	
	
	
    public static void addLink(Mapping m, String srcComponentId, String srcPath, String tgtComponentId, String tgtPath, String path) {
        Link l = new Link();
        Source src = new Source();
        src.setComponentId(srcComponentId);
        src.setId(srcPath);
        Target tgt = new Target();
        tgt.setComponentId(tgtComponentId);
        tgt.setId(tgtPath);
        l.setSource(src);
        l.setTarget(tgt);
        l.setPath(path.trim());
        if(m.getLinks()==null)
        {
        	List<Link> links = new ArrayList();
        	links.add(l);
        	m.setLinks(links);
        }
        else
        {
        	m.getLinks().add(l);
        }
    }
	
    public void addResources(Mapping m, List<MethodType>methodList)
    {
    	
    	List<Resource> resourceList = new ArrayList();
    	List<String> resourceListfromPanel = new ArrayList();
    	resourceListfromPanel.add("Book.class");
    	//Iterator<String> it = mappingPanel.getPOJOClassList().iterator();
    	Iterator<String> it = resourceListfromPanel.iterator();
    	Iterator<MethodType> mt = methodList.iterator();
    	while(it.hasNext())
    	{
    		Resource resource = new Resource();
    		String resourceName = (String)it.next();
    		resource.setName(resourceName);
    		if(MappingMainPanel.getResourcePathValues()!=null && (String)MappingMainPanel.getResourcePathValues().get(resourceName)!=null)
        	{
        		System.out.println("vals...."+(String)MappingMainPanel.getResourcePathValues().get(resourceName));
        		resource.setPath((String)MappingMainPanel.getResourcePathValues().get(resourceName));
        	}
    		resourceList.add(resource);
    		
    	}
    	
    	while(mt.hasNext())
    	{
    		MethodType mtype = (MethodType)mt.next();
    		for(int i=0;i<resourceList.size();i++)
    		{
    			Resource rsc = (Resource) resourceList.get(i);
    			System.out.println("resourcename and method type ..."+mtype.getResourceName()+"  "+rsc.getName());
    			rsc.setPojoLocation(mtype.getResourceLocation());
    			if(mtype.getResourceName().equals(rsc.getName()))
    			{
    			 // add the methods here
    				if(rsc.getMethods()==null)
    				{
    					
    					List<Method> methods = new ArrayList();
    					methods.add(mtype.getMethod());
    					rsc.setMethods(methods);
    				}
    				else
    				{
    					rsc.getMethods().add(mtype.getMethod());
    				}
    			
    			}
    		}
    	}
    	
    	// set the resources here
    	
    	if(m.getResources()==null)
    	{
    		
    		m.setResources(resourceList);
    	}
    	
    }
    
    
    
    public MethodType addMethods(DefaultSourceTreeNode sourceNode, DefaultTargetTreeNode targetNode, String path)
    {
    	
    	/*
    	 * 
    	 * 	•Create = PUT
			•Retrieve = GET
			•Update = POST
			•Delete = DELETE

    	 * 
    	 */
    	
    	MethodType methodType = new MethodType();
    	Method method = new Method();
    	Implementation implementation = new Implementation();
    	Operation operation = new Operation();
    	Output output = new Output();
    	List<Input> inputs = new ArrayList();
    	// set the inputs and outputs
    	if(targetNode.getInputType()!=null && targetNode.getInputType().size()>0)
    	{
    		int param = 1;
    		ArrayList inputsNode = targetNode.getInputType();
    		Iterator it = inputsNode.iterator();
    		while(it.hasNext())
    		{
    			Input input = new Input();
    			input.setType((String)it.next());
    			input.setName("param"+param++);
    			inputs.add(input);
    		}
    		
    	}
    	
    	if(targetNode.getOutputType()!=null)
    	{
    		output.setType(targetNode.getOutputType());
    	}
    	
    	if(operation.getInputs()==null)
    	{
    		operation.setInputs(inputs);
    	}
    	if(operation.getOutput()==null)
    	{
    		operation.setOutput(output);
    	}
    	
    	operation.setName(targetNode.getOperationName());
    	operation.setStyle(targetNode.getOperationStyle());
    	implementation.setOperation(operation);
    	implementation.setType(targetNode.getImplementationType());
    	if(targetNode.getImplementationType()=="SOAP")
    	{
    		implementation.setClientType(targetNode.getClientType());
        	implementation.setName(targetNode.getServiceName());
        	implementation.setPortName(targetNode.getPortName());
    	}
    	else
    	{
    		implementation.setName(targetNode.getEJBName());
    		if(MappingMainPanel.getEjbType()!=null)
    		{
    			implementation.setClientType(MappingMainPanel.getEjbType());
    		}
    		if(MappingMainPanel.getEnterJNDIName()!=null)
    		{
    			implementation.setJndiName(MappingMainPanel.getEnterJNDIName());
    		}
    		else
    		{
    			implementation.setJndiName("");
    		}
    		
    		if(MappingMainPanel.getJNDIPropertiesFilePath()!=null)
    		{
    			implementation.setJndiProperties(MappingMainPanel.getJNDIPropertiesFilePath());
    		}
    		else
    		{
    			implementation.setJndiProperties("");
    		}
    	}
    	if(path!=null && path.trim().length()>0)
    	{
    		implementation.setPath(path);
    	}
    	else
    	{
    		implementation.setPath("");
    	}
    	method.setImplementation(implementation);
    	if(sourceNode.toString().contains("Create"))
    	{
    		method.setName(Method.PUT);
    	}
    	else if (sourceNode.toString().contains("Update"))
    	{
    		method.setName(Method.POST);
    	}
    	else if (sourceNode.toString().contains("Read"))
    	{
    		method.setName(Method.GET);
    	}
    	else
    	{
    		method.setName(Method.DELETE);
    	}
    	method.setPathName(((DefaultSourceTreeNode) sourceNode).toString());
    	methodType.setResourceName(((DefaultSourceTreeNode) sourceNode).getResourceName());
    	methodType.setResourceLocation(((DefaultSourceTreeNode) sourceNode).getResourceLocation());
    	methodType.setMethod(method);
    	return methodType;
    	
    }
	public static void saveMapping(File f, gov.nih.nci.restgen.mapping.model.Mapping m) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance( "gov.nih.nci.restgen.mapping.model" );
        Marshaller u = jc.createMarshaller();
        u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
        u.marshal(new JAXBElement<gov.nih.nci.restgen.mapping.model.Mapping>(new QName("mapping"),gov.nih.nci.restgen.mapping.model.Mapping.class, m), f);

    }    
	
}
