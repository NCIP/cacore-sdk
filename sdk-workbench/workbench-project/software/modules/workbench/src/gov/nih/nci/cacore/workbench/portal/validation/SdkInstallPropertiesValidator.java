package gov.nih.nci.cacore.workbench.portal.validation;

import gov.nih.nci.cacore.workbench.portal.viewer.SdkInstallPropertiesViewer;

import com.jgoodies.validation.ValidationResult;

public class SdkInstallPropertiesValidator implements TabbedPanePropertiesValidator {
	
	SdkInstallPropertiesViewer parentContainer = null;
	
	public SdkInstallPropertiesValidator(SdkInstallPropertiesViewer parentContainer){
			this.parentContainer=parentContainer;
	}

    public void validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	for(PanelValidator panelValidator:parentContainer.getPanelValidators()){
    		result.addAllFrom(panelValidator.validateInput());
    	}

    	parentContainer.getValidationModel().setResult(result);

    	parentContainer.toggleInstallButton();
    	
    	parentContainer.updateComponentTreeSeverity();

    }
    
    public void initValidation(){
    	
    	for(PanelValidator panelValidator:parentContainer.getPanelValidators()){
    		panelValidator.initValidation();
    	}
        
    	this.validateInput();
    }
    
	public void setDirty(boolean isDirty){
		parentContainer.setDirty(isDirty);
	}
}
