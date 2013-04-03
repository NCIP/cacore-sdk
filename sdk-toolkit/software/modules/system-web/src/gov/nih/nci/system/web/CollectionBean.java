package gov.nih.nci.system.web;

import gov.nih.nci.system.web.ResourceLink;
import java.util.Collection;

public interface CollectionBean extends java.io.Serializable
{
	public String getType();
	public Collection<ResourceLink> getLinks();
}