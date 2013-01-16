package gov.nih.nci.codegen.transformer;


import gov.nih.nci.codegen.ArtifactHandler;
import gov.nih.nci.codegen.GenerationException;
import gov.nih.nci.codegen.GeneratorError;
import gov.nih.nci.codegen.GeneratorErrors;
import gov.nih.nci.codegen.Transformer;
import gov.nih.nci.codegen.artifact.BaseArtifact;
import gov.nih.nci.codegen.util.TransformerUtils;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;

import java.util.Collection;
import java.util.List;

public class caDSRMetadataTransformer implements Transformer {

	private String name = caDSRMetadataTransformer.class.getName();
	private boolean enabled = true;
	protected TransformerUtils transformerUtils;
	private ArtifactHandler artifactHandler;
	
	public void setTransformerUtils(TransformerUtils transformerUtils) {
		this.transformerUtils = transformerUtils;
	}
	
	public void setArtifactHandler(ArtifactHandler artifactHandler)
	{
		this.artifactHandler = artifactHandler;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name= name;
	}
	public Boolean isEnabled() {

		return enabled;
	}

	public void setEnabled(boolean bool) {

		this.enabled = bool;
	}

	public GeneratorErrors validate(UMLModel model) {
		return null;
	}

	public GeneratorErrors execute(UMLModel model) {
		GeneratorErrors errors = new GeneratorErrors();
		try 
		{
			BaseArtifact arctifact = new BaseArtifact(transformerUtils);
			arctifact.setContent(generate(model));
			artifactHandler.handleArtifact(arctifact);
		} 
		catch (GenerationException e) 
		{
			errors.addError(new GeneratorError(getName() + ": Error while generating artifact for the model",e));
		}
		return errors;
	}

	private String generate(UMLModel model) throws GenerationException {
		
		StringBuffer stringBuffer  = new StringBuffer();
		
		stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		stringBuffer.append("\n<metadata>");
		
		Collection<UMLClass> classes= transformerUtils.getAllClasses(model);
		
		for(UMLClass klas: classes){
			
			UMLClass currentKlass = klas;
			boolean classEle = false;
			while (currentKlass!= null)
			{
				for (UMLAttribute attribute : currentKlass.getAttributes()){
					boolean attrEle = false;
					Collection<UMLTaggedValue> taggedValues = attribute.getTaggedValues();
					
					if(taggedValues != null && taggedValues.size() > 0)
					{
						

						for(UMLTaggedValue UMLTaggedValue : taggedValues)
						{
							String tagName = UMLTaggedValue.getName();
							String tagValue = UMLTaggedValue.getValue();
							if(tagName.startsWith("CADSR_"))
							{
								if(!classEle)
								{
									stringBuffer.append("\n<class name=\""+transformerUtils.getFQCN(klas)+"\">");
									classEle =true;
								}
								if(!attrEle)
								{
									stringBuffer.append("\n<attribute name=\""+attribute.getName()+"\" ");
									attrEle = true;
								}
								
								stringBuffer.append(tagName+"=\""+tagValue+"\" ");
							}
						}
						if(attrEle)
							stringBuffer.append("/>");
					}
				}
				currentKlass = transformerUtils.getSuperClass(currentKlass);
				
			}
			if(classEle)
				stringBuffer.append("\n</class>");
		}
		stringBuffer.append("\n</metadata>");
		return stringBuffer.toString();
	}

	
}
