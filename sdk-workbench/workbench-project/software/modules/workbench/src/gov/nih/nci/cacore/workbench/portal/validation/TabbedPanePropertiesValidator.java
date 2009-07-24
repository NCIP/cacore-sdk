package gov.nih.nci.cacore.workbench.portal.validation;


public interface TabbedPanePropertiesValidator {
	
	public void validateInput(); 
	public void initValidation();
	public void setDirty(boolean isDirty);

}