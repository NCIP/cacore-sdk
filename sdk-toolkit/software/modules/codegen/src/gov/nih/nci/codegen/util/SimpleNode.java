package gov.nih.nci.codegen.util;

public class SimpleNode extends Node
{
	private String columnName;
	
	public SimpleNode(String name)
	{
		nodeType = "simple";
		this.name = name;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	
}