/**
 * Group.java
 *
 */

package gov.nih.nci.system.query.cql;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Binary joint
 */
public class CQLGroup  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    private Collection associationCollection;
    private Collection attributeCollection;
    private Collection groupCollection;
    private CQLLogicalOperator logicOperator;  // attribute

    public CQLGroup() {
    }

    public CQLGroup(Collection associationCollection, Collection attributeCollection, Collection groupCollection, CQLLogicalOperator logicOperator)
	{
		super();
		this.associationCollection = associationCollection;
		this.attributeCollection = attributeCollection;
		this.groupCollection = groupCollection;
		this.logicOperator = logicOperator;
	}

	public Collection getAssociationCollection()
	{
		return associationCollection;
	}

	public void setAssociationCollection(Collection associationCollection)
	{
		this.associationCollection = associationCollection;
	}

	public Collection getAttributeCollection()
	{
		return attributeCollection;
	}

	public void setAttributeCollection(Collection attributeCollection)
	{
		this.attributeCollection = attributeCollection;
	}

	public Collection getGroupCollection()
	{
		return groupCollection;
	}

	public void setGroupCollection(Collection groupCollection)
	{
		this.groupCollection = groupCollection;
	}

	public CQLLogicalOperator getLogicOperator()
	{
		return logicOperator;
	}

	public void setLogicOperator(CQLLogicalOperator logicOperator)
	{
		this.logicOperator = logicOperator;
	}

	public void addAssociation(CQLAssociation association)
	{
		if(association == null) return;
		if(this.associationCollection == null)
			associationCollection = new ArrayList();
		associationCollection.add(association);
	}

	public void addGroup(CQLGroup group)
	{
		if(group == null) return;
		if(this.groupCollection == null)
			groupCollection = new ArrayList();
		groupCollection.add(group);
	}
	
	public void addAttribute(CQLAttribute attribute)
	{
		if(attribute == null) return;
		if(this.attributeCollection == null)
			attributeCollection = new ArrayList();
		attributeCollection.add(attribute);	
	}
}
