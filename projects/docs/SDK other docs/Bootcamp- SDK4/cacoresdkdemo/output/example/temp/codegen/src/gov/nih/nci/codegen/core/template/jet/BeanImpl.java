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

public class BeanImpl implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = ".impl;" + NL + "import ";
  protected final String TEXT_4 = ".*;" + NL + "/**" + NL + " * <!-- LICENSE_TEXT_START -->" + NL + " * <!-- LICENSE_TEXT_END -->" + NL + " */" + NL;
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "public class ";
  protected final String TEXT_7 = "Impl extends ";
  protected final String TEXT_8 = " " + NL + "{" + NL + "\t" + NL + "}";

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

    stringBuffer.append(TEXT_2);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(UML13JavaSourceHelper.getAllClassJavadoc(klass));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}