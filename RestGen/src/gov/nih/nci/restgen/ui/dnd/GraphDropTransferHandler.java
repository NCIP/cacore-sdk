/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.ui.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;


import gov.nih.nci.restgen.ui.common.MappableNode;
import gov.nih.nci.restgen.ui.mapping.MappingMiddlePanel;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;

public class GraphDropTransferHandler extends CommonTransferHandler {
private static DataFlavor[] acceptableFlavors =new DataFlavor[]{TreeTransferableNode.mutableTreeNodeFlavor};

private static  final List<DataFlavor> acceptableFlavorsList = Arrays.asList( TreeTransferableNode.flavors );

/* (non-Javadoc)
 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.TransferSupport)
 */
@Override
public boolean importData(TransferSupport info) 
{
	Object transferableObject;
    try {
    	transferableObject = info.getTransferable().getTransferData(TreeTransferableNode.mutableTreeNodeFlavor);
    } catch (UnsupportedFlavorException e) {
    	e.printStackTrace();
        return false;
    } catch (IOException e) {
    	e.printStackTrace();
        return false;
    }
   
    if (transferableObject instanceof DefaultMutableTreeNode)
    {
    	DefaultMutableTreeNode treeNodeTransfered=(DefaultMutableTreeNode)transferableObject;
    	JGraph middlePanelGraph=(JGraph)info.getComponent();
    	MappingMiddlePanel rootMappingPanel=(MappingMiddlePanel)retrieveRootMappingPanel(middlePanelGraph);
    	
    	Point2D pDrop=info.getDropLocation().getDropPoint();
    	    	
    	boolean inputPort=false;
    	if (treeNodeTransfered instanceof DefaultSourceTreeNode)
    		inputPort=true;
    	
    	//create a link between tree node and function port
    	/*TreePath pathSelected=rootMappingPanel.getMappingPanel().getTargetTree().getSelectionPath();    	
    	if (inputPort)
    		pathSelected=rootMappingPanel.getMappingPanel().getSourceTree().getSelectionPath();

		DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) pathSelected.getLastPathComponent();   	 
    	boolean isSucess= rootMappingPanel.getGraphController().createMapping((MappableNode)nodeSelected,fPort);
    	if (isSucess)
    	{
    		if (inputPort)
    			rootMappingPanel.getMappingPanel().getSourceTree().repaint();
    		else
    			rootMappingPanel.getMappingPanel().getTargetTree().repaint();
    	}    	
    	return isSucess;*/
    }
    return false;
}

private JComponent retrieveRootMappingPanel(JComponent childComp)
{
	JComponent rtnComp=null;
	while (childComp.getParent()!=null)
	{
		rtnComp=(JComponent)childComp.getParent();
		if (rtnComp instanceof MappingMiddlePanel)
			return rtnComp;
		childComp=rtnComp;
	}
	return rtnComp;
}

/* (non-Javadoc)
 * @see javax.swing.TransferHandler#canImport(javax.swing.TransferHandler.TransferSupport)
 */
@Override
public boolean canImport(TransferSupport info) {
	if (info.getTransferable().isDataFlavorSupported(TreeTransferableNode.mutableTreeNodeFlavor))
		return true;
    return super.canImport(info);
}

}
