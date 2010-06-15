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

public class BeanWS implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = ".ws;" + NL + "import gov.nih.nci.system.applicationservice.*;" + NL + "import java.util.*;" + NL + "/**" + NL + " * <!-- LICENSE_TEXT_START -->" + NL + " * <!-- LICENSE_TEXT_END -->" + NL + " */" + NL + "" + NL + "public ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " class ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = NL + "    extends ";
  protected final String TEXT_8 = ".ws.";
  protected final String TEXT_9 = NL + "\timplements java.io.Serializable" + NL + "{" + NL + "\tprivate static final long serialVersionUID = 1234567890L;" + NL + "" + NL + "\t";
  protected final String TEXT_10 = NL + "\t   ";
  protected final String TEXT_11 = NL + "\t   ";
  protected final String TEXT_12 = " ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = ";";
  protected final String TEXT_15 = " ";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL + "\t   public ";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = " get";
  protected final String TEXT_20 = "(){" + NL + "\t      return ";
  protected final String TEXT_21 = ";" + NL + "\t   }" + NL + "\t   " + NL + "\t   public void set";
  protected final String TEXT_22 = "(";
  protected final String TEXT_23 = " ";
  protected final String TEXT_24 = ")";
  protected final String TEXT_25 = " ";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = ")";
  protected final String TEXT_28 = "{" + NL + "\t      this.";
  protected final String TEXT_29 = " = ";
  protected final String TEXT_30 = ";" + NL + "\t   }" + NL + "\t";
  protected final String TEXT_31 = NL + NL + "\t";
  protected final String TEXT_32 = NL + "\t   ";
  protected final String TEXT_33 = NL + "\t   ";
  protected final String TEXT_34 = NL + "\t   ";
  protected final String TEXT_35 = NL + "\t      ";
  protected final String TEXT_36 = NL + "\t\t\tprivate java.util.Collection ";
  protected final String TEXT_37 = " = new java.util.HashSet();" + NL + "\t\t\tpublic java.util.Collection get";
  protected final String TEXT_38 = "(){" + NL + "\t              return ";
  protected final String TEXT_39 = ";" + NL + "\t          }" + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t\t\t   \t\t\t   " + NL + "\t      ";
  protected final String TEXT_40 = NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_41 = NL + "\t\t\t" + NL + "\t\t\t" + NL + "\t\t\tprivate ";
  protected final String TEXT_42 = " ";
  protected final String TEXT_43 = ";" + NL + "\t\t\tpublic ";
  protected final String TEXT_44 = " get";
  protected final String TEXT_45 = "(){" + NL + "\t\t\t  return ";
  protected final String TEXT_46 = ";" + NL + "                        }" + NL + "\t\t   " + NL + "\t      ";
  protected final String TEXT_47 = NL + "\t\t\t" + NL + "\t\t\t" + NL + "\t\t\tprivate ";
  protected final String TEXT_48 = " ";
  protected final String TEXT_49 = ";" + NL + "\t\t\tpublic ";
  protected final String TEXT_50 = " get";
  protected final String TEXT_51 = "(){" + NL + "\t\t\t  return ";
  protected final String TEXT_52 = ";\t\t\t" + NL + "                        }" + NL + "                        " + NL + "\t      ";
  protected final String TEXT_53 = NL + "\t\t     ";
  protected final String TEXT_54 = NL + "\t      ";
  protected final String TEXT_55 = NL + "\t               " + NL + "\t   ";
  protected final String TEXT_56 = NL + "\t   \tpublic void set";
  protected final String TEXT_57 = "(java.util.Collection ";
  protected final String TEXT_58 = "){" + NL + "\t   \t\tthis.";
  protected final String TEXT_59 = " = ";
  protected final String TEXT_60 = ";" + NL + "\t        }\t" + NL + "\t   ";
  protected final String TEXT_61 = NL + "\t   " + NL + "\t   ";
  protected final String TEXT_62 = NL + "\t   public void set";
  protected final String TEXT_63 = "(";
  protected final String TEXT_64 = " ";
  protected final String TEXT_65 = "){" + NL + "\t\tthis.";
  protected final String TEXT_66 = " = ";
  protected final String TEXT_67 = ";" + NL + "\t   }\t" + NL + "\t   ";
  protected final String TEXT_68 = NL + "\t   ";
  protected final String TEXT_69 = NL + "\t      ";
  protected final String TEXT_70 = NL + "\t   private Long ";
  protected final String TEXT_71 = "Id;" + NL + "\t   public Long get";
  protected final String TEXT_72 = "Id(){" + NL + "\t      return ";
  protected final String TEXT_73 = "Id;" + NL + "\t   }" + NL + "\t      ";
  protected final String TEXT_74 = NL + "\t   ";
  protected final String TEXT_75 = NL + "\t";
  protected final String TEXT_76 = NL + NL + "\t\tpublic boolean equals(Object obj){" + NL + "\t\t\tboolean eq = false;" + NL + "\t\t\tif(obj instanceof ";
  protected final String TEXT_77 = ") {" + NL + "\t\t\t\t";
  protected final String TEXT_78 = " c =(";
  protected final String TEXT_79 = ")obj; \t\t\t " + NL + "\t\t\t\t";
  protected final String TEXT_80 = " thisId = getId();\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_81 = NL + "\t\t\t\t    if(thisId == c.getId()){" + NL + "\t\t\t\t       eq = true;" + NL + "\t\t\t\t    }" + NL + "\t\t\t\t ";
  protected final String TEXT_82 = NL + "\t\t\t\t\tif(thisId != null && thisId.equals(c.getId())) {" + NL + "\t\t\t\t\t   eq = true;" + NL + "\t\t\t\t    }\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_83 = NL + "\t\t\t}" + NL + "\t\t\treturn eq;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tpublic int hashCode(){" + NL + "\t\t\tint h = 0;" + NL + "\t\t\t";
  protected final String TEXT_84 = NL + "\t\t\t h += (new Long(getId())).hashCode();" + NL + "\t\t\t";
  protected final String TEXT_85 = NL + "\t\t\tif(getId() != null) {" + NL + "\t\t\t\th += getId().hashCode();" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_86 = NL + "\t\t\treturn h;" + NL + "\t}" + NL + "\t" + NL + "\t" + NL + "}";
  protected final String TEXT_87 = NL;

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
String basePkg1 = null;
String isAbstract = new String();

if (klass.isAbstract()) {
  isAbstract="abstract";
} 
if (isAbstract.equals("abstract")){
   
}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_3);
     if (isAbstract.equals("abstract")) {
    stringBuffer.append(TEXT_4);
    stringBuffer.append(isAbstract);
    }
    stringBuffer.append(TEXT_5);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_6);
     UmlClass superClass = UML13Utils.getSuperClass((UmlClass)klass); 
     if(superClass != null){ 
    	String superPkgName = null;
	if(superClass != null)
		superPkgName = UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(superClass), basePkg), superClass);

    stringBuffer.append(TEXT_7);
    stringBuffer.append(superPkgName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(superClass.getName());
     } 
    stringBuffer.append(TEXT_9);
     for(Iterator i = UML13Utils.getAttributes((UmlClass)klass).iterator(); i.hasNext();){ 
    stringBuffer.append(TEXT_10);
     Attribute att = (Attribute)i.next(); 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(att.getVisibility().toString().substring(3));
    stringBuffer.append(TEXT_12);
     if(getQualifiedName(att.getType(), basePkg).startsWith(".")){
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_14);
    } else {
    stringBuffer.append(getQualifiedName(att.getType(), basePkg));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    if(getQualifiedName(att.getType(), basePkg).startsWith(".")){
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    } else { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(getQualifiedName(att.getType(),basePkg));
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_22);
     if(getQualifiedName(att.getType(), basePkg).startsWith(".")) {
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_24);
    } else {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(getQualifiedName(att.getType(), basePkg));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_27);
     } 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_30);
     } 
    stringBuffer.append(TEXT_31);
     for(Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();){
    stringBuffer.append(TEXT_32);
     AssociationEnd thisEnd = (AssociationEnd)i.next(); 
    stringBuffer.append(TEXT_33);
     AssociationEnd otherEnd = (AssociationEnd)UML13Utils.getOtherAssociationEnd(thisEnd); 
    stringBuffer.append(TEXT_34);
     if(otherEnd.isNavigable()){ 
    stringBuffer.append(TEXT_35);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_36);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_39);
     } else if( UML13Utils.isMany2One(thisEnd, otherEnd) ){ 
    stringBuffer.append(TEXT_40);
    
			String fullInterfaceName=getQualifiedName(otherEnd.getType(), basePkg);
		        String implName=fullInterfaceName.substring(fullInterfaceName.lastIndexOf(".")+1);
			String fullBeanName = fullInterfaceName.substring(0, fullInterfaceName.lastIndexOf("."))+".ws."+implName;
			
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fullBeanName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(fullBeanName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_46);
     }else if(UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    
			String fullInterfaceName=getQualifiedName(otherEnd.getType(), basePkg);
		        String implName=fullInterfaceName.substring(fullInterfaceName.lastIndexOf(".")+1);
			String fullBeanName = fullInterfaceName.substring(0, fullInterfaceName.lastIndexOf("."))+".ws."+implName;
			
    stringBuffer.append(TEXT_47);
    stringBuffer.append(fullBeanName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(fullBeanName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_52);
     }else{ 
    stringBuffer.append(TEXT_53);
     if(true) throw new RuntimeException("Unknown association multiplicity: " + thisEnd.getType().getName() + "." + thisEnd.getName() + ".upper=" + ((MultiplicityRange)thisEnd.getMultiplicity().getRange().iterator().next()).getUpper() + ", " + otherEnd.getType().getName() + "." + otherEnd.getName() + ".upper=" + ((MultiplicityRange)otherEnd.getMultiplicity().getRange().iterator().next()).getUpper()); 
    stringBuffer.append(TEXT_54);
     } 
    stringBuffer.append(TEXT_55);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_56);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_60);
     } else if(UML13Utils.isMany2One(thisEnd, otherEnd) || UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_61);
    
	   			String fullInterfaceName=getQualifiedName(otherEnd.getType(), basePkg);
	   		        String implName=fullInterfaceName.substring(fullInterfaceName.lastIndexOf(".")+1);
	   			String fullBeanName = fullInterfaceName.substring(0, fullInterfaceName.lastIndexOf("."))+".ws."+implName;
			
    stringBuffer.append(TEXT_62);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(fullBeanName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_67);
     } 
    stringBuffer.append(TEXT_68);
     }else{//end if otherEnd.isNavigable() 
    stringBuffer.append(TEXT_69);
     if(otherEnd.getAggregation().equals(AggregationKindEnum.AK_AGGREGATE)){ 
    stringBuffer.append(TEXT_70);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_73);
     } 
    stringBuffer.append(TEXT_74);
     } 
    stringBuffer.append(TEXT_75);
     } 
    stringBuffer.append(TEXT_76);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(getIdAtt((UmlClass)klass).getType().getName());
    stringBuffer.append(TEXT_80);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_81);
     } else { 
    stringBuffer.append(TEXT_82);
     } 
    stringBuffer.append(TEXT_83);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_84);
     } else { 
    stringBuffer.append(TEXT_85);
     } 
    stringBuffer.append(TEXT_86);
    stringBuffer.append(TEXT_87);
    return stringBuffer.toString();
  }
}