/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.validation;


public interface TabbedPanePropertiesValidator {
	
	public void validateInput(); 
	public void initValidation();
	public void setDirty(boolean isDirty);

}