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
   * An object used to hold information pertaining to I.M.A.G.E. clones; provides access to sequence 
   * information, associated trace files, and the clone's library. 
   * 
   */

public  class Clone 	implements java.io.Serializable 
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	
		
	/**
   * internal Id
   */

	protected java.lang.Long id;
	
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
   * Whether the clone has been verified.
   */

	public java.lang.Boolean verified;
	
	/**
Retreives the value of verified attribue
   * @return verified

   */

	public java.lang.Boolean getVerified()
	{
		return verified;
	}
	
	/**
Sets the value of verified attribue
@param verified Whether the clone has been verified.
   */
	
	public void setVerified(java.lang.Boolean verified)
	{
		this.verified = verified;
	}
	
		
	/**
   * The insert size of the clone.
   */

	private java.lang.Long insertSize;
	
	/**
Retreives the value of insertSize attribue
   * @return insertSize

   */

	public java.lang.Long getInsertSize()
	{
		return insertSize;
	}
	
	/**
Sets the value of insertSize attribue
@param insertSize The insert size of the clone.
   */
	
	public void setInsertSize(java.lang.Long insertSize)
	{
		this.insertSize = insertSize;
	}
	
		
	/**
   * The gene bank accession number for the sequences of the clone object.
   */

	private java.lang.String accessionNumber;
	
	/**
Retreives the value of accessionNumber attribue
   * @return accessionNumber

   */

	public java.lang.String getAccessionNumber()
	{
		return accessionNumber;
	}
	
	/**
Sets the value of accessionNumber attribue
@param accessionNumber The gene bank accession number for the sequences of the clone object.
   */
	
	public void setAccessionNumber(java.lang.String accessionNumber)
	{
		this.accessionNumber = accessionNumber;
	}
	
		
	/**
   * The name of the clone.
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
@param name The name of the clone.
   */
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
		
	/**
   * The clone version.
   */

	private java.lang.String version;
	
	/**
Retreives the value of version attribue
   * @return version

   */

	public java.lang.String getVersion()
	{
		return version;
	}
	
	/**
Sets the value of version attribue
@param version The clone version.
   */
	
	public void setVersion(java.lang.String version)
	{
		this.version = version;
	}
	
		
	/**
   * Not populated.
   */

	private java.lang.String strain;
	
	/**
Retreives the value of strain attribue
   * @return strain

   */

	public java.lang.String getStrain()
	{
		return strain;
	}
	
	/**
Sets the value of strain attribue
@param strain Not populated.
   */
	
	public void setStrain(java.lang.String strain)
	{
		this.strain = strain;
	}
	
	
		
		
	/**
An associated Library object
   */
		
	private gov.nih.nci.cabio.domain.Library library;

	/**
Retreives the value of library attribue
   * @return library
   */
		
	public gov.nih.nci.cabio.domain.Library getLibrary()
	{
			
		if(library==null ||  library.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.library from gov.nih.nci.cabio.domain.Clone as parent where parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				if (resultList!=null && resultList.size()>0) 
					library = (gov.nih.nci.cabio.domain.Library)resultList.get(0);
				else
					library = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getLibrary throws exception ... ...",ex);
			}
		}
		return library;	
					
	}

	/**
Sets the value of library attribue
@param library
   */
		
	public void setLibrary(gov.nih.nci.cabio.domain.Library library)
	{
		this.library = library;
	}
		
	
	
	
		
		
	/**
An associated  collection of Sequence object
   */
		
	private java.util.Collection sequenceCollection = new java.util.HashSet();
	
	/**
Retreives the value of sequenceCollection attribue
   * @return sequenceCollection
   */
		
	public java.util.Collection getSequenceCollection()
	{
		if (sequenceCollection==null || sequenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.sequenceCollection from gov.nih.nci.cabio.domain.Clone as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Sequence");				 
				sequenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Clone.class.getName());
				log.error("Clone:getSequenceCollection throws exception ... ...",ex);
			}
		}	
		return sequenceCollection;
	}
	
	/**
Sets the value of sequenceCollection attribue
@param sequenceCollection
   */
		
	public void setSequenceCollection(java.util.Collection sequenceCollection)
	{
		this.sequenceCollection = sequenceCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Clone) 
		{
			Clone c =(Clone)obj; 			 
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