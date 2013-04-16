/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Element;

public class HtmlUtils {

	private static Logger log = Logger.getLogger(HtmlUtils.class.getName());

	private static String HTML_SCREEN_BEGIN = "<table cellspacing=\"0\" cellpadding=\"3\" border=\"1\" width=\"100%\" >";
	private static String HTML_SCREEN_END = "</table>";

	private static String booleanSelect = null;
	private static List<String> booleanOptions = new ArrayList<String>(Arrays.asList("","True (Yes)","False (No)"));
	private static List<String> booleanValues = new ArrayList<String>(Arrays.asList("","true","false"));

	private static String compressionSelect = null;
	private static List<String> compressionOptions = new ArrayList<String>(Arrays.asList("","DF (deflate)","GZ (gzip)","ZL (zlib)","Z (compress)","BZ (bzip)","Z7 (z7)"));
	private static List<String> compressionValues = new ArrayList<String>(Arrays.asList("","DF","GZ","ZL","Z","BZ","Z7"));

	private static String identifierReliabilitySelect = null;
	private static List<String> identifierReliabilityOptions = new ArrayList<String>(Arrays.asList("","ISS (issued by system)","VRF (verified by system)","UNV (unverified by system)"));
	private static List<String> identifierReliabilityValues = new ArrayList<String>(Arrays.asList("","ISS","VRF","UNV"));

	private static String identifierScopeSelect = null;
	private static List<String> identifierScopeOptions = new ArrayList<String>(Arrays.asList("","BUSN (business identifier)","OBJ (object identifier)","VER (version identifier)","VW (view specific identifier)"));
	private static List<String> identifierScopeValues = new ArrayList<String>(Arrays.asList("","BUSN","OBJ","VER","VW"));

	private static String nullFlavorSelect = null;
	private static List<String> nullFlavorOptions = new ArrayList<String>(Arrays.asList("","NI (No Information)","INV (Invalid)","OTH (Other)","NINF (Negative Infinity)","PINF (Positive Infinity)","UNC (Unencoded)","DER (Derived)","UNK (Unknown)","ASKU (Asked but Unknown)","NAV (Temporarily Unavailable)","QS (Sufficient Quantity)","NASK (Not Asked)","TRC (Trace)","MSK (Masked)","NA (Not Applicable)"));
	private static List<String> nullFlavorValues = new ArrayList<String>(Arrays.asList("","NI","INV","OTH","NINF","PINF","UNC","DER","UNK","ASKU","NAV","QS","NASK","TRC","MSK","NA"));

	private static Map<String,String> addressPartTypeOptionMap = new HashMap<String,String>();
	private static String addressPartTypeSelect = null;
	private static List<String> addressPartTypeOptions = new ArrayList<String>(Arrays.asList("","AL (address line)","ADL (additional locator)","UNID (unit identifier)","UNIT (unit designator)","DAL (delivery address line)","DINST (delivery installation type)","DINSTA (delivery installation area)","DINSTQ (delivery installation qualifier)","DMOD (delivery mode)","DMODID (delivery mode identifier)","SAL (street address line)","BNR (building number)","BNN (building number numeric)","BNS (building number suffix)","STR (street name)","STB (street name base)","STTYP (street type)","DIR (direction)","CAR (care of)","CEN (census tract)","CNT (country)","CPA (county or parish)","CTY (municipality)","DEL (delimiter)","POB (post box)","PRE (precinct)","STA (state or province)","ZIP (postal code)"));
	private static List<String> addressPartTypeValues = new ArrayList<String>(Arrays.asList("","AL","ADL","UNID","UNIT","DAL","DINST)","DINSTA","DINSTQ","DMOD","DMODID","SAL","BNR","BNN","BNS","STR","STB","STTYP","DIR","CAR","CEN","CNT","CPA","CTY","DEL","POB","PRE","STA","ZIP"));

