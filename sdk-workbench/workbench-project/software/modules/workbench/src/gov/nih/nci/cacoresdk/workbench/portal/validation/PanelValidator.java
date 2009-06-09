package gov.nih.nci.cacoresdk.workbench.portal.validation;

import com.jgoodies.validation.ValidationResult;

public interface PanelValidator {

	public ValidationResult validateInput();
	public void initValidation();

}