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
   * An object representing a CGAP library; provides access to information about: the tissue sample 
   * and its method of preparation, the library protocol that was used, the clones contained in the library, 
   * and the sequence information derived from the library. 
   * 
   */

public  class Library 	implements java.io.Serializable 
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	
		
	/**
   * internal Id
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
@param id internal Id
   */
	
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	/**
   * The type for the library object.
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
@param type The type for the library object.
   */
	
	public void setType(java.lang.String type)
	{
		this.type = type;
	}
	
		
	/**
   * The library name for the library object.
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
@param name The library name for the library object.
   */
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
		
	/**
   * The keyword for the library.
   */

	private java.lang.String keyword;
	
	/**
Retreives the value of keyword attribue
   * @return keyword

   */

	public java.lang.String getKeyword()
	{
		return keyword;
	}
	
	/**
Sets the value of keyword attribue
@param keyword The keyword for the library.
   */
	
	public void setKeyword(java.lang.String keyword)
	{
		this.keyword = keyword;
	}
	
		
	/**
   * The description for the library.
   */

	private java.lang.String description;
	
	/**
Retreives the value of description attribue
   * @return description

   */

	public java.lang.String getDescription()
	{
		return description;
	}
	
	/**
Sets the value of description attribue
@param description The description for the library.
   */
	
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
		
	/**
   * The R Site 1 for the library.
   */

	private java.lang.String RSite1;
	
	/**
Retreives the value of RSite1 attribue
   * @return RSite1

   */

	public java.lang.String getRSite1()
	{
		return RSite1;
	}
	
	/**
Sets the value of RSite1 attribue
@param RSite1 The R Site 1 for the library.
   */
	
	public void setRSite1(java.lang.String RSite1)
	{
		this.RSite1 = RSite1;
	}
	
		
	/**
   * The R Site 2 for the library.
   */

	private java.lang.String RSite2;
	
	/**
Retreives the value of RSite2 attribue
   * @return RSite2

   */

	public java.lang.String getRSite2()
	{
		return RSite2;
	}
	
	/**
Sets the value of RSite2 attribue
@param RSite2 The R Site 2 for the library.
   */
	
	public void setRSite2(java.lang.String RSite2)
	{
		this.RSite2 = RSite2;
	}
	
		
	/**
   * The Unigene identification for the library.
   */

	private java.lang.Long unigeneId;
	
	/**
Retreives the value of unigeneId attribue
   * @return unigeneId

   */

	public java.lang.Long getUnigeneId()
	{
		return unigeneId;
	}
	
	/**
Sets the value of unigeneId attribue
@param unigeneId The Unigene identification for the library.
   */
	
	public void setUnigeneId(java.lang.Long unigeneId)
	{
		this.unigeneId = unigeneId;
	}
	
		
	/**
   * The creation date for the library.
   */

	private java.util.Date creationDate;
	
	/**
Retreives the value of creationDate attribue
   * @return creationDate

   */

	public java.util.Date getCreationDate()
	{
		return creationDate;
	}
	
	/**
Sets the value of creationDate attribue
@param creationDate The creation date for the library.
   */
	
	public void setCreationDate(java.util.Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
		
	/**
   * The lab host for the library.
   */

	private java.lang.String labHost;
	
	/**
Retreives the value of labHost attribue
   * @return labHost

   */

	public java.lang.String getLabHost()
	{
		return labHost;
	}
	
	/**
Sets the value of labHost attribue
@param labHost The lab host for the library.
   */
	
	public void setLabHost(java.lang.String labHost)
	{
		this.labHost = labHost;
	}
	
		
	/**
   * The number of clones for the library object.
   */

	private java.lang.Long clonesToDate;
	
	/**
Retreives the value of clonesToDate attribue
   * @return clonesToDate

   */

	public java.lang.Long getClonesToDate()
	{
		return clonesToDate;
	}
	
	/**
Sets the value of clonesToDate attribue
@param clonesToDate The number of clones for the library object.
   */
	
	public void setClonesToDate(java.lang.Long clonesToDate)
	{
		this.clonesToDate = clonesToDate;
	}
	
		
	/**
   * The number of the sequences associated with the library.
   */

	private java.lang.Long sequencesToDate;
	
	/**
Retreives the value of sequencesToDate attribue
   * @return sequencesToDate

   */

	public java.lang.Long getSequencesToDate()
	{
		return sequencesToDate;
	}
	
	/**
Sets the value of sequencesToDate attribue
@param sequencesToDate The number of the sequences associated with the library.
   */
	
	public void setSequencesToDate(java.lang.Long sequencesToDate)
	{
		this.sequencesToDate = sequencesToDate;
	}
	
	
		
		
	/**
An associated  collection of Clone object
   */
		
	private java.util.Collection cloneCollection = new java.util.HashSet();
	
	/**
Retreives the value of cloneCollection attribue
   * @return cloneCollection
   */
		
	public java.util.Collection getCloneCollection()
	{
		if (cloneCollection==null || cloneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.cloneCollection from gov.nih.nci.cabio.domain.Library as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Clone");				 
				cloneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getCloneCollection throws exception ... ...",ex);
			}
		}	
		return cloneCollection;
	}
	
	/**
Sets the value of cloneCollection attribue
@param cloneCollection
   */
		
	public void setCloneCollection(java.util.Collection cloneCollection)
	{
		this.cloneCollection = cloneCollection;
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
				String hql = "select parent.geneCollection from gov.nih.nci.cabio.domain.Library as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Library.class.getName());
				log.error("Library:getGeneCollection throws exception ... ...",ex);
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
		if(obj instanceof Library) 
		{
			Library c =(Library)obj; 			 
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