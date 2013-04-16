/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.mapping;

import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.IceCream;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert;

import org.jdom.Document;


public class DifferentPackageWithAssociationXMLMappingTest extends SDKXMLMappingTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Different Package With Association XML Mapping Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String xmlMappingFileName = "xml-mapping.xml";
		doc = getDocument(xmlMappingFileName);
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
			validateClassElements(targetClass,"DessertAlias","id");
			validateFieldElement(targetClass, "id","idAlias", "Integer");
		} else{
			validateClassElements(targetClass,"id");
			validateFieldElement(targetClass, "id", "Integer");
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
		Class targetClass = Pie.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"PieAlias","id");
			validateFieldElement(targetClass, "filling","fillingAlias","String");
		} else{
			validateSubclassElements(targetClass,"id");
			validateFieldElement(targetClass, "filling","String");
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
		Class targetClass = IceCream.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"IceCreamAlias","id");
			validateFieldElement(targetClass, "topping","toppingAlias","String");
		} else{
			validateSubclassElements(targetClass,"id");
			validateFieldElement(targetClass, "topping","String");
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
		Class targetClass = Utensil.class;
		
		if (useGMETags){
			validateClassElements(targetClass,"UtensilAlias","id");
			validateFieldElement(targetClass, "id","idAlias","Integer");
			validateFieldElement(targetClass, "name","nameAlias","String");
		} else{
			validateClassElements(targetClass,"id");
			validateFieldElement(targetClass, "id", "Integer");
			validateFieldElement(targetClass, "name", "String");
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
		Class targetClass = Dessert.class;
		Class associatedClass = Utensil.class;
		
		if (useGMETags){
			validateClassAssociationElements(targetClass,"DessertAlias", associatedClass,"UtensilAlias","utensilCollection","utensilAliasRoleName",true);
		} else{
			validateClassAssociationElements(targetClass, associatedClass,"utensilCollection",true);
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
		Class targetClass = Utensil.class;
		Class associatedClass = Dessert.class;
		
		if (useGMETags){
			validateClassAssociationElements(targetClass,"UtensilAlias", associatedClass, "DessertAlias","dessertCollection","dessertAliasRoleName",true);
		} else{
			validateClassAssociationElements(targetClass, associatedClass, "dessertCollection",true);
		}
	}		
}
