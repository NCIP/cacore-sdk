package gov.nih.nci.codegen.util;


public abstract class Node
{
	protected String name;
	protected String nodeType;

	private String isoClassName;
	public String getIsoClassName()
	{
		return isoClassName;
	}

	public void setIsoClassName(String isoClassName)
	{
		this.isoClassName = isoClassName;
	}
	
	public String getName()
	{
		return name;
	}

	public String getNodeType()
	{
		return nodeType;
	}
}