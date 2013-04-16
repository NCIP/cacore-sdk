/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.query.hql;

import java.util.List;

import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class DeleteHQLQuery extends HQLCriteria implements HQLManipulationQuery,SDKQuery
{
	private static final long serialVersionUID = 1L;

	public DeleteHQLQuery(String hql, List<Object> parameters) {
		super(hql, parameters);
	}
	
	public DeleteHQLQuery(String hql) {
		super(hql);
	}
	
}