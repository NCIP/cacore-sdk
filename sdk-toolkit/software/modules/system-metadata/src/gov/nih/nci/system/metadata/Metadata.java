package gov.nih.nci.system.metadata;

import java.util.List;

public interface Metadata {
	public String getMetadataContextName();

	public MetadataElement getMetadata(String className, String attribute);
}
