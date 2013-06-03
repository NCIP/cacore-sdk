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

import gov.nih.nci.restgen.ui.jgraph.MiddlePanelJGraphController;
import gov.nih.nci.restgen.ui.mapping.MappingMiddlePanel;

import javax.swing.*;

/**
 * The class is the default action class that will be inherited by other action class in this package.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 */
public abstract class DefaultAbstractJgraphAction extends AbstractContextAction
{
	private MappingMiddlePanel middlePanel;
	private MiddlePanelJGraphController controller;

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a default icon.
	 */
	protected DefaultAbstractJgraphAction(String name, MappingMiddlePanel middlePanel, MiddlePanelJGraphController controller)
	{
		this(name, (Icon)null, middlePanel, controller);
	}

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	protected DefaultAbstractJgraphAction(String name, Icon icon, MappingMiddlePanel middlePanel, MiddlePanelJGraphController controller)
	{
		super(name, icon);
		if (middlePanel == null && controller != null)
		{
			middlePanel = controller.getMiddlePanel();
		}
		this.middlePanel = middlePanel;
		this.controller = controller;
	}

	protected MappingMiddlePanel getMiddlePanel()
	{
		return middlePanel;
	}

	protected MiddlePanelJGraphController getController()
	{
		return controller;
	}
}
/**
 * HISTORY      : $Log: not supported by cvs2svn $
 */
