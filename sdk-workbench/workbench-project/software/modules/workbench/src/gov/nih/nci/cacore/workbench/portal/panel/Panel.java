/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.panel;

import java.util.Map;

import javax.swing.JPanel;

public interface Panel {
	
	public JPanel getSettingsPanel();
	public JPanel getSummaryPanel();
	public Map<String,String> getPropsMap();

}