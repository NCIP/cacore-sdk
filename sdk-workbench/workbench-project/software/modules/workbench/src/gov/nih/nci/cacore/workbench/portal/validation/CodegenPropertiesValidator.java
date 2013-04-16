/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.validation;

import gov.nih.nci.cacore.workbench.portal.viewer.CodegenPropertiesViewer;

import com.jgoodies.validation.ValidationResult;

public class CodegenPropertiesValidator implements TabbedPanePropertiesValidator {
	
	CodegenPropertiesViewer parentContainer = null;
	
	public CodegenPropertiesValidator(CodegenPropertiesViewer parentContainer){
			this.parentContainer=parentContainer;
	}

    public void validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	for(PanelValidator panelValidator:parentContainer.getPanelValidators()){
    		result.addAllFrom(panelValidator.validateInput());
    	}

    	parentContainer.getValidationModel().setResult(result);

    	parentContainer.toggleSaveButton();
    	parentContainer.togglePreviousButton();
    	parentContainer.toggleNextButton();
    	parentContainer.toggleGenerateButton();
    	
    	parentContainer.togglePanels();
    	
    	parentContainer.updateGenerateApplicationTabContents();
    	
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
