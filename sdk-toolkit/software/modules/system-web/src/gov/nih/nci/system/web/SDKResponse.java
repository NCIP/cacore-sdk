package gov.nih.nci.system.web;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import gov.nih.nci.system.web.ResourceLink;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementRef;

@XmlRootElement
public class SDKResponse {

	private static final long serialVersionUID = 1L;

	@XmlElementRef
	private List<ResourceLink> links;

	@XmlElementRef
	private Map<Object, List<ResourceLink>> response;
	private boolean collectionFlag = false;

	public SDKResponse()
	{
	}

	public SDKResponse(Map<Object, List<ResourceLink>> response, List<ResourceLink> links, boolean collectionFlag)
	{
		this.response = response;
		this.links = links;
		this.collectionFlag = collectionFlag;
	}

	public Map<Object, List<ResourceLink>> getResponse()
	{
		return this.response;
	}

	public List<ResourceLink> getLinks() {
		return links;
	}

	public boolean isCollection()
	{
		return collectionFlag;
	}
}
