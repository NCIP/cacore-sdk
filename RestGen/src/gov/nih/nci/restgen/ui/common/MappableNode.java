/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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
package gov.nih.nci.restgen.ui.common;

/**
 * An interface to indicate whether the subject has participated in mapping or not.
 * The implementation of this interface will help UI render to present different cues
 * to indicate its (mapping) status.
 * Also the implementation class shall define an instance member variable to remember the status.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 *
 */
public interface MappableNode
{
	/**
	 * Set the map status to new value, which might trigger underline property change.
	 * @param newValue
	 */
	void setMapStatus(boolean newValue);

	/**
	 * Answer if this given node is mapped.
	 * @return
	 */
	boolean isMapped();
}
/**
 * HISTORY: $Log: not supported by cvs2svn $
 */

