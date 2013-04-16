/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.validation;

import com.jgoodies.validation.ValidationResult;

public interface PanelValidator {

	public ValidationResult validateInput();
	public void initValidation();

}