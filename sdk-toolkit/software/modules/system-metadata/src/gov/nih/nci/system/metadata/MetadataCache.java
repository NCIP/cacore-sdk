package gov.nih.nci.system.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public final class MetadataCache {
	public final String CADSR_METADATA_FILENAME = "caDSRMetadata.xml";
	private final static MetadataCache cache = new MetadataCache();
	private Map<String, Metadata> metadata = new HashMap<String, Metadata>();
	private Map<String, List> classMap = new HashMap<String, List>();
	private Map<String, List> attributeMap = new HashMap<String, List>();
	

	public static MetadataCache getInstance() {
		return cache;
	}

	private MetadataCache() {
		loadcaDSRMetadata(CADSR_METADATA_FILENAME);
	}

	private Map loadcaDSRMetadata(String fileName) {
		List<String> classList = new ArrayList<String>();
		//Key format is: <metadata context>:<Class name>:<attribute name>
		caDSRMetadata cMetadata = new caDSRMetadata();
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("/"+fileName);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(in);
			Element rootEle = doc.getRootElement(); // <metadata>
			List<Element> classElements = rootEle.getChildren();
			for (Element classEle : classElements) // <class>
			{
				String className = classEle.getAttributeValue("name");
				List<String> attrList = new ArrayList<String>();
				List<Element> attrElements = classEle.getChildren();
				if (attrElements.size() > 0)
					classList.add(className);
				for (Element attrEle : attrElements) // <attribute>
				{
					String classAttrName = attrEle.getAttributeValue("name");
					MetadataElement mData = new MetadataElement();
					mData.setName(classAttrName);
					mData.setFullName(className + "." + classAttrName);
					mData.setClassName(className);
					List<Attribute> attrs = attrEle.getAttributes();
					for (Attribute attr : attrs) {
						String attrName = attr.getName();
						String attrValue = attr.getValue();
						if (attrName.equals("NCI_CADSR_DE_ID"))
							mData.setPublicId(attrValue);
						else if (attrName.equals("NCI_CADSR_DE_VERSION"))
							mData.setVersionId(attrValue);
						
						if(!attrName.equals("name"))
						mData.addAttribute(attrName, attrValue);
					}
					attrList.add(classAttrName);
					cMetadata.addMetadataElement(className, classAttrName, mData);
				}
				
				attributeMap.put(caDSRMetadata.CONTEXT_NAME+ ":" + className, attrList);
			}
			metadata.put(caDSRMetadata.CONTEXT_NAME, cMetadata);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		classMap.put(caDSRMetadata.CONTEXT_NAME, classList);
		return metadata;
	}

	public Metadata getMetadata(String contextName) {
		return metadata.get(contextName);
	}
	
	public boolean hasMetadata(String metadataContext, String className)
	{
		List classList = classMap.get(metadataContext);
		if(classList == null || classList.size() == 0)
			return false;
		if(classList.contains(className))
			return true;
		else
			return false;
		
	}
	
	public MetadataElement getMetadata(String metadataContext, String className, String attribute)
	{
		@SuppressWarnings("unchecked")
		Metadata mdata = metadata.get(metadataContext);
		if(mdata == null)
			return null;
		
		return mdata.getMetadata(className, attribute);
	}
	
	public List getAttributeList(String metadataContext, String className)
	{
		return attributeMap.get(metadataContext+":"+className);
	}
	
}
