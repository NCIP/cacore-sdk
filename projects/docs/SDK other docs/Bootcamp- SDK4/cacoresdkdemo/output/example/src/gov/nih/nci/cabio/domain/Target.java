package gov.nih.nci.cabio.domain;

import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A gene thought to be at the root of a disease etiology, and which is targeted for therapeutic intervention 
   * in a clinical trial. 
   * 
   */

public  class Target 	implements java.io.Serializable 
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	
		
	/**
   * internal id
   */

	private java.lang.Long id;
	
	/**
Retreives the value of id attribue
   * @return id

   */

	public java.lang.Long getId()
	{
		return id;
	}
	
	/**
Sets the value of id attribue
@param id internal id
   */
	
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	/**
   * The type of the target.
   */

	private java.lang.String type;
	
	/**
Retreives the value of type attribue
   * @return type

   */

	public java.lang.String getType()
	{
		return type;
	}
	
	/**
Sets the value of type attribue
@param type The type of the target.
   */
	
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
		
	/**
   * The name of the target. 
   */

	private java.lang.String name;
	
	/**
Retreives the value of name attribue
   * @return name

   */

	public java.lang.String getName()
	{
		return name;
	}
	
	/**
Sets the value of name attribue
@param name The name of the target. 
   */
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	
		
		
	/**
An associated  collection of Gene object
   */
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	
	/**
Retreives the value of geneCollection attribue
   * @return geneCollection
   */
		
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.geneCollection from gov.nih.nci.cabio.domain.Target as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Target.class.getName());
				log.error("Target:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	/**
Sets the value of geneCollection attribue
@param geneCollection
   */
		
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Target) 
		{
			Target c =(Target)obj; 			 
			Long thisId = getId();		
			
			if(thisId != null && thisId.equals(c.getId()))
				eq = true;
			
			}
			return eq;
		}
		
	public int hashCode()
	{
		int h = 0;
		
		if(getId() != null)
			h += getId().hashCode();
		
		return h;
	}
}