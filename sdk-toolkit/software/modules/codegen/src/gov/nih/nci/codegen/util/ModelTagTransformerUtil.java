package gov.nih.nci.codegen.util;

import gov.nih.nci.codegen.UMLModelLoader;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociationEnd;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Utility methods for mapping tag values from EA to ArgoUML 
 * 
 */
public class ModelTagTransformerUtil{
	
	public static final List<String> sdkCustomTagValues = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("id-attribute");
			add("mapped-attributes");
			add("implements-association");
			add("inverse-of");
			add("discriminator");
			add("correlation-table");
			add("documentation");
			add("description");
			add("lazy-load");
			add("mapped-collection-table");
			add("mapped-element");
			add("CADSR_ConceptualDomainPublicID");
			add("CADSR_ConceptualDomainVersion");
			add("NCI_CASCADE_ASSOCIATION");
			add("NCI_EAGER_LOAD");
			add("NCI_GENERATOR");
			add("NCI_GENERATOR_PROPERTY");		
			add("mapped-collection-element-type");
			add("NCI_GME_XML_NAMESPACE");
			add("NCI_GME_XML_ELEMENT");			
			add("NCI_GME_XML_LOC_REF");
			add("NCI_GME_SOURCE_XML_LOC_REF");
			add("NCI_GME_TARGET_XML_LOC_REF");
		}
	};
	
	private static Logger log = Logger.getLogger(ModelTagTransformerUtil.class);
	
	public static void execute(UMLModel eaModel,UMLModel argoModel){
		UMLPackage rootEAPackage=null;
		UMLPackage rootArgoPackage=null;
		
		for (UMLPackage rootPackage : eaModel.getPackages()) {
			if (rootPackage.getName().equals("Logical View")) {
				rootEAPackage=rootPackage;
			}
		}
		for (UMLPackage rootPackage : argoModel.getPackages()) {
			if (rootPackage.getName().equals("Logical View")) {
				rootArgoPackage=rootPackage;
			}
		}
		Map<String,UMLClass> umlClasses = new HashMap<String,UMLClass>();
		getUMLKlassesList(rootEAPackage, umlClasses);
		insertTaggedValues(rootArgoPackage,umlClasses);
		DebugUtils.printModel(argoModel);		
	}
	
	public static void insertTaggedValues(UMLPackage argoModel,Map<String,UMLClass> eaUMLClasses){		
		for(UMLPackage pack:argoModel.getPackages()){
			
			for(UMLClass argoKlass:pack.getClasses()){
				//for each EA-Architect class,get List of attributes and add to Argouml klass
				if(eaUMLClasses.containsKey(argoKlass.getName())){
					UMLClass eaKlass = eaUMLClasses.get(argoKlass.getName());
					log.info("Adding tag values for Class  "+eaKlass.getName());
					//add klass level tags if present in sdk custom tag values
					Collection<UMLTaggedValue> eaClassTagValues =eaKlass.getTaggedValues();
					for (UMLTaggedValue eaUMLTaggedValue : eaClassTagValues) {						
						for (String customTag : sdkCustomTagValues) {
							if (eaUMLTaggedValue.getName().startsWith(customTag)) {
								argoKlass.removeTaggedValue(eaUMLTaggedValue.getName());
								argoKlass.addTaggedValue(eaUMLTaggedValue.getName(), eaUMLTaggedValue.getValue());
								log.info("Class Level Tag Value:  "+eaUMLTaggedValue.getName() +"  "+eaUMLTaggedValue.getValue());
							}
						}
					}

					for(UMLAttribute eaAttr:eaKlass.getAttributes()){
						for(UMLAttribute argoAttr:argoKlass.getAttributes()){
							if(eaAttr.getName().equals(argoAttr.getName())){								
								Collection<UMLTaggedValue> taggedValues=eaAttr.getTaggedValues();
								for (UMLTaggedValue umlTaggedValue : taggedValues) {									
									for (String customTag : sdkCustomTagValues) {
										//add attribute level tags if present in sdk custom tag values
										if (umlTaggedValue.getName().startsWith(customTag)) {
											argoAttr.removeTaggedValue(umlTaggedValue.getName());
											argoAttr.addTaggedValue(umlTaggedValue.getName(), umlTaggedValue.getValue());
											log.info("Attribute level tag value:  " +umlTaggedValue.getName() +"   "+umlTaggedValue.getValue());
										}
									}
								}								
							}
						}
					}
					// add association level tags
					for (UMLAssociation eaAssociation : eaKlass.getAssociations()) {
						UMLAssociationEnd eaOtherEnd=getOtherEnd(eaKlass, eaAssociation);
						
						for (UMLAssociation argoAssociation : argoKlass.getAssociations()) {
							UMLAssociationEnd argoOtherEnd=getOtherEnd(argoKlass, argoAssociation);
							
							if(argoOtherEnd==null) continue;
							if(eaOtherEnd==null) continue;
							
							UMLClass eaUmlClass=(UMLClass)eaOtherEnd.getUMLElement();
							UMLClass argoUmlClass=(UMLClass)argoOtherEnd.getUMLElement();
							
							if (argoOtherEnd.getRoleName()!=null && argoOtherEnd.getRoleName().equals(eaOtherEnd.getRoleName())
									&& argoUmlClass.getName().equals(eaUmlClass.getName())
									&& eaOtherEnd.isNavigable()
									&& argoOtherEnd.isNavigable()) {
								
								Collection<UMLTaggedValue> eaTaggedValues=eaAssociation.getTaggedValues();
								for (UMLTaggedValue eaUmlTaggedValue : eaTaggedValues) {
									for (String customTag : sdkCustomTagValues) {
										if (eaUmlTaggedValue.getName().startsWith(customTag)) {
											argoAssociation.removeTaggedValue(eaUmlTaggedValue.getName());
											argoAssociation.addTaggedValue(eaUmlTaggedValue.getName(), eaUmlTaggedValue.getValue());
											log.info("Association level Tag value:  "+eaUmlTaggedValue.getName() +"   "+eaUmlTaggedValue.getValue());
										}
									}
								}
							}
						}											
					}
					log.info("\n");
				}
			}
			insertTaggedValues(pack, eaUMLClasses);
		}
	}

	private static UMLAssociationEnd getOtherEnd(UMLClass klass,
			UMLAssociation association) {
		List<UMLAssociationEnd> assocEnds = association.getAssociationEnds();
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);
		UMLAssociationEnd otherEnd = null;
		if (end1.getUMLElement().equals(klass))
			otherEnd = end2;
		else if (end1.getUMLElement().equals(klass))
			otherEnd = end1;
		return otherEnd;
	}

	public static void getUMLKlassesList(UMLPackage eaPackage,
			Map<String, UMLClass> umlClasses) {

		for (UMLPackage pack : eaPackage.getPackages()) {
			for (UMLClass klass : pack.getClasses()) {
				umlClasses.put(klass.getName(), klass);
			}
			getUMLKlassesList(pack, umlClasses);
		}
	}
	
	public static void main(String[] args) throws Exception {
		UMLModelLoader loader1 = new UMLModelLoader(
				"C:/Documents and Settings/Garmillas/Desktop/ISO/sdk.xmi", "EA");

		UMLModelLoader loader2 = new UMLModelLoader(
				"C:/Documents and Settings/Garmillas/Desktop/ISO/sdk.uml",
				"ARGO");
		execute(loader1.getUMLModel(),loader2.getUMLModel());
		loader2.getXmiHandler().save("C:/Documents and Settings/Garmillas/Desktop/ISO/sdk2.uml");

	}
}