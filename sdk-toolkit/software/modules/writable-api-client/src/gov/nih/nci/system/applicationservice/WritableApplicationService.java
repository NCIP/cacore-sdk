/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.applicationservice;

import java.util.List;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;

public interface WritableApplicationService extends ApplicationService
{
	public SDKQueryResult executeQuery(SDKQuery query) throws ApplicationException;
	
	public List<SDKQueryResult> executeBatchQuery(List<SDKQuery> batchQuery) throws ApplicationException;
}