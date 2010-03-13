package gov.nih.nci.codegen.transformer;

import gov.nih.nci.codegen.Artifact;
import gov.nih.nci.codegen.ArtifactHandler;
import gov.nih.nci.codegen.GenerationException;
import gov.nih.nci.codegen.GeneratorError;
import gov.nih.nci.codegen.GeneratorErrors;
import gov.nih.nci.codegen.Transformer;
import gov.nih.nci.codegen.util.TransformerUtils;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author Satish Patel
 * 
 */
public abstract class UMLPackageJetTransformer implements Transformer {

	private static Logger log = Logger.getLogger(UMLPackageJetTransformer.class);
	private ArtifactHandler artifactHandler;
	private boolean enabled = true;
	private String name = UMLClassJetTransformer.class.getName();

	protected TransformerUtils transformerUtils;

	public void setTransformerUtils(TransformerUtils transformerUtils) {
		this.transformerUtils = transformerUtils;
	}

	/**
	 * @param artifactHandler
	 *            the artifactHandler to set
	 */
	public void setArtifactHandler(ArtifactHandler artifactHandler) {
		this.artifactHandler = artifactHandler;
	}

	public GeneratorErrors execute(UMLModel model) {
		GeneratorErrors errors = new GeneratorErrors();
		Collection<UMLPackage> packages = null;

		packages = model.getPackages();
		for (UMLPackage umlPackage : packages) {
			System.out.println(umlPackage.getName());
			try {
				executeTemplateRecursion(model,umlPackage, errors);	
			} catch (GenerationException ge) {
				errors.addError(new GeneratorError(getName()
						+ ": Error while generating artifact for the package "+ umlPackage.getName() + "\n\t", ge));
			}
		}
		return errors;
	}

	public void executeTemplateRecursion (UMLModel model, UMLPackage pkg, GeneratorErrors errors)throws GenerationException{
		for(UMLPackage innerPkg: pkg.getPackages())
			executeTemplateRecursion(model,innerPkg, errors);

		if(transformerUtils.isIncluded(pkg) && transformerUtils.containsIncludedClass(pkg)){
			try {
				Artifact artifact = executeTemplate(model, pkg);
				if(artifact!=null)
					artifactHandler.handleArtifact(artifact);
			} catch (GenerationException ge) {
				errors.addError(new GeneratorError(getName()
						+ ": Error while generating artifact for the package "+ pkg.getName() + "\n\t", ge));
			}
		}
	}
	
	public abstract Artifact executeTemplate(UMLModel model, UMLPackage pkg)
			throws GenerationException;

	public GeneratorErrors validate(UMLModel model) {
		return null;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}