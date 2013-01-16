package gov.nih.nci.restgen.ui.tree;



import gov.nih.nci.restgen.core.Mapping;
import gov.nih.nci.restgen.ui.actions.DeleteNodeAction;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
 

public class TreeMouseAdapter extends MouseAdapter {
	protected MainFrameContainer mainFrame = null;
	protected JTree tree = null;
	
	public TreeMouseAdapter(MainFrameContainer mainFrame,JTree tree)
	{
		this.mainFrame = mainFrame;
		this.tree = tree;
		
	}
	
    /**
	 * Invoked when the mouse has been clicked on a component.
	 */
	public void mousePressed(MouseEvent e)
	{
		if (SwingUtilities.isRightMouseButton(e))
		{         	
			JTree slctTree=(JTree)e.getSource();
            //MappingBaseTree slctTree=(MappingBaseTree)e.getSource();
            TreePath slctedPath=slctTree.getSelectionPath();
			if (slctedPath==null)
				return;
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) slctedPath.getLastPathComponent();
			
			Container parentC = e.getComponent().getParent();
			
			while ( !(parentC instanceof MappingMainPanel))
			{
				parentC=parentC.getParent();
			}
	
			// Create PopupMenu for the Cell
			if(treeNode.getChildCount()>0)
			{
				JPopupMenu menu = createTreePopupMenu(treeNode, slctTree, (MappingMainPanel)parentC);
				menu.show(e.getComponent(), e.getX(), e.getY());
			}
						
			
		}
	}

	/**
	 * Set popup menu for the nodes of source tree 
	 * @return sourceNodePopup
	 */
	private JPopupMenu createTreePopupMenu(DefaultMutableTreeNode treeNode, JTree tree, MappingMainPanel parentPanel)
	{
        Mapping mappingData = parentPanel.getGraphController().retrieveMappingData(false);
        Object obj = treeNode.getUserObject();
        JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.add(new JMenuItem(new DeleteNodeAction(mainFrame,tree,treeNode)));
	    return popupMenu;
	
	}
	
}
