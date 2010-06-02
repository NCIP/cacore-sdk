package gov.nih.nci.codegen.transformer;

import gov.nih.nci.codegen.ArtifactHandler;
import gov.nih.nci.codegen.GenerationException;
import gov.nih.nci.codegen.GeneratorError;
import gov.nih.nci.codegen.GeneratorErrors;
import gov.nih.nci.codegen.Transformer;
import gov.nih.nci.codegen.artifact.BaseArtifact;
import gov.nih.nci.codegen.util.IsoDatatypeTransformationHelper;
import gov.nih.nci.codegen.util.TransformerUtils;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.iso21090.hibernate.node.ComplexNode;
import gov.nih.nci.iso21090.hibernate.node.ConstantNode;
import gov.nih.nci.iso21090.hibernate.node.Node;
import gov.nih.nci.iso21090.hibernate.node.RootNode;

import java.util.Collection;
import java.util.List;

public class IsoConstantTransformer implements Transformer{

	
	private String name = IsoConstantTransformer.class.getName();
	private boolean enabled = true;
	protected TransformerUtils transformerUtils;
	private ArtifactHandler artifactHandler;
	private IsoDatatypeTransformationHelper isoDatatypeTransformationHelper;	
	
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
			isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
			isoDatatypeTransformationHelper.setModel(model);
			isoDatatypeTransformationHelper.setUtils(transformerUtils);

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
		stringBuffer.append("\n<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		stringBuffer.append("\n xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:util=\"http://www.springframework.org/schema/util\"");
		stringBuffer.append("\n xsi:schemaLocation=\"http://www.springframework.org/schema/beans	http://www.springframework.org/schema/beans/spring-beans-2.5.xsd	http://www.springframework.org/schema/util	http://www.springframework.org/schema/util/spring-util-2.5.xsd\"");
		stringBuffer.append("\n	default-lazy-init=\"false\" default-dependency-check=\"none\" default-autowire=\"no\">");
		
		Collection<UMLClass> classes= transformerUtils.getAllClasses(model);
		
		for(UMLClass klas: classes){
			if(transformerUtils.isImplicitParent(klas)) 
				continue;
			UMLClass table = transformerUtils.getTable(klas);
			UMLAttribute idAttr = transformerUtils.getClassIdAttr(klas);
			
			UMLClass currentKlass = klas;
			while (currentKlass!= null)
			{
				for (UMLAttribute attribute : currentKlass.getAttributes()){
					if(!transformerUtils.isJavaDataType(attribute)){
						RootNode rootNode = isoDatatypeTransformationHelper.getDatatypeNode(klas,attribute,table,true);
						stringBuffer.append(convertToAnnotation(transformerUtils.getFQCN(klas)+"."+attribute.getName(), rootNode));
						
					}
				}
				currentKlass = transformerUtils.getSuperClass(currentKlass);
			}//while(currentKlass!=null && transformerUtils.isImplicitParent(currentKlass));
		}
		stringBuffer.append("\n</beans>");
		return stringBuffer.toString();
	}

	private StringBuffer convertToAnnotation(String dataTypeClassName,Node rootNode) throws GenerationException{

		StringBuffer sb = new StringBuffer();
		
		if(convertToAnnotationNew(dataTypeClassName,rootNode,"",sb,"   ",true))
		{
			return sb;
		}
		else
		{
			StringBuffer buffer= new StringBuffer();
			return buffer;	
		}
		
	}
	
	private boolean convertToAnnotationNew(String dataTypeClassName, Node node, String prefixBeanString, StringBuffer strBuffer, String spacer, boolean useBeansFullName ) throws GenerationException {
		boolean printSomethingAlready = false;
		String spacer2 = spacer + "   ";
		if (node instanceof ConstantNode)
		{
			printConstantNode(node, strBuffer, spacer);
			return true;
		}
		if (node instanceof ComplexNode)
		{

			boolean hasConstant = false, hasComplex = false;
			for (Node nde : ((ComplexNode) node).getInnerNodes())
			{
				if (nde instanceof ConstantNode)
				{
					hasConstant = true;
				} else if (nde instanceof ComplexNode)
				{
					hasComplex = true;
				}
			}
			if (hasConstant || hasComplex)
			{
				if (useBeansFullName)
					//strBuffer.append("\n" + spacer2 + "<bean id=\"" + ((RootNode) node).getParentClassName() + "." + node.getName() + "\" class=\"gov.nih.nci.iso21090.hibernate.node.ComplexNode\">");
					strBuffer.append("\n" + spacer2 + "<bean id=\"" +dataTypeClassName+ "\" class=\"gov.nih.nci.iso21090.hibernate.node.ComplexNode\">");
				else
					strBuffer.append("\n" + spacer2 + "<bean  class=\"gov.nih.nci.iso21090.hibernate.node.ComplexNode\">");
				
				strBuffer.append("\n" + spacer2 + "    <constructor-arg value=\"" + removeBraces(node.getName()) + "\"/>");
				strBuffer.append("\n" + spacer2 + "    <property name=\"isoClassName\" value=\"" + isoDatatypeTransformationHelper.converteIsoClassNameToJavaClassName(node.getIsoClassName()) + "\"/>");
				

				StringBuffer sbInner = new StringBuffer();
				for (Node nde : ((ComplexNode) node).getInnerNodes())
				{
					if (convertToAnnotationNew(null,nde, "", sbInner, spacer2 + "        ", false))
					{
						printSomethingAlready = true;
					} else
					{
						// System.out.print("");
					}
				}
				if (sbInner.length() > 0)
				{
					strBuffer.append("\n" + spacer2 + "    <property name=\"innerNodes\">\n" + spacer2 + "    " + "    " + "<list>");
					strBuffer.append(sbInner.toString());
					strBuffer.append("\n" + spacer2 + "        " + "</list>\n" + spacer2 + "    </property>\n" + spacer2 + "</bean>");
				}

			} else
			{
				return false;
			}
		}
		
		return printSomethingAlready;
	}
	private String printConstantNode(Node nde, StringBuffer constantNodeBuffer, String spacer3) throws GenerationException {
		constantNodeBuffer.append("\n"+spacer3+"   <bean class=\"gov.nih.nci.iso21090.hibernate.node.ConstantNode\">");
		constantNodeBuffer.append("\n"+spacer3+"      <constructor-arg value=\""+((ConstantNode) nde).getName()+"\"/>");
		constantNodeBuffer.append("\n"+spacer3+"      <property name=\"constantValue\" value=\""+((ConstantNode) nde).getConstantValue()+"\"/>");
		
		
		if (isoDatatypeTransformationHelper.isEnum(nde.getIsoClassName()))
		{
			String tempIsoClassName = nde.getIsoClassName();
			constantNodeBuffer.append("\n" + spacer3 + "      <property name=\"instance\">");
			constantNodeBuffer.append("\n" + spacer3 + "         <util:constant static-field=\"" + tempIsoClassName + "." + ((ConstantNode) nde).getConstantValue() + "\"/>");
			constantNodeBuffer.append("\n" + spacer3 + "      </property>");
		} 
		else
		{
			constantNodeBuffer.append("\n"+spacer3+"      <property name=\"instance\" value=\"" + ((ConstantNode) nde).getConstantValue() + "\"/>");
		}

		constantNodeBuffer.append("\n"+spacer3+"   </bean>");
		return null;
	}

	private String removeBraces(String name) 
	{

		if(name.indexOf('[')<0) return name;
		
		String temp = name;
		temp = temp.replace('[','_');
		temp = temp.substring(0, name.length()-1);
		
		return temp;
	}
}