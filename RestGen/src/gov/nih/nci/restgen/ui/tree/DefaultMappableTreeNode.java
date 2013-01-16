/**
 * The content of this file is subject to the caAdapter Software License (the "License").  
 * A copy of the License is available at:
 * [caAdapter CVS home directory]\etc\license\caAdapter_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent
 * /docs/caAdapter_License
 */
package gov.nih.nci.restgen.ui.tree;


import gov.nih.nci.restgen.ui.common.MappableNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * This class is to provide the basic implementation of MappableNode on a Tree Node,
 * overriding equals() and hashCode() methods, and other functions to facilitate usage of
 * various occassions.
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 *
 */
public class DefaultMappableTreeNode extends DefaultMutableTreeNode implements MappableNode
{
	private boolean mapFlag = false;

	/**
	 * Creates a tree node with no parent, no children, but which allows
	 * children, and initializes it with the specified user object.
	 *
	 * @param userObject an Object provided by the user that constitutes
	 *                   the node's data
	 */
	public DefaultMappableTreeNode(Object userObject)
	{
		super(userObject);
	}

	/**
	 * Creates a tree node with no parent, no children, initialized with
	 * the specified user object, and that allows children only if
	 * specified.
	 *
	 * @param userObject     an Object provided by the user that constitutes
	 *                       the node's data
	 * @param allowsChildren if true, the node is allowed to have child
	 *                       nodes -- otherwise, it is always a leaf node
	 */
	public DefaultMappableTreeNode(Object userObject, boolean allowsChildren)
	{
		super(userObject, allowsChildren);
	}

	public void setMapStatus(boolean newValue)
	{
		mapFlag = newValue;
	}

	public boolean isMapped()
	{
		return mapFlag;
	}

}
/**
 * HISTORY: $Log: not supported by cvs2svn $
 * HISTORY: Revision 1.1  2008/10/27 20:06:30  linc
 * HISTORY: GUI first add.
 * HISTORY:
 */

