/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.dao;

import java.util.List;

/**
 * @author Satish Patel, Dan Dumitru
 */
public interface DAO {

	/**
	 * Queries the datasource 
	 * 
	 * @param request 
	 *           
	 * @return
	 * @throws DAOException
	 */
	public Response query(Request request) throws DAOException, Exception;
	
	public List<String> getAllClassNames();

}
