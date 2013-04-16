/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import java.util.List;

public interface DomainView
{
	public String getDomainName();
	public String getDomainDesc();
	public List<String> getConceptList();

	public void setDomainName(String _domainName);
	public void setDomainDesc(String _domainDesc);
	public void setConceptList(List<String> _conceptList);

}
