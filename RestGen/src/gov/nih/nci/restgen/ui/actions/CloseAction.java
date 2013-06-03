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


import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Currently play as place holder to define the general look and feel of a "Options..." action.
 * Descendant class will provide concrete implementation of the action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class CloseAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.CLOSE;
	protected static final Character COMMAND_MNEMONIC = new Character('C');
	public static final ImageIcon IMAGE_ICON = null;
	protected transient MainFrameContainer mainFrame = null;
	

	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public CloseAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME,null, mainFrame);
	}



	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public CloseAction(String name, Icon icon, MainFrameContainer mainFrame)
	{
		super(name, icon);
		this.mainFrame = mainFrame;
		setAdditionalAttributes();
	}

	/**
	 * provide descendant class to override.
	 */
	protected void setAdditionalAttributes()
	{
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
	}
	


	
	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	@SuppressWarnings("static-access")
	protected boolean doAction(ActionEvent e) throws Exception
	{
		if(mainFrame.getMainFrame().getMappingMainPanel().getSourceTree()!=null)
		{
			
			
			mainFrame.getMainFrame().getMappingMainPanel().getSourceScrollPane().setViewportView(null);
			mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraphController().handleDeleteAll();
			mainFrame.getMainFrame().getMappingMainPanel().setSourceTree(null);
			
		}
		if(mainFrame.getMainFrame().getMappingMainPanel().getTargetTree()!=null)
		{
			mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setViewportView(null);
			mainFrame.getMainFrame().getMappingMainPanel().setTargetTree(null);
			mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder(""));
			mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("");
			mainFrame.getMainFrame().getMappingMainPanel().getTargetButtonPanel().removeAll();
			mainFrame.getMainFrame().getMappingMainPanel().getTargetRadioButtonPanel().setBorder(BorderFactory.createTitledBorder(""));
			mainFrame.getMainFrame().getMappingMainPanel().getTargetRadioButtonPanel().removeAll();
			mainFrame.getMainFrame().getMappingMainPanel().getTargetButtonPanel().updateUI();
			//mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setBackground(new Color(212,208,200));
			
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
