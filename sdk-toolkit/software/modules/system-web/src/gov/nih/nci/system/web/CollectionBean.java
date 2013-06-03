/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web;

import gov.nih.nci.system.web.ResourceLink;
import java.util.Collection;

public interface CollectionBean extends java.io.Serializable
{
	public String getType();
	public Collection<ResourceLink> getLinks();
}