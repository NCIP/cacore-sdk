/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen.util;

import gov.nih.nci.codegen.UMLModelLoader;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociationEnd;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggableElement;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			add("mapped-constant");
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
	
	public static void executeTaggedValuesInsertion(UMLModel eaModel,
			UMLModel argoModel) {
		List<UMLPackage> modelsPackage = resolveModelPackages(eaModel, argoModel);
		Map<String, UMLClass> umlClasses = new HashMap<String, UMLClass>();
		getUMLKlassesList(modelsPackage.get(1), umlClasses);
		insertTaggedValues2(modelsPackage.get(0), umlClasses);
		DebugUtils.printModel(argoModel);
	}

	@SuppressWarnings("unchecked")
	public static void executeEAArgoValidation(UMLModel eaModel,UMLModel argoModel){
		List<UMLPackage> modelsPackage = resolveModelPackages(eaModel, argoModel);
		
		Map<String, UMLClass> eaUMLClasses = new HashMap<String, UMLClass>();
		Map<String, UMLClass> argoUMLClasses = new HashMap<String, UMLClass>();
		
		getUMLKlassesList(modelsPackage.get(0), eaUMLClasses);
		getUMLKlassesList(modelsPackage.get(1), argoUMLClasses);
		
		StringBuilder errorMessage= new StringBuilder();
		
	    Iterator eait = eaUMLClasses.entrySet().iterator();
	    errorMessage.append("\n--------------------------------------------------------------\n");
	    while (eait.hasNext()) {
	        Map.Entry eapairs = (Map.Entry)eait.next();
	      //validate for class
	        UMLClass argoUmlClass = argoUMLClasses.get(eapairs.getKey());
			if(argoUmlClass==null){
	        	errorMessage.append("Class "+eapairs.getKey()+" is not defined in ArgoUML").append("\n");
	        }else{
	        	//validate for attributes for each class
	        	UMLClass eaUmlClass=(UMLClass)eapairs.getValue();
	        	List<UMLAttribute> eaUmlAttributes=eaUmlClass.getAttributes();
	        	List<UMLAttribute> argoUmlAttributes=argoUmlClass.getAttributes();
	        	
	        	List<String> attributeNames=new ArrayList<String>();
	        	for (UMLAttribute umlAttribute : argoUmlAttributes) {
					attributeNames.add(umlAttribute.getName());
				}	        	
	        	for (UMLAttribute eaUmlAttribute : eaUmlAttributes) {
	        		boolean found=false;
	        		for (String argoAttributeName : attributeNames) {
						if(eaUmlAttribute.getName().equals(argoAttributeName)){
							found=true;
							break;
						}
					}
	        		if(!found){
	        			errorMessage.append("EA Class "+eapairs.getKey()+" with attribute "+eaUmlAttribute.getName()+" was not mapped in ArgoUML\n");
	        		}
				}
	        	
	        	//validate for associations for each class
	        	Set<UMLAssociation> eaUmlAssociations=eaUmlClass.getAssociations();
	        	Set<UMLAssociation> argoUmlAssociations=argoUmlClass.getAssociations();
	        	
	        	List<String> associationNames=new ArrayList<String>();
	        	for (UMLAssociation argoUmlAssociation : argoUmlAssociations) {
	        		
	        		associationNames.add(argoUmlAssociation.getRoleName());
					
				}
	        	for (UMLAssociation eaUmlAssociation : eaUmlAssociations) {
	        		boolean found=false;
	        		for (String argoAssociationName : associationNames) {
						if(eaUmlAssociation.getRoleName()!=null && eaUmlAssociation.getRoleName().equals(argoAssociationName)){
							found=true;
							break;
						}
					}
	        		if(!found && eaUmlAssociation.getRoleName()!=null){
	        			errorMessage.append("EA Class "+eapairs.getKey()+" with Association Role Name "+eaUmlAssociation.getRoleName()+" was not mapped in ArgoUML\n");
	        		}
				}
	        }
	    }
	    errorMessage.append("\n--------------------------------------------------------------\n");
	    
	    System.out.println(errorMessage);
	}
	
	private static List<UMLPackage> resolveModelPackages(UMLModel eaModel,
			UMLModel argoModel) {
		UMLPackage rootEAPackage = null;
		UMLPackage rootArgoPackage = null;

		for (UMLPackage rootPackage : eaModel.getPackages()) {
			if (rootPackage.getName().equals("Logical View")) {
				rootEAPackage = rootPackage;
			}
		}
		for (UMLPackage rootPackage : argoModel.getPackages()) {
			if (rootPackage.getName().equals("Logical View")) {
				rootArgoPackage = rootPackage;
			}
		}
		List<UMLPackage> modelsPackage = new ArrayList<UMLPackage>();
		modelsPackage.add(rootEAPackage);
		modelsPackage.add(rootArgoPackage);
		return modelsPackage;
	}

	private static void removetTaggedValues(UMLTaggableElement element)
	{
		for (UMLTaggedValue argoUMLTaggedValue : element.getTaggedValues()) 
		{
			for (String customTag : sdkCustomTagValues) 
			{
				if (argoUMLTaggedValue.getName().startsWith(customTag)) 
				{
					element.removeTaggedValue(argoUMLTaggedValue.getName());
				}
			}
		}
	}
	
	private static int transferTaggedValues(UMLTaggableElement source, UMLTaggableElement target)
	{
		int count = 0;
		for (UMLTaggedValue sourceUMLTaggedValue : source.getTaggedValues()) 
		{	
			for (String customTag : sdkCustomTagValues) 
			{
				if (sourceUMLTaggedValue.getName().startsWith(customTag)) 
				{
					try {
					target.addTaggedValue(sourceUMLTaggedValue.getName(), sourceUMLTaggedValue.getValue());
					count++;
					} catch (Exception e)
					{
						log.error(e);
					}
				}
			}
		}
		return count;
	}
	
	
	public static void insertTaggedValues2(UMLPackage eaModel,Map<String,UMLClass> argoUMLClasses){
		for(UMLPackage pack:eaModel.getPackages()){
			
			for(UMLClass eaKlass:pack.getClasses()){
				if(argoUMLClasses.containsKey(eaKlass.getName())){
					UMLClass argoKlass = argoUMLClasses.get(eaKlass.getName());
					log.info("Adding tag values for Class  "+eaKlass.getName());
					
					removetTaggedValues(argoKlass);
					transferTaggedValues(eaKlass, argoKlass);
					

					if(eaKlass.getName().equals("ANY"))
						System.out.println();
					
					for(UMLAttribute eaAttr:eaKlass.getAttributes()){
						boolean found = false;
						for(UMLAttribute argoAttr:argoKlass.getAttributes()){
							if(eaAttr.getName().equals(argoAttr.getName())){
								found = true;
								removetTaggedValues(argoAttr);
								int transferCOunt = transferTaggedValues(eaAttr, argoAttr);
								log.info("Transfering Tag Value:  "+eaKlass.getName() +"."+eaAttr.getName()+" : "+transferCOunt);
								break;
							}
						}
						if(found == false)
							log.info("Skipping attribute "+eaKlass.getName()+"."+eaAttr.getName());
					}

					
					// add association level tags
					for (UMLAssociation eaAssociation : eaKlass.getAssociations()) {
						UMLAssociationEnd eaOtherEnd=getOtherEnd(eaKlass, eaAssociation);
						boolean found = false;
						for (UMLAssociation argoAssociation : argoKlass.getAssociations()) {
							UMLAssociationEnd argoOtherEnd=getOtherEnd(argoKlass, argoAssociation);
							
							if(argoOtherEnd==null) continue;
							if(eaOtherEnd==null) continue;
							
							UMLClass eaUmlClass=(UMLClass)eaOtherEnd.getUMLElement();
							UMLClass argoUmlClass=(UMLClass)argoOtherEnd.getUMLElement();
							
							if(eaOtherEnd.isNavigable() && eaOtherEnd.getRoleName()!=null && eaOtherEnd.getRoleName().equals(argoOtherEnd.getRoleName()))
							{
								found = true;
								removetTaggedValues(argoAssociation);
								transferTaggedValues(eaAssociation, argoAssociation);
								break;
							}
						}
						if(found == false && eaOtherEnd !=null)
							log.info("Skipping association "+eaKlass.getName()+"."+(eaOtherEnd.getRoleName()));
					}
					
				}
				else
				{
					log.info("Skipping class "+eaKlass.getName());
				}
			}
			insertTaggedValues2(pack, argoUMLClasses);
		}
		
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
						boolean found = false;
						for(UMLAttribute argoAttr:argoKlass.getAttributes()){
							if(eaAttr.getName().equals(argoAttr.getName())){
								found = true;
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
						if(found == false)
							log.info("Skipping attribute "+eaKlass.getName()+"."+eaAttr.getName());
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
				else
				{
					log.info("Skipping class "+argoKlass.getName());
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
				"C:/Satish/workspace/sdk-svn-trunk/sdk-toolkit/iso-example-project/models/sdk.xmi", "EA");

		UMLModelLoader loader2 = new UMLModelLoader(
				"C:/Satish/workspace/sdk-svn-trunk/sdk-toolkit/iso-example-project/models/sdk.uml",
				"ARGO");
		//executeEAArgoValidation(loader1.getUMLModel(),loader2.getUMLModel());
		executeTaggedValuesInsertion(loader1.getUMLModel(),loader2.getUMLModel());
		loader2.getXmiHandler().save("C:/Satish/workspace/sdk-svn-trunk/sdk-toolkit/iso-example-project/models/sdk2.uml");
	}
}