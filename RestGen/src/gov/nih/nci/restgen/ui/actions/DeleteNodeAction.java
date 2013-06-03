/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.actions;


import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultMappableTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultPort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

/**
 * Currently play as place holder to define the general look and feel of a "Delete Node..." action.
 * Descendant class will provide concrete implementation of the action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class DeleteNodeAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.DELETENODE;
	protected static final Character COMMAND_MNEMONIC = new Character('D');
	//public static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("fileSaveAs.gif"));
    public static final ImageIcon IMAGE_ICON = null;
	protected transient MainFrameContainer mainFrame = null;
	private DefaultMutableTreeNode treeNode = null;
	private JTree tree = null;
	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public DeleteNodeAction(MainFrameContainer mainFrame, JTree tree, DefaultMutableTreeNode treeNode)
	{
		this(COMMAND_NAME,null);
		this.mainFrame = mainFrame;
		this.treeNode = treeNode;
		this.tree = tree;
		
	}



	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public DeleteNodeAction(String name, Icon icon)
	{
		super(name, icon);
		
		setAdditionalAttributes();
	}

	/**
	 * provide descendant class to override.
	 */
	protected void setAdditionalAttributes()
	{
		setMnemonic(COMMAND_MNEMONIC);

	}

		
	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e) throws Exception
	{
		
		//System.out.println("Delete node clicked.......");
       if (treeNode==null)
       {
    	   //System.out.println("Tree node null returning.......");
			return false;
       }
       
       DefaultEdge [] linkEdge = null;
       DefaultEdge linkEdgeTemp = null;
       DefaultPort [] srcPort = null;
       DefaultPort srcPortTemp = null;
       DefaultPort [] tgtPort = null;
       DefaultPort tgtPortTemp = null;
	for (Object child:MainFrame.getMappingMainPanel().getMiddlePanel().getGraph().getRoots())
	{
			if (child instanceof DefaultEdge)
			{
				linkEdge = new DefaultEdge[2];
				srcPort = new DefaultPort[1];
				tgtPort = new DefaultPort[1];
				linkEdgeTemp = (DefaultEdge)child;
				//linkEdge[0] = linkEdgeTemp;
				srcPortTemp=(DefaultPort)linkEdgeTemp.getSource();
				srcPort[0] = srcPortTemp;
				tgtPortTemp=(DefaultPort)linkEdgeTemp.getTarget();
				tgtPort[0] = tgtPortTemp;
				Iterator edges = srcPortTemp.getEdges().iterator();
				int i=0;
                while (edges.hasNext())
                {
                	linkEdge[i] = (DefaultEdge)edges.next();
                	i++;
                }

				Object sourceNode = srcPortTemp.getUserObject();
				if(sourceNode instanceof DefaultMutableTreeNode)
				{
					DefaultMutableTreeNode srcTreeNode = (DefaultMutableTreeNode)sourceNode;
					if(srcTreeNode.getParent().equals(treeNode))
					{
						
						MainFrame.getMappingMainPanel().getMiddlePanel().getGraphController().removeCells(linkEdge,true);
						//MainFrame.getMappingMainPanel().getMiddlePanel().getGraphController().removeCells(tgtPort,true);
						MainFrame.getMappingMainPanel().getTargetScrollPane().repaint();
					}
				}
				
			}
		
	}
	//System.out.println("Before Tree node removal.......");
	 if(treeNode.getParent()!=null)
	 {
		 treeNode.removeFromParent();
	 }
	 else
	 {
		 tree.removeAll();
	 }
	 // remove the resource from the list
	 MainFrame.getMappingMainPanel().getPOJOClassList().remove(treeNode.toString());
	 //System.out.println("TREENODE STR..."+treeNode.toString());
	 //
     tree.updateUI();
     //
        if(MainFrame.getMappingMainPanel().getMappingTargetFile()!=null)
        {
        	Mapping mappingData = MainFrame.getMappingMainPanel().getMiddlePanel().getGraphController().retrieveMappingData(true,"Temp");
        	if(mappingData!=null)
        	{
        		MainFrame.getMappingMainPanel().getMiddlePanel().getGraphController().setMappingData(mappingData, true);
        		MainFrame.getMappingMainPanel().getMiddlePanel().setMappingNamesforLinkInGraph(mappingData);
        	}
		
        }
       return true;
	}

	/**
	 * Return the associated UI component.
	 *
	 * @return the associated UI component.
	 */
	protected Component getAssociatedUIComponent()
	{
		return mainFrame.getAssociatedUIComponent();
	}

}

/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.1  2008/12/09 19:04:17  linc
 * HISTORY      : First GUI release
 * HISTORY      :
 * HISTORY      : Revision 1.1  2008/12/03 20:46:14  linc
 * HISTORY      : UI update.
 * HISTORY      :
 */
