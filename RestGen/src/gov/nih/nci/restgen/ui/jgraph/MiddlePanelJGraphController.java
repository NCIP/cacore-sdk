/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.jgraph;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;

//import gov.nih.nci.cbiit.cmts.mapping.MappingFactory;
import gov.nih.nci.restgen.core.Component;
import gov.nih.nci.restgen.core.ComponentType;
import gov.nih.nci.restgen.core.KindType;
import gov.nih.nci.restgen.core.LinkType;
import gov.nih.nci.restgen.core.LinkpointType;
import gov.nih.nci.restgen.mapping.model.*;
import gov.nih.nci.restgen.core.Mapping.Components;
import gov.nih.nci.restgen.core.Mapping.Links;
import gov.nih.nci.restgen.ui.actions.UploadWSDLAction;
import gov.nih.nci.restgen.ui.common.MappableNode;
import gov.nih.nci.restgen.ui.common.UIHelper;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;
import gov.nih.nci.restgen.ui.mapping.MappingMiddlePanel;
import gov.nih.nci.restgen.ui.properties.DefaultPropertiesSwitchController;
import gov.nih.nci.restgen.ui.properties.PropertiesSwitchController;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;

import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Hashtable;
import java.util.Arrays;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

/**
 * This is the controller class of Middle Panel JGraph implementation. The
 * MiddlePanelJGraphController class will deal with real implementation of some
 * of actions to modify (mainly CRUD) upon graph, and mainly focuses on
 * drag-and-drop and handlings of repaint of graph, for example.
 * MiddlePanelMarqueeHandler will help handle key and mouse driven events such
 * as display pop menus, etc.
 * 
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since CMTS v1.0
 * @version $Revision: 1.14 $
 * @date       $Date: 2013-01-11
 * 
 */
public class MiddlePanelJGraphController {
	private boolean graphSelected = false;
	private boolean isGraphChanged = false;

	private Mapping mappingData = null;
	private MappingMainPanel mappingPanel = null;
	public static final Color DEFAULT_MAPPING_LINK_COLOR = Color.BLUE.darker().darker();
	private DefaultPropertiesSwitchController propertiesSwitchController;

    private List<Source> sourceMissedLink = new ArrayList<Source>();
    private List<Target> targetMissedLink = new ArrayList<Target>();

    public MiddlePanelJGraphController(MappingMainPanel mappingPan) {
		mappingPanel = mappingPan;
	}

	
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

