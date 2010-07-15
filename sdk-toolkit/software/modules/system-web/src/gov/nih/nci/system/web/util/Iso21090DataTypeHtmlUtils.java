package gov.nih.nci.system.web.util;

import java.util.ArrayList;
import java.util.Arrays;
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

	private static List<String> ARRAY_AddressPartType = new ArrayList<String>(Arrays.asList("","AL (address line)","ADL (additional locator)","UNID (unit identifier)","UNIT (unit designator)","DAL (delivery address line)","DINST (delivery installation type)","DINSTA (delivery installation area)","DINSTQ (delivery installation qualifier)","DMOD (delivery mode)","DMODID (delivery mode identifier)","SAL (street address line)","BNR (building number)","BNN (building number numeric)","BNS (building number suffix)","STR (street name)","STB (street name base)","STTYP (street type)","DIR (direction)","CAR (care of)","CEN (census tract)","CNT (country)","CPA (county or parish)","CTY (municipality)","DEL (delimiter)","POB (post box)","PRE (precinct)","STA (state or province)","ZIP (postal code)"));

	private static List<String> entityNamePartTypeOptions = new ArrayList<String>(Arrays.asList("","FAM (family)","GIV (given)","PFX (prefix)","SFX (suffix)","DEL (delimiter)"));
	private static List<String> entityNamePartTypeValues = new ArrayList<String>(Arrays.asList("","FAM","GIV","PFX","SFX","DEL"));
	
	private static List<String> entityNamePartQualifierOptions = new ArrayList<String>(Arrays.asList("","LS (legal status)","AC (academic)","NB (nobility)","PR (professional)","VV (voorvoegsel)","AD (adopted)","BR (birth)","SP (spouse)","CL (callme)","IN (initial)","TITLE (title)"));
	private static List<String> entityNamePartQualifierValues = new ArrayList<String>(Arrays.asList("","LS","AC","NB","PR","VV","AD","BR","SP","CL","IN","TITLE"));

	private static String getSelect_AddressPartType() {
		StringBuffer sb = new StringBuffer("<select name=\"type\" class=\"formFieldSized\">");

		for (String addressPartType : ARRAY_AddressPartType) {
			sb.append("<option>").append(addressPartType).append("</option>");
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
			sb.append("<option value=\"").append(entityNamePartQualifierValues.get(i)).append("\")>").append(entityNamePartQualifierOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}
	
	
	private static String getSelect_Boolean(String name) {
		StringBuffer sb = new StringBuffer("<select name=\"").append(name).append("\"  class=\"formFieldSized\">");
		
		int size = booleanOptions.size();
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(booleanValues.get(i)).append("\")>").append(booleanOptions.get(i)).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}
	
	private static String getSelect_Compression() {
		StringBuffer sb = new StringBuffer("<select name=\"compression\"  class=\"formFieldSized\">");
		
		int size = compressionOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(compressionValues.get(i)).append("\")>").append(compressionOptions.get(i)).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}

	private static String getSelect_IdentifierReliability() {
		StringBuffer sb = new StringBuffer("<select name=\"reliability\" class=\"formFieldSized\">");
		
		int size = identifierReliabilityOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(identifierReliabilityValues.get(i)).append("\")>").append(identifierReliabilityOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		return sb.toString();
	}

	private static String getSelect_IdentifierScope() {
		StringBuffer sb = new StringBuffer("<select name=\"scope\" class=\"formFieldSized\">");
		
		
		int size = identifierScopeOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(identifierScopeValues.get(i)).append("\")>").append(identifierScopeOptions.get(i)).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
	}
	
	private static String getSelect_NullFlavor() {
		StringBuffer sb = new StringBuffer("<select name=\"nullFlavor\" class=\"formFieldSized\">");

		int size = nullFlavorOptions.size();
		
		for(int i=0;i<size; i++ ){
			sb.append("<option value=\"").append(nullFlavorValues.get(i)).append("\")>").append(nullFlavorOptions.get(i)).append("</option>");
		}

		sb.append("</select>");
		
		return sb.toString();
	}

	private static String getHTML_ANY() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Null Flavor:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_NullFlavor()).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}

	private static String getHTML_BL() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("value")).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}
	

	private static String getHTML_CD() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Original Text (ED):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("originalText", "ED")).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"code\" id=\"code\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystem\" id=\"codeSystem\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System Name:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemName\" id=\"codeSystemName\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");	
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System Version:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemVersion\" id=\"codeSystemVersion\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Display Name (ST):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("displayName", "ST")).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}

	private static String getHTML_ED() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Compression:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_Compression()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Data:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"data\" id=\"data\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		
		return sb.toString();
	}

	private static String getHTML_EN() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP")).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}
	
	private static String getHTML_ENON() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP")).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}
	
	private static String getHTML_ENPN() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Part (ENXP):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("part", "ENXP")).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}	
	
	private static String getHTML_ENXP() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"code\" id=\"code\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystem\" id=\"codeSystem\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System Name:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemName\" id=\"codeSystemName\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");	
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code System Version:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"codeSystemVersion\" id=\"codeSystemVersion\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Qualifier:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_EntityNamePartQualifier()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Type:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_EntityNamePartType()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		
		return sb.toString();
	}	

	private static String getHTML_ED_TEXT() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_II() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Displayable:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_Boolean("displayable")).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Extension:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"extension\" id=\"extension\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Identifier Name:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"identifierName\" id=\"identifierName\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Reliability:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierReliability()).append("</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Root:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"root\" id=\"root\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Scope:</td>");
		sb.append("  <td class=\"isoFormField\">").append(getSelect_IdentifierScope()).append("</td>");
		sb.append("</tr>");
		
		return sb.toString();
	}

	private static String getHTML_INT() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_PQV() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Precision:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"precision\" id=\"precision\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_PQ() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Unit:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"unit\" id=\"unit\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_QTY() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}

	private static String getHTML_REAL() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}
	
	private static String getHTML_SC() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Code (CD):</td>");
		sb.append("  <td class=\"isoFormField\">").append(getHtmlFor("code", "CD")).append("</td>");		
		sb.append("</tr>");
		
		return sb.toString();
	}	

	private static String getHTML_ST() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\"/></td>");
		sb.append("</tr>");
		
		return sb.toString();
	}

	private static String getHTML_ST_NT() {
		return getHTML_ST();
	}
	
	// todo :: Need to resolve class="*" issue

	private static String getHTML_TEL() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_TEL_EMAIL() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value (Email):</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_TEL_PERSON() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value (Phone or Email):</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_TEL_PHONE() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value (Phone):</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_TEL_URL() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value (URL):</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"url\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getHTML_TS() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">Value:</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"value\" id=\"value\" class=\"date\" class=\"formField\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}

	private static String getScreen_BL() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_BL() + HTML_SCREEN_END;
	}

	private static String getScreen_BL_NONNULL() {
		return HTML_SCREEN_BEGIN + getHTML_BL() + HTML_SCREEN_END;
	}
	

	private static String getScreen_CD() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_CD() + HTML_SCREEN_END;
	}

	private static String getScreen_ED() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ED() + HTML_SCREEN_END;
	}

	private static String getScreen_ED_TEXT() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ED_TEXT() + HTML_SCREEN_END;
	}
	
	private static String getScreen_EN() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_EN() + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENON() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ENON() + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENPN() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ENPN() + HTML_SCREEN_END;
	}
	
	private static String getScreen_ENXP() {
		return HTML_SCREEN_BEGIN + getHTML_ENXP() + HTML_SCREEN_END;
	}	

	private static String getScreen_II() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_II() + HTML_SCREEN_END;
	}

	private static String getScreen_INT() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_INT() + HTML_SCREEN_END;
	}

	private static String getScreen_PQ() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_QTY() + getHTML_PQV() + getHTML_PQ() + HTML_SCREEN_END;
	}

	private static String getScreen_REAL() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_QTY() + getHTML_REAL() + HTML_SCREEN_END;
	}
	

	private static String getScreen_SC() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ST() + getHTML_SC() + HTML_SCREEN_END;
	}	

	private static String getScreen_ST() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ST() + HTML_SCREEN_END;
	}

	private static String getScreen_ST_NT() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_ST_NT() + HTML_SCREEN_END;
	}

	private static String getScreen_TEL() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_TEL() + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_EMAIL() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_TEL_EMAIL() + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_PERSON() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_TEL_PERSON() + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_PHONE() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_TEL_PHONE() + HTML_SCREEN_END;
	}

	private static String getScreen_TEL_URL() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_TEL_URL() + HTML_SCREEN_END;
	}

	private static String getScreen_TS() {
		return HTML_SCREEN_BEGIN + getHTML_ANY() + getHTML_QTY() + getHTML_TS() + HTML_SCREEN_END;
	}

	public static String getHtmlFor(String attrName, String isoType) {
		String html = null;

		if ("BL".equalsIgnoreCase(isoType)){
			html = getScreen_BL();
		} else if ("BLNONNULL".equalsIgnoreCase(isoType)){
			html = getScreen_BL_NONNULL();
		} else if ("CD".equalsIgnoreCase(isoType)){
			html = getScreen_CD();			
		} else if ("ED".equalsIgnoreCase(isoType)){
			html = getScreen_ED();
		} else if ("EDTEXT".equalsIgnoreCase(isoType)){
			html = getScreen_ED_TEXT();
		} else if ("EN".equalsIgnoreCase(isoType)){
			html = getScreen_EN();
		} else if ("ENON".equalsIgnoreCase(isoType)){
			html = getScreen_ENON();
		} else if ("ENPN".equalsIgnoreCase(isoType)){
			html = getScreen_ENPN();			
		} else if ("ENXP".equalsIgnoreCase(isoType)){
			html = getScreen_ENXP();			
		} else if ("II".equalsIgnoreCase(isoType)){
			html = getScreen_II();
		} else if ("INT".equalsIgnoreCase(isoType)){
			html = getScreen_INT();
		} else if ("PQ".equalsIgnoreCase(isoType)){
			html = getScreen_PQ();
		} else if ("REAL".equalsIgnoreCase(isoType)){
			html = getScreen_REAL();	
		} else if ("SC".equalsIgnoreCase(isoType)){
			html = getScreen_SC();			
		} else if ("ST".equalsIgnoreCase(isoType)){
			html = getScreen_ST();
		} else if ("STNT".equalsIgnoreCase(isoType)){
			html = getScreen_ST_NT();	
		} else if ("TEL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL();	
		} else if ("TELEMAIL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_EMAIL();	
		} else if ("TELPERSON".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_PERSON();	
		} else if ("TELPHONE".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_PHONE();	
		} else if ("TELURL".equalsIgnoreCase(isoType)){
			html = getScreen_TEL_URL();	
		} else if ("TS".equalsIgnoreCase(isoType)){
			html = getScreen_TS();			
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
	
	private String getInputTextRowHtml(String label, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("  <td class=\"isoFormLabel\">").append(label).append(":</td>");
		sb.append("  <td class=\"isoFormField\"><input type=\"text\" name=\"").append(name).append("\" id=\"").append(name).append("\" class=\"formFieldSized\" /></td>");
		sb.append("</tr>");
		return sb.toString();
	}
}
