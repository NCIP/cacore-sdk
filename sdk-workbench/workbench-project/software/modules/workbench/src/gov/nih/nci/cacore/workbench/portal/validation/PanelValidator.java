package gov.nih.nci.cacore.workbench.portal.validation;

import com.jgoodies.validation.ValidationResult;

public interface PanelValidator {

	public ValidationResult validateInput();
	public void initValidation();

}