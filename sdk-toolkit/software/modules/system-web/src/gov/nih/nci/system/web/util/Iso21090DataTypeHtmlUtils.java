package gov.nih.nci.system.web.util;

import gov.nih.nci.system.util.ClassCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class Iso21090DataTypeHtmlUtils {
	
	private static Logger log = Logger.getLogger(Iso21090DataTypeHtmlUtils.class.getName());

	private static String HTML_SCREEN_BEGIN = "<table cellspacing=\"0\" cellpadding=\"3\" border=\"1\" width=\"100%\" >";
	private static String HTML_SCREEN_END = "</table>";
	
	// todo :: Cache Select Options; standardize naming convention

	private static List<String> booleanOptions = new ArrayList<String>(Arrays.asList("","True (Yes)","False (No)"));
	private static List<String> booleanValues = new ArrayList<String>(Arrays.asList("","true","false"));
	
	private static List<String> compressionOptions = new ArrayList<String>(Arrays.asList("","DF (deflate)","GZ (gzip)","ZL (zlib)","Z (compress)","BZ (bzip)","Z7 (z7)"));
	private static List<String> compressionValues = new ArrayList<String>(Arrays.asList("","DF","GZ","ZL","Z","BZ","Z7"));
	
	private static List<String> identifierReliabilityOptions = new ArrayList<String>(Arrays.asList("","ISS (issued by system)","VRF (verified by system)","UNV (unverified by system)"));
	private static List<String> identifierReliabilityValues = new ArrayList<String>(Arrays.asList("","ISS","VRF","UNV"));
	
	private static List<String> identifierScopeOptions = new ArrayList<String>(Arrays.asList("","BUSN (business identifier)","OBJ (object identifier)","VER (version identifier)","VW (view specific identifier)"));
	private static List<String> identifierScopeValues = new ArrayList<String>(Arrays.asList("","BUSN","OBJ","VER","VW"));
	
	private static List<String> nullFlavorOptions = new ArrayList<String>(Arrays.asList("","NI (No Information)","INV (Invalid)","OTH (Other)","NINF (Negative Infinity)","PINF (Positive Infinity)","UNC (Unencoded)","DER (Derived)","UNK (Unknown)","ASKU (Asked but Unknown)","NAV (Temporarily Unavailable)","QS (Sufficient Quantity)","NASK (Not Asked)","TRC (Trace)","MSK (Masked)","NA (Not Applicable)"));
	private static List<String> nullFlavorValues = new ArrayList<String>(Arrays.asList("","NI","INV","OTH","NINF","PINF","UNC","DER","UNK","ASKU","NAV","QS","NASK","TRC","MSK","NA"));

	private static List<String> addressPartTypeOptions = new ArrayList<String>(Arrays.asList("","AL (address line)","ADL (additional locator)","UNID (unit identifier)","UNIT (unit designator)","DAL (delivery address line)","DINST (delivery installation type)","DINSTA (delivery installation area)","DINSTQ (delivery installation qualifier)","DMOD (delivery mode)","DMODID (delivery mode identifier)","SAL (street address line)","BNR (building number)","BNN (building number numeric)","BNS (building number suffix)","STR (street name)","STB (street name base)","STTYP (street type)","DIR (direction)","CAR (care of)","CEN (census tract)","CNT (country)","CPA (county or parish)","CTY (municipality)","DEL (delimiter)","POB (post box)","PRE (precinct)","STA (state or province)","ZIP (postal code)"));
	private static List<String> addressPartTypeValues = new ArrayList<String>(Arrays.asList("","AL","ADL","UNID","UNIT","DAL","DINST)","DINSTA","DINSTQ","DMOD","DMODID","SAL","BNR","BNN","BNS","STR","STB","STTYP","DIR","CAR","CEN","CNT","CPA","CTY","DEL","POB","PRE","STA","ZIP"));

	private static List<String> entityNamePartTypeOptions = new ArrayList<String>(Arrays.asList("","FAM (family)","GIV (given)","PFX (prefix)","SFX (suffix)","DEL (delimiter)"));
	private static List<String> entityNamePartTypeValues = new ArrayList<String>(Arrays.asList("","FAM","GIV","PFX","SFX","DEL"));
	
	private static List<String> entityNamePartQualifierOptions = new ArrayList<String>(Arrays.asList("","LS (legal status)","AC (academic)","NB (nobility)","PR (professional)","VV (voorvoegsel)","AD (adopted)","BR (birth)","SP (spouse)","CL (callme)","IN (initial)","TITLE (title)"));
	private static List<String> entityNamePartQualifierValues = new ArrayList<String>(Arrays.asList("","LS","AC","NB","PR","VV","AD","BR","SP","CL","IN","TITLE"));

	private static String getSelect_AddressPartType() {
		StringBuffer sb = new StringBuffer("<select name=\"type\" class=\"formFieldSized\">");
		
		int size = addressPartTypeOptions.size();
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(addressPartTypeValues.get(i)).append("\")>").append(addressPartTypeOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}
	
	private static String getSelect_EntityNamePartType() {
		StringBuffer sb = new StringBuffer("<select name=\"type\" class=\"formFieldSized\">");
		
		int size = entityNamePartTypeOptions.size();
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(entityNamePartTypeValues.get(i)).append("\")>").append(entityNamePartTypeOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}
	
	private static String getSelect_EntityNamePartQualifier() {
		StringBuffer sb = new StringBuffer("<select name=\"qualifier\" class=\"formFieldSized\">");
		
		int size = entityNamePartQualifierOptions.size();
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(entityNamePartQualifierValues.get(i)).append("\">").append(entityNamePartQualifierOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}
	
	private static String getSelect_Boolean(String name) {
		StringBuffer sb = new StringBuffer("<select name=\"").append(name).append("\" class=\"formFieldSized\">");
		
		int size = booleanOptions.size();
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(booleanValues.get(i)).append("\">").append(booleanOptions.get(i)).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}
	
	private static String getSelect_Compression() {
		StringBuffer sb = new StringBuffer("<select name=\"compression\"  class=\"formFieldSized\">");
		
		int size = compressionOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(compressionValues.get(i)).append("\">").append(compressionOptions.get(i)).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

	private static String getSelect_IdentifierReliability() {
		StringBuffer sb = new StringBuffer("<select name=\"reliability\" class=\"formFieldSized\">");
		
		int size = identifierReliabilityOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(identifierReliabilityValues.get(i)).append("\">").append(identifierReliabilityOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}

	private static String getSelect_IdentifierScope() {
		StringBuffer sb = new StringBuffer("<select name=\"scope\" class=\"formFieldSized\">");
		
		
		int size = identifierScopeOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(identifierScopeValues.get(i)).append("\">").append(identifierScopeOptions.get(i)).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
	}
	
	private static String getSelect_NullFlavor() {
		StringBuffer sb = new StringBuffer("<select name=\"nullFlavor\" class=\"formFieldSized\">");

		int size = nullFlavorOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(nullFlavorValues.get(i)).append("\">").append(nullFlavorOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		
		return sb.toString();
	}

	private static String getHTML_ANY(List<Object> searchableFields) {
		if (searchableFields.contains("nullFlavor")){
			StringBuffer sb = new StringBuffer();
			
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
			StringBuffer sb = new StringBuffer();
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("value")).append("</td>");
			sb.append("</tr>");
			return sb.toString();
		}
		return "";		
	}
	
	private static String getHTML_AD(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
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
		StringBuffer sb = new StringBuffer();
		
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
		
		//always include Address Part Type, as it is required - also TODO :: client-side validation to make it required
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Address Part Type:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_AddressPartType()).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}	

	private static String getHTML_CD(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();

		List<Object> originalTextSearchFields = getComplexSearchFields("originalText", searchableFields);
		if (originalTextSearchFields != null && !originalTextSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Original Text (ED):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("originalText", "ED",originalTextSearchFields)).append("</td>");
			sb.append("</tr>");
		}
		
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
		
		List<Object> displayNameSearchFields = getComplexSearchFields("displayName", searchableFields);
		if (displayNameSearchFields != null && !displayNameSearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Display Name (ST):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("displayName", "ST",displayNameSearchFields)).append("</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	// todo :: complete
	private static String getHTML_DSET(String genericType,List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Item ("+genericType+"):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("item", genericType,searchableFields)).append("</td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_ED(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("compression")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Compression:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Compression()).append("</td>");
			sb.append("</tr>");
		}
		
		if (searchableFields.contains("data")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Data:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"data\" id=\"data\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	private static String getHTML_ED_TEXT(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_EN(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		//todo :: complete for all parts
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
		StringBuffer sb = new StringBuffer();
		
		//todo :: complete for all parts
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
		StringBuffer sb = new StringBuffer();
		
		//todo :: complete for all parts
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
		StringBuffer sb = new StringBuffer();
		
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
		
		if (searchableFields.contains("type")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Type:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_EntityNamePartType()).append("</td>");
			sb.append("</tr>");
		}
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}	

	private static String getHTML_II(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("displayable")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Displayable:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("displayable")).append("</td>");
			sb.append("</tr>");
		}
		
		if (searchableFields.contains("extension")){		
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Extension:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"extension\" id=\"extension\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		if (searchableFields.contains("identifierName")){			
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Identifier Name:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"identifierName\" id=\"identifierName\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		if (searchableFields.contains("reliability")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Reliability:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierReliability()).append("</td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("root")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Root:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"root\" id=\"root\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("scope")){	
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Scope:</td>");
			sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierScope()).append("</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_INT(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	

	private static String getHTML_IVL(String genericType,List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		List<Object> anySearchFields = getComplexSearchFields("any", searchableFields);
		if (anySearchFields != null && !anySearchFields.isEmpty()){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Any ("+genericType+"):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("any", genericType,anySearchFields)).append("</td>");
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
		
		List<Object> widthSearchFields = getComplexSearchFields("width", searchableFields);
		if (widthSearchFields != null && !widthSearchFields.isEmpty()){	
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Width ("+genericType+"):</td>");
			sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("width", genericType,widthSearchFields)).append("</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_PQV(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("precision")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Precision:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"precision\" id=\"precision\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}

		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_PQ(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("unit")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Unit:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"unit\" id=\"unit\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_QTY(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");		
		}
		
		return sb.toString();
	}

	private static String getHTML_REAL(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	private static String getHTML_SC(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
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
		StringBuffer sb = new StringBuffer();
		
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
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_TEL_EMAIL(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Email):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_TEL_PERSON(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Phone or Email):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}

	private static String getHTML_TEL_PHONE(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (Phone):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}

	private static String getHTML_TEL_URL(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value (URL):</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}

	private static String getHTML_TS(List<Object> searchableFields) {
		StringBuffer sb = new StringBuffer();
		
		if (searchableFields.contains("value")){
			sb.append("<tr>");
			sb.append("  <td class=\"isoFormLabel\">Value:</td>");
			sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	private static String getScreen_AD(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_AD(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_ADXP(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ADXP(searchableFields) + HTML_SCREEN_END;
	}	

	private static String getScreen_BL(List<Object> searchableFields) {
		
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_BL(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_BL_NONNULL(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_BL(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_CD(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_CD(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_DSET(String genericType,List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_DSET(genericType,searchableFields) + HTML_SCREEN_END;
	}	

	private static String getScreen_ED(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ED(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_ED_TEXT(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ED_TEXT(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_EN(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_EN(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENON(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ENON(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENPN(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ENPN(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENXP(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ENXP(searchableFields) + HTML_SCREEN_END;
	}	

	private static String getScreen_II(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_II(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_INT(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_INT(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_IVL(String genericType,List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_IVL(genericType,searchableFields) + HTML_SCREEN_END;
	}	

	private static String getScreen_PQ(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_PQV(searchableFields) + getHTML_PQ(searchableFields) + HTML_SCREEN_END;
	}
	
	private static String getScreen_QTY(List<Object> searchableFields) {	
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_QTY(searchableFields)+ HTML_SCREEN_END;
	}	

	private static String getScreen_REAL(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_REAL(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_SC(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ST(searchableFields) + getHTML_SC(searchableFields) + HTML_SCREEN_END;
	}	

	private static String getScreen_ST(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ST(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_ST_NT(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_ST_NT(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TEL(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TEL(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_EMAIL(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TEL_EMAIL(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_PERSON(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TEL_PERSON(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_PHONE(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TEL_PHONE(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_URL(List<Object> searchableFields) {
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TEL_URL(searchableFields) + HTML_SCREEN_END;
	}

	private static String getScreen_TS(List<Object> searchableFields) {	
		return HTML_SCREEN_BEGIN + getHTML_ANY(searchableFields) + getHTML_TS(searchableFields) + HTML_SCREEN_END;
	}

	public static String getHtmlFor(String attrName, String isoType, List<Object> searchableFields) {
		if (searchableFields == null || searchableFields.isEmpty()){
			return "No searchable fields found for attribute "+attrName+" of type " + isoType;
		}
		
		String html = null;

		if ("AD".equalsIgnoreCase(isoType)){
			html = getScreen_AD(searchableFields);
		} else if ("ADXP".equalsIgnoreCase(isoType)){
			html = getScreen_ADXP(searchableFields);
		} else if ("BL".equalsIgnoreCase(isoType)){
			html = getScreen_BL(searchableFields);
		} else if ("BLNONNULL".equalsIgnoreCase(isoType)){
			html = getScreen_BL_NONNULL(searchableFields);
		} else if ("CD".equalsIgnoreCase(isoType)){
			html = getScreen_CD(searchableFields);	
		} else if ("DSET&lt;AD&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_DSET("AD",searchableFields);
		} else if ("DSET&lt;CD&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_DSET("CD",searchableFields);	
		} else if ("DSET&lt;II&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_DSET("II",searchableFields);	
		} else if ("DSET&lt;TEL&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_DSET("TEL",searchableFields);				
		} else if ("ED".equalsIgnoreCase(isoType)){
			html = getScreen_ED(searchableFields);
		} else if ("EDTEXT".equalsIgnoreCase(isoType)){
			html = getScreen_ED_TEXT(searchableFields);
		} else if ("EN".equalsIgnoreCase(isoType)){
			html = getScreen_EN(searchableFields);
		} else if ("ENON".equalsIgnoreCase(isoType)){
			html = getScreen_ENON(searchableFields);
		} else if ("ENPN".equalsIgnoreCase(isoType)){
			html = getScreen_ENPN(searchableFields);			
		} else if ("ENXP".equalsIgnoreCase(isoType)){
			html = getScreen_ENXP(searchableFields);			
		} else if ("II".equalsIgnoreCase(isoType)){
			html = getScreen_II(searchableFields);
		} else if ("INT".equalsIgnoreCase(isoType)){
			html = getScreen_INT(searchableFields);
		} else if ("IVL&lt;INT&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_IVL("INT",searchableFields);	
		} else if ("IVL&lt;PQ&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_IVL("PQ",searchableFields);	
		} else if ("IVL&lt;REAL&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_IVL("REAL",searchableFields);	
		} else if ("IVL&lt;TS&gt;".equalsIgnoreCase(isoType)){
			html = getScreen_IVL("TS",searchableFields);				
		} else if ("PQ".equalsIgnoreCase(isoType)){
			html = getScreen_PQ(searchableFields);
		} else if ("QTY".equalsIgnoreCase(isoType)){
			html = getScreen_QTY(searchableFields);	
		} else if ("REAL".equalsIgnoreCase(isoType)){
			html = getScreen_REAL(searchableFields);	
		} else if ("SC".equalsIgnoreCase(isoType)){
			html = getScreen_SC(searchableFields);			
		} else if ("ST".equalsIgnoreCase(isoType)){
			html = getScreen_ST(searchableFields);
		} else if ("STNT".equalsIgnoreCase(isoType)){
			html = getScreen_ST_NT(searchableFields);	
		} else if ("TEL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL(searchableFields);	
		} else if ("TELEMAIL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_EMAIL(searchableFields);	
		} else if ("TELPERSON".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_PERSON(searchableFields);	
		} else if ("TELPHONE".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_PHONE(searchableFields);	
		} else if ("TELURL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_URL(searchableFields);	
		} else if ("TS".equalsIgnoreCase(isoType)){
			html = getScreen_TS(searchableFields);			
		} else {
			html = "<p>Unknown ISO Data Type: " + isoType + "</p>";
		}
		
		String nameAttribute = "name=\"";
		String idAttribute = "id=\"";

		html = html.replaceAll(nameAttribute, nameAttribute + attrName + ".");
		html = html.replaceAll(idAttribute, idAttribute + attrName + ".");
		
		System.out.println("HTML for ISO data type " +isoType + ": "+html);
		log.debug("HTML for ISO data type " +isoType + ": "+html);
		
		return html;
	}
	
	private static String getInputTextRowHtml(String label, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">").append(label).append(":</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"").append(name).append("\" id=\"").append(name).append("\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
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
}
