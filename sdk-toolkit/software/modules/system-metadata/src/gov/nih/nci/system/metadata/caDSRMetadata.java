package gov.nih.nci.system.metadata;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class caDSRMetadata implements Metadata {

	public static final String CONTEXT_NAME = "caDSR";
	private Map<String, MetadataElement> attributeMap = new HashMap<String, MetadataElement>();
	
	public caDSRMetadata() {
	}

	public String getMetadataContextName() {
		return CONTEXT_NAME;
	}

	public void addMetadataElement(String className, String attributeName, MetadataElement element)
	{
		String keyName = className+":"+attributeName;
		attributeMap.put(keyName, element);
	}
	
	public MetadataElement getMetadata(String className, String attributeName) {
		return attributeMap.get(className+":"+attributeName);
	}

}
