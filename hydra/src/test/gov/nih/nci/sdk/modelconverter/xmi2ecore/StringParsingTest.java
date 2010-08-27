package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import org.apache.commons.lang.StringUtils;

public class StringParsingTest {
	
	public void test_stringParsing1() {
		String line = "		<thecustomprofile:prop.per.primary.key base_Attribute=\"EAID_F0C81987_C4A1_4e4f_B05C_C5EFCA810E53\" prop.per.primary.key=\"0\"/>";
		System.out.println(XMI2EcoreModelConverter.getValueForToken(line, "base_Attribute"));
		System.out.println(XMI2EcoreModelConverter.getValueForToken(line, "prop.val.required"));
		
		init(line);
	}
	
	public void test_stringParsing2() {
		String line = "		<thecustomprofile:Anonymous base_Attribute=\"EAID_96ED44B6_C066_491c_B7DA_8D1A34E23C46\"/>";		
		init(line);
	}
	
	private void init(String line) {
		String name = null;
		if (line == null) return;
		try {
			//parse the line
			line = line.trim();
			name = line.substring("<thecustomprofile:".length(), line.indexOf(' '));
			name = StringUtils.remove(name, '"');
			System.out.println("name: " + name);
			
			int valueStartIndex = line.indexOf(name + '=');
			if (valueStartIndex != -1) {
				String value = line.substring(line.lastIndexOf('=') + 2, line.lastIndexOf('"'));
				System.out.println("value: " + value);
				
				String ln = line.substring(line.indexOf("base_") + 5, valueStartIndex -1);
				ln = StringUtils.remove(ln, '"');
				String baseLevel = ln.substring(0, ln.indexOf('='));
				System.out.println("baseLevel: " + baseLevel);
				
				String baseId = ln.substring(ln.indexOf('=') + 1);
				System.out.println("baseId: " + baseId);
			}
		}
		catch (Exception ex) {
			throw new IllegalArgumentException("Failed to parse line \"" + line + "\".");
		}
	}
	
	public void test_getvalueForToken() {
		String line = "<packagedElement xmi:type=\"uml:Class\" xmi:id=\"EAID_15E7F775_6CC4_47bd_A818_5FDFB8740D95\" name=\"Patient\" visibility=\"public\">";
		System.out.println("visibility: " + XMI2EcoreModelConverter.getValueForToken(line, "visibility"));
		System.out.println("xmi:type: " + XMI2EcoreModelConverter.getValueForToken(line, "xmi:type"));
		System.out.println("xmi:id: " + XMI2EcoreModelConverter.getValueForToken(line, "xmi:id"));
		System.out.println("name: " + XMI2EcoreModelConverter.getValueForToken(line, "name"));
	}
	
	public static void main(String[] args) {
		StringParsingTest test = new StringParsingTest();
		test.test_stringParsing1();
		//test.test_stringParsing2();
		//test.test_getvalueForToken();
	}
}
