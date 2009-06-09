package gov.nih.nci.cacoresdk.workbench.portal.validation;


public interface TabbedPanePropertiesValidator {
	
	public void validateInput(); 
	public void initValidation();
	public void setDirty(boolean isDirty);

}