package test.xsd;

import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.IceCream;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert;

import org.jdom.Document;

import test.xsd.SDKXSDTestBase;

public class DifferentPackageWithAssociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Different Parent With Association XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName=null;
		if (useGMETags){
			schemaFileName="domain.differentpackage.associations.xsd";
			String namespacePrefix="gme://test.test/1.5/";
			doc = getDocument(namespacePrefix,schemaFileName);
		} else{
			schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.xsd";
			doc = getDocument(schemaFileName);
		}
	}

	public Document getDoc() {
		return doc;
	}
	
	/**
	 * Uses xpath to query XSD
	 * Verifies that common XSD elements are present 
	 * 
	 * @throws Exception
	 */
	public void testCommonSchemaElements() throws Exception
	{
		validateCommonSchemaElements();
	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement1() throws Exception
	{
		Class targetClass = Dessert.class;
		
		if (useGMETags){
			validateClassElements(targetClass,"DessertAlias");
			validateAttributeElement(targetClass,"DessertAlias", "idAlias", "Integer");
		} else {
			validateClassElements(targetClass);
			validateAttributeElement(targetClass, "id", "Integer");
		}
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Subclass are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement2() throws Exception
	{
		if (!useGMETags){
			String schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.xsd";
			doc = getDocument(schemaFileName);
		}

		Class targetClass = Pie.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"PieAlias","DessertAlias");
			validateSubclassAttributeElement(targetClass,"PieAlias","DessertAlias", "fillingAlias","String");			
		} else {
			validateSubclassElements(targetClass);
			validateSubclassAttributeElement(targetClass, "filling","String");
		}
	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Subclass are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement3() throws Exception
	{
		if (!useGMETags){
			String schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.xsd";
			doc = getDocument(schemaFileName);
		}
		
		Class targetClass = IceCream.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"IceCreamAlias","DessertAlias");
			validateSubclassAttributeElement(targetClass,"IceCreamAlias","DessertAlias","toppingAlias","String");			
		} else {
			validateSubclassElements(targetClass);
			validateSubclassAttributeElement(targetClass, "topping","String");
		}

	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement4() throws Exception
	{
		if (!useGMETags){
			String schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.xsd";
			doc = getDocument(schemaFileName);
		}
		
		Class targetClass = Utensil.class;
		
		if (useGMETags){
			validateClassElements(targetClass,"UtensilAlias");
			validateAttributeElement(targetClass,"UtensilAlias", "idAlias", "Integer");
			validateAttributeElement(targetClass,"UtensilAlias", "nameAlias", "String");
		} else {
			validateClassElements(targetClass);
			validateAttributeElement(targetClass, "id", "Integer");
			validateAttributeElement(targetClass, "name", "String");
		}
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements1() throws Exception
	{
		if (!useGMETags){
			String schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.xsd";
			doc = getDocument(schemaFileName);
		}
		
		Class targetClass = Dessert.class;
		Class associatedClass = Utensil.class;
		
		if (useGMETags){
			validateClassAssociationElements(targetClass,"DessertAlias", associatedClass,"UtensilAlias","utensilAliasRoleName","0","unbounded");
		} else {
			validateClassAssociationElements(targetClass,"Dessert", associatedClass,"gov.nih.nci.cacoresdk.domain.other.differentpackage.associations:Utensil","utensilCollection","0","unbounded");
		}

	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements2() throws Exception
	{
		if (!useGMETags){
			String schemaFileName="gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.xsd";
			doc = getDocument(schemaFileName);
		}
		
		Class targetClass = Utensil.class;
		Class associatedClass = Dessert.class;
		
		if (useGMETags){
			validateClassAssociationElements(targetClass,"UtensilAlias", associatedClass,"DessertAlias","dessertAliasRoleName","0","unbounded");
		} else {
			validateClassAssociationElements(targetClass,"Utensil", associatedClass,"gov.nih.nci.cacoresdk.domain.other.differentpackage:Dessert","dessertCollection","0","unbounded");
		}
	}	
}
