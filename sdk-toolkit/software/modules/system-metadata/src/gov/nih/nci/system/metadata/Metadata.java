/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.metadata;

import java.util.List;

public interface Metadata {
	public String getMetadataContextName();

	public MetadataElement getMetadata(String className, String attribute);
}
