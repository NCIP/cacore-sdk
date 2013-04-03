package gov.nih.nci.system.web;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

public class ResourceLink {

	private static final long serialVersionUID = 1L;
	private String relationship;
	private String type;
	private String href;

	public ResourceLink(String relationship, String type, String href)
	{
		this.relationship = relationship;
		this.type = type;
		this.href = href;
	}


	public String getRelationship()
	{
		return this.relationship;
	}


	public void setRelationship(String relationship)
	{
		this.relationship = relationship;
	}

	public String getType()
	{
		return this.type;
	}


	public void setType(String type)
	{
		this.type = type;
	}

	public String getHref()
	{
		return this.href;
	}


	public void setHref(String href)
	{
		this.href = href;
	}


	public String toString()
	{
		return "rel=\""+relationship+"\" type=\""+type+"\" href=\""+href+"\"";
	}
}
