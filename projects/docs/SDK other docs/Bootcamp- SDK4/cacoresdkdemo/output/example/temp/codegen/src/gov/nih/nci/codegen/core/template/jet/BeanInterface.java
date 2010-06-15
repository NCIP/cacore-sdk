package gov.nih.nci.codegen.core.template.jet;

import java.util.*;
import org.omg.uml.foundation.core.*;
import org.omg.uml.modelmanagement.*;
import org.omg.uml.foundation.datatypes.*;
import gov.nih.nci.codegen.core.*;
import gov.nih.nci.codegen.core.transformer.*;
import gov.nih.nci.codegen.core.transformer.template.*;
import gov.nih.nci.codegen.core.filter.*;
import gov.nih.nci.codegen.core.handler.*;
import gov.nih.nci.codegen.core.access.*;
import gov.nih.nci.codegen.core.util.*;
import org.omg.uml.modelmanagement.UmlPackage;
import org.omg.uml.foundation.core.*;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.core.Operation;
import org.omg.uml.foundation.core.Parameter;
import org.omg.uml.foundation.core.UmlAssociation;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;
import org.omg.uml.modelmanagement.UmlPackage;

public class BeanInterface implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "" + NL + "" + NL + "public interface ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = NL + "    extends ";
  protected final String TEXT_6 = ".";
  protected final String TEXT_7 = ", Ontology";
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL + "    extends ";
  protected final String TEXT_10 = ".";
  protected final String TEXT_11 = ", OntologyRelationship";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "    extends ";
  protected final String TEXT_14 = ".";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + "    extends Ontology";
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = NL + "    extends OntologyRelationship";
  protected final String TEXT_19 = NL + NL + NL + "{" + NL + "\t";
  protected final String TEXT_20 = NL + "\t   ";
  protected final String TEXT_21 = NL + "\t   public ";
  protected final String TEXT_22 = " ";
  protected final String TEXT_23 = " get";
  protected final String TEXT_24 = "();" + NL + "\t   public void set";
  protected final String TEXT_25 = "(";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = ")";
  protected final String TEXT_28 = " ";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = ")";
  protected final String TEXT_31 = ";" + NL + "\t";
  protected final String TEXT_32 = NL + "\t";
  protected final String TEXT_33 = NL + "\t   ";
  protected final String TEXT_34 = NL + "\t   ";
  protected final String TEXT_35 = NL + "\t   ";
  protected final String TEXT_36 = NL + "\t      ";
  protected final String TEXT_37 = NL + "\t" + NL + "\tpublic java.util.Collection get";
  protected final String TEXT_38 = "();" + NL + "\t      ";
  protected final String TEXT_39 = NL + "\t" + NL + "        public ";
  protected final String TEXT_40 = " get";
  protected final String TEXT_41 = "();" + NL + "\t\t  ";
  protected final String TEXT_42 = NL + "\t\t     ";
  protected final String TEXT_43 = NL + "\t\t  ";
  protected final String TEXT_44 = NL + "\t\t";
  protected final String TEXT_45 = NL + "\tpublic void set";
  protected final String TEXT_46 = "(java.util.Collection ";
  protected final String TEXT_47 = ");" + NL + "\t\t";
  protected final String TEXT_48 = NL + "\tpublic void set";
  protected final String TEXT_49 = "(";
  protected final String TEXT_50 = " ";
  protected final String TEXT_51 = ");" + NL + "\t\t" + NL + "\t";
  protected final String TEXT_52 = NL + "\t   ";
  protected final String TEXT_53 = NL + "\t      ";
  protected final String TEXT_54 = NL + "\t   public Long ";
  protected final String TEXT_55 = "Id;" + NL + "\t   public Long get";
  protected final String TEXT_56 = "Id();" + NL + "\t      ";
  protected final String TEXT_57 = NL + "\t   ";
  protected final String TEXT_58 = NL + "\t";
  protected final String TEXT_59 = NL + "\t" + NL + "}";
  protected final String TEXT_60 = NL;

        private String _omPkg, _dmPkg;
        private static Vector eagerFetchPackages = getEagerFetchPackages();
	public String capFirst(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	public String uncapFirst(String s){
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
	private String getPackage(UmlClass klass) {
		        UmlPackage pkg = null;
		        pkg = UML13Utils.getModel(klass);
		        return UML13Utils.getNamespaceName(pkg, klass);
        }
	public String getGetterName(AssociationEnd ae){
		String name = ae.getName();
		if(name == null || name.trim().length() == 0){
			name = ae.getType().getName();
		}
		return "get" + capFirst(name);
	}
	public String getSetterName(AssociationEnd ae){
		String name = ae.getName();
		if(name == null || name.trim().length() == 0){
			name = ae.getType().getName();
		}
		return "set" + capFirst(name);
	}
	
	public boolean extendsOntology(Classifier klass) {
	boolean extendsOntology = false;
	TaggedValue ontologyTag = UML13Utils.getTaggedValue(klass,"implements-ontology");
	if (ontologyTag!=null) {
		if (ontologyTag.getValue().equals("yes")) {
	 	 extendsOntology=true;
		}
	}
	return extendsOntology;
	}
	
	public boolean extendsOntologyRelationship(Classifier klass) {
	boolean extendsOntologyRelationship = false;
		TaggedValue ontologyRelationshipTag = UML13Utils.getTaggedValue(klass,"implements-ontologyRelationship");
		if (ontologyRelationshipTag!=null) {
			if (ontologyRelationshipTag.getValue().equals("yes")) {
		 	 extendsOntologyRelationship=true;
			}
		}
		return extendsOntologyRelationship;
	}
	
	public String getMultipleRoleNames(AssociationEnd asEnd){
		String roleName = null;
		UmlAssociation ass = asEnd.getAssociation();
		TaggedValue opTag = null;
		if(UML13Utils.getTaggedValue(ass, "multiplerolename")!=null){
			opTag = UML13Utils.getTaggedValue(ass,"multiplerolename");
			roleName = opTag.getValue();
			if (roleName.equals("default")) {
			   roleName = capFirst(asEnd.getName());
			}   
		}		
		return roleName;
	}
	
	public String getQualifiedName(ModelElement me, String basePkg){

	String qName = null;
        UmlPackage pkg = null;
        if (basePkg != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(me), basePkg);
        } else {
            pkg = UML13Utils.getModel(me);
        }
        qName = UML13Utils.getNamespaceName(pkg, me) + "." + me.getName();
        return qName;		
	}
	
	public Attribute getIdAtt(UmlClass klass){
		Attribute idAtt = null;
		UmlClass superClass = klass;
		while(superClass != null && idAtt == null){
			idAtt = UML13Utils.getAttribute(superClass, "id");
			superClass = UML13Utils.getSuperClass(superClass);
		}
		return idAtt;
	}
	
	static private Vector getEagerFetchPackages()
	{
	    Vector vec = new Vector();
		try{
			Properties _properties = new Properties();
	
			_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));

			String eagerPackages = (String)_properties.getProperty("eager_fetch_many2one_packages");
			StringTokenizer tokens = new StringTokenizer(eagerPackages, ",");
			while (tokens.hasMoreTokens())
			{
				String temp = tokens.nextToken().trim();
//				System.out.println("adding into property vector = " + temp);
				vec.add(temp);
			}
		}
    	catch(Exception ex){	
		    ex.printStackTrace();
			System.out.println("getProperties Exception - "+ ex.getMessage());
		}
    	
    	return vec;
	}
	
	public boolean isPrimitive(String typeName)
	{
		if (typeName.equals("int"))
		{
			return true;
		}
		if (typeName.equals("long"))
		{
			return true;
		}
		if (typeName.equals("double"))
		{
			return true;
		}		
		if (typeName.equals("float"))
		{
			return true;
		}		
		return false;
	}

	public String execute(Map context){
		return generate(context);
	}
	
	public String generate(Map context)
  {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
Classifier klass = (Classifier)context.get("modelElement");
String basePkg = (String)context.get("basePackage");
String isAbstract = new String();
if (klass.isAbstract()) {
   isAbstract="abstract";
}

 if (isAbstract.equals("abstract")){
   
}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_4);
     UmlClass superClass = UML13Utils.getSuperClass((UmlClass)klass); 
	String superPkgName = null;
	if(superClass != null)
		superPkgName = UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(superClass), basePkg), superClass);

     if(superClass != null && !extendsOntologyRelationship(klass) && extendsOntology(klass)){ 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(superPkgName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(superClass.getName());
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
     if(superClass != null && extendsOntologyRelationship(klass) && !extendsOntology(klass)){ 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(superPkgName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(superClass.getName());
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_12);
     if(superClass != null && !extendsOntology(klass) && !extendsOntologyRelationship(klass)){ 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(superPkgName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(superClass.getName());
     } 
    stringBuffer.append(TEXT_15);
     if(superClass == null && extendsOntology(klass) && !extendsOntologyRelationship(klass)){ 
    stringBuffer.append(TEXT_16);
     } 
    stringBuffer.append(TEXT_17);
     if(superClass == null && !extendsOntology(klass) && extendsOntologyRelationship(klass)){ 
    stringBuffer.append(TEXT_18);
     } 
    stringBuffer.append(TEXT_19);
     for(Iterator i = UML13Utils.getAttributes((UmlClass)klass).iterator(); i.hasNext();){ 
    stringBuffer.append(TEXT_20);
     Attribute att = (Attribute)i.next(); 
    stringBuffer.append(TEXT_21);
    if(getQualifiedName(att.getType(), basePkg).startsWith(".")){
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    } else { 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(getQualifiedName(att.getType(),basePkg));
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_25);
     if(getQualifiedName(att.getType(), basePkg).startsWith(".")) {
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_27);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(getQualifiedName(att.getType(), basePkg));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_30);
     } 
    stringBuffer.append(TEXT_31);
     } 
    stringBuffer.append(TEXT_32);
     for(Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();){
    stringBuffer.append(TEXT_33);
     AssociationEnd thisEnd = (AssociationEnd)i.next(); 
    stringBuffer.append(TEXT_34);
     AssociationEnd otherEnd = (AssociationEnd)UML13Utils.getOtherAssociationEnd(thisEnd); 
    stringBuffer.append(TEXT_35);
     if(otherEnd.isNavigable()){ 
    stringBuffer.append(TEXT_36);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_37);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_38);
     }else if(UML13Utils.isMany2One(thisEnd, otherEnd) || UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_39);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_41);
     }else{ 
    stringBuffer.append(TEXT_42);
     if(true) throw new RuntimeException("Unknown association multiplicity: " + thisEnd.getType().getName() + "." + thisEnd.getName() + ".upper=" + ((MultiplicityRange)thisEnd.getMultiplicity().getRange().iterator().next()).getUpper() + ", " + otherEnd.getType().getName() + "." + otherEnd.getName() + ".upper=" + ((MultiplicityRange)otherEnd.getMultiplicity().getRange().iterator().next()).getUpper()); 
    stringBuffer.append(TEXT_43);
     } 
    stringBuffer.append(TEXT_44);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_45);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_47);
     }else if(UML13Utils.isMany2One(thisEnd, otherEnd) || UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_48);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_51);
     } 
    stringBuffer.append(TEXT_52);
     }else{//end if otherEnd.isNavigable() 
    stringBuffer.append(TEXT_53);
     if(otherEnd.getAggregation().equals(AggregationKindEnum.AK_AGGREGATE)){ 
    stringBuffer.append(TEXT_54);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_56);
     } 
    stringBuffer.append(TEXT_57);
     } 
    stringBuffer.append(TEXT_58);
     } 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    return stringBuffer.toString();
  }
}