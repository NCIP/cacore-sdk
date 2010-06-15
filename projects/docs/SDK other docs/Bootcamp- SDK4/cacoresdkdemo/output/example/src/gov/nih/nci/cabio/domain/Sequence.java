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
   * An object representation of a gene sequence; provides access to the clones from which it was derived, 
   * the ASCII representation of the residues it contains, and the sequence ID. 
   * 
   */

public  class Sequence 	implements java.io.Serializable 
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
   * The length of the sequence.
   */

	private java.lang.Long length;
	
	/**
Retreives the value of length attribue
   * @return length

   */

	public java.lang.Long getLength()
	{
		return length;
	}
	
	/**
Sets the value of length attribue
@param length The length of the sequence.
   */
	
	public void setLength(java.lang.Long length)
	{
		this.length = length;
	}
	
		
	/**
   * Whether the sequence is a reference sequence.
   */

	private java.lang.Boolean isReferenceSequence;
	
	/**
Retreives the value of isReferenceSequence attribue
   * @return isReferenceSequence

   */

	public java.lang.Boolean getIsReferenceSequence()
	{
		return isReferenceSequence;
	}
	
	/**
Sets the value of isReferenceSequence attribue
@param isReferenceSequence Whether the sequence is a reference sequence.
   */
	
	public void setIsReferenceSequence(java.lang.Boolean isReferenceSequence)
	{
		this.isReferenceSequence = isReferenceSequence;
	}
	
		
	/**
   * Indicates whether this sequence is EST or MRNA.
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
@param description Indicates whether this sequence is EST or MRNA.
   */
	
	public void setDescription(java.lang.String description)
	{
		this.description = description;
	}
	
		
	/**
   * The ASCII string for the sequence object.
   */

	private java.lang.String asciiString;
	
	/**
Retreives the value of asciiString attribue
   * @return asciiString

   */

	public java.lang.String getAsciiString()
	{
		return asciiString;
	}
	
	/**
Sets the value of asciiString attribue
@param asciiString The ASCII string for the sequence object.
   */
	
	public void setAsciiString(java.lang.String asciiString)
	{
		this.asciiString = asciiString;
	}
	
		
	/**
   * The accession number of the sequence.
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
@param accessionNumber The accession number of the sequence.
   */
	
	public void setAccessionNumber(java.lang.String accessionNumber)
	{
		this.accessionNumber = accessionNumber;
	}
	
		
	/**
   * The version of the sequence's accession number.
   */

	private java.lang.String accessionNumberVersion;
	
	/**
Retreives the value of accessionNumberVersion attribue
   * @return accessionNumberVersion

   */

	public java.lang.String getAccessionNumberVersion()
	{
		return accessionNumberVersion;
	}
	
	/**
Sets the value of accessionNumberVersion attribue
@param accessionNumberVersion The version of the sequence's accession number.
   */
	
	public void setAccessionNumberVersion(java.lang.String accessionNumberVersion)
	{
		this.accessionNumberVersion = accessionNumberVersion;
	}
	
		
	/**
   * The type of Sequence: either EST or MRNA.
   */

	private java.lang.Long type;
	
	/**
Retreives the value of type attribue
   * @return type

   */

	public java.lang.Long getType()
	{
		return type;
	}
	
	/**
Sets the value of type attribue
@param type The type of Sequence: either EST or MRNA.
   */
	
	public void setType(java.lang.Long type)
	{
		this.type = type;
	}
	
	
		
		
	/**
An associated Clone object
   */
		
	private gov.nih.nci.cabio.domain.Clone clone;

	/**
Retreives the value of clone attribue
   * @return clone
   */
		
	public gov.nih.nci.cabio.domain.Clone getClone()
	{
			
		if(clone==null ||  clone.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.clone from gov.nih.nci.cabio.domain.Sequence as parent where parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Clone");				 
				if (resultList!=null && resultList.size()>0) 
					clone = (gov.nih.nci.cabio.domain.Clone)resultList.get(0);
				else
					clone = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Sequence.class.getName());
				log.error("Sequence:getClone throws exception ... ...",ex);
			}
		}
		return clone;	
					
	}

	/**
Sets the value of clone attribue
@param clone
   */
		
	public void setClone(gov.nih.nci.cabio.domain.Clone clone)
	{
		this.clone = clone;
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
				String hql = "select parent.geneCollection from gov.nih.nci.cabio.domain.Sequence as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Sequence.class.getName());
				log.error("Sequence:getGeneCollection throws exception ... ...",ex);
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
		if(obj instanceof Sequence) 
		{
			Sequence c =(Sequence)obj; 			 
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