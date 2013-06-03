/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.ui.dnd;

import javax.swing.JComponent;
import javax.swing.TransferHandler;


public class CommonTransferHandler extends TransferHandler {

	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	@Override
	public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
	}

}
