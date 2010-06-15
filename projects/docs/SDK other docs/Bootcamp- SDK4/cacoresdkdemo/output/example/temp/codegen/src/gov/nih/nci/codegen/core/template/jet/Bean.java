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

public class Bean implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import ";
  protected final String TEXT_3 = ".*;" + NL + "import gov.nih.nci.system.applicationservice.*;" + NL + "import gov.nih.nci.common.util.HQLCriteria;" + NL + "import java.util.*;" + NL + "import org.apache.log4j.Logger;" + NL + "" + NL + "/**" + NL + " * <!-- LICENSE_TEXT_START -->" + NL + " * <!-- LICENSE_TEXT_END -->" + NL + " */" + NL + " ";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "public ";
  protected final String TEXT_6 = " abstract ";
  protected final String TEXT_7 = " class ";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = " extends ";
  protected final String TEXT_10 = ".";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = "\timplements java.io.Serializable " + NL + "{" + NL + "\t/**" + NL + "\t* An attribute to allow serialization of the domain objects" + NL + "\t*/" + NL + "\tprivate static final long serialVersionUID = 1234567890L;" + NL + "\t";
  protected final String TEXT_13 = NL + "\t";
  protected final String TEXT_14 = NL + "\t";
  protected final String TEXT_15 = "\t" + NL + "\t";
  protected final String TEXT_16 = NL + "\t";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = ";" + NL + "\t" + NL + "\t";
  protected final String TEXT_20 = NL + "\tpublic ";
  protected final String TEXT_21 = " get";
  protected final String TEXT_22 = "()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_23 = ";" + NL + "\t}" + NL + "\t" + NL + "\t";
  protected final String TEXT_24 = "\t" + NL + "\tpublic void set";
  protected final String TEXT_25 = "(";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = ")" + NL + "\t{" + NL + "\t\tthis.";
  protected final String TEXT_28 = " = ";
  protected final String TEXT_29 = ";" + NL + "\t}";
  protected final String TEXT_30 = NL + "\t";
  protected final String TEXT_31 = NL + "\t";
  protected final String TEXT_32 = NL + "\t\t";
  protected final String TEXT_33 = NL + "\t\t";
  protected final String TEXT_34 = NL + "\t";
  protected final String TEXT_35 = "\t\t" + NL + "\tprivate java.util.Collection ";
  protected final String TEXT_36 = " = new java.util.HashSet();" + NL + "\t" + NL + "\t";
  protected final String TEXT_37 = "\t\t" + NL + "\tpublic java.util.Collection get";
  protected final String TEXT_38 = "()" + NL + "\t{" + NL + "\t\tif (";
  protected final String TEXT_39 = "==null || ";
  protected final String TEXT_40 = ".getClass().getName().indexOf(\"PersistentSet\")>0)\t\t" + NL + "\t\t{" + NL + "\t      try " + NL + "\t\t\t{" + NL + "\t\t\t\tString idString = (Class.forName(\"java.lang.String\").isInstance(getId()))? \"'\"+ getId() + \"'\" : \"\"+getId(); " + NL + "\t\t\t\tString hql = \"select parent.";
  protected final String TEXT_41 = " from ";
  protected final String TEXT_42 = " as parent where parent.id=\"+idString;" + NL + "\t\t\t\t" + NL + "\t\t\t\tHQLCriteria hqlCriteria = new HQLCriteria(hql);" + NL + "\t\t\t\tApplicationService applicationService = ApplicationServiceProvider.getApplicationService();" + NL + "\t\t\t\tjava.util.List resultList = applicationService.query(hqlCriteria,\"";
  protected final String TEXT_43 = "\");\t\t\t\t " + NL + "\t\t\t\t";
  protected final String TEXT_44 = " = resultList;\t " + NL + "\t\t\t}" + NL + "\t\t\tcatch(Exception ex) " + NL + "\t\t\t{" + NL + "\t\t\t\tLogger log = Logger.getLogger(";
  protected final String TEXT_45 = ".class.getName());" + NL + "\t\t\t\tlog.error(\"";
  protected final String TEXT_46 = ":get";
  protected final String TEXT_47 = " throws exception ... ...\",ex);" + NL + "\t\t\t}" + NL + "\t\t}\t" + NL + "\t\treturn ";
  protected final String TEXT_48 = ";" + NL + "\t}" + NL + "\t" + NL + "\t";
  protected final String TEXT_49 = "\t\t" + NL + "\tpublic void set";
  protected final String TEXT_50 = "(java.util.Collection ";
  protected final String TEXT_51 = ")" + NL + "\t{" + NL + "\t\tthis.";
  protected final String TEXT_52 = " = ";
  protected final String TEXT_53 = ";" + NL + "\t}\t" + NL + "\t\t";
  protected final String TEXT_54 = NL + "\t\t";
  protected final String TEXT_55 = NL + "\t";
  protected final String TEXT_56 = "\t\t" + NL + "\tprivate ";
  protected final String TEXT_57 = " ";
  protected final String TEXT_58 = ";" + NL + "" + NL + "\t";
  protected final String TEXT_59 = "\t\t" + NL + "\tpublic ";
  protected final String TEXT_60 = " get";
  protected final String TEXT_61 = "()" + NL + "\t{" + NL + "\t\t\t";
  protected final String TEXT_62 = NL + "\t\treturn ";
  protected final String TEXT_63 = ";\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_64 = NL + "\t\tif(";
  protected final String TEXT_65 = "==null ||  ";
  protected final String TEXT_66 = ".getClass().getName().indexOf('$')>0)" + NL + "\t\t{\t\t\t" + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\tString idString = (Class.forName(\"java.lang.String\").isInstance(getId()))? \"'\"+ getId() + \"'\" : \"\"+getId(); " + NL + "\t\t\t\tString hql = \"select parent.";
  protected final String TEXT_67 = " from ";
  protected final String TEXT_68 = " as parent where parent.id=\"+idString;" + NL + "\t\t\t\tHQLCriteria hqlCriteria = new HQLCriteria(hql);" + NL + "\t\t\t\tApplicationService applicationService = ApplicationServiceProvider.getApplicationService();" + NL + "\t\t\t\tjava.util.List resultList = applicationService.query(hqlCriteria,\"";
  protected final String TEXT_69 = "\");\t\t\t\t " + NL + "\t\t\t\tif (resultList!=null && resultList.size()>0) " + NL + "\t\t\t\t\t";
  protected final String TEXT_70 = " = (";
  protected final String TEXT_71 = ")resultList.get(0);" + NL + "\t\t\t\telse" + NL + "\t\t\t\t\t";
  protected final String TEXT_72 = " = null;" + NL + "\t\t\t}" + NL + "\t\t\tcatch(Exception ex) " + NL + "\t\t\t{ " + NL + "\t\t\t\tLogger log = Logger.getLogger(";
  protected final String TEXT_73 = ".class.getName());" + NL + "\t\t\t\tlog.error(\"";
  protected final String TEXT_74 = ":get";
  protected final String TEXT_75 = " throws exception ... ...\",ex);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_76 = ";\t" + NL + "\t\t\t";
  protected final String TEXT_77 = "\t\t" + NL + "\t}" + NL + "" + NL + "\t";
  protected final String TEXT_78 = "\t\t" + NL + "\tpublic void set";
  protected final String TEXT_79 = "(";
  protected final String TEXT_80 = " ";
  protected final String TEXT_81 = ")" + NL + "\t{" + NL + "\t\tthis.";
  protected final String TEXT_82 = " = ";
  protected final String TEXT_83 = ";" + NL + "\t}" + NL + "\t\t";
  protected final String TEXT_84 = NL + "\t\t\t";
  protected final String TEXT_85 = NL + "\t\t";
  protected final String TEXT_86 = NL + "\t";
  protected final String TEXT_87 = NL + "\t\t";
  protected final String TEXT_88 = NL + "\tprivate Long ";
  protected final String TEXT_89 = "Id;" + NL + "\tpublic Long get";
  protected final String TEXT_90 = "Id()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_91 = "Id;" + NL + "\t}" + NL + "\t\t";
  protected final String TEXT_92 = NL + "\t";
  protected final String TEXT_93 = NL + NL + "\tpublic boolean equals(Object obj)" + NL + "\t{" + NL + "\t\tboolean eq = false;" + NL + "\t\tif(obj instanceof ";
  protected final String TEXT_94 = ") " + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_95 = " c =(";
  protected final String TEXT_96 = ")obj; \t\t\t " + NL + "\t\t\t";
  protected final String TEXT_97 = " thisId = getId();\t\t" + NL + "\t\t\t";
  protected final String TEXT_98 = NL + "\t\t\tif(thisId == c.getId())" + NL + "\t\t\t\teq = true;" + NL + "\t\t\t";
  protected final String TEXT_99 = NL + "\t\t\tif(thisId != null && thisId.equals(c.getId()))" + NL + "\t\t\t\teq = true;" + NL + "\t\t\t";
  protected final String TEXT_100 = NL + "\t\t\t}" + NL + "\t\t\treturn eq;" + NL + "\t\t}" + NL + "\t\t" + NL + "\tpublic int hashCode()" + NL + "\t{" + NL + "\t\tint h = 0;" + NL + "\t\t";
  protected final String TEXT_101 = NL + "\t\th += (new Long(getId())).hashCode();" + NL + "\t\t";
  protected final String TEXT_102 = NL + "\t\tif(getId() != null)" + NL + "\t\t\th += getId().hashCode();" + NL + "\t\t";
  protected final String TEXT_103 = NL + "\t\treturn h;" + NL + "\t}" + NL + "}";

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
    
	Classifier klass = (Classifier)context.get("modelElement");
	String basePkg = (String)context.get("basePackage");
	UmlClass superClass = UML13Utils.getSuperClass((UmlClass)klass);
	String pkgName = UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass);
	String fullyQualifiedClassName = pkgName + "." + klass.getName();
	String superPkgName = null;
	if(superClass != null)
		superPkgName = UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(superClass), basePkg), superClass);
	

    stringBuffer.append(TEXT_1);
    stringBuffer.append(pkgName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(pkgName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(UML13JavaSourceHelper.getClassJavadoc(klass));
    stringBuffer.append(TEXT_5);
     if (klass.isAbstract()){
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_8);
     if(superClass != null){ 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(superPkgName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(superClass.getName());
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_12);
    //Iterate through all the attributes and generate getters and setters for the simple attributes
     for(Iterator i = UML13Utils.getAttributes((UmlClass)klass).iterator(); i.hasNext();){ 
    stringBuffer.append(TEXT_13);
     Attribute att = (Attribute)i.next(); 
    stringBuffer.append(TEXT_14);
     String attTypeName = (getQualifiedName(att.getType(), basePkg).startsWith("."))?getQualifiedName(att.getType(), basePkg).substring(1)+att.getName():getQualifiedName(att.getType(), basePkg); 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(UML13JavaSourceHelper.getAttributeJavadoc(att));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(att.getVisibility().toString().substring(3));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(attTypeName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(UML13JavaSourceHelper.getAttributeJavadocGetter(att));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(attTypeName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(UML13JavaSourceHelper.getAttributeJavadocSetter(att));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(attTypeName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_29);
     } 
    //Iterate through all the association and generate getters and setters for the association
     for(Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();){
    stringBuffer.append(TEXT_30);
     AssociationEnd thisEnd = (AssociationEnd)i.next();
		AssociationEnd otherEnd = (AssociationEnd)UML13Utils.getOtherAssociationEnd(thisEnd);
  		String fullyQualifiedTargetClassName = getQualifiedName(otherEnd.getType(), basePkg);
  	
    stringBuffer.append(TEXT_31);
     if(otherEnd.isNavigable()){ 
    stringBuffer.append(TEXT_32);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_33);
    //Other end of the association is MANY
    stringBuffer.append(TEXT_34);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadoc(otherEnd.getType().getName(), true));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadocGetter(otherEnd.getName()));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(fullyQualifiedClassName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadocSetter(otherEnd.getName()));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_53);
     } else if( UML13Utils.isMany2One(thisEnd, otherEnd)|| UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_54);
    //Other end of the association is ONE
    stringBuffer.append(TEXT_55);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadoc(otherEnd.getType().getName(), false));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadocGetter(otherEnd.getName()));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_61);
     if (eagerFetchPackages.contains(pkgName)) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_63);
     } else { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(fullyQualifiedClassName);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_76);
     } 
    stringBuffer.append(TEXT_77);
    stringBuffer.append(UML13JavaSourceHelper.getAssociationAttributeJavadocSetter(otherEnd.getName()));
    stringBuffer.append(TEXT_78);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_79);
    stringBuffer.append(fullyQualifiedTargetClassName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_83);
     }else{ 
    stringBuffer.append(TEXT_84);
     if(true) throw new RuntimeException("Unknown association multiplicity: " + thisEnd.getType().getName() + "." + thisEnd.getName() + ".upper=" + ((MultiplicityRange)thisEnd.getMultiplicity().getRange().iterator().next()).getUpper() + ", " + otherEnd.getType().getName() + "." + otherEnd.getName() + ".upper=" + ((MultiplicityRange)otherEnd.getMultiplicity().getRange().iterator().next()).getUpper()); 
    stringBuffer.append(TEXT_85);
     } 
    stringBuffer.append(TEXT_86);
     }else{//end if otherEnd.isNavigable() 
    stringBuffer.append(TEXT_87);
     if(otherEnd.getAggregation().equals(AggregationKindEnum.AK_AGGREGATE)){ 
    stringBuffer.append(TEXT_88);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_91);
     } 
    stringBuffer.append(TEXT_92);
     } 
     } 
    stringBuffer.append(TEXT_93);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_95);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_96);
    stringBuffer.append(getIdAtt((UmlClass)klass).getType().getName());
    stringBuffer.append(TEXT_97);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_98);
     } else { 
    stringBuffer.append(TEXT_99);
     } 
    stringBuffer.append(TEXT_100);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_101);
     } else { 
    stringBuffer.append(TEXT_102);
     } 
    stringBuffer.append(TEXT_103);
    return stringBuffer.toString();
  }
}