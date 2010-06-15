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
   * An object representing the various names (scientific, common, abbreviated, etc.) for a species 
   * associated with a specific instance of a Gene, Chromosome. 
   * 
   */

public  class Taxon 	implements java.io.Serializable 
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
   * commonly used scientific term to identify this taxon.
   */

	private java.lang.String scientificName;
	
	/**
Retreives the value of scientificName attribue
   * @return scientificName

   */

	public java.lang.String getScientificName()
	{
		return scientificName;
	}
	
	/**
Sets the value of scientificName attribue
@param scientificName commonly used scientific term to identify this taxon.
   */
	
	public void setScientificName(java.lang.String scientificName)
	{
		this.scientificName = scientificName;
	}
	
		
	/**
   * color or type
   */

	private java.lang.String ethnicityOrStrain;
	
	/**
Retreives the value of ethnicityOrStrain attribue
   * @return ethnicityOrStrain

   */

	public java.lang.String getEthnicityOrStrain()
	{
		return ethnicityOrStrain;
	}
	
	/**
Sets the value of ethnicityOrStrain attribue
@param ethnicityOrStrain color or type
   */
	
	public void setEthnicityOrStrain(java.lang.String ethnicityOrStrain)
	{
		this.ethnicityOrStrain = ethnicityOrStrain;
	}
	
		
	/**
   * common abbreviation for this taxon.
   */

	private java.lang.String abbreviation;
	
	/**
Retreives the value of abbreviation attribue
   * @return abbreviation

   */

	public java.lang.String getAbbreviation()
	{
		return abbreviation;
	}
	
	/**
Sets the value of abbreviation attribue
@param abbreviation common abbreviation for this taxon.
   */
	
	public void setAbbreviation(java.lang.String abbreviation)
	{
		this.abbreviation = abbreviation;
	}
	
		
	/**
   * accepted common name.
   */

	private java.lang.String commonName;
	
	/**
Retreives the value of commonName attribue
   * @return commonName

   */

	public java.lang.String getCommonName()
	{
		return commonName;
	}
	
	/**
Sets the value of commonName attribue
@param commonName accepted common name.
   */
	
	public void setCommonName(java.lang.String commonName)
	{
		this.commonName = commonName;
	}
	
		
	/**
   * boolean value indicating if this is the preferred taxon.
   */

	private java.lang.Boolean isPreferred;
	
	/**
Retreives the value of isPreferred attribue
   * @return isPreferred

   */

	public java.lang.Boolean getIsPreferred()
	{
		return isPreferred;
	}
	
	/**
Sets the value of isPreferred attribue
@param isPreferred boolean value indicating if this is the preferred taxon.
   */
	
	public void setIsPreferred(java.lang.Boolean isPreferred)
	{
		this.isPreferred = isPreferred;
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
				String hql = "select parent.geneCollection from gov.nih.nci.cabio.domain.Taxon as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getGeneCollection throws exception ... ...",ex);
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
		
	
	
	
		
		
	/**
An associated  collection of Chromosome object
   */
		
	private java.util.Collection chromosomeCollection = new java.util.HashSet();
	
	/**
Retreives the value of chromosomeCollection attribue
   * @return chromosomeCollection
   */
		
	public java.util.Collection getChromosomeCollection()
	{
		if (chromosomeCollection==null || chromosomeCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.chromosomeCollection from gov.nih.nci.cabio.domain.Taxon as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Chromosome");				 
				chromosomeCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Taxon.class.getName());
				log.error("Taxon:getChromosomeCollection throws exception ... ...",ex);
			}
		}	
		return chromosomeCollection;
	}
	
	/**
Sets the value of chromosomeCollection attribue
@param chromosomeCollection
   */
		
	public void setChromosomeCollection(java.util.Collection chromosomeCollection)
	{
		this.chromosomeCollection = chromosomeCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Taxon) 
		{
			Taxon c =(Taxon)obj; 			 
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