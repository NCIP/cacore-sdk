/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.dao.orm;

import org.hibernate.cfg.Configuration;

public class HibernateConfigurationHolder {

	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