	/**
	 * Return the number of pixels changed due to scrolling.
	 * 
	 * @param treeScrollPane
	 * @param treeNode
	 * @param reCalculateToNearestParent
	 * @return the number of pixels changed due to scrolling.
	 */
	public int calculateScrolledDistanceOnY(JScrollPane treeScrollPane,
			DefaultMutableTreeNode treeNode, boolean reCalculateToNearestParent) {
		/**
		 * Design rationale: 1) check the given tree node, if it is null or
		 * root, set the nodePositionBasedOnTotalPanel to the default root
		 * value, i.e., 8; 2) if the tree node is not root or null, proceed with
		 * normal calculation.
		 */
		final int DEFAULT_ROOT_Y_VALUE = 8;
		int nodePositionBasedOnTotalPanel = 0;
		// find the # of pixels hidden. For example : 30
		if (treeNode == null || treeNode.getParent() == null) {
			// Log.logInfo(this, (treeNode == null ? "Tree node is null." :
			// "Tree node is the root") + " will use default value.");
			nodePositionBasedOnTotalPanel = DEFAULT_ROOT_Y_VALUE;
		} else
		    {
			// System.out.println("To figure out the value via scroll bar positions.");
			// find the Y coordinate of the node. For example : 300
			TreePath tp = new TreePath(treeNode.getPath());
			if((JTree) treeScrollPane.getViewport().getView()==null)
			{
				return 0;
			}
			JTree tree = ((JTree) treeScrollPane.getViewport().getView());
			int row = tree.getRowForPath(tp);
			Rectangle pathBounds = tree.getPathBounds(tp);
			if (pathBounds == null) {
				// Log.logInfo(this, "path bounds is null. tp is '" +
				// tp.toString() + "'.");
				// System.out.println("The path bounds is null! on '" + treeNode
				// + "' of type " + treeNode.getClass().getName());
				if (reCalculateToNearestParent) {// escape if not reCal or if
													// the treeNode is the root.
					return calculateScrolledDistanceOnY(treeScrollPane,
							(DefaultMutableTreeNode) treeNode.getParent(),
							reCalculateToNearestParent);
				} else {// default to the root
					row = 0;
				}
			}
			if (row == -1)// (r==null)
			{
				// Log.logInfo(this, "tp is '" + tp.toString() + "'.");
				// System.out.println("the row value is -1! on '" + treeNode +
				// "' of type " + treeNode.getClass().getName());
				if (reCalculateToNearestParent && treeNode.getParent() != null) {// escape
																					// if
																					// not
																					// reCal
																					// or
																					// if
																					// the
																					// treeNode
																					// is
																					// the
																					// root.
					return calculateScrolledDistanceOnY(treeScrollPane,
							(DefaultMutableTreeNode) treeNode.getParent(),
							reCalculateToNearestParent);
				} else {// default to the root
					// System.out.println("Default set to the root!");
					row = 0;
				}
			}
			// System.out.println("Row value: " + row);
			if (row > 0) {
				Rectangle r = tree.getRowBounds(row);
				Point point = r.getLocation();
				int graphHeightHidden = (int) getMiddlePanel()
						.getGraphScrollPane().getViewport().getViewPosition()
						.getY();
				int treeHeightHidden = (int) treeScrollPane.getViewport()
						.getViewPosition().getY();
				nodePositionBasedOnTotalPanel = (int) point.getY()
						+ (int) r.getHeight() / 2 + graphHeightHidden
						- treeHeightHidden;
			} else {
				nodePositionBasedOnTotalPanel = DEFAULT_ROOT_Y_VALUE;
			}
			// find the Y coordinate based on the *visible* area.
			// for example : 300 - 30 + 1/2(the node height) = 290
			// Log.logInfo(this, treeNode.toString() + " view position:' " +
			// treeHeightHidden + "'");
			// Log.logInfo(this, treeNode.toString() + " tree node Y:' " +
			// nodePositionBasedOnTotalPanel + "'");
		}
		int newYpos = nodePositionBasedOnTotalPanel;
		if (newYpos < DEFAULT_ROOT_Y_VALUE) {// never lower than the
												// NOT_FOUND_VALUE
			newYpos = DEFAULT_ROOT_Y_VALUE;
		}
		System.out.println(treeNode.toString() + " new YPos: '" + newYpos + "'.");
		return newYpos;
	}

	/**
	 * Get mapping relation consolidated.
	 * 
	 * @param refresh
	 *            if true, the underline implementation will refresh data from
	 *            user's input; otherwise, it will return what it has now, which
	 *            may not be up-to-date;
	 * @return mapping relation consolidated.
	 */
	public Mapping retrieveMappingData(boolean refresh, String mappingName) {
		if (!refresh)
			return mappingData;
		if(mappingData!=null)
		{
			mappingData= null;
		}
		List<MethodType> methodList = new ArrayList();
		// clear out the data before adding.
		if (mappingData==null)
		{
			mappingData = new Mapping();
			mappingData.setName(mappingName);
			mappingData.setVersion("1.0");
			mappingData.setDescription(mappingName);
			mappingData.setCreatedOn(Calendar.getInstance().getTime());
			mappingData.setLastUpdatedOn(Calendar.getInstance().getTime());
		}
		
// add source and destination components here PV start...
		
	    gov.nih.nci.restgen.mapping.model.Component startComp = new gov.nih.nci.restgen.mapping.model.Component();
	    startComp.setKind(KindType.XML.value());
	    startComp.setId(getNewComponentId(mappingData));
       // endComp.setLocation(schemaParser.getSchemaURI());
	    startComp.setLocation(mappingPanel.getMappingSourceFile().getPath());
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
        endComp.setLocation(mappingPanel.getMappingTargetFile().getPath());
        //endComp.setRootElement(e);
        endComp.setType(ComponentType.TARGET.value());
        if(mappingData.getComponents()!=null)
	    {
        	mappingData.getComponents().add(endComp);
	    }
// add source and destination components here PV end...		
		
		List<DefaultEdge> graphEdgeLinks = this.getMiddlePanel()
				.retrieveLinks();
		for (DefaultEdge linkEdge : graphEdgeLinks) {

			DefaultPort srcPort = (DefaultPort) linkEdge.getSource();
			String srcComponentId = "";
			String srcPath = "";
			String path = (String)getMiddlePanel().getGraph().getModel().getValue(linkEdge);
			MappableNode sourceNode = (MappableNode) srcPort.getUserObject();
			srcPath = UIHelper.getPathStringForNode((DefaultSourceTreeNode) sourceNode);
			DefaultPort trgtPort = (DefaultPort) linkEdge.getTarget();
			String tgtComponentId = "";
			String tgtPath = "";
			MappableNode targetNode = (MappableNode) trgtPort.getUserObject();
			srcComponentId = ((DefaultSourceTreeNode) sourceNode).toString();
			tgtComponentId = ((DefaultTargetTreeNode) targetNode).getOperationName();
			tgtPath = UIHelper.getPathStringForNode((DefaultTargetTreeNode) targetNode);
			addLink(mappingData, srcComponentId, srcPath,tgtComponentId, tgtPath);
			addOptions(mappingData);
			MethodType method = addMethods((DefaultSourceTreeNode) sourceNode, (DefaultTargetTreeNode) targetNode, path);
			methodList.add(method);
		    }
		    
		addResources(mappingData,methodList);
				        
		return mappingData;
	}