	private static Map<String,String> entityNamePartTypeOptionMap = new HashMap<String,String>();
	private static String entityNamePartTypeSelect = null;
	private static List<String> entityNamePartTypeOptions = new ArrayList<String>(Arrays.asList("","FAM (family)","GIV (given)","PFX (prefix)","SFX (suffix)","DEL (delimiter)"));
	private static List<String> entityNamePartTypeValues = new ArrayList<String>(Arrays.asList("","FAM","GIV","PFX","SFX","DEL"));

	private static String entityNamePartQualifierSelect = null;
	private static List<String> entityNamePartQualifierOptions = new ArrayList<String>(Arrays.asList("","LS (legal status)","AC (academic)","NB (nobility)","PR (professional)","VV (voorvoegsel)","AD (adopted)","BR (birth)","SP (spouse)","CL (callme)","IN (initial)","TITLE (title)"));
	private static List<String> entityNamePartQualifierValues = new ArrayList<String>(Arrays.asList("","LS","AC","NB","PR","VV","AD","BR","SP","CL","IN","TITLE"));

	// todo :: make AddressPartType required if any other Address (AD) field is populated
	private static String getSelect_AddressPartType() {

		if (addressPartTypeSelect == null){
			StringBuilder sb = new StringBuilder("<select name=\"type\" id=\"type\" class=\"formFieldSized\">");

			int size = addressPartTypeOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(addressPartTypeValues.get(i)).append("\")>").append(addressPartTypeOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			addressPartTypeSelect = sb.toString();
		}

		return addressPartTypeSelect;
	}

	private static String getSelect_AddressPartType(List<Object> validAddressPartTypes) {

		StringBuilder sb = new StringBuilder("<select name=\"type\" id=\"type\" class=\"formFieldSized\">");

		sb.append("<option value=\"\"></option>");

		String addressPartTypeOption = null;
		for(Object validAddressPartType : validAddressPartTypes){
			addressPartTypeOption = addressPartTypeOptionMap.get((String)validAddressPartType);
			if (addressPartTypeOption != null){
				sb.append("<option value=\"").append((String)validAddressPartType).append("\")>").append(addressPartTypeOption).append("</option>");
			}
		}

		sb.append("</select>");

		return sb.toString();
	}

	private static String getSelect_Boolean(String attrName) {

		StringBuilder sb = new StringBuilder("<select name=\"").append(attrName).append("\" id=\"").append(attrName).append("\" class=\"formFieldSized\">");
		if (booleanSelect == null){ // lazy initialize
			StringBuilder tempSb = new StringBuilder();
			int size = booleanOptions.size();
			for(int i=0;i<size; i++ ){
				tempSb.append("<option value=\"").append(booleanValues.get(i)).append("\">").append(booleanOptions.get(i)).append("</option>");
			}

			tempSb.append("</select>");

			booleanSelect = tempSb.toString();
		}
		return sb.append(booleanSelect).toString();
	}

	private static String getSelect_Boolean(String attrName, String value) {

		StringBuilder sb = new StringBuilder("<select name=\"").append(attrName).append("\" id=\"").append(attrName).append("\" class=\"formFieldSized\">");
		//if (booleanSelect == null){ // lazy initialize
			StringBuilder tempSb = new StringBuilder();
			int size = booleanOptions.size();
			for(int i=0;i<size; i++ ){
				String selected = "";
				if(value != null && value.equals(booleanValues.get(i)))
					selected = "selected=\"selected\"";
				tempSb.append("<option "+ selected + "value=\"").append(booleanValues.get(i)).append("\">").append(booleanOptions.get(i)).append("</option>");
			}

			tempSb.append("</select>");

			booleanSelect = tempSb.toString();
		//}
		return sb.append(booleanSelect).toString();
	}

	private static String getSelect_EntityNamePartType() {

		if (entityNamePartTypeSelect == null){ // lazy initialize
			StringBuilder sb = new StringBuilder("<select name=\"type\" id=\"type\" class=\"formFieldSized\">");

			int size = entityNamePartTypeOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(entityNamePartTypeValues.get(i)).append("\")>").append(entityNamePartTypeOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			entityNamePartTypeSelect = sb.toString();
		}

		return entityNamePartTypeSelect;
	}

	private static String getSelect_EntityNamePartType(List<Object> validEntityNamePartTypes) {

		StringBuilder sb = new StringBuilder("<select name=\"type\" id=\"type\" class=\"formFieldSized\">");

		sb.append("<option value=\"\"></option>");

		String validEntityNamePartTypeOption = null;
		for(Object validEntityNamePartType : validEntityNamePartTypes){
			validEntityNamePartTypeOption = entityNamePartTypeOptionMap.get((String)validEntityNamePartType);
			if (validEntityNamePartTypeOption != null){
				sb.append("<option value=\"").append((String)validEntityNamePartType).append("\")>").append(validEntityNamePartTypeOption).append("</option>");
			}
		}

		sb.append("</select>");

		return sb.toString();
	}

	private static String getSelect_EntityNamePartQualifier() {
		if (entityNamePartQualifierSelect == null){ // lazy initialize
			StringBuilder sb = new StringBuilder("<select name=\"qualifier\" id=\"qualifier\" class=\"formFieldSized\">");

			int size = entityNamePartQualifierOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(entityNamePartQualifierValues.get(i)).append("\">").append(entityNamePartQualifierOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			entityNamePartQualifierSelect = sb.toString();
		}
		return entityNamePartQualifierSelect;
	}

	private static String getSelect_Compression() {

		if (compressionSelect == null){
			StringBuilder sb = new StringBuilder("<select name=\"compression\" id=\"compression\" class=\"formFieldSized\">");

			int size = compressionOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(compressionValues.get(i)).append("\">").append(compressionOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			compressionSelect = sb.toString();
		}

		return compressionSelect;
	}

	private static String getSelect_IdentifierReliability() {

		if (identifierReliabilitySelect == null){
			StringBuilder sb = new StringBuilder("<select name=\"reliability\" id=\"reliability\" class=\"formFieldSized\">");

			int size = identifierReliabilityOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(identifierReliabilityValues.get(i)).append("\">").append(identifierReliabilityOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			identifierReliabilitySelect = sb.toString();
		}

		return identifierReliabilitySelect;
	}

	private static String getSelect_IdentifierScope() {

		if (identifierScopeSelect == null){

			StringBuilder sb = new StringBuilder("<select name=\"scope\" id=\"scope\" class=\"formFieldSized\">");

			int size = identifierScopeOptions.size();
			for(int i=0;i<size; i++ ){
				sb.append("<option value=\"").append(identifierScopeValues.get(i)).append("\">").append(identifierScopeOptions.get(i)).append("</option>");
			}

			sb.append("</select>");

			identifierScopeSelect = sb.toString();
		}

		return identifierScopeSelect;
	}

	private static String getSelect_NullFlavor() {
		if (nullFlavorSelect == null) {
		StringBuilder sb = new StringBuilder("<select name=\"nullFlavor\" id=\"nullFlavor\" class=\"formFieldSized\">");

		int size = nullFlavorOptions.size();

		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(nullFlavorValues.get(i)).append("\">").append(nullFlavorOptions.get(i)).append("</option>");
		}

		sb.append("</select>");

		nullFlavorSelect = sb.toString();
		}

		return nullFlavorSelect;
	}

	private static String getHTML_AD(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		int counter = 0;
		for(Object fieldHashMap: searchableFields){
			if (fieldHashMap instanceof java.util.HashMap){
				for (Object searchableComplexField : ((HashMap)fieldHashMap).values()){
					sb.append("<tr>");
					sb.append("  <td class=\"isoFormLabel\">Address Part (ADXP):</td>");
					sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part" + counter++, "ADXP",(List<Object>)searchableComplexField)).append("</td>");
					sb.append("</tr>");
				}
			} else {
				if (fieldHashMap != null){
					log.error("Error: expected Address Part (ADXP), found: '" + fieldHashMap.toString()+"' of type: "+fieldHashMap.getClass().getName());
				} else {
					log.error("Error: expected Address Part (ADXP), found: null");
				}
			}
		}

		return sb.toString();
	}

	private static String getHTML_ADXP(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("code")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"code\" id=\"code\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystem")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystem\" id=\"codeSystem\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		//always include Address Part Type, as it is required
		List<Object> addressPartTypeOptions = getComplexSearchFields("type", searchableFields);
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Address Part Type:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_AddressPartType(addressPartTypeOptions)).append("</td>");
		sb.append("</tr>");

		return sb.toString();
	}

	private static String getHTML_ANY(List<Object> searchableFields) {
		if (searchableFields.contains("nullFlavor")){
			StringBuilder sb = new StringBuilder();

			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Null Flavor:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_NullFlavor()).append("</td>");
			sb.append("</tr>");

			return sb.toString();
		}

		return "";
	}

	private static String getHTML_BL(List<Object> searchableFields) {
		if (searchableFields.contains("value")){
			StringBuilder sb = new StringBuilder();
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("value")).append("</td>");
			sb.append("</tr>");
			return sb.toString();
		}
		return "";
	}

