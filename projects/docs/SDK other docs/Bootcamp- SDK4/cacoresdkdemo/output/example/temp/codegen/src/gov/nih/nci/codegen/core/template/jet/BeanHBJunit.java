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

public class BeanHBJunit implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = NL + "package ";
  protected final String TEXT_2 = ".impl.test;" + NL + "" + NL + "import org.hibernate.HibernateException;" + NL + "import org.hibernate.Session;" + NL + "import org.hibernate.Transaction;" + NL + "import org.apache.log4j.Logger;" + NL + "import junit.framework.TestCase;" + NL + "import gov.nih.nci.common.util.HibernateUtil;" + NL + "" + NL + "/**" + NL + " * <!-- LICENSE_TEXT_START -->" + NL + " * <!-- LICENSE_TEXT_END -->" + NL + " */" + NL + " " + NL + "public class ";
  protected final String TEXT_3 = "Test extends TestCase{" + NL + "" + NL + "    private static Logger _logger = Logger.getLogger(";
  protected final String TEXT_4 = "Test.class);" + NL + "    " + NL + "\tpublic static final Class CLASS = ";
  protected final String TEXT_5 = "Test.class;" + NL + "" + NL + "    public ";
  protected final String TEXT_6 = "Test(String testName) {" + NL + "        super(testName);" + NL + "    }" + NL + "" + NL + "\tpublic static void main(String[] args) {" + NL + "\t\ttry {" + NL + "\t\t\tjunit.textui.TestRunner.main(new String[] { CLASS.getName() });" + NL + "\t\t} catch (Exception ex) {" + NL + "\t\t\tSystem.out.println(\"Error: \" + ex.getMessage());" + NL + "\t\t\tex.printStackTrace();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\tpublic static junit.framework.Test suite() {" + NL + "\t\tjunit.framework.TestSuite suite = new junit.framework.TestSuite();" + NL + "" + NL + "\t\t//suite.addTest(new ";
  protected final String TEXT_7 = "Test(\"testPopulate\"));" + NL + "\t\tsuite.addTest(new ";
  protected final String TEXT_8 = "Test(\"testRetrieve\"));" + NL + "\t\t" + NL + "\t\treturn suite;" + NL + "\t} " + NL + "" + NL + "\tpublic void testPopulate(){" + NL + "\t\ttry{" + NL + "\t\t        ";
  protected final String TEXT_9 = ".ws.";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " = new ";
  protected final String TEXT_12 = ".ws.";
  protected final String TEXT_13 = "() ;" + NL + "\t\t\tsave(";
  protected final String TEXT_14 = ");" + NL + "\t\t}catch(Exception ex){" + NL + "\t\t    ex.printStackTrace();" + NL + "\t\t    fail(\"Error retrieving ";
  protected final String TEXT_15 = ". Got \" + ex.getClass().getName() + \": \" + ex.getMessage());" + NL + "\t\t" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\tpublic void testRetrieve(){" + NL + "\t\ttry{" + NL + "\t\t\tSession sess = gov.nih.nci.common.util.HibernateUtil.currentSession();" + NL + "\t\t\tTransaction tx = sess.beginTransaction();" + NL + "\t\t\t" + NL + "\t\t\tsess.createCriteria(";
  protected final String TEXT_16 = ".class).list();" + NL + "\t\t\ttx.commit();" + NL + "\t\t\tgov.nih.nci.common.util.HibernateUtil.closeSession();" + NL + "\t\t}catch(Exception ex){" + NL + "\t\t    ex.printStackTrace();" + NL + "\t\t    fail(\"Error retrieving ";
  protected final String TEXT_17 = ". Got \" + ex.getClass().getName() + \": \" + ex.getMessage());" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "    public void save(Object obj) {" + NL + "" + NL + "        Transaction tx = null;" + NL + "        Session sess = null;" + NL + "        boolean success = true;" + NL + "" + NL + "" + NL + "\t((";
  protected final String TEXT_18 = ")obj).setId(new ";
  protected final String TEXT_19 = "(\"1\"));" + NL + "" + NL + "        try {" + NL + "            sess = gov.nih.nci.common.util.HibernateUtil.currentSession();" + NL + "            tx = sess.beginTransaction();" + NL + "            sess.saveOrUpdate(obj);" + NL + "        } catch (Exception ex) {" + NL + "            ex.printStackTrace();" + NL + "            success = false;" + NL + "            try {" + NL + "                tx.rollback();" + NL + "            } catch (Exception ex2) {" + NL + "                throw new RuntimeException(\"Error rolling back.\", ex2);" + NL + "            }" + NL + "        } finally {" + NL + "            try {" + NL + "                if (success && tx != null) {" + NL + "                    tx.commit();" + NL + "                }" + NL + "                gov.nih.nci.common.util.HibernateUtil.closeSession();" + NL + "            } catch (Exception ex) {" + NL + "                throw new RuntimeException(\"Error cleaning up\", ex);" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}" + NL + "   " + NL;
  protected final String TEXT_20 = NL + NL;
  protected final String TEXT_21 = NL;

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
String isAbstract = new String();
int endPackage = getQualifiedName(klass, basePkg).lastIndexOf(".");
String tempPackage = getQualifiedName(klass, basePkg).substring(0,endPackage);
String className = getQualifiedName(klass, basePkg).substring(endPackage);
String finalPackageClass = tempPackage + ".ws" + className;
		
if (klass.isAbstract()) {
  isAbstract="abstract";
} 
if (!isAbstract.equals("abstract")){ 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(uncapFirst(klass.getName()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(uncapFirst(klass.getName()));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(finalPackageClass);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(finalPackageClass);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(getQualifiedName(getIdAtt((UmlClass)klass).getType(), basePkg));
    stringBuffer.append(TEXT_19);
     } else { }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}