	// added PV start
	
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
	
	// added PV end
	
	@SuppressWarnings("static-access")
	public void addOptions(Mapping m)
	{
		
			if(m.getOptions()==null)
			{
				Options options = new Options();
				if(getMappingPanel().getOptionsPath()!=null)
				{
					options.setOutputPath(getMappingPanel().getOptionsPath());
				}
				else
				{
					options.setOutputPath("");
				}
				if(UploadWSDLAction.getServiceEndPoint()!=null)
				{
					options.setWsdlLocation(UploadWSDLAction.getServiceEndPoint());
				}
				
				if(MappingMainPanel.getTargetFileType().equals("EJB"))
				{
					options.setWrapperType(Options.EJB);
				}
				else
				{
					options.setWrapperType(Options.SOAP_SERVICE);
				}
				
				m.setOptions(options);
			}
		
		
	}
	
	
	
	 /**
     * add link to specified Mapping
     * @param m - Mapping object to load into
     * @param srcComponentId -  source component id
     * @param srcPath - source object path
     * @param tgtComponentId - target component id
     * @param tgtPath - target object path
     */
    public static void addLink(Mapping m, String srcComponentId, String srcPath, String tgtComponentId, String tgtPath) {
        Link l = new Link();
        Source src = new Source();
        src.setComponentId(srcComponentId);
        src.setId(srcPath);
        Target tgt = new Target();
        tgt.setComponentId(tgtComponentId);
        tgt.setId(tgtPath);
        l.setSource(src);
        l.setTarget(tgt);
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
    	Iterator<String> it = mappingPanel.getPOJOClassList().iterator();
    	Iterator<MethodType> mt = methodList.iterator();
    	while(it.hasNext())
    	{
    		Resource resource = new Resource();
    		resource.setName((String)it.next());
    		resourceList.add(resource);
    	}
    	
    	while(mt.hasNext())
    	{
    		MethodType mtype = (MethodType)mt.next();
    		for(int i=0;i<resourceList.size();i++)
    		{
    			Resource rsc = (Resource) resourceList.get(i);
    			System.out.println("resourcename and method type ..."+mtype.getResourceName()+"  "+rsc.getName());
    			rsc.setPath(mtype.getResourceLocation());
    			rsc.setPojoLocation(mtype.getResourcePath());
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
    	Input input = new Input();
    	Output output = new Output();
    	
    	// set the inputs and outputs
    	if(targetNode.getInputType()!=null)
    	{
    		input.setType(targetNode.getInputType());
    	}
    	else
    	{
    		input.setType("");
    	}
    	
    	if(targetNode.getOutputType()!=null)
    	{
    		input.setType(targetNode.getOutputType());
    	}
    	else
    	{
    		input.setType("");
    	}
    	
    	if(operation.getInputs()==null)
    	{
    		List<Input> inputs = new ArrayList();
    		inputs.add(input);
    		operation.setInputs(inputs);
    	}
    	else
    	{
    		operation.getInputs().add(input);
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
    		implementation.setClientType(targetNode.getEndPoint());
        	implementation.setName(targetNode.getServiceName());
        	implementation.setClasspath("");
    	}
    	else
    	{
    		implementation.setClientType(targetNode.getClientType());
    		implementation.setName(targetNode.getEJBName());
    		implementation.setClasspath(targetNode.getClassPath());
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
    	if(sourceNode.toString().equals("Create"))
    	{
    		method.setName(Method.PUT);
    	}
    	else if (sourceNode.toString().equals("Update"))
    	{
    		method.setName(Method.POST);
    	}
    	else if (sourceNode.toString().equals("Retrieve"))
    	{
    		method.setName(Method.GET);
    	}
    	else
    	{
    		method.setName(Method.DELETE);
    	}
    	method.setPathName(((DefaultSourceTreeNode) sourceNode).toString());
    	methodType.setResourceName(sourceNode.getResourceName());
    	methodType.setResourcePath(sourceNode.getResourceLocation());
    	methodType.setResourceLocation(sourceNode.getResourcePathLocation());
    	methodType.setMethod(method);
    	return methodType;
    	
    }
    
    
    
	
	public void setMappingData(Mapping mappingData, boolean isRebuild) {
		if (isGraphChanged()
				|| getMiddlePanel().getGraph().getRoots().length > 0) {// if
																		// changed,
																		// clear
																		// them
																		// up
			// clean up
			handleDeleteAll();
		}
		this.mappingData = mappingData;
		if (mappingData != null) {
			constructMappingGraph();
			// clear the flag so that from this point on, any user change on the
			// graph will be considered as change.
			setGraphChanged(false);

			// register graph selection listener
			LinkSelectionHighlighter linkSelectionHighlighter = new LinkSelectionHighlighter(
					this);
			this.getMiddlePanel().getGraph().addGraphSelectionListener(
					linkSelectionHighlighter);
		}
	}

	/**
	 * Called to render mapping (functional-box-driven or direct) after
	 * setMappingData() is called. When this is called, it assumes the source
	 * and target tree have been loaded successfully.
	 */
	private synchronized void constructMappingGraph() {
        sourceMissedLink = new ArrayList<Source>();
        targetMissedLink = new ArrayList<Target>();
        
        if (mappingData.getLinks() == null) {
        	List<Link> links = new ArrayList();
			mappingData.setLinks(links);
        
        }
		List<Link> linkList = mappingData.getLinks();
		if (linkList == null) {
			return;
		}
		
		for (Link map : linkList) {
			Source sourceMapComp = map.getSource();
			Target targetMapComp = map.getTarget();

			MappableNode sourceNode = null;
			MappableNode targetNode = null;

				sourceNode = getSourceMappableNode(sourceMapComp);
				targetNode = getTargetMappableNode(targetMapComp);
				createMapping(sourceNode, targetNode);
			//}
		}
		
	}

	private MappableNode getSourceMappableNode(Source sourceMapComp) {
		MappableNode sourceNode = null;
		String id = sourceMapComp.getId();
		sourceNode = UIHelper.constructMappableNodeObjectXmlPath(mappingPanel.getSourceTree().getModel().getRoot(), id);
        if (sourceNode == null)
        {
            if ((id.trim().equals("constant"))||
                (id.trim().equals("currentDate"))) {}
            else
            {
                sourceMissedLink.add(sourceMapComp);
                //System.out.println("UIHelper.constructMappableNodeObjectXmlPath():Could not find the data obj in the given tree rooted by '" + treeRoot + "'. path:"+ dtObjectXmlPath);
            }

        }
        return sourceNode;
	}

	private MappableNode getTargetMappableNode(Target targetMapComp) {
		MappableNode targetNode = null;
		String id = targetMapComp.getId();
		targetNode = UIHelper.constructMappableNodeObjectXmlPath(mappingPanel.getTargetTree().getModel().getRoot(), id);
        if (targetNode == null) targetMissedLink.add(targetMapComp);
        return targetNode;
	}

	/**
	 * Create mapping relation between the source and target nodes.
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @return if mapping is successfully created.
	 */
    public boolean createMapping(MappableNode sourceNode,
			MappableNode targetNode)
    {
    	
        return createMapping(sourceNode, targetNode, "");
    }
    public boolean createMapping(MappableNode sourceNode,
			MappableNode targetNode, String id) {
		boolean result = false;
		// to remember the list of cells, edges, etc. that involve in the
		// mapping.
		
		List<DefaultGraphCell> graphCellList = new ArrayList<DefaultGraphCell>();
		try {
			if (sourceNode == null || targetNode == null) {
				String msg = (sourceNode == null) ? "source node is null" : "";
				if (targetNode == null) {
					if (msg.length() > 0) {
						msg += " and ";
					}
					msg += "target node is null";
				}
				msg += "! : " + id;
                //if ((id.equals("currentDate"))||(id.equals("constant"))) {}
                //else 
                    System.out.println(msg);
                //throw new Exception(msg);
                result = false;
				return result;
			}

			if (sourceNode instanceof DefaultMutableTreeNode) {// drag from tree
																// to middle
																// panel
				/*if (targetNode instanceof FunctionBoxGraphPort) {
					result = createTreeToFunctionBoxPortMapping(sourceNode,
							(FunctionBoxGraphPort) targetNode, graphCellList);
				} else*/
				if (targetNode instanceof DefaultTargetTreeNode)// targetNode
																		// instanceof
																		// DefaultMutableTreeNode
				{// mapping between source and target tree node
					result = createTreeToTreeDirectMapping(
							(DefaultSourceTreeNode) sourceNode,
							(DefaultTargetTreeNode) targetNode, graphCellList);
				}
				else if (targetNode instanceof DefaultSourceTreeNode)// targetNode
																		// instanceof
																		// DefaultMutableTreeNode
				{// mapping between source and target tree node
					// reversed drag and drop
					result = createTreeToTreeDirectMapping(
							(DefaultSourceTreeNode) targetNode,
							(DefaultTargetTreeNode) sourceNode, graphCellList);
				}
				
				else {
				/*	System.out
							.println("Not a graph cell or tree node, what is it? '"
									+ (targetNode == null ? "null" : targetNode
											.toString()
											+ " "
											+ targetNode.getClass().getName())
									+ "'");
				*/
					
					result = createTreeToTreeDirectMapping(	(DefaultSourceTreeNode)sourceNode,(DefaultTargetTreeNode)targetNode, graphCellList);
				}
			} else {
				System.out.println(sourceNode + " is not accepted by "
						+ getClass().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Log.logException(this, e);
		}
		if (result) {// successfully mapped, add to mapping
			sourceNode.setMapStatus(true);
			targetNode.setMapStatus(true);
			setGraphChanged(true);
			// if save menu is disabled enable it!!
			MainFrameContainer mainFrame = getMappingPanel().getMainFrame();
			
			if(!mainFrame.getMainMenuBar().getDefinedMenuItem("Save").isEnabled())
			{
				mainFrame.getMainMenuBar().getDefinedMenuItem("Save").setEnabled(true);
			}
		}

        return result;
	}
    
	private boolean createTreeToTreeDirectMapping(
			DefaultSourceTreeNode sourceNode, DefaultTargetTreeNode targetNode,
			List<DefaultGraphCell> graphCellList) {
			// PV to allowing mapping of source nodes to multiple target nodes!!
			//boolean result = sourceNode.isMapped() || targetNode.isMapped();
		boolean result = targetNode.isMapped();
		if (!result) {// neither one has been mapped before
			ConnectionSet cs = new ConnectionSet();
			Map<DefaultGraphCell, AttributeMap> attributes = new Hashtable<DefaultGraphCell, AttributeMap>();
			// The X and Y position of treeNode anchor will be calculated as
			// rendering the graph cell
			int sourceYpos = calculateScrolledDistanceOnY(mappingPanel
					.getSourceScrollPane(), sourceNode, false);
			DefaultGraphCell sourceCell = new DefaultGraphCell();
			sourceCell.add(new DefaultPort(sourceNode));
			AttributeMap soucreCellAttributes = UIHelper
					.getDefaultInvisibleVertexAttribute(
							new Point(0, sourceYpos-31), true);
			attributes.put(sourceCell, soucreCellAttributes);
			// so the same for the Target side.
			int targetYpos = calculateScrolledDistanceOnY(mappingPanel
					.getTargetScrollPane(), targetNode, false);
			DefaultGraphCell targetCell = new DefaultGraphCell();
			targetCell.add(new DefaultPort(targetNode));
			AttributeMap targetCellAttributes = UIHelper
					.getDefaultInvisibleVertexAttribute(new Point(
							getMaximalXValueOnPane(), targetYpos+210), false);
			attributes.put(targetCell, targetCellAttributes);
			// process the edge
			DefaultEdge linkEdge = new MappingGraphLink();
			Color linkColor=DEFAULT_MAPPING_LINK_COLOR;
			//AttributeMap lineStyle = UIHelper
				//	.getDefaultUnmovableEdgeStyle(((ElementMetaLoader.MyTreeObject) sourceNode
					//		.getUserObject()).getUserObject());
			AttributeMap lineStyle = UIHelper.getDefaultUnmovableMappingEdgeStyle(linkColor);
			attributes.put(linkEdge, lineStyle);
			// return back those being affected.
			graphCellList.add(sourceCell);
			graphCellList.add(targetCell);
			graphCellList.add(linkEdge);
			cs.connect(linkEdge, sourceCell.getChildAt(0), targetCell
					.getChildAt(0));
			getMiddlePanel().getGraph().getGraphLayoutCache().insert(
					new Object[] { sourceCell, targetCell, linkEdge },
					attributes, cs, null, null);
			getMiddlePanel().getGraph().getGraphLayoutCache()
					.setSelectsAllInsertedCells(false);
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	private int getMaximalXValueOnPane() {
		int visibleWidth = (int) getMiddlePanel().getGraphScrollPane()
				.getVisibleRect().getWidth();
		return visibleWidth - 20;
	}

	/**
	 * @return the mappingPanel
	 */
	public MappingMainPanel getMappingPanel() {
		return mappingPanel;
	}

	public MappingMiddlePanel getMiddlePanel() {
		return mappingPanel.getMiddlePanel();
	}

	public PropertiesSwitchController getPropertiesSwitchController() {
		if (propertiesSwitchController == null) {
			propertiesSwitchController = new DefaultPropertiesSwitchController();// graph);
		}
		return propertiesSwitchController; 
	}

	/**
	 * Handle the deletion of graph cells on the middle panel.
	 */
	public synchronized void deleteGraphLink() {
		Object[] cells = getMiddlePanel().getGraph().getSelectionCells();
		removeCells(cells, true);
		setGraphChanged(true);
		// if save menu is disabled enable it!!
					MainFrameContainer mainFrame = getMappingPanel().getMainFrame();
					
					if(!mainFrame.getMainMenuBar().getDefinedMenuItem("Save").isEnabled())
					{
						mainFrame.getMainMenuBar().getDefinedMenuItem("Save").setEnabled(true);
					}
	}

	/**
	 * Handle the deletion of all graph cells on the middle panel.
	 */
	public synchronized void handleDeleteAll() {
		// clean up
		Object[] cells = DefaultGraphModel.getDescendants(
				getMiddlePanel().getGraph().getModel(),
				getMiddlePanel().getGraph().getRoots()).toArray();
		// call to remove all cells
		removeCells(cells, false);
		setGraphChanged(true);
		// if save menu is disabled enable it!!
		MainFrameContainer mainFrame = getMappingPanel().getMainFrame();
		if(!mainFrame.getMainMenuBar().getDefinedMenuItem("Save").isEnabled());
		{
			mainFrame.getMainMenuBar().getDefinedMenuItem("Save").setEnabled(true);
		}
	}

	public void removeCells(Object[] cells, boolean findAssociatedCells) {
		unmapCells(cells);
		// repaint the source and target tree panel if a functionBox is deleted
		boolean repaintSourceTarget = false;

		cells = DefaultGraphModel.getDescendants(
				getMiddlePanel().getGraph().getModel(), cells).toArray();
		if (!findAssociatedCells) {// no need to find associated cells, so
									// directly remove them.
			getMiddlePanel().getGraph().getGraphLayoutCache().remove(cells,
					true, true);
			return;
		}
		List cellSelectionList = new ArrayList(Arrays.asList(cells));

		// reverse back in case some additions are added by calling
		// comp.findMatchedCell() above.
		cells = cellSelectionList.toArray();
		if (cells != null) {
			cells = DefaultGraphModel.getDescendants(
					getMiddlePanel().getGraph().getModel(), cells).toArray();
			// graph.getModel().remove(cells);
			getMiddlePanel().getGraph().getGraphLayoutCache().remove(cells,
					true, true);
		}

		// repaint the source and target scrollPanes
		if (repaintSourceTarget) {
			mappingPanel.getSourceScrollPane().repaint();
			mappingPanel.getTargetScrollPane().repaint();
		}
	}

	private void unmapCells(Object[] cells) {
		// System.out.println("middlePanel kind: " + middlePanel.getKind() );
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == null || !(cells[i] instanceof DefaultEdge))
				continue;
			DefaultEdge linkEdge = (DefaultEdge) cells[i];
			DefaultPort srcPort = (DefaultPort) linkEdge.getSource();
			//if (srcPort instanceof FunctionBoxGraphPort)
			//	((FunctionBoxGraphPort) srcPort).setMapStatus(false);
			//else {
				MappableNode sourceNode = (MappableNode) srcPort
						.getUserObject();
				sourceNode.setMapStatus(false);
			//}
			DefaultPort trgtPort = (DefaultPort) linkEdge.getTarget();
			//if (trgtPort instanceof FunctionBoxGraphPort)
			//	((FunctionBoxGraphPort) trgtPort).setMapStatus(false);
			//else {
				MappableNode targetNode = (MappableNode) trgtPort
						.getUserObject();
				targetNode.setMapStatus(false);
			//}
		}
	}

	public boolean isGraphChanged() {
		return isGraphChanged;
	}

	/**
	 * @return the graphSelected
	 */
	public boolean isGraphSelected() {
		return graphSelected;
	}

	/**
	 * Explicitly set the value.
	 * 
	 * @param newValue
	 */
	public void setGraphChanged(boolean newValue) {
		isGraphChanged = newValue;
		if (isGraphChanged) {
			// update source and target tree
			mappingPanel.getTargetScrollPane().repaint();
			mappingPanel.getSourceScrollPane().repaint();
		}
	}

	/**
	 * @param graphSelected
	 *            the graphSelected to set
	 */
	public void setGraphSelected(boolean graphSelected) {
		this.graphSelected = graphSelected;
	}

	/**
	 * @param mappingPanel
	 *            the mappingPanel to set
	 */
	public void setMappingPanel(MappingMainPanel mappingPanel) {
		this.mappingPanel = mappingPanel;
	}

    public List<Source> getSourceMissedLink()
    {
        return sourceMissedLink;
    }
    public List<Target> getTargetMissedLink()
    {
        return targetMissedLink;
    }
}
/**
 * HISTORY: $Log: not supported by cvs2svn $ HISTORY: Revision 1.13 2009/11/03
 * 18:33:37 wangeug HISTORY: clean codes: add JScroll panel as instance variable
 * HISTORY: HISTORY: Revision 1.12 2009/11/03 18:13:21 wangeug HISTORY: clean
 * codes: keep MiddlePanelJGraphController only with MiddleMappingPanel HISTORY:
 * HISTORY: Revision 1.11 2009/11/02 14:54:53 wangeug HISTORY: clean codes
 * HISTORY: HISTORY: Revision 1.10 2009/10/30 14:45:46 wangeug HISTORY: simplify
 * code: only respond to link highter HISTORY: HISTORY: Revision 1.9 2009/10/28
 * 15:03:11 wangeug HISTORY: clean codes HISTORY: HISTORY: Revision 1.8
 * 2009/10/27 18:22:44 wangeug HISTORY: hook property panel with tree nodes
 * HISTORY: HISTORY: Revision 1.7 2009/01/02 16:05:17 linc HISTORY: updated.
 * HISTORY: HISTORY: Revision 1.6 2008/12/29 22:18:18 linc HISTORY: function UI
 * added. HISTORY: HISTORY: Revision 1.5 2008/12/10 15:43:02 linc HISTORY: Fixed
 * component id generator and delete link. HISTORY: HISTORY: Revision 1.4
 * 2008/12/09 19:04:17 linc HISTORY: First GUI release HISTORY: HISTORY:
 * Revision 1.3 2008/12/04 21:34:20 linc HISTORY: Drap and Drop support with new
 * Swing. HISTORY: HISTORY: Revision 1.2 2008/12/03 20:46:14 linc HISTORY: UI
 * update. HISTORY: HISTORY: Revision 1.1 2008/10/30 16:02:14 linc HISTORY:
 * updated. HISTORY:
 */
