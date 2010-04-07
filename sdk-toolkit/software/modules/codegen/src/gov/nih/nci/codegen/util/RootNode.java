package gov.nih.nci.codegen.util;

public class RootNode extends ComplexNode
{
	private String parentClassName;
	
	private String selfTableName;
	private String selfTableForeignKey;
	
	private String targetTableName;
	private String targetTablePrimaryKey;
	private String targetTableForeignKey;
	
	private String joinTableName;
	private String joinTableForeignKey;
	private String joinTableInverseKey;
	
	public RootNode(String name)
	{
		super(name);
	}

	public String getParentClassName()
	{
		return parentClassName;
	}

	public void setParentClassName(String parentClassName)
	{
		this.parentClassName = parentClassName;
	}

	public String getSelfTableName()
	{
		return selfTableName;
	}

	public void setSelfTableName(String selfTableName)
	{
		this.selfTableName = selfTableName;
	}

	public String getSelfTableForeignKey()
	{
		return selfTableForeignKey;
	}

	public void setSelfTableForeignKey(String selfTableForeignKey)
	{
		this.selfTableForeignKey = selfTableForeignKey;
	}

	public String getTargetTableName()
	{
		return targetTableName;
	}

	public void setTargetTableName(String targetTableName)
	{
		this.targetTableName = targetTableName;
	}

	public String getTargetTablePrimaryKey()
	{
		return targetTablePrimaryKey;
	}

	public void setTargetTablePrimaryKey(String targetTablePrimaryKey)
	{
		this.targetTablePrimaryKey = targetTablePrimaryKey;
	}

	public String getTargetTableForeignKey()
	{
		return targetTableForeignKey;
	}

	public void setTargetTableForeignKey(String targetTableForeignKey)
	{
		this.targetTableForeignKey = targetTableForeignKey;
	}

	public String getJoinTableName()
	{
		return joinTableName;
	}

	public void setJoinTableName(String joinTableName)
	{
		this.joinTableName = joinTableName;
	}

	public String getJoinTableForeignKey()
	{
		return joinTableForeignKey;
	}

	public void setJoinTableForeignKey(String joinTableForeignKey)
	{
		this.joinTableForeignKey = joinTableForeignKey;
	}

	public String getJoinTableInverseKey()
	{
		return joinTableInverseKey;
	}

	public void setJoinTableInverseKey(String joinTableInverseKey)
	{
		this.joinTableInverseKey = joinTableInverseKey;
	}
	
	
}