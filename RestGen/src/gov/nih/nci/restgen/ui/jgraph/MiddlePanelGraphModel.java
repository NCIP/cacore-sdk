/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.jgraph;


import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.Edge;

//import gov.nih.nci.cbiit.cmts.ui.util.GeneralUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines a custom model that does not allow Self-References and also remember additional
 * link data to support graph.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 *
 */
public class MiddlePanelGraphModel extends DefaultGraphModel
{
	private List<String> reasonList;
	/**
	 * Override Superclass Method to provide additional checking.
	 */
	public boolean acceptsSource(Object edge, Object port)
	{
		reasonList = new ArrayList<String>();
		Object target = ((Edge) edge).getTarget();
		boolean result = true;
		// Source only Valid if not Equal Target
		boolean lineResult = (target != port);
		if(!lineResult)
		{
			reasonList.add("The source cannot be same with the target.");
			result = lineResult;
		}
		if(target instanceof DefaultPort && port instanceof DefaultPort)
		{
			//lineResult = !GeneralUtilities.areEqual(((DefaultPort)target).getParent(), ((DefaultPort) port).getParent());
			//if(!lineResult)
			//{
			//	reasonList.add("The source and target ports are originated from the same vertex.");
			//	result = lineResult;
			//}
		}
		return result;
	}

	/**
	 * Override Superclass Method to provide additional checking.
	 */
	public boolean acceptsTarget(Object edge, Object port)
	{
		reasonList = new ArrayList<String>();
		Object source = ((Edge) edge).getSource();
		boolean result = true;
		// Target only Valid if not Equal Source
		boolean lineResult = (source != port);
		if (!lineResult)
		{
			reasonList.add("The source cannot be same with the target.");
			result = lineResult;
		}
		if (source instanceof DefaultPort && port instanceof DefaultPort)
		{
			//lineResult = !GeneralUtilities.areEqual(((DefaultPort) source).getParent(), ((DefaultPort) port).getParent());
			//if (!lineResult)
			//{
			//	reasonList.add("The source and target ports are originated from the same vertex.");
			//	result = lineResult;
			//}
		}
		return result;
	}

	/**
	 * Return reason list.
	 * @return
	 */
	public List getNotAcceptableReasonList()
	{
		return reasonList;
	}
}
/**
 * HISTORY: $Log: not supported by cvs2svn $
 * HISTORY: Revision 1.1  2008/10/27 20:06:30  linc
 * HISTORY: GUI first add.
 * HISTORY:
 */