	private static String getHTML_Boolean(String attrName) {

		StringBuilder sb = new StringBuilder();
		sb.append(getSelect_Boolean(attrName));

		return sb.toString();

	}

	private static String getHTML_Boolean(String attrName, String value) {
		StringBuilder sb = new StringBuilder();
		sb.append(getSelect_Boolean(attrName, value));

		return sb.toString();

	}

	private static String getHTML(String attrName, String validationClass) {
		StringBuilder sb = new StringBuilder();
		sb.append("<input type=\"text\" name=\"").append(attrName).append("\" id=\"").append(attrName).append("\" class=\"formFieldSized ").append(validationClass).append("\"/>");

		return sb.toString();
	}

	private static String getHTML(String attrName, String validationClass, String value) {
		StringBuilder sb = new StringBuilder();
		if(value == null)
			value = "";
		sb.append("<input type=\"text\" name=\"").append(attrName).append("\" value=\"").append(value).append("\" id=\"").append(attrName).append("\" class=\"formFieldSized ").append(validationClass).append("\"/>");

		return sb.toString();
	}

	private static String getHTML_CD(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> originalTextSearchFields = getComplexSearchFields("originalText", searchableFields);
		if (originalTextSearchFields != null && !originalTextSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Original Text (ED):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("originalText", "ED",originalTextSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("code") || getComplexSearchFields("code", searchableFields).contains("code")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"code\" id=\"code\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystem") || getComplexSearchFields("codeSystem", searchableFields).contains("codeSystem")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystem\" id=\"codeSystem\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystemName") || getComplexSearchFields("codeSystemName", searchableFields).contains("codeSystemName")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System Name:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemName\" id=\"codeSystemName\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystemVersion") || getComplexSearchFields("codeSystemVersion", searchableFields).contains("codeSystemVersion")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System Version:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemVersion\" id=\"codeSystemVersion\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		List<Object> displayNameSearchFields = getComplexSearchFields("displayName", searchableFields);
		if (displayNameSearchFields != null && !displayNameSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Display Name (ST):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("displayName", "ST",displayNameSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_DSET(String genericType,List<Object> searchableFields) {
		List<Object> itemSearchFields = getComplexSearchFields("item", searchableFields);
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Item ("+genericType+"):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("item",genericType,itemSearchFields)).append("</td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_ED(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("compression")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Compression:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Compression()).append("</td>");
			sb.append("</tr>");
		}

		// Even though "data" (of type byte[] / blob) is included in the metadata, exclude it as a search field
//		if (searchableFields.contains("data")){
//			sb.append("<tr>");
//			sb.append("  <td class=\"isoFormLabel\">Data:</td>");
//			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"data\" id=\"data\" class=\"formFieldSized\"/></td>");
//			sb.append("</tr>");
//		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ED_TEXT(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_EN(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> partSearchFields = getComplexSearchFields("part", searchableFields);
		if (partSearchFields != null && !partSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP",partSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ENON(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> partSearchFields = getComplexSearchFields("part", searchableFields);
		if (partSearchFields != null && !partSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP",partSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ENPN(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> partSearchFields = getComplexSearchFields("part", searchableFields);
		if (partSearchFields != null && !partSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP",partSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ENXP(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("code")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"code\" id=\"code\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystem")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystem\" id=\"codeSystem\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystemName")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System Name:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemName\" id=\"codeSystemName\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("codeSystemVersion")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code System Version:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemVersion\" id=\"codeSystemVersion\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("qualifier")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Qualifier:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_EntityNamePartQualifier()).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		//always include Entity Name Part Type, as it is required - also TODO :: client-side validation to make it required
		List<Object> entityNamePartTypeOptions = getComplexSearchFields("type", searchableFields);
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Type:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_EntityNamePartType(entityNamePartTypeOptions)).append("</td>");
		sb.append("</tr>");

		return sb.toString();
	}

	private static String getHTML_II(List<Object> searchableFields, String attrName) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("displayable") || getComplexSearchFields("displayable", searchableFields).contains("displayable")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Displayable:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("displayable")).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("extension") || getComplexSearchFields("extension", searchableFields).contains("extension")){
			String extensionClass = "";
			if ("id".equalsIgnoreCase(attrName)){
				extensionClass = "number ";
			}
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Extension:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"extension\" id=\"extension\" class=\"" + extensionClass + "formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("identifierName") || getComplexSearchFields("identifierName", searchableFields).contains("identifierName")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Identifier Name:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"identifierName\" id=\"identifierName\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("reliability") || getComplexSearchFields("reliability", searchableFields).contains("reliability")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Reliability:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierReliability()).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("root") || getComplexSearchFields("root", searchableFields).contains("root")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Root:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"root\" id=\"root\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("scope") || getComplexSearchFields("scope", searchableFields).contains("scope")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Scope:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierScope()).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_INT(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized INT_value\"/></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}


	private static String getHTML_IVL(String genericType,List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> anySearchFields = getComplexSearchFields("any", searchableFields);
		if (anySearchFields != null && !anySearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Any ("+genericType+"):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("any", genericType,anySearchFields)).append("</td>");
			sb.append("</tr>");
		}

		List<Object> lowSearchFields = getComplexSearchFields("low", searchableFields);
		if (lowSearchFields != null && !lowSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Low ("+genericType+"):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("low", genericType,lowSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("lowClosed")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Low Closed:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("lowClosed")).append("</td>");
			sb.append("</tr>");
		}

		List<Object> highSearchFields = getComplexSearchFields("high", searchableFields);
		if (highSearchFields != null && !highSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">High ("+genericType+"):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("high", genericType,highSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("highClosed")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">High Closed:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("highClosed")).append("</td>");
			sb.append("</tr>");
		}

		List<Object> widthSearchFields = getComplexSearchFields("width", searchableFields);
		if (widthSearchFields != null && !widthSearchFields.isEmpty()){
			if (genericType.equalsIgnoreCase("TS")){
				sb.append("<tr>");
				sb.append("  <td class=\"isoFormLabel\">Width (PQ):</td>");
				sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("width", "PQ",widthSearchFields)).append("</td>");
				sb.append("</tr>");
			} else {
				sb.append("<tr>");
				sb.append("  <td class=\"isoFormLabel\">Width ("+genericType+"):</td>");
				sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("width", genericType,widthSearchFields)).append("</td>");
				sb.append("</tr>");
			}

		}

		return sb.toString();
	}

	private static String getHTML_PQV(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("precision")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Precision:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"precision\" id=\"precision\" class=\"formFieldSized PQV_precision\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized number\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_PQ(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("unit")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Unit:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"unit\" id=\"unit\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_QTY(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	private static String getHTML_REAL(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized number\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_SC(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		List<Object> codeSearchFields = getComplexSearchFields("code", searchableFields);
		if (codeSearchFields != null && !codeSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Code (CD):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("code", "CD",codeSearchFields)).append("</td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ST(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_ST_NT(List<Object> searchableFields) {
		return getHTML_ST(searchableFields);
	}

	private static String getHTML_TEL(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value") || getComplexSearchFields("value", searchableFields).contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized TEL_value\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_TEL_EMAIL(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Email):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized TEL_EMAIL_value\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_TEL_PERSON(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Phone or Email):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized TEL_PERSON_value\" /></td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}

	private static String getHTML_TEL_PHONE(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Phone):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized TEL_PHONE_value\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getHTML_TEL_URL(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (URL):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized defaultInvalid url\" /></td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}

	private static String getHTML_TS(List<Object> searchableFields) {
		StringBuilder sb = new StringBuilder();

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized TS_value\" /></td>");
			sb.append("</tr>");
		}

		return sb.toString();
	}

	private static String getScreen_AD(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_AD(searchableFields);
	}

	private static String getScreen_ADXP(List<Object> searchableFields) {
		return getHTML_ADXP(searchableFields);
	}

	private static String getScreen_BL(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_BL(searchableFields);
	}

	private static String getScreen_BL_NONNULL(List<Object> searchableFields) {
		return getHTML_BL(searchableFields);
	}

	private static String getScreen_CD(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_CD(searchableFields);
	}

	private static String getScreen_DSET(String genericType,List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_DSET(genericType,searchableFields);
	}

	private static String getScreen_ED(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ED(searchableFields);
	}

	private static String getScreen_ED_TEXT(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ED_TEXT(searchableFields);
	}

	private static String getScreen_EN(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_EN(searchableFields);
	}

	private static String getScreen_ENON(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ENON(searchableFields);
	}

	private static String getScreen_ENPN(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ENPN(searchableFields);
	}

	private static String getScreen_ENXP(List<Object> searchableFields) {
		return getHTML_ENXP(searchableFields);
	}

	private static String getScreen_II(List<Object> searchableFields, String attrName) {
		return getHTML_ANY(searchableFields) + getHTML_II(searchableFields, attrName);
	}

	private static String getScreen_INT(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields) + getHTML_INT(searchableFields);
	}

	private static String getScreen_IVL(String genericType,List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields) + getHTML_IVL(genericType,searchableFields);
	}

	private static String getScreen_PQ(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields) + getHTML_PQV(searchableFields) + getHTML_PQ(searchableFields);
	}

	private static String getScreen_QTY(List<Object> searchableFields) {
		return getHTML_QTY(searchableFields);
	}

	private static String getScreen_REAL(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields) + getHTML_REAL(searchableFields);
	}

	private static String getScreen_SC(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ST(searchableFields) + getHTML_SC(searchableFields);
	}

	private static String getScreen_ST(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ST(searchableFields);
	}

	private static String getScreen_ST_NT(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_ST_NT(searchableFields);
	}

	private static String getScreen_TEL(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_TEL(searchableFields);
	}

	private static String getScreen_TEL_EMAIL(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_TEL_EMAIL(searchableFields);
	}

	private static String getScreen_TEL_PERSON(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_TEL_PERSON(searchableFields);
	}

	private static String getScreen_TEL_PHONE(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_TEL_PHONE(searchableFields);
	}

	private static String getScreen_TEL_URL(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_TEL_URL(searchableFields);
	}

	private static String getScreen_TS(List<Object> searchableFields) {
		return getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields) + getHTML_TS(searchableFields);
	}

	public static String getHtmlFor(String attrName, String attrType) {

		StringBuilder html = new StringBuilder();
		String validationClass = null;

		if ("Boolean".equalsIgnoreCase(attrType)){
			html.append(getHTML_Boolean(attrName));
		} else if ("Double".equalsIgnoreCase(attrType) || "Float".equalsIgnoreCase(attrType)){
			validationClass = "number";
			html.append(getHTML(attrName,validationClass));
		} else if ("Integer".equalsIgnoreCase(attrType) || "Long".equalsIgnoreCase(attrType)){
			validationClass = "int_long";
			html.append(getHTML(attrName, validationClass));
		} else if ("Character".equalsIgnoreCase(attrType)){
			validationClass = "char";
			html.append(getHTML(attrName, validationClass));
		} else if ("Date".equalsIgnoreCase(attrType)){
			validationClass = "date";
			html.append(getHTML(attrName, validationClass));
		} else {
			validationClass = "";
			html.append(getHTML(attrName, validationClass));
		}

		log.debug("HTML for non-ISO data type " +attrType + ": "+html);

		return html.toString();
	}

	public static String getHtmlFor(String attrName, String attrType, String value) {

		StringBuilder html = new StringBuilder();
		String validationClass = null;

		if ("Boolean".equalsIgnoreCase(attrType)){
			html.append(getHTML_Boolean(attrName, value));
		} else if ("Double".equalsIgnoreCase(attrType) || "Float".equalsIgnoreCase(attrType)){
			validationClass = "number";
			html.append(getHTML(attrName,validationClass, value));
		} else if ("Integer".equalsIgnoreCase(attrType) || "Long".equalsIgnoreCase(attrType)){
			validationClass = "int_long";
			html.append(getHTML(attrName, validationClass, value));
		} else if ("Character".equalsIgnoreCase(attrType)){
			validationClass = "char";
			html.append(getHTML(attrName, validationClass, value));
		} else if ("Date".equalsIgnoreCase(attrType)){
			validationClass = "date";
			html.append(getHTML(attrName, validationClass, value));
		} else {
			validationClass = "";
			html.append(getHTML(attrName, validationClass, value));
		}

		//System.out.println("HTML for non-ISO data type " +attrType + ": "+html);
		log.debug("HTML for non-ISO data type " +attrType + ": "+html);

		return html.toString();
	}

	public static String getAttributeValue(Element root, String attName)
	{
		if(attName == null || root == null)
			return null;
		
		Attribute attr = root.getAttribute(attName);
		if(attr != null)
			return attr.getValue();
		else
			return null;
	}

	public static String getHtmlFor(String attrName, String attrType, List<Object> searchableFields) {
		if (searchableFields == null || searchableFields.isEmpty()){
			return "No searchable fields found for attribute '"+attrName+"' of type '" + attrType +"'";
		}

		StringBuilder html = new StringBuilder(HTML_SCREEN_BEGIN);

		if ("AD".equalsIgnoreCase(attrType)){
			html.append(getScreen_AD(searchableFields));
		} else if ("ADXP".equalsIgnoreCase(attrType)){
			html.append(getScreen_ADXP(searchableFields));
		} else if ("BL".equalsIgnoreCase(attrType)){
			html.append(getScreen_BL(searchableFields));
		} else if ("BLNONNULL".equalsIgnoreCase(attrType)){
			html.append(getScreen_BL_NONNULL(searchableFields));
		} else if ("CD".equalsIgnoreCase(attrType)){
			html.append(getScreen_CD(searchableFields));
		} else if ("DSET&lt;AD&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_DSET("AD",searchableFields));
		} else if ("DSET&lt;CD&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_DSET("CD",searchableFields));
		} else if ("DSET&lt;II&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_DSET("II",searchableFields));
		} else if ("DSET&lt;TEL&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_DSET("TEL",searchableFields));
		} else if ("ED".equalsIgnoreCase(attrType)){
			html.append(getScreen_ED(searchableFields));
		} else if ("EDTEXT".equalsIgnoreCase(attrType)){
			html.append(getScreen_ED_TEXT(searchableFields));
		} else if ("EN".equalsIgnoreCase(attrType)){
			html.append(getScreen_EN(searchableFields));
		} else if ("ENON".equalsIgnoreCase(attrType)){
			html.append(getScreen_ENON(searchableFields));
		} else if ("ENPN".equalsIgnoreCase(attrType)){
			html.append(getScreen_ENPN(searchableFields));
		} else if ("ENXP".equalsIgnoreCase(attrType)){
			html.append(getScreen_ENXP(searchableFields));
		} else if ("II".equalsIgnoreCase(attrType)){
			html.append(getScreen_II(searchableFields, attrName));
		} else if ("INT".equalsIgnoreCase(attrType)){
			html.append(getScreen_INT(searchableFields));
		} else if ("IVL&lt;INT&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_IVL("INT",searchableFields));
		} else if ("IVL&lt;PQ&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_IVL("PQ",searchableFields));
		} else if ("IVL&lt;REAL&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_IVL("REAL",searchableFields));
		} else if ("IVL&lt;TS&gt;".equalsIgnoreCase(attrType)){
			html.append(getScreen_IVL("TS",searchableFields));
		} else if ("PQ".equalsIgnoreCase(attrType)){
			html.append(getScreen_PQ(searchableFields));
		} else if ("QTY".equalsIgnoreCase(attrType)){
			html.append(getScreen_QTY(searchableFields));
		} else if ("REAL".equalsIgnoreCase(attrType)){
			html.append(getScreen_REAL(searchableFields));
		} else if ("SC".equalsIgnoreCase(attrType)){
			html.append(getScreen_SC(searchableFields));
		} else if ("ST".equalsIgnoreCase(attrType)){
			html.append(getScreen_ST(searchableFields));
		} else if ("STNT".equalsIgnoreCase(attrType)){
			html.append(getScreen_ST_NT(searchableFields));
		} else if ("TEL".equalsIgnoreCase(attrType)){
			html.append(getScreen_TEL(searchableFields));
		} else if ("TELEMAIL".equalsIgnoreCase(attrType)){
			html.append(getScreen_TEL_EMAIL(searchableFields));
		} else if ("TELPERSON".equalsIgnoreCase(attrType)){
			html.append(getScreen_TEL_PERSON(searchableFields));
		} else if ("TELPHONE".equalsIgnoreCase(attrType)){
			html.append(getScreen_TEL_PHONE(searchableFields));
		} else if ("TELURL".equalsIgnoreCase(attrType)){
			html.append(getScreen_TEL_URL(searchableFields));
		} else if ("TS".equalsIgnoreCase(attrType)){
			html.append(getScreen_TS(searchableFields));
		} else {
			html.append("<p>Unknown ISO Data Type: " + attrType + "</p>");
		}

		html.append(HTML_SCREEN_END);

		String nameAttribute = "name=\"";
		String idAttribute = "id=\"";

		String finalHtml = html.toString().replaceAll(nameAttribute, nameAttribute + attrName + ".");
		finalHtml = finalHtml.replaceAll(idAttribute, idAttribute + attrName + ".");

		log.debug("HTML for ISO data type " +attrType + ": "+finalHtml);

		return finalHtml;
	}

	private static List<Object> getComplexSearchFields(String complexType, List<Object> searchableFields){
		for(Object attribute: searchableFields){
			if (attribute instanceof java.util.HashMap){
				for (Object searchableComplexAttributeKey : ((HashMap)attribute).keySet()){
					if ( ((String)searchableComplexAttributeKey).startsWith(complexType)){
						return (List<Object>)(((HashMap)attribute).get((String)searchableComplexAttributeKey));
					}
				}
			}
		}

		return new ArrayList<Object>();
	}

	static {
		int size = addressPartTypeValues.size();
		for(int i=0;i<size; i++ ){
			addressPartTypeOptionMap.put(addressPartTypeValues.get(i), addressPartTypeOptions.get(i));
		}

		size = entityNamePartTypeValues.size();
		for(int i=0;i<size; i++ ){
			entityNamePartTypeOptionMap.put(entityNamePartTypeValues.get(i), entityNamePartTypeOptions.get(i));
		}

	}
}
