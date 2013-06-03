/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core; 

public interface Message
{
	public String getName();
	public String getMessage();
	public String getCategory();

	public void setName(String _name);
	public void setMessage(String _message);
	public void setCategory(String _category);
}