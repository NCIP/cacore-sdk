/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.tree;

/**
 * This class extends the default mutable tree node as the tree node used to
 * construct Source Tree for left-pane MetaData in mapping panel or
 * other occurrences related to CSV or other type of metadata loaders in the whole UI arena.
 * One of primary reasons to have a distinct class is for differentiation purpose for future use of instanceof, for example.
 * 
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 *
 */
public class DefaultSourceTreeNode extends DefaultMappableTreeNode
{
   

	public DefaultSourceTreeNode(Object userObject)
	{
		super(userObject);
	}
	public DefaultSourceTreeNode(Object userObject, boolean allowsChildren)
	{
		super(userObject, allowsChildren);
	}
}
/**
 * HISTORY: $Log: not supported by cvs2svn $
 */

