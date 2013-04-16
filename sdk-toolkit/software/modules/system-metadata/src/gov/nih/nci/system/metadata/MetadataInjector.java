/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.metadata;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public final class MetadataInjector {

	public static Document injectMetadata(MetadataCache metadata, String metadataContext, Document xml, String className)
	{
		if(!metadata.hasMetadata(metadataContext, className))
		{
			return xml;
		}
		
		List<String> attributeList = metadata.getAttributeList(metadataContext, className);
		if(attributeList == null || attributeList.size() == 0)
		{
			return xml;
		}
		
		Element root = (Element)xml.getRootElement().clone();
		for(String attribute: attributeList)
		{
			MetadataElement attrMetadata = metadata.getMetadata(metadataContext, className, attribute);
			if(attrMetadata == null)
				continue;

			Attribute attr = root.getAttribute(attribute);
			String attrValue = null;
			
			if(attr != null)
			{
				attrValue = attr.getValue();
				Element attrEle = new Element(attr.getName(), root.getNamespace());
				attrEle.addContent(attrValue);
				Map<String, String> attrs = attrMetadata.getAttributes();
				Iterator iter = attrs.keySet().iterator();
				while(iter.hasNext())
				{
					String mName = (String)iter.next();
					String mValue = (String)attrs.get(mName);
					attrEle.setAttribute(new Attribute(mName, mValue));
				}
				root.removeAttribute(attribute);
				root.addContent(attrEle);				
			}
			else
			{
/*				try {
					XMLOutputter outputter = new XMLOutputter();
				      outputter.output(xml, System.out);       
				    }
				    catch (IOException e) {
				      System.err.println(e);
				    }
*/			

				Element subEle = root.getChild(attribute, root.getNamespace());
				if(subEle == null)
					continue;
				Map<String, String> attrs = attrMetadata.getAttributes();
				Iterator iter = attrs.keySet().iterator();
				while(iter.hasNext())
				{
					String mName = (String)iter.next();
					String mValue = (String)attrs.get(mName);
					subEle.setAttribute(new Attribute(mName, mValue));
				}
			}
		}
		org.jdom.Document xmlDoc = new org.jdom.Document(root);
		
		return xmlDoc;
	}
}
