/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.security;

import gov.nih.nci.system.applicationservice.ApplicationService;

public interface GridApplicationServiceProvider {

	public ApplicationService getApplicationService(String username,
		   String password) throws Exception;

}
