package gov.nih.nci.common.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author zengje
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NestedCriteria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String targetObjectName;
	private String sourceObjectName;
	private String roleName;
	private boolean targetCollection;
	
	
	
//	private HashMap criterionMap;
	
//	private Object sourceObject;
	
	private List sourceObjectList = new ArrayList();
	
	private NestedCriteria internalNestedCriteria;
	
	protected boolean caseSensitivityFlag;
	
	public void setTargetObjectName(String targetName)
	{
		this.targetObjectName = targetName;
	}
	
	public String getTargetObjectName()
	{
		return this.targetObjectName;
	}
	
	public void setSourceObjectName(String sourceName)
	{
		this.sourceObjectName = sourceName;
	}
	
	public String getSourceName()
	{
		return this.sourceObjectName;
	}
	
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	
	public String getRoleName()
	{
		return this.roleName;
	}
	
	public void setInternalNestedCriteria(NestedCriteria nestedCriteria)
	{
		this.internalNestedCriteria = nestedCriteria;
	}
	
	public NestedCriteria getInternalNestedCriteria()
	{
		return this.internalNestedCriteria;
	}
	
//	public void setSourceObject(Object obj)
//	{
//		this.sourceObject = obj;
//	}
//	
//	public Object getSourceObject()
//	{
//		return this.sourceObject;
//	}
	public void setSourceObjectList(List objList)
	{
		this.sourceObjectList = objList;
	}
	
	public List getSourceObjectList()
	{
		return this.sourceObjectList;
	}
	public void addSourceObject(Object obj)
	{
		sourceObjectList.add(obj);
	}
	
	public void setSearchCaseSensitivity(boolean caseSensitivity)
	{
		this.caseSensitivityFlag = caseSensitivity;
	}

	public boolean isTargetCollection()
	{
		return targetCollection;
	}

	public void setTargetCollection(boolean targetCollection)
	{
		this.targetCollection = targetCollection;
	}

	public NestedCriteria(String sourceObjectName, String targetObjectName, String roleName, NestedCriteria internalNestedCriteria)
	{
		super();
		this.sourceObjectName = sourceObjectName;
		this.targetObjectName = targetObjectName;
		this.roleName = roleName;
		this.internalNestedCriteria = internalNestedCriteria;
	}
	public NestedCriteria()
	{
	}
	
}

