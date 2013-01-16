/**
 * The content of this file is subject to the caAdapter Software License (the "License").  
 * A copy of the License is available at:
 * [caAdapter CVS home directory]\etc\license\caAdapter_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent
 * /docs/caAdapter_License
 */
package gov.nih.nci.restgen.ui.jgraph;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.ParentMap;

//import gov.nih.nci.cbiit.cmts.mapping.MappingFactory;
import gov.nih.nci.restgen.core.Component;
import gov.nih.nci.restgen.core.ComponentType;
import gov.nih.nci.restgen.core.KindType;
import gov.nih.nci.restgen.core.LinkType;
import gov.nih.nci.restgen.core.LinkpointType;
import gov.nih.nci.restgen.core.Mapping;
import gov.nih.nci.restgen.core.ViewType;
import gov.nih.nci.restgen.core.Mapping.Components;
import gov.nih.nci.restgen.core.Mapping.Links;
import gov.nih.nci.restgen.ui.common.MappableNode;
import gov.nih.nci.restgen.ui.common.UIHelper;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;
import gov.nih.nci.restgen.ui.mapping.MappingMiddlePanel;
import gov.nih.nci.restgen.ui.properties.DefaultPropertiesSwitchController;
import gov.nih.nci.restgen.ui.properties.PropertiesSwitchController;
import gov.nih.nci.restgen.ui.tree.DefaultMappableTreeNode;
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
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;
import java.util.Arrays;
import java.util.List;

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

    private List<LinkpointType> sourceMissedLink = new ArrayList<LinkpointType>();
    private List<LinkpointType> targetMissedLink = new ArrayList<LinkpointType>();

    public MiddlePanelJGraphController(MappingMainPanel mappingPan) {
		mappingPanel = mappingPan;
	}

	/*public boolean addFunction(FunctionDef function, Point2D startPoint) {
		if (startPoint == null) {// set to default value.
			startPoint = new Point(25, 25);
		}

		ViewType functionViewtype = new ViewType();

		functionViewtype.setX(BigInteger.valueOf((int) startPoint.getX()));
		functionViewtype.setY(BigInteger.valueOf((int) startPoint.getY()));

		FunctionBoxGraphCell functionBox = FunctionBoxUsageManager
				.getInstance().createOneFunctionBoxGraphCell(function,
						functionViewtype, mappingPanel.getRootContainer());
		if (functionBox == null) {
			return false;
		}
		if (functionBox.getInputElements().isEmpty())
		{
			functionBox.getViewMeta().setHight(BigInteger.valueOf(50));
			functionBox.getViewMeta().setWidth(BigInteger.valueOf(100));
		}
		else
		{
			int viewH=functionBox.getInputElements().size()*20 +28;		
			functionBox.getViewMeta().setHight(BigInteger.valueOf(viewH));
		}
		return addFunctionInstance(functionBox);
	}

	/*private boolean addFunctionInstance(FunctionBoxGraphCell functionInstance) {
		FunctionDef function = functionInstance.getFunctionDef();
		ViewType viewInfo = functionInstance.getViewMeta();
		Point2D startPoint = new Point(viewInfo.getX().intValue() < 0 ? 25
				: viewInfo.getX().intValue(),
				viewInfo.getY().intValue() < 0 ? 25 : viewInfo.getY()
						.intValue());
		// Construct Vertex with Label
		Dimension functionBoxDimension = new Dimension(viewInfo.getWidth()
				.intValue(), viewInfo.getHight().intValue());
		// Create a Map that holds the attributes for the functionBoxVertex
		// functionBoxVertex.getAttributes().applyMap(createCellAttributes(startPoint,
		// functionBoxDimension));
		// Color backGroundColor = viewInfo.getColor() == null ?
		// UIHelper.DEFAULT_VERTEX_COLOR : viewInfo.getColor();
		Color backGroundColor = UIHelper.DEFAULT_VERTEX_COLOR;
		Map funcBoxAttrbutes = UIHelper.createBounds(new AttributeMap(),
				startPoint, functionBoxDimension, backGroundColor, true);
		GraphConstants.setSizeable(funcBoxAttrbutes, true);
		// Insert the functionBoxVertex (including child port and attributes)
		Map portAttributes = new Hashtable();
		ParentMap parentMap = new ParentMap();
		int numOfInputs = functionInstance.getInputElements().size();
		int numOfOutputs = functionInstance.getOutputElements().size();
		int maximumPorts = Math.max(numOfInputs, numOfOutputs);
		addFunctionGraphPorts(function, portAttributes, parentMap,
				functionInstance, funcBoxAttrbutes, numOfInputs, UIHelper
						.getDefaultFunctionalBoxInputOrientation(),
				maximumPorts);
		addFunctionGraphPorts(function, portAttributes, parentMap,
				functionInstance, funcBoxAttrbutes, numOfOutputs, UIHelper
						.getDefaultFunctionalBoxOutputOrientation(),
				maximumPorts);
		// Create a Map that holds the attributes for the Vertex
		functionInstance.getAttributes().applyMap(funcBoxAttrbutes);
		getMiddlePanel().getGraph().getGraphLayoutCache().insert(
				functionInstance);
		getMiddlePanel().getGraph().getGraphLayoutCache().insert(
				functionInstance.getChildren().toArray(), portAttributes, null,
				parentMap, null);
		setGraphChanged(true);
        
        return true;
		// EDIT does not work!
		// graph.getGraphLayoutCache().edit(functionBoxVertex.getChildren().toArray(),
		// portAttributes);
		// graph.getGraphLayoutCache().edit(portAttributes);
		// graph.getGraphLayoutCache().insert(new Object[]{functionBoxVertex},
		// funcBoxAttrbutes, null, parentMap, null);
		// Log.logInfo(this, "functionBoxVertex.getChildren().size(): " +
		// functionBoxVertex.getChildren().size());
		// this.getGraphLayoutCache().insert(functionBoxVertex.getChildren().toArray(),
		// portAttributes, null, parentMap);
		// following received java.lang.ClassCastException
		// graph.getModel().insert(new Object[]{functionBoxVertex},
		// funcBoxAttrbutes, null, null, null);
		// graph.getModel().edit(portAttributes, null, null, null);
	}*/



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
	public Mapping retrieveMappingData(boolean refresh) {
		if (!refresh)
			return mappingData;

		// clear out the data before adding.
		if (mappingData==null)
		{
			mappingData = new Mapping();
		}
		if (mappingData.getLinks() == null) {
			mappingData.setLinks(new Mapping.Links());
		} else {
			mappingData.getLinks().getLink().clear();
		}
		if (mappingData.getTags()==null)
			mappingData.setTags(new Mapping.Tags());
		
// add source and destination components here PV start...
		
	    Component startComp = new Component();
	    startComp.setKind(KindType.XML);
	    startComp.setId(getNewComponentId(mappingData));
       // endComp.setLocation(schemaParser.getSchemaURI());
	    startComp.setLocation("C:\\xyz\\abc");
        //endComp.setRootElement(e);
	    startComp.setType(ComponentType.SOURCE);
        mappingData.getComponents().getComponent().add(startComp);
        
        
        Component endComp = new Component();
        endComp.setKind(KindType.XML);
        endComp.setId(getNewComponentId(mappingData));
       // endComp.setLocation(schemaParser.getSchemaURI());
        endComp.setLocation("C:\\xyz\\abc");
        //endComp.setRootElement(e);
        endComp.setType(ComponentType.TARGET);
        mappingData.getComponents().getComponent().add(endComp);
        
// add source and destination components here PV end...		
		
		List<DefaultEdge> graphEdgeLinks = this.getMiddlePanel()
				.retrieveLinks();
		for (DefaultEdge linkEdge : graphEdgeLinks) {

			DefaultPort srcPort = (DefaultPort) linkEdge.getSource();
			String srcComponentId = "";
			String srcPath = "";
			MappableNode sourceNode = (MappableNode) srcPort
						.getUserObject();
//				srcComponentId = ((Component) ((ElementMetaLoader.MyTreeObject) ((DefaultMutableTreeNode) sourceNode)
	//					.getUserObject()).getRootObject()).getId();
				srcComponentId = sourceNode.toString();
				srcPath = UIHelper
						.getPathStringForNode((DefaultSourceTreeNode) sourceNode);
			

			DefaultPort trgtPort = (DefaultPort) linkEdge.getTarget();
			String tgtComponentId = "";
			String tgtPath = "";
			MappableNode targetNode = (MappableNode) trgtPort
						.getUserObject();
				//tgtComponentId = ((Component) ((ElementMetaLoader.MyTreeObject) ((DefaultMutableTreeNode) targetNode)
					//	.getUserObject()).getRootObject()).getId();
				//tgtComponentId = targetNode.toString();
				tgtPath = UIHelper
						.getPathStringForNode((DefaultTargetTreeNode) targetNode);
		
			addLink(mappingData, srcComponentId, srcPath,tgtComponentId, tgtPath);
		}

		        
		return mappingData;
	}

	// added PV start
	
	private static String getNewComponentId(Mapping m){
        if(m.getComponents() == null)
            m.setComponents(new Components());
        int num = 0;
        for(Component c:m.getComponents().getComponent()){
            int tmp = -1;
            try{
                tmp = Integer.parseInt(c.getId());
            }catch(Exception ignored){}
            if(tmp>=num)
                num = tmp+1;
        }
        return String.valueOf(num);
    }
	
	// added PV end
	
	
	 /**
     * add link to specified Mapping
     * @param m - Mapping object to load into
     * @param srcComponentId -  source component id
     * @param srcPath - source object path
     * @param tgtComponentId - target component id
     * @param tgtPath - target object path
     */
    public static void addLink(Mapping m, String srcComponentId, String srcPath, String tgtComponentId, String tgtPath) {
        LinkType l = new LinkType();
        LinkpointType lp = new LinkpointType();
        lp.setComponentid(srcComponentId);
        lp.setId(srcPath);
        l.setSource(lp);
        lp = new LinkpointType();
        lp.setComponentid(tgtComponentId);
        lp.setId(tgtPath);
        l.setTarget(lp);
        if(m.getLinks() == null) m.setLinks(new Links());
        m.getLinks().getLink().add(l);
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
        sourceMissedLink = new ArrayList<LinkpointType>();
        targetMissedLink = new ArrayList<LinkpointType>();
        //System.out.println("CCCCC mapping data == constructMappingGraph()");
        if (mappingData.getLinks() == null) {
			mappingData.setLinks(new Mapping.Links());
            //System.out.println("CCCCC mapping link data is null.");
        }
		List<LinkType> linkList = mappingData.getLinks().getLink();
		if (linkList == null) {
			return;
		}
		// render functional box first
		// Log.logInfo(this, "Total function component: '" + functionSize +
		// "'.");
		// render map second
		for (LinkType map : linkList) {
			LinkpointType sourceMapComp = map.getSource();
			LinkpointType targetMapComp = map.getTarget();

			MappableNode sourceNode = null;
			MappableNode targetNode = null;

				sourceNode = getSourceMappableNode(sourceMapComp);
				targetNode = getTargetMappableNode(targetMapComp);
				createMapping(sourceNode, targetNode);
			//}
		}
	}

	private MappableNode getSourceMappableNode(LinkpointType sourceMapComp) {
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

	private MappableNode getTargetMappableNode(LinkpointType targetMapComp) {
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

	/*private boolean createTreeToFunctionBoxPortMapping(
			MappableNode mappableNode, FunctionBoxGraphPort port,
			List<DefaultGraphCell> graphCellList) {
		boolean isDataFromSourceTree = false;
		if (mappingPanel.getSourceTree().getSelectionPath() != null)
			isDataFromSourceTree = true;

		AttributeMap treeCellAttributes = null;
		DefaultGraphCell treeNodeCell = new DefaultGraphCell();
		treeNodeCell.add(new DefaultPort(mappableNode));
		if (isDataFromSourceTree) {
			int treeNodeYpos = calculateScrolledDistanceOnY(mappingPanel
					.getSourceScrollPane(),
					(DefaultMutableTreeNode) mappableNode, false);
			treeCellAttributes = UIHelper.getDefaultInvisibleVertexAttribute(
					new Point(0, treeNodeYpos), true);
		} else {
			int treeNodeYpos = calculateScrolledDistanceOnY(mappingPanel
					.getTargetScrollPane(),
					(DefaultMutableTreeNode) mappableNode, false);
			treeCellAttributes = UIHelper.getDefaultInvisibleVertexAttribute(
					new Point(getMaximalXValueOnPane(), treeNodeYpos), false);
		}

		ConnectionSet cs = new ConnectionSet();
		Map<DefaultGraphCell, AttributeMap> attributes = new Hashtable<DefaultGraphCell, AttributeMap>();
		DefaultEdge linkEdge = new MappingGraphLink();
		cs.connect(linkEdge, treeNodeCell.getChildAt(0), port);
		attributes.put(treeNodeCell, treeCellAttributes);
		attributes.put(port, port.getAttributes());

		graphCellList.add(treeNodeCell);
		graphCellList.add(port);
		graphCellList.add(linkEdge);
		getMiddlePanel().getGraph().getGraphLayoutCache().insert(
				new Object[] { treeNodeCell, port, linkEdge }, attributes, cs,
				null, null);
		return true;
	}*/

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
	 * Called by MiddlePanelMarqueeHandler Insert a new Edge between source
	 * function port to and target function port
	 */
	public boolean linkFunctionPortToFunctionPort(DefaultPort source,
			DefaultPort target) {
		if (!source.getEdges().isEmpty() || !target.getEdges().isEmpty()) {
			StringBuffer msg = new StringBuffer();
			if (!source.getEdges().isEmpty()) {
				msg.append("This source port number is being used. Input again another port number.");
			}
			if (!target.getEdges().isEmpty()) {
				if (msg.length() > 0) {
					msg.append("\n");
				}
				msg.append("This target port number is being used. Input again another port number.");
			}
			JOptionPane.showMessageDialog(getMiddlePanel().getRootPane()
					.getParent(), msg.toString(), "Mapping Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Log.logInfo(this, getClass().getName() +
		// " will link source and target port.");
		// Construct Edge with no label
		DefaultEdge edge = new MappingGraphLink();
		edge.setSource(source);
		edge.setTarget(target);
		AttributeMap lineStyle = UIHelper.getDefaultUnmovableEdgeStyle(source);
		if (getMiddlePanel().getGraph().getModel().acceptsSource(edge, source)
				&& getMiddlePanel().getGraph().getModel().acceptsTarget(edge,
						target)) {
			// Create a Map that holds the attributes for the edge
			edge.getAttributes().applyMap(lineStyle);
			MappableNode sourceNode = (MappableNode) source;// getMappableNodeThroughPort(source);
			MappableNode targetNode = (MappableNode) target;// getMappableNodeThroughPort(target);
			if (sourceNode == null || targetNode == null) {
				StringBuffer msg = new StringBuffer(
						"Cannot find mappable source or target node.");
				JOptionPane.showMessageDialog(getMiddlePanel().getRootPane()
						.getParent(), msg.toString(), "Mapping Error",
						JOptionPane.ERROR_MESSAGE);
			}
			// Insert the Edge and its Attributes
			getMiddlePanel().getGraph().getGraphLayoutCache().insertEdge(edge,
					source, target);
			setGraphChanged(true);
			return true;
		} else {
			List reasonList = ((MiddlePanelGraphModel) getMiddlePanel()
					.getGraph().getModel()).getNotAcceptableReasonList();
			JOptionPane.showMessageDialog(getMiddlePanel().getGraph()
					.getRootPane().getParent(), reasonList
					.toArray(new Object[0]), "Mapping Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Handle the deletion of graph cells on the middle panel.
	 */
	public synchronized void deleteGraphLink() {
		Object[] cells = getMiddlePanel().getGraph().getSelectionCells();
		removeCells(cells, true);
		setGraphChanged(true);
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
	}

	private void removeCells(Object[] cells, boolean findAssociatedCells) {
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

    public List<LinkpointType> getSourceMissedLink()
    {
        return sourceMissedLink;
    }
    public List<LinkpointType> getTargetMissedLink()
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
