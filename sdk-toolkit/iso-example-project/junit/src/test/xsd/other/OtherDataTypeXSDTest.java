/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd.other;

import gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.BlNonNullDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetTelDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EdTextDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnOnDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnPnDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IntDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlPqDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.PqDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.RealDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.ScDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.StDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.StNtDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TelDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TelEmailDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TelPersonDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TelPhoneDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TelUrlDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class OtherDataTypeXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Other data types XSD Test Cases";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.other.datatype.xsd";
		doc = getDocument(schemaFileName);
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
	public void testTsDataType() throws Exception
	{
		Class<TsDataType> targetClass = TsDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TS");
		validateAttributeElement(targetClass, "value2", "TS");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testTelUrlDataType() throws Exception
	{
		Class<TelUrlDataType> targetClass = TelUrlDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TEL.Url");
		validateAttributeElement(targetClass, "value2", "TEL.Url");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testTelPhoneDataType() throws Exception
	{
		Class<TelPhoneDataType> targetClass = TelPhoneDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TEL.Phone");
		validateAttributeElement(targetClass, "value2", "TEL.Phone");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testTelPersonDataType() throws Exception
	{
		Class<TelPersonDataType> targetClass = TelPersonDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TEL.Person");
		validateAttributeElement(targetClass, "value2", "TEL.Person");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testTelEmailDataType() throws Exception
	{
		Class<TelEmailDataType> targetClass = TelEmailDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TEL.Email");
		validateAttributeElement(targetClass, "value2", "TEL.Email");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testTelDataType() throws Exception
	{
		Class<TelDataType> targetClass = TelDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "TEL");
		validateAttributeElement(targetClass, "value2", "TEL");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testStNtUrlDataType() throws Exception
	{
		Class<StNtDataType> targetClass = StNtDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "ST.NT");
		validateAttributeElement(targetClass, "value2", "ST.NT");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testStDataType() throws Exception
	{
		Class<StDataType> targetClass = StDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "ST");
		validateAttributeElement(targetClass, "value2", "ST");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testScDataType() throws Exception
	{
		Class<ScDataType> targetClass = ScDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "SC");
		validateAttributeElement(targetClass, "value2", "SC");
		validateAttributeElement(targetClass, "value3", "SC");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testRealDataType() throws Exception
	{
		Class<RealDataType> targetClass = RealDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "REAL");
		validateAttributeElement(targetClass, "value2", "REAL");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testPqDataType() throws Exception
	{
		Class<PqDataType> targetClass = PqDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "PQ");
		validateAttributeElement(targetClass, "value2", "PQ");
		validateAttributeElement(targetClass, "value3", "PQ");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIntDataType() throws Exception
	{
		Class<IntDataType> targetClass = IntDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "INT");
		validateAttributeElement(targetClass, "value2", "INT");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIiDataType() throws Exception
	{
		Class<IiDataType> targetClass = IiDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "II");
		validateAttributeElement(targetClass, "value2", "II");
		validateAttributeElement(targetClass, "value3", "II");
		validateAttributeElement(targetClass, "value4", "II");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testEdTextDataType() throws Exception
	{
		Class<EdTextDataType> targetClass = EdTextDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "ED.Text");
		validateAttributeElement(targetClass, "value2", "ED.Text");
		validateAttributeElement(targetClass, "value3", "ED");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testEdDataType() throws Exception
	{
		Class<EdDataType> targetClass = EdDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "ED");
		validateAttributeElement(targetClass, "value2", "ED");
		validateAttributeElement(targetClass, "value3", "ED");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testCdDataType() throws Exception
	{
		Class<CdDataType> targetClass = CdDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "CD");
		validateAttributeElement(targetClass, "value2", "CD");
		validateAttributeElement(targetClass, "value3", "CD");
		validateAttributeElement(targetClass, "value4", "CD");
		validateAttributeElement(targetClass, "value5", "CD");
		validateAttributeElement(targetClass, "value6", "CD");
		validateAttributeElement(targetClass, "value7", "CD");
		validateAttributeElement(targetClass, "value8", "CD");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAdDataType() throws Exception
	{
		Class<AdDataType> targetClass = AdDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "AD");
		validateAttributeElement(targetClass, "value2", "AD");
		validateAttributeElement(targetClass, "value3", "AD");
		validateAttributeElement(targetClass, "value4", "AD");
		validateAttributeElement(targetClass, "value5", "AD");
		validateAttributeElement(targetClass, "value6", "AD");
		validateAttributeElement(targetClass, "value7", "AD");
		validateAttributeElement(targetClass, "value8", "AD");
		validateAttributeElement(targetClass, "value9", "AD");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testBlNonNullDataType() throws Exception
	{
		Class<BlNonNullDataType> targetClass = BlNonNullDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "BL.NonNull");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testBlDataType() throws Exception
	{
		Class<BlDataType> targetClass = BlDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "BL");
		validateAttributeElement(targetClass, "value2", "BL");
	}	
	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIvlTsDataType() throws Exception
	{
		Class<IvlTsDataType> targetClass = IvlTsDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "IVL_TS");
		validateAttributeElement(targetClass, "value2", "IVL_TS");
		validateAttributeElement(targetClass, "value3", "IVL_TS");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIvlRealDataType() throws Exception
	{
		Class<IvlRealDataType> targetClass = IvlRealDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "IVL_REAL");
		validateAttributeElement(targetClass, "value2", "IVL_REAL");
		validateAttributeElement(targetClass, "value3", "IVL_REAL");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIvlPqDataType() throws Exception
	{
		Class<IvlPqDataType> targetClass = IvlPqDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "IVL_PQ");
		validateAttributeElement(targetClass, "value2", "IVL_PQ");
		validateAttributeElement(targetClass, "value3", "IVL_PQ");
		validateAttributeElement(targetClass, "value4", "IVL_PQ");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testIvlIntDataType() throws Exception
	{
		Class<IvlIntDataType> targetClass = IvlIntDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "IVL_INT");
		validateAttributeElement(targetClass, "value2", "IVL_INT");
		validateAttributeElement(targetClass, "value3", "IVL_INT");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testEnPnDataType() throws Exception
	{
		Class<EnPnDataType> targetClass = EnPnDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "EN.PN");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testEnOnDataType() throws Exception
	{
		Class<EnOnDataType> targetClass = EnOnDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "EN.ON");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testEnDataType() throws Exception
	{
		Class<EnDataType> targetClass = EnDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "EN");
		validateAttributeElement(targetClass, "value2", "EN");
		validateAttributeElement(targetClass, "value3", "EN");
		validateAttributeElement(targetClass, "value4", "EN");
		validateAttributeElement(targetClass, "value5", "EN");
		validateAttributeElement(targetClass, "value6", "EN");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testDsetTelDataType() throws Exception
	{
		Class<DsetTelDataType> targetClass = DsetTelDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "DSET_TEL");
		validateAttributeElement(targetClass, "value2", "DSET_TEL");
		validateAttributeElement(targetClass, "value3", "DSET_TEL");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testDsetIiDataType() throws Exception
	{
		Class<DsetIiDataType> targetClass = DsetIiDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "DSET_II");
		validateAttributeElement(targetClass, "value2", "DSET_II");
		validateAttributeElement(targetClass, "value3", "DSET_II");
		validateAttributeElement(targetClass, "value4", "DSET_II");
		validateAttributeElement(targetClass, "value5", "DSET_II");
		validateAttributeElement(targetClass, "value6", "DSET_II");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testDsetCdDataType() throws Exception
	{
		Class<DsetCdDataType> targetClass = DsetCdDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "DSET_CD");
		validateAttributeElement(targetClass, "value2", "DSET_CD");
		validateAttributeElement(targetClass, "value3", "DSET_CD");
		validateAttributeElement(targetClass, "value4", "DSET_CD");
		validateAttributeElement(targetClass, "value5", "DSET_CD");
		validateAttributeElement(targetClass, "value6", "DSET_CD");
		validateAttributeElement(targetClass, "value7", "DSET_CD");
	}	

	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testDsetAdDataType() throws Exception
	{
		Class<DsetAdDataType> targetClass = DsetAdDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "value1", "DSET_AD");
		validateAttributeElement(targetClass, "value2", "DSET_AD");
		validateAttributeElement(targetClass, "value3", "DSET_AD");
		validateAttributeElement(targetClass, "value4", "DSET_AD");
		validateAttributeElement(targetClass, "value5", "DSET_AD");
		validateAttributeElement(targetClass, "value6", "DSET_AD");
		validateAttributeElement(targetClass, "value7", "DSET_AD");
		validateAttributeElement(targetClass, "value8", "DSET_AD");
	}	

}
